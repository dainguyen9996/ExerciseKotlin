package com.example.exercise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.R
import com.example.exercise.databinding.ItemRoleBinding
import com.example.exercise.model.Role

class RoleAdapter(
    private val onCLickEditListener: OnClickEditListener,
    private val onCLickListener: OnClickListener,
    private val onSwipe: OnSwiper
) :
    ListAdapter<Role, RoleAdapter.MyViewHolder>(MyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRoleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val staff = getItem(position)
        holder.bind(staff)

        holder.itemView.setOnClickListener {
            onCLickListener.onClick(staff)
        }
    }

    object MyDiffUtil : DiffUtil.ItemCallback<Role>() {
        override fun areItemsTheSame(oldItem: Role, newItem: Role): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Role, newItem: Role): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }

    }

    class OnClickListener(val clickListener: (staff: Role) -> Unit) {
        fun onClick(staff: Role) = clickListener(staff)
    }

    class OnClickEditListener(val clickListener: (staff: Role) -> Unit) {
        fun onClick(staff: Role) = clickListener(staff)
    }

    class OnSwiper(val clickListener: (staff: Role) -> Unit) {
        fun onCLick(staff: Role) = clickListener(staff)
    }

    inner class MyViewHolder(private val binding: ItemRoleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(role: Role?) {
            binding.apply {
                tvRoleName.text = role?.name
                tvDes.text = role?.description
                imgEdit.setOnClickListener {
                    role?.let { it1 -> onCLickEditListener.onClick(it1) }
                }
                imgDel.setOnClickListener {
                    role?.let { it1 -> onSwipe.onCLick(it1) }
                }

                setImageBackground(role?.roleAction?.creatStaff, imgCrStaff)
                setImageBackground(role?.roleAction?.edittStaff, imgEditStaff)
                setImageBackground(role?.roleAction?.delStaff, imgDelStaff)
                setImageBackground(role?.roleAction?.creatRole, imgCrRole)
                setImageBackground(role?.roleAction?.editRole, imgEditRole)
                setImageBackground(role?.roleAction?.delRole, imgDelRole)
            }
        }
    }

    fun setImageBackground(isAccept: Boolean?, img: ImageView) {
        if (isAccept == true) {
            img.setImageResource(R.drawable.ic_baseline_check_24)
        } else {
            img.setImageResource(R.drawable.ic_baseline_clear_24)
        }
    }
}
