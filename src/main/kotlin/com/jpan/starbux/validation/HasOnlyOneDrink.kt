package com.jpan.starbux.validation

import com.jpan.starbux.model.Item
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [HasOnlyOneDrinkValidator::class])
annotation class HasOnlyOneDrink(
        val message: String = "order item must contain a single drink",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)

class HasOnlyOneDrinkValidator : ConstraintValidator<HasOnlyOneDrink, MutableSet<Item>> {

    override fun isValid(value: MutableSet<Item>?, context: ConstraintValidatorContext?) = value.hasOnlyOneDrink()
}

fun MutableSet<Item>?.hasOnlyOneDrink(): Boolean =
        this?.filter { it.category == Item.ItemCategory.DRINK }?.size?.compareTo(1)?.equals(0) ?: false