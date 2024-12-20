package dduw.com.mobile.finalreport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.finalreport.databinding.ActivityIntroduceBinding

class IntroduceActivity : AppCompatActivity() {
    val introBinding by lazy {
        ActivityIntroduceBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(introBinding.root)

        introBinding.introImg.setImageResource(R.mipmap.j)
        introBinding.introName.setText("20211689 김현진")
        introBinding.introClass.setText("모바일 소프트웨어 01분반")

        introBinding.introBye.setOnClickListener {
            finish()
        }
    }
}