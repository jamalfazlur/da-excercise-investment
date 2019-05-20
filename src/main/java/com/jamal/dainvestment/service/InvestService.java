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
     * @return list semua invest
     */
    List<Investment> findAll();

    /**
     * @Service
     * findById untuk menampilkan data berdasarkan ID
     * @return invest by id
     * @param id dari entity investment
     */
    Investment findById(String id);

    /**
     * @Service
     * create untuk membuat data investasi
     * @param investment data investment
     * @return data yg telah dibuat
     */
    Investment create(Investment investment);

    /**
     * @Service
     * update untuk update data berdasarkan ID
     * @param id untuk key,
     * @param investment untuk full data
     * @return data terbaru setelah update
     */
    Investment update(String id, Investment investment);

    /**
     * @Service
     * delete untuk menghapus data berdasarkan ID
     * @param id for key
     */
    void delete(String id);
}
