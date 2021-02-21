package app.ito.akki.checkwork

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmChangeListener
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_book_data_cell.view.*

class BookAdapter(
    private val context: Context,
    private var bookList: OrderedRealmCollection<BookShelf>?,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<BookShelf, BookAdapter.BookViewHolder>(bookList, autoUpdate) {

    //リスナー格納用変数
    lateinit var listener: OnItemClickListener


    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: LinearLayout = view.findViewById(R.id.container)
        val bookName: TextView = view.findViewById(R.id.booknameTextView)
        val authorName: TextView = view.findViewById(R.id.authorTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = bookList?.size ?: 0

    //ViewHolderに表示する画像とテキストを挿入
    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        //押されたポジションの本
        val book: BookShelf = bookList?.get(position) ?: return

        holder.bookName.text = book.title
        holder.authorName.text = book.authorName

        //タップした時
        holder.container.setOnClickListener {
            listener.onItemClickListener(it, position, book)
        }
    }


    interface OnItemClickListener {
        //実際にリストが押された時に呼び出されるメソッド
        fun onItemClickListener(view: View, position: Int, clickedBook: BookShelf)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        //Activityで書いた処理の内容を代入してる
        this.listener = listener
    }

}
