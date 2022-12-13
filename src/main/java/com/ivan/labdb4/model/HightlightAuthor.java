package com.ivan.labdb4.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class HightlightAuthor extends Customer {
    private String nickname;
}
