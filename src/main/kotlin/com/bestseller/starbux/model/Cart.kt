package com.bestseller.starbux.model

import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table
data class Cart(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
        @SequenceGenerator(name = "cart_seq", allocationSize = 1)
        var id: Long? = null,

        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name = "cart_order_items")
        val items: MutableSet<OrderItem> = HashSet(),

        var totalPrice: BigDecimal = BigDecimal.ZERO,

        var totalDiscount: BigDecimal = BigDecimal.ZERO,

        var netPrice: BigDecimal = BigDecimal.ZERO,

        @field:NotEmpty
        // not nullable because, in true starbux fashion, you have to give out your name :)
        var customer: String = ""
) {
        fun calculateTotalPrice() {
                totalPrice = items.map {
                        it.items.map { item -> item.price }.fold(BigDecimal.ZERO, { total, price -> total.plus(price) })
                }.fold(BigDecimal.ZERO, { total, price -> total.plus(price) })
        }

        fun setDiscount(value: BigDecimal) {
                totalDiscount = value
        }

        fun addDiscount(value: BigDecimal) {
                totalDiscount += value
        }

        fun calculateNetPrice() {
                netPrice = totalPrice.minus(totalDiscount)
        }
}