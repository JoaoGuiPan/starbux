package com.jpan.starbux.repository

import com.jpan.starbux.common.CreateRepository
import com.jpan.starbux.common.ListRepository
import com.jpan.starbux.model.CustomerOrders
import com.jpan.starbux.model.Item
import com.jpan.starbux.model.Order
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.ArrayList

interface OrderRepository: CrudRepository<Order, Long> {
    @Query("select new com.jpan.starbux.model.CustomerOrders( o.customer, sum(o.netPrice) ) " +
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