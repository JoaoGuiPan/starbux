package com.bestseller.starbux.repository

import com.bestseller.starbux.common.CreateRepository
import com.bestseller.starbux.common.DeleteRepository
import com.bestseller.starbux.common.ListRepository
import com.bestseller.starbux.common.UpdateRepository
import com.bestseller.starbux.model.Item
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface ItemRepository: CrudRepository<Item, Long> {
    @Query("select i" +
            " from Order o " +
            " join o.items oi " +
            " join oi.items i " +
            " where i.category = 'TOPPING_OR_SIDE' "
    )
    fun getAllToppingsBoughtByCustomers(): List<Item>

    fun categoryIs(category: Item.ItemCategory): List<Item>
}

interface ListToppings {
    fun getAllToppings(): List<Item>
    fun getAllToppingsBoughtByCustomers(): List<Item>
}

@Repository
class ItemJpaRepository(private val repository: ItemRepository): CreateRepository<Item>, UpdateRepository<Item>,
        ListRepository<Item>, DeleteRepository<Item>, ListToppings {

    override fun create(entity: Item) = repository.save(entity)

    override fun update(entity: Item) = repository.save(entity)

    override fun listAll(): List<Item> = repository.findAll().toList()

    override fun delete(entity: Item) {
        repository.delete(entity)
    }

    override fun getAllToppings(): List<Item> = repository.categoryIs(Item.ItemCategory.TOPPING_OR_SIDE)

    override fun getAllToppingsBoughtByCustomers(): List<Item> = repository.getAllToppingsBoughtByCustomers()
}