package supermarket.model

interface SupermarketCatalog {
    fun addProductToCatalog(product: Product, price: Double)

    fun getUnitPriceFor(product: Product): Double
}
