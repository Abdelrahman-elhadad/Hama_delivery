package hama.alsaygh.kw.delivery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.store.vendor_info;

import java.util.ArrayList;

public class Vendor_info_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<vendor_info> vendor_infoArrayList;
    private String type;
    FragmentManager fragmentManager;
    Context context;
    FragmentTransaction fragmentTransaction;

    public Vendor_info_adapter(ArrayList<vendor_info> vendor_infoArrayList, String type) {
        this.vendor_infoArrayList = vendor_infoArrayList;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jawaher_store,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return vendor_infoArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv1 , out_of_delivery;
        View view;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
             out_of_delivery=(TextView)itemView.findViewById(R.id.out_of_delivery);
            itemView.setOnClickListener((View.OnClickListener) this);


        }


        @Override
        public void onClick(View v) {

        }
    }

}
