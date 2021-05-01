package com.nab.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
