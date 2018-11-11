package com.chen.baseapplication.tagview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import com.chen.baseapplication.R;
import com.chen.baseapplication.tagview.view.TagContainerLayout;




public class TagViewActivity extends AppCompatActivity {

    private TagContainerLayout mTagContainerLayout2, mTagContainerLayout3, mTagContainerLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_view);
         List<String> list2 = new ArrayList<String>();
        list2.add("China");
        list2.add("USA");
        list2.add("Austria");
        list2.add("问花");
        list2.add("泪眼");
        list2.add("乱红");
//        list2.add("UK");
//        list2.add("Germany");
//        list2.add("Niger");
//        list2.add("Poland");
//        list2.add("Norway");
//        list2.add("Uruguay");
//        list2.add("Brazil");

        mTagContainerLayout2 = (TagContainerLayout) findViewById(R.id.tagcontainerLayout2);
        mTagContainerLayout2.setTags(list2);
    }

    public class TagRecyclerViewAdapter
            extends RecyclerView.Adapter<TagRecyclerViewAdapter.TagViewHolder>{

        private Context mContext;
        private String[] mData;
        private View.OnClickListener mOnClickListener;

        public TagRecyclerViewAdapter(Context context, String[] data){
            this.mContext = context;
            this.mData = data;
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        @Override
        public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TagViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.view_recyclerview_item, parent, false), mOnClickListener);
        }

        @Override
        public void onBindViewHolder(TagViewHolder holder, int position) {
            holder.tagContainerLayout.setTags(mData);
            holder.button.setOnClickListener(mOnClickListener);
        }

        public void setOnClickListener(View.OnClickListener listener){
            this.mOnClickListener = listener;
        }

        class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            TagContainerLayout tagContainerLayout;
            View.OnClickListener clickListener;
            Button button;

            public TagViewHolder(View v, View.OnClickListener listener){
                super(v);
                this.clickListener = listener;
                tagContainerLayout = (TagContainerLayout) v.findViewById(R.id.tagcontainerLayout);
                button = (Button) v.findViewById(R.id.button);
//                v.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    clickListener.onClick(v);
                }
            }
        }
    }
}

