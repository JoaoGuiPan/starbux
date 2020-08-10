package com.jpan.starbux.validation

import com.jpan.starbux.Mocks
import com.jpan.starbux.model.OrderItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HasOnlyOneDrinkValidatorTest {

    @Test
    fun `When Order Item Has More Than One Drink Then Validator Is False`() {
        val orderItem = OrderItem(
                items = listOf(
                        Mocks.blackCoffee,
                        Mocks.blackCoffee
                ).toHashSet()
        )

        Assertions.assertTrue(orderItem.items.hasOnlyOneDrink())
    }
}