package com.example.app_prestamo_movil

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RestPassActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    val TAG: String = "RestPassActivity";
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

        recuperar.setOnClickListener {
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
           /* val ProgressDialog = ProgressDialog(this)
            ProgressDialog.setMessage("Espere un Momento...");
            ProgressDialog.setCanceledOnTouchOutside(false)
            ProgressDialog.show()*/
            resetPassword(email.text.toString());
            /*ProgressDialog.dismiss()*/
        }
        volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }
    }

    private fun resetPassword(email: String) {
        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    baseContext, "Se ha enviado un Correo, para Reestablecer tu Contraseña",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, MainActivity::class.java);
                startActivity(intent);
            } else {
                Toast.makeText(
                    baseContext, "No se Pudo Enviar el Correo de Reestablecer Contraseña",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}