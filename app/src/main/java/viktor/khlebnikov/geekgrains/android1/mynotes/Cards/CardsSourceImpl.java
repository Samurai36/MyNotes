package viktor.khlebnikov.geekgrains.android1.mynotes.Cards;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import viktor.khlebnikov.geekgrains.android1.mynotes.R;

public class CardsSourceImpl implements CardsSource {
    private static final Object LOCK = new Object();
    private volatile static CardsSourceImpl sInstance;
    private final LinkedList<Note> mNoteSource = new LinkedList<>();
    private final Resources mResources;

    public static CardsSourceImpl getInstance(Resources resources) {
        CardsSourceImpl instance = sInstance;
        if (instance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    instance = new CardsSourceImpl(resources);
                    sInstance = instance;
                }
            }
        }
        return instance;
    }

    private CardsSourceImpl(Resources resources) {
        mResources = resources;
    }

    public CardsSourceImpl init() {
        String[] titles = mResources.getStringArray(R.array.notes_names);
        String[] descriptions = mResources.getStringArray(R.array.notes_descriptions);
        for (int i = 0; i < descriptions.length; i++) {
            mNoteSource.add(new Note(titles[i], descriptions[i]));
        }
        return this;
    }

    @Override
    public List<Note> getCardData() {
        return Collections.unmodifiableList(mNoteSource);
    }

    public Note getNoteId(int position) {
        return mNoteSource.get(position);
    }

    public int size() {
        return mNoteSource.size();
    }

    @Override
    public void add(@NonNull Note note) {
        mNoteSource.add(note);
    }

    @Override
    public void clear() {
        mNoteSource.clear();
    }

    @Override
    public void remove(int position) {
        mNoteSource.remove(position);
    }
}
