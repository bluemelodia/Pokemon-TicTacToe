package com.example.bluemelodia.connect3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 0 = Squirtle, 1 = Charmander
    private static int turn = 0;
    private static ImageAdapter adapter;
    private static int squirtleScore = 0;
    private static int charmanderScore = 0;

    // change all the tiles back to the pokeball
    public void resetBoard(View view) {
        GridView gridView =  (GridView) findViewById(R.id.gridView);
        for (int i = 0; i < 9; i++) {
            View child = gridView.getChildAt(i);
            ImageView viewToChange = (ImageView) child;
            Log.i("Grid: ", String.valueOf(viewToChange));
            if (viewToChange != null) {
                viewToChange.setAlpha(0.2f);
                adapter.setTileState(i, 0);
                viewToChange.setImageResource(R.drawable.pokemonicon);
            }
        }
        Toast.makeText(MainActivity.this, "Reset the board.", Toast.LENGTH_SHORT).show();
    }

    /*private void dropSquirtle(int position) {
        adapter.setTileState(position, 1);
        //adapter.getView(position, null, null);
    }

    private void dropCharmander(int position) {
        adapter.setTileState(position, 2);
        //adapter.getView(position, null, null);
    }*/

    private int checkWin() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final GridView gridView = (GridView) findViewById(R.id.gridView);
        // set a custom adapter (ImageAdapter) as the source for all items to be displayed in the grid
        adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);

        /* to do something when an item in the grid is clicked, the setOnItemClickListener() method
            is passed a new AdapterView.OnItemClickListener. This anonymous instance defines the
            onItemClick() callback method to show a Toast displaying the index position of the selected
            item (in a real world scenario, the position could be used to get the full-sized image for
            some other task) */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // user can only drop a squirtle or charmander on an empty tile
                if (adapter.getTileState(position) == 0) {
                    Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
                    if (turn == 0) { // Squirtle
                        adapter.setTileState(position, 1);
                        int resID = adapter.getStateResource(1);
                        ImageView viewToChange = (ImageView) view;
                        viewToChange.setAlpha(1.0f);
                        viewToChange.setImageResource(R.drawable.squirtle);
                        turn = 1; // Charmander's turn
                    } else { // Charmander
                        adapter.setTileState(position, 2);
                        int resID = adapter.getStateResource(2);
                        ImageView viewToChange = (ImageView) view;
                        viewToChange.setAlpha(1.0f);
                        viewToChange.setImageResource(R.drawable.charmander);
                        turn = 0; // Squirtle's turn
                    }

                    TextView turnLabel = (TextView) findViewById(R.id.whoseTurn);
                    TextView idLabel = (TextView) findViewById(R.id.you);

                    // Change the label for the next person's turn
                    if (turn == 1) {
                        turnLabel.setText("It is Squirtle's turn");
                        idLabel.setText("You are Squirtle.");
                    } else {
                        turnLabel.setText("It is Charmander's turn");
                        idLabel.setText("You are Charmander.");
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
