package com.mkaa.nakithesap.webwievs;
import android.app.*;
import android.os.*;
import com.mkaa.nakithesap.*;
import android.view.*;
import android.content.*;

public class WebWievMain extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webviewmain);
		
	}
	public void onClickYemekSepeti(View v)
	{
		Intent i =new Intent(this,WwYemekSepeti.class);
		startActivity(i);
	}
}
