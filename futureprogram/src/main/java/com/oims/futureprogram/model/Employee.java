package com.oims.futureprogram.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")

public class Employee {

    @Id
    @GeneratedValue(generator = "employee_generator")
    @SequenceGenerator(
            name = "employee_generator",
            sequenceName = "employee_generator",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
