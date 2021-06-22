package com.example.app_prestamo_movil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClientesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClientesFragment : Fragment() {

    lateinit var contenedorCliente: RecyclerView
    lateinit var adapterCliente: AdapterCliente
    lateinit var addCliente: FloatingActionButton
    //lateinit var newClienteFragment: NewClienteFragment
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_clientes, container, false)
        val view: View = inflater.inflate(R.layout.fragment_clientes, container, false)
        contenedorCliente = view.findViewById(R.id.contenedorCliente)
        addCliente = view.findViewById(R.id.floating_action_button)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        contenedorCliente.layoutManager = linearLayout
        adapterCliente = AdapterCliente(context,cargarDatosFirebase(), R.id.cardCliente)
        contenedorCliente.adapter = adapterCliente
        addCliente.setOnClickListener{
            irRegistrarCliente()
        }

        return view
    }

    private fun irRegistrarCliente(){
        val intent = Intent(context,NewClienteActivity::class.java);
        startActivity(intent);
    }

    fun cargarDatosFirebase(): ArrayList<ClienteEntity> {
        val clientes = ArrayList<ClienteEntity>()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()
        myRef.child("clientes").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    clientes.clear()
                    for (data in snapshot.children) {
                        val cliente = data.getValue(ClienteEntity::class.java)
                        clientes.add(cliente as ClienteEntity)
                        adapterCliente.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ClientesFragment", "Carga Cancelada", error.toException())
            }
        })
        return clientes
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClientesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClientesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}