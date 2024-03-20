package com.mkaa.nakithesap.mutabakat;
import android.app.*;
import android.os.*;
import android.widget.*;
import com.mkaa.nakithesap.*;
import android.graphics.*;

public class MutabakatActivity extends Activity
{
	private TextView tvBaslik;
	private LinearLayout ly;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		ly = new LinearLayout(this);
		ly.setOrientation(LinearLayout.VERTICAL);
		
		tvBaslik = new TextView(this);
		tvBaslik.setText("MutabakatActivity");
		tvBaslik.setGravity(50);
		tvBaslik.setTextSize(25);
		
		ly.addView(tvBaslik);
		setContentView(ly);
	}
	
}
