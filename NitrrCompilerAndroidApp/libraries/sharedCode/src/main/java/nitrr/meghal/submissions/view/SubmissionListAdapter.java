package nitrr.meghal.submissions.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nitrr.meghal.R;
import nitrr.meghal.submissions.data.SubmissionDetails;

public class SubmissionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<SubmissionDetails> submissionDetailsList = new ArrayList<>();

    public SubmissionListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.submission_list_item, parent, false);
        return new ClassListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final SubmissionDetails submissionDetails = submissionDetailsList.get(position);

        final ClassListViewHolder classListViewHolder = (ClassListViewHolder) holder;
        classListViewHolder.classTitle.setText(submissionDetails.getTime_taken()+" seconds");
//        classListViewHolder.classDescription.setText(submissionDetails.getResponse());
        classListViewHolder.created_time.setText(submissionDetails.getCreated());
        classListViewHolder.submissionNo.setText((position+1)+"");
        classListViewHolder.classListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof SubmissionListActivity) {
//                    Intent intent = new Intent(context, SubmissionListActivity.class);
//                    intent.putExtra("class_id", classData.getClass_id());
//
//                    ((ClassListActivity)context).startActivity(intent);

//                    Toast.makeText(context, "Class Clicked:" + classData.getTitle(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return submissionDetailsList.size();
    }

    public void setData(List<SubmissionDetails> submissionDetailsList) {
        this.submissionDetailsList = submissionDetailsList;
    }

    class ClassListViewHolder extends RecyclerView.ViewHolder {
        TextView submissionNo;
        TextView classTitle;
        TextView classDescription;
        LinearLayout classListLayout;
        TextView created_time;

        public ClassListViewHolder(View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.classTitle);
//            classDescription = itemView.findViewById(R.id.classDescription);
            classListLayout = itemView.findViewById(R.id.classListLayout);
            submissionNo = itemView.findViewById(R.id.submissionNo);
            created_time = itemView.findViewById(R.id.created_time);



        }
    }

}
