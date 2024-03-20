package com.mkaa.nakithesap;

public class Not
{
    int id;
    String tarih, aciklama, tutar,turu;

    public Not( String tarih, String aciklama, String tutar,String turu)
    {
        //	this.id = id;
        this.tarih = tarih;
        this.aciklama = aciklama;
        this.tutar = tutar;
		this.turu = turu;
    }

    public Not()
    {

    }

	public void setTarih(String tarih)
	{
		this.tarih = tarih;
	}

	public String getTarih()
	{
		return tarih;
	}

	public void setAciklama(String aciklama)
	{
		this.aciklama = aciklama;
	}

	public String getAciklama()
	{
		return aciklama;
	}

	public void setTutar(String tutar)
	{
		this.tutar = tutar;
	}

	public String getTutar()
	{
		return tutar;
	}

	public void setTuru(String turu)
	{
		this.turu = turu;
	}

	public String getTuru()
	{
		return turu;
	}

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    }
