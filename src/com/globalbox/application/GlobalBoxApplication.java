/**
 * 
 */
package com.globalbox.application;

import android.app.Application;

import com.globalbox.helpers.PreferenceHelper;

/**
 * @author darshanbidkar
 *
 */
public class GlobalBoxApplication extends Application {

	private static GlobalBoxApplication sGlobalBoxApplication;

	/**
	 * 
	 */
	public GlobalBoxApplication() {

	}

	@Override
	public void onCreate() {
		super.onCreate();
		GlobalBoxApplication.sGlobalBoxApplication = this;
		PreferenceHelper.getInstance(GlobalBoxApplication.sGlobalBoxApplication
				.getApplicationContext());
	}

	public static synchronized final GlobalBoxApplication getApplicationInstance() {
		return GlobalBoxApplication.sGlobalBoxApplication;
	}

}
