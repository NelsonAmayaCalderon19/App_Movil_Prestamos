package com.example.app_prestamo_movil

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class AdapterNotificacion (var context: Context?, val dataSet: ArrayList<DeudaEntity>, recurso: Int): RecyclerView.Adapter<AdapterNotificacion.DeudaViewHolder>() {
    var posicion = 0;
    class DeudaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre: TextView
        val valorcuota : TextView
        val cardNotificacion: CardView
        init{
            nombre = view.findViewById(R.id.clientenot)
            valorcuota = view.findViewById(R.id.cuotica)
            cardNotificacion = view.findViewById(R.id.cardNotificacion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeudaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_notificacion, parent, false)

        return DeudaViewHolder(view)
    }


    override fun onBindViewHolder(holder: DeudaViewHolder, position: Int) {
        holder.nombre.text = dataSet[position].nameCliente
        holder.valorcuota.text = dataSet[position].valorcuota
        val pos=position;
        holder.cardNotificacion.setOnClickListener(){v ->
            posicion = pos;
            if(posicion==position){
                val intent = Intent(v.context, CobroActivity::class.java).apply {
                    putExtra("nombre" , dataSet[posicion].nameCliente);
                    putExtra("id", dataSet[posicion].id);
                    putExtra("idCliente", dataSet[posicion].idcliente);
                    putExtra("valorcuota", dataSet[posicion].valorcuota);
                    putExtra("tipopago", dataSet[posicion].tipopago);
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
