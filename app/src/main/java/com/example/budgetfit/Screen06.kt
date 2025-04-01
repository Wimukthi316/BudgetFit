package com.example.budgetfit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.*
import android.widget.ImageView

class Screen06 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen06)

        // Option 1: Using android.R.id.content (recommended if you don't have @+id/main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize ExpenseRepository
        val expenseRepository = ExpenseRepository(this)

        // Calculate and display total expenses
        val totalExpenses = expenseRepository.getAllExpenses().sumOf { it.amount }
        val balanceAmountText = findViewById<TextView>(R.id.balanceAmount)
        balanceAmountText.text = formatCurrency(totalExpenses)

        // Set click listeners for quick action cards
        findViewById<View>(R.id.expensesCard).setOnClickListener {
            startActivity(Intent(this, Screen07::class.java))
        }

        findViewById<View>(R.id.allExpensesCard).setOnClickListener {
            startActivity(Intent(this, Screen08::class.java))
        }

        findViewById<View>(R.id.budgetPlanCard).setOnClickListener {
            startActivity(Intent(this, Screen09::class.java))
        }
    }

    private fun formatCurrency(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance("USD")
        return format.format(amount)
    }
}