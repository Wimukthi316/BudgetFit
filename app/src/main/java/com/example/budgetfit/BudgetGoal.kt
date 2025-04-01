package com.example.budgetfit

import java.util.*

data class BudgetGoal(
    val id: String = UUID.randomUUID().toString(),
    val monthYear: String, // Format: MM/yyyy
    val amount: Double
)