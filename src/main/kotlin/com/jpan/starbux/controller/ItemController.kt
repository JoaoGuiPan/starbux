package com.jpan.starbux.controller

import com.jpan.starbux.common.CONSTANTS.DEFAULT_CURRENCY
import com.jpan.starbux.common.CONSTANTS.HAS_ANY_ROLE
import com.jpan.starbux.common.CONSTANTS.IS_ADMIN
import com.jpan.starbux.common.CreateRepository
import com.jpan.starbux.common.DeleteRepository
import com.jpan.starbux.common.ListRepository
import com.jpan.starbux.common.UpdateRepository
import com.jpan.starbux.model.Item
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Api(value = "Items", description = "REST endpoints responsible for CRUD operations of items")
@RestController
@RequestMapping("items")
data class ItemController(
        private val itemList: ListRepository<Item>,
        private val itemCreate: CreateRepository<Item>,
        private val itemUpdate: UpdateRepository<Item>,
        private val itemDelete: DeleteRepository<Item>
) {

    val logger: Logger = LoggerFactory.getLogger(ItemController::class.java)

    @Value("\${currency:EUR}")
    private val currency: String = DEFAULT_CURRENCY

    @ApiOperation(value = "Fetch all items. User role: USER or ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping
    @PreAuthorize(HAS_ANY_ROLE)
    fun getItems(): List<Item> {
        logger.info("Fetching all items")
        return itemList.listAll()
    }

    @ApiOperation(value = "Fetch item by id. User role: USER or ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping("/{item}")
    @PreAuthorize(HAS_ANY_ROLE)
    fun getById(@PathVariable item: Item): Item {
        logger.info("Fetching Item ID " + item.id)
        return item
    }

    @ApiOperation(value = "Create item. User role: ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @PostMapping
    @PreAuthorize(IS_ADMIN)
    fun createItem(@RequestBody item: Item) : Item {
        logger.debug("Creating new Item - $item")
        val created = itemCreate.create(
                item.copy(
                        currency = currency
                )
        )
        logger.info("Item created. ID " + created.id)
        return created
    }

    @ApiOperation(value = "Update item. User role: ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @PutMapping("/{item}")
    @PreAuthorize(IS_ADMIN)
    fun updateItem(@PathVariable item: Item, @RequestBody body: Item): Item {
        logger.debug("Updating Item ID " + item.id)
        val updated = itemUpdate.update(
                item.copy(
                        category = body.category,
                        description = body.description,
                        price = body.price
                )
        )
        logger.info("Item updated - $updated")
        return updated
    }

    @ApiOperation(value = "Delete item. User role: ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @DeleteMapping("/{item}")
    @PreAuthorize(IS_ADMIN)
    fun deleteItem(@PathVariable item: Item) {
        logger.debug("Deleting Item ID " + item.id)
        itemDelete.delete(item)
        logger.info("Item deleted, ID " + item.id)
    }
}