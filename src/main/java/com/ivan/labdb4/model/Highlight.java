package com.ivan.labdb4.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Highlight {

    @Id
    private Long id;
    @OneToOne(targetEntity = HightlightAuthor.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private HightlightAuthor author;
//    private String fileLink;
}
