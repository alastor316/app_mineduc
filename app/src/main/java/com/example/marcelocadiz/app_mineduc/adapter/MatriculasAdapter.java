package com.example.marcelocadiz.app_mineduc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marcelocadiz.app_mineduc.R;
import com.example.marcelocadiz.app_mineduc.model.Matriculas;

import java.util.ArrayList;

/**
 * Created by marcelo.cadiz on 25/10/2017.
 */

public class MatriculasAdapter extends RecyclerView.Adapter<MatriculasAdapter.ViewHolder> {

    private ArrayList<Matriculas> mDataset;
    private Context mContext;



    public MatriculasAdapter(ArrayList<Matriculas> comments, Context context){
        mDataset = comments;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Matriculas aComment= mDataset.get(position);
        holder.txtRbd.setText("RBD : "+aComment.getRBD());
        holder.txtNivel.setText("Nivel : "+aComment.getNivel());
        holder.txtGrado.setText("Grado : "+aComment.getGrado());
        holder.txtTipoEnsenianza.setText("Tipo Enseñanza : "+aComment.getEnsenianza());
        holder.txtAnio.setText("Año : "+aComment.getAnio());
        holder.txtCantidad.setText("Cantidad : "+aComment.getCantidad());




    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtRbd, txtNivel, txtAnio, txtCantidad, txtGrado, txtTipoEnsenianza ;
        ViewHolder(View itemView) {
            super(itemView);
            txtRbd = (TextView) itemView.findViewById(R.id.txtRbdMatri);
            txtNivel = (TextView) itemView.findViewById(R.id.txtNivelMatri);
            txtAnio = (TextView) itemView.findViewById(R.id.txtAnioMatri);
            txtCantidad = (TextView) itemView.findViewById(R.id.txtCantidadMatri);
            txtGrado = (TextView) itemView.findViewById(R.id.txtgradoMatri);
            txtTipoEnsenianza = (TextView) itemView.findViewById(R.id.txtTipoEnsenianzaMatri);

        }
    }
}
