package app.komatsuzaki.kotori.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import app.komatsuzaki.kotori.quiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityQuizBinding

    // クイズを作る
    val quizLists: List<List<String>> = listOf(
        listOf("Androidコースのキャラクターの名前は？","ランディ","フィル","ドロイド", "ランディ"),
        listOf("Androidを開発する言語はどれ？","JavaScript","Kotlin","Swift", "Kotlin"),
        listOf("ImageViewは、何を扱える？","文字","音声","画像", "何も扱えない"),
    )

    // クイズをシャッフルして変数に入れる
    val shuffledLists:List<List<String>> = quizLists.shuffled()

    //　クイズ数をカウントする変数を作る
    var quizCount: Int = 0

    //　正解の回数を入れる変数を作る
    var correctCount: Int = 0

    // 正解の答えを入れる変数を作る
    var correctAnswer: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        binding = ActivityQuizBinding.inflate(layoutInflater).apply{ setContentView(this.root) }

        // クイズを表示する
        showQuestion()

        // 1つ目のボタンがタップされたら
        binding.answerButton1.setOnClickListener {
            //回答をチェックする
            checkAnswer(binding.answerButton1.text.toString())
        }
        // 2つ目のボタンがタップされたら
        binding.answerButton2.setOnClickListener {
            //回答をチェックする
            checkAnswer(binding.answerButton2.text.toString())
        }
        // 3つ目のボタンがタップされたら
        binding.answerButton3.setOnClickListener {
            //回答をチェックする
            checkAnswer(binding.answerButton3.text.toString())
        }

        // 次に進むボタンがタップされたら
        binding.nextButton.setOnClickListener {
            // 現在のクイズ数と、全問クイズ数が一致するか比較して
            if(quizCount == quizLists.size) {
                // 一緒だったらリザルト画面
                val resultIntent: Intent = Intent(this, ResultActivity::class.java)

                // クイズ数をセットする
                resultIntent.putExtra("QuizCount", quizLists.size)

                // 正解数をセットする
                resultIntent.putExtra("CorrectCount", correctCount)

                // 結果画面に移動する
                startActivity(resultIntent)

            } else {
                // 一緒でなければ、判定画像を非表示する
                binding.judgeImage.isVisible = false
                binding.judgeImage.isVisible = false
                //回答ボタンを有効に
                binding.answerButton1.isEnabled = true
                binding.answerButton2.isEnabled = true
                binding.answerButton3.isEnabled = true
                //正解表示をリセットする
                binding.correctAnswerText.text = " "
                //クイズを表示
                showQuestion()
            }
        }




    }
    // 画面にクイズを表示させる関数
    fun showQuestion() {
        // クイズを取り出す
        val question: List<String> = shuffledLists[quizCount]

        // クイズの中身を確認する
        Log.d("debug", question.toString())

        // クイズを、TextViewに反映する
        binding.quizText.text = question[0]

        // クイズの選択肢を表示する
        binding.answerButton1.text = question[1]
        binding.answerButton2.text = question[2]
        binding.answerButton3.text = question[3]

        // クイズの正しい答えをセットする
        correctAnswer = question[4]

    }

    // 回答をチェックする関数
    fun checkAnswer(answerText: String) {
        // タップされた回答と、正しい答えを比べて
        if (answerText == correctAnswer) {
            binding.judgeImage.setImageResource(R.drawable.maru_image)
            // 正解した回数をカウントする
            correctCount++
        } else {
            //　違っていたら、×画像を反映する
            binding.judgeImage.setImageResource(R.drawable.batu_image)
        }
        //　判定画像を表示する
        showAnswer()
        //　クイズ数をカウントする
        quizCount++
    }

    fun showAnswer() {
        //正解表示
        binding.correctAnswerText.text = "正解:$correctAnswer"
        // 判定画像を表示する
        binding.judgeImage.isVisible = true
        // 次へボタンを表示する
        binding.nextButton.isVisible = true
        // 回答ボタンを無効にする
        binding.answerButton1.isEnabled = false
        binding.answerButton2.isEnabled = false
        binding.answerButton3.isEnabled = false
    }

}