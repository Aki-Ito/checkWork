package app.ito.akki.checkwork

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults

class RecyclerViewAdapter(private val context: Context,
                          private var bookList: OrderedRealmCollection<BookShelf>?,
                          private val autoUpdate: Boolean

): RealmRecyclerViewAdapter<BookShelf,RecyclerViewAdapter.BookShelfViewHolder>(bookList, autoUpdate) {
   //RecyclerViewに表示されるリストを宣言している
   val items: MutableList<BookShelf> = mutableListOf()

    //ViewHolderクラスを定義して,idを関連付けする
    class BookShelfViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookName : TextView = view.findViewById(R.id.booknameTextView)
        val authorName : TextView = view.findViewById(R.id.authorTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookShelfViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell, parent, false)
        return BookShelfViewHolder(view)
    }

    override fun getItemCount(): Int = bookList?.size ?: 0

    override fun onBindViewHolder(holder: BookShelfViewHolder, position: Int) {
        //itemsのposition番目の要素をviewに表示するコードをかく。
       val book: BookShelf = bookList?.get(position) ?: return
        holder.bookName.text = book.title
        holder.authorName.text = book.authorName
    }


}