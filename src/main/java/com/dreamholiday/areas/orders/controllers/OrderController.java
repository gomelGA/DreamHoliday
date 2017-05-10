package com.dreamholiday.areas.orders.controllers;

import com.dreamholiday.areas.orders.models.bindingModels.AddOrderBindingModels;
import com.dreamholiday.areas.offers.models.viewModels.OfferViewModel;
import com.dreamholiday.areas.orders.models.viewModels.OrderViewModel;
import com.dreamholiday.areas.offers.services.OfferService;
import com.dreamholiday.areas.orders.servicies.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OfferService offerService;

    private final OrderService orderService;

    @Autowired
    public OrderController(OfferService offerService, OrderService orderService) {
        this.offerService = offerService;
        this.orderService = orderService;
    }

    @GetMapping("details/offerId={offerId}&userId={userId}")
    public String getOrderPage(Model model, @PathVariable Long offerId) {
        if (!model.containsAttribute("addOrderBindingModels")) {
            model.addAttribute("addOrderBindingModels", new AddOrderBindingModels());
        }

        OfferViewModel offerViewModel = this.offerService.findOneById(offerId);
        model.addAttribute("offerViewModel", offerViewModel);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/orders/order-details");

        return "base-layout";
    }

    @PostMapping("details/offerId={offerId}?userId={userId}")
    public String AddOrder(Model model, @PathVariable Long offerId, @PathVariable Long userId, @Valid @ModelAttribute AddOrderBindingModels addOrderBindingModels) {

        this.orderService.persist(addOrderBindingModels, offerId, userId);

        return "redirect:/";
    }

    @GetMapping("details/orderId={orderId}")
    public String getOrderPreviewPage(Model model, @PathVariable Long orderId) {
        OrderViewModel orderViewModel = this.orderService.findOneById(orderId);
        model.addAttribute("orderViewModel", orderViewModel);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/orders/order-preview");

        return "base-layout";
    }
}
