package com.lonebench.simplesafe;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.ads.*;

public class SimpleSafe extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        // defining variables           
        final Button md5 = (Button)findViewById(R.id.md5);
        final Button sha1 = (Button)findViewById(R.id.sha1);
        TextView link = (TextView) findViewById(R.id.sitelink);
        link.setText("http://simplesafeapp.com");
        Linkify.addLinks(link, Linkify.ALL);
        
        md5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	//MD5 goes here
                EditText phrase = (EditText)findViewById(R.id.phrase);
            	String tobeTranslated = phrase.getText().toString();
            	tobeTranslated = tobeTranslated + "simpleSafeSalt";
            	try{
            		MessageDigest m = MessageDigest.getInstance("MD5");
            		byte[] data = tobeTranslated.getBytes(); 
            		m.update(data,0,data.length);
            		BigInteger i = new BigInteger(1,m.digest());
            		String stringFormat = (String.format("%1$032X", i));
            		String finalString = "6:" + stringFormat.substring(0, 6);
            		for(int number = 2; number < 8; number++){
            			int substrNumber = number + 5;
            			finalString += "\n" + (substrNumber) + ": " + stringFormat.substring(0,substrNumber);   			
            		}
            		TextView answer = (TextView)findViewById(R.id.answer);
            		answer.setText(finalString.toLowerCase());
        		}
            	catch(NoSuchAlgorithmException lException) {
            		throw new RuntimeException(lException);
            	}
            }
        });
        sha1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        	final EditText phrase = (EditText)findViewById(R.id.phrase);
        	TextView answer = (TextView)findViewById(R.id.answer);
    		String tobeTranslated = phrase.getText().toString();
    		tobeTranslated = tobeTranslated + "simpleSafeSalt";
    		answer.setText("");
    		try{
            	MessageDigest m = MessageDigest.getInstance("SHA-1");
        		byte[] data = tobeTranslated.getBytes(); 
        		m.update(data,0,data.length);
        		BigInteger i = new BigInteger(1,m.digest());
        		String stringFormat = (String.format("%1$032X", i));
        		String finalString = "6:" + stringFormat.substring(0, 6);
        		for(int number = 2; number < 8; number++){
        			int substrNumber = number + 5;
        			finalString += "\n" + (substrNumber) + ": " + stringFormat.substring(0,substrNumber);   			
        	}
        		answer.setText(finalString.toLowerCase());
        	}
            	catch(NoSuchAlgorithmException lException) {
            		throw new RuntimeException(lException);
           }
  
    	
            }
        });
    }
    
}