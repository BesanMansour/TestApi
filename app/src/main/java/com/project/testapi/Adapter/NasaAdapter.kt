package com.project.testapi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.testapi.Model.Nasa
import com.project.testapi.databinding.ItemNasaBinding

class NasaAdapter(
    private val nasaList: List<Nasa>,
    private val context: Context
) : RecyclerView.Adapter<NasaAdapter.NasaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaHolder {
        val binding = ItemNasaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NasaHolder(binding)
    }

    override fun onBindViewHolder(holder: NasaHolder, position: Int) {
        val nasaItem = nasaList[position]
//        holder.bind(nasaItem)

        if (nasaItem.url != null && nasaItem.title != null && nasaItem.des != null && nasaItem.name != null && nasaItem.date != null) {
            holder.title.text = nasaItem.title
            holder.des.text = nasaItem.des
            holder.name.text = nasaItem.name
            holder.date.text = nasaItem.date
            Glide.with(context)
                .load(nasaItem.url)
                .into(holder.image)
        }else{

        }
    }

    override fun getItemCount(): Int {
        return nasaList.size
    }

    inner class NasaHolder(private val binding: ItemNasaBinding) :
        RecyclerView.ViewHolder(binding.root) {
            val title = binding.ItemTitle
            val image = binding.ItemImg
            val des = binding.ItemDes
            val date = binding.ItemDate
            val name = binding.ItemName
    }
}
//fun bind(nasaItem: Nasa) {
//    binding.apply {
//        // هنا يجب عرض البيانات من الكائن Nasa على واجهة المستخدم
//        // على سبيل المثال:
//        titleTextView.text = nasaItem.title
//        explanationTextView.text = nasaItem.explanation
//        // يمكنك استخدام Glide لعرض الصورة من العنوان الموجود في nasaItem.hdUrl
//        Glide.with(context)
//            .load(nasaItem.hdUrl)
//            .into(imageView)
//    }
//}
