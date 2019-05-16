package com.jamal.dainvestment.repository;

import com.jamal.dainvestment.model.Trx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a Repository for Transaction (trx) entity
 */
@Repository
public interface TrxRepository extends JpaRepository<Trx, Integer> {
}
