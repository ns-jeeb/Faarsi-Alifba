package com.jeeb.farsialifba
import android.media.MediaPlayer
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivitykt : ComponentActivity() {
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

// ========== FIX #1: Change var to val for immutable data ==========
data class FarsiLetter(val letter: String, val name: String, val audioResId: Int)
@Composable
fun FarsiAlphabetApp(modifier: Modifier = Modifier) {
    val alphabetData = listOf(
        FarsiLetter("آ", "الف", R.raw.alif),
        FarsiLetter("ا", "الف", R.raw.alif),
        FarsiLetter("ب", "ب", R.raw.baa),
        FarsiLetter("پ", "پ", R.raw.paye),
        FarsiLetter("ت", "ت", R.raw.ta),
        FarsiLetter("ث", "ث", R.raw.saya),
        FarsiLetter("ج", "جیم", R.raw.jim),
        FarsiLetter("چ", "چ", R.raw.chaye),
        FarsiLetter("ح", "ح", R.raw.hay),
        FarsiLetter("خ", "خ", R.raw.khaye),
        FarsiLetter("د", "دال", R.raw.dol),
        FarsiLetter("ذ", "ذال", R.raw.zol),
        FarsiLetter("ر", "ر", R.raw.raye),
        FarsiLetter("ز", "ز", R.raw.zaye),
        FarsiLetter("ژ", "ژ", R.raw.zghe),
        FarsiLetter("س", "سین", R.raw.sin),
        FarsiLetter("ش", "شین", R.raw.shin),
        FarsiLetter("ص", "صاد", R.raw.swat),
        FarsiLetter("ض", "ضاد", R.raw.zwat),
        FarsiLetter("ط", "طا", R.raw.toe),
        FarsiLetter("ظ", "ظا", R.raw.zoe),
        FarsiLetter("ع", "عین", R.raw.m_hain),
        FarsiLetter("غ", "غین", R.raw.ghain),
        FarsiLetter("ف", "ف", R.raw.faye),
        FarsiLetter("ق", "قاف", R.raw.qof),
        FarsiLetter("ک", "کاف", R.raw.kaf),
        FarsiLetter("گ", "گاف", R.raw.gaf),
        FarsiLetter("ل", "لام", R.raw.lom),
        FarsiLetter("م", "میم", R.raw.mim),
        FarsiLetter("ن", "نون", R.raw.non),
        FarsiLetter("و", "واو", R.raw.wow),
        FarsiLetter("ه", "ه", R.raw.hamza),
        FarsiLetter("ی", "ی", R.raw.yah)
    )
    val context = LocalContext.current
    var selectedLetterData by remember { mutableStateOf(alphabetData.first()) }

    val mediaPlayer = remember { MediaPlayer() }

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

    // Initial audio playback for the first letter
    LaunchedEffect(Unit) {
        playAudio(selectedLetterData.audioResId)
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
            // ====================== THE FIX ======================
            // The .fillMaxSize() modifier has been removed from this Column.
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // =====================================================
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

            Text("پخش صدا", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.background(Color(0xFF6B46C1)))



        Spacer(modifier = Modifier.height(24.dp))

        // Alphabet Grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 64.dp),
            modifier = Modifier
                .weight(1f) // This allows the grid to take the remaining space
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
                            playAudio(item.audioResId)
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