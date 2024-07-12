package np.edu.ncit.crud;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Button btnAdd;
Button btnDeleteAll;
Button btnView;
EditText etName;
EditText etPhone;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnAdd= (Button)findViewById(R.id.btnadd);
        btnView=(Button)findViewById(R.id.btnview);
        btnDeleteAll=(Button)findViewById(R.id.btndeleteall);
        etName=(EditText)findViewById(R.id.name);
        etPhone=(EditText)findViewById(R.id.phone);
        listView=(ListView)findViewById(R.id.listview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase database=new MyDatabase(MainActivity.this);
                if(!etName.getText().toString().isEmpty()){
                    database.addStudent(etName.getText().toString(),etPhone.getText().toString());
                    Toast.makeText(MainActivity.this,"Succefully added student",Toast.LENGTH_SHORT).show();
                    etName.clearComposingText();

                    etPhone.clearComposingText();
                    etPhone.clearFocus();

                }
                else {
                    Toast.makeText(MainActivity.this,"Failed added student",Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase database=new MyDatabase(MainActivity.this);
                database.deleteAllData();
                Toast.makeText(MainActivity.this,"Failed added student",Toast.LENGTH_SHORT).show();

            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase database=new MyDatabase(MainActivity.this);
                ArrayList<String>list=new ArrayList<>();
                ArrayList<StudentModel>students= database.getStudents();
                for (StudentModel s:students
                ) {
                    list.add(s.getId()+" "+s.getName()+" "+s.getPhone());
//                    list.add(s.getId()+" "+s.getName()+" "+s.getPhone());
                    Log.d("STUDENT DETAIL",s.getId()+" "+s.getName()+" "+s.getPhone());
                }

                ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,list);

                listView.setAdapter(adapter);
            }
        });



    }
}