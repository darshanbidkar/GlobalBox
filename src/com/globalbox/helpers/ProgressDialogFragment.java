/**
 * A progress dialog represented in a dialog fragment
 */
package com.globalbox.helpers;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author darshan
 * 
 */
public class ProgressDialogFragment extends DialogFragment {

	public ProgressDialogFragment() {
	}

	/**
	 * Creates a new instance of progress dialog
	 * 
	 * @param title
	 *            the title to be displayed
	 * @return returns a new instance of this class
	 */
	public static ProgressDialogFragment newInstance(String title) {
		ProgressDialogFragment frag = new ProgressDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		ProgressDialog progressDialog = new ProgressDialog(super.getActivity());
		String title = super.getArguments().getString("title");
		progressDialog.setMessage(title);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setIndeterminate(true);
		return progressDialog;
	}

}
