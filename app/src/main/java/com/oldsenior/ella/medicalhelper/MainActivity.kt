package com.oldsenior.ella.medicalhelper

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oldsenior.ella.core_ui.view.recycler.BaseListAdapter
import com.oldsenior.ella.medicalhelper.listsample.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  MedicalListMenuRouterImpl.startMedicalListMenuActivity(this, Intent())

        val section = SampleSection()
        val section2 = SampleSection()
        val section3 = SampleSection()

        val sectionAdapter = BaseListAdapter()

        section.addHeaderItem(EventHeader("today"))
        section.addContentItems(EventsFactory.getEvents())
        section.addFooterItem(EventFooter("footer"))

        section2.addHeaderItem(EventHeader("yesterday"))
        section2.addContentItems(EventsFactory.getSecondEvents())
        section2.addFooterItem(EventFooter("footer"))

//        section3.addHeaderItem(EventHeader("newlist"))
//        section3.addContentItems(EventsFactory.getSecondList())
//        section3.addFooterItem(EventFooter("footer3"))
        sectionAdapter.addSection(section2)
        sectionAdapter.addSection(section)

       // sectionAdapter.addSection(section3)

        val recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        recyclerView.adapter = sectionAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<EditText>(R.id.bankListSearchViewEditText).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                sectionAdapter.filter.filter(editable.toString())
               // sectionAdapter.filter(editable.toString())
            }
        })
    }
}
