package example.com.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TitlesFragment titlesFragment;
    private List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        titlesFragment = (TitlesFragment) getSupportFragmentManager().findFragmentById(R.id.titles);
        notesList = new ArrayList<>();
        feedNotes();
        titlesFragment.setNotes(notesList);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details);
        if (detailsFragment != null) {
            titlesFragment.changeItemChecked(detailsFragment.getShownIndex());
        }
    }

    private void feedNotes() {
        notesList.add(new Note("Notatka","krótki tekst o charakterze informacyjnym, nastawiony na przekaz danych, wyróżniający się zrozumiałością przedstawianych wiadomości: rzeczowością i zwięzłością. W notatce umieszczane są najważniejsze informacje w sposób skrótowy i uporządkowany. Gatunek ten przedstawia jakiś fakt, zawiera elementy streszczenia, opisu, opowiadania i charakterystyki. Tekst spójny i logiczny. Opowiada o tym, co wydarzyło się w przeszłości, planowane jest w przyszłości lub opisuje stan obecny. Konieczne jest nadanie tytułu."));
        notesList.add(new Note("Activity","to jeden z podstawowych komponentów systemu Android. Klasa ta (a raczej jej podklasy) odpowiedzialna jest za interakcję z użytkownikiem, tworzenie okna naszej aplikacji i uruchamianie innych podstawowych komponentów systemowych. Zazwyczaj jedna podklasa Activity reprezentuje jedno okno naszej aplikacji.\n" +
                "W tym artykule zagłębimy się w jej budowę, cykl życia i kilka innych mechanizmów z nią związanych."));
        notesList.add(new Note("Fragment","obiekty Fragment są świetnym narzędziem, które przydaje się w wielu problematycznych sytuacjach, w których tylko klasy Activity były dostępne. Fragmenty pozwalają na organizowanie komponentów interfejsu projektu dla różnych urządzeń, dając możliwość wyświetlania wielu segmentów interfejsu na dużym ekranie, np. na tablecie lub wyświetlać jeden i połączyć wszystkie ze sobą na mniejszym ekranie."));
    }
}
