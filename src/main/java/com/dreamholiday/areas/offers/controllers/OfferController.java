package com.dreamholiday.areas.offers.controllers;

import com.dreamholiday.areas.offers.exceptions.OfferNotFoundException;
import com.dreamholiday.areas.offers.models.bindingModels.AddOfferBindingModel;
import com.dreamholiday.areas.offers.models.viewModels.OfferViewModel;
import com.dreamholiday.areas.offers.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("details/offerId={id}")
    public String getOfferDetails(Model model, @PathVariable Long id) {
        OfferViewModel offerViewModel = this.offerService.findOneById(id);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("offerViewModel", offerViewModel);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/offers/offer-details");

        return "base-layout";
    }

    @GetMapping("{destination}")
    public String getOffersBulgaria(Model model, @PathVariable("destination") String destination) {
        List<OfferViewModel> offersByDestinationViewModelList = this.offerService.findByDestinationLike(destination);
        model.addAttribute("offers", offersByDestinationViewModelList);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/home");

        return "base-layout";
    }

    @GetMapping("all/administrator")
    public String getAllOffersPage(Model model) {
        List<OfferViewModel> offerViewModelList = this.offerService.findAll();
        model.addAttribute("offers", offerViewModelList);
        model.addAttribute("view", "/offers/offers-table");

        return "base-layout";
    }

    @GetMapping("profitableOffers")
    public String getTopOffersPage(Model model) {
        List<OfferViewModel> topFiveOffersViewModelList = this.offerService.findTop5ByOrderByFinalPriceAsc();
        model.addAttribute("offers", topFiveOffersViewModelList);
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/home");

        return "base-layout";
    }

    @GetMapping("add")
    public String getAddOfferPage(Model model) {
        if (!model.containsAttribute("addOfferBindingModel")) {
            model.addAttribute("addOfferBindingModel", new AddOfferBindingModel());
        }
        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(250.00d, 5);
        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
        model.addAttribute("view", "/offers/add-offer");

        return "base-layout";
    }

    @PostMapping("add")
    public String AddOffer(@Valid @ModelAttribute AddOfferBindingModel addOfferBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferBindingModel", addOfferBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferBindingModel", bindingResult);

            return "redirect:/offers/add";
        }

        this.offerService.persist(addOfferBindingModel);

        return "redirect:/offers/administrator";
    }

    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String catchOfferNotFoundException() {
        return "exceptions/offer-not-found";
    }

//    @PutMapping("/edit/offerId={id}")
//    public ResponseEntity update(@PathVariable long id, @RequestBody AddOfferBindingModel addOfferBindingModel) {
//        this.offerService.updateOffer(id, addOfferBindingModel);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/offerId={id}")
//    public ResponseEntity delete(@PathVariable long id) {
//        this.offerService.deleteById(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<OfferViewModel>> getAllOffer() {
//
//        List<OfferViewModel> offerViewModelList = this.offerService.findAll();
//
//        if(offerViewModelList == null){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//
//        ResponseEntity<List<OfferViewModel>> responseEntity
//                = new ResponseEntity(offerViewModelList, HttpStatus.OK);
//
//        return responseEntity;
//    }

//    @GetMapping("/search")
//    public ResponseEntity<List<OfferViewModel>> search(@RequestParam String searchWord) {
//        List<OfferViewModel> toDoItemViewModels = this.offerService.findAllByDestination(searchWord);
//        return new ResponseEntity(toDoItemViewModels, HttpStatus.OK);
//    }

    //    public String search(Model model, @RequestParam String searchWord) {
//        List<OfferViewModel> offers = this.offerService.findByDestinationLike(searchWord);
//        model.addAttribute("offers", offers);
//        List<OfferViewModel> recommendedOffersViewModelList = this.offerService.findByFinalPriceGreaterThanAndBoughtVouchersGreaterThan(250.00d, 5);
//        model.addAttribute("recommendedOffers", recommendedOffersViewModelList);
//        model.addAttribute("view", "/home");

//        return "search";
}
