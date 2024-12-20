package dduw.com.mobile.finalreport

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dduw.com.mobile.finalreport.data.DramaDBHelper
import dduw.com.mobile.finalreport.data.DramaDao
import dduw.com.mobile.finalreport.data.DramaDto
import dduw.com.mobile.finalreport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 과제명: OTT 드라마 후기 관리 앱
    // 분반: 01 분반
    // 학번: 20211689 성명: 김현진
    // 제출일: 2024년 6월 23일

    val REQ_ADD = 100
    val REQ_UPDATE = 200

    val dao: DramaDao by lazy {
        DramaDao(this)
    }

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var drama : ArrayList<DramaDto>
    //lateinit var adapter : DramaAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //val dramas = DramaDao().dramas
        drama = dao.getAllDramas()

        val adapter = DramaAdpater(drama)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

//        val listener = object: DramaAdpater.OnItemClickListener{
//            override fun onItemClick(view: View, position: Int) {
//                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity).apply {
//                    setTitle("${drama[position]}의 상세 정보를 보시겠습니까?")
//
//                    setPositiveButton("확인") { p0: DialogInterface?, whichButton: Int ->
//                        //Toast.makeText(this@EveryActivity, "보이나??", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this@MainActivity, ShowActivity::class.java)
//                        intent.putExtra("selectedDrama", drama[position])
//                        startActivity(intent)
//                    }
//                    setNegativeButton("취소", null)
//                    setCancelable(false)
//                }
//                builder.show()
//            }
//        }
//        adapter.setOnItemClickListener(listener)

//        binding.btnChange.setOnClickListener {
//            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
//            intent.putExtra("dto", dramas)   // 클릭한 RecyclerView 항목 위치의 dto 전달
//            startActivityForResult(intent, REQ_UPDATE)      // 수정결과를 받아오도록 Activity 호출
//
//            binding.recyclerView.adapter?.notifyDataSetChanged()
//        }

        adapter.setOnItemClickListener(object : DramaAdpater.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("dto", drama.get(position) )   // 클릭한 RecyclerView 항목 위치의 dto 전달
                startActivityForResult(intent, REQ_UPDATE)      // 수정결과를 받아오도록 Activity 호출
            }
        })

        adapter.setOnItemLongClickListener(object : DramaAdpater.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("${drama[position]}를 삭제하시겠습니까?")

                    setPositiveButton("확인") { p0: DialogInterface?, whichButton: Int ->
                         if (deleteDrama(drama.get(position)) > 0) {
//                             drama.clear()
//                             drama.addAll(dao.getAllDramas())
                             onResume()
                             //binding.recyclerView.adapter?.notifyDataSetChanged()
                         }
                    }
                    setNegativeButton("취소", null)
                    setCancelable(false)
                }
                builder.show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //adapter.notifyDataSetChanged()
        drama.clear()
        drama.addAll(dao.getAllDramas())
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_UPDATE -> {
                if (resultCode == RESULT_OK) {
//                    drama.clear()                       // 기존 항목 제거 // List 내용을 없앰 // 얘를 주석처리 해보고 실행하면 기존 List에 추가됨
//                    drama.addAll(dao.getAllDramas())         // 항목 추가 // DB를 다시 읽어서 추가
                    //adapter.notifyDataSetChanged()      // RecyclerView 갱신
                    //binding.recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "change 취소됨", Toast.LENGTH_SHORT).show()
                }
            }
            REQ_ADD -> {
                if (resultCode == RESULT_OK) {
//                    drama.clear()
//                    drama.addAll(dao.getAllDramas())
                    //adapter.notifyDataSetChanged()
                    Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show()
                    //binding.recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "add 취소됨", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deleteDrama(dto: DramaDto): Int {
        val helper = DramaDBHelper(this)
        val db = helper.writableDatabase

        val whereClause = "${BaseColumns._ID}=?"
        val whereArgs = arrayOf(dto.id.toString()) // 문자열이어야 함
        val result = db.delete(DramaDBHelper.TABLE_NAME, whereClause, whereArgs)

        helper.close()

        //return 0
        return result
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.where_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {  // switch문
            R.id.searchMenu -> {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.addDramaMenu -> {
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivityForResult(intent, REQ_ADD)

                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
            R.id.introduceDeveloperMenu -> {
                val intent = Intent(this@MainActivity, IntroduceActivity::class.java)
                startActivity(intent)
//                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity).apply {
//                    setTitle("자기 소개")
//                    setIcon(R.mipmap.mysmile)
//                    setMessage("모소01분반, 20211689 김현진")
//                    setPositiveButton("확인", null)
//                    setNegativeButton("취소", null)
//                    setCancelable(false)
//                }
//                builder.show()
            }
            R.id.appFinishMenu -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("앱 종료")
                    setMessage("앱을 종료하시겠습니까?")
                    setPositiveButton("종료") { p0: DialogInterface?, whichButton: Int ->
                        finish()
                    }
                    setNegativeButton("취소", null)
                    setCancelable(false)
                }
                builder.show()
            }
        }
        return true
    }
}