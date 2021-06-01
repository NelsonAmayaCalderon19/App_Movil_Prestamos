package com.example.app_prestamo_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CobroActivity : AppCompatActivity() {
    lateinit var concepto: TextInputEditText
    lateinit var monto: TextInputEditText
    lateinit var deuda: TextInputEditText
    lateinit var restante: TextInputEditText
    lateinit var proximo: TextInputEditText
    lateinit var tipoPago: TextInputEditText
    lateinit var confirmar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro)
        concepto = findViewById(R.id.textConcepto);
        monto = findViewById(R.id.textMonto);
        deuda = findViewById(R.id.textDeuda);
        restante = findViewById(R.id.textRestante);
        proximo = findViewById(R.id.textProximo);
        tipoPago = findViewById(R.id.textTipo);
        confirmar = findViewById(R.id.guardarCobro);

        confirmar.setOnClickListener {

        }
    }
}