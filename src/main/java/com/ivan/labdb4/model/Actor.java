package com.ivan.labdb4.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "actor_movie_meta_info",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_meta_info_id")
    )
    Set<MovieMetaInfo> movieMetaInfos;
}
