package si.um.feri.mapboxexample

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.eq
import com.mapbox.maps.extension.style.image.image
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.extension.style.layers.generated.lineLayer
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.extension.style.sources.addSource
import com.mapbox.maps.extension.style.sources.generated.GeoJsonSource
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.sources.getSourceAs
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.logE
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import si.um.feri.mapboxexample.databinding.ActivityMainBinding
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mapView: MapView
    private val points = mutableListOf<Point>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mapView = binding.mapView
        mapView.mapboxMap.loadStyle(Style.SATELLITE_STREETS) { style ->
            mapView.getMapboxMap().addOnMapClickListener { point ->
                addMarker(point, style)
                points.add(point)
                drawLine(style)
                true
            }
        }

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
        binding.satelliteButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.SATELLITE)
        }
        binding.outdoorsButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.OUTDOORS)
        }
        binding.standardButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.STANDARD)
        }
        binding.standardSatelliteButton.setOnClickListener {
            mapView.mapboxMap.loadStyle(Style.STANDARD_SATELLITE)
        }
    }

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
        } else {
            val source = style.getSourceAs<GeoJsonSource>("marker-source-id")
            source?.geometry(point)
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
        } else {
            val source = style.getSourceAs<GeoJsonSource>("line-source-id")
            source?.geometry(com.mapbox.geojson.LineString.fromLngLats(points))
        }
    }

    companion object {
        private const val SOURCE = "TERRAIN_SOURCE"
        private const val SKY_LAYER = "sky"
        private const val TERRAIN_URL_TILE_RESOURCE = "mapbox://mapbox.mapbox-terrain-dem-v1"
    }
}