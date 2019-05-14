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
     */
    List<User> findAll();

    /**
     * This is a Javadoc comment
     * menampilkan data berdasarkan ID
     */
    User findById(Integer id);

    /**
     * This is a Javadoc comment
     * mengubah data, butuh parameter ID
     */
    User update (Integer id, User user);

    /**
     * This is a Javadoc comment
     * menambah data baru
     */
    User create(User user);

    /**
     * This is a Javadoc comment
     * menghapus data berdasarkan ID
     */
    void delete(Integer id);
}
