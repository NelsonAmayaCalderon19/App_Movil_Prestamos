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

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    val TAG:  String = "MainActivity";
    lateinit var usuario: TextInputEditText
    lateinit var contraseña: TextInputEditText
    lateinit var nuevaCuenta: TextView
    lateinit var recuperarcontrasenia: TextView
    lateinit var sesion: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance();
        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        sesion = findViewById(R.id.iniciar);
        nuevaCuenta = findViewById(R.id.nuevaCuenta);
        recuperarcontrasenia = findViewById(R.id.recuperarContrasenia);
        nuevaCuenta.setOnClickListener {
            val intent = Intent( this, RegisterActivity::class.java);
            startActivity(intent);
        }
        recuperarcontrasenia.setOnClickListener {
            val intent = Intent( this, RestPassActivity::class.java);
            startActivity(intent);
        }
        sesion.setOnClickListener {
            if (usuario.text.isNullOrEmpty()) {
                usuario.error = "Usuario Requerido"
                usuario.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(usuario.text.toString()).matches()) {
                usuario.error = "Email No Valido"
                usuario.requestFocus()
                return@setOnClickListener
            }
            if (contraseña.text.isNullOrEmpty() || contraseña.length() < 6) {
                contraseña.error = "Contraseña de Minimo 6 Caracteres"
                contraseña.requestFocus()
                return@setOnClickListener
            }
            loginUser(usuario.text.toString(), contraseña.text.toString())
        }
    }
    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            //reload();
        }
    }

    private fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmailAndPassword:success")
                    Toast.makeText(baseContext, "Autenticación Exitosa.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    showHome(email)
                } else {
                    showAlert()
                }
            }
    }

    private fun showHome(email: String){

        val intent = Intent( this, NavegacionActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent);
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Usuario no Encontrado, Intente Nuevamente")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}