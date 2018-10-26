package com.oims.futureprogram.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table (name = "inventories")
public class Inventories {

    @Id
    @GeneratedValue (generator = "inventories_generator")
    @SequenceGenerator(
            name = "inventories_generator",
            sequenceName = "inventories_generator",
            initialValue = 1
    )
    private Long id;

    private Long id_inventory;

    private Long jumlah_inventory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FormRequest formRequest;

    public Long getId_inventory() {
        return id_inventory;
    }

    public void setId_inventory(Long id_inventory) {
        this.id_inventory = id_inventory;
    }

    public Long getJumlah_inventory() {
        return jumlah_inventory;
    }

    public void setJumlah_inventory(Long jumlah_inventory) {
        this.jumlah_inventory = jumlah_inventory;
    }

    public FormRequest getFormRequest() {
        return formRequest;
    }

    public void setFormRequest(FormRequest formRequest) {
        this.formRequest = formRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
