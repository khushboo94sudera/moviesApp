package com.example.myapplication.presentation.log_in
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.reusable_content.FilledContentButton
import com.example.myapplication.ui.reusable_content.HeadingText
import com.example.myapplication.ui.reusable_content.HorizontalOrDivider
import com.example.myapplication.ui.reusable_content.MediumHeadingText
import com.example.myapplication.ui.reusable_content.OutlinedContentButton
import com.example.myapplication.ui.reusable_content.OutlinedContentButtonWithIcon
import com.example.myapplication.ui.reusable_content.TextBoxContent
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("SuspiciousIndentation")
@Composable
fun Login(
    state: SignInState,
    navigateToMenu:()->Unit,
    navigateToSignUp:()->Unit,
    googleNavigationToMenu:()->Unit
) {

    //error handling
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()


        Box(
            modifier = Modifier
                .fillMaxSize()
                //.padding(it)
                .background(color = Color.Black)
                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier.padding( start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,

                ) {
                Spacer(modifier = Modifier.height(60.dp))
                HeadingText(name = "Login")
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Email or Username")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(
                    name = "Enter your Email or Username",
                    value = email,
                    onValueChange = {email = it}
                )
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Password")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(
                    name = "Enter your Password",
                    value = password,
                    onValueChange = {password = it},
                    //visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    navigateToMenu()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Failed to sign in: ${task.exception?.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffA21313)),

                    ){
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedContentButton(name = "Register", navigate = navigateToSignUp)
                Spacer(modifier = Modifier.height(22.dp))
                HorizontalOrDivider(name = "Or")
                Spacer(modifier = Modifier.height(22.dp))
                OutlinedContentButtonWithIcon(
                    name = "Sign in with Google",
                    icon = R.drawable.logos_google_icon,
                    navigate = googleNavigationToMenu
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedContentButtonWithIcon(
                    name = "Sign in with Facebook",
                    icon = R.drawable.logos_facebook
                )
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    MyApplicationTheme {
        //Login()
    }
}