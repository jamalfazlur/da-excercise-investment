package com.jamal.dainvestment.repository;

import com.jamal.dainvestment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a Javadoc comment
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {}
