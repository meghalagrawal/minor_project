package nitrr.meghal.classes.view;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nitrr.meghal.R;
import nitrr.meghal.assignments.view.AssignmentsActivity;
import nitrr.meghal.classes.data.ClassData;
import nitrr.meghal.classes.view.ClassListActivity;

public class ClassListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ClassData> classDataList = new ArrayList<>();

    public ClassListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.class_list_item, parent, false);
        return new ClassListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ClassData classData = classDataList.get(position);

        ClassListViewHolder classListViewHolder = (ClassListViewHolder) holder;
        classListViewHolder.classTitle.setText(classData.getTitle());
        classListViewHolder.classDescription.setText(classData.getDescription());
        classListViewHolder.classListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof ClassListActivity) {
                    Intent intent = new Intent(context, AssignmentsActivity.class);
                    intent.putExtra("class_id", classData.getClass_id());

                    ((ClassListActivity)context).startActivity(intent);

//                    Toast.makeText(context, "Class Clicked:" + classData.getTitle(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return classDataList.size();
    }

    public void setData(List<ClassData> classDataList) {
        this.classDataList = classDataList;
    }

    class ClassListViewHolder extends RecyclerView.ViewHolder {

        TextView classTitle;
        TextView classDescription;
        LinearLayout classListLayout;

        public ClassListViewHolder(View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.classTitle);
            classDescription = itemView.findViewById(R.id.classDescription);
            classListLayout = itemView.findViewById(R.id.classListLayout);


        }
    }

}
