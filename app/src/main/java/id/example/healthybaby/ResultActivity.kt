package id.example.healthybaby

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import id.example.healthybaby.databinding.ActivityProsesBinding
import id.example.healthybaby.databinding.ActivityResultBinding
import java.util.*

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    var preferences = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)

        binding.btnSimpan.setOnClickListener {
            startActivity(Intent(this, SaveActivity::class.java))
            finishAffinity()
        }


    }

    fun saveResult(){
        val db = Firebase.firestore
        val userId = preferences.getUserID() // Replace with the actual user ID
//        val medicalHistoryRef = userId?.let { db.collection("bayi").document(it).collection("medicalHistory") }

//        medicalHistoryRef?.get()
//            ?.addOnSuccessListener { querySnapshot ->
//                for (document in querySnapshot) {
//                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
//                }
//            }
//            ?.addOnFailureListener { e ->
//                Log.w(ContentValues.TAG, "Error getting medical history data", e)
//            }

//        val db = Firebase.firestore
//        val userId = "abc123" // Replace with the actual user ID
        val medicalHistoryRef = userId?.let { db.collection("bayi").document(it).collection("medicalHistory") }

        val newRecord = hashMapOf(
            "tinggi badan" to preferences.getTB(),
            "berat badan" to preferences.getBB(),
            "status gizi" to "",
            "status tinggi" to "",
            "date" to Date()
        )

        medicalHistoryRef?.add(newRecord)
            ?.addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "Medical record added with ID: ${documentReference.id}")
            }
            ?.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding medical record", e)
            }

    }


    fun statusGizi(){
        val day = preferences.getTanggalLahir()?.substringBefore("/")?.toInt()
        val month = preferences.getTanggalLahir()?.substringAfter("/")?.substringBefore("/")?.toInt()
        val year = preferences.getTanggalLahir()?.substringAfterLast("/")?.toInt()
        val usia = day?.let { month?.let { it1 -> year?.let { it2 -> getAge(it, it1, it2) } } }!!.toInt()
        val berat = preferences.getBB()!!.toDouble()
        val panjang = preferences.getTB()!!.toDouble()
        val imt = berat/(panjang*panjang)
        if (preferences.getJenisKelamin() == "Laki-Laki"){
            val median = arrayListOf(17.3,17.3,17.3,17.2,17.0,16.9,16.8,16.7,16.6,16.4,16.3,16.3,16.2,16.1,16.1,16.0,15.9,15.8,15.8,15.7)
            val sd2min = arrayListOf(14.7,14.8,14.7,14.7,14.6,14.5,14.4,14.3,14.2,14.1,14.0,13.9,13.9,13.8,13.7,13.7,13.6,13.6,13.6)
            val sd1min = arrayListOf(16.0,16.0,15.9,15.8,15.7,15.6,15.5,15.4,15.3,15.2,15.1,15.0,14.9,14.9,14.8,14.7,14.7,14.6,14.6)
            val sd1plus = arrayListOf(18.8,18.8,18.7,18.6,18.5,18.4,18.2,18.1,18.0,17.8,17.7,17.6,17.5,17.4,17.3,17.2,17.2,17.1,17.0)
            val sd2plus = arrayListOf(20.5,20.5,20.4,20.3,20.1,20.0,19.8,19.7,19.5,19.4,19.3,19.1,19.0,18.9,18.8,18.7,18.7,18.6,18.5)
            if(berat < median[0]){
                val nilaistatus = (imt - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus < sd2min[usia-1] ){
                    println("Sangat Kurus")
                }else{
                    println("Kurus")
                }
            }else{
                val nilaistatus = (imt - median[usia-1]) / (sd1plus[usia-1] - (median[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus > sd2plus[usia-1] ){
                    println("Sangat Obesitas")
                }else{
                    println("Obesitas")
                }
            }
        }else{
            val median = arrayListOf(16.9,16.9,16.8,16.7,16.6,16.5,16.4,16.2,16.1,16.0,15.9,15.8,15.7,15.7,15.6,15.5,15.5,15.4,15.4)
            val sd2min = arrayListOf(14.1,14.2,14.1,14.1,14.0,13.9,13.8,13.7,13.6,13.5,13.5,13.4,13.3,13.3,13.2,13.2,13.1,13.1,13.1)
            val sd1min = arrayListOf(15.5,15.5,15.4,15.3,15.2,15.1,15.0,14.9,14.8,14.7,14.6,14.5,14.4,14.4,14.3,14.3,14.2,14.2,14.2)
            val sd1plus = arrayListOf(18.5,18.5,18.4,18.3,18.2,18.0,17.9,17.7,17.6,17.5,17.4,17.3,17.2,17.1,17.0,17.0,16.9,16.9,16.8)
            val sd2plus = arrayListOf(20.3,20.3,20.2,20.1,19.9,19.8,19.6,19.5,19.3,19.2,19.1,18.9,18.8,18.8,18.7,18.6,18.5,18.5,18.4)

            if(panjang < median[0]){
                val nilaistatus = (imt - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus < sd2min[usia-1] ){
                    println("Sangat Kurus")
                }else{
                    println("Kurus")
                }
            }else{
                val nilaistatus = (imt - median[usia-1]) / (sd1plus[usia-1] - (median[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus > sd2plus[usia-1] ){
                    println("Sangat Obesitas")
                }else{
                    println("Obesitas")
                }
            }
        }
    }

    fun statusTinggi(){
        if (preferences.getJenisKelamin() == "Laki-Laki"){
            val median = arrayListOf(67.6,69.2,70.6,72.0,73.3,74.5,75.7,76.9,78.0,79.3,80.2,81.2,82.3,83.2,84.2,85.1,86.0,86.9,87.9)
            val sd2min = arrayListOf(63.3,64.8,66.2,67.5,68.7,69.9,71.0,72.0,72.1,73.1,74.1,75.0,76.0,76.9,77.7,78.6,79.4,80.2,81.0,81.7)
            val sd1min = arrayListOf(65.5,67.0,68.4,69.7,71.0,72.2,73.4,74.5,75.6,76.6,77.6,78.6,79.6,80.5,81.4,82.3,83.1,83.9,84.8)
            val sd1plus = arrayListOf(69.8,71.3,72.8,74.2,75.6,76.9,78.1,79.3,80.5,81.7,82.8,83.9,85.0,86.0,87.0,88.0,89.0,89.9,90.9)
            val sd2plus = arrayListOf(71.9,73.5,75.0,76.5,77.9,79.2,80.5,81.8,83.0,84.2,85.4,86.5,87.7,88.8,89.8,90.9,91.9,92.9,93.9)

            val day = preferences.getTanggalLahir()?.substringBefore("/")?.toInt()
            val month = preferences.getTanggalLahir()?.substringAfter("/")?.substringBefore("/")?.toInt()
            val year = preferences.getTanggalLahir()?.substringAfterLast("/")?.toInt()
            val usia = day?.let { month?.let { it1 -> year?.let { it2 -> getAge(it, it1, it2) } } }!!.toInt()
            val panjang = preferences.getTB()!!.toDouble()

            if(panjang < median[0]){
                val nilaistatus = (panjang - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus < sd2min[usia-1] ){
                    println("Sangat Pendek")
                }else{
                    println("Pendek")
                }
            }else{
                val nilaistatus = (panjang - median[usia-1]) / (sd1plus[usia-1] + (median[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus > sd2plus[usia-1] ){
                    println("Sangat Tinggi")
                }else{
                    println("Tinggi")
                }
            }

        }else{
            val median = arrayListOf(65.7,67.3,68.7,70.1,71.5,72.8,74.0,75.2,76.4,77.5,78.6,79.7,80.7,81.7,82.7,83.7,84.6,85.5,86.4)
            val sd2min = arrayListOf(61.2,62.7,64.0,65.3,66.5,67.7,68.9,70.0,71.0,72.0,73.0,74.0,74.9,75.8,76.7,77.5,78.4,79.2,80.0)
            val sd1min = arrayListOf(63.5,65.0,66.4,67.7,69.0,70.3,71.4,72.6,73.7,74.8,75.8,76.8,77.8,78.8,79.7,80.6,81.5,82.3,83.2)
            val sd1plus = arrayListOf(68.0,69.6,71.1,72.6,73.9,75.3,76.6,77.8,79.1,80.2,81.4,82.5,83.6,84.7,85.7,86.7,87.7,88.7,89.6)
            val sd2plus = arrayListOf(70.3,71.9,73.5,75.0,76.4,77.8,79.2,80.5,81.7,83.0,84.2,85.4,86.5,87.6,88.7,89.8,90.8,91.9,92.9)
            val day = preferences.getTanggalLahir()?.substringBefore("/")?.toInt()
            val month = preferences.getTanggalLahir()?.substringAfter("/")?.substringBefore("/")?.toInt()
            val year = preferences.getTanggalLahir()?.substringAfterLast("/")?.toInt()
            val usia = day?.let { month?.let { it1 -> year?.let { it2 -> getAge(it, it1, it2) } } }!!.toInt()
            val panjang = preferences.getTB()!!.toDouble()

            if(panjang < median[0]){
                val nilaistatus = (panjang - median[usia-1]) / (median[usia-1] - (sd1min[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus < sd2min[usia-1] ){
                    println("Sangat Pendek")
                }else{
                    println("Pendek")
                }
            }else{
                val nilaistatus = (panjang - median[usia-1]) / (sd1plus[usia-1] + (median[usia-1]))
                if (nilaistatus == median[usia-1]){
                    println("Normal")
                }else if (nilaistatus > sd2plus[usia-1] ){
                    println("Sangat Tinggi")
                }else{
                    println("Tinggi")
                }
            }
        }

    }

}




