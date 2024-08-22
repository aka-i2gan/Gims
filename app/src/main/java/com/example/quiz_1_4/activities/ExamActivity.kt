package com.example.quiz_1_4.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz_1_4.Files.Constants
import com.example.quiz_1_4.Files.VVP
import com.example.quiz_1_4.R
import com.example.quiz_1_4.classes.Question
import com.example.quiz_1_4.databinding.ActivityExamBinding

class ExamActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("SetTextI18n")

    lateinit var bndCl: ActivityExamBinding

    // номер вопроса
    private var mCurrentPosition: Int = 1
    // список вопросов
    private var mQuestionList: ArrayList<Question>? = null
    // номер ответа
    private var mSelectedOptionPosition: Int = 0
    // количество правильных ответов
    private var mCorrectAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bndCl = ActivityExamBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(bndCl.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
         * Toolbar
         */
        setSupportActionBar(bndCl.tbExam)
        supportActionBar?.apply {
            title = "Экзамен"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        // Меняем цвет
        bndCl.tbExam.navigationIcon?.apply {
            colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
        }
        /*
         */

        // Получаем строку из номеров блоков для экзамена
        val examBlocks: String = intent.getStringExtra("examBlocks").toString()
        // Строку в массив
        val blocksArr = mutableListOf<Char>()
        for (char in examBlocks.indices) {
            blocksArr += examBlocks[char]
        }
        /*
         * End of Old
         */


        /*
         * из урока
         */
        // получаем список вопросов
        mQuestionList = VVP.getVVPQuestions()

        setQuestion()

        bndCl.tvOptionOne.setOnClickListener(this)
        bndCl.tvOptionTwo.setOnClickListener(this)
        bndCl.tvOptionThree.setOnClickListener(this)
        bndCl.tvOptionFour.setOnClickListener(this)

        bndCl.btnSubmit.setOnClickListener(this)

    }

    // выбранный ответ
    fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        // если перехода на 1-й номер вопроса еще не было
        if (mSelectedOptionPosition == 0) {
            // переходим
            mCurrentPosition++

            when {
                // если не достигли последнего вопроса
                mCurrentPosition <= mQuestionList!!.size -> {
                    setQuestion()
                }

                // если достигли
                else -> {
                    val intent = Intent(this, ResultActivity::class.java)
                    // отправляем количество правильных ответов
                    intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                    // и общее количество вопросов
                    intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                    startActivity(intent)
                    finish()
                }
            }

            // если не на нулевом вопросе
        } else {
            // получаем очередной вопрос
            val question = mQuestionList?.get(mCurrentPosition - 1)

            // получаем номер текстового блока с правильным ответом
            var correctPos = 0

            if (question!!.correctAnswer == bndCl.tvOptionOne.text.toString()) {
                correctPos = 1
            } else if (question!!.correctAnswer == bndCl.tvOptionTwo.text.toString()) {
                correctPos = 2
            } else if (question!!.correctAnswer == bndCl.tvOptionThree.text.toString()) {
                correctPos = 3
            } else if (question!!.correctAnswer == bndCl.tvOptionFour.text.toString()) {
                correctPos = 4
            }


            // если номер правильного ответа не совпадает с выбранным номером
            if (correctPos != mSelectedOptionPosition) {
                // подсвечиваем красным неправильный ответ
                answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
            } else {

                // увеличиваем количество правильных ответов
                mCorrectAnswers++
            }

            // подсвечиваем зеленым правильный ответ
            answerView(correctPos, R.drawable.correct_option_border_bg)

            if (mCurrentPosition == mQuestionList!!.size) {
                bndCl.btnSubmit.text = "Завершить"
            } else {
                bndCl.btnSubmit.text = "Слещующий вопрос"
            }
            mSelectedOptionPosition = 0
        }
    }

    // ответ по дефолту
    fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, bndCl.tvOptionOne)
        options.add(0, bndCl.tvOptionTwo)
        options.add(0, bndCl.tvOptionThree)
        options.add(0, bndCl.tvOptionFour)

        for (option in options) {
            // цвет текста в каждом текстовом блоке
            option.setTextColor(Color.parseColor("#7A8089"))
            // стиль текста
            option.typeface = Typeface.DEFAULT_BOLD
            // фон
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    fun setQuestion() {

        // вопрос из списка вопросов
        val question = mQuestionList!![mCurrentPosition - 1]

        // задаем блоки ответов по дефолту
        defaultOptionsView()

        // Если последний вопрос - текст кнопки - Finish
        if (mCurrentPosition == mQuestionList!!.size) {
            bndCl.btnSubmit.text = "Завершить"
        } else {
            bndCl.btnSubmit.text = "Следующий вопрос"
        }

        // Progress Bar
        bndCl.progressBar.progress = mCurrentPosition
        bndCl.tvProgress.text = "$mCurrentPosition / ${bndCl.progressBar.max}"

        // Текст вопроса
        bndCl.tvQuestion.text = question!!.question

        // картинка вопроса
        bndCl.ivQuestionImage.setImageResource(question.image)

        // получаем массив вариантов ответов и перемешиваем его
        val answers = arrayOf(question.optionOne, question.optionTwo, question.optionThree, question.optionFour)
        answers.shuffle()

        // варианты ответов помещаем в текстовые блоки
        bndCl.tvOptionOne.text = answers[0]
        bndCl.tvOptionTwo.text = answers[1]
        bndCl.tvOptionThree.text = answers[2]
        bndCl.tvOptionFour.text = answers[3]
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(bndCl.tvOptionOne, 1)
            }

            R.id.tvOptionTwo -> {
                selectedOptionView(bndCl.tvOptionTwo, 2)
            }

            R.id.tvOptionThree -> {
                selectedOptionView(bndCl.tvOptionThree, 3)
            }

            R.id.tvOptionFour -> {
                selectedOptionView(bndCl.tvOptionFour, 4)
            }

            /*
             * Выбор правильного ответа и переход к след. вопросу
             */

            R.id.btnSubmit -> {
                // если перехода на 1-й номер вопроса еще не было
                if (mSelectedOptionPosition == 0) {
                    // переходим
                    mCurrentPosition++

                    when {
                        // если не достигли последнего вопроса
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }

                        // если достигли
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            // отправляем количество правильных ответов
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            // и общее количество вопросов
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    // функция вызывает красный или зеленый блок
    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                bndCl.tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            2 -> {
                bndCl.tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            3 -> {
                bndCl.tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            4 -> {
                bndCl.tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }
}