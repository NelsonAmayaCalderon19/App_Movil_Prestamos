package com.example.app_prestamo_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class NewPassActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    val TAG:  String = "NewPassActivity";
    lateinit var pass: TextInputEditText
    lateinit var rep_pass: TextInputEditText
    lateinit var actualizar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pass)
        auth = FirebaseAuth.getInstance();
        pass = findViewById(R.id.pass);
        rep_pass = findViewById(R.id.rep_pass);
        actualizar = findViewById(R.id.actualizar);

        actualizar.setOnClickListener{
            if(pass.text.isNullOrEmpty()){
                pass.error = "Contraseña Requerida"
                pass.requestFocus()
                return@setOnClickListener
            }
            if(pass.length() < 6){
                pass.error = "Contraseña de Minimo 6 Caracteres"
                pass.requestFocus()
                return@setOnClickListener
            }
            if(rep_pass.text.isNullOrEmpty()){
                rep_pass.error = "Debe Repetir la Contraseña"
                rep_pass.requestFocus()
                return@setOnClickListener
            }
            if(rep_pass.length() < 6){
                rep_pass.error = "Contraseña de Minimo 6 Caracteres"
                rep_pass.requestFocus()
                return@setOnClickListener
            }
            val intent = Intent( this, MainActivity::class.java);
            startActivity(intent);
        }
    }
}