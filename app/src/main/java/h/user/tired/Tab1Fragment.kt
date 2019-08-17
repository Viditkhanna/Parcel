package h.user.tired


import android.app.Activity
import android.app.FragmentTransaction
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.*
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.firebase.ui.FirebaseUI
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home_page2.*
import kotlinx.android.synthetic.main.activity_tabbed.*
import kotlinx.android.synthetic.main.image_item.*
import kotlinx.android.synthetic.main.tab1.*
import kotlinx.android.synthetic.main.tab1.view.*
import kotlinx.android.synthetic.main.tab3.*
import kotlinx.android.synthetic.main.tab3.view.*
import java.text.SimpleDateFormat
import java.util.*

lateinit var newone:String
lateinit var mUpload:MutableList<Upload>
lateinit var downloadurl:String
class Tab1Fragment : Fragment() {
    lateinit var con:Context

    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: ImageAdapter
    val ab= ArrayList<Upload>()
    var flagss=true

    lateinit var storagereff:DatabaseReference
lateinit var vieww:View
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu, menu);  // Use filter.xml from step 1

    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        activity!!.getMenuInflater().inflate(R.menu.menu, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_filter) {
            //Do whatever you want to do
            true
        } else super.onOptionsItemSelected(item)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setHasOptionsMenu(true);

//        val  rootRef = FirebaseDatabase.getInstance().reference
//        //database reference pointing to demo node
//        val demoRef = rootRef.child("demo")
//        val value = "0"
//
//        demoRef.child("valuee").setValue(value)
//
//        try{
//
//            demoRef.child("value2").addValueEventListener(object : ValueEventListener {
//
//                override fun onDataChange(p0: DataSnapshot) {
//                    var size=p0.value.toString().length
//                    size-=1
//
//                    var  ones="1"
//                    for(i in 1 until size)
//                        ones+="1"
//                    newone=ones
//
//                }
//
//                override fun onCancelled(p0: DatabaseError) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })
//
//        }catch(ex:Exception){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
//        }

    }

    fun callfindfriends() {
startActivity(Intent(activity,friendsactivity::class.java))

    }

    fun addphoto11(){
        try {
            var int = Intent()
            int.setType("image/*")
            int.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(int, 111)
        }catch(ex:Exception){}
    }
    fun callfriends(){
        startActivity(Intent(activity,viewfriends::class.java))

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==111 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null){

            val imageuri=data.data
//            Picasso.with(activity).load(imageuri).into(pic)





            if(imageuri!=null) {

newupload.visibility=View.VISIBLE
                var mDatabaseRef: DatabaseReference =FirebaseDatabase.getInstance().getReference("uploads")

                var mStorageRef = FirebaseStorage.getInstance().getReference("uploads")


                var  rootRef = FirebaseDatabase.getInstance().reference
                //database reference pointing to demo node
                var demoRef = rootRef.child("demo")
                var value = "0"
                var du=DemoUpload(value)
                demoRef.child("valuee").setValue(value)

                lateinit var t:String
                lateinit var p00:String
                var i=1

                try{
                    demoRef.child("value2").addListenerForSingleValueEvent(object :ValueEventListener {

                        override fun onDataChange(p0: DataSnapshot) {
                            //Toast.makeText(this@home_page2, "onDataSnapshot", Toast.LENGTH_SHORT).show()
                            //  Toast.makeText(this@home_page2, p0.value.toString(), Toast.LENGTH_SHORT).show()
                            var usrname=""

                            if(i==1){
                                val fileReference = mStorageRef.child(p0.value.toString()
                                        + "." + "jpg")
                                fileReference.putFile(imageuri)
                                        .addOnSuccessListener { taskSnapshot ->

                                            Toast.makeText(activity,"Uploaded Successfully", Toast.LENGTH_SHORT).show()
                                            Toast.makeText(activity,"Loading...", Toast.LENGTH_SHORT).show()

                                            newupload.visibility=View.INVISIBLE

                                            val handler = Handler()
                                            handler.postDelayed(Runnable { pb.setProgress(0) }, 500)

                                            //                                            try{
//                                            Glide.with(activity).load(taskSnapshot.task.result?.metadata?.reference?.downloadUrl)
////                                            Picasso.with(activity).load(taskSnapshot.task.snapshot.uploadSessionUri).into(pic)
//pic.setImageURI(taskSnapshot.metadata?.reference?.downloadUrl?.result)}catch (ex:Exception){
//                                                Toast.makeText(activity, ex.toString(), Toast.LENGTH_LONG).show()
//
//                                            }
//  Toast.makeText(activity, taskSnapshot.uploadSessionUri, Toast.LENGTH_LONG).show()

//                                            val upload = Upload(taskSnapshot.metadata!!.reference!!.downloadUrl.toString())
//                                            val uploadId = mDatabaseRef!!.push().getKey()










//                        Picasso.with(this@home_page2)
//                                .load(taskSnapshot.metadata!!.reference!!.downloadUrl.toString())
//                                .fit()
//                                .centerCrop()
//                                .placeholder(R.mipmap.ic_launcher)
//                                .into(test)
                                            // Toast.makeText(this@home_page2,taskSnapshot.metadata!!.reference!!.downloadUrl.toString(),Toast.LENGTH_SHORT).show()

                                        }
                                        .addOnFailureListener { e -> //Toast.makeText(this@home_page2, e.message, Toast.LENGTH_SHORT).show()
                                            newupload.visibility=View.INVISIBLE

                                        }
                                        .addOnProgressListener{ taskSnapshot ->
                                            val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                                            pb.setProgress(progress.toInt())
                                            i=0
                                            var  rootRef = FirebaseDatabase.getInstance().reference
                                            //database reference pointing to demo node
                                            var demoRef = rootRef.child("demo")
                                            var value = p0.value.toString()+"1"
                                            var uid=FirebaseAuth.getInstance().uid
                                            val valu=value.substring(0,value.length-1)
                                            mDatabaseRef.child(valu).setValue(uid.toString())

                                            demoRef.child("value2").setValue(value)
                                            //  demoRef.child("Likes/"+value).setValue(0)

                                        }.addOnCompleteListener {task ->
                                            try {
//                                                Toast.makeText(activity, "Heyyyyy", Toast.LENGTH_LONG).show()
//                                            Picasso.with(activity).load( task.result!!.uploadSessionUri).into(pic)
////                                            pic.setImageURI(task./)
//                                                Glide.with(context).load(task.result!!.metadata!!.reference!!.downloadUrl).into(pic)


                                                                    if(task.isComplete && task.isSuccessful)
                                                FirebaseStorage.getInstance().getReference("uploads").child(p0.value.toString()+ "." + "jpg")
                                                        .downloadUrl.addOnSuccessListener { uri ->
//                                                    Toast.makeText(activity,uri.toString()+"3333",Toast.LENGTH_SHORT).show()
                                                    FirebaseDatabase.getInstance().getReference("Images").child("ICounter")

                                                        //Current user kai Images ka counter
                                                     try {
                                                        val upvotesRef = FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString())
                                                                .child("Images")
                                                                .child("ICounter")
                                                        upvotesRef.runTransaction(object: Transaction.Handler {
                                                            override fun doTransaction(p0: MutableData): Transaction.Result {
                                                                try {

                                                                    var value:Long = 0
                                                                    if(p0.getValue() != null) {
                                                                        val numQuestions =  p0.getValue().toString()
                                                                        value = numQuestions.toLong(16);
                                                                    }
                                                                    value--;
                                                                    val incHex = (value).toString(16);
                                                                    p0.setValue(incHex);

                                                                } catch (e: Exception) {Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()
                                                                }
                                                                return Transaction.success(p0)

                                                            }

                                                            override fun onComplete(p0: DatabaseError?, p1: Boolean, p2: DataSnapshot?) {
//                                                                Toast.makeText(activity,"Yaa "+ p2!!.value.toString(), Toast.LENGTH_LONG).show()
                                                           try{
                                                                FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString())
                                                                        .child("Images")
                                                                        .child(p2!!.value.toString())
                                                                    .setValue(uri.toString()+ "." + "jpg").addOnCompleteListener { task ->

                                                                        }}catch (e:Exception){Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()}
                                                            }

                                                        })
                                                    } catch (e: Exception) {Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show() }
                                                                    //Current user kai friends
                                                    FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString()).child("Friends").addListenerForSingleValueEvent(object:ValueEventListener{
                                                                    override fun onCancelled(p0: DatabaseError) {
                                                                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                                                    }

                                                                    override fun onDataChange(p0: DataSnapshot) {
                                                                        for(p:DataSnapshot in p0.children) {//p contains id of friends

//                                                                        Toast.makeText(activity,p.value.toString(),Toast.LENGTH_SHORT).show()
                                                                         val upvotesRef=FirebaseDatabase.getInstance().getReference("Names").child(p.value.toString()).child("NCounter")
                                                                            upvotesRef.runTransaction(object:Transaction.Handler{

                                                                                override fun doTransaction(p0: MutableData): Transaction.Result {
                                                                                    try {

                                                                                        var value:Long = 0
                                                                                        if(p0.getValue() != null) {
                                                                                            val numQuestions =  p0.getValue().toString()
                                                                                            value = numQuestions.toLong(16);
                                                                                        }
                                                                                        value--;
                                                                                        val incHex = (value).toString(16);
                                                                                        p0.setValue(incHex);

                                                                                    } catch (e: Exception) {Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()
                                                                                    }
                                                                                    return Transaction.success(p0)


                                                                                }

                                                                                override fun onComplete(p0: DatabaseError?, p1: Boolean, p2: DataSnapshot?) {
                                                                                    try {
                                                                                        val sdf=SimpleDateFormat("dd").format(Date())
//                                                                                        Toast.makeText(activity,p.value.toString(),Toast.LENGTH_SHORT).show()
                                                                                        FirebaseDatabase.getInstance().getReference("Names")
                                                                                            .child(p.value.toString())
                                                                                            .child("Notification")
                                                                                            .child(p2!!.value.toString())
                                                                                            .child(FirebaseAuth.getInstance().uid.toString()).setValue(uri.toString()).addOnCompleteListener {
                                                                                                 try {
                                                                                                     FirebaseDatabase.getInstance().getReference("Names")
                                                                                                             .child(p.value.toString())
                                                                                                             .child("Notification")
                                                                                                             .child(p2.value.toString())
                                                                                                             .child(sdf.toString()).setValue("0")
                                                                                                 }catch (e:Exception){
                                                                                                     Toast.makeText(activity,e.toString(), Toast.LENGTH_SHORT).show()
                                                                                                 }
                                                                                                }.addOnFailureListener { exception ->
                                                                                                    Toast.makeText(activity,exception.message.toString(),Toast.LENGTH_SHORT).show()

                                                                                                }
                                                                                    } catch (e: Exception) {Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()
                                                                                    }

                                                                                }


                                                                            })
//                                                                            Toast.makeText(activity, p.value.toString(), Toast.LENGTH_SHORT).show()



                                                                        }
                                                                    }



                                                    })
                                                                                    try {
                                                                                        val upvotesRef2 = FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString()).child("NCounter")
                                                                                        upvotesRef2.runTransaction(object: Transaction.Handler
                                                                                        {
                                                                                            override fun doTransaction(p0: MutableData): Transaction.Result {

                                                                                                try {
                                                                                                    var value:Long = 0
                                                                                                    if(p0.getValue() != null) {
                                                                                                        val numQuestions =  p0.getValue().toString()
                                                                                                        value = numQuestions.toLong(16);
                                                                                                    }
                                                                                                    value--;
                                                                                                    val incHex = (value).toString(16);
                                                                                                    p0.setValue(incHex);
                                                                                                } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()

                                                                                                }

                                                                                                return Transaction.success(p0)


                                                                                            }

                                                                                            override fun onComplete(p0: DatabaseError?, p1: Boolean, p2: DataSnapshot?) {

//                                                                                                    Toast.makeText(activity, "321", Toast.LENGTH_SHORT).show()

                                                                                                    try {
                                                                                                        val sdf = SimpleDateFormat("dd").format(Date())
                                                                                                        FirebaseDatabase.getInstance().getReference("Names")
                                                                                                                .child(FirebaseAuth.getInstance().uid.toString())
                                                                                                                .child("Notification")
                                                                                                                .child(p2!!.value.toString())
                                                                                                                .child(FirebaseAuth.getInstance().uid.toString()).setValue(uri.toString()).addOnCompleteListener {Task->
                                                                                                                    try {
                                                                                                                        if(Task.isComplete && Task.isSuccessful){
//                                                                                                                        Toast.makeText(activity, "123", Toast.LENGTH_SHORT).show()
                                                                                                                        FirebaseDatabase.getInstance().getReference("Names")
                                                                                                                                .child(FirebaseAuth.getInstance().uid.toString())
                                                                                                                                .child("Notification")
                                                                                                                                .child(p2.value.toString())
                                                                                                                                .child(sdf.toString()).setValue("0")
                                                                                                                        }
                                                                                                                    } catch (e: Exception) {
                                                                                                                        Toast.makeText(activity,  e.toString(), Toast.LENGTH_SHORT).show()
                                                                                                                    }
                                                                                                                }.addOnFailureListener { exception ->
                                                                                                                    Toast.makeText(activity, exception.message.toString(), Toast.LENGTH_SHORT).show()

                                                                                                                }
                                                                                                    } catch (e: Exception) {
                                                                                                        Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()
                                                                                                    }

                                                                                            }

                                                                                        })}catch (ex:Exception){Toast.makeText(activity,ex.message.toString(),Toast.LENGTH_SHORT).show()
                                                                                    }














//                                                FirebaseDatabase.getInstance().getReference("Images").child("222").setValue("1")
                                                }.addOnFailureListener {exception: Exception -> Toast.makeText(activity,exception.toString(),Toast.LENGTH_SHORT).show() }else{

                                                                        Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
                                                                    }




                                            }catch (ex:Exception){  Toast.makeText(activity, ex.toString(), Toast.LENGTH_LONG).show()
                                            }}
                            }






//            for (out: DataSnapshot in p0.children) {
//                Toast.makeText(this@home_page2, "onDataSnapshot 1", Toast.LENGTH_SHORT).show()
//
//                for (value1: DataSnapshot in out.child("value").children) {
//                    Toast.makeText(this@home_page2, "onDataSnapshot 2", Toast.LENGTH_SHORT).show()
//
//                    var tt:String = value1.getKey()!!
//                    var v = value1.value
//                    if(tt=="" ||v=="")
//                        Toast.makeText(this@home_page2, "Empty", Toast.LENGTH_SHORT).show()
//                    else
//                    Toast.makeText(this@home_page2, tt.toString() + v.toString(), Toast.LENGTH_SHORT).show()
//
//                }
//            }
//     var text=p0.getValue(DemoUpload::class.java)
//      Toast.makeText(this@home_page2,text!!.text,Toast.LENGTH_SHORT).show()
//  t=text!!.text


                        }

                        override fun onCancelled(p0: DatabaseError) {
                            Toast.makeText(activity, p0.toString(), Toast.LENGTH_LONG).show()
                        }

                    })}catch (ex:Exception){
                    //Toast.makeText(this,ex.toString(), Toast.LENGTH_SHORT).show()

                }
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
//    lateinit var all:StringBuilder
//    if (fileList().contains("count.txt") ) {
//        try {
//            val file = InputStreamReader(openFileInput("count.txt"))
//            val br = BufferedReader(file)
//            var line = br.readLine()
//            all = StringBuilder()
//            while (line != null) {
//                all.append(line + "\n")
//                line = br.readLine()
//            }
//            br.close()
//            file.close()
//            Toast.makeText(this,all,Toast.LENGTH_SHORT).show()
//
//        } catch (e: IOException) {
//        }



            }else
                Toast.makeText(activity,"Image not selected",Toast.LENGTH_SHORT).show()

        }


        super.onActivityResult(requestCode, resultCode, data)
    }
    fun assignusername() {
        try{
            var i: Int = 0
            var ref = FirebaseDatabase.getInstance().reference
            ref.child("uploads").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                var i = newone.length - 1
                override fun onDataChange(p0: DataSnapshot) {
                    try{
                    //Toast.makeText(this@home_page2,newone.toString(),Toast.LENGTH_SHORT).show()
                    for (p: DataSnapshot in p0.children) {
                        //  Toast.makeText(this@home_page2,p.key.toString()+p.value.toString(),Toast.LENGTH_SHORT).show()
                        try {
                            var userid = p.value.toString()


                            var userview = TextView(activity)
//                            rl.addView(userview)

                            userview.layoutParams.width = 1000
                            userview.layoutParams.height = 1000
//                            useridtoname(userid, i, rl.layoutParams.height - 860,userview)
                            i += 800
                        }catch (ex:Exception){Toast.makeText(activity,ex.message.toString(),Toast.LENGTH_SHORT).show()}

                    }
                }catch (ex:Exception){Toast.makeText(activity,ex.message.toString(),Toast.LENGTH_SHORT).show()}


                }
            })
        }catch (ex:Exception){Toast.makeText(activity,ex.message.toString(),Toast.LENGTH_SHORT).show()}

    }
    fun useridtoname(userid:String,i:Int,j:Int,userview:TextView){
        if(userid!=null || userid!=""|| i!=null || i!=0|| j!=null || j!=0){


            try{
        //   var j=i
        var ref11=FirebaseDatabase.getInstance().reference.child("Names")
            if(ref11!=null)
        ref11.child(userid).addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p01: DataSnapshot) {
                if (p01 != null){
                    try {
                        for (p2: DataSnapshot in p01.children) {
                            if (p2 != null){
                                try {
                                    if (p2.key.toString().equals("Name")) {

                                        try {
                                            //Toast.makeText(this@home_page2,p.value.toString(),Toast.LENGTH_SHORT).show()
                                            //Toast.makeText(this@home_page2,rl.layoutParams.height.toString()+i.toString(),Toast.LENGTH_SHORT).show()
                                            userview.text = p2.value.toString()
                                            userview.visibility = View.VISIBLE
                                            userview.y = j.toFloat() - i.toFloat()
                                            userview.textSize = 16f
                                            userview.setTextColor(Color.parseColor("#000000"))
                                            userview.setTypeface(null, Typeface.BOLD)
                                        } catch (ex: Exception) {
                                            Toast.makeText(activity, ex.message.toString(), Toast.LENGTH_SHORT).show()
                                        }

                                    } else {
                                    }
                                } catch (ex: Exception) {
                                    Toast.makeText(activity, ex.message.toString(), Toast.LENGTH_SHORT).show()
                                }

                        }                        }
                    } catch (ex: Exception) {
                        Toast.makeText(activity, ex.message.toString(), Toast.LENGTH_SHORT).show()
                    }
            }
            }


        })}catch (ex:Exception){Toast.makeText(activity,ex.message.toString(),Toast.LENGTH_SHORT).show()}}

    }
    fun downloadimages(iv:ImageView,iv2:ImageView,size:Int,ones:String){

    }
//    override fun onBackPressed() {
//        //  Toast.makeText(this,"chal peeche",Toast.LENGTH_SHORT).show()
//
//        var int=Intent(Intent.ACTION_MAIN)
//        int.addCategory(Intent.CATEGORY_HOME)
//        int.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(int)
//
////        int.putExtra("EXIT",true)
////       Toast.makeText(this,"chal peeche",Toast.LENGTH_SHORT).show()
////        startActivity(int)
//        // moveTaskToBack(true)
//        activity!!.onBackPressed()
//    }

    override fun onPause() {
        try{
            //   startActivity(Intent(this, homepage2::class.java))
            // Toast.makeText(this, "onPasue", Toast.LENGTH_SHORT).show()
        }catch(ex:Exception){}
        super.onPause()
    }

    override fun onResume() {
//         Toast.makeText(activity,"resume",Toast.LENGTH_SHORT).show()
        super.onResume()
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

         vieww = inflater.inflate(R.layout.tab1, container, false)
//        Toast.makeText(activity, "OncreateView", Toast.LENGTH_SHORT).show()
        vieww.progresssignout.visibility=View.VISIBLE
       vieww. newupload.visibility=View.INVISIBLE

        try {
            vieww.upload.setOnClickListener {
                logout()
            }
            vieww.addphoto.setOnClickListener {

                addphoto11()
            }

            vieww.findfriends.setOnClickListener {
                callfindfriends()
            }
            vieww.friends.setOnClickListener {
                callfriends()
            }
            mUpload = ArrayList()


//            val lm = object : LinearLayoutManager(activity) {
//                override fun requestChildRectangleOnScreen(parent: RecyclerView, child: View, rect: Rect, immediate: Boolean): Boolean {
//                    if ((child as ViewGroup).focusedChild ==mRecyclerView) {
//                        return false
//
//                    }
//                    return super.requestChildRectangleOnScreen(parent, child, rect, immediate)
//
//                }
//            }
            mRecyclerView = vieww.findViewById<RecyclerView>(R.id.myrecycler)
            mRecyclerView.setLayoutManager(LinearLayoutManager(context))

//            mRecyclerView.setHasFixedSize(true)

           storagereff= FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString())
                    .child("Notification")

        }catch (ex:Exception){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()}

        return vieww
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        con=context
    }

    override fun onDestroy() {

        try {
            super.onDestroy()
//            Toast.makeText(activity,"onDestroy",Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
//        Toast.makeText(activity,"onDestroyview",Toast.LENGTH_SHORT).show()


    }
    private fun logout() {
        try {


//            Toast.makeText(activity,"Signing out",Toast.LENGTH_SHORT).show()
//            FirebaseAuth.getInstance().signOut()

//            Handler().postDelayed(Runnable(){
            FirebaseAuth.getInstance().signOut()
            vieww.progresssignout.visibility=View.VISIBLE

            Handler().postDelayed(object :Runnable{
        override fun run() {
            startActivity(Intent(activity,tiredy::class.java))
            activity!!.finish()

        }

    },2000)

//            Toast.makeText(activity,activity!!.moveTaskToBack(true).toString(),Toast.LENGTH_SHORT).show();
//            Toast.makeText(activity,activity!!.finish().toString(),Toast.LENGTH_SHORT).show();
            try {
//                val abc:Fragment=Tab1Fragment()
            Toast.makeText(activity,"Signing out",Toast.LENGTH_SHORT).show()

//                val manager=activity!!.supportFragmentManager
//                val trans=manager.beginTransaction()
//                trans.remove(Tab1Fragment())
//                manager.popBackStack(R.id.tab1,FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//                manager.popBackStack()
//                trans.commit()
            } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()

            }
            onDestroyView()
onDestroy()
//            activity!!.finish()
//
//
//
//            },3000)

////            activity!!.onBackPressed()
//            activity!!.fragmentManager.popBackStack()
//            activity!!.fragmentManager.beginTransaction().commit()
//            activity!!.fragmentManager.beginTransaction().commitAllowingStateLoss()
//            activity!!.fragmentManager.beginTransaction().detach(android.app.Fragment())
//            activity!!.fragmentManager.beginTransaction().remove(android.app.Fragment())
//
//
//            activity!!.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
////            activity!!.fragmentManager.popBackStack(null,activity.fragmentManager.P4

        } catch (e: Exception) {Toast.makeText(activity,e.message,Toast.LENGTH_SHORT).show()

        }
    }

    companion object {
        private val TAB = "Tab1Fragment"
    }

    override fun onStart() {
        super.onStart()
            FirebaseDatabase.getInstance().getReference().child("Names").child(FirebaseAuth.getInstance().uid.toString()).child("Notification").addValueEventListener(
                    object:ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            if(p0.value==null){
                                Toast.makeText(context, "Use the credentials given in the repository", Toast.LENGTH_LONG).show()
                                vieww.progresssignout.visibility=View.INVISIBLE


                            }else{

                                mRecyclerView.setLayoutManager(LinearLayoutManager(context))

//        Toast.makeText(activity,storagereff.toString(),Toast.LENGTH_SHORT).show()
                                try {
                                    val adapterr: FirebaseRecyclerAdapter<Upload, iViewHolder>
                                    val options=FirebaseRecyclerOptions.Builder<Upload>()
                                            .setQuery(storagereff,Upload::class.java)
                                            .build()

//                                    Toast.makeText(context,options.snapshots.size.toString(),Toast.LENGTH_SHORT).show()
//                                    Toast.makeText(context,options.owner.toString(),Toast.LENGTH_SHORT).show()

//            storagereff.key==null
//        Toast.makeText(activity,"ONSTART2",Toast.LENGTH_SHORT).show()

//                                    if(options.snapshots.size==0) {
//                                        Toast.makeText(context, "Use the credentials given in the repository", Toast.LENGTH_LONG).show()
//                                        Toast.makeText(context,"Null is "+options.owner.toString(),Toast.LENGTH_SHORT).show()
//
//                                        vieww.progresssignout.visibility = View.INVISIBLE
//
//                                    }
                                    adapterr = object:FirebaseRecyclerAdapter<Upload,iViewHolder>(options){

                                        override fun onBindViewHolder(holder: iViewHolder, position: Int, model: Upload) {
//                    Toast.makeText(activity,"ONSTART3",Toast.LENGTH_SHORT).show()
//                                Toast.makeText(activity,position.toString(),Toast.LENGTH_SHORT).show()

                                            try {
                                                getRef(position).addListenerForSingleValueEvent(object :ValueEventListener{
                                                    override fun onCancelled(p0: DatabaseError) {
                                                        Toast.makeText(activity,p0.message,Toast.LENGTH_SHORT).show()
                                                    }

                                                    override fun onDataChange(p0: DataSnapshot) {
//                                                        Toast.makeText(context,"P) is ",Toast.LENGTH_SHORT).show()
//                                    if(p0.children.none()|| (!p0.exists())) {
//                                        Toast.makeText(context, "No pics posted by friends , you can post one too", Toast.LENGTH_SHORT).show()
//                                        vieww.progresssignout.visibility=View.INVISIBLE
//
//                                    }
                                                        try {
                                                            var int=0
                                                            for(p1:DataSnapshot in p0.children){
                                                                try {
//                                    Toast.makeText(activity,"p1: "+ p1.value.toString(), Toast.LENGTH_SHORT).show()
                                                                    if(int==1)
                                                                        Glide.with(activity).load(p1.value.toString()).listener(object: RequestListener<Drawable> {
                                                                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                                                                vieww.progresssignout.visibility=View.INVISIBLE

                                                                                return false
                                                                            }

                                                                                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                                                                    vieww.progresssignout.visibility=View.INVISIBLE

//                                                                                    Toast.makeText(activity,e?.message.toString(),Toast.LENGTH_SHORT).show()
                                                                                  return false
                                                                            }

                                                                        }

                                                                        ).into(holder.imageView)

                                                                    int=1
                                                                } catch (e: Exception) {
//                                        Toast.makeText(activity, "p0: " + e.toString(), Toast.LENGTH_SHORT).show()

                                                                }

                                                            }
                                                        } catch (e: Exception) {Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()

                                                        }
                                                    }

                                                })
                                            } catch (e: Exception) {Toast.makeText(activity,e.toString(), Toast.LENGTH_SHORT).show()

                                            }

                                        }

                                        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): iViewHolder {
//                    Toast.makeText(activity,"ONSTART4",Toast.LENGTH_SHORT).show()

                                            val view=LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false)
                                            val viewholder=iViewHolder(view)
                                            return viewholder


                                        }



                                        override fun getItemViewType(position: Int): Int {
//                    Toast.makeText(activity,"getitemviewtype",Toast.LENGTH_SHORT).show()

                                            return super.getItemViewType(position)
                                        }

                                        override fun getItemCount(): Int {
//                    Toast.makeText(activity,"getitemcount",Toast.LENGTH_SHORT).show()

                                            return super.getItemCount()
                                        }
                                    }
                                    adapterr.startListening()
                                    mRecyclerView.adapter=adapterr
                                    adapterr.notifyDataSetChanged()
                                } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()

                                }


                            }
                        }

                    }

            )


    }
    public class iViewHolder:RecyclerView.ViewHolder{
        var imageView:ImageView
        init {

        }
        constructor(itemView: View):super(itemView)
        {

          imageView=itemView.findViewById(R.id.image_view_upload)
        }




    }
}