package com.bestseller.starbux.view.controller

import com.bestseller.starbux.common.CreateService
import com.bestseller.starbux.common.DeleteRepository
import com.bestseller.starbux.common.UpdateService
import com.bestseller.starbux.model.Cart
import com.bestseller.starbux.model.Order
import com.bestseller.starbux.repository.CartRepository
import com.bestseller.starbux.service.CheckoutCommand
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("checkout")
data class CheckoutController(
        private val createCartService: CreateService<Cart>,
        private val updateCartService: UpdateService<Cart>,
        private val deleteCartRepository: DeleteRepository<Cart>,
        private val checkoutCommand: CheckoutCommand
) {
    @PostMapping("/carts")
    fun createCart(@RequestBody @Validated cart: Cart) = createCartService.create(cart)

    @PutMapping("/carts/{cart}")
    fun updateCart(@PathVariable cart: Cart, @RequestBody @Validated payload: Cart) =
            updateCartService.update(cart, payload)

    @GetMapping("/carts/{cart}")
    fun getById(@PathVariable cart: Cart) = cart

    @DeleteMapping("/cancel/{cart}")
    fun cancelOrder(@PathVariable cart: Cart) {
        deleteCartRepository.delete(cart)
    }

    @PostMapping("/{cart}")
    fun checkout(@PathVariable cart: Cart) = checkoutCommand.execute(cart)
}