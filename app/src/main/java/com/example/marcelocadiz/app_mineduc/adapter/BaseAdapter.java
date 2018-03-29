package com.example.marcelocadiz.app_mineduc.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcelocadiz.app_mineduc.R.layout.*;
import com.example.marcelocadiz.app_mineduc.R;
import com.example.marcelocadiz.app_mineduc.model.Colegios;

import java.util.List;

/**
 * Created by Marxelo on 30-10-2017.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private List<Colegios> usuarioList;
    private Context myContext;
    CursorAdapter myCursorAdapter;

    public BaseAdapter(Context context, Cursor cursor){
        myContext = context;
        myCursorAdapter = new CursorAdapter(myContext,cursor,0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View retView = inflater.inflate(R.layout.item_favoritos,parent,false);


                return retView;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView rbd = (TextView) view.findViewById(R.id.txtRbdFav);
                TextView nombre = (TextView) view.findViewById(R.id.txtColegioFav);

                rbd.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                nombre.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
            }
        };
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favoritos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        myCursorAdapter.getCursor().moveToPosition(position);
        myCursorAdapter.bindView(holder.itemView,myContext,myCursorAdapter.getCursor());

    }

    @Override
    public int getItemCount() {
        return myCursorAdapter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rbd,nombre;



        CardView cardView;

        public ViewHolder(View item){
            super(item);

            cardView = (CardView) item.findViewById(R.id.cardviewFav);
            rbd = (TextView) item.findViewById(R.id.txtRbdFav);
            nombre = (TextView) item.findViewById(R.id.txtColegioFav);

        }
    }
}
