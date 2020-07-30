package com.bestseller.starbux.service

import com.bestseller.starbux.model.Item
import com.bestseller.starbux.repository.ListToppings
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemReportsService(private val listToppings: ListToppings) {

    fun getToppingsMostBoughtByCustomers(): List<Item> {
        var occurrences: List<Pair<Long, Int>> = ArrayList()

        val allToppings = listToppings.getAllToppings()
        val toppingsBought = listToppings.getAllToppingsBoughtByCustomers().map { top -> top.id }

        allToppings.forEach {
            val frequency = Collections.frequency(toppingsBought, it.id)
            if (frequency > 0) {
                occurrences = occurrences.plus(Pair(it.id!!, frequency))
            }
        }

        occurrences = occurrences.sortedWith(compareBy({ it.second }, { it.first })).asReversed()

        return occurrences.map { occur -> allToppings.find { occur.first == it.id }!! }
    }
}