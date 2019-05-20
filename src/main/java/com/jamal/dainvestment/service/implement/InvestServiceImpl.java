package com.jamal.dainvestment.service.implement;

import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.exception.NullableFalseException;
import com.jamal.dainvestment.model.Investment;
import com.jamal.dainvestment.repository.InvestRepository;
import com.jamal.dainvestment.service.InvestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a Javadoc comment
 * implementasi dari com.jamal.dainvestment.service.InvestService
 */

@Slf4j
@Service
public class InvestServiceImpl implements InvestService {
    @Autowired
    private InvestRepository investRepository;

    @Override
    public List<Investment> findAll() {
        return investRepository.findAll();
    }

    @Override
    public Investment findById(String id) {
        Optional<Investment> optionalUser = investRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
        return  optionalUser.get();
    }

    @Override
    public Investment create(Investment investment) {
        double hargaSatuan = investment.getHargaSatuan();
        double imbalan = investment.getImbalan();

        log.info("------------------------------------------>>------------------------------------------>> SBN : " + investment.getNamaSbn() );
        log.info("------------------------------------------>> Harga Satuan : Rp." + hargaSatuan );
        log.info("------------------------------------------>> Imbalan : " + (int)imbalan*100 + "%" );

            if (hargaSatuan <= 0) {
                throw new NullableFalseException("Kolom harga_satuan Wajib Diisi");
            } else if (investment.getImbalan() <= 0) {
                throw new NullableFalseException("Kolom imbalan Wajib Diisi");
            }
        return investRepository.save(investment);
    }

    @Override
    public Investment update(String id, Investment investment) {
        investment.setIdSbn(id);
        return investRepository.save(investment);
    }

    @Override
    public void delete(String id) {
        investRepository.deleteById(id);
    }
}
