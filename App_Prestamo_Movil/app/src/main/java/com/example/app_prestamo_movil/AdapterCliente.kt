package com.example.app_prestamo_movil

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterCliente (var context: Context?, val dataSet:List<ClienteEntity>, recurso: Int): RecyclerView.Adapter<AdapterCliente.ClienteViewHolder>() {
var posicion = 0;
    class ClienteViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val nombre: TextView
        val telefono: TextView
        val imagen: ImageView
        val card: CardView

        init {

            nombre = view.findViewById(R.id.titulo)
            telefono = view.findViewById(R.id.descripcion)
            imagen = view.findViewById(R.id.imagen)
            card = view.findViewById(R.id.cardCliente)

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
val pos=position;
        holder.card.setOnClickListener(){v ->
            posicion = pos;
            if(posicion==position){
                //println("Hola1 "+posicion)
               val intent = Intent(v.context, PrestamoActivity::class.java).apply {
                   putExtra("nombre" , dataSet[posicion].nombre);
                   putExtra("id", dataSet[posicion].id);
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
