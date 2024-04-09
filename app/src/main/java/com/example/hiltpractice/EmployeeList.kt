package com.example.hiltpractice

data class EmployeeList(
    val `data`: List<Employee>,
    val message: String,
    val status: String
)

data class Employee(
    val employee_age: Int,
    val employee_name: String,
    val employee_salary: Int,
    val id: Int,
    val profile_image: String
)