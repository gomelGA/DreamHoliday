package com.dreamholiday.areas.users.controllers;

import com.dreamholiday.areas.users.models.bindingModels.AddUserBindingModel;
import com.dreamholiday.areas.users.models.bindingModels.EditUserBindingModel;
import com.dreamholiday.errors.Errors;
import com.dreamholiday.areas.offers.models.viewModels.OfferViewModel;
import com.dreamholiday.areas.orders.models.viewModels.OrderViewModel;
import com.dreamholiday.areas.users.models.viewModels.EditUserViewModel;
import com.dreamholiday.areas.offers.services.OfferService;
import com.dreamholiday.areas.orders.servicies.OrderService;
import com.dreamholiday.areas.users.services.OrdinaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final OrdinaryUserService ordinaryUserService;

    private final OfferService offerService;

    private final OrderService orderService;

    @Autowired
    public UserController(OrdinaryUserService ordinaryUserService, OfferService offerService, OrderService orderService) {
        this.ordinaryUserService = ordinaryUserService;
        this.offerService = offerService;
        this.orderService = orderService;
    }

    @GetMapping("/user/profile")
    public String getUserProfilePage(Model model) {
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/user/user-profile");

        return "base-layout";
    }

    @GetMapping("/user/profileChange/userId={id}")
    public String getUserProfilePage(Model model, @PathVariable() long id) {
        if (!model.containsAttribute("editUserBindingModel")) {
            model.addAttribute("editUserBindingModel", new EditUserBindingModel());
        }

        EditUserViewModel editUserViewModel = this.ordinaryUserService.findOneToEdit(id);
        model.addAttribute("user", editUserViewModel);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/user/user-profile-change");

        return "base-layout";
    }

    @PostMapping("/user/profileChange/userId={id}")
    public String getUserProfileChange(Model model, @PathVariable long id, @Valid @ModelAttribute EditUserBindingModel editUserBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editUserBindingModel", editUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUserBindingModel", bindingResult);

            return "redirect:/user/profileChange/userId={id}";
        }

        this.ordinaryUserService.update(editUserBindingModel);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/user/user-profile-change");

        return "redirect:/logout";
    }

    @GetMapping("/user/{id}/orders")
    public String getUserOrders(Model model, @PathVariable long id) {
        List<OrderViewModel> orderViewModelList = this.orderService.findByUserId(id);
        model.addAttribute("orders", orderViewModelList);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "orders/orders");

        return "base-layout";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        if (!model.containsAttribute("addUserBindingModel")) {
            model.addAttribute("addUserBindingModel", new AddUserBindingModel());
        }

        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/user/register");

        return "base-layout";
    }

    @PostMapping("/register")
    public String getUserRegisterPage(@Valid @ModelAttribute AddUserBindingModel addUserBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addUserBindingModel", addUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addUserBindingModel", bindingResult);

            return "redirect:/register";
        }

        this.ordinaryUserService.register(addUserBindingModel);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/user/login");

        return "base-layout";
    }
}
