package com.example.bluemelodia.connect3;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.View;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bluemelodia on 1/4/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private static final int EMPTY = 0;
    private static final int SQUIRTLE = 1;
    private static final int CHARMANDER = 2;
    private List<Integer> tileStates = new ArrayList<Integer>();

    private Map<Integer, Integer> tileStateResources = new HashMap<Integer, Integer>();

    public ImageAdapter(Context c) {
        mContext = c;
        // Map each state to its graphics.
        tileStateResources.put(EMPTY, R.drawable.pokemonicon);
        tileStateResources.put(SQUIRTLE, R.drawable.squirtle);
        tileStateResources.put(CHARMANDER, R.drawable.charmander);

        // Each tile starts out as white.
        for(int i = 0; i < 9; i++) {
            tileStates.add(EMPTY);
        }
    }

    public int getCount() {
        return 9;
    }

    // return the actual object at the specified position in the adapter
    public Object getItem(int position) {
        return null;
    }

    // get the current state of the tile
    public int getTileState(int position) {
        return tileStates.get(position);
    }

    // change the state of the tile
    public void setTileState(int position, int newState) {
        tileStates.set(position, newState);
        for (int i = 0; i < 9; i++) {
            Log.i("States:", String.valueOf(i) + " " + String.valueOf(tileStates.get(i)));
        }
    }

    // get tile state resource
    public int getStateResource(int state) {
        return tileStateResources.get(state);
    }

    // return the row id of the item
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAlpha(0.2f);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        // use the current state of the tile to pick which image goes into it
        int state = tileStates.get(position);
        Log.i("Changing tile to state:", String.valueOf(state));
        if (state == 0) {
            Log.i("EMPTY", String.valueOf(state));
            imageView.setImageResource(R.drawable.pokemonicon);
        } else if (state == 1) {
            Log.i("SQUIRTLE", String.valueOf(state));
            imageView.setImageResource(R.drawable.squirtle);
        } else if (state == 2) {
            Log.i("CHARMANDER", String.valueOf(state));
            imageView.setImageResource(R.drawable.charmander);
        }
        return imageView;
    }
}
