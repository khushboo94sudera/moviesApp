package com.example.myapplication.ui.sign_up
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.ui.log_in.SignInState
import com.example.myapplication.ui.reusable_content.FilledContentButton
import com.example.myapplication.ui.reusable_content.HeadingText
import com.example.myapplication.ui.reusable_content.HorizontalOrDivider
import com.example.myapplication.ui.reusable_content.MediumHeadingText
import com.example.myapplication.ui.reusable_content.OutlinedContentButtonWithIcon
import com.example.myapplication.ui.reusable_content.TextBoxContent
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


@SuppressLint("SuspiciousIndentation")
@Composable
fun SignUp(
    navigateToLogin:()->Unit,
    navigateToMenu:()->Unit,
    googleNavigationToMenu:()->Unit,
    context: Context = LocalContext.current
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var profilePictureUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        // Handle the selected image URI
        uri?.let { profilePictureUri = it }
    }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                //.padding(it)
                .background(color = Color.Black)
                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                ) {
                Spacer(modifier = Modifier.height(60.dp))
                HeadingText(name = "Register")
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Select Profile Picture")
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(226.dp)
                        .border(
                            BorderStroke(width = 10.dp, color = Color(0xffA21313)),
                            shape = RoundedCornerShape(100)
                        )
                        //.fillMaxWidth()
                        //.aspectRatio(1f)
                        .clickable {
                            launcher.launch("image/*")
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = "Add Photo",
                        //modifier = Modifier.align(Alignment.Center)
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(210.dp)
                            .clip(CircleShape)
                    )
                    profilePictureUri?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Profile Picture",
                            //modifier = Modifier.fillMaxSize()
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(210.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    // Placeholder or icon for selecting profile picture

                }

                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Name")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(
                    name = "Enter your Full Name",
                    value = fullName,
                    onValueChange = {fullName = it}
                )
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Email")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(
                    name = "Enter your Email",
                    value = email,
                    onValueChange = {email = it}
                )
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "User")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(
                    name = "Enter your username",
                    value = username,
                    onValueChange = {username = it}
                )
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Password")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(
                    name = "Enter your Password",
                    value = password,
                    onValueChange = {password = it},
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                //FilledContentButton(name = "Register")
                Button(
                    onClick = {
                        if (fullName.isNotBlank() && username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val user = auth.currentUser
                                        val profileUpdates = UserProfileChangeRequest.Builder()
                                            .setDisplayName(username)
                                            .build()
                                        user?.updateProfile(profileUpdates)
                                            ?.addOnCompleteListener { profileTask ->
                                                if (profileTask.isSuccessful) {
                                                    navigateToMenu()
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Failed to update profile: ${profileTask.exception?.message}",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Failed to sign up: ${task.exception?.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(
                                context,
                                "Please fill in all required fields",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffA21313)),

                    ){
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.height(22.dp))
                FilledContentButton(name = "Login", navigate = navigateToLogin)
                Spacer(modifier = Modifier.height(22.dp))
                HorizontalOrDivider(name = "Or")
                Spacer(modifier = Modifier.height(22.dp))
                OutlinedContentButtonWithIcon(
                    name = "Sign Up with Google",
                    icon = R.drawable.logos_google_icon,
                    navigate = googleNavigationToMenu
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedContentButtonWithIcon(
                    name = "Register in with Facebook",
                    icon = R.drawable.logos_facebook
                )
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    MyApplicationTheme {
        //SignUp()
    }
}