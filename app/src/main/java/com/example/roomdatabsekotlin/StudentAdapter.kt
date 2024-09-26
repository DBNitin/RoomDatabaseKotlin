package com.example.roomdatabsekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val datalist: ArrayList<studentVM>, private val mContext: Context) : RecyclerView.Adapter<StudentAdapter.studentViewHolder>() {

    class studentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentidView: TextView = itemView.findViewById(R.id.studentid)
        val nameview: TextView = itemView.findViewById(R.id.studentname)
        val emailView: TextView = itemView.findViewById(R.id.studentemail)
        val deleteButton: Button = itemView.findViewById(R.id.delete)
        val editButton: Button = itemView.findViewById(R.id.edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): studentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return studentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: studentViewHolder, position: Int) {
        // Use mContext instead of coroutineContext
        val db = DBHandler.getDB(mContext)
        val list = db.studentDao.getAllStudent()

        holder.studentidView.text = list[position].id.toString()
        holder.nameview.text = list[position].name
        holder.emailView.text = list[position].email
        holder.deleteButton.setOnClickListener {
        db.studentDao.deleteStudent(list[position])
        }
    }
}
