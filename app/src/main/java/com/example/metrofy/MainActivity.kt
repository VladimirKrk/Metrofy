package com.example.metrofy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.metrofy.model.MetroStation
import com.example.metrofy.ui.theme.MetrofyTheme
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.platform.LocalContext

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
    val metroMapImage = painterResource(id = R.drawable.metro_map)

    // State variables to track scale and translation (offset)
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    // Get the list of stations from MetroStation
    val stations = MetroStation.getStations()

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTransformGestures { _, pan, zoom, _ ->
                scale = (scale * zoom).coerceIn(1f, 7f) // Limit the scale between 1x and 7x
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

        // Add station markers to the map
        stations.forEach { station ->
            StationMarker(station, scale, offsetX, offsetY)
        }
    }
}


@Composable
fun StationMarker(station: MetroStation, scale: Float, offsetX: Float, offsetY: Float) {
    val context = LocalContext.current

    // Adjust the position of the station marker according to scale and translation
    val adjustedX = station.position.x * scale + offsetX
    val adjustedY = station.position.y * scale + offsetY

    Box(
        modifier = Modifier
            .offset(x = adjustedX.dp, y = adjustedY.dp)
            .clickable {
                // Action when the station is clicked
                Toast.makeText(context, "Clicked on ${station.name}", Toast.LENGTH_SHORT).show()
            }
            .size(20.dp * scale) // Size of clickable area, adjusting with scale
            .background(Color.Red.copy(alpha = 0.5f)) // For visibility; remove after testing
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMetroMapScreen() {
    MetrofyTheme {
        MetroMapScreen()
    }
}
