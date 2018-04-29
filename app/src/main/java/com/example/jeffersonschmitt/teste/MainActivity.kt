package com.example.jeffersonschmitt.teste

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.multidex.MultiDex
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.narayanacharya.waveview.WaveView


class MainActivity : AppCompatActivity() {

    lateinit var et_valueMoney: TextView
    private var display: String = ""
    lateinit var bnt_del: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MultiDex.install(this);

        val intent = Intent(this, Receber::class.java)

        val btn_receber = findViewById(R.id.btn_receber) as Button
        btn_receber.setOnClickListener({

            var bundle=Bundle()

            bundle.putString("tx_money", display)
            intent.putExtras(bundle)
            startActivity(intent)

        })


        val resideMenu = ResideMenu(this)
        resideMenu.setBackground(R.color.orangeDark)
        resideMenu.attachToActivity(this)
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);


        val titles = arrayOf("RECEBER", "HISTÓRICO ", "AJUDA", "NOVIDADES", "SISTEMA", "SAIR")
        for (i in 0 until titles.size) {
            val item = ResideMenuItem(this,titles[i])
            item.setOnClickListener({ this })
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT) // or  ResideMenu.DIRECTION_RIGHT
        }

        val sine = findViewById<WaveView>(R.id.waveView)
        sine.backgroundColor = ContextCompat.getColor(this, R.color.values)

        sine.waveColor = ContextCompat.getColor(this, R.color.wave)




        findViewById<View>(R.id.title_bar_left_menu).setOnClickListener { resideMenu.openMenu(ResideMenu.DIRECTION_LEFT) }

        bnt_del = findViewById(R.id.keyboardBackspace) as ImageButton

        bnt_del.setOnClickListener {

            if (display.length > 0) {
                display = display.substring(0, display.length - 1)
                updateScreen()

            }
        }
    }


    private fun updateScreen() {
        et_valueMoney = findViewById(R.id.valueMoney) as TextView
        et_valueMoney.setFilters(arrayOf<InputFilter>(InputFilter.LengthFilter(15)))
        et_valueMoney.setText(display)
    }

    fun onClickNumber(v: View) {
        val b = v as Button
        display += b.text
        updateScreen()
    }


}
