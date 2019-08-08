package com.idisfkj.androidapianalysis.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.idisfkj.androidapianalysis.R
import kotlinx.android.synthetic.main.fragment_welcome.view.*

/**
 * Created by idisfkj on 2019-08-07.
 * Email : idisfkj@gmail.com.
 */
class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false).apply {
            register_bt.setOnClickListener {
                Navigation.findNavController(this).navigate(R.id.action_go_to_register_page)
            }
            stroll_bt.setOnClickListener {
                Navigation.findNavController(this).navigate(R.id.action_go_to_order_list_page)
            }
        }
    }
}