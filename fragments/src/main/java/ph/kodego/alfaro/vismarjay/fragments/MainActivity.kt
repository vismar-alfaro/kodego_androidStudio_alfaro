package ph.kodego.alfaro.vismarjay.fragments

import androidx.appcompat.app.AppCompatActivity
import ph.kodego.alfaro.vismarjay.fragments.databinding.ActivityMainBinding
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}