package com.jpan.starbux.repository

import com.jpan.starbux.common.CreateRepository
import com.jpan.starbux.common.DeleteRepository
import com.jpan.starbux.common.ListRepository
import com.jpan.starbux.common.UpdateRepository
import com.jpan.starbux.model.Cart
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface CartRepository: CrudRepository<Cart, Long>

@Repository
class CartJpaRepository(private val repository: CartRepository): CreateRepository<Cart>, UpdateRepository<Cart>,
        DeleteRepository<Cart> {

    override fun create(entity: Cart) = repository.save(entity)

    override fun update(entity: Cart) = repository.save(entity)

    override fun delete(entity: Cart) {
        repository.delete(entity)
    }
}