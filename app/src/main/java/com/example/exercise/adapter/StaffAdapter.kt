package com.example.exercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.databinding.ItemStaffBinding
import com.example.exercise.model.Staff

class StaffAdapter(
    private val dept : String,
    private val onCLickEditListener: OnClickEditListener,
    private val onCLickListener: OnClickListener,
    private val onSwipe: OnSwiper
) :
    ListAdapter<Staff, StaffAdapter.MyViewHolder>(MyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemStaffBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val staff = getItem(position)
        holder.bind(staff)

        //onSwipe.onCLick(staff)

        holder.itemView.setOnClickListener {
            onCLickListener.onClick(staff)
        }
//
//        holder.itemView.setOnClickListener {
//            onCLickListener.onClick(staff)
//        }

    }

    object MyDiffUtil : DiffUtil.ItemCallback<Staff>() {
        override fun areItemsTheSame(oldItem: Staff, newItem: Staff): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Staff, newItem: Staff): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (staff: Staff) -> Unit) {
        fun onClick(staff: Staff) = clickListener(staff)
    }

    class OnClickEditListener(val clickListener: (staff: Staff) -> Unit) {
        fun onClick(staff: Staff) = clickListener(staff)
    }

    class OnSwiper(val clickListener: (staff: Staff) -> Unit) {
        fun onCLick(staff: Staff) = clickListener(staff)
    }

    inner class MyViewHolder(private val binding: ItemStaffBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(staff: Staff?) {
            binding.apply {
                tvName.text = staff?.name
                tvRole.text = "staff"
                imgEdit.setOnClickListener {
                    staff?.let { it1 -> onCLickEditListener.onClick(it1) }
                }
                imgDel.setOnClickListener {
                    staff?.let { it1 -> onSwipe.onCLick(it1) }
                }
            }
        }
    }
}
