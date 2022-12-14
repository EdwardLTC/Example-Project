package com.edward.assigment;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.fragment.books.MainBookFragment;
import com.edward.assigment.fragment.admin.ModeratorSystemFragment;
import com.edward.assigment.fragment.oder.OrderFragment;
import com.edward.assigment.fragment.statistical.StatisticalFragment;
import com.edward.assigment.fragment.store.StoreInfoFragment;
import com.edward.assigment.menu.DrawerAdapter;
import com.edward.assigment.menu.DrawerItem;
import com.edward.assigment.menu.SimpleItem;
import com.edward.assigment.menu.SpaceItem;
import com.edward.assigment.modal.Admin;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    private static final int POS_BOOKS = 0;
    private static final int POS_ORDER = 1;
    private static final int POS_MODERATOR = 2;
    private static final int POS_STATISTICAL = 3; //POS_LOGOUT_MOD = 3 for Mod role
    private static final int POS_STORE = 4;
    private static final int POS_LOGOUT = 6;
    private static final int POS_LOGOUT_MOD = 3;

    private static int ROLE;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        admin = (Admin) getIntent().getSerializableExtra("Object");
        ROLE = admin.get_role();


        Toolbar toolbar = findViewById(R.id.toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();
        DrawerAdapter adapter = null;
        if (ROLE == 0) {
            adapter = new DrawerAdapter(Arrays.asList(
                    createItemFor(POS_BOOKS).setChecked(true),
                    createItemFor(POS_ORDER),
                    createItemFor(POS_MODERATOR),
                    createItemFor(POS_STATISTICAL),
                    createItemFor(POS_STORE),
                    new SpaceItem(48),
                    createItemFor(POS_LOGOUT)));

        }
        if (ROLE == 1) {
            adapter = new DrawerAdapter(Arrays.asList(
                    createItemFor(POS_BOOKS).setChecked(true),
                    createItemFor(POS_ORDER),
                    new SpaceItem(48),
                    createItemFor(POS_LOGOUT_MOD)));
        }

        assert adapter != null;
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_BOOKS);

    }

    public void Logout() {
        try {
            if (shp == null)
                shp = getSharedPreferences("myPreferences", MODE_PRIVATE);

            shpEditor = shp.edit();
            shpEditor.putString("Object", "");
            shpEditor.commit();

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onItemSelected(int position) {
        Fragment fragment;
        switch (position) {
            case POS_BOOKS:
                fragment = new MainBookFragment();
                showFragment(fragment);
                slidingRootNav.closeMenu();
                break;
            case POS_MODERATOR:
                fragment = new ModeratorSystemFragment();
                showFragment(fragment);
                slidingRootNav.closeMenu();
                break;
            case POS_ORDER:
                fragment = new OrderFragment(admin.get_id());
                showFragment(fragment);
                slidingRootNav.closeMenu();
                break;
            case POS_STATISTICAL:
                if (ROLE == 1) { //cuz the log out of the mod is the third item in the menu
                    Logout();
                    break;
                }
                fragment = new StatisticalFragment();
                showFragment(fragment);
                slidingRootNav.closeMenu();
                break;
            case POS_STORE:

                fragment = new StoreInfoFragment();
                showFragment(fragment);
                slidingRootNav.closeMenu();
                break;
            case POS_LOGOUT:
                Logout();
                break;
        }

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        String[] arr = null;
        if (ROLE == 0) {
            arr = getResources().getStringArray(R.array.ld_activityScreenTitles);
        }
        if (ROLE == 1) {
            arr = getResources().getStringArray(R.array.ld_ModScreenTitles);
        }
        return arr;
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = null;
        if (ROLE == 0) {
            ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        }
        if (ROLE == 1) {
            ta = getResources().obtainTypedArray(R.array.ld_ModScreenIcons);
        }
        assert ta != null;
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}