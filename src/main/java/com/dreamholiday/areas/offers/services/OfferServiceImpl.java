package com.dreamholiday.areas.offers.services;

import com.dreamholiday.areas.offers.entities.Offer;
import com.dreamholiday.areas.offers.exceptions.OfferNotFoundException;
import com.dreamholiday.areas.offers.models.bindingModels.AddOfferBindingModel;
import com.dreamholiday.areas.offers.models.viewModels.OfferViewModel;
import com.dreamholiday.areas.offers.repositories.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private static final int FULL_PERCENTAGE_BASE = 100;

    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OfferViewModel findOneById(Long id) {
        Offer offer = this.offerRepository.findOneById(id);
        if (offer == null) {
            throw new OfferNotFoundException();
        }

        OfferViewModel offerViewModel = this.modelMapper.map(offer, OfferViewModel.class);
        return offerViewModel;
    }

    @Override
    public List<OfferViewModel> findAll() {
        List<Offer> offerList = this.offerRepository.findAll();
        List<OfferViewModel> offerViewModelList = new ArrayList<OfferViewModel>();
        OfferViewModel offerViewModel;
        for (Offer offer : offerList) {
            offerViewModel = this.modelMapper.map(offer, OfferViewModel.class);
            offerViewModelList.add(offerViewModel);
        }

        return offerViewModelList;
    }

    @Override
    public void persist(AddOfferBindingModel addOfferBindingModel) {
        Offer offer = this.modelMapper.map(addOfferBindingModel, Offer.class);
        Double regularPrice = offer.getRegularPrice();
        Double discount = offer.getDiscount();
        Double finalPrice;
        if (discount == null || discount == 0) {
            offer.setDiscount(0.00);
            finalPrice = regularPrice;
        } else {
            finalPrice = regularPrice * (OfferServiceImpl.FULL_PERCENTAGE_BASE - discount) / OfferServiceImpl.FULL_PERCENTAGE_BASE;
        }
        offer.setFinalPrice(finalPrice);
        offer.setBoughtVouchersCount(0);
        String notSplittedUrls = addOfferBindingModel.getPictures();
        if (notSplittedUrls != null) {
            List<String> picturesUrls = this.getPicturesFromUrls(notSplittedUrls, offer);
            offer.setPictures(picturesUrls);
        }

        this.offerRepository.save(offer);
    }

    @Override
    public void updateOffer(long id, AddOfferBindingModel addOfferBindingModel) {
        Offer offer = this.offerRepository.findOneById(id);
//        toDoItem.setName(toDoItemBindingModel.getName());
//        toDoItem.setDeadline(toDoItemBindingModel.getDeadline());
//        toDoItem.setEnabled(toDoItemBindingModel.isEnabled());
    }

    @Override
    public void deleteById(long id) {
        this.offerRepository.delete(id);
    }

    @Override
    public List<OfferViewModel> findTop5ByOrderByFinalPriceAsc() {
        List<Offer> topFiveOffers = this.offerRepository.findTop5ByOrderByFinalPriceAsc();
        List<OfferViewModel> topFiveOfferViewModelList = new ArrayList<OfferViewModel>();
        OfferViewModel offerViewModel;
        for (Offer topFiveOffer : topFiveOffers) {
            offerViewModel = this.modelMapper.map(topFiveOffer, OfferViewModel.class);
            topFiveOfferViewModelList.add(offerViewModel);
        }

        return topFiveOfferViewModelList;
    }

    @Override
    public List<OfferViewModel> findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(Double finalPrice, Integer boughtVouchersCount) {
        List<Offer> recommendedOffers = this.offerRepository.findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(finalPrice, boughtVouchersCount);
        List<OfferViewModel> recommendedOffersViewModelList = new ArrayList<OfferViewModel>();
        OfferViewModel offerViewModel;
        for (Offer recommendedOffer : recommendedOffers) {
            offerViewModel = this.modelMapper.map(recommendedOffer, OfferViewModel.class);
            recommendedOffersViewModelList.add(offerViewModel);
        }

        return recommendedOffersViewModelList;
    }

    @Override
    public List<OfferViewModel> findByDestinationLike(String destination) {
        switch (destination) {
            case "bulgaria":
                destination = "България";
                break;
            case "greece":
                destination = "Гърция";
                break;
            case "turkey":
                destination = "Турция";
                break;
           }
        List<Offer> offersByDestination = this.offerRepository.findByDestinationLike(destination);

        List<OfferViewModel> offersByDestinationViewModelList = new ArrayList<OfferViewModel>();
        OfferViewModel offerViewModel;
        for (Offer offerbyDestination : offersByDestination) {
            offerViewModel = this.modelMapper.map(offerbyDestination, OfferViewModel.class);
            offersByDestinationViewModelList.add(offerViewModel);
        }

        return offersByDestinationViewModelList;
    }

    @Override
    public List<OfferViewModel> findAllByDestination(String searchWord) {
        List<Offer> offersByDestination = this.offerRepository.findAllByDestination(searchWord);

        List<OfferViewModel> offersByDestinationViewModelList = new ArrayList<OfferViewModel>();
        OfferViewModel offerViewModel;
        for (Offer offerbyDestination : offersByDestination) {
            offerViewModel = this.modelMapper.map(offerbyDestination, OfferViewModel.class);
            offersByDestinationViewModelList.add(offerViewModel);
        }

        return offersByDestinationViewModelList;
    }

    private List<String> getPicturesFromUrls(String nonSplittedUrls, Offer offer) {
        List<String> picturesUrls = new ArrayList<String>();
        String[] urls = nonSplittedUrls.split(";");
        for (String url : urls) {
            picturesUrls.add(url);
        }

        return picturesUrls;
    }
}
