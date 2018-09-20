package com.lxb.testapp.adapter

import android.text.format.DateFormat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.lxb.testapp.FindFragment.OnListFragmentInteractionListener
import com.lxb.testapp.R
import com.lxb.testapp.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.recycler_view_item.view.*


class MyItemRecyclerViewAdapter(
        private val mValues: List<DummyItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            val position = v.getTag(R.id.recycler_item_position) as Int
            mListener?.onListFragmentInteraction(item, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.setImageResource(R.drawable.ic_me)
        holder.mContentView.text = item.id
        holder.descTv.text = item.content

        holder.timeTv.text = DateFormat.format("HH:mm", item.time)

        with(holder.mView) {
            tag = item
            setTag(R.id.recycler_item_position, position)

            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: ImageView = mView.avatar_iv
        val mContentView: TextView = mView.name_tv
        val descTv: TextView = mView.desc_tv
        val timeTv: TextView = mView.time_tv

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
