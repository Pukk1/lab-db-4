package com.ivan.labdb4.model;

import com.ivan.labdb4.model.security.CustomerRole;
import com.ivan.labdb4.model.security.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends Person {
    private String email;
    @Column(name = "login")
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private CustomerRole role;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "liked",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "highlight_metainfo_id")
    )
    private List<HighlightMetainfo> liked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subscribers",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "highlight_author_id")
    )
    private List<HighlightAuthor> subscriptions;
}