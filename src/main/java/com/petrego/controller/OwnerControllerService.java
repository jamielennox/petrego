package com.petrego.controller;

import com.petrego.dao.Owner;
import com.petrego.dao.OwnerRepository;
import com.petrego.domain.MessageCode;
import com.petrego.domain.PetRegoException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerControllerService {

    private final OwnerRepository ownerRepository;

    public OwnerControllerService(final OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Get owner information based upon id.
     * @param ownerId
     * @return Owner details
     * @throws Exception
     */
    public Owner getOwner(final long ownerId) throws Exception {
        try {
            Optional<Owner> owner = ownerRepository.findById(ownerId);

            if (owner.isPresent()) {
                return owner.get();
            } else {
                throw new PetRegoException(MessageCode.NOT_FOUND, "Owner not found.");
            }
        } catch (Exception exception) {
            throw exception;
        }
    }
}
