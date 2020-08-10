package com.jpan.starbux

import com.jpan.starbux.common.CONSTANTS.DEFAULT_CURRENCY
import com.jpan.starbux.model.Cart
import com.jpan.starbux.model.Item
import com.jpan.starbux.model.OrderItem
import java.math.BigDecimal

object Mocks {

    //DRINKS
    val blackCoffee = Item(
            id = 1,
            description = "Black Coffee",
            price = BigDecimal(4),
            currency = DEFAULT_CURRENCY
    )

    val latte = Item(
            id = 2,
            description = "Latte",
            price = BigDecimal(5),
            currency = DEFAULT_CURRENCY
    )

    val mocha = Item(
            id = 3,
            description = "Mocha",
            price = BigDecimal(6),
            currency = DEFAULT_CURRENCY
    )

    val tea = Item(
            id = 4,
            description = "Tea",
            price = BigDecimal(3),
            currency = DEFAULT_CURRENCY
    )


    // TOPPINGS
    val milk = Item(
            id = 5,
            description = "Milk",
            category = Item.ItemCategory.TOPPING_OR_SIDE,
            price = BigDecimal(2),
            currency = DEFAULT_CURRENCY
    )

    val hazelnutSyrup = Item(
            id = 6,
            description = "Hazelnut syrup",
            category = Item.ItemCategory.TOPPING_OR_SIDE,
            price = BigDecimal(3),
            currency = DEFAULT_CURRENCY
    )

    val chocolateSauce = Item(
            id = 7,
            description = "Chocolate sauce",
            category = Item.ItemCategory.TOPPING_OR_SIDE,
            price = BigDecimal(5),
            currency = DEFAULT_CURRENCY
    )

    val lemon = Item(
            id = 8,
            description = "Lemon",
            category = Item.ItemCategory.TOPPING_OR_SIDE,
            price = BigDecimal(2),
            currency = DEFAULT_CURRENCY
    )

    // CART
    val joesCart = Cart(
            id = 1,
            customer = "Joe",
            items = listOf(
                    OrderItem(
                            items = listOf(
                                    blackCoffee,
                                    milk
                            ).toHashSet()
                    )
            ).toHashSet()
    )
}