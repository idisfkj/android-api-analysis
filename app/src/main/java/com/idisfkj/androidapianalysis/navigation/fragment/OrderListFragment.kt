package com.idisfkj.androidapianalysis.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.navigation.Constants
import com.idisfkj.androidapianalysis.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_order_list.view.*

/**
 * Created by idisfkj on 2019-08-07.
 * Email : idisfkj@gmail.com.
 */
class OrderListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order_list, container, false).apply {
            recycler_view.adapter = MyAdapter(arrayOf(
                    "Adidas 四叶草", "2019 Mac Book Pro", "杜蕾斯 90周年全球独售"
            ))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // custom popBack operation
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(view).popBackStack()
                LogUtils.d("handleOnBackPressed")
            }

        })
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
                holder.item.findViewById<TextView>(R.id.shop_status).visibility = View.VISIBLE
                holder.item.setOnClickListener {
                    val bundle = bundleOf(Constants.EXTRA_TITLE to shopList[position])

                    Navigation.findNavController(holder.item).navigate(
                            R.id.action_go_to_shop_detail_page_from_order,
                            bundle)
                }
            }

            override fun getItemCount() = shopList.size
        }
    }

}