package com.example.linkvest.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

// dati finti
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
data class User(val id: String, val name: String)
data class Tag(val id: String, val label: String)
data class CardItem(val id: String, val imageRes: Int, val title: String, val tags: List<String>)

@Composable
fun HomeScreen(navController: NavController) {
    val backgroundColor = Color(0xFFF6E7D8)
    val accentColor = Color(0xFFD27B48)
    val textColor = Color(0xFFB75C1D)
    val tagColor = Color(0xFF2C2827)
    val tagTextColor = Color(0xFFECECEC)

    // Drawer state
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Mock utenti seguiti
    val users = remember { listOf(
        User("1", "Alice"),
        User("2", "Bob"),
        User("3", "Carla"),
        User("4", "Dario"),
        User("5", "Elena")
    ) }

    // Mock tag
    var selectedTags by remember { mutableStateOf(setOf("art", "city")) }
    val tags = remember { listOf(
        Tag("art", "Art"),
        Tag("city", "City"),
        Tag("food", "Food"),
        Tag("travel", "Travel"),
        Tag("music", "Music")
    ) }

    // Mock card items
    val allCards = remember { listOf(
        CardItem("1", android.R.drawable.ic_menu_gallery, "Passeggiata in centro", listOf("city", "art")),
        CardItem("2", android.R.drawable.ic_menu_gallery, "London Bus", listOf("city", "travel")),
        CardItem("3", android.R.drawable.ic_menu_gallery, "Caffè Iris", listOf("food", "city"))
    )}

    // Filtra card in base ai tag selezionati
    val filteredCards = allCards.filter { card ->
        selectedTags.isEmpty() || card.tags.any { it in selectedTags }
    }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerMenu(navController)
        },
        drawerState = drawerState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(20.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = accentColor,
                    modifier = Modifier
                        .size(38.dp)
                        .clickable {
                            scope.launch { drawerState.open() }
                        }
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Hi User!",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp,
                    color = textColor,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            // Avatar row
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
                    .height(90.dp)
                    .background(accentColor, RoundedCornerShape(40.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        users.forEach { user ->
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF6E4B3A))
                                    .padding(6.dp)
                                    .padding(end = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Person, contentDescription = user.name, tint = Color(0xFFB9C4E1), modifier = Modifier.size(36.dp))
                            }
                        }
                    }
                    // Add new user button
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color(0xFFE9B994))
                            .clickable { /* TODO: azione aggiungi utente */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add", tint = accentColor, modifier = Modifier.size(36.dp))
                    }
                }
            }

            // Tag row
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                items(tags) { tag ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .background(tagColor, RoundedCornerShape(16.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .clickable {
                                selectedTags = if (selectedTags.contains(tag.id)) {
                                    selectedTags - tag.id
                                } else {
                                    selectedTags + tag.id
                                }
                            }
                    ) {
                        Text("✗", color = tagTextColor, fontSize = 16.sp, modifier = Modifier.padding(end = 4.dp))
                        Text(tag.label, color = tagTextColor, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    }
                }
            }

            // Card images row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                filteredCards.forEach { card ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(0.6f)
                            .padding(horizontal = 6.dp),
                        shape = RoundedCornerShape(22.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            // Placeholder image, puoi cambiare con coil/Glide se vuoi immagini da URL
                            Image(
                                painter = painterResource(id = card.imageRes),
                                contentDescription = card.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                            )
                            Text(
                                card.title,
                                modifier = Modifier.padding(8.dp),
                                color = textColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}