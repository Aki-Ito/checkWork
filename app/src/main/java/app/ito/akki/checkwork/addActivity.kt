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

        val book: BookShelf? = read()
        if (book != null){
            //editTextに保存した内容が保存されているようにする
            titleEditText.setText(book.title)
            authorEditText.setText(book.authorName)
            moneyEditText.setText(book.money)
            explanationEditText.setText(book.explanation)
        }

        //ボタンを押したときにrealmの保存が行われる処理を実行する
        //元からデータが保存されている場合、更新処理を行う
        addButton.setOnClickListener {
            val toListActivity = Intent(this,ListActivity::class.java)
            startActivity(toListActivity)

            val title: String = titleEditText.text.toString()
            val author: String = authorEditText.text.toString()
            val money: String = moneyEditText.text.toString()
            val explanation: String = explanationEditText.text.toString()
            //保存ボタンを押した時に、titleEditTextとcontentEditTextに入力されたテキストを取得し
            //save()メソッドに値を渡す
            save(title,author,money,explanation)

            //Realmを終了する処理
            realm.close()
        }


    }

    fun save(title: String, author: String, money: String, explanation: String) {
        //保存する処理を中に記述するようにする
        val book: BookShelf? = read()
        realm.executeTransaction {
            if (book != null) {
                book.title = title
                book.authorName = author
                book.money = money
                book.explanation = explanation
            } else {
                //メモの新規作成
                val newBook: BookShelf = it.createObject(BookShelf::class.java)
                newBook.title = title
                newBook.authorName = author
                newBook.money = money
                newBook.explanation = explanation
            }
            if (book != null) {
                println("${book.title}")
            }
        }
    }

    fun read() : BookShelf? {
        return realm.where(BookShelf::class.java).findFirst()
    }
}