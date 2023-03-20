package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.example.healthybaby.databinding.ActivityResultBinding
import id.example.healthybaby.databinding.ActivitySaveBinding

class SaveActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveBinding
    var statusGizi=""
    var nilaiGizi=""
    var nilaiTinggi=""
    var statusTinggi=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finishAffinity()
    }
}