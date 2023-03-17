package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import id.example.healthybaby.databinding.ActivityInputNikactivityBinding
import id.example.healthybaby.databinding.ActivityLoginBinding
import java.util.*

class InputNIKActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputNikactivityBinding
    var preferences  = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNikactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)

        if (preferences.getLoggedAs() == "Nakes") {
            ucapanSelamat()
            binding.btnLogout.visibility = View.VISIBLE
        }

        binding.tvRegistrasi.setOnClickListener {
            startActivity(Intent(this,RegistrasiActivity::class.java))
        }

        binding.btnMasuk.setOnClickListener {
            getNIK(binding.etNik.text.toString())
        }

        binding.btnLogout.setOnClickListener {
            preferences.preferencesLogout()
            startActivity(Intent(this,LoginActivity::class.java))
            finishAffinity()
        }
    }



    fun ucapanSelamat(){
        when (getTimeNow()) {
            in 0..11 -> {
                binding.tvNamaNakes.text = "Selamat Pagi ${preferences.getNamaNakes()?.capitalized()}!!"
            }
            in 12..14 -> {
                binding.tvNamaNakes.text = "Selamat Siang ${preferences.getNamaNakes()?.capitalized()}!!"
            }
            in 15..17 -> {
                binding.tvNamaNakes.text = "Selamat Sore ${preferences.getNamaNakes()?.capitalized()}!!"
            }
            in 18..23 -> {
                binding.tvNamaNakes.text = "Selamat Malam ${preferences.getNamaNakes()?.capitalized()}!!"
            }
            else -> {
                binding.tvNamaNakes.text = "Halo ${preferences.getNamaNakes()?.capitalized()}!!"
            }
        }
    }

    fun getNIK(nik:String){
        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("bayi")

        val query = usersRef.whereEqualTo("NIK", nik).limit(1)

        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                if (querySnapshot.isEmpty) {
                    Toast.makeText(this,"Akun tidak ditemukan!", Toast.LENGTH_LONG).show()
                } else {
                    // Retrieve user document and compare password
                    val userDoc = querySnapshot.documents[0]
                    val user = userDoc.data

                    preferences.saveNama(user?.get("nama") as String?)
                    preferences.saveNIK(user?.get("NIK") as String?)
                    preferences.saveTempatLahir(user?.get("tempat lahir") as String?)
                    preferences.saveTanggalLahir(user?.get("tanggal lahir") as String?)
                    preferences.saveJenisKelamin(user?.get("jenis kelamin") as String?)

//                    Toast.makeText(this,"Ditemukan",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))
//                    if (user?.get("password") == enteredPassword) {
//                        startActivity(Intent(this,InputNIKActivity::class.java))
//                        // Passwords match, authenticate user
//                    } else {
//                        // Passwords do not match, reject login attempt
//                        Toast.makeText(this,"Username atau Password anda salah!", Toast.LENGTH_LONG).show()
//                    }
                }
            } else {
                Log.e("LoginActivity", "Error retrieving user document:", task.exception)
            }
        }
    }

}