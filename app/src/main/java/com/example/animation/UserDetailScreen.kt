package com.example.animation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun UserDetailScreen(userId: Int, navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val users by viewModel.users.collectAsState()
    val user = users.find { it.id == userId }

    user?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { navController.navigateUp() }) {
                Text("Назад к списку")
            }

            val infiniteTransition = rememberInfiniteTransition(label = "user image transition")

            val scaleX by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "image scale x"
            )

            val scaleY by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "image scale y"
            )

            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "image alpha"
            )

            AsyncImage(
                model = "https://via.placeholder.com/150",
                contentDescription = "Аватар ${it.name}",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .graphicsLayer(
                        scaleX = scaleX,
                        scaleY = scaleY
                    )
                    .alpha(alpha)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Основная информация о пользователе
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Личная информация",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Имя: ${it.name}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Имя пользователя: ${it.username}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Email: ${it.email}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Телефон: ${it.phone}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Сайт: ${it.website}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Информация об адресе
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Адрес",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Улица: ${it.address.street}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Квартира/Офис: ${it.address.suite}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Город: ${it.address.city}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Почтовый индекс: ${it.address.zipcode}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Геолокация:",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "Широта: ${it.address.geo.lat}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Долгота: ${it.address.geo.lng}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Информация о компании
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Компания",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Название: ${it.company.name}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Слоган: ${it.company.catchPhrase}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Сфера деятельности: ${it.company.bs}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}