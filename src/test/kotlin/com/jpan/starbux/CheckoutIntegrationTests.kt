package com.jpan.starbux

import com.jpan.starbux.Mocks.joesCart
import com.jpan.starbux.repository.CartRepository
import com.jpan.starbux.repository.OrderRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertNotNull

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class CheckoutIntegrationTests {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    private val objectMapper: ObjectMapper? = null

    @Autowired
    private val cartRepository: CartRepository? = null

    @Autowired
    private val orderRepository: OrderRepository? = null

	@Test
	fun whenCustomerOrderItemsThenCheckout() {

        mockMvc!!.perform(
                post("/checkout/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                .content(objectMapper!!.writeValueAsString(joesCart))
        ).andExpect(status().isOk)

        assertNotNull(cartRepository!!.findById(1))

        mockMvc.perform(post("/checkout/{cart}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                .content(objectMapper.writeValueAsString(joesCart)))
                .andExpect(status().isOk)

        assertNotNull(orderRepository!!.findById(1))
	}
}
