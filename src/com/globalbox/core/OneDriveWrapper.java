/**
 * 
 */
package com.globalbox.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.globalbox.constants.OneDriveConstants;
import com.globalbox.interfaces.OneDriveCallback;
import com.globalbox.model.onedrive.FileStructure;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.microsoft.live.LiveAuthClient;
import com.microsoft.live.LiveAuthException;
import com.microsoft.live.LiveAuthListener;
import com.microsoft.live.LiveConnectClient;
import com.microsoft.live.LiveConnectSession;
import com.microsoft.live.LiveDownloadOperation;
import com.microsoft.live.LiveDownloadOperationListener;
import com.microsoft.live.LiveOperation;
import com.microsoft.live.LiveOperationException;
import com.microsoft.live.LiveOperationListener;
import com.microsoft.live.LiveStatus;

/**
 * @author darshanbidkar
 *
 */
public class OneDriveWrapper {

	private LiveAuthClient mLiveAuthClient;
	private LiveConnectClient mClient;
	private Context mContext;
	private static OneDriveWrapper sOneDriveWrapper;
	private LiveConnectSession session;

	public static synchronized OneDriveWrapper getInstance(Activity activity) {
		if (OneDriveWrapper.sOneDriveWrapper == null)
			OneDriveWrapper.sOneDriveWrapper = new OneDriveWrapper(activity);
		return OneDriveWrapper.sOneDriveWrapper;
	}

	/**
	 * 
	 */
	private OneDriveWrapper(final Activity activity) {
		this.mContext = activity.getApplicationContext();
		this.mLiveAuthClient = new LiveAuthClient(activity,
				OneDriveConstants.CLIENT_ID);
		this.mLiveAuthClient.initialize(OneDriveConstants.SCOPES,
				new LiveAuthListener() {

					@Override
					public void onAuthError(LiveAuthException arg0, Object arg1) {

					}

					@Override
					public void onAuthComplete(LiveStatus arg0,
							LiveConnectSession arg1, Object arg2) {
						if (arg0 == LiveStatus.CONNECTED) {
							session = arg1;
							mClient = new LiveConnectClient(session);
						} else {
							signIn(activity);
						}

					}
				});
		// this.mLiveAuthClient.login(activity, OneDriveConstants.SCOPES, this);

	}

	public void signIn(final Activity activity) {
		mLiveAuthClient.login(activity, OneDriveConstants.SCOPES,
				new LiveAuthListener() {

					@Override
					public void onAuthComplete(LiveStatus arg0,
							LiveConnectSession arg1, Object arg2) {
						mClient = new LiveConnectClient(arg1);
					}

					@Override
					public void onAuthError(LiveAuthException arg0, Object arg1) {
					}
				});
	}

	public void fetchFiles(final OneDriveCallback callBack, String path) {
		final ArrayList<FileStructure> filesMeta = new ArrayList<FileStructure>();
		this.mClient.getAsync(path + "/files", new LiveOperationListener() {

			@Override
			public void onError(LiveOperationException arg0, LiveOperation arg1) {
				Toast.makeText(OneDriveWrapper.this.mContext,
						"Error performing operation...", Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public void onComplete(LiveOperation arg0) {
				Log.d("files from one drive", arg0.getResult().toString());
				JSONObject object = arg0.getResult();
				if (object.has("data")) {
					try {
						FileStructure[] files = new Gson().fromJson(arg0
								.getResult().getJSONArray("data").toString(),
								FileStructure[].class);
						filesMeta.addAll(Arrays.asList(files));
					} catch (JsonSyntaxException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					FileStructure f = new Gson().fromJson(arg0.getResult()
							.toString(), FileStructure.class);
					filesMeta.add(f);
				}
				callBack.listFiles(filesMeta);
			}
		});
	}

	public void getFile(String id, String fileName,
			final OneDriveCallback callBack) {
		final File f = new File(Environment.getExternalStorageDirectory()
				+ "/GlobalBox/" + fileName);
		this.mClient.downloadAsync(id + "/content", f,
				new LiveDownloadOperationListener() {

					@Override
					public void onDownloadProgress(int arg0, int arg1,
							LiveDownloadOperation arg2) {

					}

					@Override
					public void onDownloadFailed(LiveOperationException arg0,
							LiveDownloadOperation arg1) {

					}

					@Override
					public void onDownloadCompleted(LiveDownloadOperation arg0) {
						callBack.downloadCompleted(f);
					}
				});
	}
}
