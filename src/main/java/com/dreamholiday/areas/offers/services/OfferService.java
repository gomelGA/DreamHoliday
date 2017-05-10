package com.dreamholiday.areas.offers.services;

import com.dreamholiday.areas.offers.models.bindingModels.AddOfferBindingModel;
import com.dreamholiday.areas.offers.models.viewModels.OfferViewModel;

import java.util.List;

public interface OfferService {

    OfferViewModel findOneById(Long id);

    List<OfferViewModel> findAll();

    void persist(AddOfferBindingModel addOfferBindingModel);

    void updateOffer(long id, AddOfferBindingModel addOfferBindingModel);

    void deleteById(long id);

    List<OfferViewModel> findTop5ByOrderByFinalPriceAsc();

    List<OfferViewModel>findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(Double finalPrice, Integer boughtVouchersCount);

    List<OfferViewModel>findByDestinationLike(String destination);

    List<OfferViewModel> findAllByDestination(String searchWord);
}
