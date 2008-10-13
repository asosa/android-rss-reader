package com.example.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowDescription extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdescription);

		String theStory = null;

		Intent intent = getIntent();

		if (intent != null) {
			Bundle b = intent.getExtras();
			if (b == null) {
				theStory = "bad bundle?";
			} else {
				theStory = b.getString("title") + "\n\n"
						+ b.getString("pubdate") + "\n\n"
						+ b.getString("description").replace('\n', ' ')
						+ "\n\nMore information:\n" + b.getString("link");
			}
		} else {
			theStory = "Information not found.";
		}
		
		TextView story = (TextView)findViewById(R.id.storybox);
		story.setText(theStory);
		
		Button backButton = (Button)findViewById(R.id.back);
		backButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				finish();
			}
		});
	}
}
