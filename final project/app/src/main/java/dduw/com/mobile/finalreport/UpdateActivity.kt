package dduw.com.mobile.finalreport

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.finalreport.data.DramaDBHelper
import dduw.com.mobile.finalreport.data.DramaDao
import dduw.com.mobile.finalreport.data.DramaDto
import dduw.com.mobile.finalreport.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    val dao: DramaDao by lazy {
        DramaDao(this)
    }
    //lateinit var dao: DramaDao

    val updateBinding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(updateBinding.root)

        val dto = intent.getSerializableExtra("dto") as DramaDto

        updateBinding.etUpdateName.setText(dto.name)     // XML 속성에서 편집금지로 지정하였음
        updateBinding.etUpdateActor.setText(dto.actor)
        updateBinding.etUpdateActress.setText(dto.actress)

        updateBinding.btnUpdateDrama.setOnClickListener {
            dto.actor = updateBinding.etUpdateActor.text.toString()
            dto.actress = updateBinding.etUpdateActress.text.toString()

            if (dao.updateDrama(dto) > 0) {
                setResult(RESULT_OK)
            } else {
                setResult(RESULT_CANCELED)
            }

            finish()
        }

        updateBinding.btnUpdateCancel.setOnClickListener{
            setResult(RESULT_CANCELED)
            finish()
        }
    }

//    fun updateDrama(dto: DramaDto): Int {
//        val helper = DramaDBHelper(this)
//        val db = helper.writableDatabase
//
//        val updateValue = ContentValues()
//        updateValue.put(DramaDBHelper.COL_ACOTR, dto.actor)
//        updateValue.put(DramaDBHelper.COL_ACTRESS, dto.actress)
//        val whereClause = "${BaseColumns._ID}=?"
////        val whereClause = "${DramaDBHelper.COL_NAME}=?"
//        val whereArgs = arrayOf(dto.id.toString())
//
//        // 함수는 결과로 수정된 개수를 반환한다 // SQL문은 반환하는 것이 없다
//        val resultCount =  db.update(DramaDBHelper.TABLE_NAME,
//            updateValue, whereClause, whereArgs)
//
//        helper.close()
//        return resultCount
//    }
}