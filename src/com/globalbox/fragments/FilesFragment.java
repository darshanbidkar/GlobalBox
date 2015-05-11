/**
 * 
 */
package com.globalbox.fragments;

import java.io.File;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.globalbox.R;
import com.globalbox.adapters.ListFilesAdapter;
import com.globalbox.constants.AccountConstants;
import com.globalbox.core.DropboxWrapper;
import com.globalbox.core.OneDriveWrapper;
import com.globalbox.helpers.ProgressDialogFragment;
import com.globalbox.interfaces.OnBackListener;
import com.globalbox.interfaces.OnPagination;
import com.globalbox.interfaces.OneDriveCallback;
import com.globalbox.model.onedrive.FileStructure;

/**
 * @author darshanbidkar
 *
 */
public class FilesFragment extends Fragment implements OnItemClickListener,
		OnBackListener, OnPagination, OneDriveCallback {

	private ListView mFilesList;
	private DropboxWrapper mDropboxWrapper;
	private ListFilesAdapter mListFilesAdapter;
	private OneDriveWrapper mOneDriveWrapper;
	ProgressDialogFragment mProgressDialogFragment;

	/**
	 * 
	 */
	public FilesFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_files,
				container, false);
		this.mFilesList = (ListView) inflatedView.findViewById(R.id.list_files);
		this.mFilesList.setOnItemClickListener(this);
		return inflatedView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (this.mListFilesAdapter == null)
			this.mListFilesAdapter = new ListFilesAdapter(super.getActivity(),
					new ArrayList<Object>(), R.layout.cell_files, this,
					super.getFragmentManager(), this);
		this.mFilesList.setAdapter(this.mListFilesAdapter);
		this.mOneDriveWrapper = OneDriveWrapper
				.getInstance(super.getActivity());
		this.mOneDriveWrapper.fetchFiles(this, "/me/skydrive");

		this.mDropboxWrapper = DropboxWrapper.getInstance(super.getActivity());
		new FileFetcher(AccountConstants.DROPBOX).execute("/");
	}

	private class FileFetcher extends AsyncTask<String, Void, Object> {
		private ProgressDialogFragment progressDialogFragment;
		private String errMessage;
		private int which;

		public FileFetcher(int which) {
			this.which = which;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialogFragment = ProgressDialogFragment
					.newInstance("Loading...");
			progressDialogFragment.show(
					FilesFragment.super.getFragmentManager(), "Loading");
		}

		@Override
		protected Object doInBackground(String... params) {
			try {
				switch (which) {
				case AccountConstants.DROPBOX:
					return FilesFragment.this.mDropboxWrapper
							.getFiles(params[0]);
				case AccountConstants.ONEDRIVE:
					FilesFragment.this.mOneDriveWrapper.fetchFiles(
							FilesFragment.this, params[0]);
					return null;
				}
			} catch (DropboxException e) {
				e.printStackTrace();
				this.errMessage = e.getMessage();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			this.progressDialogFragment.dismissAllowingStateLoss();
			if (result == null) {
			} else {
				if (result instanceof Entry) {
					FilesFragment.this.mListFilesAdapter
							.addAll(((Entry) result).contents);
					FilesFragment.this.mListFilesAdapter.notifyDataSetChanged();
				}
			}

		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		this.mListFilesAdapter.processClick(id);
	}

	@Override
	public void onBackPressed() {
		if (!this.mListFilesAdapter.processBack()) {
			super.getActivity().finish();
		}
	}

	@Override
	public void onMoreDropboxFiles(String path) {
		new FileFetcher(AccountConstants.DROPBOX).execute(path);
	}

	@Override
	public void onMoreOnedriveFiles(String path) {
		new FileFetcher(AccountConstants.ONEDRIVE).execute(path);
	}

	@Override
	public void onMoreGoogledriveFiles(String path) {

	}

	@Override
	public void listFiles(ArrayList<FileStructure> files) {
		this.mListFilesAdapter.addAll(files);
		this.mListFilesAdapter.notifyDataSetChanged();
	}

	@Override
	public void downloadCompleted(File f) {
		this.mProgressDialogFragment.dismissAllowingStateLoss();
		this.mListFilesAdapter.openFile(f, "*");
		
	}

	@Override
	public void initiateDownload(String id, String fname,
			OneDriveCallback oneDriveCallback) {
		this.mProgressDialogFragment=ProgressDialogFragment.newInstance("Loading");
		this.mProgressDialogFragment.show(super.getFragmentManager(), "Loading");
		OneDriveWrapper.getInstance(null).getFile(id,
				fname, this);		
	}

}
