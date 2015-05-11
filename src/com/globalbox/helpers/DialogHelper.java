/**
 * Helper class responsible for displaying/dimissing dialogs
 */
package com.globalbox.helpers;

import android.support.v4.app.FragmentManager;

import com.globalbox.dialogfragments.AlertDialogFragment;
import com.globalbox.interfaces.DialogActionListener;
import com.globalbox.interfaces.DialogOKActionListener;

/**
 * showing various dialog messages
 * 
 * @author darshan
 * 
 */
public class DialogHelper {
	/**
	 * showing dialog with ok button
	 * 
	 * @param manager
	 * @param message
	 */
	public static void showMessageAlertDialogFragment(FragmentManager manager,
			String message) {
		AlertDialogFragment alertDialogFragment = AlertDialogFragment
				.newInstance(message, false);
		alertDialogFragment.show(manager, message);
	}

	/**
	 * showing dialog with ok button and callback on ok button
	 * 
	 * @param dialogOKActionListener
	 * @param manager
	 * @param message
	 */
	public static void showMessageAlertDialogFragment(
			DialogOKActionListener dialogOKActionListener,
			FragmentManager manager, String message) {
		AlertDialogFragment alertDialogFragment = AlertDialogFragment
				.newInstance(dialogOKActionListener, message, false);
		alertDialogFragment.show(manager, message);
	}

	/**
	 * showing dailog with yes/no option and callback on respective buttons
	 * 
	 * @param dialogActionListener
	 * @param manager
	 * @param message
	 */
	public static void showQuestionAlertDialogFragment(
			DialogActionListener dialogActionListener, FragmentManager manager,
			String message) {
		AlertDialogFragment alertDialogFragment = AlertDialogFragment
				.newInstance(dialogActionListener, message, true);
		alertDialogFragment.show(manager, message);
	}
}
