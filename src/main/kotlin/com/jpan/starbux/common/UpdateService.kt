package com.jpan.starbux.common

import com.jpan.starbux.model.Cart

interface UpdateService<T> {
    fun update(entity: T, payload: Cart): T
}