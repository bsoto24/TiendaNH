package com.sunafil.mitienda.feature.maps.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sunafil.mitienda.R
import com.sunafil.mitienda.data.local.preferences.SPHelper
import com.sunafil.mitienda.databinding.ActivityMapsBinding
import com.sunafil.mitienda.feature.maps.domain.Marcador
import com.sunafil.mitienda.feature.products.view.dialog.InformationDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    @Inject
    lateinit var spHelper: SPHelper

    val TAG: String = MapsActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
        initMap()
    }

    private fun initFirebase() {
        val database = Firebase.database //Obtenemos la instancia de la bd de firebase
        val myRef = database.getReference("marcadores") //Referenciamos al campo marcadores en firebase
        //Listener para escuchar los cambios en tiempo real
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) { // Recibimos los cambios si el campo marcadores fue modificado
                snapshot.children.map {
                    it.getValue(Marcador::class.java) // json -> Marcador kotlin
                }.forEach {
                    if (it?.latitud != null && it.longitud != null)
                        addMarker(it.latitud, it.longitud, "Marcador")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
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

    //El mapa esta listo para poder utilizarlo
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        enableMyLocation()
        initListener()
    }

    //Agregar marcador en el mapa
    private fun addMarker(latitud: Double, longitud: Double, title: String) {
        val coordenadas = LatLng(latitud, longitud)
        mMap.addMarker(MarkerOptions().position(coordenadas).title(title))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 5f))
    }

    //Activa la localización del usuario
    private fun enableMyLocation() {
        //verifico si tengo los permisos de ubicacion activados
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true // activamos la localización
        } else {
            //solicita el permiso de localizacion
            ActivityCompat.requestPermissions(
                this@MapsActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                10
            )
        }
    }


    //Recibo la respuesta de permisos, el usuario acepto o rechazo
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