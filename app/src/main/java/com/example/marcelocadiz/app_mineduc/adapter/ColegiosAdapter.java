package com.example.marcelocadiz.app_mineduc.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcelocadiz.app_mineduc.DataBaseManager;
import com.example.marcelocadiz.app_mineduc.DbHelper;
import com.example.marcelocadiz.app_mineduc.R;
import com.example.marcelocadiz.app_mineduc.model.Colegios;
import com.example.marcelocadiz.app_mineduc.model.Matriculas;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;


import java.util.ArrayList;

/**
 * Created by ExpoCode Tech http://expocodetech.com
 */

public class ColegiosAdapter extends RecyclerView.Adapter<ColegiosAdapter.ViewHolder> implements Filterable {
    private ArrayList<Colegios> mDataset;
    private ArrayList<Colegios> arraydos;
    private Context mContext;
    private ArrayList<Matriculas> arrayMatriculas;
    private View.OnClickListener listener;
    static int lastPosition = -1;
    private Cursor fila;

    public interface click{

        void clickNombre(Colegios colegio);

        void clickMatricula(Colegios cole);
    }



    public ColegiosAdapter(ArrayList<Colegios> colegiox, Context context){
        mDataset = colegiox;
        mContext = context;
        arraydos = colegiox;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Colegios colegio = mDataset.get(position);

        holder.colegio.setText(colegio.getNombre());
        holder.rbd.setText(colegio.getRBD());


        holder.botonEstablecim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContext instanceof click) {
                    ((click) mContext).clickNombre(colegio);
                }
            }
        });


        holder.botonMatricula.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mContext instanceof click) {
                    ((click) mContext).clickMatricula(colegio);
                }
            }
        });


      /*  holder.botonMatricula.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mContext instanceof ColegiosAdapterListener) {
                    ((ColegiosAdapterListener) mContext).OnItemClicked(colegio);
                }
            }
        });*/

        //   holder.favorite.setFavorite(false, true);
        holder.favorite.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                        DbHelper helper = new DbHelper(mContext);
                        SQLiteDatabase bd = helper.getWritableDatabase();
                        String id = holder.rbd.getText().toString();
                        String nombre = holder.colegio.getText().toString();


                        if (favorite) {
                            Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                                    "select nombre from Favorito where nombre='" + nombre + "'", null);
                            if (fila.moveToFirst()) {

                                //capturamos los valores del cursos y lo almacenamos en variable
                                fila.getString(0);
                                Toast.makeText(mContext, "Ya esta en el Colegio !", Toast.LENGTH_SHORT).show();
                            }else{

                                DataBaseManager dat = new DataBaseManager(mContext);
                                ContentValues registro = new ContentValues();  //es una clase para guardar datos
                                registro.put("id", id);
                                registro.put("nombre", nombre);
                                bd.insert(DataBaseManager.TABLE_NAME, null, dat.generarContentValues(id,nombre));

                                bd.close();
                            }

                        } else {


                            //   bd.delete(DataBaseManager.TABLE_NAME, DataBaseManager.USR_NOM+ "="+ nombre, null); // (votantes es la nombre de la tabla, condición)

                            bd.execSQL("DELETE FROM favorito WHERE nombre='" + nombre + "'");
                            Toast.makeText(mContext, "Se ha borrado el Colegio !", Toast.LENGTH_SHORT).show();
                            bd.close();

                        }
                    }
                });


    }





    @Override
    public int getItemCount() {
        return mDataset.size();
    }





    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mDataset = arraydos;
                } else {

                    ArrayList<Colegios> filteredList = new ArrayList<>();

                    for (Colegios colegios : arraydos) {

                        if (colegios.getRBD().toLowerCase().contains(charString) || colegios.getNombre().toLowerCase().contains(charString)) {

                            filteredList.add(colegios);
                        }
                    }

                    mDataset = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataset;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataset = (ArrayList<Colegios>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }




    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView colegio,rbd;
        Button botonMatricula, botonEstablecim;
        MaterialFavoriteButton favorite;
        CardView cardView;

        ViewHolder(View item) {
            super(item);

            rootView = itemView;


            cardView = (CardView) item.findViewById(R.id.cardview);
            colegio = (TextView) item.findViewById(R.id.txtColegio);
            rbd = (TextView) item.findViewById(R.id.txtRbd);
            botonMatricula = (Button) item.findViewById(R.id.botonMatricula);
            botonEstablecim = (Button) item.findViewById(R.id.botonEstablecim);
            favorite = (MaterialFavoriteButton) item.findViewById(R.id.buttonFavotite);




        }
    }
}
