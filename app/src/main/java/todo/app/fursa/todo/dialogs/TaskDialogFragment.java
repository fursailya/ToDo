package todo.app.fursa.todo.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import todo.app.fursa.todo.DTO.TaskDTO;
import todo.app.fursa.todo.R;

/**
 * Created by Ilya Fursa on 04.04.2017.
 */

public class TaskDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final java.lang.String DATE_PICKER_TAG = "DatePickerDialogFragment";
    private static final java.lang.String TIME_PICKER_TAG = "TimePickerDialogFragment";
    //   private static final String DB_URL = "https://todo-335d3.firebaseio.com/";
    private EditText etTitle;
    private EditText etTask;
    private EditText etDate;
    private EditText etTime;
    private Spinner prioritySpinner;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference();
    private FirebaseAuth mAuth;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //offline data

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_task, null);

        etTitle = (EditText) view.findViewById(R.id.etTitle);
        etTask = (EditText) view.findViewById(R.id.etTask);
        etDate = (EditText) view.findViewById(R.id.etDate);
        etTime = (EditText) view.findViewById(R.id.etTime);
        prioritySpinner = (Spinner) view.findViewById(R.id.prioritySpinner);

        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title_task);
        builder.setView(view);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                mAuth = FirebaseAuth.getInstance();
                long randomID = System.currentTimeMillis();
                writeToFirebase(randomID);
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TaskDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private void writeToFirebase(long uid) {
        TaskDTO task = new TaskDTO(uid, etTitle.getText().toString(),
                etTask.getText().toString(),
                etDate.getText().toString(),
                etTime.getText().toString(),
                prioritySpinner.getSelectedItem().toString()
        );
        reference.child(String.valueOf(uid) + mAuth.getCurrentUser().getUid()).setValue(task);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etDate:
                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment(etDate);
                datePickerDialogFragment.show(getFragmentManager(), DATE_PICKER_TAG);
                break;
            case R.id.etTime:
                TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment(etTime);
                timePickerDialogFragment.show(getFragmentManager(), TIME_PICKER_TAG);

        }
    }
}
