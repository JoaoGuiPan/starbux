package com.bestseller.starbux.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table
data class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
        @SequenceGenerator(name = "item_seq", allocationSize = 1)
        var id: Long? = null,

        @Enumerated(EnumType.STRING)
        val category: ItemCategory = ItemCategory.DRINK,

        var description: String = "",

        var price: BigDecimal = BigDecimal.ZERO
) {
        enum class ItemCategory {
                DRINK, TOPPING_OR_SIDE
        }
}
