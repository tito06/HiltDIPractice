package com.example.hiltpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.hiltpractice.ui.theme.HiltPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //val userViewModel by viewModels<UserViewModel>()

    private val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*UserList(userList = userViewModel.userList.collectAsState())
                    userViewModel.getUserList()*/

                    /* MovieList(movieList = userViewModel.movieListResponse)
                     userViewModel.getMovieList()*/

                    EmployeeList(employeeList = userViewModel.employeeList.collectAsState())
                    userViewModel.getEmployee()


                }
            }
        }
    }
}


@Composable
fun EmployeeList(employeeList: State<List<EmployeeData>>){
    LazyColumn{
        items(employeeList.value){
            Employee(it)
        }
    }
}

@Composable
fun Employee(employee:EmployeeData){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(110.dp)
        .padding(8.dp, 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()) {
/*
                Image(
                    painter = rememberImagePainter(data = userResponse.avatar,
                        builder = {
                            scale(Scale.FILL)
                            transformations(CircleCropTransformation())

                        }),
                    contentDescription = userResponse.id.toString(),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f))*/

            Column ( verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f)){

                Text(
                    text = employee.employee_name,
                    fontWeight = FontWeight.Bold
                )

                Text(text = employee.employee_salary.toString())

            }

        }

    }
}

@Composable
fun UserList(userList: State<List<UserData>>){
    LazyColumn{
       /* itemsIndexed(items = userList){index, item ->
            User(userResponse = item)
        }*/

        items(userList.value){
            User(userResponse = it)
        }
    }
}

@Composable
fun MovieList(movieList: List<Movie>) {
    LazyColumn {
        itemsIndexed(items = movieList) { index, item ->
            MovieItem(movie = item)
        }
    }
}




@OptIn(ExperimentalCoilApi::class)
@Composable
fun User(userResponse: UserData){

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(110.dp)
        .padding(8.dp, 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()) {
/*
                Image(
                    painter = rememberImagePainter(data = userResponse.avatar,
                        builder = {
                            scale(Scale.FILL)
                            transformations(CircleCropTransformation())

                        }),
                    contentDescription = userResponse.id.toString(),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f))*/

            Column ( verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f)){

                Text(
                    text = userResponse.first_name,
                    fontWeight = FontWeight.Bold
                )

                Text(text = userResponse.email)

            }

        }

    }

}




@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Surface() {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = movie.imageUrl,

                        builder = {
                            scale(Scale.FILL)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = movie.desc,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.category,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                    Text(
                        text = movie.desc,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }

}