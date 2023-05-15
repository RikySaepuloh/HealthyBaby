package id.example.healthybaby

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import id.example.healthybaby.databinding.ActivityResultBinding
import java.util.*

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    var preferences = Preferences()
    var imt=0.0
    var statusGizi=""
    var nilaiGizi=""
    var nilaiTinggi=""
    var statusTinggi=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)
        val usia = getMonthFromBirthdate(preferences.getTanggalLahir())
        binding.tvUsia.text = "$usia bulan"
        statusGizi()
        statusTinggi()
        statusMPAsi()

        binding.btnSimpan.setOnClickListener {
            saveResult()
            startActivity(
                Intent(this, SaveActivity::class.java)
                    .putExtra("status gizi",statusGizi)
                    .putExtra("status tinggi",statusTinggi)
                    .putExtra("nilai gizi",nilaiGizi)
                    .putExtra("nilai tinggi",nilaiTinggi)
            )
            finishAffinity()
        }

        showData()

    }


    fun statusMPAsi(){
        var hasiljenis = preferences.getHasilJenis()
        binding.tvMpAsi.text = hasiljenis
        when (hasiljenis) {
            "Kurang" -> {
                binding.tvMpAsi.setTextColor(ContextCompat.getColor(this,R.color.red))
            }
            "Cukup" -> {
                binding.tvMpAsi.setTextColor(ContextCompat.getColor(this,R.color.yellow))
            }
            else -> {
                binding.tvMpAsi.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }
    }

    fun saveResult(){
        val db = Firebase.firestore
        val userId = preferences.getUserID() // Replace with the actual user ID
        val medicalHistoryRef = userId?.let { db.collection("bayi").document(it).collection("medicalHistory") }
        var loggedas=""
        var nama=""
        if (preferences.getLoggedAs() == "Dokter" || preferences.getLoggedAs() == "Kader"){
            loggedas = preferences.getLoggedAs().toString()
            nama = preferences.getNamaNakes().toString()
        }else{
            loggedas = preferences.getLoggedAs().toString()
            nama = "-"
        }


        val newRecord = hashMapOf(
            "tinggi badan" to preferences.getTB()!!.toDouble(),
            "berat badan" to preferences.getBB()!!.toDouble(),
            "status gizi" to statusGizi,
            "status tinggi" to statusTinggi,
            "nilai tinggi" to nilaiTinggi.toDouble(),
            "nilai gizi" to nilaiGizi.toDouble(),
            "mp-asi 1" to preferences.getJenis1(),
            "mp-asi 2" to preferences.getJenis2(),
            "mp-asi 3" to preferences.getJenis3(),
            "mp-asi 4" to preferences.getJenis4(),
            "frekuensi" to preferences.getFrekuensi(),
            "hasil mp-asi" to preferences.getHasilJenis(),
            "imt" to imt,
            "pic" to nama,
            "hakakses" to loggedas,
            "date" to dateTodaddmmyyy()
        )



        medicalHistoryRef?.add(newRecord)
            ?.addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "Medical record added with ID: ${documentReference.id}")
            }
            ?.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding medical record", e)
            }

    }

    fun showData(){
        binding.tvNama.text = preferences.getNama()
        binding.tvNik.text = preferences.getNIK()
        binding.tvJk.text = preferences.getJenisKelamin()
        binding.tvTempatLahir.text = preferences.getTempatLahir()
        binding.tvTanggalLahir.text = preferences.getTanggalLahir()
        binding.tvNilaiGizi.text = nilaiGizi
        binding.tvNilaiTinggi.text = nilaiTinggi
        binding.tvStatusGizi.text = statusGizi
        binding.tvStatusTinggi.text = statusTinggi
        binding.tvTb.text = preferences.getTB()
        binding.tvBb.text = preferences.getBB()
        binding.tvMpAsi1.text = preferences.getJenis1()
        binding.tvMpAsi2.text = preferences.getJenis2()
        binding.tvMpAsi3.text = preferences.getJenis3()
        binding.tvMpAsi4.text = preferences.getJenis4()
        binding.tvStatusTinggi.text = statusTinggi
        val berat = preferences.getBB()!!.toDouble()
        val panjang = preferences.getTB()!!.toDouble()
        imt = berat/((panjang/100)*(panjang/100))
        binding.tvImt.text = imt.toString().substringBefore(".") + "." + imt.toString().substringAfter(".").substring(0,1)
        when (statusTinggi) {
            "Sangat Pendek" , "Tidak Diketahui"-> {
                binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(this,R.color.red))
            }
            "Pendek", "Tinggi" -> {
                binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(this,R.color.yellow))
            }
            else -> {
                binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }

        when (statusGizi) {
            "Gizi Buruk", "Obesitas", "Tidak Diketahui" -> {
                binding.tvStatusGizi.setTextColor(ContextCompat.getColor(this,R.color.red))
            }
            "Gizi Kurang", "Beresiko Gizi Lebih", "Gizi Lebih" -> {
                binding.tvStatusGizi.setTextColor(ContextCompat.getColor(this,R.color.yellow))
            }
            else -> {
                binding.tvStatusGizi.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }
    }


    fun statusGizi(){
        val usia = if(getMonthFromBirthdate(preferences.getTanggalLahir()) == 0){
            1
        }else{
            getMonthFromBirthdate(preferences.getTanggalLahir())
        }
        val berat = preferences.getBB()!!.toDouble()
        val panjang = preferences.getTB()!!.toDouble()
        imt = berat/((panjang/100)*(panjang/100))
        if (preferences.getJenisKelamin() == "Laki-Laki"){
            val median = arrayListOf(17.3,17.3,17.3,17.2,17.0,16.9,16.8,16.7,16.6,16.4,16.3,16.3,16.2,16.1,16.1,16.0,15.9,15.8,15.8,15.7)
            val sd3min = arrayListOf(13.6, 13.7, 13.6, 13.6, 13.5, 13.4, 13.4, 13.3, 13.2, 13.1, 13.1, 13.0, 12.9, 12.9, 12.8, 12.8, 12.7, 12.7, 12.7)
            val sd2min = arrayListOf(14.7,14.8,14.7,14.7,14.6,14.5,14.4,14.3,14.2,14.1,14.0,13.9,13.9,13.8,13.7,13.7,13.6,13.6,13.6)
            val sd1min = arrayListOf(16.0,16.0,15.9,15.8,15.7,15.6,15.5,15.4,15.3,15.2,15.1,15.0,14.9,14.9,14.8,14.7,14.7,14.6,14.6)
            val sd1plus = arrayListOf(18.8,18.8,18.7,18.6,18.5,18.4,18.2,18.1,18.0,17.8,17.7,17.6,17.5,17.4,17.3,17.2,17.2,17.1,17.0)
            val sd2plus = arrayListOf(20.5,20.5,20.4,20.3,20.1,20.0,19.8,19.7,19.5,19.4,19.3,19.1,19.0,18.9,18.8,18.7,18.7,18.6,18.5)
            val sd3plus = arrayListOf(22.3, 22.3, 22.2, 22.1, 22.0, 21.8, 21.6, 21.5, 21.3, 21.2, 21.0, 20.9, 20.8, 20.7, 20.6, 20.5, 20.4, 20.3, 20.3)
            try {
                if(imt < median[0]){
                    val nilaistatus = (imt - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                    nilaiGizi = nilaistatus.toString()
                    statusGizi = if (nilaistatus < sd3min[usia-1]){
                        "Gizi Buruk"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus < sd2min[usia-1]){
                        "Gizi Kurang"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus <= sd1plus[usia-1]){
                        "Gizi Baik (Normal)"
                    }else if (nilaistatus > sd1plus[usia-1] && nilaistatus <= sd2plus[usia-1]){
                        "Beresiko Gizi Lebih"
                    }else if (nilaistatus > sd2plus[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        "Gizi Lebih"
                    }else if(nilaistatus > sd3plus[usia-1]){
                        "Obesitas"
                    }else{
                        "Tidak diketahui"
                    }
                }else{
                    val nilaistatus = (imt - median[usia-1]) / (sd1plus[usia-1] - (median[usia-1]))
                    nilaiGizi = nilaistatus.toString()
                    statusGizi = if (nilaistatus < sd3min[usia-1]){
                        "Gizi Buruk"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus < sd2min[usia-1]){
                        "Gizi Kurang"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus <= sd1plus[usia-1]){
                        "Gizi Baik (Normal)"
                    }else if (nilaistatus > sd1plus[usia-1] && nilaistatus <= sd2plus[usia-1]){
                        "Beresiko Gizi Lebih"
                    }else if (nilaistatus > sd2plus[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        "Gizi Lebih"
                    }else if(nilaistatus > sd3plus[usia-1]){
                        "Obesitas"
                    }else{
                        "Tidak diketahui"
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{
            val median = arrayListOf(16.9,16.9,16.8,16.7,16.6,16.5,16.4,16.2,16.1,16.0,15.9,15.8,15.7,15.7,15.6,15.5,15.5,15.4,15.4)
            val sd3min = arrayListOf(13.0, 13.0, 13.0, 12.9, 12.9, 12.8, 12.7, 12.6, 12.6, 12.5, 12.4, 12.4, 12.3, 12.3, 12.2, 12.2, 12.2, 12.2, 12.1)
            val sd2min = arrayListOf(14.1,14.2,14.1,14.1,14.0,13.9,13.8,13.7,13.6,13.5,13.5,13.4,13.3,13.3,13.2,13.2,13.1,13.1,13.1)
            val sd1min = arrayListOf(15.5,15.5,15.4,15.3,15.2,15.1,15.0,14.9,14.8,14.7,14.6,14.5,14.4,14.4,14.3,14.3,14.2,14.2,14.2)
            val sd1plus = arrayListOf(18.5,18.5,18.4,18.3,18.2,18.0,17.9,17.7,17.6,17.5,17.4,17.3,17.2,17.1,17.0,17.0,16.9,16.9,16.8)
            val sd2plus = arrayListOf(20.3,20.3,20.2,20.1,19.9,19.8,19.6,19.5,19.3,19.2,19.1,18.9,18.8,18.8,18.7,18.6,18.5,18.5,18.4)
            val sd3plus = arrayListOf(22.3,22.3,22.2,22.1,21.9,21.8,21.6,21.4,21.3,21.1,21.0,20.9,20.8,20.7,20.6,20.5,20.4,20.4,20.3)

            try {
                if(imt < median[0]){
                    val nilaistatus = (imt - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                    nilaiGizi = nilaistatus.toString()
                    statusGizi = if (nilaistatus < sd3min[usia-1]){
                        "Gizi Buruk"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus < sd2min[usia-1]){
                        "Gizi Kurang"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus <= sd1plus[usia-1]){
                        "Gizi Baik (Normal)"
                    }else if (nilaistatus > sd1plus[usia-1] && nilaistatus <= sd2plus[usia-1]){
                        "Beresiko Gizi Lebih"
                    }else if (nilaistatus > sd2plus[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        "Gizi Lebih"
                    }else if(nilaistatus > sd3plus[usia-1]){
                        "Obesitas"
                    }else{
                        "Tidak diketahui"
                    }
                }else{
                    val nilaistatus = (imt - median[usia-1]) / (sd1plus[usia-1] - (median[usia-1]))
                    nilaiGizi = nilaistatus.toString()
                    statusGizi = if (nilaistatus < sd3min[usia-1]){
                        "Gizi Buruk"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus < sd2min[usia-1]){
                        "Gizi Kurang"
                    }else if (nilaistatus >= sd2min[usia-1] && nilaistatus <= sd1plus[usia-1]){
                        "Gizi Baik (Normal)"
                    }else if (nilaistatus > sd1plus[usia-1] && nilaistatus <= sd2plus[usia-1]){
                        "Beresiko Gizi Lebih"
                    }else if (nilaistatus > sd2plus[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        "Gizi Lebih"
                    }else if(nilaistatus > sd3plus[usia-1]){
                        "Obesitas"
                    }else{
                        "Tidak diketahui"
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun statusTinggi(){
        var usia = if(getMonthFromBirthdate(preferences.getTanggalLahir()) == 0){
            1
        }else{
            getMonthFromBirthdate(preferences.getTanggalLahir())
        }
        val panjang = preferences.getTB()!!.toDouble()
        if (preferences.getJenisKelamin() == "Laki-Laki"){
            val median = arrayListOf(67.6,69.2,70.6,72.0,73.3,74.5,75.7,76.9,78.0,79.3,80.2,81.2,82.3,83.2,84.2,85.1,86.0,86.9,87.9)
            val sd3min = arrayListOf(61.2, 62.7, 64.0, 65.2, 66.4, 67.6, 68.6,69.6,70.6, 71.6,72.5,73.3,74.2,75.0, 75.8, 76.5, 77.2, 78.0, 78.7)
            val sd2min = arrayListOf(63.3,64.8,66.2,67.5,68.7,69.9,71.0,72.0,72.1,73.1,74.1,75.0,76.0,76.9,77.7,78.6,79.4,80.2,81.0,81.7)
            val sd1min = arrayListOf(65.5,67.0,68.4,69.7,71.0,72.2,73.4,74.5,75.6,76.6,77.6,78.6,79.6,80.5,81.4,82.3,83.1,83.9,84.8)
            val sd1plus = arrayListOf(69.8,71.3,72.8,74.2,75.6,76.9,78.1,79.3,80.5,81.7,82.8,83.9,85.0,86.0,87.0,88.0,89.0,89.9,90.9)
            val sd2plus = arrayListOf(71.9,73.5,75.0,76.5,77.9,79.2,80.5,81.8,83.0,84.2,85.4,86.5,87.7,88.8,89.8,90.9,91.9,92.9,93.9)
            val sd3plus = arrayListOf(74.0,75.7,77.2,78.7,80.1,81.5,82.9,84.2,85.5,86.7,88.0,89.2,90.4,91.5,92.6,93.8,94.9,95.9,97.0)

            try {
                if(panjang < median[0]){
                    val nilaistatus = (panjang - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                    nilaiTinggi = nilaistatus.toString()
                    statusTinggi = if (nilaistatus <= sd3min[usia-1]){
                        ("Sangat Pendek")
                    }else if (nilaistatus > sd3min[usia-1] && nilaistatus <= sd2min[usia-1]){
                        ("Pendek")
                    }else if (nilaistatus > sd2min[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        ("Normal")
                    }else if(nilaistatus > sd3plus[usia-1]){
                        ("Tinggi")
                    }else{
                        ("Tidak Diketahui")
                    }
                }else{
                    val nilaistatus = (panjang - median[usia-1]) / (sd1plus[usia-1] + (median[usia-1]))
                    nilaiTinggi = nilaistatus.toString()
                    statusTinggi = if (nilaistatus <= sd3min[usia-1]){
                        ("Sangat Pendek")
                    }else if (nilaistatus > sd3min[usia-1] && nilaistatus <= sd2min[usia-1]){
                        ("Pendek")
                    }else if (nilaistatus > sd2min[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        ("Normal")
                    }else if(nilaistatus > sd3plus[usia-1]){
                        ("Tinggi")
                    }else{
                        ("Tidak Diketahui")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }else{
            val median = arrayListOf(65.7,67.3,68.7,70.1,71.5,72.8,74.0,75.2,76.4,77.5,78.6,79.7,80.7,81.7,82.7,83.7,84.6,85.5,86.4)
            val sd3min = arrayListOf(58.9,60.3,61.7,62.9,64.1,65.2,66.3,67.3,68.3,69.3,70.2,71.1,72.0,72.8,73.7,74.5,75.2,76.0,76.7)
            val sd2min = arrayListOf(61.2,62.7,64.0,65.3,66.5,67.7,68.9,70.0,71.0,72.0,73.0,74.0,74.9,75.8,76.7,77.5,78.4,79.2,80.0)
            val sd1min = arrayListOf(63.5,65.0,66.4,67.7,69.0,70.3,71.4,72.6,73.7,74.8,75.8,76.8,77.8,78.8,79.7,80.6,81.5,82.3,83.2)
            val sd1plus = arrayListOf(68.0,69.6,71.1,72.6,73.9,75.3,76.6,77.8,79.1,80.2,81.4,82.5,83.6,84.7,85.7,86.7,87.7,88.7,89.6)
            val sd2plus = arrayListOf(70.3,71.9,73.5,75.0,76.4,77.8,79.2,80.5,81.7,83.0,84.2,85.4,86.5,87.6,88.7,89.8,90.8,91.9,92.9)
            val sd3plus = arrayListOf(72.5,74.2,75.8,77.4,78.9,80.3,81.7,83.1,84.4,85.7,87.0,88.2,89.4,90.6,91.7,92.9,94.0,95.0,96.1)


            try {
                if(panjang < median[0]){
                    val nilaistatus = (panjang - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                    nilaiTinggi = nilaistatus.toString()
                    statusTinggi = if (nilaistatus <= sd3min[usia-1]){
                        ("Sangat Pendek")
                    }else if (nilaistatus > sd3min[usia-1] && nilaistatus <= sd2min[usia-1]){
                        ("Pendek")
                    }else if (nilaistatus > sd2min[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        ("Normal")
                    }else if(nilaistatus > sd3plus[usia-1]){
                        ("Tinggi")
                    }else{
                        ("Tidak Diketahui")
                    }
                }else{
                    val nilaistatus = (panjang - median[usia-1]) / (sd1plus[usia-1] + (median[usia-1]))
                    nilaiTinggi = nilaistatus.toString()
                    statusTinggi = if (nilaistatus <= sd3min[usia-1]){
                        ("Sangat Pendek")
                    }else if (nilaistatus > sd3min[usia-1] && nilaistatus <= sd2min[usia-1]){
                        ("Pendek")
                    }else if (nilaistatus > sd2min[usia-1] && nilaistatus <= sd3plus[usia-1]){
                        ("Normal")
                    }else if(nilaistatus > sd3plus[usia-1]){
                        ("Tinggi")
                    }else{
                        ("Tidak Diketahui")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}




