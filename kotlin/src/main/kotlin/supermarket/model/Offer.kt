package supermarket.model

sealed interface Offer {
    fun isOfferApplicable(quantity: Double): Boolean
    fun getDiscount(quantity: Double, unitPrice: Double, product: Product): Discount
}

class ThreeForTwo : Offer {
    override fun isOfferApplicable(quantity: Double): Boolean {
        return quantity.toInt() > 2
    }

    override fun getDiscount(quantity: Double, unitPrice: Double, product: Product): Discount {
        val quantityAsInt = quantity.toInt()
        val discountAmount = (quantityAsInt - (quantityAsInt / 3.0 * 2.0 + quantityAsInt % 3)) * unitPrice
        return Discount(product, "3 for 2", discountAmount)
    }
}

class PercentDiscount(val percent: Int) : Offer {
    override fun isOfferApplicable(quantity: Double): Boolean = true
    override fun getDiscount(quantity: Double, unitPrice: Double, product: Product): Discount {
        val discountAmount = quantity * unitPrice * percent / 100.0
        return Discount(product, "$percent % off", discountAmount)
    }
}

class QuantityForAmount(val offerQuantity: Int, val amount: Double) : Offer {
    override fun isOfferApplicable(quantity: Double): Boolean {
        return quantity.toInt() >= offerQuantity
    }

    override fun getDiscount(quantity: Double, unitPrice: Double, product: Product): Discount {
        val quantityAsInt = quantity.toInt()
        val total = amount * (quantityAsInt / offerQuantity) + quantityAsInt % offerQuantity * unitPrice
        val discountAmount = unitPrice * quantityAsInt - total
        return Discount(product, "$offerQuantity for $amount", discountAmount)
    }
}

