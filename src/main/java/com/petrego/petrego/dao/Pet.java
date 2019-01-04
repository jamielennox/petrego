package com.petrego.petrego.dao;

import com.petrego.petrego.domain.PetType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "pet")
public final class Pet {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = MIN_LENGTH, max = MAX_LENGTH, message = "Owner name must be between 3 and 255 characters long.")
    @NotNull
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private PetType petType;

    @Column(name = "created_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToMany(mappedBy = "pets")
    private Set<Owner> owners = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(final PetType petType) {
        this.petType = petType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(final Set<Owner> owners) {
        this.owners = owners;
    }
}
