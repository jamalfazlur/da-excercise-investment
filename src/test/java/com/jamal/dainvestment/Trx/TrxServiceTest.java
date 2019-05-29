package com.jamal.dainvestment.Trx;
import com.jamal.dainvestment.dto.TrxDto;
import com.jamal.dainvestment.model.Trx;
import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.service.TrxService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TrxServiceTest {
    @Autowired
    TrxService trxService;

    @Test
    public void testCreateTrx() throws Exception {
        String idSbn = "ST";
        int idUser = 1;
        int jml = 2;

        TrxDto trx = new TrxDto();

        trx.setIdSbn(idSbn);
        trx.setJumlahBeli(jml);
        trx.setIdUser(idUser);

        trxService.create(trx);

        List<Trx> trxNew = trxService.findAll(); //4
        int trxNewSize = trxNew.size()-1; //3

        log.info("=== Comparing: " + idSbn + " <> " + trxNew.get(trxNewSize).getIdSbn());
        assertThat(idSbn).isEqualTo(trxNew.get(trxNewSize).getIdSbn());

        log.info("=== Comparing: " + idUser + " <> " + trxNew.get(trxNewSize).getIdUser());
        assertThat(idUser).isEqualTo(trxNew.get(trxNewSize).getIdUser());

        log.info("=== Comparing: " + jml + " <> " + trxNew.get(trxNewSize).getJumlahBeli());
        assertThat(jml).isEqualTo(trxNew.get(trxNewSize).getJumlahBeli());

        log.info("=== User: " + trxNew.get(trxNewSize).getUserNama());
        log.info("=== Imbalan: Rp." + trxNew.get(trxNewSize).getImbalan());
        log.info("=== Pajak: Rp." + trxNew.get(trxNewSize).getPajak());
        log.info("=== Nett Imbalan: Rp." + trxNew.get(trxNewSize).getImbalanNett());
        log.info("=== Total Imbalan: Rp." + trxNew.get(trxNewSize).getTotalImbalan());
    }

    @Test
    public void findByIdUserService() throws Exception {
        Trx trx= trxService.findById(1);

        assertThat(trx)
                .hasFieldOrProperty("id")
                .hasFieldOrProperty("idUser")
                .hasFieldOrProperty("userNama")
                .hasFieldOrProperty("idSbn")
                .hasFieldOrProperty("jumlahBeli")
                .hasFieldOrProperty("totalBayar")
                .hasFieldOrProperty("imbalan")
                .hasFieldOrProperty("pajak")
                .hasFieldOrProperty("imbalanNett")
                .hasFieldOrProperty("totalImbalan");
    }

    @Test
    public void findAllUserService() throws  Exception {
        List<Trx> trx = trxService.findAll();
        log.info("============= Print #1st Trx =============");
        for(int i = 0; i < 1; i++){
            log.info("======= ID Trx: " + trx.get(i).getId());
            log.info("======= User: " + trx.get(i).getUserNama());
            log.info("======= ID SBN: " + trx.get(i).getIdSbn());
            log.info("======= Jml Beli: " + trx.get(i).getJumlahBeli());
            log.info("======= Total Bayar: " + trx.get(i).getTotalBayar());
            log.info("======= Imbalan: " + trx.get(i).getImbalan());
            log.info("======= Pajak: " + trx.get(i).getPajak());

            assertThat(trx.get(i))
                    .hasFieldOrProperty("id")
                    .hasFieldOrProperty("idUser")
                    .hasFieldOrProperty("userNama")
                    .hasFieldOrProperty("idSbn")
                    .hasFieldOrProperty("jumlahBeli")
                    .hasFieldOrProperty("totalBayar")
                    .hasFieldOrProperty("imbalan")
                    .hasFieldOrProperty("pajak")
                    .hasFieldOrProperty("imbalanNett")
                    .hasFieldOrProperty("totalImbalan");
        }

        assertThat(trx.size())
                .isGreaterThan(0);

    }

}
