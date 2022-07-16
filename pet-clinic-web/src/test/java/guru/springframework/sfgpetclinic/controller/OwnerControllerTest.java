package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    private OwnerController ownerController;

    @Mock
    private Model model;

    @Mock
    private OwnerService ownerService;

    @Test
    void listOwners() throws Exception {
        // given
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
        RequestBuilder requestBuilder = get("/owners");

        Set<Owner> returnOwner = new HashSet<>();
        returnOwner.add(new Owner());
        returnOwner.add(new Owner());
        returnOwner.add(new Owner());
        given(ownerService.findAll()).willReturn(returnOwner);

        // when
        ownerController.listOwners(model);

        //then
        verify(ownerService, times(1)).findAll();
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(3)));
    }
}