package nitrr.meghal.assignments.view;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nitrr.meghal.R;
import nitrr.meghal.assignments.data.AssignmentDetails;
import nitrr.meghal.classes.view.ClassListActivity;
import nitrr.meghal.submissions.view.SubmissionListActivity;

public class AssignmentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<AssignmentDetails> assignmentDetailsList= new ArrayList<>();

    public AssignmentListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.assignment_list_item, parent, false);
        return new ClassListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final AssignmentDetails assignmentDetails = assignmentDetailsList.get(position);

        ClassListViewHolder classListViewHolder = (ClassListViewHolder) holder;
        classListViewHolder.classTitle.setText(assignmentDetails.getTitle());
        classListViewHolder.classDescription.setText(assignmentDetails.getDescription());
        classListViewHolder.classListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof AssignmentsActivity) {
                    Intent intent = new Intent(context, SubmissionListActivity.class);
                    intent.putExtra("class_id", assignmentDetails.getAssignment_id());

                    ((AssignmentsActivity)context).startActivity(intent);

//                    Toast.makeText(context, "Class Clicked:" + classData.getTitle(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return assignmentDetailsList.size();
    }

    public void setData(List<AssignmentDetails> assignmentDetailsList) {
        this.assignmentDetailsList = assignmentDetailsList;
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
