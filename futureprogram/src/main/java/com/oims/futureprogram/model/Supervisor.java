package com.oims.futureprogram.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "supervisor")

public class Supervisor {

    @Id
    @GeneratedValue(generator = "supervisor_generator")
    @SequenceGenerator(
            name = "supervisor_generator",
            sequenceName = "supervisor_generator",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nama;

    @Size(min = 11, max = 13)
    private String hp;

    @Email
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
