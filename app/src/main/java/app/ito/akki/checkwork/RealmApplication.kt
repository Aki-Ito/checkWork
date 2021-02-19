package app.ito.akki.checkwork

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        //データベースに保存するモデルに変更を加えた時
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}