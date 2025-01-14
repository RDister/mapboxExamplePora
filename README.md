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
    <string name="mapbox_access_token" translatable="false" tools:ignore="UnusedResources">pk.eyJ1IjoibWF0ZWpyYWp0ZXIiLCJhIjoiY201d3JtdHRvMDEyZTJrc2NpMG1pbTZiZiJ9.PO1fUDOu46ATQ_zbZ9T38w</string>
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
### Dovoljenja
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
![image](https://github.com/user-attachments/assets/811ad083-3426-4511-9507-2ff8cb8f95d8)

