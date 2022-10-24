package and5.finalproject.rotatesharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var etNumberOne : EditText
    private lateinit var etNumberTwo : EditText
    private lateinit var tvResult : Button

    private val PREF_NAME = "Shared"
    private val KEY_NAME = "ResultCalc"
    private val KEY_ET_ONE = "EtOne"
    private val KEY_ET_TWO = "EtTwo"
    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumberOne = findViewById(R.id.et_number_one)
        etNumberTwo = findViewById(R.id.et_number_two)
        tvResult = findViewById(R.id.tv_hasil)
        sharedPreference = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val btnPlus = findViewById<Button>(R.id.btn_plus)
        val btnMin = findViewById<Button>(R.id.btn_min)
        val btnDiv = findViewById<Button>(R.id.btn_div)
        val btnMul = findViewById<Button>(R.id.btn_mul)

        btnPlus.setOnClickListener (this)
        btnMin.setOnClickListener (this)
        btnDiv.setOnClickListener (this)
        btnMul.setOnClickListener (this)

        val result = sharedPreference.getFloat(KEY_NAME, 0.0f)
        val etNumberOneRestore = sharedPreference.getString(KEY_ET_ONE, "0")
        val etNumberTwoRestore = sharedPreference.getString(KEY_ET_TWO, "0")
        tvResult.text = result.toString()
        etNumberOne.setText(etNumberOneRestore)
        etNumberTwo.setText(etNumberTwoRestore)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editor : SharedPreferences.Editor = sharedPreference.edit()
        editor.putFloat(KEY_NAME, tvResult.text.toString().toFloat())
        editor.putString(KEY_ET_ONE, etNumberOne.text.toString())
        editor.putString(KEY_ET_TWO, etNumberTwo.text.toString())
        editor.apply()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val result = sharedPreference.getFloat(KEY_NAME,0.0f)
        tvResult.text = result.toString()
    }

    override fun onClick(p0: View?) {
        val nilai : Float
        if (p0 != null){
            when(p0.id){
                R.id.btn_plus -> {
                    nilai = etNumberOne.text.toString().toFloat() + etNumberTwo.text.toString().toFloat()
                    tvResult.text = nilai.toString()
                }
                R.id.btn_min -> {
                    nilai = etNumberOne.text.toString().toFloat() - etNumberTwo.text.toString().toFloat()
                    tvResult.text = nilai.toString()
                }
                R.id.btn_div -> {
                    nilai = etNumberOne.text.toString().toFloat() / etNumberTwo.text.toString().toFloat()
                    tvResult.text = nilai.toString()
                }
                R.id.btn_mul -> {
                    nilai = etNumberOne.text.toString().toFloat() * etNumberTwo.text.toString().toFloat()
                    tvResult.text = nilai.toString()
                }

            }
        }
    }
}