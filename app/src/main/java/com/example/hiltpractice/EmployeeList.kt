package com.example.hiltpractice

data class EmployeeList(
    val `data`: List<EmployeeData>,
    val message: String,
    val status: String
)

data class EmployeeData(
    val employee_age: Int,
    val employee_name: String,
    val employee_salary: Int,
    val id: Int,
    val profile_image: String
)