package com.oims.futureprogram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

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
    private String nama;

    @Size(min = 11, max = 13)
    private String hp;

    @Email
    private String email;

}
