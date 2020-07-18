package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity(), FragmentOne.OnButtonClickListener{

    override fun buttonClicked(data:String) {
        // delegate the event to the other fragment.
        onButtonClickedInFragment(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            val fm = supportFragmentManager
            val ft= fm.beginTransaction()
            ft.add(R.id.containerOne,FragmentOne(),"ONE")
            ft.add(R.id.containerTwo,FragmentTwo(),"TWO")
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment is FragmentOne) {
            val headlinesFragment = fragment
           headlinesFragment.setOnButtonClickListenr(this)
        }
    }
    fun onButtonClickedInFragment(data:String){
       val fragmentTwo:FragmentTwo? = supportFragmentManager.findFragmentByTag("TWO") as FragmentTwo?
        if(fragmentTwo!=null){
            fragmentTwo.updateUI(data)
        }else{
            // crate te fragment and set the data in a bundle.
        }
    }
}
