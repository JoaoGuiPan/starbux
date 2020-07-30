package com.bestseller.starbux.config

import com.bestseller.starbux.common.CreateRepository
import com.bestseller.starbux.model.Item
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import javax.annotation.PostConstruct

@Configuration
data class InitializeDataConfig(
        private val createItem: CreateRepository<Item>
) {

    @PostConstruct
    fun init() {

        //DRINKS
        val blackCoffee = Item(
                description = "Black Coffee",
                price = BigDecimal(4)
        )
        createItem.create(blackCoffee)

        val latte = Item(
                description = "Latte",
                price = BigDecimal(5)
        )
        createItem.create(latte)

        val mocha = Item(
                description = "Mocha",
                price = BigDecimal(6)
        )
        createItem.create(mocha)

        val tea = Item(
                description = "Tea",
                price = BigDecimal(3)
        )
        createItem.create(tea)

        // TOPPINGS
        val milk = Item(
                description = "Milk",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(2)
        )
        createItem.create(milk)

        val hazelnutSyrup = Item(
                description = "Hazelnut syrup",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(3)
        )
        createItem.create(hazelnutSyrup)

        val chocolateSauce = Item(
                description = "Chocolate sauce",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(5)
        )
        createItem.create(chocolateSauce)

        val lemon = Item(
                description = "Lemon",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(2)
        )
        createItem.create(lemon)
    }
}