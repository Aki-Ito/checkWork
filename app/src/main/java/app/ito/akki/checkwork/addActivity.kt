package app.ito.akki.checkwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*

class addActivity : AppCompatActivity() {
    //realmの変数を宣言
    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        addButton.setOnClickListener {
            val toListActivity = Intent(this,listActivity::class.java)
            startActivity(toListActivity)


            //Realmを終了する処理
            realm.close()
        }
    }
}