package com.ivan.labdb4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Preview {
    @Id
    private Long id;
    private Integer height;
    private Integer width;
    private String tag;
    private String link;
    private Integer movieMetaInfoId;
}
