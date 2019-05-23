package com.jamal.dainvestment.service;

import com.jamal.dainvestment.dto.InvestDto;
import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.exception.NullableFalseException;
import com.jamal.dainvestment.model.Investment;
import com.jamal.dainvestment.repository.InvestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a Javadoc comment
 * implementasi dari com.jamal.dainvestment.service
 */

@Slf4j
@Service
public class InvestService {
    @Autowired
    private InvestRepository investRepository;

    /**
     * Investment findAll / GET
     * @return all data Investment
     */
    public List<Investment> findAll() {
        return investRepository.findAll();
    }

    /**
     * Investment findBy ID / GET
     * @param id id_sbn
     * @return data object
     */
    public Investment findById(String id) {
        Optional<Investment> optionalUser = investRepository.findById(id.toUpperCase());
        if(!optionalUser.isPresent()){
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
        return  optionalUser.get();
    }

    /**
     * Investment create / POST
     * @param investment obj
     * @return investment obj
     */
    public InvestDto create(InvestDto investment) {

        Investment newInvest = new Investment();

        newInvest.setIdSbn(investment.getIdSbn().toUpperCase());
        newInvest.setNamaSbn(investment.getNamaSbn());
        newInvest.setHargaSatuan(investment.getHargaSatuan());
        newInvest.setImbalan(investment.getImbalan());
        newInvest.setPajak(investment.getPajak());

        if (investment.getHargaSatuan() <= 0) {
            throw new NullableFalseException("Kolom harga_satuan Wajib Diisi"); }
        if (investment.getImbalan() <= 0) {
            throw new NullableFalseException("Kolom imbalan Wajib Diisi"); }

        investment.setIdSbn(investRepository.save(newInvest).getIdSbn().toUpperCase());

        return investment;
    }

    /**
     * Investment update / PUT
     * @param id id_invest
     * @param investment obj
     * @return investment data updated
     */
    public InvestDto update(String id, InvestDto investment) {
        Investment putInvest = new Investment();

        putInvest.setIdSbn(id.toUpperCase());
        putInvest.setNamaSbn(investment.getNamaSbn());
        putInvest.setHargaSatuan(investment.getHargaSatuan());
        putInvest.setImbalan(investment.getImbalan());
        putInvest.setPajak(investment.getPajak());

        investRepository.save(putInvest);

        investment.setIdSbn(id.toUpperCase());
        return investment;
    }

    /**
     * Investment update / PUT
     * @param id id_invest
     * @return investment data deleted
     */
    public void delete(String id) {
        investRepository.deleteById(id.toUpperCase());
    }
}
