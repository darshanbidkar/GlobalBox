/**
 * 
 */
package com.globalbox.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.globalbox.constants.SharedPreferenceConstants;

/**
 * @author darshanbidkar
 * 
 */
public class PreferenceHelper {
	private static PreferenceHelper sPreferenceHelper;
	private static Context sContext;
	private static SharedPreferences sSharedPreferences;
	private static SharedPreferences.Editor sEditor;

	/**
	 * This class is used as singleton so private constructor
	 * 
	 * @param context
	 */

	private PreferenceHelper(Context context) {
		if (context == null) {
			Log.d("asdfas", "dsfasdf");
		}
		PreferenceHelper.sContext = context;
		PreferenceHelper.sSharedPreferences = PreferenceHelper.sContext
				.getSharedPreferences(SharedPreferenceConstants.FILE_NAME,
						Context.MODE_PRIVATE);
		PreferenceHelper.sEditor = PreferenceHelper.sSharedPreferences.edit();
	}

	/**
	 * Returns the singleton object for this class
	 * 
	 * @param context
	 *            Context
	 * @return Instance of {@link PreferenceHelper}
	 */
	public static synchronized PreferenceHelper getInstance(Context context) {
		if (PreferenceHelper.sPreferenceHelper == null) {
			PreferenceHelper.sPreferenceHelper = new PreferenceHelper(context);

		}
		return PreferenceHelper.sPreferenceHelper;
	}

	/**
	 * Save string data to persistent storage
	 * 
	 * @param key
	 * @param value
	 */

	public static void saveData(String key, String value) {
		PreferenceHelper.sEditor.putString(key, value);
		PreferenceHelper.sEditor.commit();
	}

	/**
	 * Save integer data to persistent storage
	 * 
	 * @param key
	 * @param value
	 */
	public static void saveData(String key, int value) {
		PreferenceHelper.sEditor.putInt(key, value);
		PreferenceHelper.sEditor.commit();
	}

	/**
	 * Save boolean data to persistent storage
	 * 
	 * @param key
	 * @param value
	 */
	public static void saveData(String key, boolean value) {
		PreferenceHelper.sEditor.putBoolean(key, value);
		PreferenceHelper.sEditor.commit();
	}

	/**
	 * Get boolean stored value
	 * 
	 * @param key
	 * @return
	 */

	public static boolean getBooleanData(String key) {
		return PreferenceHelper.sSharedPreferences.getBoolean(key, false);
	}

	/**
	 * Get stored string value
	 * 
	 * @param key
	 * @return
	 */
	public static String getStringData(String key) {
		return PreferenceHelper.sSharedPreferences.getString(key, null);
	}

	/**
	 * Get stored integer value
	 * 
	 * @param key
	 * @return
	 */
	public static int getIntData(String key) {
		return PreferenceHelper.sSharedPreferences.getInt(key, 0);
	}
}
