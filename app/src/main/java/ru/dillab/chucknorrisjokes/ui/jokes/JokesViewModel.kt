package ru.dillab.chucknorrisjokes.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dillab.chucknorrisjokes.network.JokesApi
import ru.dillab.chucknorrisjokes.network.JokesProperty
import java.lang.Exception


// Enum class for loading status
enum class JokesApiStatus { LOADING, ERROR, DONE }

class JokesViewModel : ViewModel() {

    //    Status value enum class, is used in BindingAdapter.kt
    private val _status = MutableLiveData<JokesApiStatus>()
    val status: LiveData<JokesApiStatus>
        get() = _status

    //    Data that we fetch from https://api.icndb.com API
    private val _jokesDataList = MutableLiveData<List<JokesProperty>>()
    val jokesDataList: LiveData<List<JokesProperty>>
        get() = _jokesDataList

    //    Number of jokes to fetch that is entered in editText View field
    val numberOfJokes = MutableLiveData<String>()

    fun getData() {
        /**
         * We check that [numberOfJokes.value] is not null and is not empty, because we dont
         * want to get "Connection error" message when we tap on RELOAD button with empty
         * editText View field
         */
        if (numberOfJokes.value != null && numberOfJokes.value != "") {
            viewModelScope.launch {
                _status.value = JokesApiStatus.LOADING
                try {
//                    Fetching JSON data from API, result is ResponseProperty object
                    val responseProperty =
                        JokesApi.retrofitService.getProperties(numberOfJokes.value)
//                    Getting List of JokesProperty objects
                    _jokesDataList.value = responseProperty.listWithJokes
                    _status.value = JokesApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = JokesApiStatus.ERROR
//                     Erasing all data in jokesDataList
                    _jokesDataList.value = listOf()
                }
            }
        }
    }
}