package com.jpan.starbux.common

interface CreateService<T> {
    fun create(entity: T): T
}