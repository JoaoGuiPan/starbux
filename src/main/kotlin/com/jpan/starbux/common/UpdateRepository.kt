package com.jpan.starbux.common

interface UpdateRepository<T> {
    fun update(entity: T): T
}