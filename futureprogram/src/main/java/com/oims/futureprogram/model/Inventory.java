package com.oims.futureprogram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

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

    private Long harga;

    private Long jumlah;

    private String deskripsi;

}
