package todo.app.fursa.todo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import todo.app.fursa.todo.DTO.TaskDTO;
import todo.app.fursa.todo.R;
import todo.app.fursa.todo.adapters.TodoRVAdapter;
import todo.app.fursa.todo.dialogs.TaskDialogFragment;

public class MainActivity extends AppCompatActivity implements ValueEventListener {
    private static final String DIALOG_TAG = "Create dialog";
    private static final String LOG_TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference myRef;
    private FirebaseUser user;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_done:
                    return true;
                case R.id.navigation_todo:
                    return true;
                case R.id.navigation_history:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerViewTodo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecyclerView.setAdapter(new TodoRVAdapter(fill()));

    }

    private List<TaskDTO> fill() {
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO(11111, "Заголовок", "Описание", "10.06.2017", "11:40", "Низкий"));
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addValueEventListener(this);
        return tasks;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_menu_sign_out:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
                finish();
                break;
            case R.id.top_menu_add:
                TaskDialogFragment taskDialogFragment = new TaskDialogFragment();
                taskDialogFragment.show(getSupportFragmentManager(), DIALOG_TAG);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d(LOG_TAG, dataSnapshot.getValue().toString());
        List<TaskDTO> tasks = new ArrayList<>();
        for (DataSnapshot child: dataSnapshot.getChildren()) {
            tasks.add(child.getValue(TaskDTO.class));
        }
        mRecyclerView.setAdapter(new TodoRVAdapter(tasks));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
