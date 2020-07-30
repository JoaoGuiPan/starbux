package com.bestseller.starbux.model

import java.math.BigDecimal
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

        @field:Min(0)
        @field:Max(100)
        val discountPercentage: BigDecimal = BigDecimal.ZERO
) {

    enum class PromotionType {
        CART, ITEM
    }
}