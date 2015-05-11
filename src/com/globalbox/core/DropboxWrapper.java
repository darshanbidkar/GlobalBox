/**
 * 
 */
package com.globalbox.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Environment;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AppKeyPair;
import com.globalbox.constants.DropboxConstants;
import com.globalbox.helpers.PreferenceHelper;

/**
 * @author darshanbidkar
 *
 */
public class DropboxWrapper {

	private final DropboxAPI<AndroidAuthSession> mDropboxAPI;
	private final AppKeyPair mAppKeys;
	private final AndroidAuthSession mAuthSession;
	private static DropboxWrapper sDropboxWrapper;

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	private DropboxWrapper(Activity returnActivity) {
		this.mAppKeys = new AppKeyPair(DropboxConstants.APP_KEY,
				DropboxConstants.APP_SECRET_KEY);
		String accessToken = PreferenceHelper
				.getStringData(DropboxConstants.ACCESS_TOKEN);
		// String accessToken=null;
		if (accessToken == null) {
			this.mAuthSession = new AndroidAuthSession(this.mAppKeys,
					DropboxConstants.ACCESS_TYPE);
			this.mDropboxAPI = new DropboxAPI<AndroidAuthSession>(
					this.mAuthSession);
			this.mDropboxAPI.getSession().startOAuth2Authentication(
					returnActivity);

		} else {
			// TODO write code
			this.mAuthSession = new AndroidAuthSession(this.mAppKeys,
					accessToken);
			this.mDropboxAPI = new DropboxAPI<AndroidAuthSession>(
					this.mAuthSession);
		}
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/GlobalBox");
		if (!file.exists())
			file.mkdir();
	}

	public static synchronized DropboxWrapper getInstance(
			Activity returnActivity) {
		if (DropboxWrapper.sDropboxWrapper == null) {
			DropboxWrapper.sDropboxWrapper = new DropboxWrapper(returnActivity);
		}
		return sDropboxWrapper;
	}

	public void finishAuthentication() {
		this.mDropboxAPI.getSession().finishAuthentication();
		PreferenceHelper.saveData(DropboxConstants.ACCESS_TOKEN,
				this.mDropboxAPI.getSession().getOAuth2AccessToken());
	}

	public String getAccessToken() {
		return this.mDropboxAPI.getSession().getOAuth2AccessToken();
	}

	public Entry getFiles(String path) throws DropboxException {
		Entry files = this.mDropboxAPI.metadata(path, 0, null, true, null);
		return files;
	}

	public File getFile(String path, String fileName) {
		FileOutputStream outputStream = null;
		try {
			File file = new File(Environment.getExternalStorageDirectory()
					+ "/GlobalBox/" + fileName);
			if (!file.exists())
				file.createNewFile();
			outputStream = new FileOutputStream(file);
			this.mDropboxAPI.getFile(path, null, outputStream, null);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
