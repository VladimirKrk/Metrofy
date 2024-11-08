package com.example.metrofy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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

    // Original dimensions of the image
    val originalImageWidth = 3039f
    val originalImageHeight = 5100f


    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 7f) // Limit the scale between 1x and 7x

                    // Update offset based on panning
                    offsetX += pan.x
                    offsetY += pan.y

                    // Calculate scaled dimensions
                    val scaledImageWidth = originalImageWidth * scale
                    val scaledImageHeight = originalImageHeight * scale

                    // Clamp the offsets after updating them
                    offsetX = offsetX.coerceIn(
                        -(scaledImageWidth - originalImageWidth) / 2, // Left bound
                        (scaledImageWidth - originalImageWidth) / 2   // Right bound
                    )


                    offsetY = offsetY.coerceIn(
                        -(scaledImageHeight - originalImageHeight) / 2, // Top bound
                        (scaledImageHeight - originalImageHeight) / 2   // Bottom bound
                    )
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
