package supermarket.model

class ShoppingCart {

    private val productQuantities: MutableMap<Product, Double> = HashMap()

    fun getItemsInCart(): Map<Product, Double> {
        return productQuantities
    }

    fun addItemQuantity(product: Product, quantity: Double) {
        if (productQuantities.containsKey(product)) {
            productQuantities[product] = productQuantities[product]!! + quantity
        } else {
            productQuantities[product] = quantity
        }
    }
}
