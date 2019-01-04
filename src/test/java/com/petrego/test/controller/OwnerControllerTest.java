package com.petrego.test.controller;

import com.petrego.controller.OwnerController;
import com.petrego.controller.OwnerControllerService;
import com.petrego.dao.Owner;
import com.petrego.domain.MessageCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerControllerService ownerControllerService;

    @Test
    public void shouldReturnOner() throws Exception {
        when(ownerControllerService.getOwner(anyLong())).thenReturn(any(Owner.class));

        MvcResult result = this.mockMvc.perform(get("/v1/owners/1"))
                .andDo(print())
                .andExpect(status().is(MessageCode.OK.getCode())).andReturn();
    }
}
