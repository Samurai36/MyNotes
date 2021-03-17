package viktor.khlebnikov.geekgrains.android1.mynotes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNotesList extends Fragment {

    private int mCurrentNoteIdx = -1;
    private int mCurrentDateIdx = -1;

    public FragmentNotesList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_notes_list, container, false);
        String[] notesNames = getResources().getStringArray(R.array.notes_names);

        int idx = 0;
        for (String noteName : notesNames) {
            TextView textView = new TextView(getContext());
            textView.setText(noteName);
            textView.setTextSize(40);
            final int NoteIdx = idx;
            textView.setOnClickListener((v) -> {
                setCurrentNoteIdx(NoteIdx);
                if (getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_PORTRAIT) {
                    inPortrait(NoteIdx);
                } else {
                    inLandscape(NoteIdx, NoteIdx);
                }
            });
            viewGroup.addView(textView);
            idx++;
        }

        return viewGroup;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);

        if (bundle != null) {
            mCurrentNoteIdx = bundle.getInt(FragmentNoteContent.ARG_NOTE_IDX, -1);
            if (mCurrentNoteIdx != -1 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                inLandscape(mCurrentNoteIdx, mCurrentDateIdx);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);

        bundle.putInt(FragmentNoteContent.ARG_NOTE_IDX, mCurrentNoteIdx);
    }

    private void setCurrentNoteIdx(int noteIdx) {
        mCurrentNoteIdx = noteIdx;
    }

    private void inPortrait(int noteDescIdx) {
        Intent intent = new Intent(getActivity(), NotesContentActivity.class);
        intent.putExtra(NotesContentActivity.KEY_DESCRIPTION_INDEX, noteDescIdx);
        startActivity(intent);
    }

    private void inLandscape(int noteDescIdx, int noteDateIdx) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.note_content_container, FragmentNoteContent.newInstance(noteDescIdx, noteDateIdx));
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

}