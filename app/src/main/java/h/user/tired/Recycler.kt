package h.user.tired

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.cardview.view.*
import android.R.attr.data



class Recycler : AppCompatActivity() {
private lateinit var adapter: MyRecyclerViewAdapter
    private lateinit var storagereff: DatabaseReference
   private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        try {
//            storagereff= FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid!!).child("Images")
//            mRecyclerView = findViewById<RecyclerView>(R.id.myrecycler3)
//            mRecyclerView.setLayoutManager(LinearLayoutManager(this))

//            val data = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48")


           val data=ArrayList<String>()
            FirebaseDatabase.getInstance().getReference("Names")
                   .child(FirebaseAuth.getInstance().uid!!)
                   .child("Images").addListenerForSingleValueEvent(object:ValueEventListener{
                       override fun onCancelled(p0: DatabaseError) {
                       }

                       override fun onDataChange(p0: DataSnapshot) {
                           for(p1:DataSnapshot in p0.children){
                               if(p1.key!="ICounter"){
                                data.add(p1.value.toString())

                               }

                           }

                           val myrecyler=findViewById<RecyclerView>(R.id.myrecycler3)
                           val numberOfColumns = 3
                           myrecyler.setLayoutManager(GridLayoutManager(this@Recycler, numberOfColumns))
                           adapter = MyRecyclerViewAdapter(this@Recycler, data)
//            adapter.setClickListener(this@Recycler)
                           myrecyler.setAdapter(adapter)
                       }


                   })



        } catch (e: Exception) {Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
        }

    }


//    override fun onStart() {
//        super.onStart()
//
//        try {
//            val adapterr: FirebaseRecyclerAdapter<Upload2, iViewHolder>
//            val options= FirebaseRecyclerOptions.Builder<Upload2>()
//                    .setQuery(storagereff,Upload2::class.java)
//                    .build()
////        Toast.makeText(activity,"ONSTART2",Toast.LENGTH_SHORT).show()
//            adapterr = object: FirebaseRecyclerAdapter<Upload2, iViewHolder>(options){
//
//                override fun onBindViewHolder(holder: iViewHolder, position: Int, model: Upload2) {
////                    Toast.makeText(activity,"ONSTART3",Toast.LENGTH_SHORT).show()
////                                Toast.makeText(activity,position.toString(),Toast.LENGTH_SHORT).show()
//
//                    try {
//                        getRef(position).addListenerForSingleValueEvent(object : ValueEventListener {
//                            override fun onCancelled(p0: DatabaseError) {
////                                Toast.makeText(activity,p0.message,Toast.LENGTH_SHORT).show()
//                            }
//
//                            override fun onDataChange(p0: DataSnapshot) {
//
//                                try {
////                                    for(p1:DataSnapshot in p0.children){
////                                        try {
//////                                    Toast.makeText(activity,"p1: "+ p1.value.toString(), Toast.LENGTH_SHORT).show()
////                                            if(p1.key!="ICounter")
////                                            Glide.with(activity).load(p1.value.toString()).into(holder.imageView)
////                                        } catch (e: Exception) {
//////                                        Toast.makeText(activity, "p0: " + e.toString(), Toast.LENGTH_SHORT).show()
////
////                                        }
////
////                                    }
//                                } catch (e: Exception) {
//                                    Toast.makeText(this@Recycler, e.toString(), Toast.LENGTH_SHORT).show()
//
//                                }
//                            }
//
//                        })
//                    } catch (e: Exception) {
//                        Toast.makeText(this@Recycler,e.toString(), Toast.LENGTH_SHORT).show()
//
//                    }
//
//                }
//
//                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): iViewHolder {
////                    Toast.makeText(activity,"ONSTART4",Toast.LENGTH_SHORT).show()
//
//                    val view= LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
//                    val viewholder= iViewHolder(view,this@Recycler)
//                    return viewholder
//
//                }
//
//
//
//                override fun getItemViewType(position: Int): Int {
////                    Toast.makeText(activity,"getitemviewtype",Toast.LENGTH_SHORT).show()
//
//                    return super.getItemViewType(position)
//                }
//
//                override fun getItemCount(): Int {
////                    Toast.makeText(activity,"getitemcount",Toast.LENGTH_SHORT).show()
//
//                    return super.getItemCount()
//                }
//            }
//            adapterr.startListening()
//            mRecyclerView.adapter=adapterr
////            adapterr.notifyDataSetChanged()
//        } catch (e: Exception) {
//            Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
//
//        }
//
//
//    }

//     class iViewHolder:RecyclerView.ViewHolder{
//       private lateinit var imageView: ImageView
//        init {
//
//        }
//        constructor(itemView: View,context: Context):super(itemView)
//        {
//
//            try {
//                imageView= itemView.findViewById(R.id.myimages)
//            } catch (e: Exception) {Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//    }

}
