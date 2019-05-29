package com.jamal.dainvestment.Investment;

import com.jamal.dainvestment.controller.InvestController;
import com.jamal.dainvestment.service.InvestService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvestControllerTest {
    private MockMvc mockMvc;

    @Mock
    InvestService investService;

    @InjectMocks
    InvestController investController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(investController).build();
    }

    @Test
    public void testFindAllInvestController() throws Exception {
        mockMvc.perform(get("/invest")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.InvestController.findAll")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Seluruh Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void getByIdInvestController() throws Exception {
        int id = 1;
        mockMvc.perform(get("/invest/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.InvestController.getById")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Data Berdasarkan ID")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateSuccessInvestController() throws Exception {
        String json =   "{\n" +
                "   \"id_sbn\":\"SBR\",\n" +
                "   \"nama_sbn\":\"Savings Bond Ritel\",\n" +
                "   \"harga_satuan\":1000000,\n" +
                "   \"imbalan\": 0.079,\n" +
                "   \"pajak\":0.15\n" +
                "}";

        mockMvc.perform(post("/invest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.InvestController.create")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Membuat Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void testCreateFailedInvestController() throws Exception {
        String json =  "";

        mockMvc.perform(post("/invest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void updateInvestController() throws Exception {
        String id = "SBR";

        String json =   "{\n" +
                "   \"id_sbn\":\"SBRUpdating\",\n" +
                "   \"nama_sbn\":\"Savings Bond Ritel\",\n" +
                "   \"harga_satuan\":1000000,\n" +
                "   \"imbalan\": 0.079,\n" +
                "   \"pajak\":0.15\n" +
                "}";

        mockMvc.perform(put("/invest/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.InvestController.update")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Update Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    public void deleteInvestController() throws Exception {
        String id = "ST";

        mockMvc.perform(delete("/invest/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", Matchers.is("com.jamal.dainvestment.controller.InvestController.deleteById")))
                .andExpect(jsonPath("$.message", Matchers.is("Data Berhasil dihapus")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }
}
