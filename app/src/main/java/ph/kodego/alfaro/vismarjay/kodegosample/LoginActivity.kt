package ph.kodego.alfaro.vismarjay.kodegosample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ph.kodego.alfaro.vismarjay.kodegosample.databinding.ActivityLoginBinding
import ph.kodego.alfaro.vismarjay.kodegosample.databinding.ActivityLoginBinding.inflate

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener{

            username = binding.usernametext.text.toString()
            password = binding.passwordtext.text.toString()

//            Snackbar.make(binding.root,"$username - $password",Snackbar.LENGTH_SHORT).show()

            var goToHome = Intent(this,HomeActivity::class.java)


            val bundle = Bundle()
            bundle.putString("username",username)
            bundle.putString("password", password)
            goToHome.putExtras(bundle)

            goToHome.putExtra("something","Extra")

            startActivity(goToHome)
            finish()



        }
    }
}