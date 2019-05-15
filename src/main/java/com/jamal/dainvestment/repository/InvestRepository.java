package com.jamal.dainvestment.repository;

import com.jamal.dainvestment.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a Repository for Invesment entity
 * information about investment type, price, tax, etc
 */
@Repository
public interface InvestRepository extends JpaRepository<Investment, String> {
}
