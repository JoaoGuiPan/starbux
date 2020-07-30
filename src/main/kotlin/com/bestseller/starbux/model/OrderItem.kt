package com.bestseller.starbux.model

import com.bestseller.starbux.validation.HasOnlyOneDrink
import javax.persistence.*

@Entity
@Table(name = "order_items")
data class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
        @SequenceGenerator(name = "order_item_seq", allocationSize = 1)
        var id: Long? = null,

        @field:HasOnlyOneDrink
        @ManyToMany
        @JoinTable(name = "order_item_items")
        val items: MutableSet<Item> = HashSet()
)