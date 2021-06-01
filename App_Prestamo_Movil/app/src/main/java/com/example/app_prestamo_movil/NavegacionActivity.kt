package com.example.app_prestamo_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class NavegacionActivity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance();
    lateinit var clientesFragment : ClientesFragment
    lateinit var actualizarPerfilFragment: ActualizarPerfilFragment
    //lateinit var addCliente: FloatingActionButton
    lateinit var deudasFragment: DeudasFragment
    lateinit var notificacionesFragment: NotificacionesFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_navegacion)
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        val headerView: View = navigationView.getHeaderView(0)
        val navUsername: TextView = headerView.findViewById(R.id.email_User)
        // val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout);
        //val toolbar: MaterialToolbar = findViewById(R.id.topAppBar);
        val extras = intent.extras
        val s = extras?.getString("email") ?: "sin Email"
        navUsername.text = s.toString()
        //addCliente = findViewById(R.id.floating_action_button)
       /*addCliente.setOnClickListener{
            irRegistrarCliente()

        }*/

        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar);
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout);
        val nave: NavigationView = findViewById(R.id.navigationView);

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        clientesFragment = ClientesFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, clientesFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        nave.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.clientes -> {
                    toolbar.title = "Clientes";
                    drawerLayout.closeDrawer(GravityCompat.START)
                    clientesFragment = ClientesFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, clientesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }R.id.notificaciones -> {
                toolbar.title = "Cobrar Hoy";
                drawerLayout.closeDrawer(GravityCompat.START)
                notificacionesFragment = NotificacionesFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, notificacionesFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
                R.id.deudas -> {
                    toolbar.title = "Prestamos Realizados";
                    drawerLayout.closeDrawer(GravityCompat.START)
                    deudasFragment = DeudasFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, deudasFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
                R.id.configuracion -> {
                    toolbar.title = "Actualizar perfil";
                    drawerLayout.closeDrawer(GravityCompat.START)
                    actualizarPerfilFragment = ActualizarPerfilFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, actualizarPerfilFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.cerrar_sesión -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    auth.signOut();
                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent);
                    finish();
                }

            }
            false

        }
    }
    /*private fun irRegistrarCliente() {
        newClienteFragment = NewClienteFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, newClienteFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }*/
}