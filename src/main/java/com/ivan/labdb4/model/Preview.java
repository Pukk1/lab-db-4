package com.ivan.labdb4.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Preview {
    @Id
    private Integer id;
    private Integer height;
    private Integer width;
    private String tag;
    private String link;
    private Integer movieMetaInfoId;
}
