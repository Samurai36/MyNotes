package viktor.khlebnikov.geekgrains.android1.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNoteContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNoteContent extends Fragment {

    public static final String ARG_NOTE_IDX = "FragmentNoteContent.note_idx";
    public static final String ARG_DATE_IDX = "FragmentNoteContent.date_idx";

    private int mNoteIdx;
 //   private int mDateIdx;

    public FragmentNoteContent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param noteIdx note index.
     * @return A new instance of fragment FragmentNoteContent.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNoteContent newInstance(int noteIdx, int dateIdx) {
        FragmentNoteContent fragment = new FragmentNoteContent();
        Bundle args = new Bundle();
        args.putInt(ARG_NOTE_IDX, noteIdx);
        args.putInt(ARG_DATE_IDX, dateIdx);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNoteIdx = getArguments().getInt(ARG_NOTE_IDX, -1);
         //   mDateIdx = getArguments().getInt(ARG_DATE_IDX, -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TypedArray description = getResources().obtainTypedArray(R.array.notes_descriptions);
        TextView textView = view.findViewById(R.id.note_description);
        textView.setText(description.getText(mNoteIdx));
        description.recycle();

        TypedArray date = getResources().obtainTypedArray(R.array.notes_date);
        TextView textViewDate = view.findViewById(R.id.note_date);
        textViewDate.setText(date.getText(mNoteIdx));
        date.recycle();

    }

}