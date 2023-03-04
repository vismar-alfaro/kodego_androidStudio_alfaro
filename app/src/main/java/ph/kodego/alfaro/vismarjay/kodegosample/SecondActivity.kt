package ph.kodego.alfaro.vismarjay.kodegosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var title:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        title = findViewById(R.id.title)
//
//        title.setText("New Second Activity")
    }
}