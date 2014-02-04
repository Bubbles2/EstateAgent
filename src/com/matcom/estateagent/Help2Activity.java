package com.matcom.estateagent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class Help2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help2);
		// 
		
		TextView ht = (TextView) findViewById(R.id.help2Txt);
		ht.setMovementMethod(new ScrollingMovementMethod());
		try {
			ht.setText(loadHelp());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help2, menu);
		return true;
	}
	private String loadHelp() throws Exception {
		// Lowercase eng, fra
				String lang = Locale.getDefault().getISO3Language();
	//			Locale myloc = Locale.getDefault();
				String helpfile = "help2_"+lang+".txt";
		    	InputStream myInput = this.getAssets().open(helpfile);
		    	//transfer bytes from the inputfile to the outputfile
			    	BufferedReader br = null;
				String line ="";
				String ht ="";
				try {
		 
					br = new BufferedReader(new InputStreamReader(myInput));
					while ((line = br.readLine()) != null) {
						ht += line+"\n";
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				myInput.close();
			  			  
			  return ht;
			
		}


}
