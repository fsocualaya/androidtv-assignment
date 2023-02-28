package com.fsocualaya.androidtv_assignment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ResultsFragment : Fragment() {
    private var pageIterator:Int = 1
    private var resultPhotos: ArrayList<Photo> = arrayListOf()
    private var recyclerView: RecyclerView? = null
    private var currentQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.results_recycler)
        recyclerView?.layoutManager = GridLayoutManager(activity, NUM_COLS)
        recyclerView?.adapter = PhotoCardsAdapter(requireContext())

        return view
    }

    inner class PhotoCardsAdapter(private val context: Context):RecyclerView.Adapter<PhotoCardsAdapter.PhotoCardsViewHolder>(){
        inner class PhotoCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val titleTV: TextView = itemView.findViewById(R.id.photo_title)
            val detailsTV: TextView = itemView.findViewById(R.id.photo_details)
            val photoIV: ImageView = itemView.findViewById(R.id.photo_image)
            init {
                photoIV.setImageResource(R.drawable.default_background)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoCardsViewHolder {
            val photoCard = LayoutInflater.from(parent.context).inflate(R.layout.photo_card, parent, false)
            photoCard.isFocusable = true
            photoCard.isFocusableInTouchMode = true
            return PhotoCardsViewHolder(photoCard)
        }

        override fun getItemCount(): Int {
            return resultPhotos.size
        }

        override fun onBindViewHolder(holder: PhotoCardsViewHolder, position: Int) {
            val currentPhoto = resultPhotos[position]
            holder.photoIV.setImageResource(R.drawable.movie)
            holder.titleTV.text = currentPhoto.title
            holder.detailsTV.text = "${currentPhoto.author} / ${currentPhoto.genDate()}"
            Glide.with(context)
                .load(currentPhoto.staticURL)
                .error(R.drawable.default_background)
                .into(holder.photoIV)
        }
    }

    fun reloadPhotos(photos:ArrayList<Photo>){
        resultPhotos.clear()
        resultPhotos.addAll(photos)
        pageIterator = 1
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun addPhotos(){
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    companion object{
        private val NUM_COLS = 3
    }
}