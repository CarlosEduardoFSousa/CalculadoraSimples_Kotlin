package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Conexão XML + Kotlin (Aula Prática)
        val txtCalculo = findViewById<TextView>(R.id.calculo)
        val txtResultado = findViewById<TextView>(R.id.resultado)
        val btnCE = findViewById<Button>(R.id.CE)
        val btnAPTS = findViewById<Button>(R.id.parente_abrindo)
        val btnFPTS = findViewById<Button>(R.id.parente_fechando)
        val btnDiv = findViewById<Button>(R.id.divisao)
        val btnSete = findViewById<Button>(R.id.sete)
        val btnOito = findViewById<Button>(R.id.oito)
        val btnNove = findViewById<Button>(R.id.nove)
        val btnMult = findViewById<Button>(R.id.multiplicacao)
        val btnQuatro = findViewById<Button>(R.id.quatro)
        val btnCinco = findViewById<Button>(R.id.cinco)
        val btnSeis = findViewById<Button>(R.id.seis)
        val btnSubtr = findViewById<Button>(R.id.subtracao)
        val btnUm = findViewById<Button>(R.id.um)
        val btnDois = findViewById<Button>(R.id.dois)
        val btnTres = findViewById<Button>(R.id.tres)
        val btnSoma = findViewById<Button>(R.id.soma)
        val btnPonto = findViewById<Button>(R.id.ponto)
        val btnZero = findViewById<Button>(R.id.zero)
        val btnApagar = findViewById<ImageButton>(R.id.apagar)
        val btnIgual = findViewById<Button>(R.id.igual)

        // 2. Função para montar a expressão
        fun acrescentarExpressao(string: String, LimparDados: Boolean){
            if (txtResultado.text.isNotEmpty()) {
                txtCalculo.text = ""
            }

            if(LimparDados){
                txtResultado.text = ""
                txtCalculo.append(string)
            } else {
                txtCalculo.append(txtResultado.text)
                txtCalculo.append(string)
                txtResultado.text = ""
            }
        }

        // 3. Lógica de Cálculo (Aula 2 e 3: When e For)
        fun calcularExpressao(expressao: String) {
            var resultado = 0.0
            var numeroAtual = ""
            var operadorAtual = '+'

            for (i in 0 until expressao.length) {
                val char = expressao[i]

                if (char.isDigit() || char == '.') {
                    numeroAtual += char
                }

                // Verificação para processar o número quando encontrar um sinal ou o fim da linha
                if (!char.isDigit() && char != '.' || i == expressao.length - 1) {

                    // AJUSTE AQUI: Só converte se houver um número guardado
                    if (numeroAtual.isNotEmpty()) {
                        val valor = numeroAtual.toDouble()

                        when (operadorAtual) {
                            '+' -> resultado += valor
                            '-' -> resultado -= valor
                            '*' -> resultado *= valor
                            '/' -> {
                                if (valor != 0.0) resultado /= valor
                                else {
                                    txtResultado.text = "Erro"
                                    return
                                }
                            }
                        }
                    }
                    operadorAtual = char
                    numeroAtual = ""
                }
            }
            // Formatação simples para o resultado
            txtResultado.text = resultado.toString()
        }

        // 4. Atribuição dos Cliques (Ação do Botão)
        btnZero.setOnClickListener { acrescentarExpressao("0", true) }
        btnUm.setOnClickListener { acrescentarExpressao("1", true) }
        btnDois.setOnClickListener { acrescentarExpressao("2", true) }
        btnTres.setOnClickListener { acrescentarExpressao("3", true) }
        btnQuatro.setOnClickListener { acrescentarExpressao("4", true) }
        btnCinco.setOnClickListener { acrescentarExpressao("5", true) }
        btnSeis.setOnClickListener { acrescentarExpressao("6", true) }
        btnSete.setOnClickListener { acrescentarExpressao("7", true) }
        btnOito.setOnClickListener { acrescentarExpressao("8", true) }
        btnNove.setOnClickListener { acrescentarExpressao("9", true) }
        btnSoma.setOnClickListener { acrescentarExpressao("+", false) }
        btnSubtr.setOnClickListener { acrescentarExpressao("-", false) }
        btnMult.setOnClickListener { acrescentarExpressao("*", false) }
        btnDiv.setOnClickListener { acrescentarExpressao("/", false) }
        btnPonto.setOnClickListener { acrescentarExpressao(".", true) }

        // Parênteses (Opcional, conforme seu layout)
        btnAPTS.setOnClickListener { acrescentarExpressao("(", true) }
        btnFPTS.setOnClickListener { acrescentarExpressao(")", true) }

        btnCE.setOnClickListener {
            txtCalculo.text = ""
            txtResultado.text = ""
        }

        btnApagar.setOnClickListener {
            val expressao = txtCalculo.text.toString()
            if(expressao.isNotEmpty()){
                txtCalculo.text = expressao.substring(0, expressao.length - 1)
            }
            txtResultado.text = ""
        }

        btnIgual.setOnClickListener {
            val texto = txtCalculo.text.toString()
            if (texto.isNotEmpty()) {
                calcularExpressao(texto)
            }
        }
    }
}