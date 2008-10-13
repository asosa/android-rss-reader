package com.example.rssreader;

import java.util.List;
import java.util.Vector;

public class RSSFeed {

	private String title = null;
	private String pubdate = null;
	private int itemcount = 0;
	private List<RSSItem> items;

	public RSSFeed() {
		items = new Vector<RSSItem>(0);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getPubdate() {
		return pubdate;
	}

	public int getItemcount() {
		return itemcount;
	}

	public List<RSSItem> getAllItems() {
		return items;
	}
	
	public int addItem (RSSItem item) {
		items.add(item);
		itemcount++;
		return itemcount;
	}
	
	public RSSItem getItem(int location) {
		return items.get(location);
	}
}
