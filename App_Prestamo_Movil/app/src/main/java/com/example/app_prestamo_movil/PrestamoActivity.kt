package com.example.app_prestamo_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class PrestamoActivity : AppCompatActivity() {
    lateinit var credito: TextInputEditText
    lateinit var interes: TextInputEditText
    lateinit var cuota: TextInputEditText
    lateinit var totalPagar: TextInputEditText
    lateinit var primer: TextInputEditText
    lateinit var tipoPag: TextInputEditText
    lateinit var aceptar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prestamo)
        credito = findViewById(R.id.valorPrest);
        interes = findViewById(R.id.interesP);
        cuota = findViewById(R.id.cuota);
        totalPagar = findViewById(R.id.totPagar);
        primer = findViewById(R.id.primPago);
        tipoPag = findViewById(R.id.tipPago);
        aceptar = findViewById(R.id.guardarPrestamo);

        aceptar.setOnClickListener {

        }
    }
}