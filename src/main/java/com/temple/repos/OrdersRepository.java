package com.temple.repos;

import org.springframework.data.repository.CrudRepository;

import com.temple.Entities.UserOrder;

public interface OrdersRepository extends CrudRepository<UserOrder, Integer> {

}
