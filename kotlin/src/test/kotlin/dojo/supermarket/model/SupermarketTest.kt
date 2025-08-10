package dojo.supermarket.model

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import supermarket.model.*

class SupermarketTest {

    @Test
    fun tenPercentDiscount() {
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(toothbrush, 1.0)
        cart.addItemQuantity(apples, 1.5)

        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0)

        val receipt = teller.checksOutArticlesFrom(cart)

        assertEquals(receipt.getItems().size, 2)
        assertEquals(receipt.totalPrice!!, 3.876,0.1)
    }

}
