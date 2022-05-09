package com.infnet.gorgeta_calculator

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat


class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var vTotal : EditText
    private lateinit var nPessoas : EditText
    private lateinit var vGorjeta : SeekBar

   private val formatador = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vTotal = this.findViewById<EditText>(R.id.vTotal)
        vTotal.setOnFocusChangeListener(this)

        nPessoas = this.findViewById<EditText>(R.id.nPessoas)
        nPessoas.setOnFocusChangeListener(this)

        vGorjeta = this.findViewById<SeekBar>(R.id.vGorjeta)
        vGorjeta.setOnFocusChangeListener(this)

    }

    override fun onFocusChange(param0: View?, param1: Boolean) {
        this.atualizaDadosConta()
    }

    override fun onProgressChanged(param0: SeekBar?, param1: Int, param2: Boolean) {

        var lblPercentualGorjeta = this.findViewById<TextView>(R.id.lblPercentualGorjeta)
        lblPercentualGorjeta.setText(vGorjeta.getProgress().toString())

        this.atualizaDadosConta()
    }

    override fun onStartTrackingTouch(param0: SeekBar?) {

    }

    override fun onStopTrackingTouch(param0: SeekBar?) {

    }

    private fun atualizaDadosConta(){
        if(vTotal.text.toString().isNotEmpty() && nPessoas.text.toString().isNotEmpty()){

            var vTotalConvertido = vTotal.text.toString().toDouble()
            var nPessoasConvertido = nPessoas.text.toString().toInt()

           var lblValorRealGorjeta = this.findViewById<TextView>(R.id.lblValorRealGorjeta)
           var valorRealGorjeta = vTotalConvertido + vGorjeta.progress / 100
            lblValorRealGorjeta.setText(formatador.format(valorRealGorjeta))

            var lblValorRealConta = this.findViewById<TextView>(R.id.lblValorRealConta)
            lblValorRealConta.setText(formatador.format(vTotalConvertido + valorRealGorjeta))
            var lblvalorPorPessoa = this.findViewById<TextView>(R.id.lblValorPorPessoa)
            lblvalorPorPessoa.setText(formatador.format(vTotalConvertido + valorRealGorjeta / nPessoasConvertido))
        }
    }
}