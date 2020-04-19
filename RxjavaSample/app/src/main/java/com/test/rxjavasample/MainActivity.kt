package com.test.rxjavasample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_rx_create.setOnClickListener(this)
        btn_rx_change.setOnClickListener(this)
        btn_rx_merge.setOnClickListener(this)
        btn_rx_utility.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_rx_create-> {
                val intent: Intent = Intent()
                intent.setClassName("com.test.rxjavasample","com.test.rxjavasample.RxjavaCreateActivity")
                startActivity(intent)
            }
            R.id.btn_rx_change->{
                val intent:Intent = Intent(this,RxjavaChangeActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_rx_merge->{
                val intent:Intent = Intent(this,RxjavaMergeActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_rx_utility->{
                val intent:Intent = Intent(this,RxjavaUtilityActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
