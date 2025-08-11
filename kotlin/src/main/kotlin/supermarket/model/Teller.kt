package supermarket.model

import java.util.HashMap


class Teller(private val catalog: SupermarketCatalog) {
    private val offers = HashMap<Product, Offer>()

    fun addSpecialOffer(product: Product, offer: Offer) {
        this.offers[product] = offer
    }


    fun checksOutArticlesFrom(theCart: ShoppingCart): Receipt {
        val receipt = Receipt()
        val itemsInCart = theCart.getItemsInCart()
        for ((product,quantity) in itemsInCart.entries) {
            val unitPrice = this.catalog.getUnitPriceFor(product)
            receipt.addReceiptItem(ReceiptItem(product, quantity, unitPrice))
            if (offers.containsKey(product)) {
                val productOffer = offers[product]!!
                if(productOffer.isOfferApplicable(quantity)) {
                    val discount = productOffer.getDiscount(quantity, unitPrice, product)
                    receipt.addDiscount(discount)
                }
            }
        }

        return receipt
    }

}
