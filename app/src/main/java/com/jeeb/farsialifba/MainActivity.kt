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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
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

data class FarsiLetter(val letter: String, val name: String, val audioResId: Int, val emoji: String)
private val alphabetData = listOf(
    FarsiLetter("آ", "الف", R.raw.alif, "💧"),
    FarsiLetter("ا", "الف", R.raw.alif, "👨‍👨‍👧‍👦"),
    FarsiLetter("ب", "ب", R.raw.baa, "☔️"),
    FarsiLetter("پ", "پ", R.raw.paye, "🦋"),
    FarsiLetter("ت", "ت", R.raw.ta, "⚽️"),
    FarsiLetter("ث", "ث", R.raw.saya, "🍓"),
    FarsiLetter("ج", "جیم", R.raw.jim, "🫙"),
    FarsiLetter("چ", "چ", R.raw.chaye, "☂️"),
    FarsiLetter("ح", "ح", R.raw.hay, "🏡"),
    FarsiLetter("خ", "خ", R.raw.khaye, "🏡"),
    FarsiLetter("د", "دال", R.raw.dol, "🦷"),
    FarsiLetter("ذ", "ذال", R.raw.zol, "🌽"),
    FarsiLetter("ر", "ر", R.raw.raye, "🚗"),
    FarsiLetter("ز", "ز", R.raw.zaye, "🔔"),
    FarsiLetter("ژ", "ژ", R.raw.zghe, "🌵"),
    FarsiLetter("س", "سین", R.raw.sin, "🍎"),
    FarsiLetter("ش", "شین", R.raw.shin, "🍬"),
    FarsiLetter("ص", "صاد", R.raw.swat, "🥣"),
    FarsiLetter("ض", "ضاد", R.raw.zwat, "🫕"),
    FarsiLetter("ط", "طا", R.raw.toe, "🌳"),
    FarsiLetter("ظ", "ظا", R.raw.zoe, "🧪"),
    FarsiLetter("ع", "عین", R.raw.m_hain, "🦉"),
    FarsiLetter("غ", "غین", R.raw.ghain, "🍇"),
    FarsiLetter("ف", "ف", R.raw.faye, "🧸"),
    FarsiLetter("ق", "قاف", R.raw.qof, "🐥"),
    FarsiLetter("ک", "کاف", R.raw.kaf, "👑"),
    FarsiLetter("گ", "گاف", R.raw.gaf, "🥎"),
    FarsiLetter("ل", "لام", R.raw.lom, "🍋"),
    FarsiLetter("م", "میم", R.raw.mim, "🍌"),
    FarsiLetter("ن", "نون", R.raw.non, "🥖"),
    FarsiLetter("و", "واو", R.raw.wow, "🧶"),
    FarsiLetter("ه", "ه", R.raw.hamza, "📱"),
    FarsiLetter("ی", "ی", R.raw.yah, "🧊")
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

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main Display Area
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Main animated emoji
                AnimatedVisibility(
                    visible = selectedLetterData.emoji.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.size(150.dp).graphicsLayer(scaleX = scale, scaleY = scale)
                ) {
                    Text(
                        text = selectedLetterData.emoji,
                        fontSize = 100.sp
                    )
                }

                // Letter and name
                Text(
                    text = selectedLetterData.letter,
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4C51BF),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.2f),
                            blurRadius = 4f,
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f)
                        )
                    )
                )
                Text(
                    text = selectedLetterData.name,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6B46C1),
                    modifier = Modifier.padding(top = 8.dp)
                )
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
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B46C1))
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
                    shape = RoundedCornerShape(12.dp)
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
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FaarsiAlifbaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            FarsiAlphabetApp(modifier = Modifier.padding(innerPadding))
        }
    }
}
