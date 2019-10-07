package h.user.tired

//import android.support.v7.app.AppCompatActivity
//import android.os.Bu
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.CalendarContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import com.google.firebase.database.*

import com.google.firebase.messaging.FirebaseMessaging
//import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.storage.FirebaseStorage
import h.user.tired.R.id.button2
import kotlinx.android.synthetic.main.activity_tiredy.*
import kotlinx.android.synthetic.main.tab3.*
import kotlin.system.exitProcess

/*
* sara register mai(Abb check krna hai password ki length ,email mai @ and . hona chahiye,name kai liye kuch ni)
*
*
*
*
* */
class tiredy : AppCompatActivity() {
//    lateinit var text:EditText

    private var mAuth=FirebaseAuth.getInstance()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d("ssss", "sss")
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiredy)
        if (Build.VERSION.SDK_INT >= 23) {
            val writePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            val internetstate = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
            if (internetstate == PackageManager.PERMISSION_DENIED)
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE), 999)

            if (writePermission == PackageManager.PERMISSION_DENIED)
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
        }
       // val connectivityManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED ||
             //   connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED
       // ){
            try {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true)
//            val fragment=fragmentManager!!.findFragmentById(R.id.tab1)

            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()

            }



//        Tabbed().finish()
//        Tab1Fragment().fragmentManager!!.popBackStack()
        pb2.visibility = View.INVISIBLE
//            fragmentManager.popBackStack()
//            fragmentManager.popBackStack()

//

        try {
            if (FirebaseAuth.getInstance().currentUser != null) {

                try {

                    startActivity(Intent(this, Tabbed::class.java))
                    finish()

                } catch (e: Exception) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()

                }
            } else {
//                Toast.makeText(this,"HERE",Toast.LENGTH_SHORT).show()
//                Tabbed().finish()
//                Tabbed().finishAffinity()
//                Tabbed().finishAndRemoveTask()


            }
        } catch (ex: Exception) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()

        }
//try{
//var int=getIntent()
//        if(int.getStringExtra("EXIT").equals(true)){
//            Toast.makeText(this,"chal",Toast.LENGTH_SHORT).show()
//            exitProcess(1)
//            System.exit(1)
//finish()
//        }}catch (ex:Exception){            Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show()
//}


//            text=findViewById(R.id.editText)

//        layout.background.alpha=120var ed:EditText=findViewById(R.id.editText5)
//        ed.visibility=View.INVISIBLE
//        editText5.visibility=View.INVISIBLE
//        button2.visibility=View.INVISIBLE
//
        clickhere.setOnClickListener {
            register()
        }
        button2.setOnClickListener{

             login()

        }

        //}
        //else{
          //  Toast.makeText(this,"Please connect to the internet",Toast.LENGTH_LONG).show()

        //}
    }





fun register(){
    startActivity(Intent(this,Main2Activity::class.java))
/*
    try {
        var mAuth= FirebaseAuth.getInstance()
        var mDatabase:DatabaseReference=FirebaseDatabase.getInstance().getReference("Names")

        mAuth.createUserWithEmailAndPassword(editText.text.toString(),editText2.text.toString()).addOnCompleteListener(this, OnCompleteListener { task ->
            if (task.isSuccessful) {
                var user=mAuth.currentUser
                var uid=user!!.uid
                mDatabase.child(uid).child("Name").setValue(editText3.text.toString())
                Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_SHORT).show()
               startActivity(Intent(this,Main2Activity::class.java))


            }
            else {

               var usercheck=mAuth.currentUser

                if(usercheck==null){

                    Toast.makeText(this, "A user already exist with that e-mail", Toast.LENGTH_SHORT).show()

                }
                else
                Toast.makeText(this, "Something went wrong! :)", Toast.LENGTH_SHORT).show()


            }

//            var database = FirebaseDatabase.getInstance().getReference("User")
//            var id: String = database.push().key
//            var name=text.text.toString().trim()
//            var usrnm = Username(name)
//            database.child(id).setValue(usrnm).addOnCompleteListener {
//                Toast.makeText(this, "Database Updated", Toast.LENGTH_SHORT).show()
//            }

        }) }catch (e: Exception) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()

    }*/


}


     override fun onBackPressed() {
//         try {
//             FirebaseAuth.getInstance().signOut()
//         } catch (e: Exception) {Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
//         }
//         var int=Intent(Intent.ACTION_MAIN)
//         int.addCategory(Intent.CATEGORY_HOME)
//         int.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//         startActivity(int)

         super.onBackPressed()
    }

    fun login(){
//        var mAuth= FirebaseAuth.getInstance()
//        var user=mAuth.currentUser
        //Toast.makeText(this,user.toString(),Toast.LENGTH_SHORT).show()
        var et=editText.text.trim()
        if(et.toString().equals("")||editText2.text.toString().trim().equals(""))
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        else {

            pb2.visibility=View.VISIBLE
            this.mAuth.signInWithEmailAndPassword(editText.text.toString(), editText2.text.toString()).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                    button2.isClickable=false

                   startActivity(Intent(this,Tabbed::class.java))
                    finish()
                } else{
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    pb2.isClickable=true
                }
                pb2.visibility=View.INVISIBLE
            }

        }

       // if(user!!.isEmailVerified==false)



    }


}








