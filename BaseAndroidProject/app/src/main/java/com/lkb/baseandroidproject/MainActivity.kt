package com.lkb.baseandroidproject

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lkb.baseandroidproject.databinding.ActivityMainRecyclerBinding
import kotlinx.android.synthetic.main.activity_main_recycler.*


class MainActivity : AppCompatActivity() {
//    private lateinit var handlers: MyClickHandlers
//    lateinit var usr: User

    val viewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainRecyclerBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_recycler)
//        setSupportActionBar(binding.toolbar)
//        usr = User()
//        usr.name.set("Lalit")
//        usr.email.set("lalitkbehera@gmail.com")
//
//        binding.user = usr
//        handlers = MyClickHandlers(this)
//        binding.handlers = handlers
//        binding.loader = ProfileViewModel(this)
        val adapter=RecyclerViewAdapter()
        recyclerView.adapter = adapter
        binding.viewModel =viewModel
        viewModel.startUpdates()


    }


//    inner class MyClickHandlers(internal var context: Context) {
//
//        fun onFabClicked(view: View) {
//            usr.name.set("Jhon Doe")
//            usr.email.set("jhon@gmail.com")
//            Toast.makeText(applicationContext, "FAB clicked!", Toast.LENGTH_SHORT).show()
//        }
//
//        fun onButtonClick(view: View) {
//            Toast.makeText(applicationContext, "Button clicked!", Toast.LENGTH_SHORT).show()
//        }
//
//        fun onButtonClickWithParam(view: View, user: User) {
//            Toast.makeText(applicationContext, "Button clicked! Name: " + user.name, Toast.LENGTH_SHORT).show()
//        }
//
//        fun onButtonLongPressed(view: View): Boolean {
//            Toast.makeText(applicationContext, "Button long pressed!", Toast.LENGTH_SHORT).show()
//            return false
//        }
//    }
}
