package br.com.bloum.android.contentprovider.app3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public class JavascriptInterface {
	Context mContext;
	
	public JavascriptInterface( Context c ) {
		mContext = c;
	}
	
	public void update( String id, String password ){
		Uri passwordUri = Uri.parse("content://br.com.bloum.contentprovider.app2.PasswordsContentProvider/passwords/");
		
		ContentValues values = new ContentValues();
		values.put( "password", md5( password ) );
		
		mContext.getContentResolver().update(passwordUri,values, "_id = "+id, null);
	}
	
	public static final String md5(final String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();
	 
	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString().substring(0, 16);
	 
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
}
