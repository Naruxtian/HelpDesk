package org.utl.helpdesk.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.utl.helpdesk.Model.Ticket;
import org.utl.helpdesk.R;

import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ViewHolder>
                            implements View.OnClickListener
{
    private List<Ticket> listaTickets;

    //Sirve para describir de que archivo vamos a tomar la interfaz a repetir
    private LayoutInflater mInflater;
    private Context context;

    //Definimos un listener para que esté en nuestros elementos del recyclerView
    private View.OnClickListener listener;

    public TicketsAdapter(List<Ticket> ItemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listaTickets = ItemList;
    }

    @Override
    public int getItemCount(){
        return listaTickets.size();
    }

    @Override
    public TicketsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.card_view_ticket, null);
        //Le asignamos la esucha listener a cada elemento del recycler
        view.setOnClickListener(this);
        return new TicketsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TicketsAdapter.ViewHolder holder, final int position){
        holder.bindData(listaTickets.get(position));
    }

    public void setItems(List<Ticket> items){
        listaTickets = items;
    }

    @Override
    public void onClick(View view) {

        if (listener != null){
            listener.onClick(view);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtDispositivo, txtTipo, txtFecha, txtEstatus;

        ViewHolder(View itemView){
            super(itemView);
            txtDispositivo = itemView.findViewById(R.id.txtDispositivo);
            txtTipo = itemView.findViewById(R.id.txtTipo);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtEstatus = itemView.findViewById(R.id.txtEstatus);
        }

        void bindData(final Ticket item){
            txtDispositivo.setText(item.getDevice());
            txtTipo.setText(item.getType());
            txtFecha.setText(item.getDate_of());
            txtEstatus.setText(Integer.toString(item.getEstatus()));
        }

    }
}
