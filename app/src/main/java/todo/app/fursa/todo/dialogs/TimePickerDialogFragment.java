package todo.app.fursa.todo.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Ilya Fursa on 04.04.2017.
 */

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private EditText etTime;

    public TimePickerDialogFragment(EditText etTime) {
        this.etTime = etTime;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar taskTime = Calendar.getInstance();
        taskTime.set(Calendar.HOUR_OF_DAY, hour);
        taskTime.set(Calendar.MINUTE, minute);
        etTime.setText(hour + ":" + minute);
    }
}
