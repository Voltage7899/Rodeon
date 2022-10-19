package com.company.rodeon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.rodeon.databinding.ElementShopCarBinding
import com.squareup.picasso.Picasso

class YourCarsAdapter: RecyclerView.Adapter<YourCarsAdapter.ViewHolder>() {

    private var ListInAdapter = ArrayList<ShopCars>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourCarsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_shop_car, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: YourCarsAdapter.ViewHolder, position: Int) {
        holder.bind(ListInAdapter[position])
    }

    override fun getItemCount(): Int {
        return ListInAdapter.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ElementShopCarBinding.bind(itemView)
        fun bind(car: ShopCars) {
            binding.yourCarName.text = car.name
            binding.yourCarPrice.text = car.price




        }
    }

    fun loadListToAdapter(productList: ArrayList<ShopCars>) {
        ListInAdapter = productList
        notifyDataSetChanged()
    }



    fun deleteItem(i: Int): String? {
        var id = ListInAdapter.get(i).name

        ListInAdapter.removeAt(i)

        notifyDataSetChanged()

        return id
    }
}