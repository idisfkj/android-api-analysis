package com.idisfkj.androidapianalysis.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.idisfkj.androidapianalysis.R
import kotlinx.android.synthetic.main.fragment_shop_detail.view.*

/**
 * Created by idisfkj on 2019-08-07.
 * Email : idisfkj@gmail.com.
 */
class ShopDetailFragment : Fragment() {

    private val args by navArgs<ShopDetailFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop_detail, container, false).apply {
            title.text = args.title
            add_cart.setOnClickListener(Navigation.createNavigateOnClickListener(ShopDetailFragmentDirections.actionGoToCartPage()))
        }
    }

}