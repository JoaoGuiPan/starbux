package com.bestseller.starbux.view.controller

import com.bestseller.starbux.common.*
import com.bestseller.starbux.common.CONSTANTS.Companion.DEFAULT_CURRENCY
import com.bestseller.starbux.common.CONSTANTS.Companion.HAS_ANY_ROLE
import com.bestseller.starbux.common.CONSTANTS.Companion.IS_ADMIN
import com.bestseller.starbux.model.Item
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
data class ItemController(
        private val itemList: ListRepository<Item>,
        private val itemCreate: CreateRepository<Item>,
        private val itemUpdate: UpdateRepository<Item>,
        private val itemDelete: DeleteRepository<Item>
) {

    @Value("currency")
    private val currency: String = DEFAULT_CURRENCY

    @GetMapping
    @PreAuthorize(HAS_ANY_ROLE)
    fun getItems() = itemList.listAll()

    @GetMapping("/{item}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun getById(@PathVariable item: Item) = item

    @PostMapping
    @PreAuthorize(IS_ADMIN)
    fun createItem(@RequestBody item: Item) = itemCreate.create(
            item.copy(
                    currency = currency
            )
    )

    @PutMapping("/{item}")
    @PreAuthorize(IS_ADMIN)
    fun updateItem(@PathVariable item: Item, @RequestBody body: Item) =
            itemUpdate.update(
                    item.copy(
                            category = body.category,
                            description = body.description,
                            price = body.price
                    )
            )

    @DeleteMapping("/{item}")
    @PreAuthorize(IS_ADMIN)
    fun deleteItem(@PathVariable item: Item) {
        itemDelete.delete(item)
    }
}