package com.jpan.starbux.service

import com.jpan.starbux.common.CreateRepository
import com.jpan.starbux.common.DeleteRepository
import com.jpan.starbux.model.Cart
import com.jpan.starbux.model.Order
import org.springframework.stereotype.Service

@Service
data class CheckoutCommand(
        private val createOrder: CreateRepository<Order>,
        private val deleteCart: DeleteRepository<Cart>
) {

    fun execute(cart: Cart): Order {
        val order = createOrder.create(
            Order(
                id = cart.id!!,
                // generates new order item ids for the order record
                items = cart.items.map { it.copy(id = null) }.toHashSet(),
                totalPrice = cart.totalPrice,
                totalDiscount = cart.totalDiscount,
                netPrice = cart.netPrice,
                customer = cart.customer
            )
        )

        deleteCart.delete(cart)

        return order
    }
}