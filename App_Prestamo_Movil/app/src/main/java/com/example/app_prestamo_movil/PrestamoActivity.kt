package com.example.app_prestamo_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class PrestamoActivity : AppCompatActivity() {
    val TAG:  String = "PrestamoActivity";
    lateinit var nameCliente: TextView
    var idcliente = "";
    var fechaprestamo = ""
    var valorcuota = 0;
    lateinit var credito: TextInputEditText
    lateinit var interes: TextInputEditText
    lateinit var cuotas: TextInputEditText
    lateinit var totalpagar: TextInputEditText
    lateinit var primerCobro: TextInputEditText
    lateinit var tipopago: TextInputEditText
    lateinit var aceptar: Button
    lateinit var tot: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prestamo)
        credito = findViewById(R.id.valorPrest);
        interes = findViewById(R.id.interesP);
        cuotas = findViewById(R.id.cuota);
        totalpagar = findViewById(R.id.totPagar);
        primerCobro = findViewById(R.id.primPago);
        tipopago = findViewById(R.id.tipPago);
        aceptar = findViewById(R.id.guardarPrestamo);
        val l = Locale("es", "CO")
        val cal = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"), l)

       fechaprestamo = ""+cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(
            Calendar.YEAR);
        primerCobro.setOnClickListener {
            showDatePickerDialog();
        }
        nameCliente = findViewById(R.id.nameClientePrest);
        tot = findViewById(R.id.totPag);
        val extras = intent.extras
        val s = extras?.getString("nombre") ?: "Sin Nombre"
        nameCliente.text = s.toString()
        val d = extras?.getString("id") ?: "Sin ID"
        idcliente = d.toString()
        aceptar.setOnClickListener {
        nuevoPrestamo();
        }



    }
    fun showDatePickerDialog(){
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
    datePicker.show(supportFragmentManager, "datePicker")
    }
fun onDateSelected(day:Int, month:Int, year:Int){
    val selectedMonth = month + 1
primerCobro.setText("$day/$selectedMonth/$year")
}
    fun nuevoPrestamo() {
        valorcuota = Integer.parseInt(totalpagar.text.toString())/Integer.parseInt(cuotas.text.toString());
        //println(Integer.parseInt(totalpagar.text.toString())/Integer.parseInt(cuotas.text.toString()));
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val prestamo = DeudaEntity(
            myRef.push().key.toString(),
            idcliente.toString(),
            nameCliente.text.toString(),
            fechaprestamo.toString(),
            primerCobro.text.toString(),
            credito.text.toString(),
            interes.text.toString(),
            tipopago.text.toString(),
            totalpagar.text.toString(),
            valorcuota.toString(),
            cuotas.text.toString()
        )
        myRef.child("deudas").child(prestamo.id).setValue(prestamo)
        finish()
    }

    /*fun queryDatabase(): ArrayList<ClienteEntity>{
        //var dataSet:List<ClienteEntity>
       // var deudas = ArrayList<ClienteEntity>()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()
        myRef.child(idcliente.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    deudas.clear()
                    for (data in snapshot.children) {
                        val deuda = data.getValue(ClienteEntity::class.java)
                        deudas.add(deuda as ClienteEntity)
                        //adapterDeuda.notifyDataSetChanged()
                    }
                }
            }
                override fun onCancelled(error: DatabaseError) {
                    Log.w("PrestamoActivity", "Carga Cancelada", error.toException())
                }
            })
            return deudas;
        }*/


    }
