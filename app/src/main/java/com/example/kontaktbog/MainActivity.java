package com.example.kontaktbog;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        FloatingActionButton addBtn = findViewById(R.id.add_contact_btn);
        addBtn.setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(this);
            View dialogView = inflater.inflate(R.layout.dialog_add_contact, null);
            EditText nameEdit = dialogView.findViewById(R.id.edit_name);
            EditText emailEdit = dialogView.findViewById(R.id.edit_email);
            EditText phoneEdit = dialogView.findViewById(R.id.edit_phone);

            new AlertDialog.Builder(this)
                .setTitle("Add Contact")
                .setView(dialogView)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = nameEdit.getText().toString().trim();
                    String email = emailEdit.getText().toString().trim();
                    String phone = phoneEdit.getText().toString().trim();
                    if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
                        Contact newContact = new Contact(name, email, phone);
                        DataIO.getInstance(getApplicationContext()).addContact(newContact);
                        contactAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
        });
    }
}