package com.example.alifbahmodelviewviewmodel.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.alifbahmodelviewviewmodel.models.Person;

public class PersonViewModel extends BaseObservable{

    private Person mPerson;

    public PersonViewModel(){
        mPerson = new Person();
    }

    @Bindable
    public String getName() {
        return mPerson.getName();
    }

    public void setPerson(Person person) {
        this.mPerson = person;
//        notifyPropertyChanged();
    }
}
