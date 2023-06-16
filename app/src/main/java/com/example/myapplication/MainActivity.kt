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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.NavyBlue


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
                    LoginForm()
                }


            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(NavyBlue)
    ) {

        val (box, jsLogo) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.loginjslogo),
            contentDescription = "logo",
            modifier = Modifier
                .size(150.dp)
                .constrainAs(jsLogo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(box.top)
                }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
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
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(18.dp)),
                    color = Color.White,
                    shape = RoundedCornerShape(18.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { "" },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        placeholder = { Text("Username") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Gray, // Set your desired stroke color when focused
                            unfocusedBorderColor = Color.Gray, // Set your desired stroke color when unfocused
                            disabledBorderColor = Color.Gray,
                            textColor = Color.Black,
                            disabledTextColor = Color.Gray
                        ),
                        singleLine = true
                    )
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(18.dp)),
                    color = Color.White,
                    shape = RoundedCornerShape(18.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { "" },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        placeholder = { Text("Password") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color.Black,
                            disabledTextColor = Color.Gray
                        ),
                        singleLine = false
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NavyBlue,
                    )

                ) {
                    Text(text = "Login")
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(text = "New to JS Mobile? Register here!", color = NavyBlue)
                Text(text = "Forgot Username / Password / FPIN", color = NavyBlue)

                Row(
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background("#DCDCDC".getColor()),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.whatsapp_icon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "JSBOT",
                        color = NavyBlue,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.fingerprint),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )


            }

        }

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