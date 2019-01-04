package com.oims.futureprogram.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FormRequest {

    @Id
    @GeneratedValue(generator = "formrequest_generator")
    @SequenceGenerator(
            name = "formrequest_generator",
            sequenceName = "formrequest_generator",
            initialValue = 1
    )
    private Long id;

    private Long supervisorId;

    private Long inventoryId;

    private Long jumlah;

    private String status;

}
