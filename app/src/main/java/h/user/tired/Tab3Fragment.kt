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
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
    }
   private fun coverphoto(view:View){
        var data=ArrayList<String>()
        FirebaseDatabase.getInstance().getReference("Names")
                .child(FirebaseAuth.getInstance().uid!!)
                .child("Images").addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        data=ArrayList<String>()
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
                            if (p0.value != null) {
                                Glide.with(activity).load(p0.value.toString())
                                        .listener(object : RequestListener<Drawable> {
                                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                                view.profilepicprogressbar.visibility = View.INVISIBLE

                                                return false
                                            }

                                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                                view.profilepicprogressbar.visibility = View.INVISIBLE
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
                        }

                    })

        }catch (e:Exception){
            print("e111"+e.toString())
        }
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
//
            try {
                if(android.os.Build.VERSION.SDK_INT>android.os.Build.VERSION_CODES.LOLLIPOP_MR1)
                    checkPermission()
                try {

                }catch (ex:Exception){                Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//    Toast.makeText(activity,"1",Toast.LENGTH_SHORT).show()
        if(requestCode==121 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null) {

            val ref = FirebaseStorage.getInstance().getReference("Cover pics").child(FirebaseAuth.getInstance().uid!!)
            val bytes = ByteArrayOutputStream()
            ref.putFile(data!!.data).addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
                override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
                    Toast.makeText(activity,"Updation Successful",Toast.LENGTH_SHORT).show()
                }
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
                Croperino.runCropImage(CroperinoFileUtil.getTempFile(), activity, true, 1, 1, R.color.gray, R.color.gray_variant);
            }
        }
        if(requestCode== CroperinoConfig.REQUEST_PICK_FILE){
            if (resultCode == Activity.RESULT_OK) {
                profilepic.setImageURI(data!!.data)
                CroperinoFileUtil.newGalleryFile(data, activity)
                Croperino.runCropImage(CroperinoFileUtil.getTempFile(), activity, true, 1, 1, R.color.gray, R.color.gray_variant);
            }}


        if(requestCode== CroperinoConfig.REQUEST_CROP_PHOTO){
            if (resultCode == Activity.RESULT_OK) {
                val i = Uri.fromFile(CroperinoFileUtil.getTempFile());
                testy.setImageURI(i);
            }}


        try{

            if(requestCode==11 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null) {
                try{
                    val imageuri:Bundle = data!!.extras
                    val imageuri2:Bitmap=imageuri.getParcelable<Bitmap>("data")
                    profilepic.setImageBitmap(imageuri2)

                }catch (ex:Exception){            Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()


                }
            }}catch (ex:Exception){            Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
        }
        if(requestCode==111 &&resultCode== Activity.RESULT_OK&&data!=null &&data.data!=null) {
            val imageuri = data.data
            if(imageuri!=null){

                try{
                }catch (ex:Exception){                Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
                try {
                    val pickedImage = data.getData();
                    // Let's read picked image path using content resolver
                    val filePath = arrayOf( MediaStore.Images.Media.DATA );
                    val cursor = activity!!.getContentResolver().query(pickedImage!!, filePath, null, null, null);
                    cursor!!.moveToFirst();
                    val imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

                    val options =  BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    var bitmap = BitmapFactory.decodeFile(imagePath, options);
                }catch (ex:Exception){ Toast.makeText(activity, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


    companion object {
        private val TAB = "Tab3Fragment"
    }
}