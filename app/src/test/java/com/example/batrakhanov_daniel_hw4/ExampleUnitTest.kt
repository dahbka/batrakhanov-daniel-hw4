package com.example.batrakhanov_daniel_hw4

import org.junit.Test

import org.junit.Assert.*
import kotlin.math.truncate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun example() {

        val iphoneCase = Product(price = 123.5, salePercent = 30)

        val pricePrinter: PricePrinter = CleanKotlinPricePrinter()

        val discountIphoneCasePrice = iphoneCase.calcDiscountPrice()
        pricePrinter.print(discountIphoneCasePrice)

        val newPricePrinter : PricePrinter= NewPricePrinter()
        val intInputCheck =3.0
        newPricePrinter.print(intInputCheck)
    }
}

class Product(
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0
) {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)
}

interface PricePrinter {

    /**
     * Outputs price in <PRICE>₽ format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)
}

class CleanKotlinPricePrinter : PricePrinter {
    override fun print(price: Double) {
        when {
            price % 1.0 >= 1e-8 -> println("Цена товара - ${"%.2f".format(price)} ₽")
            else -> println("Цена товара - ${price.toInt()} ₽")
        }
    }
}

class NewPricePrinter : PricePrinter {
    override fun print(price: Double) {
        when {
            price % 1.0 >= 1e-8 -> println("Цена товара - ${"%.2f".format(price)} ₽")
            else -> println("Цена товара - ${"%.0f".format(price)} ₽")
        }
    }
}


