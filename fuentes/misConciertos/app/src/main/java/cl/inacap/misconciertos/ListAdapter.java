package cl.inacap.misconciertos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Modelo> {

    private List<Modelo> mList;
    private Context mContext;
    private int resourceLayout;

    public ListAdapter(@NonNull Context context, int resource, List<Modelo> objects) {
        super(context, resource, objects);
        this.mList = objects;
        this.mContext = context;
        this.resourceLayout= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        Modelo modelo = mList.get(position);

        ImageView imagen = view.findViewById(R.id.imageView);
        imagen.setImageResource(modelo.getImage());

        TextView textofecha = view.findViewById(R.id.fecha1Txt);
        textofecha.setText(modelo.getFecha1());

        TextView nombreArtista = view.findViewById(R.id.nombArtistaTxt);
        nombreArtista.setText(modelo.getNombre());

        TextView valorEntrada = view.findViewById(R.id.valorEntradaTxt);
        valorEntrada.setText(modelo.getValorDeEntrada());

        return view;
    }
}
