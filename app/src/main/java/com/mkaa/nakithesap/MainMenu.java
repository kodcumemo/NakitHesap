package com.mkaa.nakithesap;
import android.app.*;
import android.os.*;
import android.content.*;
import android.view.*;
import com.mkaa.nakithesap.mutabakat.*;
import com.mkaa.nakithesap.webwievs.*;
import com.mkaa.nakithesap.intentler.*;
import com.mkaa.nakithesap.programatik.*;

public class MainMenu extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenuxml);
	}
	public void  ClickHesapDefteri(View v)
	{
		Intent i=new Intent(this,MainActivity.class);
		startActivity(i);
	}
	public void  ClickMutabakat(View v)
	{
		Intent i=new Intent(this,MutabakatActivity.class);
		startActivity(i);
	}
	public void  ClickVv(View v)
	{
		Intent i=new Intent(this,WebWievMain.class);
		startActivity(i);
	}
	public void  ClickGiris(View v)
	{
		Intent i=new Intent(this,Giris.class);
		startActivity(i);
	}
	public void  ClickProgramatik(View v)
	{
		Intent i=new Intent(this,ProgramatikActivity.class);
		startActivity(i);
	}
}
