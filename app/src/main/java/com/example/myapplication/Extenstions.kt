package com.example.myapplication


import androidx.compose.ui.graphics.Color

fun String.getColor(): Color {
  return  Color(android.graphics.Color.parseColor(this))
}