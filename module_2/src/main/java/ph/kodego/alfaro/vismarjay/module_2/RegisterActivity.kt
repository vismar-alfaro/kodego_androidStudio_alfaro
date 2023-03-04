package ph.kodego.alfaro.vismarjay.module_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ph.kodego.alfaro.vismarjay.module_2.databinding.ActivityLoginBinding
import ph.kodego.alfaro.vismarjay.module_2.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener{
            val intent = Intent()
            intent.putExtra("username",binding.registerusername.text.toString())
            setResult(1,intent)
            finish()
        }
    }
}