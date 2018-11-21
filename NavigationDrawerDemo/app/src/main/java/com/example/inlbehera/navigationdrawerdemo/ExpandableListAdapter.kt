package com.example.inlbehera.navigationdrawerdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandableListAdapter(
    private val context: Context,
    private val header: List<String>,
    private val childList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): Any {
        return header[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view:View? = convertView
        var headerTitle = getGroup(groupPosition)
        if(view ==null){
            var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.list_item,null)
            var header = view.findViewById<TextView>(R.id.header)
            header.text = headerTitle.toString()
        }
        return view!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childList[header[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childList[header[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view:View? = convertView
        var headerTitle = getChild(groupPosition,childPosition) as String
        if(view ==null){
           var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.list_group,null)
            var listText = view.findViewById<TextView>(R.id.list_child)
            listText.text = headerTitle
        }
return view!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }

}