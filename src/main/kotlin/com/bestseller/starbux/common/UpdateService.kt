package com.bestseller.starbux.common

import com.bestseller.starbux.model.Cart

interface UpdateService<T> {
    fun update(entity: T, payload: Cart): T
}