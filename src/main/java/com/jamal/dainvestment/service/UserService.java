package com.jamal.dainvestment.service;
import com.jamal.dainvestment.model.User;
import java.util.List;

/**
 * This is a Javadoc comment
 * User Service
 */
public interface UserService {
    /**
     * This is a Javadoc comment
     * findAll untuk menampilkan semua data
     * @return all data
     */
    List<User> findAll();

    /**
     * This is a Javadoc comment
     * menampilkan data berdasarkan ID
     * @param id for key
     * @return data by id
     */
    User findById(Integer id);

    /**
     * This is a Javadoc comment
     * mengubah data, butuh parameter ID
     * @param id for key
     * @param user for data
     * @return data updated
     */
    User update (Integer id, User user);

    /**
     * This is a Javadoc comment
     * menambah data baru
     * @param user for data
     * @return data created
     */
    User create(User user);

    /**
     * This is a Javadoc comment
     * menghapus data berdasarkan ID
     * @param id for key
     */
    void delete(Integer id);
}
