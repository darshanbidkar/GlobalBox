package com.globalbox.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.globalbox.R;
import com.globalbox.fragments.AddAccountFragment;
import com.globalbox.fragments.FilesFragment;
import com.globalbox.interfaces.FragmentSwitcher;
import com.globalbox.interfaces.OnBackListener;

public class HomeActivity extends FragmentActivity implements FragmentSwitcher {

	private AddAccountFragment mAddAccountFragment;
	private FilesFragment mFilesFragment;
	private OnBackListener mOnBackListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		this.mAddAccountFragment = new AddAccountFragment();
		super.getSupportFragmentManager().beginTransaction()
				.replace(R.id.home_container, this.mAddAccountFragment)
				.commit();
		this.mOnBackListener = this.mAddAccountFragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Fragment Swtiching

	@Override
	public void switchToFilesFragment() {
		if (this.mFilesFragment == null)
			this.mFilesFragment = new FilesFragment();
		super.getSupportFragmentManager().beginTransaction()
				.replace(R.id.home_container, this.mFilesFragment).commit();
		this.mOnBackListener = (OnBackListener) this.mFilesFragment;
	}

	@Override
	public void onBackPressed() {
		this.mOnBackListener.onBackPressed();
	}

}
