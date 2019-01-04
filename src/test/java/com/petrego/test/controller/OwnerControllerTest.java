package com.petrego.test.controller;

import com.petrego.controller.OwnerController;
import com.petrego.controller.OwnerControllerService;
import com.petrego.dao.Owner;
import com.petrego.dao.Pet;
import com.petrego.domain.MessageCode;
import com.petrego.domain.PetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

    private static final String TESTNAME = "Test Name";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerControllerService ownerControllerService;

    @Test
    public void givenOwner_wheGetOwnerPets_thenOwnerPetsListed() throws Exception {
        Owner owner = new Owner();
        owner.setName(TESTNAME);

        Pet pet = new Pet();
        pet.setName(TESTNAME);
        pet.setPetType(PetType.dog);

        Set<Pet> pets = new HashSet<>();
        pets.add(pet);

        owner.setPets(pets);

        when(ownerControllerService.getOwner(anyLong())).thenReturn(owner);

        MvcResult result = this.mockMvc.perform(get("/v1/owners/1"))
                .andDo(print())
                .andExpect(status().is(MessageCode.OK.getCode())).andReturn();
    }
}
