package com.mkaa.nakithesap.bulmacaoyunu;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.icu.text.*;
import org.xml.sax.*;
import com.mkaa.nakithesap.*;

public class BulmacaOyunu extends Activity
{
	private LinearLayout pnl;
	private Button btnincr;
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
		pnl.addView(cb);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.sorucevapoyunumain);
		
		Intent i = getIntent();
		String gelenKAdi = String.valueOf( i.getStringExtra("kismi"));
		String gelenSifre =String.valueOf( i.getStringExtra("ksifre"));
		
		txtSayac.setText("Hosgeldin "+ gelenKAdi+ "/n"+ gelenSifre);
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
