package com.jamal.dainvestment.Investment;

import com.jamal.dainvestment.dto.InvestDto;
import com.jamal.dainvestment.model.Investment;
import com.jamal.dainvestment.service.InvestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvestServiceTest {
    @Autowired
    private InvestService investService;

    @Test
    public void testFindAll() throws Exception{
        List<Investment> investments = investService.findAll();

        log.info("Investment Count: " + investments.size());
        for (int i = 0; i < investments.size(); i++){
            log.info("##################(  " + (i+(int)1) + "  )##################");
            log.info("======= ID SBN: " + investments.get(i).getIdSbn());
            log.info("======= Nama SBN: " + investments.get(i).getNamaSbn());
            log.info("======= Harga Satuana: Rp." + investments.get(i).getHargaSatuan());
            log.info("======= Imbalan: " + investments.get(i).getImbalan()*100 + "%");
            log.info("======= Pajak: " + investments.get(i).getPajak()*100 + "%");
        }
    }

    @Test
    public void testFindById() throws Exception {
        String id = "ST";

        log.info("======= Test Find By Id =======");
        Investment investment = investService.findById(id);
        log.info("======= What to find? =========> " + id);
        log.info("======= .: Result :. =======");
        log.info("======= Nama SBN: " + investment.getNamaSbn());
        log.info("======= Harga per Unit: Rp." + investment.getHargaSatuan());
        log.info("======= Imbalan: " + investment.getImbalan()*100 + "%");
        log.info("======= Pajak: " + investment.getPajak()*100 + "%");
    }

    public void testCreateRefInvestment() throws Exception {
        InvestDto investment = new InvestDto();
        investment.setIdSbn("ORI");
        investment.setNamaSbn("Obligasi Negara Ritel");
        investment.setHargaSatuan(1000000);
        investment.setImbalan(0.0825);
        investment.setPajak(0.20);

        investService.create(investment);

    }
}
