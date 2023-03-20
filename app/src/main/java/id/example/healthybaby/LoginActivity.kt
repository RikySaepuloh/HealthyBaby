package id.example.healthybaby

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import id.example.healthybaby.databinding.ActivityLoginBinding
import id.example.healthybaby.databinding.ActivityWelcomeBinding
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var tetapMasuk=true
    var preferences  = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)
        checkLogin()

        binding.btnMasuk.setOnClickListener {
            login(binding.etEmail.text.toString(),binding.etPassword.text.toString())
        }

        binding.btnKembali.setOnClickListener {
            startActivity(Intent(this,IntroductionActivity::class.java))
            finishAffinity()
        }
    }

    private fun checkLogin(){
        if(preferences.getLogStatus()){
            val intent =
                Intent(this, InputNIKActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun login(email:String,enteredPassword:String){
        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("nakes")

        val query = usersRef.whereEqualTo("email", email).limit(1)

        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                if (querySnapshot.isEmpty) {
                    Toast.makeText(this,"Akun tidak ditemukan!",Toast.LENGTH_LONG).show()
                } else {
                    // Retrieve user document and compare password
                    val userDoc = querySnapshot.documents[0]
                    val user = userDoc.data
                    if (user?.get("password") == enteredPassword) {
                        preferences.saveUserID(userDoc.id)
                        preferences.saveLogStatus(true)
                        preferences.saveNamaNakes(user["nama"] as String?)

                        startActivity(Intent(this,InputNIKActivity::class.java))
                        finishAffinity()
                    // Passwords match, authenticate user
                    } else {
                        // Passwords do not match, reject login attempt
                        Toast.makeText(this,"Username atau Password anda salah!",Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Log.e("LoginActivity", "Error retrieving user document:", task.exception)
            }
        }
    }

}