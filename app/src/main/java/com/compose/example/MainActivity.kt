package com.compose.example

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.compose.example.ui.theme.NewComposeMapTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.ButtCap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


private val singapore = LatLng(1.35, 103.87)
private val singapore2 = LatLng(2.50, 103.87)
private val TAG = MainActivity::class.simpleName

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewComposeMapTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // 1. Add a map to your app
                    // * https://www.boltuix.com/2022/11/integrate-google-maps-into-jetpack.html
                    //ComposeMapDemo()



                    // 2. Add a markers to your app
                    // * https://www.boltuix.com/2022/11/add-marker-to-google-maps-in-android.html
                    //ComposeMapDemoMarkers()



                    // 3. Set properties on a map
                    // https://www.boltuix.com/2022/11/set-properties-on-map-in-android-using.html
                    //ComposeMapPropertiesDemo()
                    // 3b. Controlling a map's camera
                   //ComposeMapControllingCamera()



                    // 4. Customizing a marker icon
                    //https://www.boltuix.com/2022/11/add-custom-marker-to-google-maps-in.html
                    // ComposeMapMapMarker()

                    // 5. Customizing a marker's info window
                    //https://www.boltuix.com/2022/11/custom-info-window-on-map-marker-clicks.html
                  // ComposeMapCustomizingMarker()


                    //6. https://www.boltuix.com/2022/11/draw-polylines-on-google-maps-in.html
                    // ComposeMapMapPolyline()
                    // https://www.boltuix.com/2022/11/draw-polylines-on-google-maps-in.html
                    // ComposeMapDrawTrack() // no api key required //to Draw Track on Google Maps


                    // 7. Center point
                    ComposeMapCenterPointMapMarker()


                }
            }
        }
    }
}



//https://www.google.com/maps/place/London,+UK/@51.5287352,-0.3817854,10z/data=!3m1!4b1!4m5!3m4!1s0x47d8a00baf21de75:0x52963a5addd52a99!8m2!3d51.5072178!4d-0.1275862
@Composable
fun ComposeMapDemo() {
    val singapore = LatLng(1.3554117053046808, 103.86454252780209)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )

    }
}



@Composable
fun ComposeMapDemoMarkers() {
    val singapore = LatLng(1.3554117053046808, 103.86454252780209)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 30f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = singapore),
            draggable = true,
            title = "Marker 1",
            snippet = "Marker in Singapore",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        )
    }
}






@Composable
fun ComposeMapPropertiesDemo() {
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings
        )
        Switch(
            checked = uiSettings.zoomControlsEnabled,
            onCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
            }
        )
    }
}
@Composable
fun ComposeMapPropertiesDemo2() {
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.SATELLITE)) }

    Box(Modifier.fillMaxSize()) {

        uiSettings = uiSettings.copy(zoomControlsEnabled = false)

        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings
        )
    }
}



/*
* Customizing a marker's info window
You can customize a marker's info window contents by using the MarkerInfoWindowContent element, or if you want to customize the entire info window, use the MarkerInfoWindow element instead. Both of these elements accept a content parameter to provide your customization in a composable lambda expression.*/
@Composable
fun ComposeDemoApp3() {


    val london = LatLng(51.52061810406676, -0.12635325270312533)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(london, 10f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            ){
            val icon = bitmapDescriptorFromVector(
                LocalContext.current, R.drawable.pin
            )
            MarkerInfoWindow(
                state = MarkerState(position = london),
                icon = icon,
            ) { marker ->
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                        )
                    ,
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.map),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),

                        )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                        //.........................Text: title
                        Text(
                            text = "Marker Title",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 60.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        //.........................Text : description
                        Text(
                            text = "Customizing a marker's info window",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                    }

                }

            }
        }
    }}

/*
* Customizing a marker's info window
You can customize a marker's info window contents by using the MarkerInfoWindowContent element, or if you want to customize the entire info window, use the MarkerInfoWindow element instead. Both of these elements accept a content parameter to provide your customization in a composable lambda expression.*/
@Composable
fun ComposeMapCustomizingMarker() {
    val london = LatLng(51.52061810406676, -0.12635325270312533)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(london, 10f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
        ){
            val icon = bitmapDescriptorFromVector(
                LocalContext.current, R.drawable.pin
            )
            MarkerInfoWindow(
                state = MarkerState(position = london),
                icon = icon,
            ) { marker ->
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                        )
                    ,
                ) {


                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.map),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth(),

                            )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))
                        //.........................Text: title
                        Text(
                            text = "Marker Title",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        //.........................Text : description
                        Text(
                            text = "Customizing a marker's info window",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                    }

                }

            }
        }
    }}

//Controlling a map's camera
@Composable
fun ComposeMapControllingCamera() {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState){
        }
        Button(onClick = {
            // Move the camera to a new zoom level
            cameraPositionState.move(CameraUpdateFactory.zoomIn())
        }) {
            Text(text = "Zoom In")
        }
    }
}


//..................................................................................
@Composable
fun ComposeMapMapMarker() {
    val singapore = LatLng(1.35, 103.87)
    val singapore2 = LatLng(2.50, 103.87)

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState){
            MapMarker(
                position = singapore,
                title = "Your Title 1",
                context = LocalContext.current,
                iconResourceId = R.drawable.pin
            )
            MapMarker(
                position = singapore2,
                title = "Your Title 2",
                context = LocalContext.current,
                iconResourceId = R.drawable.pin
            )
        }
    }
}
@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    title: String,
    @DrawableRes iconResourceId: Int
) {
    val icon = bitmapDescriptorFromVector(
        context, iconResourceId
    )
    Marker(
        state = MarkerState(position = position),
        title = title,
        icon = icon,
    )
}
fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}
//.................................................................................


@Composable
fun ComposeMapCenterPointMapMarker() {
    val markerPosition = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(markerPosition, 18f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pin2),
                    contentDescription = "marker",
                )
            }
            Text(text = "Is camera moving: ${cameraPositionState.isMoving}" +
                    "\n Latitude and Longitude: ${cameraPositionState.position.target.latitude} " +
                    "and ${cameraPositionState.position.target.longitude}",
                textAlign = TextAlign.Center
            )
        }
}

//.............................................................................


@Composable
fun ComposeMapDrawTrack() {
    val context = LocalContext.current
    Box(Modifier.fillMaxSize()) {
        Button(onClick = {
            //source location to the destination location
            drawTrack("Louisville", "old louisville", context)
        }) {
            Text(text = "Google map Draw Track")
        }
    }
}

private fun drawTrack(source: String, destination: String, context: Context) {
    try {
        // create a uri
        val uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/$source/$destination")
        // initializing a intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // below line is to set maps package name
        i.setPackage("com.google.android.apps.maps")
        // below line is to set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // start activity
        context.startActivity(i)
    } catch (e: ActivityNotFoundException) {
        // when the google maps is not installed on users device
        // we will redirect our user to google play to download google maps.
        val uri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
        // initializing intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // to start activity
        context.startActivity(i)
    }
}



@Composable
fun ComposeMapMapPolyline() {
    val singapore = LatLng(44.811058, 20.4617586)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState){
            MapMarker(
                position = singapore,
                title = "Your Title",
                context = LocalContext.current,
                iconResourceId = R.drawable.pin
            )

            Polyline(
                points = listOf(
                    LatLng(44.811058, 20.4617586),
                    LatLng(44.811058, 20.4627586),
                    LatLng(44.810058, 20.4627586),
                    LatLng(44.809058, 20.4627586),
                    LatLng(44.809058, 20.4617586)
                )
                ,color = Color.Magenta
            )
        }
    }
}



