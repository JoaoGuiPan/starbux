package com.jpan.starbux.common

interface UpdateService<T, P> {
    fun update(entity: T, payload: P): T
}