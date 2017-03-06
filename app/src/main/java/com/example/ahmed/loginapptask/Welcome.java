package com.example.ahmed.loginapptask;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity implements AdapterView.OnItemClickListener{

    TextView tv_name;
    ListView list;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    String[] data;
    public static String name;
  //  MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    //    MainActivity.count =0;

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        list = (ListView) findViewById(R.id.list);
        tv_name = (TextView) findViewById(R.id.tv_welcome);



        Intent i = getIntent();
         name = i.getStringExtra("username");
        tv_name.setText("Welcome Mr "+name);


        data = getResources().getStringArray(R.array.profile);
        ArrayAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1, data);
      //  adapter = new MyAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        getSupportActionBar().setTitle("Menu");
        actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawer , R.string.drawerOpen ,R.string.drawerclosed)
            {
                @Override
                public void onDrawerOpened(View drawerView) {
                    getSupportActionBar().setTitle("Menu");
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                   getSupportActionBar().setTitle("Menu");
                }
            };
        drawer.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }



    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }



    // when click on the home button the drawer open then closed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectionItem (position );
        selectItemFromDrawer(position);

    }
    public void selectionItem(int position ) {
        list.setItemChecked(position , true);
        setTitle(data[position]);
    }
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }



    public void selectItemFromDrawer(int position ) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (position == 0)
        {
            transaction.replace(R.id.main_content , new Fragment_Picture()).commit();
        }
        else  if (position == 1)
        {
            transaction.replace(R.id.main_content , new Fragment_profile()).commit();
        }
        else  if (position == 2)
        {
            transaction.replace(R.id.main_content , new Fragment_setting()).commit();
        }
        list.setItemChecked(position , true);
        drawer.closeDrawer(list);
    }



    public class MyAdapter extends BaseAdapter
    {
        Context con;
        String [] data = {};
        int[] images = {R.mipmap.pic , R.mipmap.profile , R.mipmap.setting };
        public MyAdapter(Context con) {
            this.con = con;
           data = con.getResources().getStringArray(R.array.profile);
        }
        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v ;
            if (convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater)con.getSystemService(LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.custom_row_welcome , parent , false);
            }
            else {
                v = convertView;
            }
                TextView tv = (TextView) findViewById(R.id.textView_welcome);
                ImageView img = (ImageView) findViewById(R.id.imageView);
                tv.setText(data[position]);
                img.setImageResource(images[position]);

            return v;
        }
    }




}
