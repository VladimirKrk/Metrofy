package com.example.metrofy.model

import androidx.compose.ui.geometry.Offset

data class MetroStation(val name: String, val position: Offset) {
    companion object {
        fun getStations(): List<MetroStation> {
            return listOf(
                MetroStation("Пятницкое Шоссе", Offset(584f, 1170f)),
                // Add other stations here
            )
        }
    }
}
