package h.user.tired

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.RelativeLayout
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.firebase.ui.database.FirebaseListAdapter



class Chat1 : AppCompatActivity() {

        var SIGN_IN_REQUEST_CODE = 1;
    private val adapter: FirebaseListAdapter<ChatMessage>? = null
    var activity_main= RelativeLayout(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat1)
        activity_main=findViewById(R.id.chat1a)

try{
        //Check if not sign in than navigate Signin page
        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            try {
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE)
            }catch (ex:Exception){Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show()}
            }
        else
        {
            Snackbar.make(activity_main,"Welcome"+FirebaseAuth.getInstance().currentUser!!.email,Snackbar.LENGTH_SHORT)
        }}catch (ex:Exception){Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show()}
    }
}
