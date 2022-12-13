package com.ivan.labdb4.model;

import javax.persistence.*;
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
public class Director extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "director_movie_meta_info",
            joinColumns = @JoinColumn(name = "director_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_meta_info_id")
    )
    private List<MovieMetaInfo> movieMetaInfos;
}