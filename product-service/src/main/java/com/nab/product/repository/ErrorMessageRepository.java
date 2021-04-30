package com.nab.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.product.model.ErrorMessage;

@Repository
public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, Long>{

}
