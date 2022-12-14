package com.ivan.labdb4.model.dto;

import com.ivan.labdb4.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String email;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Date birthdate;
    private Gender gender;
}
