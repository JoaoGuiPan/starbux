package com.bestseller.starbux.service

import com.bestseller.starbux.common.*
import com.bestseller.starbux.model.Cart
import org.springframework.stereotype.Service

@Service
data class CartService(
        private val discountCommand: DiscountCommand,
        private val cartCreate: CreateRepository<Cart>,
        private val cartUpdate: UpdateRepository<Cart>,
        private val cartDelete: DeleteRepository<Cart>
): CreateService<Cart>, UpdateService<Cart> {

    override fun create(entity: Cart): Cart {
        entity.calculateTotalPrice()
        discountCommand.applyDiscounts(entity)
        cartCreate.create(entity)
        return entity
    }

    override fun update(entity: Cart, payload: Cart): Cart {
        val updated = entity.copy(
                items = payload.items
        )
        updated.calculateTotalPrice()
        discountCommand.applyDiscounts(updated)
        return cartUpdate.update(updated)
    }
}