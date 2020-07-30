package com.bestseller.starbux.common

interface DeleteRepository<T> {
    fun delete(entity: T)
}