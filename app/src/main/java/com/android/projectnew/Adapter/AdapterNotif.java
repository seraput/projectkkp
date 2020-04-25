package com.android.projectnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.projectnew.DataController.NotifUser.DataItemUser;
import com.android.projectnew.R;

import java.util.List;

public class AdapterNotif extends ArrayAdapter<DataItemUser> {

    Context context;
    List<DataItemUser> arrayListDataItemUser;

    public AdapterNotif(@NonNull Context context, List<DataItemUser> arrayListDataItemUser) {
        super(context, R.layout.custom_list_item_notif, arrayListDataItemUser);

        this.context = context;
        this.arrayListDataItemUser = arrayListDataItemUser;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_notif, null, true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvEmail = view.findViewById(R.id.txt_email);
        TextView tvNama = view.findViewById(R.id.txt_nama);
        TextView tvStatus = view.findViewById(R.id.txt_status);

        tvID.setText(arrayListDataItemUser.get(position).getId());
        tvEmail.setText(arrayListDataItemUser.get(position).getEmail());
        tvNama.setText(arrayListDataItemUser.get(position).getNama());
        tvStatus.setText(arrayListDataItemUser.get(position).getStatus());

        return view;
    }
}
