package com.example.itogovai.viewmodel

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.itogovai.R
import com.example.itogovai.model.api.PostApi
import com.example.itogovai.model.database.Post
import com.example.itogovai.model.database.PostDatabase
import com.example.itogovai.model.model.StateFragments
import com.example.itogovai.view.AddFragment
import com.example.itogovai.view.ViewFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val _postsLiveData: MutableLiveData<List<Post>> = MutableLiveData(emptyList())
    val postLiveData: LiveData<List<Post>> = _postsLiveData

    private val _currentFragment: MutableLiveData<StateFragments> =
        MutableLiveData(StateFragments.View)
    val currentFragment: LiveData<StateFragments> = _currentFragment

    var database: PostDatabase? = null

    fun goToView(fragmentManager: FragmentManager, viewFragment: ViewFragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.container, viewFragment)
            .commit()
    }

    fun goToAdd(fragmentManager: FragmentManager, addFragment: AddFragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.container, addFragment)
            .commit()
    }

    fun getPostFromServer(postApi: PostApi, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = postApi.getPosts()
            putToDB(response.body()!!, context)
        }
    }

    fun putToDB(posts: List<Post>, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (database == null) {
                database = Room.databaseBuilder(context, PostDatabase::class.java, "posts")
                    .build()
            }
            database!!.getDao().insertPost(*posts.toTypedArray())
        }
    }

    fun getPostsFromDb(context: Context): List<Post> {
        viewModelScope.launch(Dispatchers.IO) {
            if (database == null) {
                database = Room.databaseBuilder(context, PostDatabase::class.java, "posts")
                    .build()
            }
            _postsLiveData.postValue(database!!.getDao().getPosts())
        }
    }
    fun putPostToDb(c)
}

