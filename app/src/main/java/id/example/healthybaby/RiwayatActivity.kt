package id.example.healthybaby

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.example.healthybaby.databinding.ActivityResultBinding
import id.example.healthybaby.databinding.ActivityRiwayatBinding

class RiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatBinding
    var preferences = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)


    }
}