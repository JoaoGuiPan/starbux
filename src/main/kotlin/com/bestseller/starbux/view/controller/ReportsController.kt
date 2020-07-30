package com.bestseller.starbux.view.controller

import com.bestseller.starbux.repository.OrderReports
import com.bestseller.starbux.service.ItemReportsService
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
    fun customersReport() = orderReports.getOrdersPerCustomer()

    @GetMapping("/orders/toppings")
    fun toppingsReport() = itemReportsService.getToppingsMostBoughtByCustomers()
}