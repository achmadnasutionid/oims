package com.oims.futureprogram.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(generator = "inventory_generator")
    @SequenceGenerator(
            name = "inventory_generator",
            sequenceName = "inventory_generator",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nama;

    @NotNull
    private Long harga;

    @NotNull
    private Long jumlah;

    @Size(max = 200)
    private String deskripsi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHarga() {
        return harga;
    }

    public void setHarga(Long harga) {
        this.harga = harga;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
