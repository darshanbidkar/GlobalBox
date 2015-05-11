/**
 * 
 */
package com.globalbox.constants;

import java.util.Arrays;

/**
 * @author darshanbidkar
 *
 */
public class OneDriveConstants {

	/**
	 * 
	 */
	private OneDriveConstants() {
	}

	public static final Iterable<String> SCOPES = Arrays.asList("wl.signin",
			"wl.basic", "wl.skydrive");
	public static final String CLIENT_ID = "000000004C139933";
	public static final String TYPE_FOLDER = "folder";
	public static final String TYPE_FILE = "file";
	public static final String TYPE_ALBUM = "album";

}
