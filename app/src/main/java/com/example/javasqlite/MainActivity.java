package com.example.javasqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database db;
    Button create,read,update,delete;
    EditText student_name,subject,marks,result,id;


       @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new Database(this);
      //  db.getWritableDatabase();

        create=(Button)findViewById(R.id.create);
        read=(Button)findViewById(R.id.read);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);
        student_name=(EditText)findViewById(R.id.student_name);
        subject=(EditText)findViewById(R.id.subject);
        marks=(EditText)findViewById(R.id.marks);
        result=(EditText)findViewById(R.id.result);
        id=(EditText)findViewById(R.id.id);



           create.setOnClickListener(v -> {
            boolean dataInsertOrNot= db.insertData(student_name.getText().toString(),
                    subject.getText().toString(),
                    marks.getText().toString(),
                    result.getText().toString());
            if(dataInsertOrNot)
            {
                Toast.makeText(MainActivity.this, "Student data is inserted", Toast.LENGTH_SHORT).show();
            }
            else
            { Toast.makeText(MainActivity.this, "Student data insertion failed", Toast.LENGTH_SHORT).show();}
           });

           read.setOnClickListener(v -> {
               Cursor cursor=db.readData();
               if(cursor.getCount()==0){
                   showData("Error","No data found");
                   return;
               }
               StringBuilder stringBuffer=new StringBuilder();
               while (cursor.moveToNext())
               {
                   stringBuffer.append("student_id:").append(cursor.getString(0)).append("\n");
                   stringBuffer.append("student_name:").append(cursor.getString(1)).append("\n");
                   stringBuffer.append("subject:").append(cursor.getString(2)).append("\n");
                   stringBuffer.append("marks:").append(cursor.getString(3)).append("\n");
                   stringBuffer.append("result:").append(cursor.getString(4)).append("\n");
               }
               showData("Data",stringBuffer.toString());
           });

           update.setOnClickListener(v -> {
            boolean isdataUpdate=  db.updateData(id.getText().toString(),student_name.getText().toString(),subject.getText().toString(),marks.getText().toString(),result.getText().toString());
               if(isdataUpdate)
               { Toast.makeText(MainActivity.this, " data is updated", Toast.LENGTH_SHORT).show();}
               else
               { Toast.makeText(MainActivity.this, "Student data updation failed", Toast.LENGTH_SHORT).show();}
           });


       delete.setOnClickListener(v->{
          Integer isDeleted= db.deleteRecord(id.getText().toString());
          if(isDeleted>0)
          {
              Toast.makeText(MainActivity.this, " data is deleted", Toast.LENGTH_SHORT).show();
          }
          else
          {
              Toast.makeText(MainActivity.this, " data is not deleted", Toast.LENGTH_SHORT).show();

          }

       });
       }




    public  void showData(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}