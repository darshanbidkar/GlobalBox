/**
 * 
 */
package com.globalbox.interfaces;

/**
 * @author darshanbidkar
 *
 */
public interface OnPagination {
	public void onMoreDropboxFiles(String path);

	public void onMoreOnedriveFiles(String path);

	public void onMoreGoogledriveFiles(String path);
}
