package com.example.listapp.ui.main.ui

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.listapp.models.TaskList

//1 karena menggunakan sharedPreference Ini
// memungkinkan Anda untuk menulis pasangan nilai kunci ke SharedPreferences.
class MainViewModel(private val sharedPreferences : SharedPreferences) : ViewModel() {

    //2
    lateinit var onListAdded : (() -> Unit)

    //3 Tambahkan properti bernama daftar, yang dibuat dengan malas.
    // Apa artinya ini sampai Anda memanggil properti, properti itu kosong.
    // Setelah Anda memanggil properti, properti akan diisi dengan memanggil
    // retrieveLists(). Ini adalah cara praktis untuk menghindari kueri data
    // yang tidak perlu sampai Anda membutuhkannya.
    val lists : MutableList<TaskList> by lazy {
        retrieveLists()
    }

    //4
    private fun retrieveLists() : MutableList<TaskList> {
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in sharedPreferencesContents){
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key,itemsHashSet)
            taskLists.add(list)
        }
        return taskLists
    }

    //5
    fun saveList(list: TaskList){
        sharedPreferences.edit().putStringSet(list.name,list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }

    fun updateList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name,
            list.tasks.toHashSet()).apply()
        lists.add(list)
    }
    fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }
}