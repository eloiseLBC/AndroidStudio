package fr.projectone.salade

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProduitAdapter(val rayons: ArrayList<Rayon>) :
    RecyclerView.Adapter<ProduitAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cell_rayon, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rayon = rayons.get(position)
        holder.textViewCategoryId.text = rayon.category_id
        holder.textViewTitre.text = rayon.title
        holder.textViewProductsUrl.text = rayon.products_url
    }

    override fun getItemCount(): Int {
        return rayons.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCategoryId = view.findViewById<TextView>(R.id.textViewCategoryId)
        val textViewTitre = view.findViewById<TextView>(R.id.textViewTitre)
        val textViewProductsUrl = view.findViewById<TextView>(R.id.textViewProductsUrl)
    }
}
