package com.example.tp1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun home(windowSizeClass: WindowSizeClass, modifier: Modifier = Modifier, navController: NavHostController) {
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.photo1),
                    contentDescription = "Photo de profil",
                    modifier = Modifier
                        .size(250.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sarra Boudrigua",
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Étudiante en FIE4",
                    fontSize = 20.sp
                )
                Text(
                    text = "Ecole d'ingenieur ISIS-INU Champolion",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(70.dp))

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.baseline_mail_24),
                            contentDescription = "Icône email",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = "sarasousse7@gmail.com",
                            fontSize = 17.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                    Row {
                    Icon(
                        painter = painterResource(R.drawable.baseline_person_outline_24),
                        contentDescription = "Icône LinkedIn",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "www.linkedin.com/in/sarra-boudrigua",
                        fontSize = 17.sp,
                        textAlign = TextAlign.Start
                    )
                        }
                }

                Spacer(modifier = Modifier.height(155.dp))

                Button( onClick = {navController.navigate("films")}) {
                    Text(text = "Démarrer")
                }
            }
        }

        else -> {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.photo1),
                        contentDescription = "Photo de profil",
                        modifier = Modifier
                            .size(250.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Sarra Boudrigua",
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Étudiante en FIE4",
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_mail_24),
                            contentDescription = "Icône email",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = "sarasousse7@gmail.com",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_person_outline_24),
                            contentDescription = "Icône LinkedIn",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = "www.linkedin.com/in/sarra-boudrigua",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button( onClick = {navController.navigate("films")}) {
                        Text(text = "Démarrer")
                    }
                }
            }
        }
    }
}
