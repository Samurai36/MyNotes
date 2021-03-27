package viktor.khlebnikov.geekgrains.android1.mynotes.Holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import viktor.khlebnikov.geekgrains.android1.mynotes.Cards.CardsSource;
import viktor.khlebnikov.geekgrains.android1.mynotes.Cards.Note;
import viktor.khlebnikov.geekgrains.android1.mynotes.R;
import viktor.khlebnikov.geekgrains.android1.mynotes.fragments.FragmentNotes;

public class ViewHolderAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final FragmentNotes mFragment;
    private final LayoutInflater mInflater;
    private final CardsSource mSource;

    private FragmentNotes.OnClickListener mOnClickListener;

    public ViewHolderAdapter(FragmentNotes fragment, CardsSource source) {
        mFragment = fragment;
        mInflater = fragment.getLayoutInflater();
        mSource = source;
    }

    public void setOnClickListener(FragmentNotes.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = mSource.getNoteId(position);
        holder.populate(mFragment, note);
        holder.itemView.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear(mFragment);
    }

    @Override
    public int getItemCount() {
        return mSource.size();
    }
}
