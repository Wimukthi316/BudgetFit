package com.example.budgetfit

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ExpenseRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "expense_preferences", Context.MODE_PRIVATE
    )
    private val gson = Gson()

    private val expenseListType = object : TypeToken<List<Expense>>() {}.type

    companion object {
        private const val KEY_EXPENSES = "expenses"
    }

    fun getAllExpenses(): List<Expense> {
        val expensesJson = sharedPreferences.getString(KEY_EXPENSES, null)
        return if (expensesJson != null) {
            gson.fromJson(expensesJson, expenseListType)
        } else {
            emptyList()
        }
    }

    fun getExpensesByCategory(category: String): List<Expense> {
        return if (category == "All Categories") {
            getAllExpenses()
        } else {
            getAllExpenses().filter { it.category == category }
        }
    }

    fun addExpense(expense: Expense) {
        val expenses = getAllExpenses().toMutableList()
        expenses.add(expense)
        saveExpenses(expenses)
    }

    fun updateExpense(updatedExpense: Expense) {
        val expenses = getAllExpenses().toMutableList()
        val index = expenses.indexOfFirst { it.id == updatedExpense.id }
        if (index != -1) {
            expenses[index] = updatedExpense
            saveExpenses(expenses)
        }
    }

    fun deleteExpense(expense: Expense) {
        val expenses = getAllExpenses().toMutableList()
        expenses.removeAll { it.id == expense.id }
        saveExpenses(expenses)
    }

    fun calculateTotalAmount(category: String = "All Categories"): Double {
        val expenses = if (category == "All Categories") {
            getAllExpenses()
        } else {
            getExpensesByCategory(category)
        }

        return expenses.sumOf { it.amount }
    }

    private fun saveExpenses(expenses: List<Expense>) {
        val editor = sharedPreferences.edit()
        val expensesJson = gson.toJson(expenses)
        editor.putString(KEY_EXPENSES, expensesJson)
        editor.apply()
    }

    fun getCategories(): List<String> {
        val categories = getAllExpenses()
            .map { it.category }
            .distinct()
            .sorted()
            .toMutableList()

        // Always include "All Categories" as the first option
        categories.add(0, "All Categories")
        return categories
    }
}