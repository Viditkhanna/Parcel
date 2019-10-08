package h.user.tired

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_friends.*
lateinit var userid:String
lateinit var idandname:ArrayList<String>
class friendsactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

idandname=ArrayList<String>()
        var mRef= FirebaseDatabase.getInstance().getReference()
        mRef.child("Names").addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            var i=200f
            override fun onDataChange(p0: DataSnapshot) {

                for(p1 in p0.children){

                    for(p2 in p1.children){
                        var mAuth=FirebaseAuth.getInstance().uid
                        if(!mAuth.equals(p1.key.toString()))
                        if(p2.key.toString().equals("Name")){
                            idandname.add(p1.key.toString())
                            var usernames=TextView(this@friendsactivity)

                            rel.addView(usernames)
                            rel.layoutParams.height+=200
//                        usernames.layoutParams=layouttext
                            idandname.add(p2.value.toString())
                        usernames.setClickable(true)
                        usernames.setText(p2.value.toString())
                        usernames.layoutParams.width=1000
                        usernames.layoutParams.height=1000
                        usernames.visibility= View.VISIBLE
                            usernames.x=200f
                        usernames.y=i
                        usernames.textSize=16f
                        usernames.setTextColor(Color.parseColor("#000000"))
                        usernames.setTypeface(null, Typeface.BOLD)

                            var mRef2= FirebaseDatabase.getInstance().getReference()
                                mRef2.child("Names").child(FirebaseAuth.getInstance().uid.toString()).child("Friends").addValueEventListener(object:ValueEventListener{
                                    override fun onCancelled(p0: DatabaseError) {
                                        }

                                    override fun onDataChange(p0: DataSnapshot) {
                                  for(p in p0.children){
                                    if(usernames.text.toString().equals(p.key.toString())){
                                       // Toast.makeText(this@friendsactivity,usernames.text.toString(),Toast.LENGTH_SHORT).show()
                                        var iv=ImageView(this@friendsactivity)
                                        rel.addView(iv)
                                        iv.setImageResource(R.drawable.alreadyfriend2)
                                        iv.y=usernames.y
                                        iv.x=usernames.x+350f
                                        iv.layoutParams.height=50
                                        iv.layoutParams.width=50
                                    }
                                    }
                                    }



                                })





                            usernames.setOnClickListener {

                                Toast.makeText(this@friendsactivity,"You made a new friend",Toast.LENGTH_SHORT).show()

                           var mRef=FirebaseDatabase.getInstance().getReference()
                             var mAuth=FirebaseAuth.getInstance().uid
                                val index= idandname.indexOf(usernames.text.toString())
                                val uid:String=idandname[index-1]
                                mRef.child("Names").child(mAuth.toString()).child("Friends").child(usernames.text.toString()).setValue(uid)


                            }
                    i=i+200}


                    }

                }


            }

        })

    }
}
