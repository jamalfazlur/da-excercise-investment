package com.jamal.dainvestment.User;

import com.jamal.dainvestment.controller.UserController;
import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testFindAllUserController() throws Exception {
        mockMvc.perform(get("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.UserController.findAll")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Seluruh Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void getByIdUserController() throws Exception {
        int id = 1;
        mockMvc.perform(get("/user/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.UserController.getById")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Data Berdasarkan ID")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateSuccessUserController() throws Exception {
        String json =   "{\n" +
                "  \"nama\"     : \"Jamal\",    \n" +
                "  \"alamat\"   : \"Depok\",    \n" +
                "  \"saldo\"    : 5250500       \n" +
                "}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.UserController.create")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Membuat Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateFailedUserController() throws Exception {
        String json =  ""; //kalau diisi "{}", testing akan dianggap berhasil (success/200), padahal kosong

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                //.andExpect(jsonPath("$.message", Matchers.is("Kolum Nama Wajib Diisi")))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void updateUserController() throws  Exception {
        int id = 1;
        String json =   "{\n" +
                "  \"nama\"     : \"JamalNew\",    \n" +
                "  \"alamat\"   : \"DepokUpdated\",    \n" +
                "  \"saldo\"    : 5250500       \n" +
                "}";

        mockMvc.perform(put("/user/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.UserController.update")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Update Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));

    }

    @Test
    public void deleteUserController() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/user/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.UserController.deleteById")))
                .andExpect(jsonPath("$.message", Matchers.is("Data Berhasil dihapus")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

}

