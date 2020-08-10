package com.jpan.starbux.common

interface DeleteRepository<T> {
    fun delete(entity: T)
}