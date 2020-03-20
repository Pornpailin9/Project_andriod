package com.example.facebook_authen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray

class MyRecyclerAdapter (context: Context, fm: FragmentManager, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = context
    private val fragment: FragmentManager = fm

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView

        }


    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, skills: Int) {

        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(skills).getString("firstname").toString() +" "+dataSource.getJSONObject(skills).getString("lastname").toString() )
        holder.detailTextView.setText( dataSource.getJSONObject(skills).getString("skills").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(skills).getString("image").toString())
            .into(holder.image)


        holder.layout.setOnClickListener{

            Toast.makeText(thiscontext,holder.titleTextView.text.toString(), Toast.LENGTH_SHORT).show()

            val head:String = dataSource.getJSONObject(skills).getString("firstname").toString() +" "+dataSource.getJSONObject(skills).getString("lastname").toString()
            val body:String = dataSource.getJSONObject(skills).getString("skills").toString()
            val img:String = dataSource.getJSONObject(skills).getString("image").toString()
            val skill:String = dataSource.getJSONObject(skills).getString("detail").toString()

            //+"\n"+ dataSource.getJSONObject(skill).getString("css").toString() ต่อข้างหลังของบรรทัดที่ 75

            val item_selected = item_selected().newInstance(head,body,img,skill)
            val transaction : FragmentTransaction = fragment.beginTransaction()
            transaction.replace(R.id.layout, item_selected,"item_selected")
            transaction.addToBackStack("item_selected")
            transaction.commit()
            Toast.makeText(thiscontext,holder.titleTextView.text.toString(), Toast.LENGTH_SHORT).show()
        }

    }







}