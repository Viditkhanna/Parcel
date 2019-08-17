import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

//package h.user.tired
//
//import android.app.Activity
////import android.app.Fragment
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.app.Fragment
//
//import android.graphics.Color
//import android.graphics.Typeface
//import android.graphics.drawable.BitmapDrawable
//import android.graphics.drawable.Drawable
//import android.graphics.drawable.DrawableContainer
//import android.graphics.drawable.DrawableWrapper
//import android.media.Image
//import android.media.MediaPlayer
//import android.net.Uri
//import android.os.AsyncTask
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.support.annotation.MainThread
//import android.webkit.MimeTypeMap
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_home_page2.*
//import android.support.annotation.NonNull
//import android.support.v7.widget.LinearLayoutManager
//import android.util.DisplayMetrics
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import com.bumptech.glide.Glide
//import com.bumptech.glide.disklrucache.DiskLruCache
//import com.google.android.gms.auth.api.signin.internal.Storage
//import com.google.android.gms.tasks.OnFailureListener
//import com.google.android.gms.tasks.OnSuccessListener
//import com.google.firebase.FirebaseApp
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.UserInfo
//import com.google.firebase.database.*
//import com.google.firebase.storage.*
//import com.google.firebase.storage.StorageReference
//import com.google.firebase.storage.FileDownloadTask
//import java.io.*
//import java.lang.reflect.Array
//import java.util.concurrent.TimeUnit
//import kotlin.math.sqrt
//
//
//class home_page2 : AppCompatActivity() {
//    lateinit var userid:String
//    lateinit var newone:String
//    var numberones = ""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home_page2)
////Toast.makeText(this,"Oncreate",Toast.LENGTH_SHORT).show()
//
//
//
//
//
//
//
//
//
//
//
//
////        var  rootRef2 = FirebaseDatabase.getInstance().reference
////
////        var demoRef2 = rootRef2.child("Names")
////        demoRef2.addValueEventListener(object :ValueEventListener{
////            override fun onDataChange(p0: DataSnapshot) {
////          Toast.makeText(this@home_page2,"Rading users",Toast.LENGTH_SHORT).show()
////                for(ds:DataSnapshot in p0.children){
////
////              Toast.makeText(this@home_page2,ds.key.toString()+ds.value.toString(),Toast.LENGTH_LONG).show()
////
////          }
////
////            }
////
////            override fun onCancelled(p0: DatabaseError) {
////                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////            }
////
////
////        })
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        username()
//        // test.visibility=View.INVISIBLE
//     //   test.isEnabled=false
//                    //VALUE ENTER IN FIREBASE 'VALUEE'(0) TO CALL ONDATACHANGE
//        var  rootRef = FirebaseDatabase.getInstance().reference
//        //database reference pointing to demo node
//        var demoRef = rootRef.child("demo")
//        var value = "0"
//
//        demoRef.child("valuee").setValue(value)
//
////VALUEEVENTLISTENER FOR VALUE2 (FOR NUMBER OF IMAGES)
//        try{
//            demoRef.child("value2").addValueEventListener(object :ValueEventListener {
//
//                override fun onDataChange(p0: DataSnapshot) {
//                   // Toast.makeText(this@home_page2, "onDataSnapshot", Toast.LENGTH_SHORT).show()
//                   // Toast.makeText(this@home_page2, p0.value.toString(), Toast.LENGTH_SHORT).show()
//                    var size=p0.value.toString().length
//                    size-=1
//
//                  //  Toast.makeText(this@home_page2,size.toString(),Toast.LENGTH_SHORT).show()
//
//
//                    //TO DOWNLOAD A SINGLE IMAGE FROM FIREBASE
//                    // Create a storage reference from our app
//                    val storageRef = FirebaseStorage.getInstance().reference
//
////Download file in Memory
//                    //if(size>0){
//                       var  ones="1"
//                        for(i in 1 until size)
//                        ones+="1"
//                    newone=ones
//                       // Toast.makeText(this@home_page2,"uploads/"+ones+".png",Toast.LENGTH_SHORT).show()
//                     //  Toast.makeText(this@home_page2,size.toString(),Toast.LENGTH_SHORT).show()
//                       // for (j in 1 until size) {
//
//                          //  if(j==1)
//                        var islandRef = storageRef.child("uploads/"+ones+".png")
//                           // else
//                                //islandRef = storageRef.child("uploads/"+newone+".png")
//
//                          //  Toast.makeText(this@home_page2,"ones are"+ ones, Toast.LENGTH_SHORT).show()
//
//                            //islandRef.downloadUrl
//                          //  Toast.makeText(this@home_page2,islandRef.getDownloadUrl().toString(),Toast.LENGTH_SHORT).show()
//                            // Picasso.with(this@home_page2).load(islandRef).into(pic)
//
//
//                            var ONE_MEGABYTE = (1024 * 1024).toLong()
//                            islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener { ba: ByteArray ->
//                                var bmp = BitmapFactory.decodeByteArray(ba, 0, ba.size);
//                                // Picasso.with(this@home_page2).load(islandRef).into(pic)
//                                // pic.setImageBitmap(Bitmap.createScaledBitmap(bmp, pic.getWidth(),
//                                //       pic.getHeight(), false));
//
//
//
////
////                                if(size==ones.length)
////                                pic.setImageDrawable(drawable)
////                                else if(size-1==ones.length)
////                                test.setImageDrawable(drawable)
//                                // if(j==1)
//                               // newone=ones.substring(1 ,(ones.length)-1)
//                               // else
//                              //newone=ones.substring(1 ,(newone.length)-1)
//
//                                //pic.layoutParams=lp
//
//                                //var j=size-1
//                               // ones=ones.substring(1..j)
//                                //pic.setImageBitmap(ba)
//                                //  var mUpload=MutableList<String>()
//                                // recycler.setHasFixedSize(false)
//                                //recycler.layoutManager= LinearLayoutManager(this@home_page2)
//                                //Upload(upload.toString())
//
//                                // mUpload.add(ones)
//
//                                // var mAdapter:ImageAdapter = ImageAdapter(this@home_page2, mUpload)
//                                }.addOnFailureListener { ex ->
//                                if (ex.toString() == "com.google.firebase.storage.StorageException: Object does not exist at location.") {
//
//                                    try{
//                                    var change_size:Int=size
//                                    var yaxis=200f
//
//                                    for(i in 0 until size){
//                                        try{
//                                       rl.layoutParams.height+=800
//                                      //  Toast.makeText(this@home_page2,"No.s of ones are(upar) "+ones,Toast.LENGTH_SHORT).show()
//                                        var iv=ImageView(this@home_page2)
//                                            var iv2=ImageView(this@home_page2)
//                                            rl.addView(iv2)
//                                        rl.addView(iv)
//                                           // Toast.makeText(this@home_page2, "Loop ones are"+ones, Toast.LENGTH_SHORT).show()
//                                            iv2.x=0f
//                                            iv2.layoutParams.height=100
//                                            iv2.layoutParams.width=100
//                                            iv2.visibility=View.VISIBLE
//                                            iv2.y=yaxis+600f
//                                         //   iv2.background=
//                                            iv.layoutParams.height=600
//                                            var dm=DisplayMetrics()
//                                            windowManager.defaultDisplay.getMetrics(dm)
//                                            iv.layoutParams.width=dm.widthPixels
//                                        iv.x=0f
//                                        iv.y=yaxis
//                                        yaxis+=800f
//                                        iv.isClickable=true
//                                        iv.isLongClickable=true
//                                           // assignusername(ones,user,iv,iv2,size)
//
//                                            val storageRef = FirebaseStorage.getInstance().reference
//                                  //          Toast.makeText(this@home_page2,"Download images ones are"+ones,Toast.LENGTH_SHORT).show()
//                                            val islandRef = storageRef.child("uploads/" + ones + ".jpg")
//                                            val ONE_MEGABYTE = (3000000).toLong()
//                                            islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener { ba: ByteArray ->
//                                                try {
//                                                    var bmp = BitmapFactory.decodeByteArray(ba, 0, ba.size);
//                                                    iv.setImageBitmap(Bitmap.createScaledBitmap(bmp, iv.getWidth(),
//                                                            iv.getHeight(), false));
//
//                                                    var send=true
//
//                                                    var user1=FirebaseAuth.getInstance().currentUser
//                                                    var uid1=user1!!.uid
//                                                    var  rootRef2 = FirebaseDatabase.getInstance().reference
//
//                                                    var demoRef2 = rootRef2.child("Names").child(uid1)
//                                                    var picno:Int=iv.y.toInt()/800.toInt()
//                                                    picno++
//                                                    // Toast.makeText(this@home_page2,picno.toString(),Toast.LENGTH_SHORT).show()
//                                                    var picones=size-picno
//                                                    picones++
//                                                    //Toast.makeText(this@home_page2,picones.toString(),Toast.LENGTH_SHORT).show()
//                                                    var  rootRef = FirebaseDatabase.getInstance().reference
//                                                    //database reference pointing to demo node
//                                                    var demoRef = rootRef.child("demo")
//                                                    demoRef.child("valuee").setValue(0)
//
//                                                    try {
//                                                        var picone = "1"
//                                                        for (i in 0 until picones) {
//                                                            picone = picone + "1"
//                                                        }
//                                                        picone = picone.substring(0, picone.length - 1)
//
//                                                        demoRef2.addValueEventListener(object :ValueEventListener{
//
//                                                            override fun onDataChange(p0: DataSnapshot) {
//                                                                //          Toast.makeText(this@home_page2,picone,Toast.LENGTH_SHORT).show()
//
//                                                                for(ds:DataSnapshot in p0.children){
//                                                                    //  Toast.makeText(this@home_page2,"Reading users",Toast.LENGTH_SHORT).show()
//                                                                    //  Toast.makeText(this@home_page2,ds.value.toString(),Toast.LENGTH_SHORT).show()
//
//                                                                    if(picone.toString().equals(ds.key.toString())){
//                                                                        //  Toast.makeText(this@home_page2,"Already Liked",Toast.LENGTH_LONG).show()
//                                                                        send=false
//                                                                        break
//                                                                    }
//
//                                                                }
//
//                                                                val ref = FirebaseDatabase.getInstance().reference
//
//                                                                val user = FirebaseAuth.getInstance().currentUser
//                                                                val uid = user!!.uid
//                                                                //  Toast.makeText(this@home_page2,send.toString(),Toast.LENGTH_SHORT).show()
//
//                                                                if(send){
//                                                                    iv2.setImageResource(R.drawable.starnew)
//
////                                                          ref.child("Names").child(uid).push().setValue(picone)
//                                                                }
//                                                                else{
//                                                                    iv2.setImageResource(R.drawable.starnewnew)
//
//                                                                }
//
//
//
//                                                            }
//
//                                                            override fun onCancelled(p0: DatabaseError) {
//                                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                                                            }
//
//
//                                                        })
//
////                                        pic.setImageBitmap(Bitmap.createScaledBitmap(bmp, pic.getWidth(),
////                                                pic.getHeight(), false));
//                                                    }catch (ex:Exception){}
//
//                                                }catch (ex:Exception){//Toast.makeText(this@home_page2,ex.toString(),Toast.LENGTH_SHORT).show()
//                                                }}
//
//
//
//
//
//
//
//
//
//
//
//
//
//                                            iv.setOnLongClickListener {
//                                            var mp=MediaPlayer.create(this@home_page2,R.raw.recordaudio)
//                                            mp.start()
//
//                                            //  Toast.makeText(this@home_page2,"Pic has been Long clicked",Toast.LENGTH_SHORT).show()
//                                            var picno:Int=iv.y.toInt()/800.toInt()
//                                            picno++
//                                           // Toast.makeText(this@home_page2,picno.toString(),Toast.LENGTH_SHORT).show()
//                                            var picones=size-picno
//                                            picones++
//                                            //Toast.makeText(this@home_page2,picones.toString(),Toast.LENGTH_SHORT).show()
//                                            var  rootRef = FirebaseDatabase.getInstance().reference
//                                            //database reference pointing to demo node
//                                            var demoRef = rootRef.child("demo")
//                                            demoRef.child("valuee").setValue(0)
//
//                                            try {
//                                                var picone = "1"
//                                                for (i in 0 until picones) {
//                                                    picone = picone + "1"
//                                                }
//                                                picone = picone.substring(0, picone.length - 1)
//
//                                                var  rootRef2 = FirebaseDatabase.getInstance().reference
//                                                var send=true
//
//                                                var user1=FirebaseAuth.getInstance().currentUser
//                                                var uid1=user1!!.uid
//                                                var demoRef2 = rootRef2.child("Names").child(uid1)
//                                                demoRef2.addValueEventListener(object :ValueEventListener{
//
//                                                    override fun onDataChange(p0: DataSnapshot) {
//                                              //          Toast.makeText(this@home_page2,picone,Toast.LENGTH_SHORT).show()
//
//                                                        for(ds:DataSnapshot in p0.children){
//                                                          //  Toast.makeText(this@home_page2,"Reading users",Toast.LENGTH_SHORT).show()
//                                                       //     Toast.makeText(this@home_page2,ds.value.toString(),Toast.LENGTH_SHORT).show()
//
//                                                            if(picone.toString().equals(ds.value.toString())){
//                                                              //  Toast.makeText(this@home_page2,"Already Liked",Toast.LENGTH_LONG).show()
//                                                                send=false
//                                                                break
//                                                            }
//
//                                                        }
//
//                                                        val ref = FirebaseDatabase.getInstance().reference
//
//                                                        val user = FirebaseAuth.getInstance().currentUser
//                                                        val uid = user!!.uid
//                                                      //   Toast.makeText(this@home_page2,send.toString(),Toast.LENGTH_SHORT).show()
//
//                                                        if(send)
//                                                        ref.child("Names").child(uid).child(picone).setValue("1")
//
//
//                                                    }
//
//                                                    override fun onCancelled(p0: DatabaseError) {
//                                                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                                                    }
//
//
//                                                })
//
//
//                                                // Toast.makeText(this@home_page2,send.toString(),Toast.LENGTH_SHORT).show()
//
//                                                //  Toast.makeText(this@home_page2,picone.toString(),Toast.LENGTH_SHORT).show()
//
//                                                //  Toast.makeText(this@home_page2,p0.child(picone).value.toString(),Toast.LENGTH_SHORT).show()
//                                            }catch(ex:Exception){}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                                                    return@setOnLongClickListener true
//                                        }
//                                        change_size--
//
//                                       // Toast.makeText(this@home_page2,change_size.toString(),Toast.LENGTH_SHORT).show()
//                                        ones=ones.substring(0,change_size)
//                                       //     newone=ones
//                                        }catch (ex:Exception){}
//                                       // Toast.makeText(this@home_page2,"No.s of ones are(niche) "+ones,Toast.LENGTH_SHORT).show()
//
//                                    }}catch (ex:Exception){}
//                                    try{
//                                      rl.layoutParams.height-=200}catch (ex:java.lang.Exception){}
//                                } else{}
//                                   // Toast.makeText(this@home_page2, ex.toString(), Toast.LENGTH_SHORT).show()
//
//                                // Handle any errors
//                         assignusername()   }
//                      //  }
//
//
//                    //}
//
//                }
//
//                override fun onCancelled(p0: DatabaseError) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//                })
//
//        }catch(ex:Exception){}
//
//
//
//
//
//
//
//
//
//
//
//
//
//        upload.setOnClickListener {
//            Toast.makeText(this@home_page2,"Signing out",Toast.LENGTH_SHORT).show()
//            var mAuth=FirebaseAuth.getInstance()
//            mAuth.currentUser
//            mAuth.signOut()
//            startActivity(Intent(this@home_page2,tiredy::class.java))
//           finish()
//        }
//
//
//
//
//
//
//message.setOnClickListener {
//    startActivity(Intent(this,Chat2::class.java))
//
//}
//
//findfriends.setOnClickListener {
//    callfindfriends()
//}
//friends.setOnClickListener {
//    callfriends()
//}
//
//
//
////        var string="https://firebasestorage.googleapis.com/v0/b/fir-3-7647d.appspot.com/o/uploads%2F1540142834607.jpg?alt=media&token=bdd985cd-2fb6-4d87-ad04-30e91df4e14c"
////        Picasso.with(this)
////                .load(string)
////                .placeholder(R.mipmap.ic_launcher)
////                .fit()
////                .centerCrop()
////                .into(test)
////        //var storage:FirebaseStorage= FirebaseStorage.getInstance()
//        addphoto.setOnClickListener {
//            addphoto()
//        }
//uploads.setOnClickListener{
//
//
//
//
//    //TO DOWNLOAD A SINGLE IMAGE FROM FIREBASE
////    // Create a storage reference from our app
////    val storageRef = FirebaseStorage.getInstance().reference
////
//////Download file in Memory
////    val islandRef = storageRef.child("uploads/1539112261495.png")
////
////    val ONE_MEGABYTE = (1024 * 1024).toLong()
////    islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {ba:ByteArray->
////       var bmp = BitmapFactory.decodeByteArray(ba, 0, ba.size);
////
////pic.setImageBitmap(Bitmap.createScaledBitmap(bmp, pic.getWidth(),
////                pic.getHeight(), false));
////
////
////
////        // Data for "images/island.jpg" is returns, use this as needed
////    }.addOnFailureListener {ex->
////        Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show()
////        // Handle any errors
////    }
////
////
////    if (fileList().contains("Imageurl.txt") && fileList().contains("Imageurl2.txt")) {
////        try {
////            val file = InputStreamReader(openFileInput("Imageurl.txt"))
////            val br = BufferedReader(file)
////            var line = br.readLine()
////            val all = StringBuilder()
////            while (line != null) {
////                all.append(line + "\n")
////                line = br.readLine()
////            }
////            br.close()
////            file.close()
////           // Toast.makeText(this,all,Toast.LENGTH_SHORT).show()
////
////        } catch (e: IOException) {
////        }
////        try {
////            val file = InputStreamReader(openFileInput("Imageurl2.txt"))
////            val br = BufferedReader(file)
////            var line = br.readLine()
////            val all = StringBuilder()
////            while (line != null) {
////                all.append(line + "\n")
////                line = br.readLine()
////            }
////            br.close()
////            file.close()
////           // Toast.makeText(this,all,Toast.LENGTH_SHORT).show()
////
////        } catch (e: IOException) {
////        }
////
////
////    }
////startActivity(Intent(this,Delete::class.java))
//
//}
//
//
//    }
//
//    fun callfindfriends() {
////startActivity(Intent(this,friendsactivity::class.java))
//startActivity(Intent(this,Tabbed::class.java))
////        try {
////            val fm = fragmentManager
////            val ft = fm!!.beginTransaction()
////            var newfragment: Fragment? = null
////            newfragment = fragmentone()
////            ft.replace(R.id.container1, newfragment)
////            //ft.addToBackStack(null);
////            ft.commit()
////        }catch (ex:Exception){}
//
//    }
//
//    fun addphoto(){
//        try {
//            var int = Intent()
//            int.setType("image/*")
//            int.setAction(Intent.ACTION_GET_CONTENT)
//            startActivityForResult(int, 111)
//        }catch(ex:Exception){}
//    }
//    fun callfriends(){
//startActivity(Intent(this,viewfriends::class.java))
//
//    }
//    fun getFileExtension(uri:Uri):String {
//        var cR = getContentResolver();
//        var mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cR.getType(uri));
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if(requestCode==111 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null){
//           var imageuri=data.data
//            Picasso.with(this).load(imageuri).into(pic)
//
//
//
//
//
//if(imageuri!=null) {
//
//
//        var mDatabaseRef:DatabaseReference=FirebaseDatabase.getInstance().getReference("uploads")
//
//   var mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
//
//
//    var  rootRef = FirebaseDatabase.getInstance().reference
//    //database reference pointing to demo node
//    var demoRef = rootRef.child("demo")
//            var value = "0"
//    var du=DemoUpload(value)
//            demoRef.child("valuee").setValue(value)
//
//    lateinit var t:String
//    lateinit var p00:String
//    var i=1
//
//    try{
//    demoRef.child("value2").addValueEventListener(object :ValueEventListener {
//
//        override fun onDataChange(p0: DataSnapshot) {
//            //Toast.makeText(this@home_page2, "onDataSnapshot", Toast.LENGTH_SHORT).show()
//          //  Toast.makeText(this@home_page2, p0.value.toString(), Toast.LENGTH_SHORT).show()
//            var usrname=""
//
//            if(i==1){
//            val fileReference = mStorageRef.child(p0.value.toString()
//                    + "." + "jpg")
//            fileReference.putFile(imageuri)
//                    .addOnSuccessListener { taskSnapshot ->
//
//                        val handler = Handler()
//                        handler.postDelayed(Runnable { pb.setProgress(0) }, 500)
//
//                        //Toast.makeText(this@home_page2, "Upload successful", Toast.LENGTH_LONG).show()
//                        val upload = Upload(taskSnapshot.metadata!!.reference!!.downloadUrl.toString())
//                        val uploadId = mDatabaseRef!!.push().getKey()
//
//                        try{
//                            var user=FirebaseAuth.getInstance().currentUser
//                            var uid=user!!.uid
//                            //   Toast.makeText(this@home_page2,uid.toString(),Toast.LENGTH_SHORT).show()
//                            var databaseReference=FirebaseDatabase.getInstance().getReference()
//                            databaseReference.child("Names").addValueEventListener(object: ValueEventListener {
//                                override fun onDataChange(p0: DataSnapshot) {
//                                    try {
//                                         usrname = p0.child(uid).child("Name").value.toString()
//
//                                          Toast.makeText(this@home_page2,"Uploaded Successfully", Toast.LENGTH_SHORT).show()
//                                        // Toast.makeText(this@home_page2, "Ballinder", Toast.LENGTH_SHORT).show()
//                                    }catch (ex:Exception){Toast.makeText(this@home_page2,ex.toString(),Toast.LENGTH_SHORT).show()}
//
//
//                                }
//
//                                override fun onCancelled(p0: DatabaseError) {
//                                }
//
//                            })}catch (ex:Exception){}
//
//
//
//
//
//
//
//
//
////                        Picasso.with(this@home_page2)
////                                .load(taskSnapshot.metadata!!.reference!!.downloadUrl.toString())
////                                .fit()
////                                .centerCrop()
////                                .placeholder(R.mipmap.ic_launcher)
////                                .into(test)
//                       // Toast.makeText(this@home_page2,taskSnapshot.metadata!!.reference!!.downloadUrl.toString(),Toast.LENGTH_SHORT).show()
//
//                    }
//                    .addOnFailureListener { e -> //Toast.makeText(this@home_page2, e.message, Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnProgressListener{ taskSnapshot ->
//                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
//                        pb.setProgress(progress.toInt())
//                        i=0
//                        var  rootRef = FirebaseDatabase.getInstance().reference
//                        //database reference pointing to demo node
//                        var demoRef = rootRef.child("demo")
//                        var value = p0.value.toString()+"1"
//                        var uid=FirebaseAuth.getInstance().uid
//                        val valu=value.substring(0,value.length-1)
//                        mDatabaseRef!!.child(valu).setValue(uid.toString())
//
//                        demoRef.child("value2").setValue(value)
//                      //  demoRef.child("Likes/"+value).setValue(0)
//
//                    }}
//
//
//
//
//
//
////            for (out: DataSnapshot in p0.children) {
////                Toast.makeText(this@home_page2, "onDataSnapshot 1", Toast.LENGTH_SHORT).show()
////
////                for (value1: DataSnapshot in out.child("value").children) {
////                    Toast.makeText(this@home_page2, "onDataSnapshot 2", Toast.LENGTH_SHORT).show()
////
////                    var tt:String = value1.getKey()!!
////                    var v = value1.value
////                    if(tt=="" ||v=="")
////                        Toast.makeText(this@home_page2, "Empty", Toast.LENGTH_SHORT).show()
////                    else
////                    Toast.makeText(this@home_page2, tt.toString() + v.toString(), Toast.LENGTH_SHORT).show()
////
////                }
////            }
////     var text=p0.getValue(DemoUpload::class.java)
////      Toast.makeText(this@home_page2,text!!.text,Toast.LENGTH_SHORT).show()
////  t=text!!.text
//
//
//        }
//
//    override fun onCancelled(p0: DatabaseError) {
//    }
//
//})}catch (ex:Exception){
//        //Toast.makeText(this,ex.toString(), Toast.LENGTH_SHORT).show()
//
//    }
//    try {
//        val file1 = OutputStreamWriter(openFileOutput("count.txt", Activity.MODE_PRIVATE))
//
//        file1.append("R")
//        // file1.append("Hey how you a doing");
//        file1.flush()
//        file1.close()
//    } catch (e: IOException) {
//
//    }
////    lateinit var all:StringBuilder
////    if (fileList().contains("count.txt") ) {
////        try {
////            val file = InputStreamReader(openFileInput("count.txt"))
////            val br = BufferedReader(file)
////            var line = br.readLine()
////            all = StringBuilder()
////            while (line != null) {
////                all.append(line + "\n")
////                line = br.readLine()
////            }
////            br.close()
////            file.close()
////            Toast.makeText(this,all,Toast.LENGTH_SHORT).show()
////
////        } catch (e: IOException) {
////        }
//
//
//
//}else
//    Toast.makeText(this,"Image not selected",Toast.LENGTH_SHORT).show()
//
//        }
//
//
//        super.onActivityResult(requestCode, resultCode, data)
//    }
//fun assignusername() {
//    var i :Int=0
//var ref=FirebaseDatabase.getInstance().reference
//    ref.child("uploads").addListenerForSingleValueEvent(object :ValueEventListener{
//        override fun onCancelled(p0: DatabaseError) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//            var i=newone.length-1
//        override fun onDataChange(p0: DataSnapshot) {
//    //Toast.makeText(this@home_page2,newone.toString(),Toast.LENGTH_SHORT).show()
//        for (p:DataSnapshot in p0.children)
//            {
//               //  Toast.makeText(this@home_page2,p.key.toString()+p.value.toString(),Toast.LENGTH_SHORT).show()
//                var userid=p.value.toString()
//
//
//
//                useridtoname(userid,i,rl.layoutParams.height-860)
//                i+=800
//
//            }
//
//
//
//
//
//
//        }
//
//
//
//
//
//
//    })
//
//}
//
//    fun useridtoname(userid:String,i:Int,j:Int){
//     //   var j=i
//        var ref=FirebaseDatabase.getInstance().reference
//        ref.child("Names").child(userid).addValueEventListener(object :ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//            for(p:DataSnapshot in p0.children){
//                if(p.key.toString().equals("Name")){
//                    //Toast.makeText(this@home_page2,p.value.toString(),Toast.LENGTH_SHORT).show()
//                    var userview=TextView(this@home_page2)
//                    rl.addView(userview)
//                    //Toast.makeText(this@home_page2,rl.layoutParams.height.toString()+i.toString(),Toast.LENGTH_SHORT).show()
//                    userview.setText(p.value.toString())
//                    userview.layoutParams.width=1000
//                    userview.layoutParams.height=1000
//                    userview.visibility=View.VISIBLE
//                    userview.y=j.toFloat()-i.toFloat()
//                    userview.textSize=16f
//                    userview.setTextColor(Color.parseColor("#000000"))
//                    userview.setTypeface(null, Typeface.BOLD)
//
//                }
//
//            }
//
//            }
//
//
//        })
//
//    }
//    fun downloadimages(iv:ImageView,iv2:ImageView,size:Int,ones:String){
//
//    }
//    override fun onBackPressed() {
//            //  Toast.makeText(this,"chal peeche",Toast.LENGTH_SHORT).show()
//
//        var int=Intent(Intent.ACTION_MAIN)
//        int.addCategory(Intent.CATEGORY_HOME)
//       int.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//       startActivity(int)
//
////        int.putExtra("EXIT",true)
////       Toast.makeText(this,"chal peeche",Toast.LENGTH_SHORT).show()
////        startActivity(int)
//       // moveTaskToBack(true)
//        super.onBackPressed()
//    }
//fun username(){
//try{
//    var user=FirebaseAuth.getInstance().currentUser
//    var uid=user!!.uid
// //   Toast.makeText(this@home_page2,uid.toString(),Toast.LENGTH_SHORT).show()
//    var databaseReference=FirebaseDatabase.getInstance().getReference()
//    databaseReference.child("Names").addValueEventListener(object: ValueEventListener {
//        override fun onDataChange(p0: DataSnapshot) {
//          try {
//              var username = p0.child(uid).child("Name").value
//            //  Toast.makeText(this@home_page2, username.toString(), Toast.LENGTH_SHORT).show()
//             // Toast.makeText(this@home_page2, "Ballinder", Toast.LENGTH_SHORT).show()
//          }catch (ex:Exception){Toast.makeText(this@home_page2,ex.toString(),Toast.LENGTH_SHORT).show()}
//
//        }
//
//        override fun onCancelled(p0: DatabaseError) {
//        }
//
//    })}catch (ex:Exception){}
//}
//
//    override fun onPause() {
//        try{
//         //   startActivity(Intent(this, homepage2::class.java))
//           // Toast.makeText(this, "onPasue", Toast.LENGTH_SHORT).show()
//        }catch(ex:Exception){}
//        super.onPause()
//    }
//
//    override fun onResume() {
//       // Toast.makeText(this@home_page2,"resume",Toast.LENGTH_SHORT).show()
//        super.onResume()
//    }
//}
