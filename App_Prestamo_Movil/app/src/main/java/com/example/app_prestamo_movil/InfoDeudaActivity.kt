package com.example.app_prestamo_movil

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class InfoDeudaActivity : AppCompatActivity() {
    lateinit var nameCliente: TextView
    lateinit var prestamo: TextView
    lateinit var abono: TextView
    lateinit var total: TextView
    lateinit var fechaPrestamo: TextView
    lateinit var fecha: TextView
    lateinit var imagen: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_deuda)
        val l = Locale("es", "CO")
        val cal = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"), l)
        val dateInString = ""+cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
        fecha = findViewById(R.id.fecha);
        fecha.setText(dateInString);
        nameCliente = findViewById(R.id.infoCliente);
        prestamo = findViewById(R.id.valorPrestamo);
        abono = findViewById(R.id.valorAbono);
        total= findViewById(R.id.totalPagar);
        fechaPrestamo= findViewById(R.id.fechaPrestamo);
        imagen = findViewById(R.id.imgCliente);

        val extras = intent.extras
        val s = extras?.getString("nombre") ?: "Sin Nombre"
        nameCliente.text = s.toString()

    }

}