package viktor.khlebnikov.geekgrains.android1.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

public class NotesContentActivity extends AppCompatActivity {

    public static final String KEY_DESCRIPTION_INDEX = "NotesContentActivity.description_idx";
    public static final String KEY_DATE_INDEX = "NotesContentActivity.date_idx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_content);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            int descriptionIdx = getIntent().getIntExtra(KEY_DESCRIPTION_INDEX, -1);
            int dateIdx = getIntent().getIntExtra(KEY_DATE_INDEX, -1);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.note_content_container, FragmentNoteContent.newInstance(descriptionIdx, dateIdx));
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }
}