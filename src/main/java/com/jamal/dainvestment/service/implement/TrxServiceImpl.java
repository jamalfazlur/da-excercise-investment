package com.jamal.dainvestment.service.implement;

import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.model.Investment;
import com.jamal.dainvestment.model.Trx;
import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.repository.InvestRepository;
import com.jamal.dainvestment.repository.TrxRepository;
import com.jamal.dainvestment.repository.UserRepository;
import com.jamal.dainvestment.service.TrxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a Javadoc comment
 * implementasi dari com.jamal.dainvestment.service.UserService
 *
 * Jangka waktu SBN = 2 tahun
 * Imbalan menggunakan sistem Floating with Floor
 * Imbalan = BI 7 Day Reverse Repo Rate (BI 7DRRR) + spread tetap 2,15 persen
 * Nilai investasi per 1 unit = Rp 1.0000.0000
 */

@Slf4j
@Service
public class TrxServiceImpl implements TrxService {
    @Autowired
    private TrxRepository trxRepository;

    @Autowired
    private InvestRepository investRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Trx> findAll() {
        return trxRepository.findAll();
    }

    @Override
    public Trx findById(Integer id) {
        Optional<Trx> optionalTrx = trxRepository.findById(id);
        if(!optionalTrx.isPresent()){
            throw new DataNotFoundException("Data Transaksi Tidak Ditemukan");
        }
        return optionalTrx.get();
    }

    @Override
    public Trx update(Integer id, Trx trx) {
        trx.setId(id);

        return trxRepository.save(trx);
    }

    @Override
    public Trx create(Trx trx) {

        int jmlBeli = trx.getJumlahBeli();

        /* get detail referensi: investasi */
        Optional<Investment> investment = investRepository.findById(trx.getIdSbn());

        if(!investment.isPresent()){
            throw new DataNotFoundException("ID SBN Salah! Data Detil Investasi Tidak Ditemukan!");
        }

        int refHargaSatuan = investment.get().getHargaSatuan();
        double refPajak = investment.get().getPajak();
        double refImbalan = investment.get().getImbalan();
        String refNamaSbn = investment.get().getNamaSbn();

        /* get detail referensi: investasi */
        Optional<User> user = userRepository.findById(trx.getIdUser());

        if(!user.isPresent()){
            throw new DataNotFoundException("ID User Salah! Data Detil User Tidak Ditemukan!");
        }

        String nama = user.get().getUserNama();
        trx.setUserNama(nama);

        /* set total_bayar */
        int totBayar = refHargaSatuan * jmlBeli; // 2 * 1jt == 2jt
        trx.setTotalBayar(totBayar);

        int saldoMinusBayar = user.get().getUserSaldo() - totBayar;
        user.get().setUserSaldo(saldoMinusBayar);

        double imbalan = (totBayar * refImbalan) / 12;
        trx.setImbalan((int) imbalan);

        double pajak = imbalan * refPajak;
        trx.setPajak((int) pajak);

        double imbalanNett = imbalan - pajak;
        trx.setImbalanNett((int) imbalanNett);

        double totImbalan = imbalanNett * 24;
        trx.setTotalImbalan((int)totImbalan);

        log.info("------------------------------------------>> User: " + nama);
        log.info("------------------------------------------>> SBN: " + refNamaSbn);
        log.info("------------------------------------------>> Harga Satuan: Rp." + refHargaSatuan);
        log.info("------------------------------------------>> Imbalan: " + refImbalan*100 + "%");
        log.info("------------------------------------------>> Jumlah Beli: " + jmlBeli);
        log.info("------------------------------------------>> Total: Rp." + totBayar);

        return trxRepository.save(trx);
    }

    @Override
    public void delete(Integer id) {
        trxRepository.deleteById(id);
    }
}
