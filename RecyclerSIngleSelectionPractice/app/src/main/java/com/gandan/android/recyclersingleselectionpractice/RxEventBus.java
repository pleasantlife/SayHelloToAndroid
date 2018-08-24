package com.gandan.android.recyclersingleselectionpractice;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxEventBus {

    private static RxEventBus rxEventBus;
    private static PublishSubject<Item> itemObject;

    private RxEventBus() {
        itemObject = PublishSubject.create();
    }

    public static RxEventBus getInstance(){
        if(rxEventBus == null){
            rxEventBus = new RxEventBus();
        }
        return rxEventBus;
    }

    public void sendEvent(Item item) {
        itemObject.onNext(item);
    }

    public Observable<Item> getItemEvent(){
        return itemObject;
    }


}
