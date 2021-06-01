package com.example.app_prestamo_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RestPassActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    val TAG:  String = "RestPassActivity";
    lateinit var email: TextInputEditText
    lateinit var volver: TextView
    lateinit var recuperar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_pass)
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        volver = findViewById(R.id.volverLogin);
        recuperar = findViewById(R.id.recuperar);

        recuperar.setOnClickListener{
            if (email.text.isNullOrEmpty()) {
                email.error = "Email Requerido"
                email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                email.error = "Email No Valido"
                email.requestFocus()
                return@setOnClickListener
            }
            val intent = Intent( this, NewPassActivity::class.java);
            startActivity(intent);
        }
        volver.setOnClickListener {
            val intent = Intent( this, MainActivity::class.java);
            startActivity(intent);
        }
    }
}