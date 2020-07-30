package com.bestseller.starbux.common

interface ListRepository<T> {
    fun listAll(): List<T>
}