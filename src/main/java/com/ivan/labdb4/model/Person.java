package com.ivan.labdb4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private Date birthdate;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}
