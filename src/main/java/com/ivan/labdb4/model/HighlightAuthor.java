package com.ivan.labdb4.model;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class HighlightAuthor extends Customer {
    private String nickname;
}
