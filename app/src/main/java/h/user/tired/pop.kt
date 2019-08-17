package h.user.tired

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.popwindow.*


class pop :Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContentView(R.layout.popwindow)
var para:WindowManager.LayoutParams=window.attributes
        para.x=0
        para.height=600
        para.width=700
        para.y=-20//less ->y below layout
        this.window.attributes=para
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popwindow, null)

        // create the popup window
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true // lets taps outside the popup also dismiss it
        val popupWindow = PopupWindow(cl, 0, 0, true)
        popupView.measure(10,10)
popupWindow.width=popupView.measuredWidth
        popupWindow.height=popupView.measuredHeight
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(popup, Gravity.CENTER, 0, 0)

        // dismiss the popup window when touched
        popupView.setOnTouchListener(object : View.OnTouchListener{

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                popupWindow.dismiss()
                return true
            }
        })








    }


}