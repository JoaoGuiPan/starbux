package com.jpan.starbux.model

import java.math.BigDecimal

data class CustomerOrders(
        val customer: String = "",
        val totalOrdersValue: BigDecimal = BigDecimal.ZERO
)
