package com.denjand.sqlitecrud.models;

public class Makanan {
    private long id;
    private String nama_makanan;
    private String harga_makanan;
    private String kategori_makanan;

    public Makanan(){

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
     * @return the nama_makanan
     */
    public String getNama_makanan(){
        return nama_makanan;
    }
    /**
     * @param nama_makanan the nama_makanan to set
     */
    public void setNama_makanan(String nama_makanan){
        this.nama_makanan = nama_makanan;
    }

    //getter and setter the brand
    /**
     * @return the merk_makanan
     */
    public String getHarga_makanan(){
        return harga_makanan;
    }
    /**
     * @param harga_makanan the merk_makanan to set
     */
    public void setHarga_makanan(String harga_makanan){
        this.harga_makanan = harga_makanan;
    }

    //getter and setter the price
    /**
     * @return the harga_makanan
     */
    public String getKategori_makanan() {
        return kategori_makanan;
    }
    /**
     * @param kategori_makanan the harga_makanan to set
     */
    public void setKategori_makanan(String kategori_makanan){
        this.kategori_makanan = kategori_makanan;
    }

    @Override
    public String toString(){
        return  nama_makanan +" "+
                harga_makanan +" "+
                kategori_makanan;
    }


}


