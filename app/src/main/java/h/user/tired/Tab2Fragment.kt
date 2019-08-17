package h.user.tired


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
//import android.view.*
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.database.FirebaseListAdapter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.tab2.*

class Tab2Fragment : Fragment() {
    var adapter: FirebaseListAdapter<ChatMessage>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        Toast.makeText(activity, "oncreateview", Toast.LENGTH_SHORT).show()

        var view=inflater.inflate(R.layout.tab2, container, false)
//        val re = FirebaseDatabase.getInstance().reference.child("Names")
//        var listofmessage = view.findViewById<ListView>(R.id.list_of_message)
//
//
//        adapter = object : FirebaseListAdapter<ChatMessage>(activity, ChatMessage::class.java, R.layout.list_item, re) {
//            override fun populateView(v: View, model: ChatMessage, position: Int) {
//                val messageText: TextView
//                val messageUser: TextView
//                val messageTime: TextView
//                messageText = v.findViewById(R.id.message_text) as TextView
//                messageUser = v.findViewById(R.id.message_user) as TextView
//                messageTime = v.findViewById(R.id.message_time) as TextView
//                messageText.text = model.messageText
//                messageUser.text = model.messageUser
//                //   messageTime.setText(DateFormat.format("dd-MM-yyyy  (HH:mm:ss)", model.getMessageTime()));
//
//            }
//
//
//        }
//        //Toast.makeText(activity, "tab211111111111", Toast.LENGTH_SHORT).show()
//
//        listofmessage.setAdapter( adapter)

        return inflater.inflate(R.layout.tab2, container, false)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toast.makeText(activity, "oncreate", Toast.LENGTH_SHORT).show()

//        try {
////
////        fab.setOnClickListener{
////            Snackbar.make(tab2, FirebaseAuth.getInstance().currentUser!!.email!!.toString(), Snackbar.LENGTH_SHORT).show()
////            FirebaseDatabase.getInstance().reference.child("messages").child("").push().setValue(ChatMessage(input.text.toString(), FirebaseAuth.getInstance().currentUser!!.email))
////            input.setText("")
////        }
//
//            //Check if not sign in than navigate Signin page
//            if (FirebaseAuth.getInstance().currentUser == null) {
//                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), 1)
//            } else {
//                //Snackbar.make(activity, "Welcome " + FirebaseAuth.getInstance().currentUser!!.email!!, Snackbar.LENGTH_SHORT).show()
//              //  displayChatMessage()
//
//            }
//        }catch (ex:Exception){
//            Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show()}

    }


    private fun displayChatMessage() {
//
//        val re = FirebaseDatabase.getInstance().reference.child("Names")
//
//        var listofmessage=activity!!.findViewById<ListView>(R.id.list_of_message)
//
//        adapter = object : FirebaseListAdapter<ChatMessage>(activity, ChatMessage::class.java, R.layout.list_item, re) {
//            override fun populateView(v: View, model: ChatMessage, position: Int) {
//                val messageText: TextView
//                val messageUser: TextView
//                val messageTime: TextView
//                messageText = v.findViewById(R.id.message_text) as TextView
//                messageUser = v.findViewById(R.id.message_user) as TextView
//                messageTime = v.findViewById(R.id.message_time) as TextView
//
//                messageText.text = model.messageText
//                messageUser.text = model.messageUser
//                //   messageTime.setText(DateFormat.format("dd-MM-yyyy  (HH:mm:ss)", model.getMessageTime()));
//
//            }
//
//
//        }
//        this.list_of_message.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        activity!!.menuInflater.inflate(R.menu.main_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if (item.itemId == R.id.menu_sign_out) {
//            AuthUI.getInstance().signOut(activity).addOnCompleteListener {
//               // Snackbar.make(activity_main2, "You have been signed out.", Snackbar.LENGTH_SHORT).show()
//                //finish();
//            }
//
//        }

        return true
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//
//        if (requestCode == 1) {
//            if (resultCode == Activity.RESULT_OK) {
//                //Snackbar.make(activity_main2, "Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show()
//                displayChatMessage()
//            } else {
//                //Snackbar.make(activity_main2, "We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show()
//                //finish();
//            }
//        }
//
//
//        super.onActivityResult(requestCode, resultCode, data)
//    }

    companion object {
        private val TAB = "Tab2Fragment"
    }
}