package com.example.facebook_authen


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * A simple [Fragment] subclass.
 */
class login : Fragment() {

    //-----------authen-------------
    var user : FirebaseUser? = null
    lateinit var facebookSignInButton : LoginButton
    var callbackManager : CallbackManager? = null
    // Firebase Auth Object.
    var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext())
        AppEventsLogger.activateApp(activity!!.baseContext)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token : AccessToken) {

        Log.d(ContentValues.TAG, "handleFacebookAccessToken:" + token)
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(ContentValues.TAG, "signInWithCredential:success")

                user = firebaseAuth!!.currentUser

                val profile = profile().newInstance(user!!.photoUrl.toString(),user!!.displayName.toString())
                val fm = fragmentManager
                val transaction : FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.layout, profile,"fragment_profile")
                transaction.addToBackStack("fragment_profile")
                transaction.commit()

            } else {
                // If sign in fails, display a message to the user.
                Log.w(ContentValues.TAG, "signInWithCredential:failure", task.getException())
                Toast.makeText(activity!!.baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //-----------authen-------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment

        val button : Button = view.findViewById(R.id.btnNextPage);

        //-----------authen-------------
        callbackManager = CallbackManager.Factory.create()
        facebookSignInButton  = view.findViewById(R.id.login_button) as LoginButton
        firebaseAuth = FirebaseAuth.getInstance()
        facebookSignInButton.setReadPermissions("email")

        // If using in a fragment
        facebookSignInButton.setFragment(this)

        val token: AccessToken?
        token = AccessToken.getCurrentAccessToken()

        if (token != null) { //Means user is not logged in
            LoginManager.getInstance().logOut()
        }

        facebookSignInButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) { // App code

                handleFacebookAccessToken(loginResult!!.accessToken)

            }
            override fun onCancel() { // App code
            }
            override fun onError(exception: FacebookException) { // App code
            }
        })

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) { // App code

                    handleFacebookAccessToken(loginResult!!.accessToken)

                }

                override fun onCancel() { // App code
                }

                override fun onError(exception: FacebookException) { // App code
                }
            })
        //-----------authen-------------

        button.setOnClickListener {
            val usrInput : EditText = view.findViewById(R.id.user_input);
            val passInput : EditText = view.findViewById(R.id.pass_input);

            val msgUsrInput: String = usrInput.text.toString()
            val passUsrInput: String = passInput.text.toString()

            if(msgUsrInput.trim().length>0&&passUsrInput.trim().length>0){
                if(msgUsrInput!=passUsrInput){
                    Toast.makeText(context,"Please try again", Toast.LENGTH_LONG).show()
                }else{
                    if(msgUsrInput=="60160174"){
                        Toast.makeText(context,"Hello : "+usrInput.text.toString()+" !!! ", Toast.LENGTH_LONG).show()

                        val fragment_menu_cookiepet = Menu_cookiepet()
                        val fm = fragmentManager
                        val transaction : FragmentTransaction = fm!!.beginTransaction()
                        transaction.replace(R.id.layout, fragment_menu_cookiepet,"fragment_menu_cookiepet")
                        transaction.addToBackStack("fragment_menu_cookiepet")
                        transaction.commit()
                    }else{
                        Toast.makeText(context,"Please try again", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(context,"Please try again", Toast.LENGTH_LONG).show()
            }
        }
        return view;
    }
}
