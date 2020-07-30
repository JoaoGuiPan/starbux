package com.bestseller.starbux.common

interface CreateService<T> {
    fun create(entity: T): T
}