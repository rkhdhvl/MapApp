package com.practice.maps_recyclerview.utils

import android.view.View
import com.practice.maps_recyclerview.model.MapData

class DataSource {
    companion object{

        fun createDataSet(): ArrayList<MapData>{
            val list = ArrayList<MapData>()
            list.add(
                MapData(
                    "Mumbai",
                    19.0760,
                    72.8777,
                    View.generateViewId()
                )
            )
            list.add(
                MapData(
                    "Gateway of India , Mumbai",
                    18.9220,
                    72.8347,
                    View.generateViewId()
                )
            )
            list.add(
                MapData(
                    "Cuffe Parade",
                    18.9127,
                    72.8213,
                    View.generateViewId()
                )
            )
            list.add(
                MapData(
                    "Malabar Hill",
                    18.9548,
                    72.7985,
                    View.generateViewId()
                )
            )
            list.add(
                MapData(
                    "Colaba",
                    18.9067,
                    72.8147,
                    View.generateViewId()
                )
            )
            return list
        }
    }
}