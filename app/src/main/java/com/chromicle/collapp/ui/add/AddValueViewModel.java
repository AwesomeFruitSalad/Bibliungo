package com.chromicle.collapp.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddValueViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddValueViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Add value fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}