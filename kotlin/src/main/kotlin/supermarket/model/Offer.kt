package supermarket.model

sealed interface Offer {
    fun isOfferApplicable(quantity: Int): Boolean
    fun getDiscount(quantity: Int, unitPrice: Double, product: Product): Discount
}

class ThreeForTwo : Offer {
    override fun isOfferApplicable(quantity: Int): Boolean {
        return quantity > 2
    }

    override fun getDiscount(quantity: Int, unitPrice: Double, product: Product): Discount {
        val discountAmount = (quantity - (quantity / 3.0 * 2.0 + quantity % 3)) * unitPrice
        return Discount(product, "3 for 2", discountAmount)
    }
}

class PercentDiscount(val percent: Int) : Offer {
    override fun isOfferApplicable(quantity: Int): Boolean = true
    override fun getDiscount(quantity: Int, unitPrice: Double, product: Product): Discount {
        val discountAmount = quantity * unitPrice * percent / 100.0
        return Discount(product, "$percent % off", discountAmount)
    }
}

class QuantityForAmount(val offerQuantity: Int, val amount: Double) : Offer {
    override fun isOfferApplicable(quantity: Int): Boolean {
        return quantity >= offerQuantity
    }

    override fun getDiscount(quantity: Int, unitPrice: Double, product: Product): Discount {
        val total = amount * (quantity / offerQuantity) + quantity % offerQuantity * unitPrice
        val discountAmount = unitPrice * quantity - total
        return Discount(product, "$offerQuantity for $amount", discountAmount)
    }
}

