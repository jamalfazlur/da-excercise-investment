package com.jamal.dainvestment.service;
import com.jamal.dainvestment.model.Trx;
import java.util.List;

/**
 * This is a Javadoc comment
 * User Service
 */
public interface TrxService {
    /**
     * This is a Javadoc comment
     * findAll untuk menampilkan semua data
     */
    List<Trx> findAll();

    /**
     * This is a Javadoc comment
     * menampilkan data berdasarkan ID
     */
    Trx findById(Integer id);

    /**
     * This is a Javadoc comment
     * mengubah data, butuh parameter ID
     */
    Trx update (Integer id, Trx user);

    /**
     * This is a Javadoc comment
     * menambah data baru
     */
    Trx create(Trx user);

    /**
     * This is a Javadoc comment
     * menghapus data berdasarkan ID
     */
    void delete(Integer id);
}
