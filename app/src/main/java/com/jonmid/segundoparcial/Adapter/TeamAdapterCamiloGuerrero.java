package com.jonmid.segundoparcial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonmid.segundoparcial.Array.Images;
import com.jonmid.segundoparcial.Model.TeamModelCamiloGuerrero;
import com.jonmid.segundoparcial.R;
import com.jonmid.segundoparcial.Views.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 17/10/2017.
 */

public class TeamAdapterCamiloGuerrero extends RecyclerView.Adapter<TeamAdapterCamiloGuerrero.ViewHolder>{

    List<TeamModelCamiloGuerrero> teamlist = new ArrayList<>();
    Context context;
    // Constructor de la clase
    public TeamAdapterCamiloGuerrero(List<TeamModelCamiloGuerrero> teamlist, Context context) {
        this.teamlist = teamlist;
        this.context = context;
    }
    @Override
    public TeamAdapterCamiloGuerrero.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Configuracion del ViewAdapter
// Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
// Pasar la vista (item.xml) al ViewHolder
        TeamAdapterCamiloGuerrero.ViewHolder viewHolder = new TeamAdapterCamiloGuerrero.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TeamAdapterCamiloGuerrero.ViewHolder holder, int position) {
// Encargado de trabajar con el item.xml y sus componentes

        holder.textViewname.setText(teamlist.get(position).getName());
        holder.textViewcode.setText(teamlist.get(position).getCode());
        Picasso.with(context).load(Images.imageRandom()).into(holder.imageView);

    }
    @Override
    public int getItemCount() {
        return teamlist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewname;
        TextView textViewcode;
        ImageView imageView;


        public ViewHolder(View item) {
            super(item);
            textViewname = (TextView) item.findViewById(R.id.id_tv_name_team);
            textViewcode = (TextView) item.findViewById(R.id.id_tv_cod_team);
            imageView = (ImageView) item.findViewById(R.id.id_img_team);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("name", teamlist.get(getLayoutPosition()).getName());
            intent.putExtra("code", teamlist.get(getLayoutPosition()).getCode());
            intent.putExtra("url", teamlist.get(getLayoutPosition()).getUrl());
            context.startActivity(intent);

        }

    }
}

