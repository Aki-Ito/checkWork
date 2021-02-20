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
import io.realm.RealmResults

class RecyclerViewAdapter(private val context: Context,
                          private var bookList: OrderedRealmCollection<bookShelf>?,
                          private val autoUpdate: Boolean

): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(bookList, autoUpdate), Parcelable {
   //RecyclerViewに表示されるリストを宣言している
   val items: MutableList<bookShelf> = mutableListOf()

    //ViewHolderクラスを定義して,idを関連付けする
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookName : TextView = view.findViewById(R.id.booknameTextView)
        val authorName : TextView = view.findViewById(R.id.authorTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = bookList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //itemsのposition番目の要素をviewに表示するコードをかく。
       val book: bookShelf = bookList?.get(position) ?: return
        holder.bookName.text = book.title
        holder.authorName.text = book.authorName
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (autoUpdate) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecyclerViewAdapter> {
        override fun createFromParcel(parcel: Parcel): RecyclerViewAdapter {
            return RecyclerViewAdapter(parcel)
        }

        override fun newArray(size: Int): Array<RecyclerViewAdapter?> {
            return arrayOfNulls(size)
        }
    }
}