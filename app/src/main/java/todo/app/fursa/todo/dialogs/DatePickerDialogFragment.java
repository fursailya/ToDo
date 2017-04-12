package todo.app.fursa.todo.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import todo.app.fursa.todo.R;

/**
 * Created by Ilya Fursa on 04.04.2017.
 */

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private EditText etDate;

    public DatePickerDialogFragment(EditText etDate) {
        this.etDate = etDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar taskDate = Calendar.getInstance();
        taskDate.set(year, month, day);
        if(month + 1 < 10) {
            etDate.setText(day + ".0" + (month + 1) + "." + year);
        }
        else {
            etDate.setText(day + "." + (month + 1) + "." + year);
        }

    }
}
