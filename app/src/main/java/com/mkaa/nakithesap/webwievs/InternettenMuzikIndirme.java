package com.mkaa.nakithesap.webwievs;
import android.app.*;
import android.os.*;
import com.mkaa.nakithesap.*;
import android.widget.*;
import android.content.*;
import android.view.*;
import java.io.*;
import java.net.*;

public class InternettenMuzikIndirme extends Activity
{
	private ProgressDialog pDialog;
	static final int progress_bar_tipi = 0;
	private Button btn;
	static String dosya_url= "https://www.mp3indirdur.pro/dinle/sxVYkUVtih4.mp3";
	Context c = this;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internettenmuzikindirmexmli);
		btn = findViewById(R.id.internettenmuzikindirmexmliButton);
		btn.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					File dosya = new File(Environment.getExternalStorageDirectory().getPath()+"sila.mp3");
					if(dosya.exists())
					{
						Toast.makeText(c,"dosya indi muzik dinleyin",Toast.LENGTH_SHORT).show();
						//muzikDinle();
					}else{
						Toast.makeText(c,"dosya yok internetten indirin",Toast.LENGTH_SHORT).show();
						//internettenDosyaindir();
					}
						
				}
		});
	//	VIBRATOR_SERVICE.startsWith("start");
		
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		// TODO: Implement this method
		switch(id)
		{
			case progress_bar_tipi:
				pDialog = new ProgressDialog(c);
				pDialog.setMessage("Mp3 dosyasi indiriliyor. Lutfen bekleyiniz");
				pDialog.setMax(100);
				pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pDialog.setCancelable(false);
				pDialog.show();
				return pDialog;
				default:
		}
		return null;
	}
	
	public class InternettenDosyaIndir extends AsyncTask<String,String,Object> //<> parantezler icinde 3 parametre alir 1.si doinbackraundun parametresi 2.si  onprogrestupdatenin parametresi 3. onpostexecutenin parametresi
	{

		@Override // islem basladiginda calisir
		protected void onPreExecute()
		{
			// TODO: Implement this method
			super.onPreExecute();
			showDialog(progress_bar_tipi);
		}

		
		@Override // islemi yapti
		protected Object doInBackground(String[] p1)
		{
			// TODO: Implement this method
			int sayi;
				try
				{
					URL url = new URL(p1[0]);
					
					
						URLConnection connection = url.openConnection();
						connection.connect();
						int dosyaUzunlugu = connection.getContentLength();
						InputStream input = new BufferedInputStream(url.openStream(),10*1024);
					OutputStream output = new FileOutputStream(Environment.
											  getExternalStorageDirectory().getPath()+"sila.mp3");
					byte data[] = new byte[1024];
					int toplam =0;
					while((sayi = input.read(data)) != -1) // > 0 ile ayni anlama geliyor
					{
						output.write(data,0,sayi);
						toplam +=sayi;
						publishProgress(String.valueOf((toplam * 100)/dosyaUzunlugu));
						publishProgress();
					}
					output.flush();
					output.close();
					input.close();
				}
				catch (MalformedURLException e)
				{
					e.printStackTrace();
					Toast.makeText(c,"indirmede sorun var",Toast.LENGTH_SHORT);
				}
			catch (IOException e)
			{
				
			}
			return null;
		}

		@Override // islem bittiginde calisir
		protected void onPostExecute(Object result)
		{
			// TODO: Implement this method
			super.onPostExecute(result);
			dismissDialog(progress_bar_tipi);
			Toast.makeText(c,"Dosya İndirme Tamamlandi",Toast.LENGTH_LONG).show();
			//muzikDinle();
		}

		@Override // arka planda surecin islemesini takip ediyor
		protected void onProgressUpdate(String[] values)
		{
			// TODO: Implement this method
			super.onProgressUpdate(values);
			pDialog.setProgress(Integer.parseInt(values[0]));
		}
		
		
		
	}
}
