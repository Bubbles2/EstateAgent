package com.matcom.estateagent;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchViewActivity extends Activity {

	private TextView resultText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_view);

		resultText = (TextView)findViewById(R.id.searchViewResult);
		//
		// If an item has been selected from the suggestions list ie. "SEARCH_SUGGESTION_CLICKED"
		//
		String s = getIntent().getAction().toString();
		if (ContactsContract.Intents.SEARCH_SUGGESTION_CLICKED.equals(s)) {
			// Get data for selected for item 
			Cursor phoneCursor = getContentResolver().query(getIntent().getData(), null, null, null, null);
			phoneCursor.moveToFirst();
			// get Index for name and Id
			int idDisplayName = phoneCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			int idInt = phoneCursor.getColumnIndex(ContactsContract.Contacts._ID);
			String name = phoneCursor.getString(idDisplayName);
			String contactId = phoneCursor.getString(idInt);
			phoneCursor.close();
			// Launching new Activity 
                   Intent i = new Intent(getApplicationContext(), ClientDetailWithSwipe.class);
                   // sending data (id and name) to new activity
                   i.putExtra("id", contactId);
                   i.putExtra("name", name);
                   startActivity(i);
                   
		}

		setupSearchView();
		
		
	}

	private void setupSearchView() {
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		final SearchView searchView = (SearchView) findViewById(R.id.searchView);
		SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
		searchView.setSearchableInfo(searchableInfo);
	}


	protected void onNewIntent (Intent intent) {
		String s = "A";
		if (ContactsContract.Intents.SEARCH_SUGGESTION_CLICKED.equals(intent.getAction())) {
			//handles suggestion clicked query
			String displayName = getDisplayNameForContact(intent);
			resultText.setText(displayName);
		} else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			// handles a search query
			String query = intent.getStringExtra(SearchManager.QUERY);
			resultText.setText("should search for query: '" + query + "'...");
		}
	}

	private String getDisplayNameForContact(Intent intent) {
		Cursor phoneCursor = getContentResolver().query(intent.getData(), null, null, null, null);
		phoneCursor.moveToFirst();
		int idDisplayName = phoneCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
		String name = phoneCursor.getString(idDisplayName);
		phoneCursor.close();
		return name;
	}
}
