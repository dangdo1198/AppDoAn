package com.example.expendlistview.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;



import com.example.expendlistview.API.DoanhNghiepHopTacApi;
import com.example.expendlistview.Adapter.CustomExpandableListAdapter;
import com.example.expendlistview.Adapter.DanhSachDoanhNghiepAdapter;


import com.example.expendlistview.MainActivity;
import com.example.expendlistview.MenuObject.GroupObject;
import com.example.expendlistview.MenuObject.ItemObject;


import com.example.expendlistview.Model.DoanhNghiepHopTac;
import com.example.expendlistview.R;


import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachDoanhNghiep extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private String activityTitle;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<GroupObject> mListGroup;
    private Map<GroupObject,List<ItemObject>> mListItem;
    RecyclerView recyclerView;
    List<DoanhNghiepHopTac> listDoanhNghiepHopTac;
    DanhSachDoanhNghiepAdapter danhSachDoanhNghiepAdapter;
    ImageView themDoanhNghiepImg;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_doanh_nghiep);

        recyclerView = findViewById(R.id.recyclerView);

        clickCallApi();



        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        activityTitle = getTitle().toString();
        expandableListView =(ExpandableListView)findViewById(R.id.navList);
        themDoanhNghiepImg = findViewById(R.id.themdoanhnghiep_img);
        themDoanhNghiepImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ThemDoanhNghiep.class);
                startActivity(intent);
            }
        });



        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header,null,false);
        expandableListView.addHeaderView(listHeaderView);
        mListItem = getmListItem();
        mListGroup = new ArrayList<>(mListItem.keySet());


        addDrawersItem();
        setUpDrawer();
        if(savedInstanceState==null)
            selectFirstItemAsDefault();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
    }




    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault() {
//        if(navigationManager!=null){
//            String firstItem = String.valueOf(mListGroup.get(0));
//            navigationManager.showFragment(firstItem);
//            getSupportActionBar().setTitle(firstItem);
//        }
    }

    private void setUpDrawer() {
        drawerToggle =  new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(activityTitle);
                invalidateOptionsMenu();
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void addDrawersItem() {
        adapter = new CustomExpandableListAdapter(this,mListGroup,mListItem);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(mListGroup.get(groupPosition).toString());
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("Trở Về");
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if(groupPosition==0)
                {
                    if(childPosition == 0){
                        Intent child01Intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(child01Intent);}

                }
                if(groupPosition==3)
                {
                    if(childPosition == 0){
                        Intent child01Intent = new Intent(getBaseContext(), QuanLyTaiLieu.class);
                        startActivity(child01Intent);
                    }
                    if(childPosition == 2){
                        Intent child02Intent = new Intent(getBaseContext(), TaiLieuNghienCuuActivity.class);
                        startActivity(child02Intent);
                    }
                    if(childPosition == 3){
                        Intent child03Intent = new Intent(getBaseContext(), ActitityDemo.class);
                        startActivity(child03Intent);}
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    private  Map<GroupObject,List<ItemObject>> getmListItem(){
        Map<GroupObject,List<ItemObject>> listMap = new LinkedHashMap<>();
        GroupObject groupObject1 = new GroupObject(1,"Trang chủ");
        GroupObject groupObject2 = new GroupObject(2,"Quản lý hệ thống");
        GroupObject groupObject3 = new GroupObject(3,"Cập nhật tài khoản");
        GroupObject groupObject4 = new GroupObject(4,"Nghiên cứu khoa học");
        GroupObject groupObject5 = new GroupObject(5,"Hợp tác doanh nghiệp");

        List<ItemObject> itemObject1 = new ArrayList<>();
        itemObject1.add(new ItemObject(0,"Giới thiệu"));
        List<ItemObject> itemObject2 = new ArrayList<>();
        itemObject2.add(new ItemObject(0,"Quản lý người dùng"));

        List<ItemObject> itemObject3 = new ArrayList<>();
        itemObject3.add(new ItemObject(0,"Chỉnh sửa hồ sơ"));
        itemObject3.add(new ItemObject(1,"Thay đổi mật khẩu"));


        List<ItemObject> itemObject4 = new ArrayList<>();
        itemObject4.add(new ItemObject(0,"Quản lý tài liệu"));
        itemObject4.add(new ItemObject(1,"Quản lý đề tài"));
        itemObject4.add(new ItemObject(2,"Tài liệu nghiên cứu"));
        itemObject4.add(new ItemObject(3,"Đăng ký đề tài"));
        itemObject4.add(new ItemObject(4,"Đề tài đã đăng ký"));
        itemObject4.add(new ItemObject(5,"Đề tài được duyệt"));
        itemObject4.add(new ItemObject(6,"Đề tài bị hủy"));

        List<ItemObject> itemObject5 = new ArrayList<>();
        itemObject5.add(new ItemObject(0,"Danh sách doanh nghiệp"));
        itemObject5.add(new ItemObject(1,"Quản lý đề xuất chương chương trình liên kết"));
        itemObject5.add(new ItemObject(2,"Đề xuất chương trình liên kết"));
        itemObject5.add(new ItemObject(2,"Đăng tin tuyển dụng"));



        listMap.put(groupObject1, itemObject1);
        listMap.put(groupObject2,itemObject2);
        listMap.put(groupObject3,itemObject3);
        listMap.put(groupObject4,itemObject4);
        listMap.put(groupObject5,itemObject5);


        return listMap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "Search button selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(this, "About button selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(this, "Help button selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(this, "Bạn đã nhấn vào ô cài đặt", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Toast.makeText(this, "Bạn đã nhấn vào ô đăng xuất", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);





    }


    public void clickCallApi() {
        DoanhNghiepHopTacApi.doanhNghiepHopTacApi.allDoanhNghiep().enqueue(new Callback<List<DoanhNghiepHopTac>>() {
            @Override
            public void onResponse(Call<List<DoanhNghiepHopTac>> call, Response<List<DoanhNghiepHopTac>> response) {
                listDoanhNghiepHopTac = response.body();
                danhSachDoanhNghiepAdapter = new DanhSachDoanhNghiepAdapter(DanhSachDoanhNghiep.this,DanhSachDoanhNghiep.this,listDoanhNghiepHopTac);
                recyclerView.setAdapter(danhSachDoanhNghiepAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachDoanhNghiep.this));
            }

            @Override
            public void onFailure(Call<List<DoanhNghiepHopTac>> call, Throwable t) {

            }
        });


    }


}