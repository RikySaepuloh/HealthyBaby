package id.example.healthybaby

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.highsoft.highcharts.common.hichartsclasses.*
import id.example.healthybaby.databinding.ActivityRiwayatBinding


class RiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatBinding
    var preferences = Preferences()
    val myadapter = RiwayatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)

        binding.hc.setWillNotDraw(true)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvRiwayat.apply {
            layoutManager = LinearLayoutManager(this@RiwayatActivity)
            adapter = myadapter
        }

        getRiwayat()
    }

    fun getRiwayat(){
        val db = FirebaseFirestore.getInstance()
//        val usersRef = db.collection("bayi")
        val userId=preferences.getUserID()
        val medicalHistoryRef = userId?.let { db.collection("bayi").document(it).collection("medicalHistory") }

//        val query = usersRef.whereEqualTo("email", email).limit(1)

        val options = HIOptions()

        val title = HITitle()
        title.text = ""
        options.title = title

        val subtitle = HISubtitle()
        subtitle.text = "Riwayat Bayi"
        options.subtitle = subtitle

        val yaxis = HIYAxis()
        yaxis.title = HITitle()
        yaxis.title.text = ""
        options.yAxis = arrayListOf(yaxis)

        val legend = HILegend()
        legend.layout = "vertical"
        legend.align = "right"
        legend.verticalAlign = "middle"
        options.legend = legend

        val plotoptions = HIPlotOptions()
        plotoptions.series = HISeries()
        plotoptions.series.label = HILabel()
        plotoptions.series.label.connectorAllowed = false
//        plotoptions.series.pointStart = 2010
        options.plotOptions = plotoptions


        val tinggibadan = arrayListOf<Double>()
        val beratbadan = arrayListOf<Double>()
        val nilaitinggi = arrayListOf<Double>()
        val nilaigizi = arrayListOf<Double>()

        medicalHistoryRef?.get()!!.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                val mydata = querySnapshot.documents
                for (i in 0 until mydata.size){
                    mydata[i].getDouble("tinggi badan")?.let { tinggibadan.add(it) }
                    mydata[i].getDouble("berat badan")?.let { beratbadan.add(it) }
                    mydata[i].getDouble("nilai gizi")?.let { nilaigizi.add(it) }
                    mydata[i].getDouble("nilai tinggi")?.let { nilaitinggi.add(it) }
                }
                Log.d("myhealthy", mydata.toString())
                myadapter.initData(mydata)
//                mydata
//                if (querySnapshot.isEmpty) {
//                    Toast.makeText(this,"Akun tidak ditemukan!", Toast.LENGTH_LONG).show()
//                } else {
//                    // Retrieve user document and compare password
//                    val userDoc = querySnapshot.documents[0]
//                    val user = userDoc.data
//                    if (user?.get("password") == enteredPassword) {
//                        preferences.saveUserID(userDoc.id)
//                        preferences.saveLogStatus(true)
//                        preferences.saveNamaNakes(user["nama"] as String?)
//
//                        startActivity(Intent(this,InputNIKActivity::class.java))
//                        finishAffinity()
//                        // Passwords match, authenticate user
//                    } else {
//                        // Passwords do not match, reject login attempt
//                        Toast.makeText(this,"Username atau Password anda salah!", Toast.LENGTH_LONG).show()
//                    }
//                }
            } else {
                Log.e("LoginActivity", "Error retrieving user document:", task.exception)
            }
        }

        val line1 = HILine()
        line1.name = "Tinggi Badan"
        line1.data = arrayListOf(10,20,30,40.5,50.5,60.2)

        val line2 = HILine()
        line2.name = "Berat Badan"
        line2.data =beratbadan

        val line3 = HILine()
        line3.name = "Nilai Gizi"
        line3.data =nilaigizi

        val line4 = HILine()
        line4.name = "Nilai Tinggi"
        line4.data = nilaitinggi

        val responsive = HIResponsive()

        val rules1 = HIRules()
        rules1.condition = HICondition()
        rules1.condition.maxWidth = 500
        val chartLegend: HashMap<String, HashMap<String,String>> = HashMap()
        val legendOptions: HashMap<String, String> = HashMap()
        legendOptions["layout"] = "horizontal"
        legendOptions["align"] = "center"
        legendOptions["verticalAlign"] = "bottom"
        chartLegend["legend"] = legendOptions
        rules1.chartOptions = chartLegend
        responsive.rules = arrayListOf(rules1)
        options.responsive = responsive
        options.credits = HICredits().apply {
            enabled = false
        }
        options.exporting = HIExporting().apply {
            enabled = false
        }
        options.series = arrayListOf(line1, line2, line3, line4)

        binding.hc.options = options

        binding.hc.setWillNotDraw(false)
    }

}