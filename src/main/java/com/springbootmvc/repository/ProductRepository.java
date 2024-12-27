package com.springbootmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootmvc.entity.Productentity;
@Repository
public interface ProductRepository extends JpaRepository<Productentity, Long> {

}
