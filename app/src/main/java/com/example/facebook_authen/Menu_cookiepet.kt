package com.example.facebook_authen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class Menu_cookiepet : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_menu_cookiepet, container, false)
        // Inflate the layout for this fragment

        val button: Button = view.findViewById(R.id.btnlogout);
        val button1: Button = view.findViewById(R.id.btncookie);
        val button2: Button = view.findViewById(R.id.btnpet);
        val button3: Button = view.findViewById(R.id.btnitem);
        val button4: Button = view.findViewById(R.id.btndb);


        button.setOnClickListener {
            Toast.makeText(context, "Logout", Toast.LENGTH_LONG).show()
            val fragment_login = login()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_login, "fragment_login")
            transaction.addToBackStack("fragment_login")
            transaction.commit()
        }

        button1.setOnClickListener {
            Toast.makeText(context, "Cookie Detail", Toast.LENGTH_LONG).show()
            val fragment_recycler_view = Recycler_view()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_recycler_view,"fragment_recycler_view")
            transaction.addToBackStack("fragment_recycler_view")
            transaction.commit()
        }

        button2.setOnClickListener {
            Toast.makeText(context, "Pet Detail", Toast.LENGTH_LONG).show()
            val fragment_recycler_view2 = Recycler_view2()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_recycler_view2,"fragment_recycler_view2")
            transaction.addToBackStack("fragment_recycler_view2")
            transaction.commit()
        }

        button3.setOnClickListener {
            Toast.makeText(context, "Item Detail", Toast.LENGTH_LONG).show()
            val fragment_recycler_view3 = Recycler_view3()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_recycler_view3,"fragment_recycler_view3")
            transaction.addToBackStack("fragment_recycler_view3")
            transaction.commit()
        }

        button4.setOnClickListener {
            Toast.makeText(context, "Dashboard", Toast.LENGTH_LONG).show()
            val MainChart = MainChart()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, MainChart,"MainChart")
            transaction.addToBackStack("MainChart")
            transaction.commit()
        }
        return view;
    }
}
