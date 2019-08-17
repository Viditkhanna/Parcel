package h.user.tired

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import java.util.ArrayList

class Upload2 {

    private lateinit var  feedImage: ImageView
    init {

    }
    constructor() {
        //empty constructor needed
    }
    constructor(bmp:Bitmap,ctx:Context){
        Toast.makeText(ctx,"Upload Bitmap",Toast.LENGTH_SHORT).show()





    }

    fun setImageView(iv:ImageView) {
        this.feedImage=iv

    }
    fun getImageView():ImageView {
        return feedImage
    }

}