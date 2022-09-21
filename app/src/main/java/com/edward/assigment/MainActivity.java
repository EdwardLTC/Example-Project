package com.edward.assigment;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.edward.assigment.fragment.BooksFragment;
import com.edward.assigment.fragment.ModeratorSystemFragment;
import com.edward.assigment.fragment.StatisticalFragment;
import com.edward.assigment.fragment.StoreInfoFragment;
import com.edward.assigment.menu.DrawerAdapter;
import com.edward.assigment.menu.DrawerItem;
import com.edward.assigment.menu.SimpleItem;
import com.edward.assigment.menu.SpaceItem;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    private static final int POS_BOOKS = 0;
    private static final int POS_MODERATOR= 1;
    private static final int POS_ORDER= 2;
    private static final int POS_STATISTICAL= 3;
    private static final int POS_STORE= 4;
    private static final int POS_LOGOUT = 6;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_BOOKS).setChecked(true),
                createItemFor(POS_MODERATOR),
                createItemFor(POS_ORDER),
                createItemFor(POS_STATISTICAL),
                createItemFor(POS_STORE),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_BOOKS);
    }
    @Override
    public void onItemSelected(int position) {
        Fragment fragment;

        switch (position){
            case POS_BOOKS:
                fragment = new BooksFragment();
                showFragment(fragment);
                slidingRootNav.closeMenu();
                break;
            case POS_MODERATOR:
                fragment = new ModeratorSystemFragment();
                showFragment(fragment);
                slidingRootNav.closeMenu();
                break;
            case POS_STATISTICAL:
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
                finish();
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
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Log.e(String.valueOf(ta.length()), "loadScreenIcons: ");
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