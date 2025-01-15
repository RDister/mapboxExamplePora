# MapBox for Android 🗺️

## Zakaj? 🤨
[Mapbox](https://www.mapbox.com/) je platforma za geolokacijske storitve, ki omogoča integracijo interaktivnih zemljevidov, navigacije, analitike in geolokacijskih funkcionalnosti v mobilne, spletne in namizne aplikacije. Poleg prilagodljivih zemljevidov Mapbox ponuja orodja za delo z različnimi vrstami podatkov ter vizualizacijo in analizo geografskih informacij.

## Prednosti ✔️
- Mapbox omogoča **popolno prilagoditev** zemljevidov, vključno z barvami, sloji in stili.
- Ponuja SDK-je za **različne platforme**, kot so Android, iOS, JavaScript in Unity.
- Vključuje **napredne funkcionalnosti**, kot so 3D-zemljevidi, animacije in toplotne karte.
- Omogoča **sledenje v realnem času**, kar je idealno za dostavne storitve in športne aplikacije.
- Podpira **shranjevanje zemljevidov** za uporabo brez internetne povezave.
- Ima **aktivno skupnost razvijalcev** in dobro dokumentacijo za podporo.

## Slabosti ❌
- **Stroški uporabe** Mapboxa se povečajo z večjim številom uporabnikov ali zahtev.
- Večina funkcij **zahteva internetno povezavo**, kar omejuje uporabo na terenu.
- **Napredne funkcionalnosti** so za začetnike lahko zahtevne za implementacijo.

## Licenca 📜
osmdroid je licenciran pod Apache License 2.0 - odprtokodna licenca, ki omogoča:

- Uporabo, spreminjanje in distribucijo programske opreme brez omejitev.
- Uporabo za osebne, izobraževalne ali komercialne namene.
- Vključitev programske opreme v zaprte (proprietarne) projekte.

## Število zvezdic, sledilcev, forkov 🌟, 🍴
![GitHub stars](https://img.shields.io/github/stars/mapbox/mapbox-maps-android?style=social)

![GitHub forks](https://img.shields.io/github/forks/mapbox/mapbox-maps-android?style=social)

![GitHub watchers](https://img.shields.io/github/watchers/mapbox/mapbox-maps-android?style=social)


## Vzdrževanje projetka ⚒️
Zadnji commit je bil 18. decembra 2024
![Forks](https://img.shields.io/badge/last%20commit-december%202024%20-blue)

![Followers](https://img.shields.io/badge/commit%20activity-2/month%20-green)

![Followers](https://img.shields.io/badge/contributors-45%20-blue)

## Primer uporabe
### Predpogoji
- Mapbox račun
- Naložen android Studio
### Konfiguriranje svojega javnega žetona
Ustvarimo novo 
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <string name="mapbox_access_token" translatable="false" tools:ignore="UnusedResources">PUBLIC_TOKEN</string>
</resources>
```
### Dovoljenja
V `AndroidManifest.xml` dodamo potrebna dovoljenja
```xml
<!-- Include this permission to grab user's general location -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<!-- Include only if your app benefits from precise location access. -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```
### Odvisnosti
V `settings.gradle.kts` dodamo 
```kts
// Mapbox Maven repository
maven {
url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
}
```
in v `build.gradle.kts` dodamo še
```kts
dependencies {
  implementation("com.mapbox.maps:android:11.9.0")
}
```
### Primer uporabe zemljevida v aplikaciji
```xml
<com.mapbox.maps.MapView
        android:id="@+id/mapView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:mapbox_cameraTargetLat="39.5"
        app:mapbox_cameraTargetLng="-98.0"
        app:mapbox_cameraZoom="2.0"
        app:mapbox_cameraPitch="0.0"
        app:mapbox_cameraBearing="0.0" />
```
### Izbiranje stila zemljevida
```kt
binding.streetsButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.MAPBOX_STREETS)
        }
        binding.lightButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.LIGHT)
        }
        binding.darkButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.DARK)
        }
        binding.satelliteStreetsButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.SATELLITE_STREETS)
        }
```
![image](https://github.com/user-attachments/assets/811ad083-3426-4511-9507-2ff8cb8f95d8)![image](https://github.com/user-attachments/assets/f4ca4614-dad6-4487-b858-62eeee84f37d)![image](https://github.com/user-attachments/assets/4e186f93-984f-4fa8-9b0d-5ed823fe1aa9)

### Dodajanje točk na zemljevidu
```kt
private fun addMarker(point: Point, style: Style) {
        val drawable = ContextCompat.getDrawable(this, R.drawable.red_marker)!!
        val originalBitmap = drawable.toBitmap()
        val aspectRatio = originalBitmap.width.toFloat() / originalBitmap.height.toFloat()
        val width = 50 // Desired width
        val height = (width / aspectRatio).toInt() // Calculate height to maintain aspect ratio
        val bitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false)

        if (!style.styleSourceExists("marker-source-id")) {
            style.addImage("marker-icon-id", bitmap)
            style.addSource(
                geoJsonSource("marker-source-id") {
                    geometry(point)
                }
            )
            style.addLayer(
                symbolLayer("marker-layer-id", "marker-source-id") {
                    iconImage("marker-icon-id")
                    iconAnchor(IconAnchor.BOTTOM)
                }
            )
        }
    }

    private fun drawLine(style: Style) {
        if (!style.styleSourceExists("line-source-id")) {
            style.addSource(
                geoJsonSource("line-source-id") {
                    geometry(com.mapbox.geojson.LineString.fromLngLats(points))
                }
            )
            style.addLayer(
                lineLayer("line-layer-id", "line-source-id") {
                    lineColor(Color.RED)
                    lineWidth(2.0)
                }
            )
        }
    }
```
![ezgif-1-87555676e5](https://github.com/user-attachments/assets/33eb2f4c-9842-43f3-90bd-987f770a821d)

### Integracija v aplikaciji
![ezgif-1-d3cd084b44](https://github.com/user-attachments/assets/6114f8da-a35c-48db-bb69-73723e84b691)




