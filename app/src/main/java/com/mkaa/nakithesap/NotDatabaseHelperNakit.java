package com.mkaa.nakithesap;
import android.content.*;
import android.database.sqlite.*;
import java.util.*;
import android.database.*;

// Bu classi kopyala yapıştır ile ilerideki projelerimde de kullanabilirim.
public class NotDatabaseHelperNakit extends SQLiteOpenHelper
{    // Bu kalıtım 4 adet parametre alır. 1. si context bu sayfa yani,2.si databasenin ismi

    private static final int database_VERSION = 2;          // Version adi
    private static final String database_NAME = "KayitDBNakit";  // Databasenin adi
    private static final String TABLOADI = "kayitlar";    // İçerideki tablonun adı
    private static final String ID = "id";             // Alanlarının adı
    private static final String TARIH = "tarih";      // tutulacak tarih
    private static final String ACIKLAMA = " aciklama";     // tutulacak aciklama
    private static final String TUTAR = "tutar";        // tutulacak para miktari
	private static final String ODEME = "odeme";     // odeme sekli belislemek icin
    private static final String[] COLUMNS = {ID,TARIH,ACIKLAMA,TUTAR,ODEME};
    private static final String CREATE_BOOK_TABLE = "CREATE TABLE "
	+ TABLOADI + " ("
	+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ TARIH + " TEXT, "
	+ ACIKLAMA + " TEXT, "
	+ TUTAR + " TEXT, "
	+ ODEME + " TEXT )";
    private int dbSecme = 0;
    public NotDatabaseHelperNakit(Context context)
    {

        	super(context, database_NAME, null, database_VERSION);
     //    super(context, name, factory, version); //orjinali böyleydi
//        super(context, new File(Environment.getExternalStorageDirectory(), database_NAME).toString(), null, database_VERSION);   //eğer external storagede kullanmak istiyorsam bu şekilde kullanılıyor.
	//	super(context, String.valueOf(context.getDatabasePath(database_NAME)), null, database_VERSION); //eğer internal hafızada kullanılmasını istiyorsam bu şekilde kullanılıyor.

        switch ( dbSecme )
        {
            case 1: {

				}
				break;
            case 2: {
					// onCreateye geri dönüp tabloyu tekrar oluşturuyor.
				}
				break;
        }
    }


    public static String getTUTAR()
    {
        return TUTAR;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    { //Burada genellikle tablo oluşturma işlemlerini yaparız.
        db.execSQL(CREATE_BOOK_TABLE);  //SQL sorgusunu çalıştırıp tabloyu açıyor.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLOADI);    //tablo varsa tabloyu siliyor. "DROP TABLE IF EXISTS " tablonun kendisini siler. Yani tamamını siler
        this.onCreate(db);                                    // onCreateye geri dönüp tabloyu tekrar oluşturuyor.
    }

    // Bu kısımda ekleme silme vs işlemlere geçiyoruz.
    public void NotEkle(Not not)
    {
        SQLiteDatabase db = this.getWritableDatabase(); // Bilgileri değiştireceğim için yazma modunda açıyorum.
        ContentValues degerler = new ContentValues();   // Ekleme silme güncelleme işlemleri için ContentValues sinifini kullanmamız gerekiyor.
        degerler.put(TARIH, not.getTarih());
        degerler.put(ACIKLAMA, not.getAciklama());     // degerler.put ile bütün alanları dolduruyoruz.
        degerler.put(TUTAR, not.getTutar());
		degerler.put(ODEME, not.getTuru());
        db.insert(TABLOADI, null, degerler);
        db.close();                                     // databaseyi kapatıyoruz.
    }

    // BU metotla geriye listeyi döndermem gerekiyor.
    public List<Not> notlariGetir()
    {
        List<Not> notlar = new ArrayList<>();       //
        String query = "SELECT * FROM " + TABLOADI;
        // SqlLiteyi yazma modundamı, seçme modundamı açmaya karar vereceğiz.
        // Seçme yapacağımız için herhangi bir datayı değiştirmeyeceğiz.
        SQLiteDatabase db = this.getReadableDatabase(); // Okuma Modunda açacağız.
        // query veya RawQuery methodu var bunlardan herhangi birini kullanabiliriz.
        Cursor cursor = db.rawQuery(query, null);  //selecet yani türkçesi imleç verilerin hepsinin üzerine gezinebilmeye yarıoyor. Sorgu sonucunda oluşan kursor objesi bize sonuyçlar üzerinde dolaşma özelliği sağlar.


        // cursor genelde aşağıdaki şekilde yapılır.
        Not not = null;                              // if in içinde kullanabişlmek için tanımlıyoruz
        if (cursor.moveToFirst())                        // boolean bir değer döndürür cursor getCount değerinden büyükse yani elde edilen bilgiden önce olup olmadığına bakıyoruz. eğer bilgiler varsa move tofirst ile 1. sıraya getiriyor. ve en başından sonuna kadar bütün nesnelerin üzerinde gezinme olanağı sağlıyor. Bütün döngüleri kullanabiliriz.
        {
            do {
                // Bir kayıtnesnesi oluşturmak gerekiyor.
                not = new Not();                     // Burda oluşturduğumuz kitap nesnesi sadece burda tanınır. dolayısıfla ifin dışında erişebilme için yukarda tanımalmamız lazım
                not.setId(Integer.parseInt(cursor.getString(0))); //artık kitap a ulaştık ve bu bilgileri bu şekilde alıyoruz. ilk nesnemiz integer olduğu için ilk elemanı integera çeviriyoruz
                not.setTarih(cursor.getString(1)); //artık ilk bilgiyi aldık.
                not.setAciklama(cursor.getString(2));
                not.setTutar(cursor.getString(3));
				not.setTuru(cursor.getString(4));
                notlar.add(not);                            // oluşturduğumuz ArrayList ile kitapları listeye ekliyoruz. ilk 3 satırı ekledik.
            } while (cursor.moveToNext());
        }
        return notlar;                                       // bununla elemanları aldıtan sonra kitapları tekrar döndürüyoruz.
    }

    public Not NotOku(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLOADI, COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        Not not = new Not();                     // Burda oluşturduğumuz kitap nesnesi sadece burda tanınır. dolayısıfla ifin dışında erişebilme için yukarda tanımalmamız lazım
        not.setId(Integer.parseInt(cursor.getString(0))); //artık kitap a ulaştık ve bu bilgileri bu şekilde alıyoruz. ilk nesnemiz integer olduğu için ilk elemanı integera çeviriyoruz
        not.setTarih(cursor.getString(1)); //artık ilk bilgiyi aldık.
        not.setAciklama(cursor.getString(2));
        not.setTutar(cursor.getString(3));
		not.setTuru(cursor.getString(4));
        return not;
    }

    public void NotSil(Not not) // herhamgi bir deger donmeyecegi icin void actik. cunku satiri komple silcez
    {
        SQLiteDatabase db = this.getWritableDatabase(); // Bilgileri değiştireceğim için yazma modunda açıyorum.

        //db.delete(toblo adi, sartim silinecek id, 2. paramettrede soru isareti gelen yere ne gelecegi);
        db.delete(TABLOADI, ID + " = ?", new String[]{String.valueOf(not.getId())});
        db.close(); // databaseyi yapatiyorum.
    }
    // Guncelleme islemi geri donus degeri ekilenen kayit sayisidir.
    // Bu yuzden int deger donduruyoruz
    public int NotGuncelle(Not not)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("tarih", not.getTarih());
        degerler.put("aciklama", not.getAciklama());
        degerler.put("tutar", not.getTutar());
		degerler.put("odeme", not.getTuru());
        int i = db.update(TABLOADI, degerler, ID + " = ?", new String[]{String.valueOf(not.getId())});
        db.close();
        return i;
    }
}


