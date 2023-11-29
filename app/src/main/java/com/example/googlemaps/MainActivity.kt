package com.example.googlemaps

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.googlemaps.ui.theme.GoogleMapsTheme
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyMapScreen()
                }
            }
        }
    }
}

@Composable
fun MapViewContainer(onMapReadyCallback: OnMapReadyCallback, context: Context) {
    AndroidView(
        factory = {
            MapView(context).apply {
                getMapAsync(onMapReadyCallback)
            }
        },
        update = { mapView ->
            mapView.getMapAsync(onMapReadyCallback)
        }
    )
}

@Composable
fun MyMapScreen() {
    MapViewContainer(onMapReadyCallback = { googleMap ->
        initializeMap(googleMap)
    }, context = LocalContext.current)
}

private fun initializeMap(googleMap: GoogleMap) {
    // Define the coordinates for a specific location (e.g., Sydney, Australia)
    val location = LatLng( 47.142071,  8.432456)

    // Move the camera to the specified location and set the zoom level
    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))

    // Add a marker at the location
    googleMap.addMarker(MarkerOptions().position(location).title("Marker in Rotkreuz"))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoogleMapsTheme {
        MyMapScreen()
    }
}