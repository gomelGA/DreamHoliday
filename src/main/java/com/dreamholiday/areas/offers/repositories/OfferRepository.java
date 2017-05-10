package com.dreamholiday.areas.offers.repositories;

import com.dreamholiday.areas.offers.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findOneById(Long id);

    List<Offer> findAll();

    List<Offer> findTop5ByOrderByFinalPriceAsc();

    List<Offer>findByFinalPriceGreaterThanAndBoughtVouchersCountGreaterThan(Double finalPrice, Integer boughtVouchersCount);

    List<Offer>findByDestinationLike(String destination);

    @Query(value = "SELECT o FROM Offer AS o " +
            "WHERE o.destination LIKE CONCAT('%', :searchWord, '%')")
    List<Offer> findAllByDestination(@Param("searchWord") String searchWord);
}
