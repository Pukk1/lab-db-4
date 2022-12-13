package com.ivan.labdb4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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
    List<MovieMetaInfo> movieMetaInfos;
}
