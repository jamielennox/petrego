package com.petrego.controller;

import com.petrego.dao.Owner;
import com.petrego.dao.Pet;
import com.petrego.domain.LogUtils;
import com.petrego.domain.MessageCode;
import com.petrego.domain.PetFood;
import com.petrego.domain.PetRegoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

// TODO Add Basic AUTH to access

@RestController
public class OwnerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OwnerControllerService ownerControllerService;

    public OwnerController(final OwnerControllerService ownerControllerService) {
        this.ownerControllerService = ownerControllerService;
    }

    /**
     * Get pets belonging to owner.
     * @param ownerId
     * @return Owner details in JSON
     */
    @GetMapping(value = "/v1/owners/{ownerId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOwnerPets(final @PathVariable long ownerId) {
        try {
            Owner owner = ownerControllerService.getOwner(ownerId);
            Resource<Owner> ownerResource = new Resource<>(owner);
            ownerResource.add(linkTo(methodOn(this.getClass()).getOwnerPets(ownerId)).withSelfRel());

            // TODO Add PetController with GET so that Owner response can contain HATEOAS link for each Pet.

            return ResponseEntity.status(MessageCode.OK.getCode()).body(ownerResource);
        } catch (PetRegoException exception) {
            return ResponseEntity.status(exception.getMessageCode().getCode())
                    .body(exception.getMessage());
        } catch (Exception exception) {
            logger.error(LogUtils.logMessage(MessageCode.FATAL_ERROR, exception.getCause().getMessage()));
            return ResponseEntity.status(MessageCode.FATAL_ERROR.getCode())
                    .body(exception.getCause().getMessage());
        }
    }

    /**
     * Get pets belonging to owner along with the food the pet eats.
     * @param ownerId
     * @return Owner information along with pet details.
     */
    @GetMapping(value = "/v2/owners/{ownerId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOwnerPetsWithFood(final @PathVariable long ownerId) {
        try {
            Owner owner = ownerControllerService.getOwner(ownerId);
            Resource<Owner> ownerResource = new Resource<>(owner);
            ownerResource.add(linkTo(methodOn(this.getClass()).getOwnerPetsWithFood(ownerId)).withSelfRel());

            for (Pet pet : owner.getPets()) {
                pet.setFood(PetFood.valueOf(pet.getPetType().toString()));
            }

            return ResponseEntity.status(MessageCode.OK.getCode()).body(ownerResource);
        } catch (PetRegoException exception) {
            return ResponseEntity.status(exception.getMessageCode().getCode())
                    .body(exception.getMessage());
        } catch (Exception exception) {
            logger.error(LogUtils.logMessage(MessageCode.FATAL_ERROR, exception.getCause().getMessage()));
            return ResponseEntity.status(MessageCode.FATAL_ERROR.getCode())
                    .body(exception.getCause().getMessage());
        }
    }

    // TODO Add POST endpoint to create owner

    // TODO Add PUT endpoint to update owner

    // TODO Add DELETE endpoint to remove owner
}
