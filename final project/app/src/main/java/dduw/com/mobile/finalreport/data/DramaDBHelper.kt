package dduw.com.mobile.finalreport.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import dduw.com.mobile.finalreport.R

class DramaDBHelper (context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    val TAG = "DramaDBHelper"

    companion object {
        const val DB_NAME = "drama_db"
        const val TABLE_NAME = "drama_table"
        const val COL_PHOTO = "photo"
        const val COL_NAME = "name"
        const val COL_MADE = "made"
        const val COL_ACOTR = "actor"
        const val COL_ACTRESS = "actress"
        const val COL_DIRECTOR = "director"
        const val COL_AUTHOR = "author"
        const val COL_REVIEW = "review"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ( ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COL_PHOTO TEXT, $COL_NAME TEXT, $COL_MADE TEXT, $COL_ACOTR TEXT, $COL_ACTRESS TEXT, $COL_DIRECTOR TEXT, $COL_AUTHOR TEXT, $COL_REVIEW TEXT)"
        Log.d(TAG, CREATE_TABLE)    // SQL 문장이 이상 없는지 Log에서 확인 필요
        db?.execSQL(CREATE_TABLE)

        /*샘플 데이터 - 필요할 경우 실행*/
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.mipmap.mydearest}, '연인', 'MBC', '남궁민', '안은진', '김성용', '황진영', '병자호란과 함께 역사 속에서 성숙해지는 두 주인공 장현길채는 완전 짱이었다, 사랑에 압도된 이가 어디까지 할 수 있는지 보여줌, 본방으로 봤다... 매주 금토마다 활력이 생기게 했던 드라마, 난 14화가 젤루 좋아 ㅋ')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.mipmap.secretgarden}, '시크릿 가든', 'SBS', '현빈', '하지원', '신우철', '김은숙', '김은숙 드라마 중에 젤 재밌다ㅎㅎ 판타지 최고! 이태리 장인이 한땀한땀~~~')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.mipmap.theworldthattheylivein}, '그들이 사는 세상', 'KBS', '현빈', '송혜교', '표민수', '노희경', '노동자들의 다양한 인간관계 이야기.. 나레이션이 참 좋다, 완전한 선/악 구별이 없어서 현실적이다')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.mipmap.discoveryoflove}, '연애의 발견', 'KBS', '문정혁', '정유미', '김성윤', '정현정', '어떻게 보면 비현실적인데 동시에 엄청 현실적.. 그리고 너무 공감되는 장면이 많다')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.mipmap.queserasera}, '케세라세라', 'MBC', '문정혁', '정유미', '김윤철', '도현정', '에릭-정유미 첫 드라마! ost랑 분위기가 좋고 정통 멜로 오랜만에 봐서 좋았다! 이거 보고 에릭 정유미 조합에 빠져서 연애의 발견도 봄')")
}

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        val DROP_TABLE ="DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }
}