package com.bestseller.starbux.service

import com.bestseller.starbux.model.Cart
import org.springframework.stereotype.Service

@Service
class DiscountCommand {

    fun applyDiscounts(cart: Cart) {
        // TODO
        cart.calculateNetPrice()
    }
}