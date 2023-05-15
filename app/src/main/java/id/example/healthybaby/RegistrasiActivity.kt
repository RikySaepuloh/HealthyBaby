package id.example.healthybaby

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import id.example.healthybaby.databinding.ActivityRegistrasiBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class RegistrasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBinding
    val preferences=Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(this)

        binding.btnBack.setOnClickListener {
            finish()
        }
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -24)
        val minDate = calendar.timeInMillis
        val calendar2 = Calendar.getInstance()
        calendar2.add(Calendar.MONTH, -6)
        val maxDate = calendar2.timeInMillis
        binding.etTglLahir.setOnClickListener {
            val constraintsBuilder = CalendarConstraints.Builder().setStart(minDate).setEnd(maxDate).setValidator(
                DateValidatorPointBackward.now())
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Pilih Tanggal Lahir")
                    .setSelection(calendar2.timeInMillis)
                    .setCalendarConstraints(constraintsBuilder.build())
                    .build()
            datePicker.addOnPositiveButtonClickListener {
                val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                utc.timeInMillis = it
                val format = SimpleDateFormat("dd/MM/yyy")
                val formatted: String = format.format(utc.time)
                binding.etTglLahir.setText(formatted)
            }

            datePicker.show(supportFragmentManager,"tgl_lahir")

        }

        binding.btnSimpan.setOnClickListener {
            val jk = if (binding.rgJk.checkedRadioButtonId == binding.rbLk.id){
                "Laki-Laki"
            }else{
                "Perempuan"
            }
            simpanData(binding.etNik.text.toString(),binding.etNama.text.toString(),binding.etTmpLahir.text.toString(),binding.etTglLahir.text.toString(),jk)
        }


    }

    fun simpanData(nik:String,nama:String,tempat_lahir:String,tgl_lahir:String,jk:String){
        val db = Firebase.firestore
        val medicalHistoryRef = db.collection("bayi")

        val newRecord = hashMapOf(
            "NIK" to nik,
            "nama" to nama,
            "tempat lahir" to tempat_lahir,
            "tanggal lahir" to tgl_lahir,
            "jenis kelamin" to jk
        )



        medicalHistoryRef.add(newRecord)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "Medical record added with ID: ${documentReference.id}")
                Toast.makeText(this,"Registrasi Berhasil!",Toast.LENGTH_LONG).show()
                preferences.saveUserID(documentReference.id)
                preferences.saveNama(nama)
                preferences.saveNIK(nik)
                preferences.saveTempatLahir(tempat_lahir)
                preferences.saveTanggalLahir(tgl_lahir)
                preferences.saveJenisKelamin(jk)
                startActivity(Intent(this,MainActivity::class.java))
                finishAffinity()
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding medical record", e)
            }


    }
}