package com.example.eats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout filterContainer;
    private List<Chip> chipList = new ArrayList<>();
    private Chip selectedChip = null;

    // Sample filter names (Arabic text from your image)
    private final String[] filters = {
            "أجبان",
            "أجبان قابلة للدهن",
            "مستورد",
            "أفضل العروض"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         filterContainer = findViewById(R.id.filterContainer);

        LayoutInflater inflater = LayoutInflater.from(this);

        for (String filter : filters) {
            final Chip chip = (Chip) inflater.inflate(R.layout.filter_chip, filterContainer, false);
            chip.setText(filter);
            chip.setChecked(false);
            chip.setChipIconVisible(false);

            chip.setOnClickListener(v -> {
                if (chip == selectedChip) {
                    chip.setChecked(false);
                    chip.setChipIconVisible(false);
                    selectedChip = null;
                    return;
                }
                if (selectedChip != null) {
                    selectedChip.setChecked(false);
                    selectedChip.setChipIconVisible(false);
                }
                chip.setChecked(true);
                chip.setChipIconVisible(true);
                selectedChip = chip;
            });

            filterContainer.addView(chip);
            chipList.add(chip);
        }
    }
}
