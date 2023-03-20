package id.example.healthybaby

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import id.example.healthybaby.databinding.ActivityPengukuranBinding
import id.example.healthybaby.databinding.ActivityRegistrasiBinding

class PengukuranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengukuranBinding
    private lateinit var database: DatabaseReference
    var preferences  = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengukuranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference
        preferences.setPreferences(applicationContext)

        getData()

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this,ResultActivity::class.java))
//            startActivity(Intent(this,ProsesActivity::class.java))
        }

        binding.btnUlangi.setOnClickListener {
            getData()
            Snackbar.make(binding.root,"Data berhasil diperbarui",Snackbar.LENGTH_LONG).show()
        }
    }

    @IgnoreExtraProperties
    data class Sensor(
        var Tinggi: Long?=0,
        var Berat: Long?=0
    ){
        @Exclude
        fun toMap():Map<String, Long?>{
            return mapOf(
                "Tinggi" to Tinggi,
                "Berat" to Berat
            )
        }
    }

    fun getData(){

//        val database = Firebase.database
//        val myRef = database.getReference("Sensor")
        binding.loading.visibility= View.VISIBLE
        database.child("Sensor").get().addOnSuccessListener {
            it.child("Berat").getValue(Double::class.java)
            binding.etTb.setText(it.child("Tinggi").getValue(Double::class.java).toString())
            binding.etBb.setText(it.child("Berat").getValue(Double::class.java).toString())
//            binding.etBb.setText(it.getValue(Sensor::class.java)?.Berat.toString())
            preferences.saveTB(it.getValue(Sensor::class.java)?.Tinggi.toString())
            preferences.saveBB(it.getValue(Sensor::class.java)?.Berat.toString())
            binding.tvTitle.text = "PENGUKURAN BERHASIL"
            Log.d(TAG, "Value is: ${it.value}")
            binding.loading.visibility= View.GONE
        }.addOnFailureListener {
            Log.d(TAG, "Error getting data", it)
            binding.loading.visibility= View.GONE
        }

        // Read from the database
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
////                val value = dataSnapshot.getValue<String>()
////                binding.etTb.setText(value)
////                binding.etBb.setText(value)
//
//                Log.d(TAG, "Value is: $dataSnapshot")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
    }
}