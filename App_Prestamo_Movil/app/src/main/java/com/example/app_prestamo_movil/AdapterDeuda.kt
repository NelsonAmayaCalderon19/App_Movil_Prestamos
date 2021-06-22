package com.example.app_prestamo_movil

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class AdapterDeuda (var context: Context?, val dataSet: ArrayList<DeudaEntity>, recurso: Int): RecyclerView.Adapter<AdapterDeuda.DeudaViewHolder>() {
    val TAG:  String = "MainActivity";
    var posicion = 0;
    class DeudaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre: TextView
        val cardDeuda: CardView
        init{

            nombre = view.findViewById(R.id.cliente)
            cardDeuda = view.findViewById(R.id.cardDeuda)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeudaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_deuda, parent, false)

        return DeudaViewHolder(view)
    }


    override fun onBindViewHolder(holder: DeudaViewHolder, position: Int) {

        holder.nombre.text = dataSet[position].nameCliente
        val pos=position;
        holder.cardDeuda.setOnClickListener(){v ->
            posicion = pos;
            if(posicion==position){
                val intent = Intent(v.context, InfoDeudaActivity::class.java).apply {
                    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val myRef: DatabaseReference = database.getReference()
                    myRef.child("clientes").child(dataSet[posicion].idcliente).get().addOnSuccessListener {
                        val imagen = it.child("imagen").getValue().toString()
                        //Log.d("firebase", "Got value ${imagen}" )
                        putExtra("imagen", imagen);
                    }.addOnFailureListener{
                        Log.e("firebase", "Error getting data", it)
                    }
                    putExtra("nombre" , dataSet[posicion].nameCliente);
                    putExtra("id", dataSet[posicion].id);
                    putExtra("idClciente", dataSet[posicion].idcliente);
                    // putExtra("imagen", Picasso.get().load(dataSet[posicion].imagen));
                    // putExtra("imagen", Picasso.get().load(dataSet[position].imagen).into(holder.imagen));

                }
                v.context.startActivity(intent);


            }else{
                println(posicion)
            }

        }
    }

    override fun getItemCount() = dataSet.size
}
