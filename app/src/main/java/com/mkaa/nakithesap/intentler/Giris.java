package com.mkaa.nakithesap.intentler;
import android.app.*;
import android.os.*;
import com.mkaa.nakithesap.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.content.*;
import com.mkaa.nakithesap.bulmacaoyunu.*;

public class Giris extends Activity
{
	private EditText etGiris,etSifre;
	private TextView tvAciklama;
	private float sayi;
	private String isim;
	Context c = this;
	static final int  contact_Request = 1;
	public void Init()
	{
		tvAciklama = findViewById(R.id.girisxmliTextViewAciklama);
		etGiris = findViewById(R.id.girisxmliEditTextKullaniciAdi);
		etSifre = findViewById(R.id.girisxmliEditTextSifre);
		sayi = 0;
		isim = null;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.girisxmli);
		Init();
	//	Sayi();
	}
	
	public void ClickGiris(View v)
	{
		
		//tvAciklama.setBackground(Color.RED);
		if(etGiris.getText().toString().toLowerCase().trim().equals("ali"))
		{
			tvAciklama.setText("Basarili");
			Intent i = new Intent(c,BulmacaOyunu.class);
			i.putExtra("kismi", etGiris.toString());
			i.putExtra("ksifre",etSifre.toString());
			isim =  null;
			startActivityForResult(i,contact_Request);
		}else{
		
		
		}
	}
	
	public void Sayi()
	{
		while(true){
		sayi +=0.1f;
		
			tvAciklama.setText(""+ sayi);
		}
	}
	
	public void ClickUyeOl(View v)
	{
		startActivity(new Intent(c,UyeOl.class));
		Toast.makeText(this,"ne yaptikta uye olacan",Toast.LENGTH_SHORT).show();
	}
}
