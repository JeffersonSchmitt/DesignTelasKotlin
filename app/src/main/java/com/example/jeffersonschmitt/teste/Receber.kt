package com.example.jeffersonschmitt.teste

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.SurfaceView
import android.widget.TextView
import github.nisrulz.qreader.QRDataListener
import github.nisrulz.qreader.QREader


class Receber : AppCompatActivity() {

    lateinit var valueMoney: TextView
    lateinit var mySurfaceView: SurfaceView
    lateinit var qrEader: QREader
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receber)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.title = ""

        val intent = getIntent()

        var bundle: Bundle

        bundle=intent.getExtras()

        valueMoney=findViewById(R.id.valueMoney)

        var valueMoneyText: String = bundle.getString("tx_money")
        if(valueMoneyText.equals("")){
            valueMoney.text = "00,00"
        }else{
            valueMoney.setText(valueMoneyText)

        }

        mySurfaceView = findViewById(R.id.camera_view) as SurfaceView

        qrEader = QREader.Builder(this, mySurfaceView, QRDataListener { data ->
            Log.d("QREader", "Value : $data")
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(mySurfaceView.height)
                .width(mySurfaceView.width)
                .build()

        onResume()

    }

    override fun onResume() {
        super.onResume()
        qrEader.initAndStart(mySurfaceView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            else -> {
            }
        }

        return true
    }


}
