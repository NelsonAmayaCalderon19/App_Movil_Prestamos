package com.example.app_prestamo_movil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterDeuda (var context: Context?, val dataSet: ArrayList<DeudaEntity>, recurso: Int): RecyclerView.Adapter<AdapterDeuda.DeudaViewHolder>() {
    class DeudaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre: TextView
        init{

            nombre = view.findViewById(R.id.cliente)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeudaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_deuda, parent, false)

        return DeudaViewHolder(view)
    }


    override fun onBindViewHolder(holder: DeudaViewHolder, position: Int) {
        holder.nombre.text = dataSet[position].idcliente

    }

    override fun getItemCount() = dataSet.size
}
