package com.tjeannin.provigen.sample;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;
import com.tjeannin.provigen.annotation.Id;
import com.tjeannin.provigen.helper.ContractUtil;
import com.tjeannin.provigen.helper.ProviGenUriBuilder;

/**
 * Created by Dre on 11.04.2016.
 *
 */
public class SampleContract {

    public static class Person implements ProviGenBaseContract {

        public static final String TABLE_NAME = "person";

        @Column(Column.Type.INTEGER)
        public static final String AGE = "age";

        @Column(Column.Type.TEXT)
        public static final String NAME = "name";

        @ContentUri
        public static final Uri CONTENT_URI = ProviGenUriBuilder.contentUri(SampleContentProvider.AUTHORITY, TABLE_NAME);

        public static final String[] DEFAULT_PROJECTION = new String[] {
                _ID,
                AGE,
                NAME
        };

        public static final String[] JOIN_PROJECTION = new String[] {
                _ID,
                AGE,
                NAME,
                ContractUtil.fullName(Specialty.TABLE_NAME, Specialty.NAME)
        };
    }

    // not implement ProviGenBaseContract interface (because non-autoincrement primary key)
    public static class Specialty {

        public static final String TABLE_NAME = "specialty";

        @Id(autoincrement = false)
        @Column(Column.Type.TEXT)
        public static final String _ID = "_id";

        @Column(Column.Type.INTEGER)
        public static final String NAME = "name";

        @ContentUri
        public static final Uri CONTENT_URI = ProviGenUriBuilder.contentUri(SampleContentProvider.AUTHORITY, TABLE_NAME);

        public static final String[] DEFAULT_PROJECTION = new String[] {
                _ID,
                NAME
        };
    }

    // not implement ProviGenBaseContract interface (because non-autoincrement composite primary key)
    public static class Passport {

        public static final String TABLE_NAME = "passport";

        @Id
        @Column(Column.Type.TEXT)
        public static final String SERIES = "series";

        @Id
        @Column(Column.Type.INTEGER)
        public static final String NUMBER = "number";

        @ContentUri
        public static final Uri CONTENT_URI = ProviGenUriBuilder.contentUri(SampleContentProvider.AUTHORITY, TABLE_NAME);

        public static final String[] DEFAULT_PROJECTION = new String[] {
                SERIES,
                NUMBER
        };
    }

    /**
     * Contract class for second database
     */
    public static class PersonSecondDb implements ProviGenBaseContract {

        public static final String TABLE_NAME = "persons_second";

        @Column(Column.Type.INTEGER)
        public static final String AGE = "age";

        @Column(Column.Type.TEXT)
        public static final String NAME = "name";

        @ContentUri
        public static final Uri CONTENT_URI = ProviGenUriBuilder.contentUri(SampleContentProvider.AUTHORITY, TABLE_NAME, SampleContentProvider.SECOND_DB_NAME); // add name of second database

        public static final String[] DEFAULT_PROJECTION = new String[] {
                _ID,
                AGE,
                NAME
        };
    }
}
