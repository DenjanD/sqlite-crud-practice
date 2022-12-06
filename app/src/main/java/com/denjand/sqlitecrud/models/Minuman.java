package com.denjand.sqlitecrud.models;

public class Minuman {
    private long id;
    private String nama_minuman;
    private String harga_minuman;
    private String kategori_minuman;

    public Minuman(){

    }

    //getter and setter the id

    /**
     * @return the id
     */
    public long getId(){
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(long id){
        this.id = id;
    }

    //getter and setter the name
    /**
     * @return the nama_minuman
     */
    public String getNama_minuman(){
        return nama_minuman;
    }
    /**
     * @param nama_minuman the nama_makanan to set
     */
    public void setNama_minuman(String nama_minuman){
        this.nama_minuman = nama_minuman;
    }

    //getter and setter the brand
    /**
     * @return the harga_minuman
     */
    public String getHarga_minuman(){
        return harga_minuman;
    }
    /**
     * @param harga_minuman the merk_makanan to set
     */
    public void setHarga_minuman(String harga_minuman){
        this.harga_minuman = harga_minuman;
    }

    //getter and setter the price
    /**
     * @return the harga_minuman
     */
    public String getKategori_minuman() {
        return kategori_minuman;
    }
    /**
     * @param kategori_minuman the harga_makanan to set
     */
    public void setKategori_minuman(String kategori_minuman){
        this.kategori_minuman = kategori_minuman;
    }

    @Override
    public String toString(){
        return  nama_minuman +" "+
                harga_minuman +" "+
                kategori_minuman;
    }


}


