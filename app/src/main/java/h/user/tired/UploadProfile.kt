package h.user.tired

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tiredy.*
import kotlinx.android.synthetic.main.activity_upload_profile.*

class UploadProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_profile)

        CroperinoConfig("Balli"+".jpg","Pictures/", Environment.getExternalStorageDirectory().path)
        CroperinoFileUtil.setupDirectory(this)
        if(CroperinoFileUtil.verifyStoragePermissions(this))
            prepareChooser()


    }

    private fun prepareChooser() {
        Croperino.prepareChooser(this, "Capture photo...", ContextCompat.getColor(this, android.R.color.background_dark));
    }

    private fun prepareCamera() {
        Croperino.prepareCamera(this);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try{

            if(requestCode==CroperinoConfig.REQUEST_TAKE_PHOTO) {
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this,"1", Toast.LENGTH_SHORT).show()

                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), this, true, 1, 1, R.color.gray, R.color.gray_variant);
                }
            }
            if(requestCode== CroperinoConfig.REQUEST_PICK_FILE){
                try{
                    if (resultCode == Activity.RESULT_OK) {
                        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()

//                yeaa.setImageURI(data!!.data)

                        CroperinoFileUtil.newGalleryFile(data, this)
                        Croperino.runCropImage(CroperinoFileUtil.getTempFile(), this, true, 1, 1, R.color.gray, R.color.gray_variant);
                    }
                }catch (ex:Exception){
                    Toast.makeText(this,ex.toString(), Toast.LENGTH_SHORT).show()}
            }


            if(requestCode== CroperinoConfig.REQUEST_CROP_PHOTO){
                Toast.makeText(this,"3", Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,resultCode.toString() +" "+Activity.RESULT_OK.toString(),Toast.LENGTH_SHORT).show()

                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, Activity.RESULT_OK.toString(), Toast.LENGTH_SHORT).show()

                    var i = Uri.fromFile(CroperinoFileUtil.getTempFile());
                    test1.setImageURI(i);
                }}}catch (ex:Exception){
            Toast.makeText(this,ex.toString(), Toast.LENGTH_SHORT).show()}
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)



        if (requestCode == CroperinoFileUtil.REQUEST_CAMERA) {
            for ( i in 0 until permissions.size) {
                val permission = permissions[i];
                val grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        prepareCamera();
                    }
                }
            }
        } else if (requestCode == CroperinoFileUtil.REQUEST_EXTERNAL_STORAGE) {
            var wasReadGranted = false;
            var wasWriteGranted = false;

            for ( i in 0 until  permissions.size) {
                val permission = permissions[i];
                val grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasReadGranted = true;
                    }
                }
                if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasWriteGranted = true;
                    }
                }
            }

            if (wasReadGranted && wasWriteGranted) {
                prepareChooser();
            }
        }
    }
}
