/**
 * 
 */
package com.globalbox.interfaces;

import java.io.File;
import java.util.ArrayList;

import com.globalbox.model.onedrive.FileStructure;

/**
 * @author darshanbidkar
 *
 */
public interface OneDriveCallback {
	public void listFiles(ArrayList<FileStructure> files);

	public void downloadCompleted(File f);
	public void initiateDownload(String id,String name,OneDriveCallback oneDriveCallback);
}

