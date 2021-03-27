package viktor.khlebnikov.geekgrains.android1.mynotes.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import viktor.khlebnikov.geekgrains.android1.mynotes.Cards.Note;
import viktor.khlebnikov.geekgrains.android1.mynotes.R;

public class FragmentNoteContent extends Fragment {

    static final String ARG_NOTE = "note";
    private Note note;

    public static FragmentNoteContent newInstance(Note note) {
        FragmentNoteContent f = new FragmentNoteContent();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_content, container, false);
        TextView TextViewName = view.findViewById(R.id.note_name_tv);
        TextViewName.setText(note.getNoteName());

        TextView TextViewDescription = view.findViewById(R.id.note_description_tv);
        TextViewDescription.setText(note.getNoteDescription());

        TextView TextViewDate = view.findViewById(R.id.note_init_date_tv);
        TextViewDate.setText(String.format("%s%s", TextViewDate.getText().toString(), note.getNoteDate()));

        return view;
    }

}
