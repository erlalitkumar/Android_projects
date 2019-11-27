package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ExpandableListView
import android.widget.ExpandableListAdapter


class MainActivity : AppCompatActivity() {
    var expandableListView: ExpandableListView? = null
    var expandableListAdapter: ExpandableListAdapter? = null
    var expandableListTitle: List<String>? = null
    var expandableListDetail: HashMap<String, List<String>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expandableListView = findViewById(R.id.expandableListView)
        expandableListDetail = ExpandableListDataPump.getData()
        expandableListTitle =
            ArrayList<String>((expandableListDetail as java.util.HashMap<String, MutableList<String>>?)?.keys)
        expandableListAdapter =
            CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail)
        expandableListView?.setAdapter(expandableListAdapter)
        expandableListView?.setOnGroupExpandListener(ExpandableListView.OnGroupExpandListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                expandableListTitle?.get(groupPosition) + " List Expanded.",
                Toast.LENGTH_SHORT
            ).show()
        })

        expandableListView?.setOnGroupCollapseListener(ExpandableListView.OnGroupCollapseListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                expandableListTitle?.get(groupPosition) + " List Collapsed.",
                Toast.LENGTH_SHORT
            ).show()
        })

        expandableListView?.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(
                applicationContext,
                expandableListTitle?.get(groupPosition)
                        + " -> "
                        + expandableListDetail?.get(
                    expandableListTitle?.get(groupPosition)
                )?.get(
                    childPosition
                ), Toast.LENGTH_SHORT
            ).show()
            false
        }
    }
}
