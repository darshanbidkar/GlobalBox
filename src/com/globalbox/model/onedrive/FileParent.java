/**
 * 
 */
package com.globalbox.model.onedrive;

import java.util.ArrayList;

/**
 * @author darshanbidkar
 *
 */
public class FileParent {
	private ArrayList<FileStructure> data;

	/**
	 * @return the data
	 */
	public synchronized ArrayList<FileStructure> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public synchronized void setData(ArrayList<FileStructure> data) {
		this.data = data;
	}

	/**
	 * 
	 */
	public FileParent() {
		// TODO Auto-generated constructor stub
	}

}
