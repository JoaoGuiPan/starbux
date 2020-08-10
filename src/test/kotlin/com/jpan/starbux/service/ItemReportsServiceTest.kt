package com.jpan.starbux.service

import com.jpan.starbux.Mocks
import com.jpan.starbux.repository.ListToppings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ItemReportsServiceTest {

    @Mock
    var listToppings: ListToppings? = null

    @Test
    fun `When Fetching Most Used Toppings Then Order By Most Used Toppings`() {

        Mockito
                .`when`(listToppings!!.getAllToppings())
                .thenReturn(listOf(
                        Mocks.milk,
                        Mocks.hazelnutSyrup,
                        Mocks.chocolateSauce,
                        Mocks.lemon
                ))

        Mockito
                .`when`(listToppings!!.getAllToppingsBoughtByCustomers())
                .thenReturn(listOf(
                        Mocks.hazelnutSyrup,
                        Mocks.chocolateSauce,
                        Mocks.lemon,
                        Mocks.milk,
                        Mocks.milk,
                        Mocks.chocolateSauce,
                        Mocks.milk,
                        Mocks.chocolateSauce,
                        Mocks.hazelnutSyrup,
                        Mocks.milk
                ))

        val itemReports = ItemReportsService(listToppings!!)

        val toppings = itemReports.getToppingsMostBoughtByCustomers()

        assertEquals(5L, toppings[0].id)
        assertEquals(7L, toppings[1].id)
        assertEquals(6L, toppings[2].id)
        assertEquals(8L, toppings[3].id)
    }
}