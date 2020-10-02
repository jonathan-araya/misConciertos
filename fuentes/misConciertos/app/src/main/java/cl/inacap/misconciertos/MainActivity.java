package cl.inacap.misconciertos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misconciertos.dto.Concierto;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nombreTxt;
    private EditText fechaTxt;
    private Spinner generosSp;
    private EditText valorTxt;
    private Spinner calificacionSp;
    private Button registrarBtn;
    private List<Concierto> concierto = new ArrayList<>();
    private ArrayAdapter<Concierto> adapter;
    private ListView lV;
    private List<Modelo> mLista = new ArrayList<>() ;
    ListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombreTxt = findViewById(R.id.nombreTxt);
        this.generosSp = findViewById(R.id.generosSp);
        this.calificacionSp = findViewById(R.id.calificacionSp);
        this.valorTxt = findViewById(R.id.valorTxt);
        this.registrarBtn = findViewById(R.id.registrarBtn);
        this.fechaTxt = findViewById(R.id.fechaTxt);
        this.lV = findViewById(R.id.lV);
        final Spinner generosSp = (Spinner) findViewById(R.id.generosSp);

        this.adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, concierto);
        this.registrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String nombreStr = nombreTxt.getText().toString().trim();
                String valorStr = valorTxt.getText().toString().trim();
                String fechaStr = fechaTxt.getText().toString().trim();
                String generoStr = generosSp.getSelectedItem().toString().trim();
                String calificacionStr = calificacionSp.getSelectedItem().toString().trim();
                int valor = 0;
                int calificacion = 0;
                try {
                    if (nombreStr.equalsIgnoreCase("")){
                        throw new NumberFormatException();
                    }
                    }catch (NumberFormatException ex){
                    errores.add("no ha puesto nombre del artista");
                }
                try {
                    valor = Integer.parseInt(valorStr);
                    if (valor == 0){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no hay valor ingresado");
                }
                try {
                    valor = Integer.parseInt(valorStr);
                    calificacion = Integer.parseInt(calificacionStr);
                    if (valor < 0 || valor >9999999){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("ponga un valor realista");
                }
                try {
                    if (fechaStr.equalsIgnoreCase("")){
                        throw new  NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no ha ingresado la fecha");
                }

                if (errores.isEmpty()){
                    Concierto c = new Concierto();
                    c.setNombArtista(nombreStr);
                    c.setFecha(fechaStr);
                    c.setValorEntrada(valor);
                    c.setGenMusical(generoStr);
                    c.setCalificacion(calificacion);
                    concierto.add(c);
                    adapter.notifyDataSetChanged();
                    nombreTxt.setText("");
                    fechaTxt.setText("");
                    valorTxt.setText("");
                    Toast.makeText(MainActivity.this, "exito", Toast.LENGTH_SHORT).show();

                }else {
                    mostrarErrores(errores);
                }
            }
        });


        ArrayList<String> generosMusicales = new  ArrayList<String>();
        generosMusicales.add("Rock");
        generosMusicales.add("Reggaeton");
        generosMusicales.add("Metal");
        generosMusicales.add("Pop");
        generosMusicales.add("Jazz");
        generosMusicales.add("salsa");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,generosMusicales);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        generosSp.setAdapter(adapter);
        Spinner calificacionSp = (Spinner) findViewById(R.id.calificacionSp);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.calificacion, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calificacionSp.setAdapter(adapter1);
        EditText fechaTxt = (EditText) findViewById(R.id.fechaTxt);
        fechaTxt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fechaTxt:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                final String selectedDate= year + " / "+(month+1) + " / " + day;
                fechaTxt.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener){
            DatePickerFragment fragment;
            fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener){
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle saverInstanceState) {
            final Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            return new DatePickerDialog(getActivity(), listener, day, month, year);
        }

    }
    private void mostrarErrores(List<String> errores){
        String mensaje = "";
        for (String e: errores){
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error en la validacion")
                .setMessage(mensaje).setPositiveButton("aceptar", null)
                .create().show();
    }


}