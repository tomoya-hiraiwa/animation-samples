/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.activityscenetransitionbasic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.android.activityscenetransitionbasic.DetailActivity
import com.squareup.picasso.Picasso

/**
 * Our main Activity in this sample. Displays a grid of items which an image and title. When the
 * user clicks on an item, [DetailActivity] is launched, using the Activity Scene Transitions
 * framework to animatedly do so.
 */
class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid)

        // Setup the GridView and set the adapter
        val grid = findViewById<GridView>(R.id.grid)
        grid.onItemClickListener = mOnItemClickListener
        val adapter = GridAdapter()
        grid.adapter = adapter
    }

    private val mOnItemClickListener = OnItemClickListener { adapterView, view, position, id ->

        /**
         * Called when an item in the [android.widget.GridView] is clicked. Here will launch
         * the [DetailActivity], using the Scene Transition animation functionality.
         */
        /**
         * Called when an item in the [android.widget.GridView] is clicked. Here will launch
         * the [DetailActivity], using the Scene Transition animation functionality.
         */
        val item = adapterView.getItemAtPosition(position) as Item

        // Construct an Intent as normal
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_PARAM_ID, item.id)

        // BEGIN_INCLUDE(start_activity)
        /*
             * Now create an {@link android.app.ActivityOptions} instance using the
             * {@link ActivityOptionsCompat#makeSceneTransitionAnimation(Activity, Pair[])} factory
             * method.
             */
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@MainActivity,  // Now we provide a list of Pair items which contain the view we can transitioning
            // from, and the name of the view it is transitioning to, in the launched activity
            Pair(
                view.findViewById(R.id.imageview_item),
                DetailActivity.VIEW_NAME_HEADER_IMAGE
            ),
            Pair(
                view.findViewById(R.id.textview_name),
                DetailActivity.VIEW_NAME_HEADER_TITLE
            )
        )

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(this@MainActivity, intent, activityOptions.toBundle())
        // END_INCLUDE(start_activity)
    }

    /**
     * [android.widget.BaseAdapter] which displays items.
     */
    private inner class GridAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return Item.ITEMS.size
        }

        override fun getItem(position: Int): Item {
            return Item.ITEMS[position]
        }

        override fun getItemId(position: Int): Long {
            return getItem(position).id.toLong()
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
            var view = view
            if (view == null) {
                view = layoutInflater.inflate(R.layout.grid_item, viewGroup, false)
            }
            val item = getItem(position)

            // Load the thumbnail image
            val image = view.findViewById<ImageView>(R.id.imageview_item)
            Picasso.with(image.context).load(item.thumbnailUrl).into(image)

            // Set the TextView's contents
            val name = view.findViewById<TextView>(R.id.textview_name)
            name.text = item.name
            return view
        }
    }
}