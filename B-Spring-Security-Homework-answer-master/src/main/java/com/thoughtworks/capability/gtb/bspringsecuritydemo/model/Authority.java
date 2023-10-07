package com.thoughtworks.capability.gtb.bspringsecuritydemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@IdClass(Authority.AuthorityId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    private String username;
    @Id
    private String authority;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthorityId implements Serializable {
        private String username;
        private String authority;
    }
}
