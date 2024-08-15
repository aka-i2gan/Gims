package com.example.quiz_1_4.classes

data class Question (
    val id: String,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: String,
    val comment: String
)