package dduw.com.mobile.finalreport

import android.content.ContentValues
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.finalreport.data.DramaDBHelper
import dduw.com.mobile.finalreport.data.DramaDao
import dduw.com.mobile.finalreport.data.DramaDto
import dduw.com.mobile.finalreport.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    val dao: DramaDao by lazy {
        DramaDao(this)
    }
    //lateinit var dao: DramaDao

    val addBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //addBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(addBinding.root)

        /*EditText에서 값을 읽어와 DB에 저장*/
        addBinding.btnAddDrama.setOnClickListener{
            if (addBinding.etAddName.text.toString().equals("")){      //제목 필수 입력 확인
                Toast.makeText(this@AddActivity, "드라마 제목을 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                val photo = R.mipmap.plusplz
                val name = addBinding.etAddName.text.toString()
                val where = addBinding.editAddMade.text.toString()
                val actor = addBinding.etAddActor.text.toString()
                val actress = addBinding.etAddActress.text.toString()
                val director = addBinding.etAddDirector.text.toString()
                val author = addBinding.etAddAuthor.text.toString()
                val review = addBinding.etAddReview.text.toString()

                if (dao.addDrama(DramaDto(0, photo, name, where, actor, actress, director, author, review)) > 0) {
                    //Toast.makeText(this@AddActivity, "1", Toast.LENGTH_SHORT).show()
                    setResult(AppCompatActivity.RESULT_OK)
                } else {
                    //Toast.makeText(this@AddActivity, "2", Toast.LENGTH_SHORT).show()
                    setResult(AppCompatActivity.RESULT_CANCELED)
                }
                //Toast.makeText(this@AddActivity, "3", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        addBinding.btnAddCancel.setOnClickListener{
            setResult(AppCompatActivity.RESULT_CANCELED)

            finish()
        }

    }

//    fun addDrama(newDto : DramaDto) : Long {// Int {
//        val helper = DramaDBHelper(this)
//        val db = helper.writableDatabase
//
//        val newRow = ContentValues()
//        newRow.put(DramaDBHelper.COL_PHOTO, newDto.photo)
//        newRow.put(DramaDBHelper.COL_NAME, newDto.name)
//        newRow.put(DramaDBHelper.COL_MADE, newDto.made)
//        newRow.put(DramaDBHelper.COL_ACOTR, newDto.actor)
//        newRow.put(DramaDBHelper.COL_ACTRESS, newDto.actress)
//        newRow.put(DramaDBHelper.COL_DIRECTOR, newDto.director)
//        newRow.put(DramaDBHelper.COL_AUTHOR, newDto.author)
//        newRow.put(DramaDBHelper.COL_REVIEW, newDto.review)
//
//        val result = db.insert(DramaDBHelper.TABLE_NAME, null, newRow)
//
//        helper.close()
//
////        return 0
//        return result
//    }
}