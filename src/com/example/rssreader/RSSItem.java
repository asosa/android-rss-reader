package com.example.rssreader;

public class RSSItem {
	private String title = null;
	private String description = null;
	private String link = null;
	private String category = null;
	private String pubdate =null;
	
	public RSSItem() {
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String toString() {
		if ( title.length() > 42 ) {
			return title.substring(0, 42) + "...";
		}
		return title;
	}
}
