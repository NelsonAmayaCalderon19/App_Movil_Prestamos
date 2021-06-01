package com.example.app_prestamo_movil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeudasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeudasFragment : Fragment() {
    lateinit var contenedorDeuda: RecyclerView
    lateinit var adapterDeuda: AdapterDeuda
    lateinit var addPrestamo: FloatingActionButton
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
        val view: View = inflater.inflate(R.layout.fragment_deudas, container, false)
        contenedorDeuda = view.findViewById(R.id.contenedorDeuda)
        addPrestamo = view.findViewById(R.id.floating_action)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        contenedorDeuda.layoutManager = linearLayout
        adapterDeuda = AdapterDeuda(context,cargarDatosFirebase(), R.id.cardDeuda)
        contenedorDeuda.adapter = adapterDeuda
       addPrestamo.setOnClickListener{
            irRegistrarPrestamo()

        }
        return view
    }

    private fun irRegistrarPrestamo(){
        val intent = Intent(context,PrestamoActivity::class.java);
        startActivity(intent);
    }

    fun cargarDatosFirebase(): ArrayList<DeudaEntity> {
        val deudas = ArrayList<DeudaEntity>()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()
        myRef.child("deudas").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    deudas.clear()
                    for (data in snapshot.children) {
                        val deuda = data.getValue(DeudaEntity::class.java)
                        deudas.add(deuda as DeudaEntity)
                        adapterDeuda.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("DeudasFragment", "Carga Cancelada", error.toException())
            }
        })
        return deudas
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DeudasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeudasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}