package com.thoughtworks.capability.gtb.bspringsecuritydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;

    private String password;

    private Boolean enabled;

    @OneToMany(cascade = ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "username")
    private List<Authority> authorities;
}
