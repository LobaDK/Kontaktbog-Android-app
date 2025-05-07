package com.example.kontaktbog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private final List<Contact> contactList;

    // Constructor to initialize the contact list
    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    // ViewHolder class to hold references to the views
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, phoneTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contact_name);
            emailTextView = itemView.findViewById(R.id.contact_email);
            phoneTextView = itemView.findViewById(R.id.contact_number);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_show_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // Get the current contact
        Contact contact = contactList.get(position);
        Context context = holder.itemView.getContext();

        // Bind the contact fields to the views
        holder.nameTextView.setText(contact.getName());
        holder.emailTextView.setText(contact.getEmail());
        holder.emailTextView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + contact.getEmail()));
            context.startActivity(intent);
        });
        holder.phoneTextView.setText(contact.getPhone());
        holder.phoneTextView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + contact.getPhone()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
