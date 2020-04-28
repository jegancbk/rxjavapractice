package com.jegan.androidtutz.contactmanager;

import android.app.Application;
import android.widget.Toast;

import com.jegan.androidtutz.contactmanager.db.ContactsAppDatabase;
import com.jegan.androidtutz.contactmanager.db.entity.Contact;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactRepository {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Application application;

    public MutableLiveData<List<Contact>> getContactsLiveData() {
        return contactsLiveData;
    }

    private MutableLiveData<List<Contact>> contactsLiveData = new MutableLiveData<>();

    private ContactsAppDatabase contactsAppDatabase;

    private long rowIdOfTheItemInserted;

    public ContactRepository(Application application) {

        this.application = application;

        contactsAppDatabase = Room.databaseBuilder(
                application.getApplicationContext(),
                ContactsAppDatabase.class,
                "ContactDB"
        ).build();

        compositeDisposable.add(
                contactsAppDatabase.getContactDAO().getContacts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Contact>>() {
                            @Override
                            public void accept(List<Contact> contacts) {

                                contactsLiveData.postValue(contacts);

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {

                            }
                        }));
    }

    public void createContact(final String name, final String email) {

        compositeDisposable.add(
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {

                        Contact contactObj = new Contact(0, name, email);

                        rowIdOfTheItemInserted = contactsAppDatabase.getContactDAO().addContact(contactObj);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Toast.makeText(
                                        application.getApplicationContext(),
                                        "contact added successfully " + rowIdOfTheItemInserted,
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                                Toast.makeText(
                                        application.getApplicationContext(),
                                        "error occurred",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }));
    }

    public void updateContact(final Contact contact) {

        compositeDisposable.add(
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {

                        contactsAppDatabase.getContactDAO().updateContact(contact);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Toast.makeText(
                                        application.getApplicationContext(),
                                        "contact updated successfully ",
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                                Toast.makeText(
                                        application.getApplicationContext(),
                                        "error occurred",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }));
    }

    public void deleteContact(Contact contact) {

        compositeDisposable.add(
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {

                        contactsAppDatabase.getContactDAO().deleteContact(contact);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Toast.makeText(
                                        application.getApplicationContext(),
                                        "contact deleted successfully ",
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                                Toast.makeText(
                                        application.getApplicationContext(),
                                        "error occurred",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }));

    }

    public void clear() {
        compositeDisposable.clear();
    }
}
