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

import com.example.expendlistview.API.ChuongTrinhLienKetApi;

import com.example.expendlistview.Adapter.CustomExpandableListAdapter;

import com.example.expendlistview.Adapter.QuanLyChuongTrinhLienKetAdapter;
import com.example.expendlistview.MainActivity;
import com.example.expendlistview.MenuObject.GroupObject;
import com.example.expendlistview.MenuObject.ItemObject;
import com.example.expendlistview.Model.CTLKDoanhNghiep;

import com.example.expendlistview.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyChuongTrinhLienKet  extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private String activityTitle;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<GroupObject> mListGroup;
    private Map<GroupObject,List<ItemObject>> mListItem;
    RecyclerView recyclerView;
    List<CTLKDoanhNghiep> listCtlkDoanhNghiep;
    QuanLyChuongTrinhLienKetAdapter quanLyChuongTrinhLienKetAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_chuong_trinh_lien_ket);

        recyclerView = findViewById(R.id.recyclerView);

        clickCallApi();



        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        activityTitle = getTitle().toString();
        expandableListView =(ExpandableListView)findViewById(R.id.navList);




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
                getSupportActionBar().setTitle("Tr??? V???");
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
                    if(childPosition == 1){
                        Intent child04Intent = new Intent(getBaseContext(), PheDuyetDeTai.class);
                        startActivity(child04Intent);
                    }
                    if(childPosition == 2){
                        Intent child02Intent = new Intent(getBaseContext(), TaiLieuNghienCuuActivity.class);
                        startActivity(child02Intent);
                    }
                    if(childPosition == 3){
                        Intent child03Intent = new Intent(getBaseContext(), DangKyDeTai.class);
                        startActivity(child03Intent);}
                    if(childPosition == 4){
                        Intent child05Intent = new Intent(getBaseContext(), DeTaiCuaToi.class);
                        startActivity(child05Intent);}
                }
                if(groupPosition==4){

                    if(childPosition == 0){
                        Intent child40Intent = new Intent(getBaseContext(), DanhSachDoanhNghiep.class);
                        startActivity(child40Intent);
                    }
                    if(childPosition == 1){
                        Intent child41Intent = new Intent(getBaseContext(), QuanLyChuongTrinhLienKet.class);
                        startActivity(child41Intent);
                    }
                    if(childPosition == 2){
                        Intent child42Intent = new Intent(getBaseContext(), FormDeXuatChuongTrinhLienKet.class);
                        startActivity(child42Intent);
                    }
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    private  Map<GroupObject,List<ItemObject>> getmListItem(){
        Map<GroupObject,List<ItemObject>> listMap = new LinkedHashMap<>();
        GroupObject groupObject1 = new GroupObject(1,"Trang ch???");
        GroupObject groupObject2 = new GroupObject(2,"Qu???n l?? h??? th???ng");
        GroupObject groupObject3 = new GroupObject(3,"C???p nh???t t??i kho???n");
        GroupObject groupObject4 = new GroupObject(4,"Nghi??n c???u khoa h???c");
        GroupObject groupObject5 = new GroupObject(5,"H???p t??c doanh nghi???p");

        List<ItemObject> itemObject1 = new ArrayList<>();
        itemObject1.add(new ItemObject(0,"Gi???i thi???u"));
        List<ItemObject> itemObject2 = new ArrayList<>();
        itemObject2.add(new ItemObject(0,"Qu???n l?? ng?????i d??ng"));

        List<ItemObject> itemObject3 = new ArrayList<>();
        itemObject3.add(new ItemObject(0,"Ch???nh s???a h??? s??"));
        itemObject3.add(new ItemObject(1,"Thay ?????i m???t kh???u"));


        List<ItemObject> itemObject4 = new ArrayList<>();
        itemObject4.add(new ItemObject(0,"Qu???n l?? t??i li???u"));
        itemObject4.add(new ItemObject(1,"Qu???n l?? ????? t??i"));
        itemObject4.add(new ItemObject(2,"T??i li???u nghi??n c???u"));
        itemObject4.add(new ItemObject(3,"????ng k?? ????? t??i"));
        itemObject4.add(new ItemObject(4,"????? t??i c???a t??i"));


        List<ItemObject> itemObject5 = new ArrayList<>();
        itemObject5.add(new ItemObject(0,"Danh s??ch doanh nghi???p"));
        itemObject5.add(new ItemObject(1,"Qu???n l?? ????? xu???t CTLKDN"));
        itemObject5.add(new ItemObject(2,"????? xu???t CTLKDN"));
        itemObject5.add(new ItemObject(3,"????ng tin tuy???n d???ng"));



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
                Toast.makeText(this, "B???n ???? nh???n v??o ?? c??i ?????t", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Toast.makeText(this, "B???n ???? nh???n v??o ?? ????ng xu???t", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);





    }


    public void clickCallApi() {
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.allDeXuat().enqueue(new Callback<List<CTLKDoanhNghiep>>() {
            @Override
            public void onResponse(Call<List<CTLKDoanhNghiep>> call, Response<List<CTLKDoanhNghiep>> response) {
                listCtlkDoanhNghiep = response.body();
                quanLyChuongTrinhLienKetAdapter = new QuanLyChuongTrinhLienKetAdapter(QuanLyChuongTrinhLienKet.this,QuanLyChuongTrinhLienKet.this,listCtlkDoanhNghiep);
                recyclerView.setAdapter(quanLyChuongTrinhLienKetAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuanLyChuongTrinhLienKet.this));
            }

            @Override
            public void onFailure(Call<List<CTLKDoanhNghiep>> call, Throwable t) {

            }
        });



    }


}