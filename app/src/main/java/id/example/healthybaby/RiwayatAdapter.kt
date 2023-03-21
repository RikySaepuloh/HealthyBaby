package id.example.healthybaby

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import id.example.healthybaby.databinding.ListRiwayatBinding
import java.util.*


class RiwayatAdapter : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {
    private var dataArray= mutableListOf<DocumentSnapshot>()
    private val dataTemporary= mutableListOf<DocumentSnapshot>()
    lateinit var context: Context


    fun initData(data: MutableList<DocumentSnapshot>){
        dataArray.clear()
        dataTemporary.clear()
        dataArray.addAll(data)
        dataTemporary.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData(){
        dataArray.clear()
        dataTemporary.clear()
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListRiwayatBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder){
            with(dataArray[position]) {
//                binding.tvKode.text = id
                binding.tvKunjungan.text = "Kunjungan ke-${position + 1}"
                binding.tvTanggal.text = data?.get("date").toString()
                binding.tvTb.text = "Tinggi Badan : ${data?.get("tinggi badan").toString()}"
                binding.tvBb.text = "Berat Badan : ${data?.get("berat badan").toString()}"
                val statusGizi = data?.get("status gizi").toString()
                when (statusGizi) {
                    "Sangat Kurus", "Sangat Obesitas" -> {
                        binding.tvStatusGizi.text = statusGizi
                        binding.tvStatusGizi.setTextColor(ContextCompat.getColor(context,R.color.red))
                    }
                    "Kurus", "Obesitas" -> {
                        binding.tvStatusGizi.text = statusGizi
                        binding.tvStatusGizi.setTextColor(ContextCompat.getColor(context, R.color.yellow))
                    }
                    else -> {
                        binding.tvStatusGizi.text = statusGizi
                        binding.tvStatusGizi.setTextColor(ContextCompat.getColor(context, R.color.green))
                    }
                }

                val statusTinggi = data?.get("status tinggi").toString()
                when (statusTinggi) {
                    "Sangat Pendek", "Sangat Tinggi" -> {
                        binding.tvStatusTinggi.text = statusTinggi
                        binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(context,R.color.red))
                    }
                    "Pendek", "Tinggi" -> {
                        binding.tvStatusTinggi.text = statusTinggi
                        binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(context,R.color.yellow))
                    }
                    else -> {
                        binding.tvStatusTinggi.text = statusTinggi
                        binding.tvStatusTinggi.setTextColor(ContextCompat.getColor(context,R.color.green))
                    }
                }

                binding.root.setOnClickListener {
                    context.startActivity(Intent(context,DetailRiwayatActivity::class.java)
                        .putExtra("id",id)
                        .putExtra("urut","${position+1}")
                    )
                }
            }
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    inner class ViewHolder(val binding: ListRiwayatBinding)
    :RecyclerView.ViewHolder(binding.root)

}