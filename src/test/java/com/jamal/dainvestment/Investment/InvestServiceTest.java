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
import static org.assertj.core.api.Assertions.*;

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
    public void testFindByIdInvestTest() throws Exception {
        String id = "ST";

        log.info("======= Test Find By Id =======");
        Investment investment = investService.findById(id);

        assertThat(investment)
                .hasFieldOrProperty("namaSbn");
        assertThat(investment)
                .hasFieldOrProperty("hargaSatuan");
        assertThat(investment)
                .hasFieldOrProperty("imbalan");
        assertThat(investment)
                .hasFieldOrProperty("pajak");

    }

    @Test
    public void testCreateInvestment() throws Exception {
        int investment1 = investService.findAll().size();

        InvestDto dataTest = new InvestDto();
        dataTest.setIdSbn("ORI");
        dataTest.setNamaSbn("Obligasi Negara Ritel");
        dataTest.setHargaSatuan(1000000);
        dataTest.setImbalan(0.0825);
        dataTest.setPajak(0.20);

        investService.create(dataTest);

        List<Investment> investments2 = investService.findAll();
        int lastIndex = investments2.size() -1;

        Investment investment = investments2.get(lastIndex);
        assertThat(investment).isEqualToComparingFieldByField(dataTest);

        assertThat(investment1).isLessThan(investments2.size());
    }

    @Test
    public void testUpdateInvestment() throws Exception {
        String id = "ST";
        Investment investment = investService.findById(id);

        String namaSbnOld = investment.getNamaSbn();
        int hargaSatuanOld = investment.getHargaSatuan();
        double imbalanOld = investment.getImbalan();
        double pajakOld = investment.getPajak();

        InvestDto invest = new InvestDto();
        invest.setIdSbn(id);
        invest.setNamaSbn("Setelah Time");
        invest.setHargaSatuan(12);
        invest.setPajak(0.9);
        invest.setImbalan(0.010);

        investService.update(id, invest);

        Investment toCompare = investService.findById(id);

        log.info("==== Comparing: " + toCompare.getIdSbn() + " <> " + id);
        assertThat(toCompare.getIdSbn()).isEqualTo(id);

        log.info("==== Comparing: " + toCompare.getNamaSbn() + " <> " + namaSbnOld);
        assertThat(toCompare.getNamaSbn()).isNotEqualTo(namaSbnOld);

        log.info("==== Comparing: " + toCompare.getHargaSatuan() + " <> " + hargaSatuanOld);
        assertThat(toCompare.getHargaSatuan()).isNotEqualTo(hargaSatuanOld);

        log.info("==== Comparing: " + toCompare.getImbalan() + " <> " + imbalanOld);
        assertThat(toCompare.getImbalan()).isNotEqualTo(imbalanOld);

        log.info("==== Comparing: " + toCompare.getPajak() + " <> " + pajakOld);
        assertThat(toCompare.getPajak()).isNotEqualTo(pajakOld);
    }

    @Test
    public void testDeleteInvestment() throws Exception {
        String id = "SBR";

        int investmentsTotal = investService.findAll().size();
        log.info("Sebelum Delete: " + String.valueOf(investmentsTotal));

        investService.delete(id);

        int investmentsTotalNew = investService.findAll().size();
        log.info("Setelah Delete: " + String.valueOf(investmentsTotalNew));

        assertThat(investmentsTotal).isGreaterThan(investmentsTotalNew);
    }
}
