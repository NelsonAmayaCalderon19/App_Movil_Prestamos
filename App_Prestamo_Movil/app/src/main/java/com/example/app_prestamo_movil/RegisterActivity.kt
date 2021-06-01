package com.example.app_prestamo_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    val TAG:  String = "RegisterActivity";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance();
        val usuario: TextInputEditText = findViewById(R.id.usuario);
        val contraseña: TextInputEditText = findViewById(R.id.contraseña);
        val repContraseña: TextInputEditText = findViewById(R.id.repic_contraseña);
        val crear: Button = findViewById(R.id.crear);
        val volver: TextView = findViewById(R.id.volver);
        volver.setOnClickListener {
            val intent = Intent( this, MainActivity::class.java);
            startActivity(intent);
        }

        crear.setOnClickListener{
            if(usuario.text.isNullOrEmpty()){
                usuario.error = "Usuario Requerido"
                usuario.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(usuario.text.toString()).matches()){
                usuario.error = "Email No Valido"
                usuario.requestFocus()
                return@setOnClickListener
            }
            if(contraseña.text.isNullOrEmpty() || contraseña.length() <6){
                contraseña.error = "Contraseña de Minimo 6 Caracteres"
                contraseña.requestFocus()
                return@setOnClickListener
            }
            if(repContraseña.text.isNullOrEmpty() || repContraseña.length() <6){
                repContraseña.error = "Contraseña de Minimo 6 Caracteres"
                repContraseña.requestFocus()
                return@setOnClickListener
            }

            registerUser(usuario.text.toString(),contraseña.text.toString())
        }
    }
    private fun registerUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmailAndPassword:success")
                    Toast.makeText(baseContext, "Registro Exitoso.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    showHome()
                } else {
                    showAlert()
                }
            }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se Pudo Registrar el Usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(){

        val Intent = Intent( this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(Intent);
    }
}