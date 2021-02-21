package app.ito.akki.checkwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        //タスクリストが空だった時にダミーデータを表示する
        if (bookList.isEmpty()){
//            createDummiyData()
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        //RecyclerViewAdapterにデータを渡してあげる
        val adapter = RecyclerViewAdapter(this, bookList, true)
        recyclerView.adapter = adapter

        val adapter2 = BookAdapter(this, bookList, object : BookAdapter.OnItemClickListener{
            override fun onItemClick(item: BookShelf){
                //クリック時の処理をここに記述

            }
        }, true)


        //フローティングアクションボタンを押した時の動作
        //addActivityボタンに画面遷移できるようにする
        fab.setOnClickListener{
            val toAddActivityIntent = Intent(this,addActivity::class.java)
            startActivity(toAddActivityIntent)
        }


    }

    fun readAll() : RealmResults<BookShelf> {
        return realm.where(BookShelf::class.java).findAll()
    }

}