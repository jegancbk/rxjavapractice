package com.jegan.androidtutz.contactmanager.db;

import com.jegan.androidtutz.contactmanager.db.entity.Contact;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactsAppDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();
}
