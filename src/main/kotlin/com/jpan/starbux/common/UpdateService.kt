package com.jpan.starbux.common

import com.jpan.starbux.model.Cart

interface UpdateService<T, P> {
    fun update(entity: T, payload: P): T
}