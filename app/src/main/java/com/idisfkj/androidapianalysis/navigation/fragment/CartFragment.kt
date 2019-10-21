package com.idisfkj.androidapianalysis.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.R
import kotlinx.android.synthetic.main.fragment_cart.view.*

/**
 * Created by idisfkj on 2019-08-07.
 * Email : idisfkj@gmail.com.
 */
class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false).apply {
            shop_detail_bt.setOnClickListener(Navigation.createNavigateOnClickListener(CartFragmentDirections.actionGoToShopListPageFromCart()))
            buy_bt.setOnClickListener(Navigation.createNavigateOnClickListener(CartFragmentDirections.actionGoToOrderListPage()))
            recycler_view.adapter = MyAdapter(arrayOf(
                    "Adidas 四叶草", "2019 Mac Book Pro", "杜蕾斯 90周年全球独售"
            ))
        }
    }

    companion object {

        class MyAdapter(private val shopList: Array<String>) :
                RecyclerView.Adapter<MyAdapter.ViewHolder>() {

            class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

            override fun onCreateViewHolder(parent: ViewGroup,
                                            viewType: Int): ViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_shop_list, parent, false)

                return ViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.item.findViewById<TextView>(R.id.title_view).text = shopList[position]

                holder.item.setOnClickListener(Navigation.createNavigateOnClickListener(CartFragmentDirections.actionGoToShopDetailPageFromCart(shopList[position])))
            }

            override fun getItemCount() = shopList.size
        }
    }
}