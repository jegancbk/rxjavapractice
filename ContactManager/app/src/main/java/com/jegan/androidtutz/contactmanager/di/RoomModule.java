package com.jegan.androidtutz.contactmanager.di;

import android.app.Application;

import com.jegan.androidtutz.contactmanager.db.ContactsAppDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    @Singleton
    ContactsAppDatabase provideContactsAppDatabase(Application application){

        return Room.databaseBuilder(application,ContactsAppDatabase.class,"ContactDB").build();
    }

}
