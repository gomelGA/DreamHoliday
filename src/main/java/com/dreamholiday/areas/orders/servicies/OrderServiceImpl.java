package com.dreamholiday.areas.orders.servicies;

import com.dreamholiday.areas.offers.entities.Offer;
import com.dreamholiday.areas.orders.entities.Order;
import com.dreamholiday.areas.users.entities.OrdinaryUser;
import com.dreamholiday.areas.orders.enums.OrderStatus;
import com.dreamholiday.areas.orders.enums.PaymentMethod;
import com.dreamholiday.areas.orders.models.bindingModels.AddOrderBindingModels;
import com.dreamholiday.areas.orders.models.viewModels.OrderViewModel;
import com.dreamholiday.areas.offers.repositories.OfferRepository;
import com.dreamholiday.areas.orders.repositories.OrderRepository;
import com.dreamholiday.areas.users.repositories.OrdinaryUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    private final OfferRepository offerRepository;

    private final OrdinaryUserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, OfferRepository offerRepository, OrdinaryUserRepository ordinaryUserRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.userRepository = ordinaryUserRepository;
    }

    @Override
    public void persist(AddOrderBindingModels addOrderBindingModels, Long offerId, Long userId) {
        Offer offer = this.offerRepository.findOneById(offerId);
        OrdinaryUser user = this.userRepository.findOne(userId);
        Order order = this.modelMapper.map(addOrderBindingModels, Order.class);
        if (order != null) {
            order.setOffer(offer);
            order.setUser(user);
            order.setCreationDate(new Date());
            if (addOrderBindingModels.getPaymentMethod().equals("bank") || addOrderBindingModels.getPaymentMethod().equals("card")) {
                order.setPaid(true);
            } else {
                order.setPaid(false);
            }

            switch (addOrderBindingModels.getPaymentMethod()) {
                case "cash":
                    order.setOrderStatus(OrderStatus.IN_PROGRESS);
                    break;
                case "bank":
                    order.setOrderStatus(OrderStatus.CONFIRMED);
                    break;
                case "card":
                    order.setOrderStatus(OrderStatus.CONFIRMED);
                    ;
                    break;
            }

            switch (addOrderBindingModels.getPaymentMethod()) {
                case "cash":
                    order.setPaymentMethod(PaymentMethod.CASH);
                    break;
                case "bank":
                    order.setPaymentMethod(PaymentMethod.BANK);
                    break;
                case "card":
                    order.setPaymentMethod(PaymentMethod.CARD);
                    break;
            }

            order.setSingleVoucherPrice(offer.getFinalPrice());
            order.setTotalPrice(order.getVoucherCount() * order.getSingleVoucherPrice());
        }

        this.changeOfferBoughtVoucherCount(order, offer);

        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public List<OrderViewModel> findAll() {
        List<Order> orders = this.orderRepository.findAll();
        List<OrderViewModel> orderViewModelList = new ArrayList<OrderViewModel>();
        OrderViewModel orderViewModel;
        for (Order order : orders) {
            orderViewModel = this.modelMapper.map(order, OrderViewModel.class);
            orderViewModelList.add(orderViewModel);
        }

        return orderViewModelList;
    }

    @Override
    public OrderViewModel findOneById(Long id) {
        Order order = this.orderRepository.findOneById(id);
        OrderViewModel offerViewModel = this.modelMapper.map(order, OrderViewModel.class);

        return offerViewModel;
    }

    @Override
    public List<OrderViewModel> findByUserId(Long id) {
        List<Order> orders = this.orderRepository.findByUserId(id);
        List<OrderViewModel> orderViewModelList = new ArrayList<OrderViewModel>();
        OrderViewModel orderViewModel;
        for (Order order : orders) {
            orderViewModel = this.modelMapper.map(order, OrderViewModel.class);
            orderViewModelList.add(orderViewModel);
        }

        return orderViewModelList;
    }

    private void changeOfferBoughtVoucherCount(Order order, Offer offer) {
        int currentBoughtVouchers = offer.getBoughtVouchersCount();
        int orderVoucherCount = order.getVoucherCount();
        offer.setBoughtVouchersCount(currentBoughtVouchers + orderVoucherCount);
    }
}
