package h.user.tired


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.tab3.*
import kotlinx.android.synthetic.main.tab3.view.*
import android.graphics.Shader.TileMode
import android.R.drawable
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.ActionBar
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.makeramen.roundedimageview.RoundedImageView
import java.io.BufferedInputStream
//import com.theartofdev.edmodo.cropper.CropImage
//import com.theartofdev.edmodo.cropper.CropImageView


//import com.mikelau.croperino.Croperino;
//import com.mikelau.croperino.CroperinoConfig;
//import com.mikelau.croperino.CroperinoFileUtil
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.NullPointerException
import java.net.URL
import java.security.acl.Permission
lateinit var whocalled:Number

class Tab3Fragment : Fragment() {
//lateinit var cropImageView: CropImageView
    lateinit var mCropImageUri:Uri
    lateinit var storagereff: DatabaseReference
    lateinit var mRecyclerView: RecyclerView
    private lateinit var adapter: MyRecyclerViewAdapter

//    public class iViewHolder:RecyclerView.ViewHolder{
//        var imageView:ImageView
//        init {
//
//        }
//        constructor(itemView: View):super(itemView)
//        {
//
//            imageView=itemView.findViewById(R.id.myimages)
//        }
//
//
//
//
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//cropImageView=CropImageView(activity)
        //Toast.makeText(activity, "Oncreate3", Toast.LENGTH_SHORT).show()

    }

    override fun onStart() {
        super.onStart()

//        try {
//            val adapterr: FirebaseRecyclerAdapter<Upload2,iViewHolder>
//            var options= FirebaseRecyclerOptions.Builder<Upload2>()
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
//                        getRef(position).addListenerForSingleValueEvent(object :ValueEventListener{
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
//                                } catch (e: Exception) {Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()
//
//                                }
//                            }
//
//                        })
//                    } catch (e: Exception) {Toast.makeText(activity,e.toString(), Toast.LENGTH_SHORT).show()
//
//                    }
//
//                }
//
//                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):iViewHolder {
////                    Toast.makeText(activity,"ONSTART4",Toast.LENGTH_SHORT).show()
//
//                    val view=LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
//                    val viewholder= iViewHolder(view)
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
//            adapterr.notifyDataSetChanged()
//        } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()
//
//        }
    }
    fun coverphoto(view:View){
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

                        val myrecyler=view.findViewById<RecyclerView>(R.id.myrecycler2)
                        val numberOfColumns = 3
                        myrecyler.setLayoutManager(GridLayoutManager(activity, numberOfColumns))
                        adapter = MyRecyclerViewAdapter(activity, data)
//            adapter.setClickListener(this@Recycler)
                        myrecyler.setAdapter(adapter)
                    }


                })
    }
    fun username(){
        Log.d("abc","ddd")
      var abc=  FirebaseDatabase.getInstance().getReference("Names")
                .child(FirebaseAuth.getInstance().uid.toString()).child("Name").addValueEventListener(object :ValueEventListener{
                  override fun onDataChange(p0: DataSnapshot) {
                      Log.d("abc2222",p0.value.toString());
                      username.setText(p0.value.toString())

                  }

                  override fun onCancelled(p0: DatabaseError) {
                  }


              })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.tab3, container, false)
       // Toast.makeText(activity, "OncreateView3", Toast.LENGTH_SHORT).show()
//        storagereff=FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid!!).child("Images")

//        FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid!!).child("Images").addListenerForSingleValueEvent(object:ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//            for(p1:DataSnapshot in p0.children){
//                if(p1.key!="ICounter") {
//                    Toast.makeText(activity, p1.value.toString(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            }
//
//
//        })


//        mRecyclerView = view.findViewById<RecyclerView>(R.id.myrecycler2)
//        mRecyclerView.setLayoutManager(GridLayoutManager(activity,3))

       coverphoto(view)
        username()

        view.plus.setOnClickListener {
        updateprofile()

        }
        view.profilepicprogressbar.visibility=View.VISIBLE
        try {
            FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString())
                    .child("Profile pic")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            Toast.makeText(activity, "p0 is :" + p0.value.toString(), Toast.LENGTH_SHORT).show()
                            if (p0.value != null) {
                                Glide.with(activity).load(p0.value.toString())
                                        .listener(object : RequestListener<Drawable> {
                                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                                view.profilepicprogressbar.visibility = View.INVISIBLE

                                                return false
                                            }

                                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                                view.profilepicprogressbar.visibility = View.INVISIBLE

//                                Toast.makeText(activity,e?.message.toString(),Toast.LENGTH_LONG).show()
                                                return false;
                                            }

                                        }

                                        )
                                        .apply(RequestOptions.circleCropTransform().skipMemoryCache(true))
                                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                        .apply(RequestOptions.circleCropTransform())
                                        .into(profilepic)
                            }else{
                                                view.profilepicprogressbar.visibility=View.INVISIBLE
                                view.profilepic.setImageResource(R.drawable.person)

                            }
//                view.profilepicprogressbar.visibility=View.INVISIBLE


                        }

                    })

//            FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString()).child("Cover pic").addValueEventListener(object : ValueEventListener {
//                override fun onCancelled(p0: DatabaseError) {
//                }
//
//                override fun onDataChange(p0: DataSnapshot) {
//                    Toast.makeText(activity, "p0 is :" + p0.value.toString(), Toast.LENGTH_SHORT).show()
//                    if (p0.value != null) {
//                        Glide.with(activity).load(p0.value.toString())
//                                .listener(object : RequestListener<Drawable> {
//                                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                                        view.profilepicprogressbar.visibility = View.INVISIBLE
//
//                                        return false
//                                    }
//
//                                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                                        view.profilepicprogressbar.visibility = View.INVISIBLE
//
////                                Toast.makeText(activity,e?.message.toString(),Toast.LENGTH_LONG).show()
//                                        return false;
//                                    }
//
//                                }
//
//                                )
//                                .apply(RequestOptions.circleCropTransform().skipMemoryCache(true))
//                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
//                                .apply(RequestOptions.circleCropTransform())
//                                .into(coverphoto)
//                    }else{
//                                   view.coverprogressbar.visibility=View.INVISIBLE
//                                    view.coverphoto.setBackgroundColor(R.color.gray)
//
//                    }
//                }
//
//            })
        }catch (e:Exception){
            print("e111"+e.toString())
        }
//        val ref=  FirebaseStorage.getInstance().getReference("Cover pics").child(FirebaseAuth.getInstance().uid!!)
//        val ONE_MEGABYTE = (3000000).toLong()
//        ref.getBytes(ONE_MEGABYTE).addOnSuccessListener { ba: ByteArray ->
//            var bmp = BitmapFactory.decodeByteArray(ba, 0, ba.size);
////            var alterheight:Int
////                alterheight = bmp.height * 43 / 100.toInt()
////            if(bmp.width.toInt()<720){
////            coverphoto.scaleType=ScaleType.FIT_XY
////}
//
////            try {
////                coverphoto.setImageBitmap(bmp)
////                val newheight:Int=coverphoto.layoutParams.height*40/100.toInt()
////                Toast.makeText(activity,coverphoto.height.toString()+" "+newheight.toString(),Toast.LENGTH_SHORT).show()
////                val bmp2=Bitmap.createBitmap(bmp, 0, 0, bmp.width, newheight.toInt())
////                coverphoto.setImageBitmap(bmp2)
////            } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()
////
////            }
////            if(bmp.height.toInt()>200) {
//                val bmp2:Bitmap
//                bmp2=Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.width/2.36.toInt())
////                Toast.makeText(activity,bmp2.height.toString(),Toast.LENGTH_SHORT).show()
//                coverphoto.setImageBitmap(bmp2)
//                val lp=RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
//                lp.setMargins(0,0,0,0)
//
//                coverphoto.setLayoutParams(lp)
//            coverprogressbar.visibility=View.INVISIBLE
//
////            try {
////                val bmp0=Bitmap.createBitmap(bmp,0,bmp2.height,bmp.width,20)
////                val bmp3=Bitmap.createScaledBitmap(bmp0,50,50,true)
////                val bmp4=Bitmap.createScaledBitmap(bmp3,100,50,true)
////                testy.setImageBitmap(bmp4)
////            } catch (e: Exception) { Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()
////
////            }
////bmp2.compress(Bitmap.CompressFormat.JPEG,10,ByteArrayOutputStream())
////            testy.setImageBitmap(bmp2)
////            val traget=object :com.squareup.picasso.Target{
////                override fun onBitmapFailed(errorDrawable: Drawable?) {
////                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////                }
////
////                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?)
////                {
////                    view.testy.setImageBitmap(BlurMaskFilter.Blur())
////                    testy.setImageBitmap()
////                }
////
////                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
////                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////                }
////
////
////            }
//
//
//
////            }
////            else
////                coverphoto.setImageBitmap(bmp)
//
////            val bmp2=Bitmap.createBitmap(bmp, 0, 0,bmp.width,alterheight)
////
////                coverphoto.setImageBitmap(bmp)
//
////            Toast.makeText(activity,"Tab3 width= "+tab3.height.toString(),Toast.LENGTH_SHORT).show()
//
//
////        Toast.makeText(activity,bmp.height.toString()+" "+alterheight.toString(),Toast.LENGTH_SHORT).show()
//
////
////        Glide.with(activity)
////                .load(Bitmap.createScaledBitmap(bmp, coverphoto.getWidth(),
////              300, false))
////                .into(coverphoto)
////        pic.setImageBitmap(Bitmap.createScaledBitmap(bmp, pic.getWidth(),
////                pic.getHeight(), false));
//        }.addOnFailureListener {exception ->
//            coverprogressbar.visibility=View.INVISIBLE
//
////            Toast.makeText(activity,exception.toString(),Toast.LENGTH_SHORT).show()
//
//        }



//        FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().uid.toString())
//                .child("Cover pic")
//                .addValueEventListener(object:ValueEventListener{
//                    override fun onCancelled(p0: DatabaseError) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onDataChange(p0: DataSnapshot) {
//                        Toast.makeText(activity,p0.value.toString(),Toast.LENGTH_SHORT).show()
//                        try {
//
//                            Glide.with(activity)
//                                    .load(p0.value.toString())
//                                    .into(testing)
//
////                            testing.setImageResource(R.drawable.b)
//                        } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()
//                        }
//
//                    }
//
//                })



//        val dv = FirebaseDatabase.getInstance().reference.child("test")
////        Toast.makeText(activity, "ggg", Toast.LENGTH_SHORT).show()
//
//        dv.child(FirebaseAuth.getInstance().uid!!).addValueEventListener(object : ValueEventListener {
//
//            override fun onCancelled(databaseError: DatabaseError) {
//
//            }
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                Toast.makeText(activity, dataSnapshot.value!!.toString()+"     EY", Toast.LENGTH_SHORT).show()
//                try {
//                    Toast.makeText(activity, "  EY", Toast.LENGTH_SHORT).show()
//                    Glide.with(activity).load(Uri.parse(dataSnapshot.value.toString())).centerCrop().into(profilepic)
//                    var tempuri = Uri.parse(dataSnapshot.value.toString())
//
//                    var riv = RoundedImageView(activity)
//                    tab3.addView(riv)
////                    riv.layoutParams.width=200
//                   riv.layoutParams=RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
//                    riv.scaleType = ScaleType.CENTER_CROP
//                    riv.cornerRadius = 40.toFloat()
//                    riv.borderWidth = 2.toFloat()
//                    riv.borderColor = Color.DKGRAY
//                    riv.mutateBackground(true)
////                    riv.setImageURI(Uri.parse(dataSnapshot.value.toString()))
////
//                    riv.isOval = true
//                    riv.tileModeX = Shader.TileMode.REPEAT
//                    riv.tileModeY = Shader.TileMode.REPEAT
////                    Glide.with(activity).load(bitmap2).centerCrop().into(riv)
//
//                }catch (ex:Exception){ Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()}
//
//            }
//
////        })
//
//        var database=FirebaseDatabase.getInstance().reference.child("Profile")
//        database.keepSynced(true)
//        database.addValueEventListener(object :ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//            override fun onDataChange(p0: DataSnapshot) {
////                Toast.makeText(activity, "Profile Changed", Toast.LENGTH_SHORT).show()
//
//                var mref=FirebaseStorage.getInstance().getReference("Profile pics").child(FirebaseAuth.getInstance().uid.toString())
//                val ONE_MEGABYTE = (3000000).toLong()
//                mref.getBytes(ONE_MEGABYTE).addOnSuccessListener { ba: ByteArray ->
//                    try {
//                        var bmp = BitmapFactory.decodeByteArray(ba, 0, ba.size);
//                       var newbmp=(Bitmap.createScaledBitmap(bmp, testy.getWidth(),
//                                                            testy.getHeight(), false));
//
//
//                        var stream=ByteArrayOutputStream()
//                        newbmp.compress(Bitmap.CompressFormat.PNG,100,stream)
//
////                            profilepic.setImageBitmap(newbmp)
////                        Glide.with(activity).load(stream.toByteArray())
////                                .apply(RequestOptions.circleCropTransform().skipMemoryCache(true))
////                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
////                                .apply(RequestOptions.circleCropTransform())
////                                .into(testy)
//
////                        Glide.with(activity).load(newbmp).listener(object:RequestListener<Bitmap,GlideDrawable>{
////                            override fun onException(e: java.lang.Exception?, model: Bitmap?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
////                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////                            }
////
////                            override fun onResourceReady(resource: GlideDrawable?, model: Bitmap?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
////
////                            return false
////                            }
////
////                        }).into(testy)
//
//
//
//// Glide.with(context).load(bmp).apply(RequestOptions().transform(RoundedCorners(6)))
////                                .into(testy)
////                                                   testy.setImageBitmap(bmp)
////                        Toast.makeText(activity,bmp.width.toString()+bmp.height.toString(),Toast.LENGTH_SHORT).show()
////                                                    var newbmp=Bitmap.createScaledBitmap(bmp,100,100,true)
////                                                    var convbm=getRoundedRectBitmap(newbmp,100)
////                                                    profilepic.setImageBitmap(convbm)
////                        try {
////                            var riv = RoundedImageView(activity)
////                            tab3.addView(riv)
//////                    riv.layoutParams.width=200
////                            riv.layoutParams.height=200
////                            riv.layoutParams.width=200
//////                    riv.scaleType = ScaleType.CENTER_CROP
////                            riv.cornerRadius = 40.toFloat()
////                            riv.borderWidth = 2.toFloat()
////                            riv.borderColor = Color.DKGRAY
////                            riv.mutateBackground(true)
////                            riv.setImageBitmap(bmp)
////                            riv.isOval = true
////                            riv.tileModeX = Shader.TileMode.REPEAT
////                            riv.tileModeY = Shader.TileMode.REPEAT
////                        }catch (ex:Exception){ Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()}
//
//
//
//
////                                                    profilepic.setImageBitmap(bmp)
//                    }catch (ex:Exception){Toast.makeText(activity,ex.message,Toast.LENGTH_SHORT).show()}}
//
//            }
//
//        })
//        var ref=FirebaseDatabase.getInstance().reference.child("Names").child(FirebaseAuth.getInstance().uid.toString())
//        ref.addValueEventListener(object :ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                for (p11:DataSnapshot in p0.children){
//                if(p11.key.equals("Name")){
//                    Toast.makeText(activity,p11.value.toString(),Toast.LENGTH_SHORT).show()
//                    username.text=p11.value.toString()
//                    username.textSize=70f
//                    username.setTextColor(Color.parseColor("#000000"))
//                    username.setTypeface(null, Typeface.BOLD)
//                    break
//                }
//
//                }
//
////                if(uid!!.equals(p0.toString())){
////                    Toast.makeText(activity,p0.toString(),Toast.LENGTH_SHORT).show()
////
////                }
//
//            }
//
//        })
        return view
    }

    private fun updatecoverphoto() {
        try {
            startActivity(Intent(activity,UploadProf::class.java))

            whocalled=2
        } catch (e: Exception) {Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private  fun  getRoundedRectBitmap(newbmp: Bitmap?, i: Int): Bitmap {
    var result:Bitmap=newbmp!!
        try{
            result= Bitmap.createBitmap(200,200,Bitmap.Config.ARGB_8888)
            var canvas=Canvas(result)
            var colorr=0xff424242
            var rect=Rect(0,0,200,200)
            val paint=Paint()
            paint.isAntiAlias=true
            canvas.drawARGB(0,0,0,0)
            paint.color=colorr.toInt()
            canvas.drawCircle(50f,50f,50f,paint)
            paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
            canvas.drawBitmap(newbmp,rect,rect,paint)

        }catch (ex:NullPointerException){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()}catch (o:OutOfMemoryError){Toast.makeText(activity,o.toString(),Toast.LENGTH_SHORT).show()}

        return result
    }

    private fun prepareChooser() {
        Croperino.prepareChooser(activity, "Capture photo...", ContextCompat.getColor(context!!, android.R.color.background_dark));
    }

    private fun prepareCamera() {
        Croperino.prepareCamera(activity);
    }

    fun updateprofile(){

        try {
//            UploadProf()
            startActivity(Intent(activity,UploadProf::class.java))
            whocalled=1
//            CroperinoConfig("IMG_"+System.currentTimeMillis()+".jpg","Pictures/",Environment.getExternalStorageDirectory().path)
//            CroperinoFileUtil.setupDirectory(activity)
//            if(CroperinoFileUtil.verifyStoragePermissions(activity))
//                prepareChooser()
//            a().picture()
//            CropImage.activity()
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .start(a());
//
            try {
                if(android.os.Build.VERSION.SDK_INT>android.os.Build.VERSION_CODES.LOLLIPOP_MR1)
                    checkPermission()
                try {
//                    var cropImageView: CropImageView
//
//                    cropImageView = activity!!.findViewById(R.id.crop_image_view)
//                    cropImageView.setImageResource(resources, R.drawable.josh_hood)
//                    //		cropImageView.setCornerDrawable(Color.rgb(255, 200, 0), 100, 100);
////		cropImageView.setCropAreaDrawable(Color.LTGRAY, 150, Color.CYAN, 200, 8);
//
//                    // OPTIONAL - keep crop square
////		cropImageView.setKeepSquare(true);
//
//                    var bitm = cropImageView.crop(activity)
//                    var im2=ImageView(activity)
//                    tab3.addView(im2)
//                    im2.setImageBitmap(bitm)
//                    im2.x=200f
//                    im2.y=200f
//                    im2.layoutParams.height=200
//                    im2.layoutParams.width=200


                }catch (ex:Exception){                Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
//                CropImage.startPickImageActivity(context!!,this)



//                var int = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                int.setType("image/*")
//                startActivityForResult(int, 111)
            }catch (ex:Exception){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show() }


        }catch(ex:Exception){ Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
        }
    }




    @TargetApi(23)
    private fun checkPermission() {
        try{
            var perm = activity!!.requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            try{
            if (perm.equals(PermissionChecker.PERMISSION_DENIED)) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)

            }}catch (ex:Exception){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()}

        }catch (ex:Exception){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()}
    }

    public fun GCD(a:Int ,b:Int):Int{
        if(b==0)
            return a
        else
            GCD(b,a%b)
        return 1

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        try{
    if(requestCode==1){

        if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_DENIED){
            Toast.makeText(activity,"Permission Denied",Toast.LENGTH_SHORT).show()

        }

    }


        if (requestCode == CroperinoFileUtil.REQUEST_CAMERA) {
            for ( i in 0 until permissions.size) {
                val permission = permissions[i]
                val grantResult = grantResults[i]

                if (permission.equals(Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        prepareCamera()
                    }
                }
            }
        } else if (requestCode == CroperinoFileUtil.REQUEST_EXTERNAL_STORAGE) {
           var wasReadGranted = false
            var wasWriteGranted = false

            for ( i in 0 until  permissions.size) {
                val permission = permissions[i]
                val grantResult = grantResults[i]

                if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasReadGranted = true
                    }
                }
                if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasWriteGranted = true
                    }
                }
            }

            if (wasReadGranted && wasWriteGranted) {
                prepareChooser();
            }
        }













        }catch (ex:Exception){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show() }

    }

//    private fun startCropImageActivity(imageUri: Uri?) {
//        CropImage.activity(imageUri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMultiTouchEnabled(true)
//                .start(context!!,this)

//    @SuppressLint("NewApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//    Toast.makeText(activity,"1",Toast.LENGTH_SHORT).show()
if(requestCode==121 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null) {

//    try {
//        val options = BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        val bo = BitmapFactory.decodeFile(File(data!!.data.path).toString(), options);
//
//        Toast.makeText(activity, bo.width.toString() + " " + bo.height.toString(), Toast.LENGTH_SHORT).show()
//    } catch (e: Exception) {
//        Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()
//
//    }
    val ref = FirebaseStorage.getInstance().getReference("Cover pics").child(FirebaseAuth.getInstance().uid!!)
    val bytes = ByteArrayOutputStream()

//    croppedImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//    val data = bytes.toByteArray()
    ref.putFile(data!!.data).addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
        override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
Toast.makeText(activity,"Updation Successful",Toast.LENGTH_SHORT).show()

//            testing.setImageURI(data!!.data)

        }
        //data!!.data.

    }).addOnProgressListener {
        object:OnProgressListener<UploadTask.TaskSnapshot>{
            override fun onProgress(p0: UploadTask.TaskSnapshot?) {
                Toast.makeText(activity,p0.toString(),Toast.LENGTH_SHORT).show()

            }
        }

    }
}
     if(requestCode==CroperinoConfig.REQUEST_TAKE_PHOTO) {
         if (resultCode == Activity.RESULT_OK) {
//             Toast.makeText(activity,"1",Toast.LENGTH_SHORT).show()

             Croperino.runCropImage(CroperinoFileUtil.getTempFile(), activity, true, 1, 1, R.color.gray, R.color.gray_variant);
         }
     }
            if(requestCode== CroperinoConfig.REQUEST_PICK_FILE){
                if (resultCode == Activity.RESULT_OK) {
//                    Toast.makeText(activity,"2",Toast.LENGTH_SHORT).show()
                    profilepic.setImageURI(data!!.data)
                    CroperinoFileUtil.newGalleryFile(data, activity)
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), activity, true, 1, 1, R.color.gray, R.color.gray_variant);
                }}


            if(requestCode== CroperinoConfig.REQUEST_CROP_PHOTO){
//                Toast.makeText(activity,"3",Toast.LENGTH_SHORT).show()

                if (resultCode == Activity.RESULT_OK) {
                    val i = Uri.fromFile(CroperinoFileUtil.getTempFile());
                    testy.setImageURI(i);
                }}




















//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            var cropImageUri=UR
//            val imageUri = CropImage.getPickImageResultUri(context!!, data)
//            // For API >= 23 we need to check specifically that we have permissions to read external storage.
//            if (CropImage.isReadExternalStoragePermissionsRequired(context!!, imageUri)) {
//                // request permissions and handle the result in onRequestPermissionsResult()
//                cropImageUri = imageUri
//                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
//            } else {
//                // no permissions required or already grunted, can start crop image activity
//                    startCropImageActivity()
//            }
//        }

//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            val result = CropImage.getActivityResult(data)
//            if (resultCode == Activity.RESULT_OK) {
//                imageView.setImageURI(result.uri)
//                Toast.makeText(this, "Image updated successfully..!!", Toast.LENGTH_LONG).show()
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG).show()
//            }
//        }

try{

        if(requestCode==11 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null) {
try{
//            Toast.makeText(activity,"2",Toast.LENGTH_SHORT).show()


            var imageuri:Bundle = data!!.extras
            var imageuri2:Bitmap=imageuri.getParcelable<Bitmap>("data")
//imageuri2.height=1500
//    imageuri2.width=1500
            profilepic.setImageBitmap(imageuri2)
//            var bytes=ByteArrayOutputStream()
//            imageuri2.compress(Bitmap.CompressFormat.JPEG,100,bytes)
//val data=bytes.toByteArray()
//    var ref2=FirebaseStorage.getInstance().getReference("Profilepic").child(FirebaseAuth.getInstance().uid.toString())
////
//    ref2.putBytes(data).addOnSuccessListener { taskSnapshot ->
//     Toast.makeText(activity,"Profile Uploaded",Toast.LENGTH_SHORT).show()
//    }

}catch (ex:Exception){            Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()


}
//            imageuri2.compress(Bitmap.CompressFormat.JPEG,100,bytes)
//            imageuri2.recycle()

//            val path=MediaStore.Images.Media.insertImage(activity!!.contentResolver,imageuri2,"Title",null)

//

//                Toast.makeText(activity, "Profile Updated", Toast.LENGTH_SHORT).show()
//
//                try{
//                    val storageRef = FirebaseStorage.getInstance().reference
//
//                    var islandRef = storageRef.child("Profilepic/"+FirebaseAuth.getInstance().uid.toString())
//
//                    //  Toast.makeText(this@home_page2,"ones are"+ ones, Toast.LENGTH_SHORT).show()
//
//                    //islandRef.downloadUrl
//                    //  Toast.makeText(this@home_page2,islandRef.getDownloadUrl().toString(),Toast.LENGTH_SHORT).show()
//                    // Picasso.with(this@home_page2).load(islandRef).into(pic)
//
//
//                    var ONE_MEGABYTE = (1024 * 1024).toLong()
//                    islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener { ba: ByteArray ->
//                        var bmp = BitmapFactory.decodeByteArray(ba, 0, ba.size);
//                        // Picasso.with(this@home_page2).load(islandRef).into(pic)
//                        // pic.setImageBitmap(Bitmap.createScaledBitmap(bmp, pic.getWidth(),
//                        //       pic.getHeight(), false));
//                        val riv = RoundedImageView(activity)
//                        tab3.addView(riv)
//                        riv.x=200f
//                        riv.y=200f
////                        Toast.makeText(activity,riv.minimumHeight.toString()+riv.minimumWidth,Toast.LENGTH_SHORT).show()
////                        Toast.makeText(activity,riv.maxHeight.toString()+riv.maxWidth,Toast.LENGTH_SHORT).show()
//
//                        riv.layoutParams.width=300
//                        riv.layoutParams.height=300
//
////                    riv.scaleType = ScaleType.CENTER_CROP
//                        riv.cornerRadius = 10.toFloat()
//                        riv.borderWidth = 2.toFloat()
//                        riv.borderColor = Color.DKGRAY
//                        riv.mutateBackground(true)
//                        riv.setImageBitmap(bmp)
//                        riv.isOval = true
//                        riv.tileModeX = Shader.TileMode.REPEAT
//                        riv.tileModeY = Shader.TileMode.REPEAT
//                        bmp=null
                  //  }
//
//
//
//
////                Toast.makeText(activity,"ia ",Toast.LENGTH_SHORT).show()
////
////            var imageuri:Bundle = data!!.extras
////            var imageuri2:Bitmap=imageuri.getParcelable<Bitmap>("data")
//
//                }catch (ex:Exception){            Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
//                }

           // }










        }}catch (ex:Exception){            Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
//            {
//                val result = CropImage.getActivityResult(data);
//                if (resultCode === RESULT_OK) {
//                    val resultUri = result.uri
//                }
//            }
            if(requestCode==111 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null) {
            var imageuri = data.data
            if(imageuri!=null){

try{
//    Toast.makeText(activity,"1",Toast.LENGTH_SHORT).show()
//
//                val pickedImage = data.getData();
//                    var uri:Uri=data.data
//                // Let's read picked image path using content resolver
//                val filePath = arrayOf( MediaStore.Images.Media.DATA );
//                val cursor = activity!!.getContentResolver().query(pickedImage, filePath, null, null, null);
//                cursor.moveToFirst();
//                val imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
//    cursor.close()
//
//    val options =  BitmapFactory.Options();
//                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//                val bitmap = BitmapFactory.decodeFile(imagePath, options);
//    var b:Bitmap= Bitmap.createBitmap(bitmap,0,0,1000,1000)
//    crop_image_view.layoutParams.height=bitmap.height
//    crop_image_view.layoutParams.width=bitmap.width
//    Toast.makeText(activity,bitmap.height.toString()+" "+bitmap.width.toString(),Toast.LENGTH_SHORT).show()
//    Toast.makeText(activity,crop_image_view.height.toString()+" "+crop_image_view.width.toString(),Toast.LENGTH_SHORT).show()
//
//    cropImageView = activity!!.findViewById(R.id.crop_image_view)
//    cropImageView.setImageBitmap(bitmap)
//    Toast.makeText(activity,cropImageView.height.toString()+" "+cropImageView.width.toString(),Toast.LENGTH_SHORT).show()

    //		cropImageView.setCornerDrawable(Color.rgb(255, 200, 0), 100, 100);
//		cropImageView.setCropAreaDrawable(Color.LTGRAY, 150, Color.CYAN, 200, 8);

    // OPTIONAL - keep crop square
//		cropImageView.setKeepSquare(true);






}catch (ex:Exception){                Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
}
//try{
//                CropImage.activity(imageuri)
////                        .setGuidelines(CropImageView.Guidelines.ON)
////                        .setAspectRatio(1,1)
//                        .start(getContext()!!,this);
//
//
//
//
//}catch (ex:Exception){Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
//}
                try {
//                    var xx=0
//                    var yy=0
//                    var apiversion=android.os.Build.VERSION.SDK_INT
//
//                    if(apiversion>=android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
//                        var display=activity!!.windowManager.defaultDisplay
//                        var size=Point()
//                        display.getSize(size)
//                        xx=size.x
//                        yy=size.y
//                    }else{
//                        var display=activity!!.windowManager.defaultDisplay
//                        xx=display.width
//                        yy=display.height
//                    }
//                        val gcd=GCD(xx,yy)
//                        val aspectx=xx/gcd
//                        val aspecty=yy/gcd
//                        val outputx=xx-aspectx*30
//                        val outputy=yy-aspecty*30
//                    Toast.makeText(activity,aspectx.toString()+"  "+aspecty.toString(),Toast.LENGTH_SHORT).show()
//                    Toast.makeText(activity,outputx.toString()+"  "+outputy.toString(),Toast.LENGTH_SHORT).show()
//                    var options=BitmapFactory.Options()
//                    options.inJustDecodeBounds=true
//                    BitmapFactory.decodeFile(File(imageuri.getPath()).absolutePath,options)
////                    Toast.makeText(activity,options.outHeight.toString()+options.outWidth.toString(),Toast.LENGTH_SHORT).show()
                     var pickedImage = data.getData();
        // Let's read picked image path using content resolver
                     var filePath = arrayOf( MediaStore.Images.Media.DATA );
        var cursor = activity!!.getContentResolver().query(pickedImage, filePath, null, null, null);
        cursor.moveToFirst();
                    var imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

       var options =  BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        var bitmap = BitmapFactory.decodeFile(imagePath, options);
//Toast.makeText(activity,bitmap.height.toString()+bitmap.width.toString(),Toast.LENGTH_SHORT).show()
//        //profilepic.setImageBitmap(bitmap)
//
//
//
//
//
//
//
//
//
//                        //853 neither height nor width,
//
//                    val cropIntent = Intent("com.android.camera.action.CROP")
//                    //indicate image type and Uri
//                    cropIntent.setDataAndType(imageuri, "image/*")
//                    //set crop properties
//                    cropIntent.putExtra("crop", "false")
//                    //indicate aspect of desired crop
//                    cropIntent.putExtra("aspectX", bitmap.width)
//                    cropIntent.putExtra("aspectY", bitmap.height)
//                    //indicate output X and Y
//                    cropIntent.putExtra("outputX", 0)
//
//                    cropIntent.putExtra("outputY", 0)
//                    //retrieve data on return
//                    cropIntent.putExtra("return-data", true)
//                    Toast.makeText(activity, data.data.toString(), Toast.LENGTH_SHORT).show()
//                    //start the activity - we handle returning in onActivityResult
//                    startActivityForResult(cropIntent, 11)
                }catch (ex:Exception){ Toast.makeText(activity, ex.toString(), Toast.LENGTH_SHORT).show()
                }




//                    var transformation=RoundedTransformationBuilder()
//                            .borderColor(Color.BLUE)
//                            .borderWidthDp(3f)
//                            .cornerRadiusDp(30f)
//                            .oval(false)
//                            .build();



//                    val riv = RoundedImageView(activity)
//                    tab3.addView(riv)
//                    riv.layoutParams.width=200
//                    riv.layoutParams.height=200
//
////                    riv.scaleType = ScaleType.CENTER_CROP
//                    riv.cornerRadius = 10.toFloat()
//                    riv.borderWidth = 2.toFloat()
//                    riv.borderColor = Color.DKGRAY
//                    riv.mutateBackground(true)
//                    riv.setImageURI(imageuri)
//                    riv.isOval = true
//                    riv.tileModeX = Shader.TileMode.REPEAT
//                    riv.tileModeY = Shader.TileMode.REPEAT
//                    Picasso.with(activity).load(imageuri)
//                            .fit()
//                            .transform(transformation)
//                            .into(profilepic)
              //  }



            }

        }
    }


    companion object {
        private val TAB = "Tab3Fragment"
    }
}