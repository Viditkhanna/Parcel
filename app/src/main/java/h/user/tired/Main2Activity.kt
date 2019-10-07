package h.user.tired

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*
import android.view.LayoutInflater

import android.os.Build;
import android.support.constraint.ConstraintLayout

import android.view.Gravity;
import android.view.ViewGroup

import android.view.ViewGroup.LayoutParams;
import android.widget.*
import com.google.firebase.auth.FirebaseAuthException


class Main2Activity : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    var mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
            progress.visibility=View.INVISIBLE




ca.setOnClickListener {
    if(name.text.isNotEmpty()&&email.text.isNotEmpty()&&password.text.isNotEmpty()&&repassword.text.isNotEmpty()){
    progress.visibility=View.VISIBLE
    register()
    }else{
        Toast.makeText(applicationContext,"Please fill all the fields",Toast.LENGTH_SHORT).show();
    }
}

var mAuth=FirebaseAuth.getInstance()
        mAuth.currentUser
    }





fun popup(){
    startActivity(Intent(this,pop::class.java))
    }





fun register() {
    if (repassword.text.toString()== password.text.toString()) {
        try {
            val mAuth = FirebaseAuth.getInstance()
            val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Names")

            mAuth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val  uid = user!!.uid
                    mDatabase.child(uid).child("Name").setValue(name.text.toString().trim())
                    Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_SHORT).show()
                    progress.visibility=View.INVISIBLE

                    startActivity(Intent(this, tiredy::class.java))
                    finish()

                } else {
                    try{
                    val usercheck = mAuth.currentUser
                        if(usercheck!=null&&usercheck.isEmailVerified){
                            Toast.makeText(this, "A user already exist with that e-mail", Toast.LENGTH_SHORT).show()
                        }else if(!task.isSuccessful) {
                        var ex=task.getException()
                        Toast.makeText(this, ex!!.message.toString(), Toast.LENGTH_SHORT).show()
                            progress.visibility=View.INVISIBLE
                        }}catch (ex:Exception){Toast.makeText(this,ex.message.toString()+"dsdsd", Toast.LENGTH_SHORT).show()
                        progress.visibility=View.INVISIBLE
                    }


                }

//            var database = FirebaseDatabase.getInstance().getReference("User")
//            var id: String = database.push().key
//            var name=text.text.toString().trim()
//            var usrnm = Username(name)
//            database.child(id).setValue(usrnm).addOnCompleteListener {
//                Toast.makeText(this, "Database Updated", Toast.LENGTH_SHORT).show()
//            }

            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()


        }


    }else{
        progress.visibility=View.INVISIBLE
        Toast.makeText(this,"Password mismatch!",Toast.LENGTH_SHORT).show()

    }
}


}
