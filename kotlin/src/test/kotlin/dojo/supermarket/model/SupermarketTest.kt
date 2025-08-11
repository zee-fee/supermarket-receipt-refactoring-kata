package dojo.supermarket.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import supermarket.model.*

class SupermarketTest {

    @Test
    fun tenPercentDiscount() {
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProductToCatalog(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProductToCatalog(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemToCart(toothbrush, 1.0)
        cart.addItemToCart(apples, 1.5)

        val teller = Teller(catalog)
        teller.addSpecialOffer(apples, PercentDiscount(10))
        teller.addSpecialOffer(toothbrush, PercentDiscount(20))

        val receipt = teller.checksOutArticlesFrom(cart)

        assertEquals(receipt.getItems().size, 2)
        assertEquals(receipt.totalPrice!!, 3.47, 0.1)
    }

    @Test
    fun threeForTwoDiscount() {
        val catalog = FakeCatalog()
        val soap = Product("soap", ProductUnit.Each)
        catalog.addProductToCatalog(soap, 2.0)
        val cart = ShoppingCart()
        cart.addItemToCart(soap, 3.0)
        val teller = Teller(catalog)
        teller.addSpecialOffer(soap, ThreeForTwo())
        val receipt = teller.checksOutArticlesFrom(cart)
        // 3 soaps for price of 2
        assertEquals(receipt.totalPrice!!, 4.0, 0.1)
    }

    @Test
    fun twoForAmountDiscount() {
        val catalog = FakeCatalog()
        val chips = Product("chips", ProductUnit.Each)
        catalog.addProductToCatalog(chips, 1.5)
        val cart = ShoppingCart()
        cart.addItemToCart(chips, 2.0)
        val teller = Teller(catalog)
        teller.addSpecialOffer(chips, QuantityForAmount(2, 2.0))
        val receipt = teller.checksOutArticlesFrom(cart)
        // 2 chips for 2.0 instead of 3.0
        assertEquals(receipt.totalPrice!!, 2.0, 0.1)
    }

    @Test
    fun fiveForAmountDiscount() {
        val catalog = FakeCatalog()
        val soda = Product("soda", ProductUnit.Each)
        catalog.addProductToCatalog(soda, 1.0)
        val cart = ShoppingCart()
        cart.addItemToCart(soda, 5.0)
        val teller = Teller(catalog)
        teller.addSpecialOffer(soda, QuantityForAmount(5, 4.0))
        val receipt = teller.checksOutArticlesFrom(cart)
        // 5 sodas for 4.0 instead of 5.0
        assertEquals(receipt.totalPrice!!, 4.0, 0.1)
    }

}
