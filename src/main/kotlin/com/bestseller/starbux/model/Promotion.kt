package com.bestseller.starbux.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Entity
@Table
data class Promotion(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_seq")
        @SequenceGenerator(name = "promotion_seq", allocationSize = 1)
        var id: Long? = null,

        @Enumerated(EnumType.STRING)
        val type: PromotionType = PromotionType.CART,

        val description: String = "",

        @field:Min(0)
        val discountValue: BigDecimal = BigDecimal.ZERO,

        val input: List<Item> = ArrayList(),

        val output: List<Item> = ArrayList(),

        val enabled: Boolean = true,

        val startDate: LocalDateTime = LocalDateTime.now(),

        val endDate: LocalDateTime = LocalDateTime.now()
)

enum class PromotionType {
    CART, ITEM
}

enum class DiscountStrategy {
    LOWEST_VALUE, HIGHEST_VALUE
}