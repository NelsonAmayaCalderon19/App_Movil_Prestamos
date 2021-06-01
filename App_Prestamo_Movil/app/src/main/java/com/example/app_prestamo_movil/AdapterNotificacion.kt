package com.example.app_prestamo_movil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterNotificacion (var context: Context?, val dataSet: ArrayList<DeudaEntity>, recurso: Int): RecyclerView.Adapter<AdapterNotificacion.DeudaViewHolder>() {
    class DeudaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre: TextView
        val valorcuota : TextView
        init{
            nombre = view.findViewById(R.id.clientenot)
            valorcuota = view.findViewById(R.id.cuotica)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeudaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_notificacion, parent, false)

        return DeudaViewHolder(view)
    }


    override fun onBindViewHolder(holder: DeudaViewHolder, position: Int) {
        holder.nombre.text = dataSet[position].idcliente
        holder.valorcuota.text = dataSet[position].fechaPrestamo
    }

    override fun getItemCount() = dataSet.size
}
