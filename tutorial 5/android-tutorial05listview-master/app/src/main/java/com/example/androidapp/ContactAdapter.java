package com.example.androidapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private List<ContactModel> contactModelList;
    private Activity activity;
    private Context mContext;
    private IOnChildItemClick iOnChildItemClick;

    public ContactAdapter(List<ContactModel> contactModelList, Activity activity){
        this.contactModelList = contactModelList;
        this.activity = activity;
    }
    public void registerChildItemClick(IOnChildItemClick iOnChildItemClick){
        this.iOnChildItemClick = iOnChildItemClick;
    }
    public void unRegisterChildItemClick(){
        this.iOnChildItemClick = null;
    }

    @Override
    public int getCount() {
        return contactModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_contact,null);
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) rowView.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar);
            holder.btCall = (Button) rowView.findViewById(R.id.btCall);
            holder.btEdit = (Button) rowView.findViewById(R.id.btEdit);
            rowView.setTag(holder);
        }
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.tvName.setText(contactModelList.get(position).getName());
        viewHolder.tvPhone.setText(contactModelList.get(position).getPhone());
        viewHolder.ivAvatar.setImageResource(contactModelList.get(position).getImage());
        viewHolder.btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall(position);
            }
        });

        if (convertView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_contact,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView)convertView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView)convertView.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) convertView.findViewById(R.id.ivAvatar);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        ContactModel model = contactModelList.get(position);
        holder.tvName.setText(model.getName());
        holder.tvPhone.setText(model.getPhone());
        holder.ivAvatar.setImageResource(model.getImage());
        return convertView;
    }
    private  void onCall(int position){
        ContactModel contactModel = contactModelList.get(position);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contactModel.getPhone()));
        if (ActivityCompat.checkSelfPermission(mContext,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        mContext.startActivity(intent);
    }
    static class ViewHolder{
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
        Button btCall;
        Button btEdit;
    }
}
