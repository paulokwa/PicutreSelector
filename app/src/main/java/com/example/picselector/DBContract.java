package com.example.picselector;

import android.provider.BaseColumns;

public final class DBContract {
    private DBContract() {}

    /* Inner class that defines the table contents */
    public static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "PictureDetails";
        public static final String COLUMN_PICTURE_NAME = "pictureName";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_COMMENT = "comment";
    }
}


