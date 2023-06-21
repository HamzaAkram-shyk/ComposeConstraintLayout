package com.example.myapplication


import android.content.Context
import android.os.Bundle
import android.view.RoundedCorner
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.states.MainEvent
import com.example.myapplication.ui.theme.MainViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.NavyBlue
import kotlinx.coroutines.flow.update
import kotlin.system.exitProcess


class MainActivity : ComponentActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            context = LocalContext.current
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<MainViewModel>()
                    LoginForm(viewModel = viewModel)
                }


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val flowUiState by viewModel.stateFlow.collectAsState()
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(NavyBlue),
    ) {

        val (box, jsLogo) = createRefs()

        Image(painter = painterResource(id = R.drawable.loginjslogo),
            contentDescription = "logo",
            modifier = Modifier
                .size(150.dp)
                .constrainAs(jsLogo) {
                    centerHorizontallyTo(parent, 0.5f)
                    top.linkTo(parent.top)
                    bottom.linkTo(box.top)
                })
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
            .padding(16.dp)
            .constrainAs(box) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
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
                        value = flowUiState.userNameText,
                        onValueChange = { newString ->
                            viewModel.onEvent(MainEvent.ChangeUserNameText(newText = newString))
                        },
                        supportingText = {
                            Text("Error", color = Color.Red)
                        },
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

                PasswordField(
                    modifier = Modifier.fillMaxWidth(),
                    flowUiState.passwordText,
                    flowUiState.passwordShow,
                    onValueChange = {
                        viewModel.onEvent(MainEvent.ChangePasswordText(newText = it))
                    }
                ) {
                    viewModel.onEvent(MainEvent.TogglePassword)
                }

                Button(
                    onClick = {
                        exitProcess(0)
                    },
                    modifier = Modifier.fillMaxWidth(),
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
                        .background(if (flowUiState.isJsBotEnable) Color.Black else "#DCDCDC".getColor()),

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
fun PasswordField(
    modifier: Modifier = Modifier,
    text: String,
    isVisible: Boolean,
    onValueChange: (String) -> Unit,
    onPasswordToggle: () -> Unit
) {
    Surface(
        modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(18.dp)),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        TextField(
            modifier = Modifier.background(Color.White),
            value = text,
            onValueChange = onValueChange,
            label = { Text("Password", color = Color.DarkGray) },
            singleLine = true,
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        // Greeting("Android")
        val viewModel = viewModel<MainViewModel>()
        LoginForm(viewModel = viewModel)
    }
}