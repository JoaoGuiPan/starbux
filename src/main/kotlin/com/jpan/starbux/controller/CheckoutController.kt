package com.jpan.starbux.controller

import com.jpan.starbux.common.CONSTANTS.HAS_ANY_ROLE
import com.jpan.starbux.common.CreateService
import com.jpan.starbux.common.DeleteRepository
import com.jpan.starbux.common.UpdateService
import com.jpan.starbux.model.Cart
import com.jpan.starbux.model.Order
import com.jpan.starbux.service.CheckoutCommand
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Api(value = "Checkout", description = "REST endpoints responsible for the checkout flow")
@RestController
@RequestMapping("checkout")
data class CheckoutController(
        private val createCartService: CreateService<Cart>,
        private val updateCartService: UpdateService<Cart>,
        private val deleteCartRepository: DeleteRepository<Cart>,
        private val checkoutCommand: CheckoutCommand
) {

    val logger: Logger = LoggerFactory.getLogger(CheckoutController::class.java)

    @ApiOperation(value = "Create cart. User role: USER or ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @PostMapping("/carts")
    @PreAuthorize(HAS_ANY_ROLE)
    fun createCart(@RequestBody @Validated cart: Cart): Cart {
        logger.debug("Creating new Cart - ", cart)
        val created = createCartService.create(cart)
        logger.info("Cart created. ID " + created.id)
        return created
    }

    @ApiOperation(value = "Update cart. User role: USER or ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @PutMapping("/carts/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun updateCart(@PathVariable cart: Cart, @RequestBody @Validated payload: Cart): Cart {
        logger.debug("Updating Cart ID ", cart.id)
        val updated = updateCartService.update(cart, payload)
        logger.info("Cart updated - $cart")
        return updated
    }

    @ApiOperation(value = "Fetch cart by id. User role: USER or ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping("/carts/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun getById(@PathVariable cart: Cart): Cart {
        logger.info("Fetching Cart ID " + cart.id)
        return cart
    }

    @ApiOperation(value = "Cancel order (delete cart). User role: USER or ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @DeleteMapping("/cancel/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun cancelOrder(@PathVariable cart: Cart) {
        logger.debug("Deleting Cart ID " + cart.id)
        deleteCartRepository.delete(cart)
        logger.info("Cart deleted, ID " + cart.id)
    }

    @ApiOperation(value = "Checkout order (delete cart and create order). User role: USER or ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @PostMapping("/{cart}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun checkout(@PathVariable cart: Cart): Order {
        logger.debug("Checking out Cart ID " + cart.id)
        val order = checkoutCommand.execute(cart)
        logger.info("Checkout completed, Order - $order")
        return order
    }
}