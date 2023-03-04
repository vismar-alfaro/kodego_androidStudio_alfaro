package ph.kodego.alfaro.vismarjay.kodegosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ph.kodego.alfaro.vismarjay.kodegosample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private final var LOGINFO = "HOMEACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        Log.d(LOGINFO,"USERNAME : ${bundle!!.getString("user")}")
        Log.d(LOGINFO,"USERNAME : ${bundle!!.getString("user")}")

        val extra = intent.getStringExtra("something")
        Log.d(LOGINFO,"SOMETHING: $extra")

    }
}