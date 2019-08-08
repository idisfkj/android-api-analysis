package com.idisfkj.androidapianalysis.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.navigation.Constants
import kotlinx.android.synthetic.main.fragment_shop_detail.view.*

/**
 * Created by idisfkj on 2019-08-07.
 * Email : idisfkj@gmail.com.
 */
class ShopDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop_detail, container, false).apply {
            title.text = arguments?.getString(Constants.EXTRA_TITLE)
            add_cart.setOnClickListener {
                Navigation.findNavController(this).navigate(R.id.action_go_to_cart_page)
            }
        }
    }

}