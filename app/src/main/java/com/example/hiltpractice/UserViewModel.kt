package com.example.hiltpractice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class UserViewModel
@Inject constructor (private val apiService: ApiService) : ViewModel() {

    //var userList : List<UserData> by mutableStateOf(listOf())

    private val _userlist = MutableStateFlow<List<UserData>>(emptyList())
    val userList : StateFlow<List<UserData>> = _userlist
    var movieListResponse:List<Movie> by mutableStateOf(listOf())

    var errorMessage : String by mutableStateOf("")


    private  val _employeeList = MutableStateFlow<List<Employee>>(emptyList())
    val employeeList: StateFlow<List<Employee>> = _employeeList


    fun getEmployeeList(){

        viewModelScope.launch {
            try {
                val employee = apiService.getEmployeeList()
                _employeeList.value = employee.body()!!.data
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }


    fun getUserList(){
        viewModelScope.launch {

            try {
                val users = apiService.getUsers()
                _userlist.value = users.body()!!.data

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