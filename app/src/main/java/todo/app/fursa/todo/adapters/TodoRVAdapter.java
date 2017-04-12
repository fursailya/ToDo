package todo.app.fursa.todo.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import todo.app.fursa.todo.DTO.TaskDTO;
import todo.app.fursa.todo.R;

/**
 * Created by Ilya Fursa on 11.04.2017.
 */

public class TodoRVAdapter extends RecyclerView.Adapter<TodoRVAdapter.ViewHolder> implements ValueEventListener{
    public List<TaskDTO> taskDTO;

    public TodoRVAdapter(List<TaskDTO> taskDTO) {
        this.taskDTO = taskDTO;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskDTO task = taskDTO.get(position);
        holder.title.setText(task.getTitle());
        holder.priority.setText(task.getPriority());
        holder.date.setText(String.valueOf(task.getDate()));
        holder.time.setText(task.getTime());
        holder.description.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        return taskDTO.size();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        CardView cardTask;
        TextView title, description, priority, date, time;

        public ViewHolder(View itemView) {
            super(itemView);
            cardTask = (CardView) itemView.findViewById(R.id.todoCard);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            description = (TextView) itemView.findViewById(R.id.tvDescription);
            date = (TextView) itemView.findViewById(R.id.tvDate);
            time = (TextView) itemView.findViewById(R.id.tvTime);
            priority = (TextView) itemView.findViewById(R.id.tvPriority);

            cardTask.setOnLongClickListener(this);


        }

        @Override
        public boolean onLongClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Удалить задачу?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    taskDTO.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();

            return false;
        }
    }

}
