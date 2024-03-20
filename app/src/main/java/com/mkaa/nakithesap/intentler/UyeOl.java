package com.mkaa.nakithesap.intentler;
import android.app.*;
import android.os.*;
import com.mkaa.nakithesap.*;
import android.view.*;
import android.content.*;
import android.widget.*;

public class UyeOl extends Activity
{
	private Context context = this;
	private EditText etad,etsoyad,ettakmaad,eteposta,etepostadogrula,etsifre;
	
	public void ınit()
	{
		etad = findViewById(R.id.uyeolxmliEditTextadin);
		etsoyad = findViewById(R.id.uyeolxmliEditTextsoyadi);
		ettakmaad = findViewById(R.id.uyeolxmliEditTextTakmaAd);
		eteposta = findViewById(R.id.uyeolxmliEditTexteposta);
		etepostadogrula = findViewById(R.id.uyeolxmliEditTextepostadogrula);
		etsifre = findViewById(R.id.uyeolxmliEditTextsifre);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uyeolxmli);
		ınit();
	}
	
	public void ClickUyeOl(View v)
	{
		Toast.makeText(context,"Bitince uye olursun",Toast.LENGTH_SHORT).show();
	}
}
