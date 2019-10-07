package h.user.tired

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.firebase.database.DatabaseReference
import android.widget.Toast
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_images.*
import android.support.annotation.NonNull
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.FileDownloadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_home_page2.*
import java.util.ArrayList
import kotlin.math.absoluteValue


class ImagesActivity : AppCompatActivity() {
lateinit var mRecyclerView:RecyclerView
    lateinit var mAdapter: ImageAdapter
    lateinit var mDataBaseReference:DatabaseReference
lateinit var database:FirebaseDatabase
    lateinit var myRef:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        lateinit var mUploads:MutableList<Upload>

        database=FirebaseDatabase.getInstance()
         myRef=database.getReference("Upload")

       mRecyclerView=findViewById(R.id.recycler_view)
        mRecyclerView.setHasFixedSize(false)
        mRecyclerView.layoutManager=LinearLayoutManager(this)
        mDataBaseReference=FirebaseDatabase.getInstance().getReference("uploads")
        mUploads=ArrayList()
        mDataBaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                     val upload:Upload? = postSnapshot.getValue(Upload::class.java)
//                    Upload(upload.toString())

//                    mUploads.add(upload!!)
                }
//                Upload(mUploads.toString())

//                if(Upload().getImageUrl()==null || Upload().getImageUrl()=="")
//                mAdapter = ImageAdapter(this@ImagesActivity, mUploads)
if(fileList().contains("empty.txt")){
}
                mRecyclerView.adapter = mAdapter
               pb1.visibility=View.INVISIBLE
                if(ImageAdapter().t==1){}

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ImagesActivity, databaseError.message, Toast.LENGTH_SHORT).show()
                pb1.visibility=View.INVISIBLE
            }
        })
    }

    override fun onStart() {
//var firebaserecycleradapter:FirebaseRecyclerAdapter<Upload,BlogViewHolder>
//        =FirebaseRecyclerAdapter<Upload,BlogViewHolder>(
//        Upload::class.java,
//            R.layout.image_item,
//            BlogViewHolder::class.java,
//        myRef){
//
//}





        super.onStart()


    }
    public class  BlogViewHolder:RecyclerView.ViewHolder{
    lateinit var mView: View
        constructor(itemView: View):super(itemView){
            mView=itemView
            itemView.setOnClickListener {



        }

       }

        }


    }



