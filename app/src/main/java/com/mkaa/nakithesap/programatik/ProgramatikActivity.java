package com.mkaa.nakithesap.programatik;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.graphics.drawable.*;
import android.view.*;
import android.graphics.*;

public class ProgramatikActivity extends Activity
{
	private LinearLayout pnl,llust,llalt;
	private Button btnincr,btnP1,btnP2;
	private TextView txtSayac;
	private CheckBox cb;
	int sayac;
	Context c = this;
	
	public void init()
	{
		pnl = new LinearLayout(c);
		pnl.setOrientation(LinearLayout.VERTICAL);
		
		txtSayac = new TextView(c);
		txtSayac.setText("Sayac = "+ sayac);
		txtSayac.setTextSize(25);
		
		btnincr = new Button(c);
		btnincr.setText("Arttir");btnincr.setAllCaps(false);

		cb = new CheckBox(c);
		cb.setChecked(true);
		
		
		pnl.addView(txtSayac);
		pnl.addView(btnincr);
		pnl.addView(Ustll());
		pnl.addView(cb);
	}
	
	public LinearLayout Ustll()
	{
		llust = new LinearLayout(c);
		llust.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout ustLinear = new LinearLayout(c);
		ustLinear.setOrientation(LinearLayout.HORIZONTAL);
		
		TextView t1= new TextView(c);
		TextView t2 = new TextView(c);
		btnP1 = new Button(c);
		btnP2 = new Button(c);
		t1.setText("Ahmet");
		t2.setText("Etem");
		btnP1.setBackgroundColor(Color.BLUE);
		btnP1.setText("birinci");
		btnP2.setBackgroundColor(Color.RED);
		btnP2.setText("İkinci");
		
		ustLinear.addView(t1);
		ustLinear.addView(btnP1);
		ustLinear.addView(t2);
		ustLinear.addView(btnP2);
		
		llust.addView(ustLinear);
		return llust;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		init();
		setContentView(pnl);
		
		btnincr.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					sayac ++;
					txtSayac.setText("Sayac = "+ sayac);
				
				}
		});
	}
	
}
