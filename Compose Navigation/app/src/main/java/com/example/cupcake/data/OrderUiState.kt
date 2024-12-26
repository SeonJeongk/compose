package com.example.cupcake.data

/**
 * Data class that represents the current UI state in terms of [quantity], [flavor],
 * [dateOptions], selected pickup [date] and [price]
 */
data class OrderUiState (
    val quantity: Int = 0,  // Selected cupcake quantity (1, 6, 12)
    val flavor: String = "",    // Flavor of the cupcakes in the order
    val date: String = "",  // Selected date for pickup (such as "Jan 1")
    val price: String = "", // Total price for the order
    val pickupOptions: List<String> = listOf()  // Available pickup dates for the order
)
