package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.btl.adapter.FragmentAdapter;
import com.example.btl.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity{
    private User user;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
//        int[] imgs = new int[]{R.drawable.cho_toi_xin_1_ve_ve_tuoi_tho, R.drawable.gam_bit_hau_master, R.drawable.ly_tu_trong_bia,
//        R.drawable.quang_trung_compact, R.drawable.thien_su_huyen_quang, R.drawable.tren_duong_bang, R.drawable.truong_sa_ki_vi_va_giao_lao,
//        R.drawable.tuoi_tho_du_doi, R.drawable.tuoi_tre_dang_gia_bao_nhieu, R.drawable.khoa_hoc_quanh_ta_su_song_la_gi};
//        for(int i=0;i<imgs.length;++i){
//            System.out.println(imgs[i] + "\n");
//        }
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNav);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.mCart).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.mOrder).setChecked(true);
                        break;

                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.mProfile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.mHome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mCart:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mOrder:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.mProfile:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    public FragmentAdapter getFragmentAdapter() {
        return fragmentAdapter;
    }

    public void setFragmentAdapter(FragmentAdapter fragmentAdapter) {
        this.fragmentAdapter = fragmentAdapter;
    }
}