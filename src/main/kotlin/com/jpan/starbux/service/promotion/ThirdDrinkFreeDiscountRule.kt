package com.jpan.starbux.service.promotion

import com.jpan.starbux.model.Cart
import com.jpan.starbux.model.Item
import org.jeasy.rules.annotation.Action
import org.jeasy.rules.annotation.Condition
import org.jeasy.rules.annotation.Fact
import org.jeasy.rules.annotation.Rule

@Rule(name = "com.jpan.starbux.service.promotion.ThirdDrinkFreeDiscountRule", description = "If Customer buys 3 or more DRINKS, the cheapest one is free")
class ThirdDrinkFreeDiscountRule {

    @Condition
    fun containsEnoughDrinks(@Fact("cart") cart: Cart): Boolean {
        val drinks = ArrayList<Item>()
        cart.items.forEach { orderItem ->
            drinks.addAll(orderItem.items.filter { it.category == Item.ItemCategory.DRINK })
        }
        return drinks.size >= 3
    }

    @Action
    fun then(@Fact("cart")cart: Cart) {
        val drinks = ArrayList<Item>()
        cart.items.forEach { orderItem ->
            drinks.addAll(orderItem.items.filter { it.category == Item.ItemCategory.DRINK })
        }
        drinks.sortBy { it.price }
        cart.setDiscount(drinks[0].price)
    }
}