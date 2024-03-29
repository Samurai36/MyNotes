package viktor.khlebnikov.geekgrains.android1.mynotes.data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import viktor.khlebnikov.geekgrains.android1.mynotes.R;

public class CardsSourceImpl implements CardsSource {
    private final List<CardData> dataSource;
    private final Resources resources;    // ресурсы приложения

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    public CardsSource init(CardsSourceResponse cardsSourceResponse){
        String[] titles = resources.getStringArray(R.array.notes_names);
        String[] descriptions = resources.getStringArray(R.array.notes_descriptions);
        for (int i = 0; i < descriptions.length; i++) {
            dataSource.add(new CardData(titles[i], descriptions[i], Calendar.getInstance().getTime()));
        }

        if (cardsSourceResponse != null){
            cardsSourceResponse.initialized(this);
        }

        return this;
    }

    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    public int size(){
        return dataSource.size();
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        dataSource.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }
}
