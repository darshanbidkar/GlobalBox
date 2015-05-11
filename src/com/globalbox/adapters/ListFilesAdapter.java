/**
 * 
 */
package com.globalbox.adapters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI.Entry;
import com.globalbox.R;
import com.globalbox.constants.AccountConstants;
import com.globalbox.constants.OneDriveConstants;
import com.globalbox.core.DropboxWrapper;
import com.globalbox.helpers.ProgressDialogFragment;
import com.globalbox.interfaces.OnPagination;
import com.globalbox.interfaces.OneDriveCallback;
import com.globalbox.model.onedrive.FileStructure;

/**
 * @author darshanbidkar
 *
 */
public class ListFilesAdapter extends ArrayAdapter<Object> {

	private LayoutInflater mLayoutInflater;
	private Stack<ArrayList<Object>> mStack;
	private OnPagination mOnPagination;
	private ArrayList<Object> mCurrentArrayList;
	private Context mContext;
	private FragmentManager mManager;
	private OneDriveCallback mOneDriveCallback;

	/**
	 * @param context
	 * @param resource
	 */
	public ListFilesAdapter(Context context, ArrayList<Object> files,
			int resource, OnPagination onPagination, FragmentManager manager,
			OneDriveCallback oneDriveCallback) {
		super(context, resource, files);
		this.mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mStack = new Stack<ArrayList<Object>>();
		this.mOnPagination = onPagination;
		this.mContext = context;
		this.mManager = manager;
		this.mCurrentArrayList = new ArrayList<Object>();
		this.mOneDriveCallback = oneDriveCallback;
	}

	@Override
	public void addAll(Collection<? extends Object> collection) {
		super.addAll(collection);
		this.mCurrentArrayList.addAll(collection);
	}

	private static class ViewHolder {
		TextView mFileName;
		ImageView mIconView, mSourceIcon;
		View view;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		Object currentObject = super.getItem(position);

		if (convertView == null) {
			convertView = this.mLayoutInflater.inflate(R.layout.cell_files,
					null);
			viewHolder = new ViewHolder();
			viewHolder.mFileName = (TextView) convertView
					.findViewById(R.id.cell_file_name);
			viewHolder.mIconView = (ImageView) convertView
					.findViewById(R.id.cell_file_icon);
			viewHolder.mSourceIcon = (ImageView) convertView
					.findViewById(R.id.cell_file_src);
			viewHolder.view = convertView.findViewById(R.id.linear_layout1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (position % 2 == 0)
			viewHolder.view.setBackgroundColor(Color.parseColor("#EAEAEA"));
		else
			viewHolder.view.setBackgroundColor(Color.parseColor("#F9F9F9"));

		if (currentObject instanceof Entry)
			this.processDropboxFile(viewHolder, (Entry) currentObject);
		else if (currentObject instanceof FileStructure)
			this.processOneDriveFile(viewHolder, (FileStructure) currentObject);
		return convertView;
	}

	private void processOneDriveFile(ViewHolder viewHolder, FileStructure obj) {
		viewHolder.mFileName.setText(obj.getName());
		Log.d("Icon type", obj.getType());
		if (obj.getType().equalsIgnoreCase(OneDriveConstants.TYPE_FOLDER)
				|| obj.getType().equalsIgnoreCase(OneDriveConstants.TYPE_ALBUM))
			viewHolder.mIconView.setImageResource(R.drawable.folder);
		else
			viewHolder.mIconView.setImageResource(R.drawable.file);
		viewHolder.mSourceIcon.setImageResource(R.drawable.skydrive);
	}

	private void processDropboxFile(ViewHolder viewHolder, Entry entry) {
		viewHolder.mSourceIcon.setImageResource(R.drawable.dropbox);
		viewHolder.mFileName.setText(entry.fileName());
		if (entry.isDir)
			viewHolder.mIconView.setImageResource(R.drawable.folder);
		else
			viewHolder.mIconView.setImageResource(R.drawable.file);
	}

	public void processClick(long id) {
		Object object = super.getItem((int) id);
		if (object instanceof Entry) {
			Entry entry = (Entry) object;
			if (entry.isDir) {
				this.mStack.push(this.mCurrentArrayList);
				this.mCurrentArrayList = new ArrayList<Object>();
				super.clear();
				this.mOnPagination.onMoreDropboxFiles(entry.path);
			} else {
				String s[] = { entry.path, entry.fileName() };
				new FetchFile(entry.mimeType, AccountConstants.DROPBOX)
						.execute(s);
			}
		} else if (object instanceof FileStructure) {
			FileStructure file = (FileStructure) object;
			if (file.getType().equalsIgnoreCase(OneDriveConstants.TYPE_FOLDER)
					|| file.getType().equalsIgnoreCase(
							OneDriveConstants.TYPE_ALBUM)) {
				this.mStack.push(this.mCurrentArrayList);
				this.mCurrentArrayList = new ArrayList<Object>();
				super.clear();
				this.mOnPagination.onMoreOnedriveFiles(file.getId());
			} else {
				this.mOneDriveCallback.initiateDownload(file.getId(),
						file.getName(), this.mOneDriveCallback);
			}
		}
		super.notifyDataSetChanged();
	}

	public boolean processBack() {
		if (this.mStack.isEmpty())
			return false;
		this.mCurrentArrayList = new ArrayList<Object>();
		super.clear();
		this.addAll(this.mStack.pop());
		super.notifyDataSetChanged();
		return true;
	}

	public void openFile(File file, String mimeType) {
		try {
			Log.d(super.getClass().getSimpleName(), mimeType);
			Intent intent = new Intent();
			intent.setAction(android.content.Intent.ACTION_VIEW);
			Log.d("Mime", mimeType);
			if (mimeType.equalsIgnoreCase("*")) {
				switch (file.getName().substring(
						file.getName().lastIndexOf(".") + 1,
						file.getName().length())) {
				case "pdf":
					mimeType = "application/pdf";
					break;
				case "jpg":
					mimeType = "image/jpg";
					break;
				case "mp4":
					mimeType = "video/mp4";
					break;
				case "zip":
					mimeType = "application/zip";
					break;
				default:
					mimeType = "*/*";
				}
			}
			Log.d("Mime", mimeType);
			intent.setDataAndType(Uri.fromFile(file), mimeType);
			this.mContext.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(this.mContext, "No app present to open file...",
					Toast.LENGTH_LONG).show();
		}
	}

	// To download files
	private class FetchFile extends AsyncTask<String, Void, File> {
		ProgressDialogFragment f;
		String mimeType;
		int which;

		public FetchFile(String mimeType, int which) {
			this.mimeType = mimeType;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			f = ProgressDialogFragment.newInstance("Loading...");
			f.show(ListFilesAdapter.this.mManager, "loading");
		}

		@Override
		protected File doInBackground(String... params) {

			return DropboxWrapper.getInstance(null).getFile(params[0],
					params[1]);

		}

		@Override
		protected void onPostExecute(File result) {
			super.onPostExecute(result);
			f.dismissAllowingStateLoss();
			ListFilesAdapter.this.openFile(result, mimeType);
		}
	}

}
