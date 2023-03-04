package ph.kodego.alfaro.vismarjay.module_2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ph.kodego.alfaro.vismarjay.module_2.databinding.ActivityLoginBinding
import ph.kodego.alfaro.vismarjay.module_2.utility.PreferenceUtility

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var preferencesUtility: PreferenceUtility


    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result->
        val data = result.data

        Snackbar.make(binding.root,
        "Registered ${data!!.getStringExtra("username")}",
        Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesUtility = PreferenceUtility(applicationContext)

        binding.usernametext.setText(preferencesUtility.getStringPreferences("username"))
        binding.passwordtext.setText(preferencesUtility.getStringPreferences("password"))


        binding.btnSubmit.setOnClickListener{

            username = binding.usernametext.text.toString()
            password = binding.passwordtext.text.toString()

            preferencesUtility.saveStringPreferences("username",binding.usernametext.text.toString())
            preferencesUtility.saveStringPreferences("password",binding.usernametext.text.toString())

//            Snackbar.make(binding.root,"$username - $password",Snackbar.LENGTH_SHORT).show()

            var goToHome = Intent(this,MainActivity::class.java)


            val bundle = Bundle()
            bundle.putString("username",username)
            bundle.putString("password", password)
            goToHome.putExtras(bundle)

            goToHome.putExtra("something","Extra")

            startActivity(goToHome)
            finish()
        }

        binding.btnRegister.setOnClickListener{
            var goToRegister = Intent(this,RegisterActivity::class.java)
            launchRegister.launch(goToRegister)
        }

    }

    override fun onBackPressed() {

    }
}