package com.albertjsoft.githubkanban.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.albertjsoft.githubkanban.R

/**
 * Created by albertj on 13/10/2018.
 */

class FakeKanbanFragment : BaseFragment(){

    private var mRootView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_page, container, false) as RecyclerView
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRootView!!.adapter = FakeKanbanAdapter(1)
    }


    private class FakeKanbanAdapter internal constructor(private val numItems: Int) : RecyclerView.Adapter<FakeKanbanVH>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FakeKanbanVH {
            val itemView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.list_item, viewGroup, false)
            itemView.findViewById<Button>(R.id.action_button).setText(R.string.kanban)
            itemView.findViewById<TextView>(R.id.item_title).setText(R.string.board)
            return FakeKanbanVH(itemView)
        }

        override fun onBindViewHolder(FakeKanbanVH: FakeKanbanVH, i: Int) {
            // do nothing
        }

        override fun getItemCount(): Int {
            return numItems
        }
    }

    private class FakeKanbanVH internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        fun newInstance(): Fragment {
            return FakeKanbanFragment()
        }
    }
}
