package com.jennyputrin.kampus;
// Create BY Jenny Putri Nengsi
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KampusAdapter extends RecyclerView.Adapter<KampusAdapter.viewHolder> {
    List<Kampus> mdata=new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public KampusAdapter(List<Kampus> mdata, OnItemClickListener onItemClickListener) {
        this.mdata = mdata;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public KampusAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KampusAdapter.viewHolder holder, int position) {
        holder.bind(mdata.get(position),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvnama,tvalamat;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvnama=itemView.findViewById(R.id.tv_namakampus);
            tvalamat=itemView.findViewById(R.id.tv_alamatkampus);

        }
        public void bind(Kampus item, OnItemClickListener onItemClickListener){
            tvnama.setText(item.getNama());
            tvalamat.setText(item.getAlamat());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KampusAdapter.this.onItemClickListener.onItemClick(item,getAdapterPosition());
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Kampus item,int position);
    }
}
