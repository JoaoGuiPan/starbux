package com.bestseller.starbux.common

interface CreateRepository<T> {
    fun create(entity: T): T
}