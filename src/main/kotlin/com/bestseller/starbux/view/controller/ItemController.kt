package com.bestseller.starbux.view.controller

import com.bestseller.starbux.common.CreateRepository
import com.bestseller.starbux.common.DeleteRepository
import com.bestseller.starbux.common.ListRepository
import com.bestseller.starbux.common.UpdateRepository
import com.bestseller.starbux.model.Item
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
data class ItemController(
        private val itemList: ListRepository<Item>,
        private val itemCreate: CreateRepository<Item>,
        private val itemUpdate: UpdateRepository<Item>,
        private val itemDelete: DeleteRepository<Item>
) {

    @GetMapping
    fun getItems() = itemList.listAll()

    @GetMapping("/{item}")
    fun getById(@PathVariable item: Item) = item

    @PostMapping
    fun createItem(@RequestBody item: Item) = itemCreate.create(item)

    @PutMapping("/{item}")
    fun updateItem(@PathVariable item: Item, @RequestBody body: Item) =
            itemUpdate.update(
                    item.copy(
                            category = body.category,
                            description = body.description,
                            price = body.price
                    )
            )

    @DeleteMapping("/{item}")
    fun deleteItem(@PathVariable item: Item) {
        itemDelete.delete(item)
    }
}