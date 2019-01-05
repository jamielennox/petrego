package com.petrego.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.petrego.domain.PetFood;
import com.petrego.domain.PetType;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "pet")
public class Pet {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long petId;

    @Column(name = "name", nullable = false)
    @Size(min = MIN_LENGTH, max = MAX_LENGTH, message = "Owner name must be between 3 and 255 characters long.")
    @NotNull
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private PetType petType;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String food;

    @Column(name = "created_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToMany(mappedBy = "pets")
    @JsonBackReference
    private Set<Owner> owners = new HashSet<>();

    public final Long getPetId() {
        return petId;
    }

    public final void setPetId(final Long id) {
        this.petId = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final PetType getPetType() {
        return petType;
    }

    public final void setPetType(final PetType petType) {
        this.petType = petType;
    }

    public final Date getCreatedDate() {
        return createdDate;
    }

    public final void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public final Set<Owner> getOwners() {
        return owners;
    }

    public final void setOwners(final Set<Owner> owners) {
        this.owners = owners;
    }

    public final String getFood() {
        return food;
    }

    public final void setFood(final PetFood food) {
        this.food = food.getFood();
    }
}
