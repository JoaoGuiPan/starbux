package com.jpan.starbux.common

interface ListRepository<T> {
    fun listAll(): List<T>
}