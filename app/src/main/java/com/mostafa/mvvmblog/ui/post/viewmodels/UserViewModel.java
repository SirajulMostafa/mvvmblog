package com.mostafa.mvvmblog.ui.post.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.mostafa.mvvmblog.data.api.ServiceGenerator;
import com.mostafa.mvvmblog.data.models.User;
import com.mostafa.mvvmblog.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
public class UserViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";
    private MediatorLiveData<Resource<List<User>>> mUsers;

    public UserViewModel(MediatorLiveData<Resource<List<User>>> mUsers) {
        this.mUsers = mUsers;
        Log.d(TAG, "UserViewModel: user viewmodel is working");
    }

    public UserViewModel() {
    }


    public LiveData<Resource<List<User>>> observeUsers() {
        //next time we will make this function in another class and simply will be called here
        if (mUsers == null) {
            mUsers = new MediatorLiveData<>();
            mUsers.setValue(Resource.loading((List<User>) null));
            final LiveData<Resource<List<User>>> resourceLiveData = LiveDataReactiveStreams.fromPublisher(
                    ServiceGenerator.getUserApi().getAllUsers()

                            .onErrorReturn(new Function<Throwable, List<User>>() {
                                @Override
                                public List<User> apply(Throwable throwable) throws Throwable {
                                    User user = new User();
                                    user.setId(-1);
                                    ArrayList<User> users = new ArrayList<>();
                                    users.add(user);

                                    return users;
                                }
                            }).map(new Function<List<User>, Resource<List<User>>>() {
                        @Override
                        public Resource<List<User>> apply(List<User> users) throws Throwable {
                            if (users.size() > 0) {
                                if (users.get(0).getId() == -1) {
                                    return Resource.error("Something went wrong ...", null);
                                }
                            }
                            return Resource.success(users);
                        }
                    }).subscribeOn(Schedulers.io()));
            mUsers.addSource(resourceLiveData, new Observer<Resource<List<User>>>() {
                @Override
                public void onChanged(Resource<List<User>> listResource) {
                    mUsers.setValue(listResource);
                    mUsers.removeSource(resourceLiveData);
                }
            });
        }

        return mUsers;
    }
}
