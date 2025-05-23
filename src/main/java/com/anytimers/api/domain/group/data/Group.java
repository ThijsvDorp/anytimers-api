package com.anytimers.api.domain.group.data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.anytimers.api.domain.user.data.User;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Group {
    
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nonnull
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "description")
    private String description;

    @Nonnull
    @Column(name = "owner_id", nullable = false)
    private int ownerId;

    //TODO: Think about adding public or private boolean.

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

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
    }
}
