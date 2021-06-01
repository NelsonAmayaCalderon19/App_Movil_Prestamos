package com.example.app_prestamo_movil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificacionesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificacionesFragment : Fragment() {
    lateinit var contenedorDeuda: RecyclerView
    lateinit var adapterDeuda: AdapterNotificacion
    lateinit var addCobro: FloatingActionButton
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_notificaciones, container, false)
        contenedorDeuda = view.findViewById(R.id.contenedorNotificacion)
        addCobro = view.findViewById(R.id.floating_action_notif)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        contenedorDeuda.layoutManager = linearLayout
        adapterDeuda = AdapterNotificacion(context,cargarDatos(), R.id.cardNotificacion)
        contenedorDeuda.adapter = adapterDeuda
        addCobro.setOnClickListener{
             irRegistrarCobro()

         }
        return view
    }

    private fun irRegistrarCobro(){
        val intent = Intent(context,CobroActivity::class.java);
        startActivity(intent);
    }

    private fun cargarDatos(): ArrayList<DeudaEntity>{
       val deudas: ArrayList<DeudaEntity> = java.util.ArrayList<DeudaEntity>()
       deudas.add(
           DeudaEntity(
               "1",
               "Cliente 1",
               "$20000"
             )
       )
       deudas.add(
           DeudaEntity(
               "2",
               "cliente 2",
               "$11000"
             )
       )
       return deudas
   }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotificacionesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificacionesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}