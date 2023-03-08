package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import id.example.healthybaby.databinding.ActivityLoginBinding
import id.example.healthybaby.databinding.ActivityWelcomeBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMasuk.setOnClickListener {
            login(binding.etEmail.text.toString(),binding.etPassword.text.toString())
        }

        binding.btnKembali.setOnClickListener {
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
                        startActivity(Intent(this,InputNIKActivity::class.java))
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

    fun getHistory(){
        val db = Firebase.firestore
        val userId = "abc123" // Replace with the actual user ID
        val medicalHistoryRef = db.collection("users").document(userId).collection("medicalHistory")

        val newRecord = hashMapOf(
            "doctorName" to "Dr. Lee",
            "diagnosis" to "Headache",
            "treatment" to "Painkillers",
            "date" to Date()
        )

        medicalHistoryRef.add(newRecord)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Medical record added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding medical record", e)
            }

        val db = Firebase.firestore
        val userId = "abc123" // Replace with the actual user ID
        val medicalHistoryRef = db.collection("users").document(userId).collection("medicalHistory")

        medicalHistoryRef.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting medical history data", e)
            }

    }
}