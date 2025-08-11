package supermarket.model

class ShoppingCart {

    private val cartItems: MutableMap<Product, Double> = HashMap()

    fun getItemsInCart(): Map<Product, Double> {
        return cartItems
    }

    fun addItemToCart(product: Product, quantity: Double) {
        if (cartItems.containsKey(product)) {
            cartItems[product] = cartItems[product]!! + quantity
        } else {
            cartItems[product] = quantity
        }
    }
}
