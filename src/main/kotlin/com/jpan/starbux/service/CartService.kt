package com.jpan.starbux.service

import com.jpan.starbux.common.*
import com.jpan.starbux.common.CONSTANTS.DEFAULT_CURRENCY
import com.jpan.starbux.model.Cart
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
data class CartService(
        private val discountCommand: DiscountCommand,
        private val cartCreate: CreateRepository<Cart>,
        private val cartUpdate: UpdateRepository<Cart>,
        private val cartDelete: DeleteRepository<Cart>
): CreateService<Cart>, UpdateService<Cart, Cart> {

    @Value("\${currency:EUR}")
    private val currency: String = DEFAULT_CURRENCY

    override fun create(entity: Cart): Cart {
        entity.calculateTotalPrice()
        discountCommand.applyDiscounts(entity)
        return cartCreate.create(entity.copy(
                currency = currency
        ))
    }

    override fun update(entity: Cart, payload: Cart): Cart {
        val updated = entity.copy(
                customer = payload.customer,
                items = payload.items
        )
        updated.calculateTotalPrice()
        discountCommand.applyDiscounts(updated)
        return cartUpdate.update(updated)
    }
}