package app.komatsuzaki.kotori.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.komatsuzaki.kotori.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        // クイズアプリ画面へ移動する準備をする(画面遷移に必要なもの)
        val quizIntent: Intent = Intent(this,QuizActivity::class.java)

        // STARTボタンがタップされたら
        binding.startButton.setOnClickListener {
            // クイズ画面に移動する
            startActivity(quizIntent)
        }
    }


}