package dduw.com.mobile.finalreport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.finalreport.data.DramaDto
import dduw.com.mobile.finalreport.databinding.ItemListBinding

class DramaAdpater (val dramas : ArrayList<DramaDto>) : RecyclerView.Adapter<DramaAdpater.DramaViewHolder>() {
    val TAG = "DramaAdapter"

    override fun getItemCount(): Int = dramas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaViewHolder {
        val itemBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DramaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DramaViewHolder, position: Int) {
        holder.itemBinding.imageView.setImageResource(dramas[position].photo)
        holder.itemBinding.showName.text = dramas[position].toString()
        holder.itemBinding.showActor.text = dramas[position].actor
        holder.itemBinding.showActress.text = dramas[position].actress
    }

    inner class DramaViewHolder(val itemBinding: ItemListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        init {
//            itemBinding.showName.setOnClickListener{
//                listener.onItemClick(it, adapterPosition)
//                //true
//            }
            itemBinding.root.setOnClickListener{
                listener.onItemClick(it, adapterPosition)
                //true
            }
            itemBinding.root.setOnLongClickListener {
                long_listener?.onItemLongClick(it, adapterPosition)

                true
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position:Int)
    }
    lateinit var listener: OnItemClickListener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view : View, position : Int)
    }
    var long_listener : OnItemLongClickListener? = null
    fun setOnItemLongClickListener(long_listener: OnItemLongClickListener?) {
        this.long_listener = long_listener
    }
}