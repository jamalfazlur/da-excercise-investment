package com.jamal.dainvestment.service;
import com.jamal.dainvestment.model.Investment;
import java.util.List;

/**
 * This is a Javadoc comment
 * User Service
 */
public interface InvestService {
    /**
     * @Service
     * findAll untuk menampilkan semua data
     */
    List<Investment> findAll();

    /**
     * @Service
     * findById untuk menampilkan data berdasarkan ID
     */
    Investment findById(String id);

    /**
     * @Service
     * create untuk membuat data investasi
     */
    Investment create(Investment investment);

    /**
     * @Service
     * update untuk update data berdasarkan ID
     */
    Investment update(String id, Investment investment);

    /**
     * @Service
     * delete untuk menghapus data berdasarkan ID
     */
    void delete(String id);
}
