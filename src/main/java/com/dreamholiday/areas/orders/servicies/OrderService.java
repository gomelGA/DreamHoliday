package com.dreamholiday.areas.orders.servicies;

import com.dreamholiday.areas.orders.models.bindingModels.AddOrderBindingModels;
import com.dreamholiday.areas.orders.models.viewModels.OrderViewModel;

import java.util.List;

public interface OrderService {

    void persist(AddOrderBindingModels addOrderBindingModels, Long offerId, Long userId);

    List<OrderViewModel> findAll();

    OrderViewModel findOneById(Long id);

    List<OrderViewModel> findByUserId(Long id);
 }
