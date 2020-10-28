package com.once.test.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.once.test.R;
import com.once.test.adapter.PersonAdapter;
import com.once.test.mode.Person;

import java.util.ArrayList;
import java.util.List;

public class TestDrawLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PersonAdapter personAdapter;

    private List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_draw_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPerson();

        drawerLayout = findViewById(R.id.draw_layout);
        navigationView = findViewById(R.id.nav_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navigationView.setCheckedItem(R.id.nav_favourite);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //drawerLayout.closeDrawers();
                return true;
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.float_action_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v,"Click float action button",Snackbar.LENGTH_SHORT).show();
                Snackbar.make(v,"click float btn",Snackbar.LENGTH_SHORT).setAction("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TestDrawLayoutActivity.this,"click cancel btn",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPerson();
                personAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        personAdapter = new PersonAdapter(personList);
        recyclerView.setAdapter(personAdapter);
    }

    private void initPerson(){
        personList.clear();
        personList.add(new Person("焰灵姬",R.drawable.q2));
        personList.add(new Person("田言",R.drawable.q6));
        personList.add(new Person("潮女妖",R.drawable.q7));
        personList.add(new Person("潮女妖",R.drawable.q10));
        personList.add(new Person("弄玉",R.drawable.q11));
        personList.add(new Person("焱妃",R.drawable.q12));
        personList.add(new Person("少司命",R.drawable.q16));
        personList.add(new Person("雪女",R.drawable.q19));
        personList.add(new Person("焰灵姬",R.drawable.q24));
        personList.add(new Person("晓梦",R.drawable.q28));
        personList.add(new Person("紫女",R.drawable.zi3));
        personList.add(new Person("紫女",R.drawable.zi5));
    }

    private void refreshPerson(){
        personList.clear();
        personList.add(new Person("雪女",R.drawable.q19));
        personList.add(new Person("焰灵姬",R.drawable.q24));
        personList.add(new Person("晓梦",R.drawable.q28));
        personList.add(new Person("紫女",R.drawable.zi3));
        personList.add(new Person("紫女",R.drawable.zi5));
        personList.add(new Person("焰灵姬",R.drawable.q2));
        personList.add(new Person("田言",R.drawable.q6));
        personList.add(new Person("潮女妖",R.drawable.q7));
        personList.add(new Person("潮女妖",R.drawable.q10));
        personList.add(new Person("弄玉",R.drawable.q11));
        personList.add(new Person("焱妃",R.drawable.q12));
        personList.add(new Person("少司命",R.drawable.q16));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                Toast.makeText(this,"Click home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                Toast.makeText(this,"Click setting",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(this,"Click share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_data:
                Toast.makeText(this,"Click data",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}