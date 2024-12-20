package dduw.com.mobile.finalreport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.finalreport.data.DramaDto
import dduw.com.mobile.finalreport.databinding.ActivityShowBinding

class ShowActivity  : AppCompatActivity() {
    val showBinding by lazy {
        ActivityShowBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(showBinding.root)

        val dramas = intent.getSerializableExtra("selectedDrama") as DramaDto

        showBinding.dramaPhoto.setImageResource(dramas.photo)
        showBinding.dramaName.setText(dramas.name)
        showBinding.dramaMade.setText(dramas.made)
        showBinding.dramaActor.setText(dramas.actor)
        showBinding.dramaActress.setText(dramas.actress)
        showBinding.dramaDirector.setText(dramas.director)
        showBinding.dramaAuthor.setText(dramas.author)
        showBinding.dramaReview.setText(dramas.review)

        showBinding.showEnd.setOnClickListener {
            finish()
        }
    }
}