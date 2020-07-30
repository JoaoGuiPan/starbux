package com.bestseller.starbux.service

import com.bestseller.starbux.model.Cart
import com.bestseller.starbux.service.promotion.MoreThanTwelveEurosDiscountRule
import com.bestseller.starbux.service.promotion.ThirdDrinkFreeDiscountRule
import org.jeasy.rules.api.Facts
import org.jeasy.rules.api.Rule
import org.jeasy.rules.api.Rules
import org.jeasy.rules.api.RulesEngine
import org.jeasy.rules.core.DefaultRulesEngine
import org.springframework.stereotype.Service
import java.math.BigDecimal

private const val CART_FACT = "cart"

@Service
class DiscountCommand {

    fun applyDiscounts(cart: Cart) {

        // TODO READ FROM FILE OR DB
        val moreThanTwelveEurosDiscountRule = MoreThanTwelveEurosDiscountRule()
        val thirdDrinkFreeDiscountRule = ThirdDrinkFreeDiscountRule()

        val discountRules = Rules(moreThanTwelveEurosDiscountRule, thirdDrinkFreeDiscountRule)

        val checkedRules = checkDiscountRules(cart, discountRules)

        // check if there are more than one eligible rules
        if (checkedRules.values.filter { it }.size > 1) {
            val discounts = getAppliedDiscounts(checkedRules, cart)
            // applies the higher discount
            cart.setDiscount(discounts.last().second)
        } else {
            // apply all rules, since only one is eligible anyway
            applyDiscountRules(cart, discountRules)
        }

        cart.calculateNetPrice()
    }

    private fun getAppliedDiscounts(checkedRules: MutableMap<Rule, Boolean>, cart: Cart): ArrayList<Pair<String, BigDecimal>> {

        val discounts = ArrayList<Pair<String, BigDecimal>>()

        checkedRules.forEach {
            if (it.value) {
                applyDiscountRules(cart, Rules(Class.forName(it.key.name).getDeclaredConstructor().newInstance()))
                discounts.add(Pair(it.key.name, cart.totalDiscount))
            }
        }

        discounts.sortBy { it.second }

        return discounts
    }

    private fun checkDiscountRules(cart: Cart, rules: Rules): MutableMap<Rule, Boolean> {
        val facts = Facts()
        facts.put(CART_FACT, cart)

        val rulesEngine: RulesEngine = DefaultRulesEngine()

        return rulesEngine.check(rules, facts)
    }

    private fun applyDiscountRules(cart: Cart, rules: Rules) {
        val facts = Facts()
        facts.put(CART_FACT, cart)

        val rulesEngine: RulesEngine = DefaultRulesEngine()

        rulesEngine.fire(rules, facts)
    }
}