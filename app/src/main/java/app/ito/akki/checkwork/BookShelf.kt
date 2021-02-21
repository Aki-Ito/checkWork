package app.ito.akki.checkwork

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.*


open class BookShelf(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var title: String = "",
    open var authorName: String = "",
    open var money: String = "",
    open var explanation: String = ""
    //Realmで保存できる型にする
) : RealmObject()