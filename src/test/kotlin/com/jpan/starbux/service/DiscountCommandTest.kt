package com.jpan.starbux.service

import com.jpan.starbux.Mocks
import com.jpan.starbux.common.CONSTANTS
import com.jpan.starbux.model.Cart
import com.jpan.starbux.model.OrderItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DiscountCommandTest {

    @Test
    fun `When Customer Buys More Than Twelve Euros Then Apply Discount`() {

        val command = DiscountCommand()

        val cart = Cart(
                currency = CONSTANTS.DEFAULT_CURRENCY,
                customer = "Joe",
                items = listOf(
                        OrderItem(
                                id = 1,
                                items = listOf(
                                        Mocks.mocha,
                                        Mocks.hazelnutSyrup,
                                        Mocks.chocolateSauce
                                ).toHashSet()
                        )
                ).toHashSet()
        )
        cart.calculateTotalPrice()

        command.applyDiscounts(cart)

        Assertions.assertEquals(BigDecimal(3.50).setScale(2), cart.totalDiscount)
    }

    @Test
    fun `When Customer Buys More Than Three Drinks Then Apply Discount`() {

        val command = DiscountCommand()

        val cart = Cart(
                currency = CONSTANTS.DEFAULT_CURRENCY,
                customer = "Joe",
                items = listOf(
                        OrderItem(
                                id = 1,
                                items = listOf(
                                        Mocks.tea
                                ).toHashSet()
                        ),
                        OrderItem(
                                id = 2,
                                items = listOf(
                                        Mocks.tea
                                ).toHashSet()
                        ),
                        OrderItem(
                                id = 3,
                                items = listOf(
                                        Mocks.tea
                                ).toHashSet()
                        )
                ).toHashSet()
        )
        cart.calculateTotalPrice()

        command.applyDiscounts(cart)

        Assertions.assertEquals(BigDecimal(3), cart.totalDiscount)
    }

    @Test
    fun `When Customer Buys More Than Three Drinks And Buys More Than Twelve Euros Then Apply Highest Discount`() {
        val command = DiscountCommand()

        val cart = Cart(
                currency = CONSTANTS.DEFAULT_CURRENCY,
                customer = "Joe",
                items = listOf(
                        OrderItem(
                                id = 1,
                                items = listOf(
                                        Mocks.blackCoffee,
                                        Mocks.milk
                                ).toHashSet()
                        ),
                        OrderItem(
                                id = 2,
                                items = listOf(
                                        Mocks.latte
                                ).toHashSet()
                        ),
                        OrderItem(
                                id = 3,
                                items = listOf(
                                        Mocks.tea,
                                        Mocks.lemon
                                ).toHashSet()
                        )
                ).toHashSet()
        )
        cart.calculateTotalPrice()

        command.applyDiscounts(cart)

        Assertions.assertEquals(BigDecimal(4.00).setScale(2), cart.totalDiscount)
    }

}