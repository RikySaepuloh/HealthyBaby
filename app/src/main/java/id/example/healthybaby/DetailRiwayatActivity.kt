package id.example.healthybaby

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import id.example.healthybaby.databinding.ActivityDetailRiwayatBinding

class DetailRiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRiwayatBinding
    var preferences  = Preferences()
    var id = ""
    var urut = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)
        id = intent.getStringExtra("id").toString()
        urut = intent.getStringExtra("urut").toString()

        binding.tvKunjungan.text = "Kunjungan ke-$urut"

        binding.btnBack.setOnClickListener {
            finish()
        }
        getDetailRiwayat(id)
    }

    fun getDetailRiwayat(id: String) {
        val db = FirebaseFirestore.getInstance()
//        val usersRef = db.collection("bayi")
        val userId=preferences.getUserID()
        val medicalHistoryRef = userId?.let { db.collection("bayi").document(it).collection("medicalHistory").document(id) }

        medicalHistoryRef?.get()!!.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                val mydata = querySnapshot

                binding.tvTb.text = mydata?.get("tinggi badan").toString()
                binding.tvBb.text = mydata?.get("berat badan").toString()

                val statusGizi = mydata?.get("status gizi").toString()
                when (statusGizi) {
                    "Sangat Kurus", "Sangat Obesitas" -> {
                        binding.tvStatusGizi.text = statusGizi
                        binding.tvStatusGizi.setTextColor(ContextCompat.getColor(this,R.color.red))
                    }
                    "Kurus", "Obesitas" -> {
                        binding.tvStatusGizi.text = statusGizi
                        binding.tvStatusGizi.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                    }
                    else -> {
                        binding.tvStatusGizi.text = statusGizi
                        binding.tvStatusGizi.setTextColor(ContextCompat.getColor(this, R.color.green))
                    }
                }

                val statusTinggi = mydata?.get("status tinggi").toString()
                when (statusTinggi) {
                    "Sangat Pendek", "Sangat Tinggi" -> {
                        binding.tvStatusTinggi.text = statusTinggi
                        binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(this,R.color.red))
                    }
                    "Pendek", "Tinggi" -> {
                        binding.tvStatusTinggi.text = statusTinggi
                        binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(this,R.color.yellow))
                    }
                    else -> {
                        binding.tvStatusTinggi.text = statusTinggi
                        binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(this,R.color.green))
                    }
                }

                binding.tvNilaiGizi.text = mydata["nilai gizi"].toString()
                binding.tvNilaiTinggi.text = mydata["nilai tinggi"].toString()
                binding.tvTanggal.text = mydata["date"].toString()

//                Log.d("myhealthy", mydata.toString())
//                myadapter.initData(mydata)
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