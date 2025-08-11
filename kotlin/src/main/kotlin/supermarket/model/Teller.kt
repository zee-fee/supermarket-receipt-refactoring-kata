package supermarket.model

import java.util.HashMap


class Teller(private val catalog: SupermarketCatalog) {
    private val offers = HashMap<Product, Offer>()

    fun addSpecialOffer(product: Product, offer: Offer) {
        this.offers[product] = offer
    }


    fun checksOutArticlesFrom(theCart: ShoppingCart): Receipt {
        val receipt = Receipt()
        val productQuantities = theCart.getItems()
        for ((product,quantity) in productQuantities.entries) {
            val unitPrice = this.catalog.getUnitPrice(product)
            receipt.addReceiptItem(ReceiptItem(product, quantity, unitPrice))
        }
        theCart.handleOffers(receipt, this.offers, this.catalog)

        return receipt
    }

}
