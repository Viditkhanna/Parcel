package h.user.tired;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.media.ExifInterface;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

//import de.keyboardsurfer.android.widget.crouton.Crouton;
//import de.keyboardsurfer.android.widget.crouton.Style;

public class CropImage extends MonitoredActivity {

    final int IMAGE_MAX_SIZE = 1024;

    private static final String TAG = "CropImage";
    public static final String IMAGE_PATH = "image-path";
    public static final String SCALE = "scale";
    public static final String ORIENTATION_IN_DEGREES = "orientation_in_degrees";
    public static final String ASPECT_X = "aspectX";
    public static final String ASPECT_Y = "aspectY";
    public static final String OUTPUT_X = "outputX";
    public static final String OUTPUT_Y = "outputY";
    public static final String SCALE_UP_IF_NEEDED = "scaleUpIfNeeded";
    public static final String CIRCLE_CROP = "circleCrop";
    public static final String RETURN_DATA = "return-data";
    public static final String RETURN_DATA_AS_BITMAP = "data";
    public static final String ACTION_INLINE_DATA = "inline-data";
    public static final int NO_STORAGE_ERROR = -1;
    public static final int CANNOT_STAT_ERROR = -2;

    private boolean mDoFaceDetection = true;
    private boolean mCircleCrop = false;
    private boolean mScaleUp = true;

    public static Uri mSaveUri = null;
    private Bitmap.CompressFormat mOutputFormat = Bitmap.CompressFormat.JPEG;
    private final Handler mHandler = new Handler();
    private final BitmapManager.ThreadSet mDecodingThreads = new BitmapManager.ThreadSet();

    private int mAspectX, mAspectY, mOutputX, mOutputY;
    private boolean mScale;
    private CropImageView mImageView;
    private ContentResolver mContentResolver;
    private Bitmap mBitmap;
    private String mImagePath;

    boolean mWaitingToPick, mSaving;
    HighlightView mCrop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ImageView iv=findViewById(R.id.save);
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//           finish();
//                //     startActivity(new Intent(getApplicationContext(),Tabbed.class).putExtra("last","LAST"));
//            }
//        });

try {
    mContentResolver = getContentResolver();
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.cropimage);

    mImageView = findViewById(R.id.image);
    showStorageToast(this);

    Intent intent = getIntent();
    Bundle extras = intent.getExtras();
    if (extras != null) {
        if (extras.getString(CIRCLE_CROP) != null) {
            mImageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            mCircleCrop = true;
            mAspectX = 1;
            mAspectY = 1;
        }

        mImagePath = extras.getString(IMAGE_PATH);
        mSaveUri = getImageUri(mImagePath);
        mBitmap = getBitmap(mImagePath);

        if (extras.containsKey(ASPECT_X) && extras.get(ASPECT_X) instanceof Integer) {
            mAspectX = extras.getInt(ASPECT_X);
        } else {
            throw new IllegalArgumentException("aspect_x must be integer");
        }
        if (extras.containsKey(ASPECT_Y) && extras.get(ASPECT_Y) instanceof Integer) {
            mAspectY = extras.getInt(ASPECT_Y);
        } else {
            throw new IllegalArgumentException("aspect_y must be integer");
        }
        mOutputX = extras.getInt(OUTPUT_X);
        mOutputY = extras.getInt(OUTPUT_Y);
        mScale = extras.getBoolean(SCALE, true);
        mScaleUp = extras.getBoolean(SCALE_UP_IF_NEEDED, true);
    }

    if (mBitmap == null) {
        finish();
        return;
    }

    assert extras != null;
    if (extras.getInt("color") != 0) {
        findViewById(R.id.rl_main).setBackgroundColor(extras.getInt("color"));
    }

    if (extras.getInt("bgColor") != 0) {
        mImageView.setBackgroundColor(extras.getInt("bgColor"));
    }

    // Make UI fullscreen.
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    findViewById(R.id.discard).setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            });

    findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            try {
                onSaveClicked();
            } catch (Exception e) {
              Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();

                //  finish();
            }
        }
    });

    findViewById(R.id.rotateLeft).setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            mBitmap = CropUtil.rotateImage(mBitmap, -90);
            RotateBitmap rotateBitmap = new RotateBitmap(mBitmap);
            mImageView.setImageRotateBitmapResetBase(rotateBitmap, true);
            mRunFaceDetection.run();
        }
    });

    findViewById(R.id.rotateRight).setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            mBitmap = CropUtil.rotateImage(mBitmap, 90);
            RotateBitmap rotateBitmap = new RotateBitmap(mBitmap);
            mImageView.setImageRotateBitmapResetBase(rotateBitmap, true);
            mRunFaceDetection.run();
        }
    });

    //fix image rotated
    rotateImageIfNecessary();

    startFaceDetection();
}catch (Exception ex){
    Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show();}
    }


    private Uri getImageUri(String path) {
        return Uri.fromFile(new File(path));
    }

    private Bitmap getBitmap(String path) {
        Uri uri = getImageUri(path);
        InputStream in = null;
        try {
            in = mContentResolver.openInputStream(uri);

            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(in, null, o);
            in.close();

            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            in = mContentResolver.openInputStream(uri);
            Bitmap b = BitmapFactory.decodeStream(in, null, o2);
            in.close();

            return b;
        } catch (FileNotFoundException e) {
            Log.e(TAG, "file " + path + " not found");
        } catch (IOException e) {
            Log.e(TAG, "file " + path + " not found");
        }
        return null;
    }

    private void startFaceDetection() {
        if (isFinishing()) {
            return;
        }

        mImageView.setImageBitmapResetBase(mBitmap, true);
        CropUtil.startBackgroundJob(this, null, "Please wait\u2026", new Runnable() {
            public void run() {
                final CountDownLatch latch = new CountDownLatch(1);
                final Bitmap b = mBitmap;
                mHandler.post(new Runnable() {
                    public void run() {
                        if (b != mBitmap && b != null) {
                            mImageView.setImageBitmapResetBase(b, true);
                            mBitmap.recycle();
                            mBitmap = b;
                        }

                        if (mImageView.getScale() == 1F) {
                            mImageView.center(true, true);
                        }

                        latch.countDown();
                    }
                });

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mRunFaceDetection.run();
            }
        }, mHandler);
    }

    private void onSaveClicked() throws Exception {
        ProgressBar progressbar=findViewById(R.id.progreesbarprofile);
        Toast.makeText(this,"Updating Profile",Toast.LENGTH_SHORT).show();
        progressbar.setVisibility(View.VISIBLE);
        if (mSaving) {
//            Toast.makeText(getApplicationContext(),"111",Toast.LENGTH_SHORT).show();

            return;
        }
            if (mCrop == null) {
//                Toast.makeText(getApplicationContext(),"222",Toast.LENGTH_SHORT).show();

                return;
        }
//        Toast.makeText(getApplicationContext(),"333",Toast.LENGTH_SHORT).show();

        mSaving = true;
        Rect r = mCrop.getCropRect();

        int width = r.width();
        int height = r.height();

        Bitmap croppedImage;
        try {

            croppedImage = Bitmap.createBitmap(width, height,
                    mCircleCrop ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } catch (Exception e) {
            throw e;
        }

        if (croppedImage == null) {
//            Toast.makeText(getApplicationContext(),"444",Toast.LENGTH_SHORT).show();

            return;
        }

        {
//            Toast.makeText(getApplicationContext(),"555",Toast.LENGTH_SHORT).show();

            Canvas canvas = new Canvas(croppedImage);
            Rect dstRect = new Rect(0, 0, width, height);
            canvas.drawBitmap(mBitmap, r, dstRect, null);
        }

        if (mCircleCrop) {
//            Toast.makeText(getApplicationContext(),"666",Toast.LENGTH_SHORT).show();

            Canvas c = new Canvas(croppedImage);
            Path p = new Path();
            p.addCircle(width / 2F, height / 2F, width / 2F,
                    Path.Direction.CW);
            c.clipPath(p, Region.Op.DIFFERENCE);
            c.drawColor(0x00000000, PorterDuff.Mode.CLEAR);
        }

        if (mOutputX != 0 && mOutputY != 0) {
//            Toast.makeText(getApplicationContext(),"777",Toast.LENGTH_SHORT).show();

            if (mScale) {
//                Toast.makeText(getApplicationContext(),"888",Toast.LENGTH_SHORT).show();

                Bitmap old = croppedImage;
                croppedImage = CropUtil.transform(new Matrix(),
                        croppedImage, mOutputX, mOutputY, mScaleUp);
                if (old != croppedImage) {
//                    Toast.makeText(getApplicationContext(),"999",Toast.LENGTH_SHORT).show();

                    old.recycle();
                }
            } else {
//                Toast.makeText(getApplicationContext(),"9991",Toast.LENGTH_SHORT).show();

                Bitmap b = Bitmap.createBitmap(mOutputX, mOutputY,
                        Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(b);

                Rect srcRect = mCrop.getCropRect();
                Rect dstRect = new Rect(0, 0, mOutputX, mOutputY);

                int dx = (srcRect.width() - dstRect.width()) / 2;
                int dy = (srcRect.height() - dstRect.height()) / 2;

                srcRect.inset(Math.max(0, dx), Math.max(0, dy));
                dstRect.inset(Math.max(0, -dx), Math.max(0, -dy));
                canvas.drawBitmap(mBitmap, srcRect, dstRect, null);
                croppedImage.recycle();
                croppedImage = b;
            }
        }

        Bundle myExtras = getIntent().getExtras();
        if (myExtras != null && (myExtras.getParcelable("data") != null || myExtras.getBoolean(RETURN_DATA))) {
//            Toast.makeText(getApplicationContext(),"99911",Toast.LENGTH_SHORT).show();

            Bundle extras = new Bundle();
            extras.putParcelable(RETURN_DATA_AS_BITMAP, croppedImage);
            setResult(RESULT_OK, (new Intent()).setAction(ACTION_INLINE_DATA).putExtras(extras));
            finish();
        } else {
//            Toast.makeText(getApplicationContext(),"999111",Toast.LENGTH_SHORT).show();

            final Bitmap b = croppedImage;




//            ByteArrayOutputStream bytes=new ByteArrayOutputStream();
//            croppedImage.compress(Bitmap.CompressFormat.JPEG,100,bytes);
//            String path= MediaStore.Images.Media.insertImage(this.getContentResolver(),croppedImage,"",null);
////            ImageView pp=findViewById(R.id.uripic);
//
////            pp.setImageURI(Uri.parse(path));
//final DatabaseReference database=FirebaseDatabase.getInstance().getReference();
//database.child("test").child(FirebaseAuth.getInstance().getUid()).setValue(Uri.parse(path).toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//    @Override
//    public void onSuccess(Void aVoid) {
//                                Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
////                                DatabaseReference dv=FirebaseDatabase.getInstance().getReference().child("test").child(FirebaseAuth.getInstance().getUid());
////                                dv.addValueEventListener(new ValueEventListener() {
////                                    @Override
////                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                                        Toast.makeText(getApplicationContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();
////                                        ImageView pp=findViewById(R.id.uripic);
////
////                                        pp.setImageURI(Uri.parse(dataSnapshot.getValue().toString()));
////                                    }
////
////                                    @Override
////                                    public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                                    }
////                                });
//    }
//}).addOnFailureListener(new OnFailureListener() {
//    @Override
//    public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
//
//    }
//});

if(Tab3FragmentKt.getWhocalled().intValue()==1){


    try{
        StorageReference ref=FirebaseStorage.getInstance().getReference("Profile pics").child(FirebaseAuth.getInstance().getUid());
        ByteArrayOutputStream bytes= new ByteArrayOutputStream();

        croppedImage.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        final byte[] data= bytes.toByteArray();
        ref.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                try{
                    FirebaseStorage.getInstance().getReference("Profile pics").child(FirebaseAuth.getInstance().getUid().toString()).getDownloadUrl()
                            .addOnSuccessListener(new OnSuccessListener<Uri>(){
                                @Override
                                public void onSuccess(Uri uri) {
                                    FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().getUid().toString())
                                            .child("Profile pic")
                                            .setValue(uri.toString());

                                }
                            });




//                        database.keepSynced(true);
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
//                        ImageView pp=findViewById(R.id.profilepic);
//                        pp.setImageResource(R.drawable.add_photo);
                    finish();}catch (Exception ex){Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }catch (Exception ex){  Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
    }
}
else{



    try{
        StorageReference ref=FirebaseStorage.getInstance().getReference("Cover pics").child(FirebaseAuth.getInstance().getUid());
        ByteArrayOutputStream bytes= new ByteArrayOutputStream();

        croppedImage.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        final byte[] data= bytes.toByteArray();
        ref.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                try{

                    FirebaseStorage.getInstance().getReference("Cover pics").child(FirebaseAuth.getInstance().getUid().toString()).getDownloadUrl()
                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    FirebaseDatabase.getInstance().getReference("Names").child(FirebaseAuth.getInstance().getUid().toString())
                                            .child("Cover pic")
                                            .setValue(uri.toString());
                                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                                }
                            });
//                        database.keepSynced(true);
                    Toast.makeText(getApplicationContext(), "Cover photo Updated", Toast.LENGTH_SHORT).show();
//                        ImageView pp=findViewById(R.id.profilepic);
//                        pp.setImageResource(R.drawable.add_photo);
                    finish();}catch (Exception ex){Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }catch (Exception ex){  Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
    }


}






//
//            CropUtil.startBackgroundJob(this, null, getString(R.string.croperino_saving_image), new Runnable() {
//                public void run() {
//                    saveOutput(b);
//                }
//            }, mHandler);
        }
    }

    private void saveOutput(Bitmap croppedImage) {


//        ImageView iv= this.findViewById(R.id.yeaa);
//        iv.setImageBitmap(croppedImage);
//        View view = LayoutInflater.inflate(R.layout.tab3,);
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = mContentResolver.openOutputStream(mSaveUri);
                if (outputStream != null) {
                    croppedImage.compress(mOutputFormat, 80, outputStream);
                }
            } catch (Exception ex) {
//                Log.e(TAG, "Cannot open file: " + mSaveUri, ex);
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(CropImage.this,"Cannot access file due to app storage encryption, Please use camera or other apps to open gallery",Toast.LENGTH_SHORT).show();
//                        Crouton.makeText(CropImage.this, "Cannot access file due to app storage encryption, Please use camera or other apps to open gallery", Style.ALERT).show();
                    }
                });

                setResult(RESULT_CANCELED);
                finish();
                return;
            } finally {
                CropUtil.closeSilently(outputStream);
            }

            Bundle extras = new Bundle();
            Intent intent = new Intent(mSaveUri.toString());
            intent.putExtras(extras);
            intent.putExtra(IMAGE_PATH, mImagePath);
            intent.putExtra(ORIENTATION_IN_DEGREES, CropUtil.getOrientationInDegree(this));
            setResult(RESULT_OK, intent);
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
//                    Crouton.makeText(CropImage.this, "Image URL does not exist please try again", Style.ALERT).show();
                }
            });
        }
        croppedImage.recycle();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BitmapManager.instance().cancelThreadDecoding(mDecodingThreads);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBitmap != null) {
            mBitmap.recycle();
        }
    }

    Runnable mRunFaceDetection = new Runnable() {
        @SuppressWarnings("hiding")
        float mScale = 1F;
        Matrix mImageMatrix;
        FaceDetector.Face[] mFaces = new FaceDetector.Face[3];
        int mNumFaces;

        private void handleFace(FaceDetector.Face f) {
            PointF midPoint = new PointF();
            int r = ((int) (f.eyesDistance() * mScale)) * 2;
            f.getMidPoint(midPoint);
            midPoint.x *= mScale;
            midPoint.y *= mScale;

            int midX = (int) midPoint.x;
            int midY = (int) midPoint.y;

            HighlightView hv = new HighlightView(mImageView);

            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();

            Rect imageRect = new Rect(0, 0, width, height);

            RectF faceRect = new RectF(midX, midY, midX, midY);
            faceRect.inset(-r, -r);
            if (faceRect.left < 0) {
                faceRect.inset(-faceRect.left, -faceRect.left);
            }

            if (faceRect.top < 0) {
                faceRect.inset(-faceRect.top, -faceRect.top);
            }

            if (faceRect.right > imageRect.right) {
                faceRect.inset(faceRect.right - imageRect.right,
                        faceRect.right - imageRect.right);
            }

            if (faceRect.bottom > imageRect.bottom) {
                faceRect.inset(faceRect.bottom - imageRect.bottom,
                        faceRect.bottom - imageRect.bottom);
            }

            hv.setup(mImageMatrix, imageRect, faceRect, mCircleCrop,
                    mAspectX != 0 && mAspectY != 0);

            mImageView.add(hv);
        }

        private void makeDefault() {
            HighlightView hv = new HighlightView(mImageView);

            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();

            Rect imageRect = new Rect(0, 0, width, height);
            int cropWidth = Math.min(width, height) * 4 / 5;
            int cropHeight = cropWidth;

            if (mAspectX != 0 && mAspectY != 0) {

                if (mAspectX > mAspectY) {

                    cropHeight = cropWidth * mAspectY / mAspectX;
                } else {

                    cropWidth = cropHeight * mAspectX / mAspectY;
                }
            }

            int x = (width - cropWidth) / 2;
            int y = (height - cropHeight) / 2;

            RectF cropRect = new RectF(x, y, x + cropWidth, y + cropHeight);
            hv.setup(mImageMatrix, imageRect, cropRect, mCircleCrop,
                    mAspectX != 0 && mAspectY != 0);

            mImageView.mHighlightViews.clear();

            mImageView.add(hv);
        }

        private Bitmap prepareBitmap() {
            if (mBitmap == null) {
                return null;
            }

            if (mBitmap.getWidth() > 256) {
                mScale = 256.0F / mBitmap.getWidth();
            }

            Matrix matrix = new Matrix();
            matrix.setScale(mScale, mScale);
            return Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        }

        public void run() {
            mImageMatrix = mImageView.getImageMatrix();
            Bitmap faceBitmap = prepareBitmap();

            mScale = 1.0F / mScale;
            if (faceBitmap != null && mDoFaceDetection) {
                FaceDetector detector = new FaceDetector(faceBitmap.getWidth(),
                        faceBitmap.getHeight(), mFaces.length);
                mNumFaces = detector.findFaces(faceBitmap, mFaces);
            }

            if (faceBitmap != null && faceBitmap != mBitmap) {
                faceBitmap.recycle();
            }

            mHandler.post(new Runnable() {
                public void run() {
                    mWaitingToPick = mNumFaces > 1;
                    if (mNumFaces > 0) {
                        for (int i = 0; i < mNumFaces; i++) {
                            handleFace(mFaces[i]);
                        }
                    } else {
                        makeDefault();
                    }
                    mImageView.invalidate();
                    if (mImageView.mHighlightViews.size() == 1) {
                        mCrop = mImageView.mHighlightViews.get(0);
                        mCrop.setFocus(true);
                    }

                    if (mNumFaces > 1) {
//                        Crouton.makeText(CropImage.this, "Multi face crop help", Style.INFO).show();
                    }
                }
            });
        }
    };

    public static void showStorageToast(Activity activity) {
        showStorageToast(activity, calculatePicturesRemaining(activity));
    }

    public static void showStorageToast(Activity activity, int remaining) {
        String noStorageText = null;
        if (remaining == NO_STORAGE_ERROR) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_CHECKING)) {
                noStorageText = activity.getString(R.string.croperino_preparing_card);
            } else {
                noStorageText = activity.getString(R.string.croperino_no_storage_card);
            }
        } else if (remaining < 1) {
            noStorageText = activity.getString(R.string.croperino_not_enough_space);
        }

        if (noStorageText != null) {
//            Crouton.makeText(activity, noStorageText, Style.ALERT).show();
        }
    }

    public static int calculatePicturesRemaining(Activity activity) {
        try {
            String storageDirectory = "";
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                storageDirectory = Environment.getExternalStorageDirectory().toString();
            } else {
                storageDirectory = activity.getFilesDir().toString();
            }
            StatFs stat = new StatFs(storageDirectory);
            float remaining = ((float) stat.getAvailableBlocks()
                    * (float) stat.getBlockSize()) / 400000F;
            return (int) remaining;
        } catch (Exception ex) {
            return CANNOT_STAT_ERROR;
        }
    }

    private void rotateImageIfNecessary() {
        try {
            ExifInterface exif = new ExifInterface(mImagePath);
            String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

            int rotationAngle = 0;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

            Matrix matrix = new Matrix();
            matrix.postRotate(rotationAngle);
            mBitmap = Bitmap.createBitmap(mBitmap, mOutputX, mOutputY, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            Log.e(TAG, "cannot rotate file");
        }
    }
}


