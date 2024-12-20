package dduw.com.mobile.finalreport.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns

class DramaDao (val context: Context) {
    @SuppressLint("Range")
    fun getAllDramas() : ArrayList<DramaDto> {
        val helper = DramaDBHelper(context)//this)
        val db = helper.readableDatabase
        val cursor = db.query(DramaDBHelper.TABLE_NAME, null, null, null, null, null, null)

        val drama = arrayListOf<DramaDto>()
        with (cursor) {
            while (moveToNext()) {
                val id = getLong( getColumnIndex(BaseColumns._ID) )
                val photo = getInt ( getColumnIndex(DramaDBHelper.COL_PHOTO) )
                val name = getString ( getColumnIndex(DramaDBHelper.COL_NAME) )
                val made = getString ( getColumnIndex(DramaDBHelper.COL_MADE) )
                val actor = getString ( getColumnIndex(DramaDBHelper.COL_ACOTR) )
                val actress = getString ( getColumnIndex(DramaDBHelper.COL_ACTRESS) )
                val director = getString ( getColumnIndex(DramaDBHelper.COL_DIRECTOR) )
                val author = getString ( getColumnIndex(DramaDBHelper.COL_AUTHOR) )
                val review = getString ( getColumnIndex(DramaDBHelper.COL_REVIEW) )
                val dto = DramaDto(id, photo, name, made, actor, actress, director, author, review)
                drama.add(dto)
            }
        }
        return drama
    }

    fun addDrama(newDto : DramaDto) : Long {// Int {
        val helper = DramaDBHelper(context)//this)
        val db = helper.writableDatabase

        val newRow = ContentValues()
        newRow.put(DramaDBHelper.COL_PHOTO, newDto.photo)
        newRow.put(DramaDBHelper.COL_NAME, newDto.name)
        newRow.put(DramaDBHelper.COL_MADE, newDto.made)
        newRow.put(DramaDBHelper.COL_ACOTR, newDto.actor)
        newRow.put(DramaDBHelper.COL_ACTRESS, newDto.actress)
        newRow.put(DramaDBHelper.COL_DIRECTOR, newDto.director)
        newRow.put(DramaDBHelper.COL_AUTHOR, newDto.author)
        newRow.put(DramaDBHelper.COL_REVIEW, newDto.review)

        val result = db.insert(DramaDBHelper.TABLE_NAME, null, newRow)

        helper.close()

//        return 0
        return result
    }
    fun updateDrama(dto: DramaDto): Int {
        val helper = DramaDBHelper(context)//this)
        val db = helper.writableDatabase

        val updateValue = ContentValues()
        updateValue.put(DramaDBHelper.COL_ACOTR, dto.actor)
        updateValue.put(DramaDBHelper.COL_ACTRESS, dto.actress)
        val whereClause = "${BaseColumns._ID}=?"
//        val whereClause = "${DramaDBHelper.COL_NAME}=?"
        val whereArgs = arrayOf(dto.id.toString())

        // 함수는 결과로 수정된 개수를 반환한다 // SQL문은 반환하는 것이 없다
        val resultCount =  db.update(DramaDBHelper.TABLE_NAME,
            updateValue, whereClause, whereArgs)

        helper.close()
        return resultCount
    }
}