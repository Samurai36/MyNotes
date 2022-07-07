package viktor.khlebnikov.geekgrains.android1.mynotes.observe;

import java.util.ArrayList;
import java.util.List;

import viktor.khlebnikov.geekgrains.android1.mynotes.data.CardData;

public class Publisher {
    private final List<Observer> observers;

    public Publisher() {
        observers = new ArrayList<>();
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifySingle(CardData cardData) {
        for (Observer observer : observers) {
            observer.updateCardData(cardData);
            unsubscribe(observer);
        }
    }

}
