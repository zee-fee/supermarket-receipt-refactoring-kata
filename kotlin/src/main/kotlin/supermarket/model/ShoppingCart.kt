package supermarket.model

class ShoppingCart {

    private val productQuantities: MutableMap<Product, Double> = HashMap()

    fun getItems(): Map<Product, Double> {
        return productQuantities
    }

    fun addItemQuantity(product: Product, quantity: Double) {
        if (productQuantities.containsKey(product)) {
            productQuantities[product] = productQuantities[product]!! + quantity
        } else {
            productQuantities[product] = quantity
        }
    }

    internal fun handleOffers(receipt: Receipt, offers: Map<Product, Offer>, catalog: SupermarketCatalog) {
        for (p in productQuantities.keys) {
            val quantity = productQuantities[p]!!
            if (offers.containsKey(p)) {
                val offer = offers[p]!!
                val unitPrice = catalog.getUnitPrice(p)
                val quantityAsInt = quantity.toInt()
                var discount: Discount? = null

                if(offer.isOfferApplicable(quantityAsInt)) {
                    discount = offer.getDiscount(quantityAsInt, unitPrice, p)
                }

                if (discount != null)
                    receipt.addDiscount(discount)
            }

        }
    }
}
