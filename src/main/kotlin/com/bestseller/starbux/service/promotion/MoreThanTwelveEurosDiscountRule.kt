package com.bestseller.starbux.service.promotion

import com.bestseller.starbux.model.Cart
import org.jeasy.rules.annotation.Action
import org.jeasy.rules.annotation.Condition
import org.jeasy.rules.annotation.Fact
import org.jeasy.rules.annotation.Rule
import java.math.BigDecimal

@Rule(name = "com.bestseller.starbux.service.promotion.MoreThanTwelveEurosDiscountRule", description = "If Customer spends 12 euros or more, apply 25% discount")
class MoreThanTwelveEurosDiscountRule {

    @Condition
    fun moreThanTwelveEuros(@Fact("cart") cart: Cart): Boolean {
        return cart.totalPrice >= BigDecimal(12)
    }

    @Action
    fun then(@Fact("cart")cart: Cart) {
        cart.setDiscount(cart.totalPrice.times(BigDecimal(0.25)))
    }
}