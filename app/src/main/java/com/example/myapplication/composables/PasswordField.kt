package com.example.myapplication.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    text: String,
    isVisible: Boolean,
    hasError: String,
    onValueChange: (String) -> Unit,
    onPasswordToggle: () -> Unit,

    ) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Surface(
            modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(18.dp)),
            color = Color.White,
            shape = RoundedCornerShape(18.dp)
        ) {
            TextField(
                value = text,
                onValueChange = onValueChange,
                label = { Text("Password", color = Color.DarkGray) },
                isError = false,
                singleLine = false,
                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (isVisible) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (isVisible) "Hide password" else "Show password"

                    IconButton(onClick = {
                        onPasswordToggle.invoke()
                    }) {


                        Icon(imageVector = image, description)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )

            )
        }
        if (hasError.isNotEmpty())
            Text(
                text = "Error: $hasError",
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

    }


}