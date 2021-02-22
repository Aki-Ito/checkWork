package app.ito.akki.checkwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class addActivity : AppCompatActivity() {
    //realmの変数を宣言
    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        //ツールバーの設定
        setSupportActionBar(findViewById(R.id.toolbar))

        val id = intent.getStringExtra("book")

        if (id != null) {
            val book: BookShelf? = realm.where(BookShelf::class.java).equalTo("id", id).findFirst()
            titleEditText.setText(book?.title)
            authorEditText.setText(book?.authorName)
            moneyEditText.setText(book?.money)
            explanationEditText.setText(book?.explanation)
        }
        //ボタンを押したときにrealmの保存が行われる処理を実行する
        //元からデータが保存されている場合、更新処理を行う
        addButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val author: String = authorEditText.text.toString()
            val money: String = moneyEditText.text.toString()
            val explanation: String = explanationEditText.text.toString()
            //保存ボタンを押した時に、titleEditTextとcontentEditTextに入力されたテキストを取得し
            //save()メソッドに値を渡す
            save(title, author, money, explanation)


            val toListActivity = Intent(this,ListActivity::class.java)
           startActivity(toListActivity)

        }


    }

    override fun onDestroy() {
        super.onDestroy()

        realm.close()
    }

    fun save(title: String, author: String, money: String, explanation: String) {
        //保存する処理を中に記述するようにする
        realm.executeTransaction {

            val id = intent.getStringExtra("book")

            val book: BookShelf? = realm.where(BookShelf::class.java)
                .equalTo("id", id)
                .findFirst()
            if (id != null) {

                book?.title = titleEditText.text.toString()
                book?.authorName = authorEditText.text.toString()
                book?.money = moneyEditText.text.toString()
                book?.explanation = explanationEditText.text.toString()

            } else {
                // `UUID.randomUUID().toString()`をBookShelf::class.javaの後ろに追加する ,
                //val newBook: BookShelf = it.createObject(BookShelf::class.java, UUID.randomUUID().toString())　
                val newBook: BookShelf =
                    it.createObject(BookShelf::class.java, UUID.randomUUID().toString())
                newBook.title = title
                newBook.authorName = author
                newBook.money = money
                newBook.explanation = explanation
            }

        }

    }
}