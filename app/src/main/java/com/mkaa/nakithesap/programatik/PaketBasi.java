package com.mkaa.nakithesap.programatik;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.transition.*;
import com.mkaa.nakithesap.*;

public class PaketBasi extends Activity
{
	private LinearLayout llAna,llpMiktar,llsiparisler,llListe;
	private Button bt1,bt2,bt3,bt4,bt5,btOnay;
	private EditText etisim,etfiyat;
	private TextView tvyazi;
	private Context c=this;
	public void init()
	{
		llAna = new LinearLayout(c);
		
		llpMiktar = new LinearLayout(c);
		
		llsiparisler = new LinearLayout(c);
		
		llListe = new LinearLayout(c);
		
		bt1 = new Button(c);
		bt2 = new Button(c);
		bt3 = new Button(c);
		bt4 = new Button(c);
		bt5 = new Button(c);
		btOnay = new Button(c);
		etisim = new EditText(c);
		etfiyat = new EditText(c);
		tvyazi = new TextView(c);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		init();
		
		AnaGorunum();
		
		setContentView(R.layout.paketbasixmli);
	}
	
	public void AnaGorunum()
	{
		llAna.setOrientation(LinearLayout.VERTICAL);
		llpMiktar.setOrientation(LinearLayout.HORIZONTAL);
		llsiparisler.setOrientation(LinearLayout.VERTICAL);
		llListe.setOrientation(LinearLayout.VERTICAL);
		
		
		bt1.setText("1 Paket");
		bt1.setWidth(100);
		bt1.setHeight(100);
		
		llpMiktar.addView(bt1);
		llAna.addView(llpMiktar);
	}
	
	
}
