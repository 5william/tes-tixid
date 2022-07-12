package com.virakumaro.testixid.ui

import androidx.lifecycle.*
import com.virakumaro.testixid.data.repository.UserRepository
import kotlinx.coroutines.flow.*

class MainViewModel(private val repository: UserRepository): ViewModel() {
    private val _lastId = MutableLiveData(0L)
    private val lastId: LiveData<Long> = _lastId

    fun setLastId(lastId:Long){
        _lastId.value = lastId
    }

    fun getUsers() = lastId.switchMap {
        repository.getUsers(it).asLiveData()
    }

}