package com.jamal.dainvestment.Trx;

import com.jamal.dainvestment.controller.InvestController;
import com.jamal.dainvestment.controller.TrxController;
import com.jamal.dainvestment.service.InvestService;
import com.jamal.dainvestment.service.TrxService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TrxControllerTest {
    private MockMvc mockMvc;

    @Mock
    TrxService trxService;

    @InjectMocks
    TrxController trxController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(trxController).build();
    }

    @Test
    public void testFindAllTrx() throws Exception {
        mockMvc.perform(get("/trx")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.TrxController.findAll")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Seluruh Data Trx")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void getByIdTrx() throws Exception {
        int id = 1;
        mockMvc.perform(get("/trx/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.TrxController.getById")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Data Berdasarkan ID")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateSuccessTrx() throws Exception {
        String json =   "{\n" +
                        "   \"id_user\":1,\n" +
                        "   \"id_sbn\":\"St\",\n" +
                        "   \"jumlah_beli\":2\n" +
                        "}";

        mockMvc.perform(post("/trx")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.TrxController.create")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Membuat Data Trx")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateFailedTrx() throws Exception {
        String json =  "";

        mockMvc.perform(post("/trx")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
        ;
    }

}
