package com.oims.futureprogram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "formrequest")
public class FormRequest{


    @Id
    @GeneratedValue (generator = "formrequest_generator")
    @SequenceGenerator(
            name = "formrequest_generator",
            sequenceName = "formrequest_generator",
            initialValue = 1
    )
    private Long id;

    @OneToMany
    private Inventory inventories;

    @NotNull
    private Date tanggal;

    @NotBlank
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Employee employee;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public Inventory getInventories() {
        return inventories;
    }

    public void setInventories(Inventory inventories) {
        this.inventories = inventories;
    }
}
