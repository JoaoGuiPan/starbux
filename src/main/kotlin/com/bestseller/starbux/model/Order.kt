package com.bestseller.starbux.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
// had a big problem here that took me an hour because apparently "order" is a reserved word for the DB :/
@Table(name = "orders")
data class Order(
        // uses the same cart id
        @Id
        var id: Long? = null,

        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name = "order_order_items")
        val items: MutableSet<OrderItem> = HashSet(),

        var totalPrice: BigDecimal = BigDecimal.ZERO,

        var totalDiscount: BigDecimal = BigDecimal.ZERO,

        var netPrice: BigDecimal = BigDecimal.ZERO,

        @field:NotEmpty
        // not nullable because, in true starbux fashion, you have to give out your name :)
        val customer: String = "",

        val date: LocalDateTime = LocalDateTime.now()
)

data class OrderFilter(
        var customer: String? = null,
        var mostUsedTopping: Item.ItemCategory? = null
)