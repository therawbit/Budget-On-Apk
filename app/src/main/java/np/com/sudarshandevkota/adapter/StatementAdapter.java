package np.com.sudarshandevkota.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import np.com.sudarshandevkota.Listener.ClickListener;
import np.com.sudarshandevkota.R;
import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.model.TransactionType;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Transaction> list;
    private ClickListener listener;

    public StatementAdapter(Context context, ArrayList<Transaction> list,ClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
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
        if(transaction.getTransactionType().equals(TransactionType.INCOME)){
            holder.amountTV.setTextColor(context.getColor(R.color.green));
            holder.senderOrReceiverTV.setTextColor(context.getColor(R.color.green));
        }
        holder.senderOrReceiverTV.setText(transaction.getSenderOrReceiver());
        holder.noteTV.setText(transaction.getNote());
        holder.amountTV.setText("Rs. "+ transaction.getAmount());
        holder.timestampTV.setText(transaction.getTimestamp().toString());
        holder.itemView.setOnClickListener(v->listener.click(position));



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
