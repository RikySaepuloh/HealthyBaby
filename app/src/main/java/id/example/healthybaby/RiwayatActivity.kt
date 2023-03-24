package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import id.example.healthybaby.databinding.ActivityResultBinding
import id.example.healthybaby.databinding.ActivityRiwayatBinding

class RiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatBinding
    var preferences = Preferences()
    val myadapter = RiwayatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvRiwayat.apply {
            layoutManager = LinearLayoutManager(this@RiwayatActivity)
            adapter = myadapter
        }

        getRiwayat()
    }

    fun getRiwayat(){
        val db = FirebaseFirestore.getInstance()
//        val usersRef = db.collection("bayi")
        val userId=preferences.getUserID()
        val medicalHistoryRef = userId?.let { db.collection("bayi").document(it).collection("medicalHistory") }

//        val query = usersRef.whereEqualTo("email", email).limit(1)

        medicalHistoryRef?.get()!!.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                val mydata = querySnapshot.documents
                Log.d("myhealthy", mydata.toString())
                myadapter.initData(mydata)
//                mydata
//                if (querySnapshot.isEmpty) {
//                    Toast.makeText(this,"Akun tidak ditemukan!", Toast.LENGTH_LONG).show()
//                } else {
//                    // Retrieve user document and compare password
//                    val userDoc = querySnapshot.documents[0]
//                    val user = userDoc.data
//                    if (user?.get("password") == enteredPassword) {
//                        preferences.saveUserID(userDoc.id)
//                        preferences.saveLogStatus(true)
//                        preferences.saveNamaNakes(user["nama"] as String?)
//
//                        startActivity(Intent(this,InputNIKActivity::class.java))
//                        finishAffinity()
//                        // Passwords match, authenticate user
//                    } else {
//                        // Passwords do not match, reject login attempt
//                        Toast.makeText(this,"Username atau Password anda salah!", Toast.LENGTH_LONG).show()
//                    }
//                }
            } else {
                Log.e("LoginActivity", "Error retrieving user document:", task.exception)
            }
        }
    }

}