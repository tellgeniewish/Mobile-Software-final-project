package dduw.com.mobile.finalreport

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.finalreport.data.DramaDBHelper
import dduw.com.mobile.finalreport.databinding.ActivitySearchBinding
import dduw.com.mobile.finalreport.databinding.ActivityUpdateBinding

class SearchActivity : AppCompatActivity() {
    lateinit var searchText : String

    val searchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(searchBinding.root)

        searchBinding.btnSearch.setOnClickListener{
            searchText = searchBinding.findDrama.text.toString()
            Toast.makeText(this@SearchActivity, "찾는 드라마는 ${searchText}", Toast.LENGTH_SHORT).show()

            val helper = DramaDBHelper(this)
            val db = helper.readableDatabase
            val cursor = db.query(DramaDBHelper.TABLE_NAME, null, null, null, null, null, null)

            var found = 0

            with (cursor) {
                while (moveToNext()) {

                    val drama_name = getString ( getColumnIndex(DramaDBHelper.COL_NAME) )
                    //Toast.makeText(this@SearchActivity, "${drama_name}", Toast.LENGTH_SHORT).show()
//
                    if (searchText.equals(drama_name)) {
                        searchBinding.dramaPhoto.setImageResource(getInt(getColumnIndex(DramaDBHelper.COL_PHOTO)))
                        searchBinding.dramaName.setText(searchText)
                        searchBinding.dramaMade.setText(getString(getColumnIndex(DramaDBHelper.COL_MADE)))
                        searchBinding.dramaActor.setText(getString(getColumnIndex(DramaDBHelper.COL_ACOTR)))
                        searchBinding.dramaActress.setText(getString(getColumnIndex(DramaDBHelper.COL_ACTRESS)))
                        searchBinding.dramaDirector.setText(getString(getColumnIndex(DramaDBHelper.COL_DIRECTOR)))
                        searchBinding.dramaAuthor.setText(getString(getColumnIndex(DramaDBHelper.COL_AUTHOR)))
                        searchBinding.dramaReview.setText(getString(getColumnIndex(DramaDBHelper.COL_REVIEW)))

                        found = 1
                        break
                    }
                    //binding.recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            if (found != 1) {
                searchBinding.dramaPhoto.setImageResource(R.mipmap.plusplz)
                searchBinding.dramaName.setText("")
                searchBinding.dramaMade.setText("")
                searchBinding.dramaActor.setText("")
                searchBinding.dramaActress.setText("")
                searchBinding.dramaDirector.setText("")
                searchBinding.dramaAuthor.setText("")
                searchBinding.dramaReview.setText("")
                Toast.makeText(this@SearchActivity, "없는 드라마입니다.", Toast.LENGTH_SHORT).show()
            }
            cursor.close()
            helper.close()

        }

        searchBinding.showEnd.setOnClickListener{
            finish()
        }
    }
}