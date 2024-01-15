package com.example.hiltpractice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor (private val apiService: ApiService) : ViewModel() {

    var userList : List<UserData> by mutableStateOf(listOf())
    var movieListResponse:List<Movie> by mutableStateOf(listOf())

    var errorMessage : String by mutableStateOf("")



    fun getUserList(){
        viewModelScope.launch {

            try {
                val users = apiService.getUsers(2)
                userList = users.body()!!.data

            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }




    fun getMovieList() {
        viewModelScope.launch {
            try {
                val movieList = apiService.getMovies()
                if (movieList.isNotEmpty()) {
                    movieListResponse = movieList
                }
                else{
                    print("Response is empty")
                }
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}