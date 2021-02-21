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

class BookAdapter (
        private val context: Context,
        private var bookList: OrderedRealmCollection<BookShelf>?,
        private var listener: OnItemClickListener,
        private val autoUpdate: Boolean
) :
        RealmRecyclerViewAdapter<BookShelf, BookAdapter.BookViewHolder>(bookList, autoUpdate) {

       override fun getItemCount(): Int = bookList?.size ?: 0

       override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
            val book: BookShelf = bookList?.get(position) ?: return

        holder.container.setOnClickListener{
            listener.onItemClick(book)
           }

        holder.bookName.text = book.title
        holder.authorName.text = book.authorName
       }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell, parent, false)
        return BookViewHolder(view)
    }

       class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container : LinearLayout = view.findViewById(R.id.container)
        val bookName : TextView = view.findViewById(R.id.booknameTextView)
        val authorName : TextView = view.findViewById(R.id.authorTextView)

       }

    interface OnItemClickListener {
        fun onItemClick(item: BookShelf)
    }

}
