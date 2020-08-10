package com.jpan.starbux.common

interface CreateRepository<T> {
    fun create(entity: T): T
}