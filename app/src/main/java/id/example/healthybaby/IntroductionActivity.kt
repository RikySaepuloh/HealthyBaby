package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.example.healthybaby.databinding.ActivityIntroductionBinding
import id.example.healthybaby.databinding.ActivityWelcomeBinding

class IntroductionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroductionBinding
    var preferences  = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)

        binding.btnIbu.setOnClickListener {
            preferences.saveLoggedAs("Ibu")
            startActivity(Intent(this,InputNIKActivity::class.java))
        }

        binding.btnNakes.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}