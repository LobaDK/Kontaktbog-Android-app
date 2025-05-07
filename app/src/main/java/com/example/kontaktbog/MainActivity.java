package com.example.kontaktbog;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ContactAdapter contactAdapter = new ContactAdapter(DataIO.getInstance(getApplicationContext()).getContacts());
        recyclerView.setAdapter(contactAdapter);

        if (DataIO.getInstance(getApplicationContext()).getContacts().isEmpty()){
            Contact contact = new Contact("Nicklas H.", "nick8870@elev.tec.dk", "42329300");
            DataIO.getInstance(getApplicationContext()).addContact(contact);
        }
    }
}