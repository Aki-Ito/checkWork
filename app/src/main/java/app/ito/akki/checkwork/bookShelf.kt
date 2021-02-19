package app.ito.akki.checkwork

import io.realm.RealmObject

open class bookShelf(
    open var title: String = "",
    open var authorName: String = "",
    open var money: String = "",
    open var explanation: String = ""
    //Realmで保存できる型にする
) : RealmObject()