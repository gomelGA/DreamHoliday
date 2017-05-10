package com.dreamholiday.controllers;

import com.dreamholiday.areas.offers.models.viewModels.OfferViewModel;
import com.dreamholiday.areas.offers.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final OfferService offerService;

    @Autowired
    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        List<OfferViewModel> offerViewModelList = this.offerService.findAll();
        model.addAttribute("offers", offerViewModelList);
        model.addAttribute("view", "/home");

//        return "search";
        return "base-layout";
    }
}
