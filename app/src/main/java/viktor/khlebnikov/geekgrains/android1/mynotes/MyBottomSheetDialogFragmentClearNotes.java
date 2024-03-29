package viktor.khlebnikov.geekgrains.android1.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragmentClearNotes extends BottomSheetDialogFragment {

    private OnDialogListener dialogListener;

    public static MyBottomSheetDialogFragmentClearNotes newInstance() {
        return new MyBottomSheetDialogFragmentClearNotes();
    }

    public void setOnDialogListener(OnDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_clear_notes, container,
                false);

        setCancelable(false);

        view.findViewById(R.id.btnYes).setOnClickListener(view1 -> {
            dismiss();
            if (dialogListener != null) dialogListener.onDialogYes();
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(view12 -> {
            dismiss();
            if (dialogListener != null) dialogListener.onDialogCancel();
        });

        return view;
    }
}