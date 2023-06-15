package com.example.myapplication


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {

    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            context = LocalContext.current
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.fillMaxSize()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        val (box) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.Black)
                .padding(16.dp)
                .constrainAs(box) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {

                Spacer(modifier = Modifier.height(80.dp))

//                OutlinedTextField(
//                    value = "", onValueChange = {},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .border(BorderStroke(3.dp, Color.Blue), RoundedCornerShape(20.dp)),
//
//                    )

//                OutlinedTextField(
//                    value = "",
//                    onValueChange = {},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .border(
//                            width = 2.dp,
//                            color = Color.Blue,
//                            shape = RoundedCornerShape(10.dp)
//                        )
//                        .background(Color.White)
//                        .padding(horizontal = 2.dp),
//                    placeholder = { Text("Enter text") },
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        focusedBorderColor = Color.Blue,  // Set your desired stroke color when focused
//                        unfocusedBorderColor = Color.Blue,  // Set your desired stroke color when unfocused
//                        disabledBorderColor = Color.LightGray,
//                        textColor = Color.Black,
//                        disabledTextColor = Color.Gray
//                    )
//                )


                val strokeWidth = 2.dp
                val cornerRadius = 8.dp

//                val textState = remember { mutableStateOf("") }


                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Blue, RoundedCornerShape(10.dp)),
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { "" },
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = { Text("Enter text") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Blue, // Set your desired stroke color when focused
                            unfocusedBorderColor = Color.Green, // Set your desired stroke color when unfocused
                            disabledBorderColor = Color.LightGray,
                            textColor = Color.Black,
                            disabledTextColor = Color.Gray
                        ),
                        singleLine = true
                    )
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(4.dp, Color.Blue, RoundedCornerShape(10.dp)),
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { "" },
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = { Text("Enter text") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(

                            textColor = Color.Black,
                            disabledTextColor = Color.Gray
                        ),
                        singleLine = false
                    )
                }



                TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Login")
                }
            }

        }
    }


}

@Composable
fun OutlinedTextFieldWithWhiteBorder() {

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

            modifier = Modifier
                .constrainAs(f1) {
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

        TextField(value = "", onValueChange = {},
            modifier = Modifier
                .constrainAs(f2) {
                    top.linkTo(f1.bottom, margin = 16.dp)
                    start.linkTo(f1.start)
                    end.linkTo(f1.end)
                    width = Dimension.fillToConstraints

                },
            label = { Text(text = "Email", fontSize = 16.sp) }
        )

        Button(
            onClick = { },
            modifier = Modifier
                .constrainAs(b) {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        // Greeting("Android")

        LoginForm()
    }
}