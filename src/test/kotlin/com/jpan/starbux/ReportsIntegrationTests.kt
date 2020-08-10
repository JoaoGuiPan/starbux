package com.jpan.starbux

import com.jpan.starbux.Mocks.joesCart
import com.jpan.starbux.Mocks.milk
import com.jpan.starbux.model.Order
import com.jpan.starbux.repository.OrderReports
import com.jpan.starbux.service.CartService
import com.jpan.starbux.service.CheckoutCommand
import com.jpan.starbux.service.ItemReportsService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest
class ReportsIntegrationTests {

    @Autowired
    private val cartService: CartService? = null

    @Autowired
    private val checkoutCommand: CheckoutCommand? = null

    @Autowired
    private val orderReports: OrderReports? = null

    @Autowired
    private val itemReportsService: ItemReportsService? = null

    private var order: Order? = null

    @BeforeAll
    fun setup() {
        val cart = cartService!!.create(joesCart)
        order = checkoutCommand!!.execute(cart)
    }

    @Test
    fun whenAdminRequestsOrderAmountPerCustomerReportThenFetchOrderAmountPerCustomer() {

        val customerOrders = orderReports!!.getOrdersPerCustomer()

        assertEquals(customerOrders[0].customer, joesCart.customer)
        assertEquals(customerOrders[0].totalOrdersValue, joesCart.netPrice.setScale(2))
    }

    @Test
    fun whenAdminRequestsMostOrderedToppingsReportThenFetchMostOrderedToppings() {

        val mostOrderedToppings = itemReportsService!!.getToppingsMostBoughtByCustomers()

        assertEquals(mostOrderedToppings[0].id, milk.id)
    }
}