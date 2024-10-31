package com.example.metrofy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.example.metrofy.ui.theme.MetrofyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MetrofyTheme {
                MetroMapScreen()
            }
        }
    }
}

@Composable
fun MetroMapScreen() {
    val metroMapImage: Painter = painterResource(id = R.drawable.metro_map)

    // State variables to track scale and translation (offset)
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTransformGestures { _, pan, zoom, _ ->
                // Update scale and offset based on user gestures
                scale = (scale * zoom).coerceIn(1f, 7f) // Limit the scale between 1x and 5x
                offsetX += pan.x
                offsetY += pan.y
            }
        }
    ) {
        Image(
            painter = metroMapImage,
            contentDescription = "Moscow Metro Map",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMetroMapScreen() {
    MetrofyTheme {
        MetroMapScreen()
    }
}
