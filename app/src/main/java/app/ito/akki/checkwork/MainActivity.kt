package app.ito.akki.checkwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //フローティングアクションボタンを押した時の動作
        //addActivityボタンに画面遷移できるようにする
        fab.setOnClickListener{
            val toAddActivityIntent = Intent(this,addActivity::class.java)
            startActivity(toAddActivityIntent)
        }
    }
}