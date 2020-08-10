package com.jpan.starbux.controller

import com.jpan.starbux.common.CONSTANTS.IS_ADMIN
import com.jpan.starbux.model.CustomerOrders
import com.jpan.starbux.model.Item
import com.jpan.starbux.repository.OrderReports
import com.jpan.starbux.service.ItemReportsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(value = "Admin Reports", description = "REST endpoints responsible for Admin Reports")
@RestController
@RequestMapping("reports")
class ReportsController(
        private val orderReports: OrderReports,
        private val itemReportsService: ItemReportsService
) {

    val logger: Logger = LoggerFactory.getLogger(ReportsController::class.java)

    @ApiOperation(value = "Fetch order amount per customer. User role: ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping("/orders/customers")
    @PreAuthorize(IS_ADMIN)
    fun customersReport(): List<CustomerOrders> {
        logger.info("Fetching order amount per customer")
        return orderReports.getOrdersPerCustomer()
    }

    @ApiOperation(value = "Fetch most used toppings. User role: ADMIN")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping("/orders/toppings")
    @PreAuthorize(IS_ADMIN)
    fun toppingsReport(): List<Item> {
        logger.info("Fetching most used toppings")
        return itemReportsService.getToppingsMostBoughtByCustomers()
    }
}