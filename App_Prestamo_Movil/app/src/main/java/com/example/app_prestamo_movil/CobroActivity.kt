package com.example.app_prestamo_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class CobroActivity : AppCompatActivity() {
    lateinit var concepto: TextInputEditText
    lateinit var nameCliente: TextView
    lateinit var monto: TextInputEditText
    lateinit var deuda: TextInputEditText
    lateinit var restante: TextInputEditText
    lateinit var proximo: TextInputEditText
    lateinit var tipoPago: TextInputEditText
    lateinit var confirmar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro)
        nameCliente = findViewById(R.id.nameClienteDeuda);
        concepto = findViewById(R.id.textConcepto);
        monto = findViewById(R.id.textMonto);
        deuda = findViewById(R.id.textDeuda);
        restante = findViewById(R.id.textRestante);
        proximo = findViewById(R.id.textProximo);
        tipoPago = findViewById(R.id.textTipo);
        confirmar = findViewById(R.id.guardarCobro);
        val extras = intent.extras
        val s = extras?.getString("nombre") ?: "Sin Nombre"
        nameCliente.text = s.toString()
        val d = extras?.getString("tipopago") ?: "Sin Tipo"
        tipoPago.setText(d.toString())

        confirmar.setOnClickListener {

        }
    }
}