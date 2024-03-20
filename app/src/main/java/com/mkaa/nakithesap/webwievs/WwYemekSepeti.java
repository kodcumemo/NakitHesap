package com.mkaa.nakithesap.webwievs;
import android.app.*;
import android.os.*;
import com.mkaa.nakithesap.*;
import android.content.*;
import android.webkit.*;

public class WwYemekSepeti extends Activity
{
	private Context c= this;
	//private WebView wv;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.wwyemeksepeti);
	//	 wv = findViewById(R.id.wwyemeksepetiWebView);
		WebView wv = new WebView(c);
		WebSettings ws = wv.getSettings();
		ws.setBuiltInZoomControls(true);
		//ws.setLightTouchEnabled(true);
		ws.setJavaScriptEnabled(true);
		wv.loadUrl("https://www.yemeksepeti.com/burger-king-yenimahalle-mehmet-akif-mah-ankara#active:restaurant_comments");
		setContentView(wv);

	}


}
