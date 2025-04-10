package com.example.ppb_f_inputoutput;

public class mahasiswa {
    private String nama;
    private String nrp;

    public mahasiswa(String nama, String nrp) {
        this.nama = nama;
        this.nrp = nrp;
    }

    public String getNama() {
        return nama;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
