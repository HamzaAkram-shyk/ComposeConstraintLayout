package com.example.myapplication.screencomposables

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.composables.LoadingIndicator
import com.example.myapplication.composables.PasswordField
import com.example.myapplication.getColor
import com.example.myapplication.routes.ScreenRoute
import com.example.myapplication.states.MainEvent
import com.example.myapplication.ui.theme.NavyBlue
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavController
) {
    val flowUiState by viewModel.stateFlow.collectAsState()
    // val events by viewModel.event.collectAsState()
    if (flowUiState.isNavigate) {
        viewModel.uiStateFlow.update { it.emptyState() }
        navController.navigate(ScreenRoute.SignUpScreen.route)
    }

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
                }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
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
                                viewModel.onEventTriggered(MainEvent.ChangeUserNameText(newText = newString))
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
                    if (flowUiState.userNameError.isNotEmpty())
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            text = flowUiState.userNameError,
                            fontSize = 12.sp,
                            color = Color.Red
                        )
                }


                PasswordField(
                    modifier = Modifier.fillMaxWidth(),
                    flowUiState.passwordText,
                    flowUiState.passwordShow,
                    flowUiState.passwordError,
                    onValueChange = {
                        viewModel.onEventTriggered(MainEvent.ChangePasswordText(newText = it))
                    }
                ) {
                    viewModel.onEventTriggered(MainEvent.TogglePassword)
                }

                Button(
                    onClick = {
                        viewModel.onEventTriggered(MainEvent.LoginClick)
                        //  navController.navigate(ScreenRoute.SignUpScreen.route)
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

        if (flowUiState.isLoading)
            LoadingIndicator()


    }


}