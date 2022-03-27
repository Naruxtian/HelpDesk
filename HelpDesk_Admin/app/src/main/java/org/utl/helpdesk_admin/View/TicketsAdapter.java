package org.utl.helpdesk_admin.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.utl.helpdesk_admin.Model.Ticket;
import org.utl.helpdesk_admin.R;
import org.utl.helpdesk_admin.Model.Ticket;
import org.utl.helpdesk_admin.databinding.CardViewTicketBinding;

import java.util.ArrayList;
import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.viewHolder>
                                    implements View.OnClickListener

{
    private List<Ticket> listaTickets = new ArrayList<>();

    private View.OnClickListener listener;

    public void updateTicketsList(List<Ticket> listaTickets){
        this.listaTickets = listaTickets;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardViewTicketBinding cardViewTicketBinding = CardViewTicketBinding.inflate(layoutInflater, parent, false);

        cardViewTicketBinding.cardViewTicket.setOnClickListener(this);
        return new viewHolder(cardViewTicketBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.bindView(listaTickets.get(position));
    }

    public void setItems(List<Ticket> items){
        listaTickets = items;
    }

    @Override
    public int getItemCount() {
        return listaTickets.size();
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


    class viewHolder extends  RecyclerView.ViewHolder{

       CardViewTicketBinding cardViewTicketBinding;
       public viewHolder(@NonNull CardViewTicketBinding cardViewTicketBinding){
           super(cardViewTicketBinding.getRoot());
           this.cardViewTicketBinding = cardViewTicketBinding;

       }

       public void bindView(Ticket item){
           cardViewTicketBinding.txtDispositivo.setText(item.getDevice());
           cardViewTicketBinding.txtTipo.setText(item.getType());
           cardViewTicketBinding.txtFecha.setText(item.getDate_of());
           cardViewTicketBinding.txtEstatus.setText(Integer.toString(item.getEstatus()));
        }
   }
}
