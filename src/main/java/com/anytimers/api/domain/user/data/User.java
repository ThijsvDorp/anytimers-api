package com.anytimers.api.domain.user.data;


import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.anytimers.api.domain.group.data.Group;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a user in the system.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotBlank
    @Column(name = "email")
    private String email;

    @Nonnull
    @Column(name = "username")
    private String userName;

    @Nonnull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "prefix")
    private String prefix;

    @Nonnull
    @Column(name = "last_name")
    private String lastName;

    @Nonnull
    @ToString.Exclude
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToMany
    @JoinTable(
        name = "user_groups",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @Column(name = "created_on")
    private Instant createdOn;

    @Setter(AccessLevel.NONE)
    @Column(name = "updated_on")
    private Instant updatedOn;

    @PreUpdate
    private void preUpdate() {
        this.updatedOn = Instant.now();
    }

    @PrePersist
    private void prePersist() {
        if (this.createdOn == null) {
            this.createdOn = Instant.now();
        } 
        if (this.role == null) {
            this.role = Role.USER;
        }
    }
}
