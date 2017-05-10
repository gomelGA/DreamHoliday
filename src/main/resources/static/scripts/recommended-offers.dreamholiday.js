var recommendedOffers = $(".troBanner");
for (var i = 0; i < recommendedOffers.length; i++) {
    var target1 = Math.floor(Math.random() * recommendedOffers.length - 1) + 1;
    var target2 = Math.floor(Math.random() * recommendedOffers.length - 1) + 1;
    recommendedOffers.eq(target1).before(recommendedOffers.eq(target2));
}