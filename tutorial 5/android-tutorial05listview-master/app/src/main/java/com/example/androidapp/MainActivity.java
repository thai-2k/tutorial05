package com.example.androidapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick{
    private ListView lvContact;
    private List<ContactModel> contactModelList = new ArrayList<>();
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        mAdapter = new ContactAdapter(contactModelList, this);
        mAdapter.registerChildItemClick(this);
        lvContact.setAdapter(mAdapter);
        lvContact = (ListView) findViewById(R.id.lvContact);
        ContactAdapter adapter = new ContactAdapter(contactModelList,this);
        lvContact.setAdapter(adapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ContactModel contactModel = contactModelList.get(position);
                Toast.makeText(MainActivity.this,contactModel.getName() + ": " + contactModel.getPhone(),Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initView(){
        lvContact = (ListView) findViewById(R.id.lvContact);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvName = (TextView) findViewById(R.id.tvName);
    }
    private void initData(){
        ContactModel contact = new ContactModel("Nguyen Van A","012321321",R.drawable.ic_u2);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN B","123123131",R.drawable.ic_u3);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN C","123123131",R.drawable.ic_u4);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN D","123123131",R.drawable.ic_u3);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN E","123123131",R.drawable.ic_u2);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN G","123123131",R.drawable.ic_u3);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN H","123123131",R.drawable.ic_u3);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN I","123123131",R.drawable.ic_u3);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN K","123123131",R.drawable.ic_u3);
        contactModelList.add(contact);
        contact = new ContactModel("NGUYEN VAN M","123123131",R.drawable.ic_u3);
        contactModelList.add(contact);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contactModel = contactModelList.get(position);
        ivUser.setImageResource(contactModel.getImage());
        tvName.setText(contactModel.getName());
    }
}