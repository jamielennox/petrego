package com.petrego.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "owner")
public class Owner extends ResourceSupport {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long ownerId;

    @Column(name = "name", nullable = false)
    @Size(min = MIN_LENGTH, max = MAX_LENGTH, message = "Owner name must be between 3 and 255 characters long.")
    @NotNull
    private String name;

    @Column(name = "created_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "owner_pet",
            joinColumns = { @JoinColumn(name = "owner_id") },
            inverseJoinColumns = { @JoinColumn(name = "pet_id") })
    @JsonManagedReference
    private Set<Pet> pets = new HashSet<>();

    public final Long getOwnerId() {
        return ownerId;
    }

    public final void setOwnerId(final Long ownerId) {
        this.ownerId = ownerId;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final Date getCreatedDate() {
        return createdDate;
    }

    public final void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public final Set<Pet> getPets() {
        return pets;
    }

    public final void setPets(final Set<Pet> pets) {
        this.pets = pets;
    }

    /**
     * Get pet by name if exists.
     * @param petName
     * @return Optional pet details.
     */
    public final Optional<Pet> getPetByName(final String petName) {
        return pets.stream().filter(p -> p.getName().equals(petName)).findAny();
    }
}
