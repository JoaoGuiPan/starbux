package com.jpan.starbux.config

import com.jpan.starbux.common.CONSTANTS.DEFAULT_CURRENCY
import com.jpan.starbux.common.CreateRepository
import com.jpan.starbux.model.Item
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import javax.annotation.PostConstruct

@Configuration
data class InitializeDataConfig(
        private val createItem: CreateRepository<Item>
) {

    @Value("\${currency:EUR}")
    private val currency: String = DEFAULT_CURRENCY

    @PostConstruct
    fun init() {
        initDefaultItems()
    }

    private fun initDefaultItems() {

        //DRINKS
        val blackCoffee = Item(
                description = "Black Coffee",
                price = BigDecimal(4),
                currency = currency
        )
        createItem.create(blackCoffee)

        val latte = Item(
                description = "Latte",
                price = BigDecimal(5),
                currency = currency
        )
        createItem.create(latte)

        val mocha = Item(
                description = "Mocha",
                price = BigDecimal(6),
                currency = currency
        )
        createItem.create(mocha)

        val tea = Item(
                description = "Tea",
                price = BigDecimal(3),
                currency = currency
        )
        createItem.create(tea)


        // TOPPINGS
        val milk = Item(
                description = "Milk",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(2),
                currency = currency
        )
        createItem.create(milk)

        val hazelnutSyrup = Item(
                description = "Hazelnut syrup",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(3),
                currency = currency
        )
        createItem.create(hazelnutSyrup)

        val chocolateSauce = Item(
                description = "Chocolate sauce",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(5),
                currency = currency
        )
        createItem.create(chocolateSauce)

        val lemon = Item(
                description = "Lemon",
                category = Item.ItemCategory.TOPPING_OR_SIDE,
                price = BigDecimal(2),
                currency = currency
        )
        createItem.create(lemon)
    }
}