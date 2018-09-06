package com.example.android.inventoryappproject.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Sidak Pasricha on 12-Aug-18.
 */

public class StockProvider extends ContentProvider {

    //DB Helper
    StockDbHelper helper;

    //Uri Matcher Codes
    private static final int STOCK = 0;
    private static final int STOCK_ID = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(StockContract.CONTENT_AUTHORITY, StockContract.PATH_STOCK, STOCK);
        sUriMatcher.addURI(StockContract.CONTENT_AUTHORITY, StockContract.PATH_STOCK + "/#", STOCK_ID);
    }

    @Override
    public boolean onCreate() {
        helper = new StockDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor cursor;

        switch (match){
            case STOCK:
                cursor = db.query(StockContract.StockEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
                break;
            case STOCK_ID:
                selection = StockContract.StockEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(StockContract.StockEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Cannot Query Unknown Uri " +uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(),  uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);

        switch (match){
            case STOCK:
                return StockContract.StockEntry.CONTENT_LIST_TYPE;
            case STOCK_ID:
                return StockContract.StockEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri " +uri+ " with Unknown match " +match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = sUriMatcher.match(uri);

        switch (match) {
            case STOCK:
                return insertItem(uri, values);
            default:
                throw new IllegalArgumentException("Insertion not supported for " +uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(StockContract.StockEntry.TABLE_NAME, null, values);

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int deleted;

        switch (match){
            case STOCK:
                deleted = db.delete(StockContract.StockEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case STOCK_ID:
                selection = StockContract.StockEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                deleted = db.delete(StockContract.StockEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Cannot delete with uri " +uri);
        }
        if(deleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);

        switch (match){
            case STOCK:
                return updateItem(uri, values, selection, selectionArgs);
            case STOCK_ID:
                selection = StockContract.StockEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateItem(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Updating is not supported for " +uri);
        }
    }

    private int updateItem(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        SQLiteDatabase db = helper.getWritableDatabase();

        int updated = db.update(StockContract.StockEntry.TABLE_NAME, values, selection, selectionArgs);

        if(updated != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updated;
    }
}
