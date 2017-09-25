package com.pfunes.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pfunes on 18/09/17.
 */
@Repository
public interface CustomerDAO extends JpaRepository<Customer,Long> {
}
