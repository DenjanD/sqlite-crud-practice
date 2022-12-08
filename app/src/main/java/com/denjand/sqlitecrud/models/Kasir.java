package com.denjand.sqlitecrud.models;

public class Kasir {
    private long id;
    private String nama_kasir;
    private String umur_kasir;
    private String alamat_kasir;

    public Kasir(){

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
     * @return the nama_kasir
     */
    public String getNama_kasir(){
        return nama_kasir;
    }
    /**
     * @param nama_kasir the nama_kasir to set
     */
    public void setNama_kasir(String nama_kasir){
        this.nama_kasir = nama_kasir;
    }

    //getter and setter the brand
    /**
     * @return the umur_kasir
     */
    public String getUmur_kasir(){
        return umur_kasir;
    }
    /**
     * @param umur_kasir the merk_makanan to set
     */
    public void setUmur_kasir(String umur_kasir){
        this.umur_kasir = umur_kasir;
    }

    //getter and setter the price
    /**
     * @return the umur_kasir
     */
    public String getAlamat_kasir() {
        return alamat_kasir;
    }
    /**
     * @param alamat_kasir the harga_makanan to set
     */
    public void setAlamat_kasir(String alamat_kasir){
        this.alamat_kasir = alamat_kasir;
    }

    @Override
    public String toString(){
        return  nama_kasir +" "+
                umur_kasir +" "+
                alamat_kasir;
    }


}


