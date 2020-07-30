package com.bestseller.starbux.repository

import com.bestseller.starbux.common.CreateRepository
import com.bestseller.starbux.common.ListRepository
import com.bestseller.starbux.model.CustomerOrders
import com.bestseller.starbux.model.Item
import com.bestseller.starbux.model.Order
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.ArrayList

interface OrderRepository: CrudRepository<Order, Long> {
    @Query("select new com.bestseller.starbux.model.CustomerOrders( o.customer, sum(o.netPrice) ) " +
            " from Order o " +
            " group by o.customer")
    fun getOrdersPerCustomer(): List<CustomerOrders>
}

interface OrderReports {
    fun getOrdersPerCustomer(): List<CustomerOrders>
}

@Repository
class OrderJpaRepository(private val repository: OrderRepository): CreateRepository<Order>, ListRepository<Order>,
        OrderReports {

    override fun create(entity: Order) = repository.save(entity)

    override fun listAll(): List<Order> = repository.findAll().toList()

    override fun getOrdersPerCustomer(): List<CustomerOrders> = repository.getOrdersPerCustomer()
}