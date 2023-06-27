package com.example.myapplication.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myapplication.R

@Composable
fun LoadingIndicator(color: Color = Color.Green) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.LightGray.copy(alpha = 0.4f)
            )
            .clickable(enabled = false) { },
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .size(80.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

        ) {}
        CircularProgressIndicator(
            color = color,
            strokeWidth = 5.dp,
            modifier = Modifier.size(50.dp)
        )

    }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun newFun() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bck_screen),
                contentScale = ContentScale.Fit
            )

    ) {

        val (f1, f2, b) = createRefs()

        TextField(
            value = "",
            onValueChange = {},

            modifier = Modifier.constrainAs(f1) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp
                )
                linkTo(
                    top = parent.top,
                    bottom = parent.bottom,
                    bias = 0.3f,
                )
                width = Dimension.fillToConstraints


            },
            label = { Text(text = "UserName", fontSize = 16.sp) },


            )

        TextField(value = "", onValueChange = {}, modifier = Modifier.constrainAs(f2) {
            top.linkTo(f1.bottom, margin = 16.dp)
            start.linkTo(f1.start)
            end.linkTo(f1.end)
            width = Dimension.fillToConstraints

        }, label = { Text(text = "Email", fontSize = 16.sp) })

        Button(onClick = { }, modifier = Modifier.constrainAs(b) {
            top.linkTo(f2.bottom, margin = 40.dp)
            start.linkTo(parent.start, margin = 10.dp)
            end.linkTo(f2.end, margin = 10.dp)
            width = Dimension.fillToConstraints
        }

        ) {
            Text(text = "Login")
        }

    }


}