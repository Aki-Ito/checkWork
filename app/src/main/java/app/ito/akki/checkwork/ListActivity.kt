package app.ito.akki.checkwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_list.fab
import kotlinx.android.synthetic.main.activity_main.*


class ListActivity : AppCompatActivity() {

    //Realmのデータの取得はlazyで行う
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        val bookList: RealmResults<BookShelf> = readAll()


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        //RecyclerViewAdapterにデータを渡してあげる
        // val adapter = RecyclerViewAdapter(this, bookList, true)
        // recyclerView.adapter = adapter

        val adapter2 = BookAdapter(this, bookList, true)
        recyclerView.adapter = adapter2

        //フローティングアクションボタンを押した時の動作
        //addActivityボタンに画面遷移できるようにする
        fab.setOnClickListener {
            val toAddActivityIntent = Intent(this, addActivity::class.java)
            startActivity(toAddActivityIntent)
        }

        //インターフェースの実装
        //リストの要素をタップされた時に取得して、実装できる
        adapter2.setOnItemClickListener(object : BookAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int, clickedBook: BookShelf) {
                val toDetailActivity = Intent(this@ListActivity, DetailActivity::class.java)

                toDetailActivity.putExtra("book", clickedBook.id)
                startActivity(toDetailActivity)


            }
        }


        )

    }

    fun readAll(): RealmResults<BookShelf> {
        return realm.where(BookShelf::class.java).findAll()
    }

}