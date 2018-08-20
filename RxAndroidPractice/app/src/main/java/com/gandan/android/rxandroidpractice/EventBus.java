package com.gandan.android.rxandroidpractice;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class EventBus {

    private static EventBus eventBus;
    private final PublishSubject<Object> subject;

    public EventBus(){
        subject = PublishSubject.create();
    }

    public static EventBus getInstance() {
        if(eventBus == null){
            eventBus = new EventBus();
        }
        return eventBus;
    }

    public void sendEvent(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getEvents() {
        return subject;
    }
}
