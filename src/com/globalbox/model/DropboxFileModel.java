/**
 * 
 */
package com.globalbox.model;

import java.util.ArrayList;

import com.dropbox.client2.DropboxAPI.Entry;

/**
 * @author darshanbidkar
 *
 */
public class DropboxFileModel extends Object {

	Entry entry;
	private long mFileSize;
	private ArrayList<Entry> mChildren;
	private String mIcon;
	private boolean isDeleted;
	private boolean isDir;
	private String mMimeType;
	private String mPath;
	private boolean isReadOnly;
	private String mId;
	private String mUISize;
	private boolean isThumbAvailable;
	private String mRoot;

	/**
	 * 
	 */
	public DropboxFileModel() {

	}

	/**
	 * @return the entry
	 */
	public synchronized Entry getEntry() {
		return entry;
	}

	/**
	 * @param entry
	 *            the entry to set
	 */
	public synchronized void setEntry(Entry entry) {
		this.entry = entry;
	}

	/**
	 * @return the mFileSize
	 */
	public synchronized long getmFileSize() {
		return mFileSize;
	}

	/**
	 * @param mFileSize
	 *            the mFileSize to set
	 */
	public synchronized void setmFileSize(long mFileSize) {
		this.mFileSize = mFileSize;
	}

	/**
	 * @return the mChildren
	 */
	public synchronized ArrayList<Entry> getmChildren() {
		return mChildren;
	}

	/**
	 * @param mChildren
	 *            the mChildren to set
	 */
	public synchronized void setmChildren(ArrayList<Entry> mChildren) {
		this.mChildren = mChildren;
	}

	/**
	 * @return the mIcon
	 */
	public synchronized String getmIcon() {
		return mIcon;
	}

	/**
	 * @param mIcon
	 *            the mIcon to set
	 */
	public synchronized void setmIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	/**
	 * @return the isDeleted
	 */
	public synchronized boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public synchronized void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the isDir
	 */
	public synchronized boolean isDir() {
		return isDir;
	}

	/**
	 * @param isDir
	 *            the isDir to set
	 */
	public synchronized void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	/**
	 * @return the mMimeType
	 */
	public synchronized String getmMimeType() {
		return mMimeType;
	}

	/**
	 * @param mMimeType
	 *            the mMimeType to set
	 */
	public synchronized void setmMimeType(String mMimeType) {
		this.mMimeType = mMimeType;
	}

	/**
	 * @return the mPath
	 */
	public synchronized String getmPath() {
		return mPath;
	}

	/**
	 * @param mPath
	 *            the mPath to set
	 */
	public synchronized void setmPath(String mPath) {
		this.mPath = mPath;
	}

	/**
	 * @return the isReadOnly
	 */
	public synchronized boolean isReadOnly() {
		return isReadOnly;
	}

	/**
	 * @param isReadOnly
	 *            the isReadOnly to set
	 */
	public synchronized void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	/**
	 * @return the mId
	 */
	public synchronized String getmId() {
		return mId;
	}

	/**
	 * @param mId
	 *            the mId to set
	 */
	public synchronized void setmId(String mId) {
		this.mId = mId;
	}

	/**
	 * @return the mUISize
	 */
	public synchronized String getmUISize() {
		return mUISize;
	}

	/**
	 * @param mUISize
	 *            the mUISize to set
	 */
	public synchronized void setmUISize(String mUISize) {
		this.mUISize = mUISize;
	}

	/**
	 * @return the isThumbAvailable
	 */
	public synchronized boolean isThumbAvailable() {
		return isThumbAvailable;
	}

	/**
	 * @param isThumbAvailable
	 *            the isThumbAvailable to set
	 */
	public synchronized void setThumbAvailable(boolean isThumbAvailable) {
		this.isThumbAvailable = isThumbAvailable;
	}

	/**
	 * @return the mRoot
	 */
	public synchronized String getmRoot() {
		return mRoot;
	}

	/**
	 * @param mRoot
	 *            the mRoot to set
	 */
	public synchronized void setmRoot(String mRoot) {
		this.mRoot = mRoot;
	}

}
