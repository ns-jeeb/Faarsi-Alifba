package com.jeeb.farsialifba

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeeb.farsialifba.ui.theme.FaarsiAlifbaTheme
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaarsiAlifbaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FarsiAlphabetApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class FarsiLetter(
    val letter: String,
    val name: String,
    val audioResId: Int,
    val emoji: String,
    val englishName: String,
    val emojiFarsiName: String
)

private val alphabetData = listOf(
    FarsiLetter("ا", "الف", R.raw.m_alif, "👨‍👨‍👧‍👦", "Alif", "آدم"),
    FarsiLetter("ب", "ب", R.raw.baa, "🌧️", "Bay", "باران"),
    FarsiLetter("پ", "پ", R.raw.paye, "🦋", "Pay", "پروانه"),
    FarsiLetter("ت", "ت", R.raw.ta, "⚽️", "Tay", "توپ"),
    FarsiLetter("ث", "ث", R.raw.saya, "🍓", "Say", "ثمر"),
    FarsiLetter("ج", "جیم", R.raw.jim, "🫙", "Jeem", "جار"),
    FarsiLetter("چ", "چه", R.raw.chaye, "☂️", "Chaye", "چتر"),
    FarsiLetter("ح", "ح", R.raw.hay, "🏡", "Hay", "حیاط"),
    FarsiLetter("خ", "خی", R.raw.khaye, "🏠", "Khay", "خانه"),
    FarsiLetter("د", "دال", R.raw.dol, "🦷", "Dol", "دندان"),
    FarsiLetter("ذ", "ذال", R.raw.zol, "🌽", "Zol", "ذرت"),
    FarsiLetter("ر", "ری", R.raw.raye, "🛣️", "Ray", "راه"),
    FarsiLetter("ز", "زی", R.raw.zaye, "🔔", "Zay", "زنگ"),
    FarsiLetter("ژ", "ژی", R.raw.zghe, "🌵", "Zhay", "ژاله"),
    FarsiLetter("س", "سین", R.raw.sin, "🍎", "Sin", "سیب"),
    FarsiLetter("ش", "شین", R.raw.shin, "🍬", "Shin", "شیرینی"),
    FarsiLetter("ص", "صاد", R.raw.swat, "🧼", "Sowt", "صابون"),
    FarsiLetter("ض", "ضاد", R.raw.zwat, "🥊", "Zod", "ضربه"),
    FarsiLetter("ط", "طا", R.raw.toe, "🌳", "Toy", "طبیعت"),
    FarsiLetter("ظ", "ظا", R.raw.zoe, "🏺", "Zoy", "ظرف"),
    FarsiLetter("ع", "عین", R.raw.m_hain, "👓", "Ain", "عینک"),
    FarsiLetter("غ", "غین", R.raw.ghain, "🍇", "Ghain", "غذا"),
    FarsiLetter("ف", "ف", R.raw.faye, "🐘", "Fay", "فیل"),
    FarsiLetter("ق", "قاف", R.raw.qof, "🥄", "Qhaf", "قاشق"),
    FarsiLetter("ک", "کاف", R.raw.kaf, "📖", "Kof", "کتاب"),
    FarsiLetter("گ", "گاف", R.raw.gaf, "🌹", "Gof", "گل"),
    FarsiLetter("ل", "لام", R.raw.lom, "🍋", "Lom", "لیمو"),
    FarsiLetter("م", "میم", R.raw.mim, "🍌", "Mim", "موز"),
    FarsiLetter("ن", "نون", R.raw.non, "🥖", "Non", "نان"),
    FarsiLetter("و", "واو", R.raw.wow, "🏋️", "Wow", "ورزش"),
    FarsiLetter("ه", "ه", R.raw.hamza, "✈️", "Hey", "هواپیما"),
    FarsiLetter("ی", "یا", R.raw.yah, "🧊", "Yah", "یخ")
)

@Composable
fun FarsiAlphabetApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var selectedLetterData by remember { mutableStateOf(alphabetData.first()) }
    val mediaPlayer = remember { MediaPlayer() }

    // State to trigger the animation
    var animationTrigger by remember { mutableStateOf(false) }

    // Animate the scale of the emoji
    val scale by animateFloatAsState(
        targetValue = if (animationTrigger) 1.2f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        ), label = "emojiScaleAnimation"
    )

    // Play audio function
    fun playAudio(audioResId: Int) {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.reset()
            val afd = context.resources.openRawResourceFd(audioResId)
            mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            afd.close()
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            e.printStackTrace() // Log errors
        }
    }

    // Make sure to release the MediaPlayer when the Composable is disposed.
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    // Trigger initial and subsequent audio playback and animation
    LaunchedEffect(selectedLetterData) {
        playAudio(selectedLetterData.audioResId)
        // Trigger the animation
        animationTrigger = true
        // Delay before resetting the animation state
        delay(500L)
        animationTrigger = false
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Main Display Area
            // Main Display Area
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 180.dp)
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                // The Row now correctly holds all its children
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    // Column for the emoji and its new Farsi name
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(120.dp) // Give it some space
                    ) {
                        AnimatedVisibility(
                            visible = selectedLetterData.emoji.isNotEmpty(),
                            enter = fadeIn(),
                            exit = fadeOut(),
                        ) {
                            Text(
                                text = selectedLetterData.emoji,
                                fontSize = 80.sp,
                                modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
                            )
                        }
                        // NEW: Text for the Farsi emoji name
                        Text(
                            text = selectedLetterData.emojiFarsiName,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                    // Farsi Letter
                    Text(
                        text = selectedLetterData.letter,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4C51BF),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.2f),
                                blurRadius = 4f,
                                offset = Offset(2f, 2f)
                            )
                        )
                    )

                    // Spacer for balance
                    Spacer(modifier = Modifier.width(16.dp))

                    // Column for the English and Farsi letter names
                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        horizontalAlignment = Alignment.Start // Align text to the left
                    ) {
                        Text(
                            text = selectedLetterData.englishName,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF6B46C1)
                        )
                        // Use height for vertical spacing in a Column
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = selectedLetterData.name,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF6B46C1)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            // Play Audio Button
            Button(
                onClick = { playAudio(selectedLetterData.audioResId) },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xADE8DEC2))
            ) {
                Text("پخش صدا", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Alphabet Grid
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 64.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(alphabetData) { item ->
                    Card(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable {
                                selectedLetterData = item
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(12.dp),

                        ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = item.letter,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF322659)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FaarsiAlifbaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            FarsiAlphabetApp(modifier = Modifier.padding(innerPadding))
        }
    }
}
