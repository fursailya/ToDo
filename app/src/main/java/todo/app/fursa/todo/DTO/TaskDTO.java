package todo.app.fursa.todo.DTO;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ilya Fursa on 04.04.2017.
 */

@IgnoreExtraProperties
public class TaskDTO {
    private String title;
    private String description;
    private String date;
    private String time;
    private String priority;
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public TaskDTO(long uid, String title, String description, String date, String time, String priority) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.priority = priority;
    }

    public TaskDTO() {

    }
}
