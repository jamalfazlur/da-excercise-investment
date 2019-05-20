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
     * @return all data
     */
    List<Trx> findAll();

    /**
     * This is a Javadoc comment
     * menampilkan data berdasarkan ID
     * @param id untuk key
     * @return data by id
     */
    Trx findById(Integer id);


    /**
     * This is a Javadoc comment
     * mengubah data, butuh parameter ID
     * @param id for key
     * @param user for data
     * @return data updated
     */
    Trx update (Integer id, Trx user);

    /**
     * This is a Javadoc comment
     * menambah data baru
     * @param user for create
     * @return data created
     */
    Trx create(Trx user);

    /**
     * This is a Javadoc comment
     * menghapus data berdasarkan ID
     * @param id for key
     */
    void delete(Integer id);
}
