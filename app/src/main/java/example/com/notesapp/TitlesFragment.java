package example.com.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.stream.Collectors;

public class TitlesFragment extends ListFragment {
    private boolean mDualPane;
    private List<Note> notes;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View detailsFrame = getActivity().findViewById(R.id.details);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (mDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    public void setNotes(List<Note> notesList) {
        notes = notesList;
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, notes.stream().map(Note::getTitle).collect(Collectors.toList())));
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    public void changeItemChecked(int index) {
        if (index < getListAdapter().getCount() && index >= 0) {
            getListView().setItemChecked(index, true);
        }
    }

    public void showDetails(int index) {
        if (mDualPane) {
            changeItemChecked(index);
            DetailsFragment details = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShownIndex() != index) {
                details = DetailsFragment.newInstance(index, notes.get(index));
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.details, details);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("title", notes.get(index).getTitle());
            intent.putExtra("text", notes.get(index).getText());
            startActivity(intent);
        }
    }
}