package h.user.tired;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class Delete extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView mBlogList;
    public Context c=this;
public     int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
//        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
//        mBlogList.setHasFixedSize(true);
//        mBlogList.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//        database=FirebaseDatabase.getInstance();
//        myRef=database.getReference("uploads");


    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        try {
//            OutputStreamWriter file1 = new OutputStreamWriter(openFileOutput("Imageurl.txt", Activity.MODE_PRIVATE));
//        }catch (Exception ex){}
//        FirebaseRecyclerAdapter<Upload,BlogViewHolderr> firebaserecycleradapter= new FirebaseRecyclerAdapter<Upload,BlogViewHolderr>(
//                Upload.class,
//                R.layout.image_item,
//                BlogViewHolderr.class,
//                myRef){
//            @Override
//            protected void populateViewHolder(BlogViewHolderr viewHolder, Upload model, int position) {
//                viewHolder.setImage(getApplicationContext(),model.getImageUrl());
//
//                try {
//                    OutputStreamWriter file1 = new OutputStreamWriter(openFileOutput("Imageurl.txt", Activity.MODE_PRIVATE));
//
//                    file1.append(model.getImageUrl());
//                   // file1.append("Hey how you a doing");
//                    file1.flush();
//                    file1.close();
//                } catch (IOException e) {
//
//                }
//
//            }
//        };
//mBlogList.setAdapter(firebaserecycleradapter);
//
//    }

    public static class  BlogViewHolderr extends RecyclerView.ViewHolder{
        View mView;
    public   BlogViewHolderr(View itemView){
        super(itemView);
        mView=itemView;

    }



        public void setImage(Context ctx, String image){
            ImageView post_image=(ImageView)mView.findViewById(R.id.image_view_upload);
            Picasso.with(ctx).load(image).placeholder(R.mipmap.ic_launcher).into(post_image);
            try {
                FileOutputStream fos;
                fos=  ctx.openFileOutput("Imageurl2.txt", Activity.MODE_PRIVATE);
                ObjectOutputStream oos= new ObjectOutputStream(fos);
              oos.writeChars(image.toString());
                fos.flush();
                fos.close();
            } catch (IOException e) {

            }

    }
    }


}
