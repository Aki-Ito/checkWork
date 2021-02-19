package app.ito.akki.checkwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_list.*


class listActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()
    //データを全取得






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        val bookSaved: RealmResults<bookShelf> = read()
        //タスクリストが空だった時にダミーデータを表示する
        if (bookSaved.isEmpty()){
//            createDummiyData()
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        //RecyclerViewAdapterで宣言したaddAllメソッドを呼んであげて,データを渡してあげる
        val adapter = RecyclerViewAdapter(this, bookSaved, true)
        recyclerView.adapter = adapter



    }


    fun read() : RealmResults<bookShelf> {
        return realm.where(bookShelf::class.java).findAll()
    }

}