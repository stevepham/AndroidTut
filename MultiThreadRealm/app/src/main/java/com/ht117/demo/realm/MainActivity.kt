package com.ht117.demo.realm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvContent.adapter = adapter

        btnInsert.setOnClickListener {
            val write = WriteThread()
            val read = ReadThread()
            write.start()
            write.join()
            read.start()
            read.join()
        }
    }

    override fun onResume() {
        super.onResume()
        ReadThread().start()
    }

    private fun generateItem(name: String): UserEntity {
        val rnd = Random(System.currentTimeMillis())
        val old = rnd.nextInt(18, 55)
        return UserEntity(UUID.randomUUID().toString(), name, old)
    }

    inner class WriteThread: Thread(Runnable {
        val realm = Realm.getDefaultInstance()

        realm.executeTransaction {
            it.insert(generateItem(currentThread().name))
        }
    })

    inner class ReadThread: Thread(Runnable {
        val realm = Realm.getDefaultInstance()
        val threadName = currentThread().name
        val users = realm.where(UserEntity::class.java)
            .findAll()
        val models = users.map { entityToModel(it) }
        runOnUiThread {
            tvValue.text = threadName
            tvTotalValue.text = "${models.size}"
            adapter.display(models)
        }
    })
}
