package com.android.projectnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.projectnew.DataController.NotifUser.DataItemAdmin;
import com.android.projectnew.R;

import java.util.List;

public class AdapterDataAdmin extends ArrayAdapter<DataItemAdmin> {

    Context context;
    List<DataItemAdmin> arrayListDataItemAdmin;

    public AdapterDataAdmin(@NonNull Context context, List<DataItemAdmin> arrayListDataItemAdmin) {
        super(context, R.layout.custom_list_data_admin, arrayListDataItemAdmin);

        this.context = context;
        this.arrayListDataItemAdmin = arrayListDataItemAdmin;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_data_admin, null, true);

        TextView tvID = view.findViewById(R.id.txt_id_adm);
        TextView tvEmail = view.findViewById(R.id.txt_email_adm);
        TextView tvNama = view.findViewById(R.id.txt_nama_adm);
        TextView tvTelp = view.findViewById(R.id.txt_telp_adm);
        TextView tvStatus = view.findViewById(R.id.txt_status_adm);

        tvID.setText(arrayListDataItemAdmin.get(position).getId());
        tvEmail.setText(arrayListDataItemAdmin.get(position).getEmail());
        tvNama.setText(arrayListDataItemAdmin.get(position).getName());
        tvTelp.setText(arrayListDataItemAdmin.get(position).getTelepon());
        tvStatus.setText(arrayListDataItemAdmin.get(position).getLevel());

        return view;
    }
}
