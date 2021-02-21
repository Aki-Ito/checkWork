package app.ito.akki.checkwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm

class DetailActivity : AppCompatActivity() {


    //Realmのデータの取得はlazyで行う
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}