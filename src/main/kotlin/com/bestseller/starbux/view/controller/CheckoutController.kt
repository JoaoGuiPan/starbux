package com.bestseller.starbux.view.controller

import com.bestseller.starbux.common.CONSTANTS.Companion.HAS_ANY_ROLE
import com.bestseller.starbux.common.CreateService
import com.bestseller.starbux.common.DeleteRepository
import com.bestseller.starbux.common.UpdateService
import com.bestseller.starbux.model.Cart
import com.bestseller.starbux.service.CheckoutCommand
import org.springframework.security.access.prepost.PreAuthorize
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
    @PreAuthorize(HAS_ANY_ROLE)
    fun createCart(@RequestBody @Validated cart: Cart) = createCartService.create(cart)

    @PutMapping("/carts/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun updateCart(@PathVariable cart: Cart, @RequestBody @Validated payload: Cart) =
            updateCartService.update(cart, payload)

    @GetMapping("/carts/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun getById(@PathVariable cart: Cart) = cart

    @DeleteMapping("/cancel/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun cancelOrder(@PathVariable cart: Cart) {
        deleteCartRepository.delete(cart)
    }

    @PostMapping("/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun checkout(@PathVariable cart: Cart) = checkoutCommand.execute(cart)
}