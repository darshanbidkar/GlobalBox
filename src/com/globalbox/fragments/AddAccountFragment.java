/**
 * 
 */
package com.globalbox.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.globalbox.R;
import com.globalbox.constants.DropboxConstants;
import com.globalbox.core.DropboxWrapper;
import com.globalbox.core.OneDriveWrapper;
import com.globalbox.helpers.PreferenceHelper;
import com.globalbox.interfaces.FragmentSwitcher;
import com.globalbox.interfaces.OnBackListener;

/**
 * @author darshanbidkar
 *
 */
public class AddAccountFragment extends Fragment implements OnBackListener {

	private boolean hasAuthenticated, oneDrive, allAuthDone;

	/**
	 * 
	 */
	public AddAccountFragment() {

	}

	@Override
	public void onStart() {
		super.onStart();
		this.hasAuthenticated = true;
		DropboxWrapper.getInstance(super.getActivity());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_add_account,
				container, false);
		((Button) inflatedView.findViewById(R.id.my_files))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (PreferenceHelper
								.getStringData(DropboxConstants.ACCESS_TOKEN) == null)
							DropboxWrapper.getInstance(getActivity())
									.finishAuthentication();
						((FragmentSwitcher) getActivity())
								.switchToFilesFragment();

					}
				});
		return inflatedView;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (this.hasAuthenticated && !this.oneDrive) {
			OneDriveWrapper.getInstance(super.getActivity());
			this.oneDrive = true;
			this.allAuthDone = true;
		}

	}

	@Override
	public void onBackPressed() {
		super.getActivity().onBackPressed();
	}

	public void done() {
		((FragmentSwitcher) (super.getActivity())).switchToFilesFragment();
	}
}
