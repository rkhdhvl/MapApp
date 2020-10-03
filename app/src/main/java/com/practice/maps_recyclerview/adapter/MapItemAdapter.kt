package com.practice.maps_recyclerview.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.generateViewId
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.practice.maps_recyclerview.R
import com.practice.maps_recyclerview.model.MapData
import kotlinx.android.synthetic.main.map_item_layout.view.*


class MapItemAdapter() : RecyclerView.Adapter<MapItemAdapter.PlaceViewHolder>() {

    private val TAG: String = "AppDebug"
    private var context: Context? = null
    private var items: List<MapData> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        // create a new view
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.map_item_layout, parent, false)
        val vh = PlaceViewHolder(v)
        // set the Context here
        context = parent.context
        return vh

    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val item = items.get(position)
        holder.item = item
    }

    override fun onViewAttachedToWindow(holder: PlaceViewHolder) {
        super.onViewAttachedToWindow(holder)
        context?.let {
            holder.item?.let { mapData ->
                holder.bind(mapData, it,
                    OnMapReadyCallback {
                        val latLng: LatLng = LatLng(mapData.latitude, mapData.longitude)
                        it.addMarker(MarkerOptions().position(latLng))
                        it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f))
                    })
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: PlaceViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder.item != null) {
            context?.let { holder.removeMapFragment(it) }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(placeList: List<MapData>) {
        items = placeList
    }

    class PlaceViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: MapData? = null
        val place = itemView.place_name
        val view = itemView.place_map
        private lateinit var supportMapFragment: SupportMapFragment

        fun removeMapFragment(mContext: Context) {
            mContext.let {
                val fragmentManager: FragmentManager =
                    (it as AppCompatActivity).supportFragmentManager
                fragmentManager.beginTransaction().remove(supportMapFragment)
                    .commitAllowingStateLoss()
            }
        }


        fun bind(dummyData: MapData, mContext: Context, callback: OnMapReadyCallback) {
            mContext.let {
                place.text = dummyData.placeName
                val frame = FrameLayout(it)
                frame.setId(generateViewId()); //you have to set unique id
                val height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    200f,
                    it.resources.displayMetrics
                ).toInt()
                val layoutParams =
                    FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height)
                frame.layoutParams = layoutParams
                view.addView(frame)
                view.controlTouchEvent(false)

                val options = GoogleMapOptions()
                options.zoomGesturesEnabled(false)
                options.scrollGesturesEnabled(false)
                options.scrollGesturesEnabledDuringRotateOrZoom(false)

                val fm: FragmentManager = (it as AppCompatActivity).supportFragmentManager
                supportMapFragment = SupportMapFragment.newInstance(options)
                supportMapFragment.getMapAsync(callback)
                fm.beginTransaction().replace(frame.getId(), supportMapFragment).commit()

            }
        }
    }


}