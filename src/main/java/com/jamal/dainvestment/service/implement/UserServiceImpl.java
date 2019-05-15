package com.jamal.dainvestment.service.implement;

import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.repository.UserRepository;
import com.jamal.dainvestment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a Javadoc comment
 * implementasi dari com.jamal.dainvestment.service.UserService
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new DataNotFoundException("Data User Tidak Ditemukan");
        }
        return  optionalUser.get();
    }

    @Override
    public User update(Integer id, User user) {
        user.setUserId(id);
        return userRepository.save(user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
