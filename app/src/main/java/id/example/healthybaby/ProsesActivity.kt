package id.example.healthybaby

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jaredrummler.materialspinner.MaterialSpinner
import id.example.healthybaby.databinding.ActivityProsesBinding


class ProsesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProsesBinding
    var frekuensi = ""
    val preferences = Preferences()
    var tekstur=""
    var karbo=""
    var proteinn=""
    var proteinh=""
    var sayur=""
    var lemak=""
    var buah=""
    var takaran=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProsesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(this)


//
//        val spinner1 = binding.etJenis1
//        spinner1.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner1.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis1.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis1.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis1.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis1.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis1.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis1.setItems(buah)
//            }else{
//                binding.etSubjenis1.setItems(karbohidrat)
//            }
//            binding.etSubjenis1.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis1=item1.toString()
//            }
//        }
//
//        val spinner2 = binding.etJenis2
//        spinner2.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner2.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis2.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis2.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis2.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis2.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis2.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis2.setItems(buah)
//            }else{
//                binding.etSubjenis2.setItems(karbohidrat)
//            }
//            binding.etSubjenis2.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis2=item1.toString()
//            }
//        }
//
//        val spinner3 = binding.etJenis3
//        spinner3.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner3.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis3.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis3.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis3.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis3.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis3.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis3.setItems(buah)
//            }else{
//                binding.etSubjenis3.setItems(karbohidrat)
//            }
//            binding.etSubjenis3.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis3=item1.toString()
//            }
//        }
//
//        val spinner4 = binding.etJenis4
//        spinner4.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner4.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis4.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis4.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis4.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis4.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis4.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis4.setItems(buah)
//            }else{
//                binding.etSubjenis4.setItems(karbohidrat)
//            }
//            binding.etSubjenis4.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis4=item1.toString()
//            }
//        }
//
//
//        if (getMonthFromBirthdate(preferences.getTanggalLahir()) < 9 ){
//            binding.etFrekuensi.setItems(frek1)
//        }else{
//            binding.etFrekuensi.setItems(frek2)
//        }
//        binding.etFrekuensi.setOnItemSelectedListener { view, position, id, item ->
//            frekuensi=item.toString()
//        }

        binding.btnSelanjutnya.setOnClickListener {
            frekuensi=binding.etFrekuens.text.toString()
            tekstur=binding.etTekstur.text.toString()
            karbo=binding.etKarbo.text.toString()
            proteinh=binding.etProteinh.text.toString()
            proteinn=binding.etProteinn.text.toString()
            sayur=binding.etSayuran.text.toString()
            lemak=binding.etLemak.text.toString()
            buah=binding.etBuah.text.toString()
            takaran=binding.etTakaran.text.toString()
            if (frekuensi==""||tekstur==""||karbo==""||proteinh==""||proteinn==""||sayur==""||lemak==""||buah==""||takaran==""){
                Snackbar.make(binding.root,"Masih terdapat data yang belum terisi!",Snackbar.LENGTH_LONG).show()
            }else{
                preferences.saveFrekuensi(frekuensi)
                preferences.saveTekstur(tekstur)
                preferences.saveKarbo(karbo)
                preferences.saveProteinh(proteinh)
                preferences.saveProteinn(proteinn)
                preferences.saveSayur(sayur)
                preferences.saveLemak(lemak)
                preferences.saveBuah(buah)
                preferences.saveTakaran(takaran)
                startActivity(Intent(this,ResultActivity::class.java))
            }
        }
//

    }
}