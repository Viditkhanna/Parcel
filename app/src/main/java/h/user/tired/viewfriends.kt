package h.user.tired

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_friends.*
import kotlinx.android.synthetic.main.activity_viewfriends.*

class viewfriends : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewfriends)
        var mRef=FirebaseDatabase.getInstance().getReference()
        var mAuth=FirebaseAuth.getInstance().uid
        mRef.child("Names").child(mAuth.toString()).child("Friends").addValueEventListener(object :ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            var i=200f

            override fun onDataChange(p0: DataSnapshot) {
                var layouttext = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
                layouttext.setMargins(275, 100, 0, 200)
                for(p1 in p0.children) {
                    //Toast.makeText(this@viewfriends, p1.key.toString(), Toast.LENGTH_SHORT).show()
Log.d("dd",p1.key.toString())
                    val usernames = TextView(this@viewfriends)
                   rel2.addView(usernames);rel2.layoutParams.height+=400
//                    usernames.layoutParams = layouttext
                    usernames.setClickable(true)
                usernames.setText(p1.key.toString())
                    usernames.layoutParams.width = 1000
                    usernames.layoutParams.height = 1000
                 usernames.visibility = View.VISIBLE
                    usernames.y = i
                    usernames.textSize = 16f
                    usernames.setTextColor(Color.parseColor("#000000"))
                    usernames.setTypeface(null, Typeface.BOLD)
                    i = i +200
                }

            }




        })


    }
}
