package com.ivan.labdb4.model;

import com.ivan.labdb4.model.security.CustomerRole;
import com.ivan.labdb4.model.security.CustomerStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class HighlightAuthor extends Customer {
    private String nickname;

    public HighlightAuthor(String email, String username, String password, CustomerRole role, CustomerStatus status, Set<HighlightMetainfo> liked, Set<HighlightAuthor> subscriptions, String nickname) {
        super(email, username, password, role, status, liked, subscriptions);
        this.nickname = nickname;
    }

    public HighlightAuthor(Integer id, String nickname) {
        this.setId(id);
        this.nickname = nickname;
    }
}
