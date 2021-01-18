package me.kandz.WindConverter;

import java.util.ArrayList;

/**
 * Created by kronos on 27.02.18.
 */

public class BeaufortList {

    private ArrayList<Beaufort> beaufortArrayList;

    public ArrayList<Beaufort> getBeaufortArrayList() {
        return beaufortArrayList;
    }

    public BeaufortList() {
        beaufortArrayList = new ArrayList<Beaufort>();
        populateList();
    }

    private void populateList() {

        beaufortArrayList.add(new Beaufort(0,
                R.drawable.bb0,
                "0 - Calm",
                "1",
                "0 - 0.02",
                "1",
                "1",
                "No smoke motion",
                "Mirror sea"));

        beaufortArrayList.add(new Beaufort(1,
                R.drawable.bb1,
                "1 - Light Air",
                "1 - 3",
                "0.3 - 1.5",
                "1 - 5",
                "1 - 3",
                "Visible smoke motion",
                "Small ripples, without foam crests"
        ));

        beaufortArrayList.add(new Beaufort(2,
                R.drawable.bb2,
                "2 - Light Breeze",
                "4 - 6",
                "1.6 - 3.3",
                "6 - 11",
                "4 - 7",
                "Leaves rustle and wind felt on skin",
                "Short wavelets"
        ));

        beaufortArrayList.add(new Beaufort(3,
                R.drawable.bb3,
                "3 - Gentle Breeze",
                "7 - 10",
                "3.4 - 5.4",
                "12 - 19",
                "8 - 12",
                "Leaves in constant motion",
                "Large wavelets"
        ));

        beaufortArrayList.add(new Beaufort(4,
                R.drawable.bb4,
                "4 - Moderate Breeze",
                "11 - 15",
                "5.5 - 7.9",
                "20 - 29",
                "13 - 17",
                "Small branches move, dust raised",
                "Small waves and many white horses are formed"
        ));

        beaufortArrayList.add(new Beaufort(5,
                R.drawable.bb5,
                "5 - Fresh Breeze",
                "16 - 21",
                "8.0 - 10.7",
                "29 - 38",
                "18 - 24",
                "Small trees sway and branches of moderate side move",
                "Moderate waves and many white horses"
        ));

        beaufortArrayList.add(new Beaufort(6,
                R.drawable.bb6,
                "6 - Strong Breeze",
                "22 - 27",
                "10.8 - 13.8",
                "39 - 49",
                "25 - 30",
                "Large branches move. Umbrella use becomes difficult",
                "Large waves form and extensive white foam crests"
        ));

        beaufortArrayList.add(new Beaufort(7,
                R.drawable.bb7,
                "7 - Near Gale",
                "28 - 33",
                "13.9 - 17.1",
                "50 - 61",
                "31 - 38",
                "Whole trees in motion.Effort to walk against the wind",
                "Sea heaps up and white foam from waves be blown along the wind direction"
        ));

        beaufortArrayList.add(new Beaufort(8,
                R.drawable.bb8,
                "8 - Gale",
                "34 - 40",
                "17.2 - 20.7",
                "62 - 74",
                "39 - 46",
                "Cars veer on road, twigs broke from trees",
                "Moderately high waves, edges of crests break into spindrift"
        ));

        beaufortArrayList.add(new Beaufort(9,
                R.drawable.bb9,
                "9 - Severe Gale",
                "41 - 47",
                "20.8 - 24.4",
                "75 - 88",
                "47 - 54",
                "Small trees blow over, big branches break off trees. Temporary signs blow over. Damage to tents",
                "High waves, crests begin to topple and roll over. Visibility can be affected"
        ));

        beaufortArrayList.add(new Beaufort(10,
                R.drawable.bb10,
                "10 - Storm",
                "48 - 55",
                "24.5 - 28.4",
                "89 - 102",
                "55 - 63",
                "Trees are broken off or uprooted, shingles peel of roofs",
                "Very high waves, Whole sea surface become white"
        ));

        beaufortArrayList.add(new Beaufort(11,
                R.drawable.bb11,
                "11 - Violent Storm",
                "56 - 63",
                "28.5 - 32.6",
                "103 - 117",
                "64 - 73",
                "Severe roof and vegetation damage",
                "Exceptionaly high waves, small and medium size ships disappear behind the waves"
        ));

        beaufortArrayList.add(new Beaufort(12,
                R.drawable.bb12,
                "12 - Hurricane",
                "64 -  ",
                "32.7 - ",
                "118 - ",
                "74 - ",
                "Considerable and widespread damage to vegetation, windows, mobile homes, sheds and barns",
                "Visibility seriously affected, sea completely white and the air filled with foam and spray"
        ));

    }
}
