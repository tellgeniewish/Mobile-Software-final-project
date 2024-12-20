package dduw.com.mobile.finalreport.data

import java.io.Serializable

data class DramaDto (val id:Long, val photo:Int, val name:String, val made:String, var actor:String, var actress:String, val director:String, val author:String, val review: String)
    : Serializable {
    //    override fun toString() = "$name / $where / ($actor , $actress) / $director / $author"
    override fun toString() = "$name"
}