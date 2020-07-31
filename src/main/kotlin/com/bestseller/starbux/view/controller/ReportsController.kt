package com.bestseller.starbux.view.controller

import com.bestseller.starbux.common.CONSTANTS.Companion.IS_ADMIN
import com.bestseller.starbux.repository.OrderReports
import com.bestseller.starbux.service.ItemReportsService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reports")
class ReportsController(
        private val orderReports: OrderReports,
        private val itemReportsService: ItemReportsService
) {

    @GetMapping("/orders/customers")
    @PreAuthorize(IS_ADMIN)
    fun customersReport() = orderReports.getOrdersPerCustomer()

    @GetMapping("/orders/toppings")
    @PreAuthorize(IS_ADMIN)
    fun toppingsReport() = itemReportsService.getToppingsMostBoughtByCustomers()
}