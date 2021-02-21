package app.ito.akki.checkwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    //Realmのデータの取得はlazyで行う
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //受け取った変数を入れる
        val id = intent.getStringExtra("book")

        val book: BookShelf? = realm.where(BookShelf::class.java)
            .equalTo("id", id)
            .findFirst()
        textView2.text = book?.title
        textView3.text = book?.authorName
        textView6.text = book?.money
        textView8.text = book?.explanation


    }


}