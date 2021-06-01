package com.example.app_prestamo_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class NewClienteActivity : AppCompatActivity() {
    lateinit var nombre: TextInputEditText
    lateinit var telefono: TextInputEditText
    lateinit var correo: TextInputEditText
    lateinit var direccion: TextInputEditText
    lateinit var imagen: TextInputEditText
    lateinit var guardar: Button
    lateinit var limpiar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cliente)
        nombre = findViewById(R.id.nomCliente);
        guardar = findViewById(R.id.guardarCli);
        limpiar = findViewById(R.id.limpiarCli);
        telefono = findViewById(R.id.telCliente);
        correo = findViewById(R.id.corCliente);
        direccion = findViewById(R.id.dirClien);
        imagen = findViewById(R.id.imagenCli);
        guardar.setOnClickListener{
            agregarCliente();
        }

        limpiar.setOnClickListener {
            nombre.setText("");
            telefono.setText("");
            correo.setText("");
            direccion.setText("");
        }
    }
    fun agregarCliente() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val cliente = ClienteEntity(
            myRef.push().key.toString(),
            nombre.text.toString(),
            telefono.text.toString(),
            correo.text.toString(),
            direccion.text.toString(),
            imagen.text.toString()
        )
        myRef.child("clientes").child(cliente.id).setValue(cliente)
        finish()
    }


}