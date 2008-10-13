package com.example.rssreader;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import android.util.Log;

public class RSSHandler extends DefaultHandler {

	private RSSFeed feed;
	private RSSItem item;

	private boolean rssChannel = false;
	private boolean rssItem = false;
	private boolean rssTitle = false;
	private boolean rssLink = false;
	private boolean rssPubdate = false;
	private boolean rssDescription = false;
	private boolean rssCategory = false;

	private int depth = 0;
	private StringBuffer sb;

	public RSSHandler() {
	}

	public RSSFeed getFeed() {
		return feed;
	}

	@Override
	public void startDocument() throws SAXException {
		feed = new RSSFeed();
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		depth++;
		sb = new StringBuffer();

		if (localName.equals("channel")) {
			// このitemはaddされないため必要ないが、分岐を減らすためここで作成する
			item = new RSSItem();
			rssChannel = true;
		}
		if (localName.equals("item")) {
			item = new RSSItem();
			rssItem = true;
		}
		if (localName.equals("title")) {
			rssTitle = true;
		}
		if (localName.equals("link")) {
			rssLink = true;
		}
		if (localName.equals("pubDate")) {
			rssPubdate = true;
		}
		if (localName.equals("description")) {
			rssDescription = true;
		}
		if (localName.equals("category")) {
			rssCategory = true;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		depth--;

		if (localName.equals("channel")) {
			rssChannel = false;
		}
		if (localName.equals("item")) {
			feed.addItem(item);
			rssItem = false;
		}
		if (localName.equals("title")) {
			if (rssItem) {
				item.setTitle(sb.toString().trim());
			} else {
				feed.setTitle(sb.toString().trim());
			}
			rssTitle = false;
		}
		if (localName.equals("pubDate")) {
			if (rssItem) {
				item.setPubdate(sb.toString().trim());
			} else {
				feed.setPubdate(sb.toString().trim());
			}
			rssPubdate = false;
		}
		if (localName.equals("link")) {
			item.setLink(sb.toString().trim());
			rssLink = false;
		}
		if (localName.equals("description")) {
			item.setDescription(sb.toString().trim());
			rssDescription = false;
		}
		if (localName.equals("category")) {
			item.setCategory(sb.toString().trim());
			rssCategory = false;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {
		String theString = new String(ch, start, length);
		Log.i("RSSReader", "characters[" + theString + "]");

		if (rssTitle || rssLink || rssCategory || rssDescription || rssPubdate) {
			sb.append(theString);
		}
	}
}
