package com.globalbox.model.onedrive;

import java.util.HashMap;
import java.util.Map;

public class FileStructure {

	private String uploadLocation;
	private Integer commentsCount;
	private Integer count;
	private SharedWith sharedWith;
	private String link;
	private Boolean isEmbeddable;
	private From from;
	private String type;
	private String updatedTime;
	private String clientUpdatedTime;
	private Integer size;
	private String id;
	private String description;
	private String name;
	private String createdTime;
	private Boolean commentsEnabled;
	private String parentId;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The uploadLocation
	 */
	public String getUploadLocation() {
		return uploadLocation;
	}

	/**
	 * 
	 * @param uploadLocation
	 *            The upload_location
	 */
	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}

	/**
	 * 
	 * @return The commentsCount
	 */
	public Integer getCommentsCount() {
		return commentsCount;
	}

	/**
	 * 
	 * @param commentsCount
	 *            The comments_count
	 */
	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	/**
	 * 
	 * @return The count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 
	 * @param count
	 *            The count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 
	 * @return The sharedWith
	 */
	public SharedWith getSharedWith() {
		return sharedWith;
	}

	/**
	 * 
	 * @param sharedWith
	 *            The shared_with
	 */
	public void setSharedWith(SharedWith sharedWith) {
		this.sharedWith = sharedWith;
	}

	/**
	 * 
	 * @return The link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 
	 * @param link
	 *            The link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 
	 * @return The isEmbeddable
	 */
	public Boolean getIsEmbeddable() {
		return isEmbeddable;
	}

	/**
	 * 
	 * @param isEmbeddable
	 *            The is_embeddable
	 */
	public void setIsEmbeddable(Boolean isEmbeddable) {
		this.isEmbeddable = isEmbeddable;
	}

	/**
	 * 
	 * @return The from
	 */
	public From getFrom() {
		return from;
	}

	/**
	 * 
	 * @param from
	 *            The from
	 */
	public void setFrom(From from) {
		this.from = from;
	}

	/**
	 * 
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The updatedTime
	 */
	public String getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * 
	 * @param updatedTime
	 *            The updated_time
	 */
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * 
	 * @return The clientUpdatedTime
	 */
	public String getClientUpdatedTime() {
		return clientUpdatedTime;
	}

	/**
	 * 
	 * @param clientUpdatedTime
	 *            The client_updated_time
	 */
	public void setClientUpdatedTime(String clientUpdatedTime) {
		this.clientUpdatedTime = clientUpdatedTime;
	}

	/**
	 * 
	 * @return The size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * 
	 * @param size
	 *            The size
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * 
	 * @return The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * 
	 * @param createdTime
	 *            The created_time
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 
	 * @return The commentsEnabled
	 */
	public Boolean getCommentsEnabled() {
		return commentsEnabled;
	}

	/**
	 * 
	 * @param commentsEnabled
	 *            The comments_enabled
	 */
	public void setCommentsEnabled(Boolean commentsEnabled) {
		this.commentsEnabled = commentsEnabled;
	}

	/**
	 * 
	 * @return The parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @param parentId
	 *            The parent_id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}