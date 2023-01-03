package np.com.sudarshandevkota.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import np.com.sudarshandevkota.R;
import np.com.sudarshandevkota.model.Transaction;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Transaction> list;

    public StatementAdapter(Context context, ArrayList<Transaction> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statement_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = list.get(position);
        holder.senderOrReceiverTV.setText(transaction.getSenderOrReceiver());
        holder.noteTV.setText(transaction.getNote());
        holder.amountTV.setText(String.valueOf(transaction.getAmount()));
        holder.timestampTV.setText(transaction.getTimestamp().toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView senderOrReceiverTV,amountTV,noteTV,timestampTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            senderOrReceiverTV = itemView.findViewById(R.id.tv_toOrFrom);
            amountTV = itemView.findViewById(R.id.tv_amount);
            noteTV = itemView.findViewById(R.id.tv_note);
            timestampTV = itemView.findViewById(R.id.tv_date);
        }
    }
}
