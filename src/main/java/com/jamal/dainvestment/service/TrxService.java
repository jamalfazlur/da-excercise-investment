package com.jamal.dainvestment.service;

import com.jamal.dainvestment.dto.TrxDto;
import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.model.Investment;
import com.jamal.dainvestment.model.Trx;
import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.repository.InvestRepository;
import com.jamal.dainvestment.repository.TrxRepository;
import com.jamal.dainvestment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This is a Javadoc comment
 * implementasi dari com.jamal.dainvestment
 *
 * Jangka waktu SBN = 2 tahun
 * Imbalan menggunakan sistem Floating with Floor
 * Imbalan = BI 7 Day Reverse Repo Rate (BI 7DRRR) + spread tetap 2,15 persen
 * Nilai investasi per 1 unit = Rp 1.0000.0000
 */

@Slf4j
@Service
public class TrxService {
    @Autowired
    private TrxRepository trxRepository;

    @Autowired
    private InvestRepository investRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Trx findAll / GET
     * @return all data
     */
    public List<Trx> findAll() {
        return trxRepository.findAll();
    }

    /**
     * Investment findById / GET
     * @param id id_trx
     * @return trx data updated
     */
    public Trx findById(Integer id) {
        Optional<Trx> optionalTrx = trxRepository.findById(id);
        if(!optionalTrx.isPresent()){
            throw new DataNotFoundException("Data Transaksi Tidak Ditemukan");
        }
        return optionalTrx.get();
    }

    /**
     * Investment create / POST
     * @param trx obj
     * @return trx data created
     */
    public TrxDto create(TrxDto trx) {

        Timestamp createdTime = new Timestamp(new Date().getTime());

        int jmlBeli = trx.getJumlahBeli();

        String idSbnUpperCase = trx.getIdSbn().toUpperCase();

        trx.setIdSbn(idSbnUpperCase);

        /*========================= get detail referensi: investasi =========================== */

        Optional<Investment> investment = investRepository.findById(idSbnUpperCase);

        if(!investment.isPresent()){
            throw new DataNotFoundException("ID SBN Salah! Data Detil Investasi Tidak Ditemukan!");
        }

        int refHargaSatuan = investment.get().getHargaSatuan();
        double refPajak = investment.get().getPajak();
        double refImbalan = investment.get().getImbalan();
        String refNamaSbn = investment.get().getNamaSbn();

        /*========================= get detail referensi: user ================================ */

        Optional<User> user = userRepository.findById(trx.getIdUser());

        if(!user.isPresent()){
            throw new DataNotFoundException("ID User Salah! Data Detil User Tidak Ditemukan!");
        }

        String nama = user.get().getUserNama();
        int saldoUser = user.get().getUserSaldo();

        /* ==================================================================================== */

        Trx createTrx = new Trx(); // instance obj for db purpose

        createTrx.setId(trx.getId()); //

        createTrx.setJumlahBeli(trx.getJumlahBeli()); // set: 2

        createTrx.setIdSbn(trx.getIdSbn()); // set: ST

        createTrx.setIdUser(trx.getIdUser()); // set: 1

        /* ============================================ */

        createTrx.setUserNama(nama);

        trx.setUserNama(nama);

        /* ============ Hitung total_bayar ============ */

        int totBayar = refHargaSatuan * jmlBeli; // ( 2 * Rp. 1jt ) == Rp. 2jt

        createTrx.setTotalBayar(totBayar); // set: total_bayar

        trx.setTotalBayar(totBayar);

        /* ============================================ */

        int saldoMinusBayar = saldoUser - totBayar; // Rp. 4.3jt - Rp. 2jt

        user.get().setUserSaldo(saldoMinusBayar); //set: decrease saldo_user

        /* ============================================ */

        double imbalan = (totBayar * refImbalan) / 12;

        createTrx.setImbalan((int) imbalan);

        trx.setImbalan((int) imbalan);

        /* ============================================ */

        double pajak = imbalan * refPajak;

        createTrx.setPajak((int) pajak);

        trx.setPajak((int)pajak);

        /* ============================================ */

        double imbalanNett = imbalan - pajak;

        createTrx.setImbalanNett((int) imbalanNett);

        trx.setImbalanNett((int) imbalanNett);

        /* ============================================ */

        double totImbalan = imbalanNett * 24;

        createTrx.setTotalImbalan((int)totImbalan);

        trx.setTotalImbalan((int) totImbalan);

        /* ============================================ */

        createTrx.setCreatedTime(createdTime);
        trx.setCreatedTime(createdTime);

        /* ============================================ */

        log.info("------------------------------------------>> TIMESTAMP: " + createdTime);
        log.info("------------------------------------------>> User: " + nama);
        log.info("------------------------------------------>> SBN: " + refNamaSbn);
        log.info("------------------------------------------>> Harga Satuan: Rp." + refHargaSatuan);
        log.info("------------------------------------------>> Imbalan: " + refImbalan * 100 + "%");
        log.info("------------------------------------------>> Jumlah Beli: " + jmlBeli);
        log.info("------------------------------------------>> Total: Rp." + totBayar);

        int id = trxRepository.save(createTrx).getId();

        trx.setId(id);

        return trx;
    }

}
