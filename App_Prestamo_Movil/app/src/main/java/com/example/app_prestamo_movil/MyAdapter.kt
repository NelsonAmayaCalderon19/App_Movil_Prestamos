package com.example.app_prestamo_movil

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class MyAdapter (
    var context: Context,
    fm: FragmentManager
): FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ClientesFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return 3;
    }

}