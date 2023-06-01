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
        val userId=preferences.getUserID()
        val medicalHistoryRef = userId?.let { db.collection("bayi").document(it).collection("medicalHistory").document(id) }

        binding.tvNama.text = preferences.getNama()
        binding.tvNik.text = preferences.getNIK()

        medicalHistoryRef?.get()!!.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                val mydata = querySnapshot

                binding.tvTb.text = mydata?.get("tinggi badan").toString()
                binding.tvBb.text = mydata?.get("berat badan").toString()
                binding.tvPic.text = mydata?.get("pic").toString()
                binding.tvJabPic.text = mydata?.get("hakakses").toString()

                val statusGizi = mydata?.get("status gizi").toString()
                when (statusGizi) {
                    "Gizi Buruk", "Obesitas", "Tidak Diketahui" -> {
                        binding.tvStatusGizi.text = statusGizi
                        binding.tvStatusGizi.setTextColor(ContextCompat.getColor(this,R.color.red))
                    }
                    "Gizi Kurang", "Beresiko Gizi Lebih", "Gizi Lebih" -> {
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
                    "Sangat Pendek" , "Tidak Diketahui"-> {
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

                binding.tvFrekuensi.text = mydata.get("frekuensi").toString()
                binding.tvKarbo.text = mydata.get("karbo").toString()
                binding.tvProteinh.text = mydata.get("proteinh").toString()
                binding.tvProteinn.text = mydata.get("proteinn").toString()
                binding.tvSayur.text = mydata.get("sayur").toString()
                binding.tvLemak.text = mydata.get("lemak").toString()
                binding.tvBuah.text = mydata.get("buah").toString()
                binding.tvTakaran.text = mydata.get("takaran").toString()
                binding.tvNilaiGizi.text = mydata["nilai gizi"].toString()
                binding.tvNilaiTinggi.text = mydata["nilai tinggi"].toString()
                binding.tvImt.text = mydata["imt"].toString()
                binding.tvTanggal.text = mydata["date"].toString()

            } else {
                Log.e("LoginActivity", "Error retrieving user document:", task.exception)
            }
        }
    }

}