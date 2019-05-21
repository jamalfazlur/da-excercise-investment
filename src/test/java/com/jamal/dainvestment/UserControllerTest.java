package com.jamal.dainvestment;

import com.jamal.dainvestment.controller.UserController;
import com.jamal.dainvestment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.UserControllerfindAll")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Seluruh Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateSuccess() throws Exception {
        String json =   "{\n" +
                            "  \"nama\"     : \"Jamal\",    \n" +
                            "  \"alamat\"   : \"Depok\",    \n" +
                            "  \"saldo\"    : 5250500       \n" +
                        "}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.UserControllercreate")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Membuat Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateFailed() throws Exception {
        String json =  "";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}
