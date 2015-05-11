/**
 * A custom dialog fragment representing an alert dialog
 */
package com.globalbox.dialogfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;

import com.globalbox.interfaces.DialogActionListener;
import com.globalbox.interfaces.DialogOKActionListener;
import com.globalbox.widgets.DialogTextView;

/**
 * @author darshan
 * 
 */
public class AlertDialogFragment extends DialogFragment {

	private DialogActionListener mDialogActionListener;
	private DialogOKActionListener mDialogOKActionListener;

	public AlertDialogFragment() {
	}

	/**
	 * creates a new instance of a dialog fragment to be displayed with Ok
	 * button and callback
	 * 
	 * @param dialogOKActionListener
	 * @param title
	 * @param isQuestion
	 * @return
	 */
	public static AlertDialogFragment newInstance(
			DialogOKActionListener dialogOKActionListener, String title,
			boolean isQuestion) {
		AlertDialogFragment frag = new AlertDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		args.putBoolean("isQuestion", isQuestion);
		frag.setArguments(args);
		frag.mDialogOKActionListener = dialogOKActionListener;
		return frag;
	}

	/**
	 * creates a new instance of a dialog fragment to be displayed with Yes/No
	 * buttons and callbacks
	 * 
	 * @param title
	 *            The integer resource id
	 * @return returns a newly created instance
	 */
	public static AlertDialogFragment newInstance(
			DialogActionListener dialogActionListener, String title,
			boolean isQuestion) {
		AlertDialogFragment frag = new AlertDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		args.putBoolean("isQuestion", isQuestion);
		frag.setArguments(args);
		frag.mDialogActionListener = dialogActionListener;
		return frag;
	}

	/**
	 * creates a new instance of a dialog fragment to be displayed
	 * 
	 * @param title
	 *            The integer resource id
	 * @return returns a newly created instance
	 */
	public static AlertDialogFragment newInstance(int title, boolean isQuestion) {
		AlertDialogFragment frag = new AlertDialogFragment();
		Bundle args = new Bundle();
		args.putInt("title", title);
		args.putBoolean("isQuestion", isQuestion);
		frag.setArguments(args);
		return frag;
	}

	/**
	 * creates a new instance of a dialog fragment to be displayed with Yes/No
	 * buttons and callbacks
	 * 
	 * @param title
	 *            The integer resource id
	 * @return returns a newly created instance
	 */
	public static AlertDialogFragment newInstance(String title,
			boolean isQuestion) {
		AlertDialogFragment frag = new AlertDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		args.putBoolean("isQuestion", isQuestion);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String title = super.getArguments().getString("title");
		// if null, extract string from strings resource
		title = "Loading";
		boolean isQuestion = super.getArguments().getBoolean("isQuestion");

		AlertDialog.Builder builder = new AlertDialog.Builder(
				super.getActivity()).setTitle(title).setCancelable(false);
		/**
		 * Applying custom textView to alertDialog
		 */
		DialogTextView dialogTextView = new DialogTextView(builder.getContext());
		dialogTextView.setSingleLine(false);
		dialogTextView.setText(title);
		dialogTextView.setMaxLines(15);
		dialogTextView.setVerticalScrollBarEnabled(true);
		dialogTextView.setVerticalFadingEdgeEnabled(true);
		dialogTextView
				.setVerticalScrollbarPosition(View.SCROLLBAR_POSITION_RIGHT);
		;
		dialogTextView.setMovementMethod(new ScrollingMovementMethod());
		dialogTextView.setGravity(Gravity.CENTER_HORIZONTAL);
		dialogTextView.setPadding(20, 40, 20, 40);
		dialogTextView.setTextColor(builder.getContext().getResources()
				.getColor(android.R.color.black));
		builder.setCustomTitle(dialogTextView);

		if (isQuestion) {
			builder.setPositiveButton("Yes", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (AlertDialogFragment.this.mDialogActionListener != null) {
						AlertDialogFragment.this.mDialogActionListener
								.onPositiveClick();
					}
				}
			}).setNegativeButton("No", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (AlertDialogFragment.this.mDialogActionListener != null) {
						AlertDialogFragment.this.mDialogActionListener
								.onNegativeClick();
					}
				}
			});
		} else {
			builder.setPositiveButton("Ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					if (AlertDialogFragment.this.mDialogOKActionListener != null) {
						AlertDialogFragment.this.mDialogOKActionListener
								.onOKClick();
					}
				}
			});
		}

		return builder.create();
	}
}
