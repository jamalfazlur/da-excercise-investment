package com.jamal.dainvestment.service;

import com.jamal.dainvestment.dto.UserDto;
import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a Javadoc comment
 * implementasi dari com.jamal.dainvestment.service untuk User
 */

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Investment findAll / GET
     * no param
     * @return users data
     */
    public List<User> findAll() { return userRepository.findAll(); }

    /**
     * Investment find By Id / GET
     * @param id id_invest
     * @return user data (filtered)
     */
    public User findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new DataNotFoundException("Data User Tidak Ditemukan");
        }
        return  optionalUser.get();
    }

    /**
     * Investment update / PUT
     * @param id user
     * @param user userDto obj
     * @return user data updated
     */
    public UserDto update(Integer id, UserDto user){
        User updateUser = new User();
        updateUser.setUserId(id);
        updateUser.setUserNama(user.getUserNama());
        updateUser.setUserAlamat(user.getUserAlamat());
        updateUser.setUserSaldo(user.getUserSaldo());

        userRepository.save(updateUser);
        user.setUserId(id); // set userId untuk response
        return user;
    }

    /**
     * Investment update / PUT
     * @param user user obj
     * @return user data created
     */
    public UserDto create(UserDto user){
        log.info("Nama: " + user.getUserNama() + " Alamat: " + user.getUserAlamat() + " Saldo: " + user.getUserSaldo());

        User newUser = new User(); // Instance Entity User

        newUser.setUserNama(user.getUserNama());
        newUser.setUserAlamat(user.getUserAlamat());
        newUser.setUserSaldo(user.getUserSaldo());

        int newUserId = userRepository.save(newUser).getUserId(); // get idUser dari hasil user yang baru dibuat

        user.setUserId(newUserId); // set idUser dari hasil user yang baru dibuat

        user.setUserId(newUser.getUserId());

        return user;

    }

    /**
     * Investment update / PUT
     * @param id userId
     */
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
