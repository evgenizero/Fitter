package com.fitter.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitter.R;
import com.fitter.model.StoryModel;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>{

    private List<StoryModel> stories;

    public StoriesAdapter(List<StoryModel> stories) {
        this.stories = stories;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.story_row, parent, false);

        return new StoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        StoryModel story = stories.get(position);
        holder.vText.setText(story.getText());
        holder.vCreatedDate.setText(new SimpleDateFormat().format(story.getCreatedDate()));
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {

        protected TextView vText;
        protected TextView vCreatedDate;

        public StoryViewHolder(View itemView) {
            super(itemView);
            vText = (TextView) itemView.findViewById(R.id.text);
            vCreatedDate = (TextView) itemView.findViewById(R.id.created_date);
        }
    }

}
