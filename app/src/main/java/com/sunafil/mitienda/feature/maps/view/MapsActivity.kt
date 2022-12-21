package com.sunafil.mitienda.feature.maps.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sunafil.mitienda.R
import com.sunafil.mitienda.data.local.preferences.SPHelper
import com.sunafil.mitienda.databinding.ActivityMapsBinding
import com.sunafil.mitienda.feature.products.view.dialog.InformationDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    @Inject
    lateinit var spHelper: SPHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMap()
    }

    private fun initListener() {
        binding.btnMarcador.setOnClickListener {
            val contador = spHelper.readInt(SPHelper.PREF_CONTADOR) + 1
            spHelper.saveInt(SPHelper.PREF_CONTADOR, contador)
            addMarker(
                binding.edtLatitud.text.toString().toDouble(),
                binding.edtLongitud.text.toString().toDouble(),
                "Tienda $contador"
            )
        }
        mMap.setOnMarkerClickListener {
            val dialog = InformationDialogFragment()
            dialog.arguments = Bundle().apply {
                putString("title", it.title)
                putDouble("latitude", it.position.latitude)
                putDouble("longitude", it.position.longitude)
            }
            supportFragmentManager
                .beginTransaction()
                .add(dialog, InformationDialogFragment::class.java.name)
                .commit()
            true
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        enableMyLocation()
        initListener()
        addMarker(-34.0, 151.0, "Marcador en Sydney")
    }

    private fun addMarker(latitud: Double, longitud: Double, title: String) {
        val coordenadas = LatLng(latitud, longitud)
        mMap.addMarker(MarkerOptions().position(coordenadas).title(title))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 5f))
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this@MapsActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                10
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation()
        }
    }

}