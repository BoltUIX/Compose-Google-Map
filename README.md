# Compose-Google-Map 🚀

A Jetpack Compose library for integrating and customizing **Google Maps** in Android applications. This project demonstrates various map features using the `google-maps-android-compose` library, providing a modern, declarative approach to map integration.

![Google Maps in Jetpack Compose](https://miro.medium.com/max/1400/1*ArNSw16ppJ3YIri-vGeIRQ.webp)



[![Watch the playlist](https://github.com/user-attachments/assets/508bc620-3eb5-4321-98fa-685235e4866e)](https://www.youtube.com/playlist?list=PLEpOsyzAgs48RxAcw6QEdqFVZCrXU5ZuC)



## Features ✨

- **Basic Map Integration** 🗺️: Display a Google Map centered at a specific location (e.g., Singapore).
- **Markers** 📍: Add markers with titles, snippets, and drag functionality.
- **Custom Marker Icons** 🎨: Use custom vector drawables for markers.
- **Custom Info Windows** ℹ️: Create styled info windows with images and text.
- **Map Properties** ⚙️: Customize map type (Normal, Satellite, etc.) and UI settings (e.g., zoom controls).
- **Camera Control** 🎥: Programmatically control map zoom and position.
- **Polylines** 🟣: Draw lines between coordinates to represent routes.
- **Circles** ⭕: Highlight areas with customizable circles.
- **External Navigation** 🧭: Open Google Maps app for route navigation.
- **Centered Marker** 🎯: Place a marker at the screen's center with camera movement info.

## Getting Started 🛠️

### Prerequisites
- Android Studio with Jetpack Compose support.
- Google Maps SDK for Android.
- **Google Maps API Key** 🔑: Obtain from [Google Cloud Console](https://console.cloud.google.com/) and add to your project.

> **Note**: Replace `YOUR_GOOGLE_API_KEY` in `AndroidManifest.xml` with your actual API key.

### Installation
1. Add the Google Maps SDK and Compose dependencies to your `build.gradle`:
   ```gradle
   implementation "com.google.maps.android:maps-compose:6.1.0"
   implementation "com.google.android.gms:play-services-maps:19.0.0"
   implementation "androidx.compose.material3:material3:1.3.0"
   ```
2. Add your API key to `AndroidManifest.xml`:
   ```xml
   <meta-data
       android:name="com.google.android.geo.API_KEY"
       android:value="YOUR_GOOGLE_API_KEY"/>
   ```
3. Sync your project and start using the composables.

## Usage Examples 📚

### 1. Basic Map
Display a map centered at Singapore with a marker:
```kotlin
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
```

### 2. Custom Marker with Info Window
Add a custom marker with a styled info window:
```kotlin
@Composable
fun ComposeMapCustomizingMarker() {
    val london = LatLng(51.52061810406676, -0.12635325270312533)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(london, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        val icon = bitmapDescriptorFromVector(LocalContext.current, R.drawable.pin)
        MarkerInfoWindow(
            state = MarkerState(position = london),
            icon = icon
        ) {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(35.dp)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.map), ...)
                    Text(text = "Marker Title", ...)
                    Text(text = "Customizing a marker's info window", ...)
                }
            }
        }
    }
}
```

### 3. Draw Polyline
Draw a route between coordinates:
```kotlin
@Composable
fun ComposeMapMapPolyline() {
    val singapore = LatLng(44.811058, 20.4617586)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Polyline(
            points = listOf(
                LatLng(44.811058, 20.4617586),
                LatLng(44.811058, 20.4627586),
                ...
            ),
            color = Color.Magenta
        )
    }
}
```

## Feature Comparison Table 📊

| Feature                  | Description                                      | Pros                              | Cons                           |
|--------------------------|--------------------------------------------------|-----------------------------------|-------------------------------- |
| Basic Map                | Simple map with a marker                         | Easy to implement                | Limited customization            |
| Draggable Marker         | Marker that can be moved                         | Interactive                      | Basic appearance                 |
| Custom Marker Icon       | Custom vector drawable for markers               | Visually appealing               | Requires drawable resources      |
| Custom Info Window       | Styled info window with images/text              | Highly customizable              | Complex composable structure     |
| Polylines                | Draw routes between coordinates                  | Useful for navigation            | Limited styling options          |
| Camera Control           | Programmatic zoom/position control               | Dynamic user experience          | Requires additional logic        |
| Centered Marker          | Marker fixed at screen center                    | Clear focus on location          | Limited interactivity            |
| External Navigation      | Opens Google Maps app for routes                 | Seamless integration             | Requires Google Maps app         |
| Advanced Map (Circle, UI)| Combines markers, circles, and controls          | Feature-rich                     | Complex implementation           |

## References 📖

- [Integrate Google Maps Into Jetpack Compose](https://www.boltuix.com/2022/11/integrate-google-maps-into-jetpack.html)
- [Add Marker to Google Maps](https://www.boltuix.com/2022/11/add-marker-to-google-maps-in-android.html)
- [Set Map Properties](https://www.boltuix.com/2022/11/set-properties-on-map-in-android-using.html)
- [Custom Marker](https://www.boltuix.com/2022/11/add-custom-marker-to-google-maps-in.html)
- [Custom Info Window](https://www.boltuix.com/2022/11/custom-info-window-on-map-marker-clicks.html)
- [Draw Polylines](https://www.boltuix.com/2022/11/draw-polylines-on-google-maps-in.html)
- [Centered Marker](https://www.boltuix.com/2022/11/place-marker-on-center-of-screen-on.html)

## Tags 🏷️
#JetpackCompose #GoogleMaps #AndroidDevelopment #Kotlin #MapsSDK #UI #Navigation #Markers #Polylines #CustomUI

## Contributing 🤝
Contributions are welcome! Please submit a pull request or open an issue for suggestions.

## License 📜
This project is licensed under the MIT License.
