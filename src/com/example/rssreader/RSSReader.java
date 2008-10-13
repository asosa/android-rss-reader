package com.example.rssreader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RSSReader extends Activity implements OnItemClickListener {
	private static final int ACTIVITY_DESCRIPTION = 0;
	private static final String MY_RSS = "http://www.pheedo.jp/f/gigazine_2";
	private RSSFeed feed = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		feed = getFeed(MY_RSS);
		UpdateDisplay();
	}

	public void onItemClick(AdapterView parent, View v, int position, long id) {
		Intent i = new Intent(this, ShowDescription.class);
		RSSItem item = feed.getItem(position);

		i.putExtra("title", item.getTitle());
		i.putExtra("description", item.getDescription());
		i.putExtra("link", item.getLink());
		i.putExtra("pubdate", item.getPubdate());

		startActivityForResult(i, ACTIVITY_DESCRIPTION);
	}

	private void UpdateDisplay() {
		TextView feedTitle = (TextView) findViewById(R.id.feedtitle);
		TextView feedPubdate = (TextView) findViewById(R.id.feedpubdate);
		ListView itemList = (ListView) findViewById(R.id.itemlist);

		if (feed == null) {
			feedTitle.setText("No RSS Feed Available");
			return;
		}

		feedTitle.setText(feed.getTitle());
		feedPubdate.setText(feed.getPubdate());

		ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(this,
				android.R.layout.simple_list_item_1, feed.getAllItems());
		itemList.setAdapter(adapter);
		itemList.setSelection(0);
		itemList.setOnItemClickListener(this);
	}

	private RSSFeed getFeed(String urlToRssFeed) {
		try {
			URL url = new URL(urlToRssFeed);

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			XMLReader xmlReader = parser.getXMLReader();
			RSSHandler rssHandler = new RSSHandler();
			xmlReader.setContentHandler(rssHandler);

			InputSource is = new InputSource(url.openStream());
			xmlReader.parse(is);

			return rssHandler.getFeed();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}