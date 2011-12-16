package br.com.bloum.android.contentprovider.app3;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class App3 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        WebView webView = (WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavascriptInterface(this), "Android");
        
        Cursor passwords = managedQuery( Uri.parse("content://br.com.bloum.contentprovider.app2.PasswordsContentProvider/passwords"), null, null, null, null );
        
		if ( passwords.moveToFirst() ){
			
			String data = "<html><head><link href='css.css' rel='stylesheet' type='text/css' />"+
				"<script type='text/javascript' src='jquery.js'></script>"+
				"<script type='text/javascript' src='js.js'></script></head><body>";
			data = data.concat("<ul>");
			
			do {
				data = data.concat("<div class='application'>");
				
				String id = passwords.getString(passwords.getColumnIndex("_id"));
				
				String application = passwords.getString(passwords.getColumnIndex("application"));
				data = data.concat( application );
				
				String user = passwords.getString(passwords.getColumnIndex("user"));
				data = data.concat( "<div class='user'>"+user+"</div>" );
				String password = passwords.getString(passwords.getColumnIndex("password"));
				data = data.concat( "<div class='password'>"+password+"</div>" );

				data = data.concat( "</div>" );
				
				data = data.concat( "<div class='crypt' app_id='"+id+"' password='"+password+"'>CRYPT</div>" );
				
			} while ( passwords.moveToNext() );
			
			data = data.concat("</ul></body></html>");
			
			webView.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "utf-8", null);
			
		} else {
			webView.loadData( "Nenhum password encontrado", "text/html", "utf-8");
		}
    }
}