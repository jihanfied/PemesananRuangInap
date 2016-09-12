package id.sch.smktelkom_mlg.tugas01.xiirpl2020.pemesananruanginap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.AbstractList;

import id.sch.smktelkom_mlg.tugas01.xiirpl2020.pemesananruanginap.R;

/**
 * Created by Jihan Fie Dhaneswara on 11/09/2016.
 */
public class SpesAdapter extends ArrayAdapter<String> {
    String mSpesialis = "";

    public SpesAdapter(Context context, AbstractList<String> listDokter) {
        super(context, R.layout.row_spinner_dokter, listDokter);
    }

    public void setmSpesialis(String Spesialis) {
        this.mSpesialis = Spesialis;
    }

    @Override
    public View getView(int position, View View, ViewGroup parent) {
        return getCustomView(position, View, parent);
    }


    @Override
    public View getDropDownView(int position, View View, ViewGroup parent) {
        return getCustomView(position, View, parent);
    }

    private View getCustomView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.row_spinner_dokter, parent, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(getItem(position).substring(0, 1));
        TextView tvDokter = (TextView) view.findViewById(R.id.tvDokter);
        tvDokter.setText(getItem(position));
        TextView tvSpesialis = (TextView) view.findViewById(R.id.tvSpesialis);
        tvSpesialis.setText(mSpesialis);

        return view;
    }
}