package com.bestseller.starbux.common

interface UpdateRepository<T> {
    fun update(entity: T): T
}