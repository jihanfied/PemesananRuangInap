package id.sch.smktelkom_mlg.tugas01.xiirpl2020.pemesananruanginap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import id.sch.smktelkom_mlg.tugas01.xiirpl2020.pemesananruanginap.adapter.SpesAdapter;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama;
    EditText etAlamat;

    RadioGroup radioJK;
    CheckBox cbVVIP, cbVIP, cbEkonomi;
    Button bOK;
    Spinner spSpesialis, spDokter;
    TextView tvHasil, tvKategori;
    int nKategori;

    String[][] arDokter = {{"Rini", "Stevani", "Yuliana",
            "Ruth Mariva", "Samuel"},
            {"Luciana", "Jimmy", "Irawati", "Roger"}, {"Philemon",
            "Yudith", "Bruce", "Andreas"},
            {"Cornelis", "Nailul", "Julita"}, {"Yustinus", "Khairul", "Abdul", "Thomas", "Hermantoni"}};
    ArrayList<String> listDokter = new ArrayList<>();
    SpesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.etNama);
        etAlamat = (EditText) findViewById(R.id.etAlamat);

        radioJK = (RadioGroup) findViewById(R.id.radioJK);

        cbVVIP = (CheckBox) findViewById(R.id.cbVVIP);
        cbVIP = (CheckBox) findViewById(R.id.cbVIP);
        cbEkonomi = (CheckBox) findViewById(R.id.cbEkonomi);

        cbVVIP.setOnCheckedChangeListener(this);
        cbVIP.setOnCheckedChangeListener(this);
        cbEkonomi.setOnCheckedChangeListener(this);

        spSpesialis = (Spinner) findViewById(R.id.spSpesialis);
        spDokter = (Spinner) findViewById(R.id.spDokter);

        tvHasil = (TextView) findViewById(R.id.tvHasil);
        tvKategori = (TextView) findViewById(R.id.tvKategori);

        bOK = (Button) findViewById(R.id.buttonOK);

        adapter = new SpesAdapter(this, listDokter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDokter.setAdapter(adapter);

        spSpesialis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listDokter.clear();
                listDokter.addAll(Arrays.asList(arDokter[position]));
                adapter.setmSpesialis((String) spSpesialis.getItemAtPosition(position));
                adapter.notifyDataSetChanged();
                spDokter.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
            }
        });

    }

    private void doProcess() {
        String hasil = null;
        String hasil1 = null;
        String hasil2 = null;

        if (isValid()) {
            String nama = etNama.getText().toString();
            String alamat = etAlamat.getText().toString();

            if (radioJK.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(radioJK.getCheckedRadioButtonId());
                hasil = rb.getText().toString();
            }

            StringBuilder builder = new StringBuilder();
            builder.append(" Dr.");
            builder.append(spDokter.getSelectedItem().toString());

            String hasil3 = " Kategori Kamar :\n";
            int Startlen = hasil3.length();
            if (cbVVIP.isChecked()) hasil3 += cbVVIP.getText() + "\n";
            if (cbVIP.isChecked()) hasil3 += cbVIP.getText() + "\n";
            if (cbEkonomi.isChecked()) hasil3 += cbEkonomi.getText() + "\n";

            if (hasil == null) {
                tvHasil.setText(" Belum Memilih Jenis Kelamin ");
            } else if (hasil3.length() == Startlen) hasil3 += "Tidak Ada Pilihan";
            else {
                tvHasil.setText("Nama Lengkap : " + nama + "\nAlamat : " + alamat
                        + "\nJenis Kelamin : " + hasil
                        + "\n" + hasil3 + "Dokter Spesialis : " + builder);
            }

        }

    }

    public boolean isValid() {
        boolean valid = true;
        String nama = etNama.getText().toString();
        String alamat = etAlamat.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }
        if (alamat.isEmpty()) {
            etAlamat.setError("Alamat belum diisi");
            valid = false;
        } else if (alamat.length() > 100) {
            etAlamat.setError("Alamat maksimal 100 karakter");
            valid = false;
        } else {
            etAlamat.setError(null);
        }

        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nKategori += 1;
        else nKategori -= 1;
        tvKategori.setText("Kategori (" + nKategori + " terpilih)");
    }
}