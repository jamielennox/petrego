package com.petrego.test.dao;

import com.petrego.dao.Owner;
import com.petrego.dao.OwnerRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OwnerRepositoryTest extends JpaTestBase {

    private static final String NAME = "Test Owner";
    private static final String JOHN_DOE = "John Doe";

    @Autowired
    private OwnerRepository ownerRepository;

    private Owner owner;

    @Test
    public void givenOwner_whenGetPets_petsListed() {
        // Given an owner
        // When find by name
        owner = ownerRepository.findByName(JOHN_DOE);

        // Then owner John Doe is returned and has a pet chicken named
        assertNotNull(owner);
        assertEquals(JOHN_DOE, owner.getName());
        assertTrue(owner.getPets().size() > 1);
    }

    @Test
    public void givenOwner_whenSave_ownerPersisted() {
        // Given new owner
        owner = new Owner();
        owner.setName(NAME);

        // When saving owner
        ownerRepository.saveAndFlush(owner);

        // Then owner is persisted to db
        Owner persistedOwner = ownerRepository.findById(owner.getOwnerId()).get();
        assertNotNull(persistedOwner);
    }
}
