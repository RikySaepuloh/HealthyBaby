package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.example.healthybaby.databinding.ActivityInputNikactivityBinding
import id.example.healthybaby.databinding.ActivityProsesBinding

class ProsesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProsesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProsesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this,ResultActivity::class.java))
        }
    }
}