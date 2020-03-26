package com.ht117.demo.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class DemoApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("multithread-realm")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}