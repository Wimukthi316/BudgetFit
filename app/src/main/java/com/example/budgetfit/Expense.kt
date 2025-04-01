package com.example.budgetfit

import java.util.*

data class Expense(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val category: String,
    val amount: Double,
    val date: String
)