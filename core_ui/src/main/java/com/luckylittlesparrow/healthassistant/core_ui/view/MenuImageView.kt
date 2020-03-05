//package com.luckylittlesparrow.healthassistant.core_ui.view
//
//import android.content.Context
//import android.util.AttributeSet
//import androidx.appcompat.widget.AppCompatImageView
//import androidx.appcompat.widget.PopupMenu
//
//class MenuImageView constructor(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {
//
//    private var sortMenuListener: SortMenuListener? = null
//
//    init {
//        setImageResource(R.drawable.ic_sort_black)
//        setOnClickListener {
//            val popupMenu = PopupMenu(context, this)
//            popupMenu.setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.menu_sort_by_az -> sortMenuListener?.sortAZ()
//                    R.id.menu_sort_by_za -> sortMenuListener?.sortZA()
//                    R.id.menu_sort_by_year -> sortMenuListener?.sortYear()
//                    R.id.menu_sort_by_duration -> sortMenuListener?.sortDuration()
//                }
//                true
//            }
//            popupMenu.inflate(R.menu.song_sort_by)
//            popupMenu.show()
//        }
//    }
//
//    fun setupMenu(listener: SortMenuListener?) {
//        this.sortMenuListener = listener
//    }
//}
