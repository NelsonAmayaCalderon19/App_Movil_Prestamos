package com.example.app_prestamo_movil

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterCliente (var context: Context?, val dataSet: ArrayList<ClienteEntity>, recurso: Int): RecyclerView.Adapter<AdapterCliente.ClienteViewHolder>() {
    class ClienteViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre: TextView
        val telefono: TextView
        //val direccion: TextView
       val imagen: ImageView
        init{

            nombre = view.findViewById(R.id.titulo)
            telefono = view.findViewById(R.id.descripcion)
            //direccion = view.findViewById(R.id.isbn)
            imagen = view.findViewById(R.id.imagen)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_cliente, parent, false)

        return ClienteViewHolder(view)
    }


    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        holder.nombre.text = dataSet[position].nombre
        holder.telefono.text = dataSet[position].telefono
        //holder.correo.text = dataSet[position].correo
      //  holder.direccion.text = dataSet[position].direccion
        Picasso.get().load(dataSet[position].imagen).into(holder.imagen);


    }

    override fun getItemCount() = dataSet.size
}
