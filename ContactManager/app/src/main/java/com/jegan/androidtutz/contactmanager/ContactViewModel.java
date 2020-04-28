package com.jegan.androidtutz.contactmanager;

import android.app.Application;

import com.jegan.androidtutz.contactmanager.db.entity.Contact;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;

    public ContactViewModel(@NonNull Application application) {
        super(application);

        contactRepository = new ContactRepository(application);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactRepository.getContactsLiveData();
    }

    public void create(String name, String email) {
        contactRepository.createContact(name, email);
    }

    public void update(Contact contact) {
        contactRepository.updateContact(contact);
    }

    public void delete(Contact contact) {
        contactRepository.deleteContact(contact);
    }

    public void clear() {
        contactRepository.clear();
    }
}
