package com.petrego.controller;

import com.petrego.dao.Owner;
import com.petrego.domain.LogUtils;
import com.petrego.domain.MessageCode;
import com.petrego.domain.PetRegoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OwnerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OwnerControllerService ownerControllerService;

    public OwnerController(final OwnerControllerService ownerControllerService) {
        this.ownerControllerService = ownerControllerService;
    }

    /**
     * Get owner information.
     * @param ownerId
     * @return Owner details in JSON
     */
    @GetMapping(value = "/v1/owners/{ownerId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOwnerPets(final @PathVariable long ownerId) {
        try {
            Owner owner = ownerControllerService.getOwner(ownerId);
            owner.add(linkTo(methodOn(this.getClass()).getOwnerPets(ownerId)).withSelfRel());

            return ResponseEntity.status(MessageCode.OK.getCode()).body(owner);
        } catch (PetRegoException exception) {
            return ResponseEntity.status(exception.getMessageCode().getCode())
                    .body(exception.getMessage());
        } catch (Exception exception) {
            logger.error(LogUtils.logMessage(MessageCode.FATAL_ERROR, exception.getCause().getMessage()));
            return ResponseEntity.status(MessageCode.FATAL_ERROR.getCode())
                    .body(exception.getCause().getMessage());
        }
    }
}
