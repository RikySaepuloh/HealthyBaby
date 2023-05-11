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
    var preferences = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengukuranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference
        preferences.setPreferences(applicationContext)

        binding.btnSelanjutnya.setOnClickListener {
            preferences.saveBB(binding.etBb.text.toString())
            preferences.saveTB(binding.etTb.text.toString())
            startActivity(Intent(this, ProsesActivity::class.java))
        }

        binding.btnCekBb.setOnClickListener {
            getBerat()
        }

        binding.btnCekTb.setOnClickListener {
            getTinggi()
        }

    }

    @IgnoreExtraProperties
    data class Sensor(
        var Tinggi: Long? = 0,
        var Berat: Long? = 0
    ) {
        @Exclude
        fun toMap(): Map<String, Long?> {
            return mapOf(
                "Tinggi" to Tinggi,
                "Berat" to Berat
            )
        }
    }

    fun getBerat() {
        binding.tvTitle.text = "MOHON TUNGGU"
        binding.loading.visibility = View.VISIBLE
        database.child("Sensor").get().addOnSuccessListener {
            val bb = it.child("Berat").getValue(Double::class.java).toString()
            try {
                binding.etBb.setText(
                    bb.substringBefore('.') + "." + bb.substringAfter('.').substring(0, 2)
                )
            } catch (e: Exception) {
                binding.etBb.setText(bb)
            }
            binding.tvTitle.text = "PENGUKURAN BERHASIL"
            binding.loading.visibility = View.GONE
            Snackbar.make(binding.root, "Data berhasil diperbarui", Snackbar.LENGTH_LONG).show()
        }.addOnFailureListener {
            Log.d(TAG, "Error getting data", it)
            binding.loading.visibility = View.GONE
        }
    }

    fun getTinggi() {
        binding.tvTitle.text = "MOHON TUNGGU"
        binding.loading.visibility = View.VISIBLE
        database.child("Sensor").get().addOnSuccessListener {
            val tb = it.child("Tinggi").getValue(Double::class.java).toString()
            try {
                binding.etTb.setText(
                    tb.substringBefore('.') + "." + tb.substringAfter('.').substring(0, 2)
                )
            } catch (e: Exception) {
                binding.etTb.setText(tb)
            }
            binding.tvTitle.text = "PENGUKURAN BERHASIL"
            binding.loading.visibility = View.GONE
            Snackbar.make(binding.root, "Data berhasil diperbarui", Snackbar.LENGTH_LONG).show()
        }.addOnFailureListener {
            Log.d(TAG, "Error getting data", it)
            binding.loading.visibility = View.GONE
        }
    }
}