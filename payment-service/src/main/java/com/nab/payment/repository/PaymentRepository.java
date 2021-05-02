package com.nab.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.payment.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
