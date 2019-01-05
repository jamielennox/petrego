package com.petrego.test.controller;

import com.petrego.controller.OwnerController;
import com.petrego.controller.OwnerControllerService;
import com.petrego.dao.Owner;
import com.petrego.dao.Pet;
import com.petrego.domain.MessageCode;
import com.petrego.domain.PetType;
import org.junit.Before;
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

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

    private static final String TESTNAME = "Test Name";
    private static final String REQUEST = "/v1/owners/1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerControllerService ownerControllerService;

    private Owner owner;
    private Pet pet;
    private Set<Pet> ownerPets;

    @Before
    public void setupTest() {
        owner = new Owner();
        owner.setName(TESTNAME);

        pet = new Pet();
        pet.setName(TESTNAME);
        pet.setPetType(PetType.dog);

        ownerPets = new HashSet<>();
        ownerPets.add(pet);
    }

    @Test
    public void givenOwner_wheGetOwnerPets_thenOwnerPetsListed() throws Exception {
        // Given owner with pet dog
        owner.setPets(ownerPets);

        // When requesting owner's pets
        when(ownerControllerService.getOwner(anyLong())).thenReturn(owner);

        // Then expect owner details returned as JSON
        MvcResult result = this.mockMvc.perform(get(REQUEST))
                .andDo(print())
                .andExpect(status().is(MessageCode.OK.getCode())).andReturn();
        // and response is HATEOAS
        assertTrue(result.getResponse().getContentAsString().contains(REQUEST));
        // and response does not contain food
        assertTrue(!result.getResponse().getContentAsString().contains("food"));
    }

    @Test
    public void givenv2_whenGetOwnerPets_thenOwnerPetsListedWithFood() throws Exception {
        // Given v2 request
        owner.setPets(ownerPets);
        final String v2Request = "/v2/owners/1";

        // When requesting owner's pets
        when(ownerControllerService.getOwner(anyLong())).thenReturn(owner);

        // Then expect owner details returned as JSON
        MvcResult result = this.mockMvc.perform(get(v2Request))
                .andDo(print())
                .andExpect(status().is(MessageCode.OK.getCode())).andReturn();
        // and response has food information
        assertTrue(result.getResponse().getContentAsString().contains("\"food\":\"bone\""));
        // and response HATEOAS link is v2
        assertTrue(result.getResponse().getContentAsString().contains(v2Request));
    }
}
