package ph.kodego.alfaro.vismarjay.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.kodego.alfaro.vismarjay.fragments.databinding.ActivityHomeBinding
import ph.kodego.alfaro.vismarjay.fragments.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}