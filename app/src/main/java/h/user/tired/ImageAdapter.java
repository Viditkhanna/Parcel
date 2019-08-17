package h.user.tired;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
public int t=0;
private Context mContext;
private ArrayList<Upload> mUploads;

public ImageAdapter(){}
public ImageAdapter(Context context,ArrayList<Upload> uploads){
    mContext=context;
    mUploads=uploads;
  }
  @NonNull
  @Override
  public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
    return new ImageViewHolder(v);
  }
  @Override
  public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {
    LinearLayoutManager lm=new LinearLayoutManager(mContext){
        @Override
        public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate) {
//      if(((ViewGroup)child).getFocusedChild()instanceof ImageViewHolder){
          Toast.makeText(mContext, "HEYYYY", Toast.LENGTH_SHORT).show();

//          return false;

//      }
            return super.requestChildRectangleOnScreen(parent, child, rect, immediate);

        }
    };
//    Upload uploadCurrent=mUploads.get(holder.position);

//        if(mUploads.get(position)==null ) {
//            t = 1;
//            try {
//                FileOutputStream file1 =new FileOutputStream("empty.txt");
//
//                OutputStreamWriter file2=new OutputStreamWriter(file1);
//                file2.append("ball");
//                // file1.append("Hey how you a doing");
//                file1.flush();
//                file1.close();
//            } catch (IOException ex) {
//
//            }
//        }if(uploadCurrent.getImageUrl()==null || uploadCurrent.getImageUrl()==""){
//            t=1;
//            try {
//        FileOutputStream file1 =new FileOutputStream("empty.txt");
//
//                OutputStreamWriter file2=new OutputStreamWriter(file1);
//        file2.append("ball");
//        // file1.append("Hey how you a doing");
//        file1.flush();
//        file1.close();
//    } catch (IOException ex) {
//
//    }
//
//        }
//        holder.position=position;
//    try {
//      Integer pos=holder.getAdapterPosition();
//
////      holder.imageview.
//      if(mUploads.get(position).getBitmap().get(0)!=null ){
//        Toast.makeText(mContext, pos.toString(), Toast.LENGTH_SHORT).show();
//
////        Toast.makeText(mContext,"IA= "+ mUploads.get(position).getBitmap().get(0).toString(), Toast.LENGTH_SHORT).show();
////          Toast.makeText(mContext,"Size= "+ pos.toString(), Toast.LENGTH_SHORT).show();
//
//        //                ArrayList<Bitmap> bmp = mUploads.get(i).getBitmap();
////          ByteArrayOutputStream stream=new ByteArrayOutputStream();
////            Bitmap tempbmp=mUploads.get(position).getBitmap().get(0);
////          Glide.with(mContext)
////                  .asBitmap()
////                    .load(stream)
////                    .tr
////                    .into(holder.imageview);
//
//        holder.imageview.setImageBitmap(mUploads.get(position).getBitmap().get(0));
//      }
//      else {
//        Toast.makeText(mContext, "Yaha kha", Toast.LENGTH_SHORT).show();
////                    Glide.with(mContext)
////                            .load(null)
////                            .into(holder.imageview);
//        holder.imageview.setImageBitmap(null);
//
//      }
//    }catch (Exception ex){ Toast.makeText(mContext, ex.toString(), Toast.LENGTH_SHORT).show();
//    }

//        Picasso.with(holder.itemView.getContext())
//                .load(R.drawable.profile)
//                .placeholder(R.mipmap.ic_launcher)
//                .fit()
//                .centerCrop()
//                .into(holder.imageview);


//        Picasso.with(mContext)
//                .load(uploadCurrent.getImageUrl())
//              .fit()
//                .centerCrop()
//                .into(holder.imageview);



  }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
  public int getItemCount() {
    return  mUploads.size();
  }

  public class ImageViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageview;

    public ImageViewHolder(View itemview){

      super(itemview);
      try {


          imageview = itemview.findViewById(R.id.image_view_upload);
      }catch (Exception ex){        Toast.makeText(mContext, ex.toString(), Toast.LENGTH_SHORT).show();
      }}



  }


}
