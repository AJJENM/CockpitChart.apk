//package com.example.myapplication.Adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import java.util.List;
//
//public class SpinnerAdapterr extends BaseAdapter {
//    private View view;
//    private Context context;
//    private List<String> hospitalList;
//    public SpinnerAdapterr(List<String> hospitalList,Context context){
//        this.hospitalList = hospitalList;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//
//    public class SpinnerViewHolder {
//    }
////    @NonNull
////    @Override
////    public SpinnerAdapterr.SpinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner,parent,false);
////        return new SpinnerViewHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull SpinnerAdapterr.SpinnerViewHolder holder, int position) {
////        holder.textView.setText(hospitalList.get(position).toString());
////    }
////
////    @Override
////    public int getItemCount() {
////        return hospitalList.size();
////    }
////
////    public class SpinnerViewHolder extends RecyclerView.ViewHolder{
////        TextView textView;
////        public SpinnerViewHolder(@NonNull View itemView) {
////            super(itemView);
////            textView = itemView.findViewById(R.id.Spinner);
////        }
////    }
//}
