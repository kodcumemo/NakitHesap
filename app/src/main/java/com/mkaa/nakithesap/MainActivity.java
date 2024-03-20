package com.mkaa.nakithesap;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.text.*;
import java.text.*;
import com.mkaa.nakithesap.globaldatas.*;
import com.mkaa.nakithesap.programatik.*;
import com.mkaa.nakithesap.mutabakat.*;

public class MainActivity extends Activity 
{
	private EditText etTarih,etAd,etTutar;
	private TextView tvGoster,tvOdemeSekli,tvNakit,tvKk,tvYk,tvOndenmis;
	private ListView listview,lvOdenmis,lvkk,lvyk;
	private List<String> list;
	private Context c = this;
	private String yazi,yazik,yazin;
	
	private ArrayList listkk,listNakit,listOdenmis,listYk;
	private List<Not> liste;
    private List<String> listBaslik;
    private String tarih,tutar,aciklama,gun,ay,yil;
    private float toplam;
    private NotDatabaseHelperNakit db;
    private ArrayAdapter<String> adapter;
	private void Init()
	{
		listkk = new ArrayList<String>();
		listNakit = new ArrayList<String>();
		listOdenmis = new ArrayList<String>();
		listYk = new ArrayList<String>();
		tvNakit = findViewById(R.id.mainTextView1nakit);
		tvKk = findViewById(R.id.mainTextView1Kredili);
		tvYk = findViewById(R.id.mainTextView1Yk);
		tvOndenmis = findViewById(R.id.mainTextView1Odenmis);
		etTarih = findViewById(R.id.mainEditText1tarih);
		etAd = findViewById(R.id.mainEditText1isim);
		etTutar = findViewById(R.id.mainEditText1tutar);
		tvGoster = findViewById(R.id.mainTextView1Goster);
		tvOdemeSekli = findViewById(R.id.mainTextViewOdemeSekli);
		listview =findViewById(R.id.mainListView1);
		lvOdenmis = findViewById(R.id.mainListViewoa);
		lvkk = findViewById(R.id.mainListViewkk);
		lvyk = findViewById(R.id.mainListViewyk);
		
		list = new ArrayList<String>();
		
		yazi = "Nakit";
		tvOdemeSekli.setText(""+yazi);
	//	db.NotEkle(new Not("010","memokaa","9"));
	}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Init();
	//	Tarihİslemleri();
		Dbases();
		Ekle();
		ListHandler();
	//	Arama();
		
		DataBind();
		
	//	Database();
	//	GenelToplam();
	//	Ekle();
	//	ArrayAdapter adapter = new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,android.R.id.text1,list);
	//	listview.setAdapter(adapter);
    }
	
	private void Ekle()
	{
		list.add("29.04.2021 mesut alym 24.99");
		list.add("test eklemesi para yok");
		list.add("bu bir testtir 8");
	}
	private void DataBind()
	{
		
		ListHandler();
		Silici();
	}
	private void Silici()
	{
		DuzeltSil(lvkk);
		DuzeltSil(listview);
		DuzeltSil(lvOdenmis);
		DuzeltSil(lvyk);
	}
	private void ListHandler()
	{
		
		AdapterHandler("Kredi Karti",listkk,lvkk,tvKk);
		AdapterHandler("Nakit",listNakit,listview,tvNakit);
		AdapterHandler("Online Odenmis",listOdenmis,lvOdenmis,tvOndenmis);
		AdapterHandler("Yemek Karti",listYk,lvyk,tvYk);
	//	GenelToplam();
	}
	// Listelere yazdirma islemi 
	private void AdapterHandler(String odemeTuru,ArrayList tempListkka,ListView lvkka,TextView tv)
	{
		float para = 0;
		tempListkka.clear();
		liste = db.notlariGetir();
		for (int i = 0 ; i < liste.size(); i++)
		{
			if(liste.get(i).getTuru().toUpperCase().contains(/*p1*/odemeTuru.toString().toUpperCase()))
			{
				tempListkka.add(i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺"+ liste.get(i).getTuru());
				float tutarPara = Float.parseFloat( liste.get(i).getTutar());
				para+=tutarPara;
				tv.setText(odemeTuru.toString() + " " + para);
				//liste.get(i).getTutar();
			}
		}
		if(tempListkka!=null && tempListkka.size() > 0 )
		{
			adapter = new ArrayAdapter<String>(c,R.layout.tektextview,R.id.tektextviewTextView,tempListkka);
			lvkka.setAdapter(adapter);
		}
		for(int i=0;i<tempListkka.size();i++)
		{
			
		}
	}
	
	public void onClickNakit(View v) { yazi = "Nakit"; tvOdemeSekli.setText(""+yazi); }
	
	public void onClickYemekKarti(View v) { yazi ="Yemek Karti"; tvOdemeSekli.setText(""+yazi); }
	
	public void onClickKk(View v) { yazi = "Kredi Karti"; tvOdemeSekli.setText(""+yazi); }
	
	public void onClickOdenmis(View v) { yazi = "Online Odenmis"; tvOdemeSekli.setText(""+yazi); }
	
	public void onClickProgramatik(View v){ Intent i = new Intent(c,MainMenu.class); startActivity(i); Toast.makeText(c,"Basma Lan \n Ne Basiyorsun" ,Toast.LENGTH_SHORT).show(); }
	private void Dbases()
	{
		db = new NotDatabaseHelperNakit(c);
		try
        {
	//	   db.onUpgrade(db.getWritableDatabase(), 1, 2); //databaseyi çağırmak için yazıyoruz ve versiyonları yazıyoruz.
            db.getWritableDatabase();
            Toast.makeText(c, "Veritabanı yazma modunda açıldı.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(c, "Bozuk Anasini Satiyim", Toast.LENGTH_LONG).show();
        }
	
	}
	
	private void Tarihİslemleri()
    {
	//	etTarih.setText(new GlobalDatas.TarihAlma.toString());
		DateFormat dateFormatDate = android.text.format.DateFormat.getDateFormat(c);
	//	String dateStr = dateFormatDate.format(Date); android.text.format.DateFormat.getTimeFormat(c);
	//	etTarih.setText(""+dateStr.toString());
    }

    public void onClickKaydet(View v)
    {
        Toast.makeText(c,"Kaydedildi",Toast.LENGTH_SHORT).show();
        // ₺ simgesi
        tvGoster.setText(etTarih.getText().toString() +" " + etAd.getText() + " " + toplam +" adet");

        tarih = etTarih.getText().toString();
        aciklama = etAd.getText().toString();
        String verilenTutar = etTutar.getText().toString();
		yazi = tvOdemeSekli.getText().toString();
        if(etTutar.getText().toString().trim().equals(""))
        {
            verilenTutar= "0";
        }

        db.NotEkle(new Not(tarih,aciklama,verilenTutar,yazi));
        etAd.setText("");
        etTutar.setText("");

        liste = db.notlariGetir();

        ListHandler();

    }

    public void GenelToplam()
    {
        toplam = 0;
        for(int i=0;i<liste.size();i++)
        {

            tutar = liste.get(i).getTutar().toString();
            toplam += Float.parseFloat(tutar);
        }
		tvGoster.setText("Toplam = " +toplam);
  //      float kdvsiz = toplam * 3.5f;
 //       float kdvli = kdvsiz * 1.18f;
  //      float kdvsi = kdvsiz = kdvsiz * 1.18f - kdvsiz;
  //      tvGoster.setText("Toplam= "+toplam+" adet *3,5 "+ kdvsiz + " tl+Kdv " + kdvli + "₺ kdvsi " + kdvsiz);
    }
	
	public void Arama()
	{
		etAd.addTextChangedListener(new TextWatcher()
			{

				@Override
				public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
					// TODO: Implement this method
				}

				@Override
				public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
					// TODO: Implement this method
					ArrayList<String> tempList = new ArrayList<>();
					liste = db.notlariGetir();
					List<String> listBaslik = new ArrayList();
					for (int i = 0 ; i < liste.size(); i++)
					{
						if(liste.get(i).getAciklama().toUpperCase().contains(p1.toString().toUpperCase()))
						{
							tempList.add(i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺");
							//  tempList.add(i, i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺");
							Toast.makeText(c, "" + tempList.size() + "\n" +listBaslik.size() + "\n" + liste.size() , Toast.LENGTH_SHORT).show();
						}
					}
					if(tempList!=null && tempList.size() > 0 )
					{
						adapter = new ArrayAdapter<String>(c,R.layout.tektextview,R.id.tektextviewTextView,tempList);
						listview.setAdapter(adapter);
					}
					
					
				}

				@Override
				public void afterTextChanged(Editable p1)
				{
					// TODO: Implement this method
				}
				
			
		});
	}
	
	private void DuzeltSil(ListView lv)
    {
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
			{

				@Override
				public boolean onItemLongClick(AdapterView<?> p1, View p2, final int pos, long p4)
				{
					// TODO: Implement this method
					AlertDialog.Builder  ad = new AlertDialog.Builder(c).
                        setTitle(tarih).
                        setMessage(liste.get(pos).getAciklama() + " " + liste.get(pos).getTutar() + "₺" + liste.get(pos).getTuru()).
                        setPositiveButton("iptal", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface p1, int p2)
                            {
                                // TODO: Implement this method
                         //       Toast.makeText(c, "Silinmedi", Toast.LENGTH_SHORT).show();
                            }
                        }).
                        setNegativeButton("Sil", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface p1, int p2)
                            {
                                // TODO: Implement this method
                                db.NotSil(liste.get(pos));

                                ListHandler();
                            }


                        });
					ad.show();
					return false;
				}
			});
    }
}

/*
private Context c = this;
    private EditText etTarih,etAciklama,etTutar;
    private TextView tvGosterge,tvToplam;
    private ListView listview;
    private List<Not> liste;
    private List<String> listBaslik;
    private String tarih,tutar,aciklama,gun,ay,yil;
    private float toplam;
    private NotDatabaseHelper db;
    private ArrayAdapter<String> adapter;
    private Button ChatButtonu;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notlukxmli);
        Init();
        Database();
        Tarihİslemleri();
        GenelToplam();
        AramaAciklama();
      //  AramaTarih();
        DuzeltSil();
    }
    private void AramaAciklama()
    {

        etAciklama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> tempList = new ArrayList<>();
                liste = db.notlariGetir();
                List<String> listBaslik = new ArrayList();
                for (int i = 0 ; i < liste.size(); i++)
                {
                    if(liste.get(i).getAciklama().toUpperCase().contains(s.toString().toUpperCase()))
                    {
                      tempList.add(i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺");
                        //  tempList.add(i, i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺");
                        Toast.makeText(c, "" + tempList.size() + "\n" +listBaslik.size() + "\n" + liste.size() , Toast.LENGTH_SHORT).show();
                    }
                }
                if(tempList!=null && tempList.size() > 0 )
                {
                    adapter = new ArrayAdapter<String>(c,R.layout.tektextviewnoticinxml,R.id.tektextviewnoticinxmlEditText,tempList);
                    listview.setAdapter(adapter);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void AramaTarih()
    {
        etTarih.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> tarihList = new ArrayList<>();
                liste = db.notlariGetir();
                List<String> listBaslik = new ArrayList();
                for (int i = 0 ; i < liste.size(); i++)
                {
                    if(liste.get(i).getTarih().toUpperCase().contains(s.toString().toUpperCase()))
                    {
                        tarihList.add(i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺");
                        //  tempList.add(i, i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺");
                        Toast.makeText(c, "" + tarihList.size() + "\n" +listBaslik.size() + "\n" + liste.size() , Toast.LENGTH_SHORT).show();
                    }
                }
                if(tarihList!=null && tarihList.size() > 0 )
                {
                    adapter = new ArrayAdapter<String>(c,R.layout.tektextviewnoticinxml,R.id.tektextviewnoticinxmlEditText,tarihList);
                    listview.setAdapter(adapter);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void DuzeltSil()
    {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> p1, View p2, final int pos, long p4)
            {
                // TODO: Implement this method
                AlertDialog.Builder  ad = new AlertDialog.Builder(c).
                        setTitle(tarih).
                        setMessage(liste.get(pos).getAciklama() + " " + liste.get(pos).getTutar() + "₺").
                        setPositiveButton("Düzelt", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface p1, int p2)
                            {
                                // TODO: Implement this method
                                Toast.makeText(c, "Değiştirildi", Toast.LENGTH_SHORT).show();
                            }
                        }).
                        setNegativeButton("Sil", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface p1, int p2)
                            {
                                // TODO: Implement this method
                                db.NotSil(liste.get(pos));

                                liste = db.notlariGetir();
                                List<String> listBaslik = new ArrayList();
                                for (int i = 0; i < liste.size(); i++)
                                {
                                    listBaslik.add(i, i + " " + liste.get(i).getTarih() + " " + liste.get(i).getAciklama() + " " + liste.get(i).getTutar() + "₺");
                                }
                                adapter = new ArrayAdapter<String>(c, R.layout.tektextviewnoticinxml, R.id.tektextviewnoticinxmlEditText, listBaslik);
                                listview.setAdapter(adapter);
                                GenelToplam();
                            }


                        });
                ad.show();
                return false;
            }
        });
    }

    private void Init()
    {
        tvGosterge = findViewById(R.id.xmlnotdefterimainTextView1Gosterge);
        tvToplam = findViewById(R.id.xmlnotdefterimainTextView1Toplam);
        etTarih = findViewById(R.id.xmlnotdefterimainEditTexttarih);
        etAciklama = findViewById(R.id.xmlnotdefterimainEditTextaciklama);
        etTutar = findViewById(R.id.xmlnotdefterimainEditTexttutar);
        listview = findViewById(R.id.xmlnotdefterimainListView1listview);
        ChatButtonu = findViewById(R.id.idChatButton);
        List<String> listBaslik = new ArrayList();
        db = new NotDatabaseHelper(c);

    }

    private void Database()
    {
        try
        {
            db.getWritableDatabase();
            Toast.makeText(c, "Vritabanı yazma modunda açıldı.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(c, "Bozuk Anasini Satiyim", Toast.LENGTH_LONG).show();
        }

        liste = db.notlariGetir();
        List<String> listBaslik = new ArrayList();
        for(int i = 0; i < liste.size(); i++)
        {
            listBaslik.add(i,i+" "+ liste.get(i).getTarih()+" "+liste.get(i).getAciklama()+" "+liste.get(i).getTutar()+"₺");
        }
        adapter = new ArrayAdapter<String>(c,R.layout.tektextviewnoticinxml,R.id.tektextviewnoticinxmlEditText,listBaslik);
        listview.setAdapter(adapter);
    }

    private void Tarihİslemleri()
    {
    //    SimpleDateFormat dfm = new SimpleDateFormat("dd:MM:yyyy");

        //SimpleDateFormat clockFormat = new SimpleDateFormat("hh:mm:ss");
  //      GregorianCalendar gcalender = new GregorianCalendar();

    //    tarih = dfm.format(gcalender.getTime());
        etTarih.setText(new GlobalDatas().TarihAlma());
    }
    
    public void btnClickKaydet(View v)
    {
        Toast.makeText(c,"Kaydedildi",Toast.LENGTH_SHORT).show();
        // ₺ simgesi
        tvGosterge.setText(etTarih.getText().toString() +" " + etAciklama.getText() + " " + toplam +" adet");

        tarih = etTarih.getText().toString();
        aciklama = etAciklama.getText().toString();
        String verilenTutar = etTutar.getText().toString();
        if(etTutar.getText().toString().trim().equals(""))
        {
            verilenTutar= "0";
        }

        db.NotEkle(new Not(tarih,aciklama,verilenTutar));
        etAciklama.setText("");
        etTutar.setText("");

        liste = db.notlariGetir();

        List<String> listBaslik = new ArrayList();
        for(int i = 0; i < liste.size(); i++)
        {
            listBaslik.add(i,i+" "+ liste.get(i).getTarih()+" "+liste.get(i).getAciklama()+" "+liste.get(i).getTutar()+"₺ " + liste.get(i).getTutar());
        }
        adapter = new ArrayAdapter<String>(c,R.layout.tektextviewnoticinxml,R.id.tektextviewnoticinxmlEditText,listBaslik);
        listview.setAdapter(adapter);
        GenelToplam();

    }

    public void btnClickSifirla(View v)
    {
        etTarih.setText(new GlobalDatas().TarihAlma());
    }
    // ana menuye dönme tuşu
    public void btnClickMainMenu(View v) { Intent i = new Intent(c, AnaMain.class); startActivity(i); }
    
    public void btnClickDiger(View v)
    {
        Intent i = new Intent(c, YeniKayitSayfasi.class);
        startActivity(i);
    }
    public void GenelToplam()
    {
        toplam = 0;
        for(int i=0;i<liste.size();i++)
        {

            tutar = liste.get(i).getTutar().toString();
            toplam += Float.parseFloat(tutar);
        }
        float kdvsiz = toplam * 3.5f;
        float kdvli = kdvsiz * 1.18f;
        float kdvsi = kdvsiz = kdvsiz * 1.18f - kdvsiz;
        tvGosterge.setText("Toplam= "+toplam+" adet *3,5 "+ kdvsiz + " tl+Kdv " + kdvli + "₺ kdvsi " + kdvsiz);
    }

    public void ListeyeUcretleriYazdirma()
    {
        toplam = 0;
        for(int i=0;i<liste.size();i++)
        {
            tutar = liste.get(i).getTutar().toString();
            toplam += Float.parseFloat(tutar);
        }
        float kdvsiz = toplam * 3.5f;
        float kdvli = kdvsiz * 1.18f;
        float kdvsi = kdvsiz = kdvsiz * 1.18f - kdvsiz;
    }

    public void btnClickChat(View v) {
        Intent i = new Intent(c, UOChatAppMain.class);
        startActivity(i);
    }
}
*/
