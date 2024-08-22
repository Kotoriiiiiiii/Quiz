package app.komatsuzaki.kotori.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.komatsuzaki.kotori.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        // クイズ画面からクイズ数を受けとる
        val quizCount: Int = intent.getIntExtra("QuizCount", 0)

        // クイズ数をTextViewに反映する
        binding.quizCountText.text = "$quizCount 問中・・・"

        // 正解数を受け取る
        val correctCount: Int = intent.getIntExtra("CorrectCount", 0)

        //正解数をTextViewに反映する
        binding.correctCountText.text = correctCount.toString()

        //　もう一回ボタンがタップされたら
        binding.againButton.setOnClickListener {
            //クイズ画面へ移動する準備をする
            val quizIntent: Intent = Intent(this, QuizActivity::class.java)
            //クイズ画面に移動する
            startActivity(quizIntent)

        }
    }



}