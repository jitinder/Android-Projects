package com.example.android.inventoryappproject;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventoryappproject.data.StockContract;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    EditText mItemName;
    EditText mItemPrice;
    EditText mItemQuantity;
    EditText mItemSupplier;
    EditText mItemImage;
    Button increaseQuantity;
    Button decreaseQuantity;

    Uri receivedUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mItemName = (EditText) findViewById(R.id.edit_item_name);
        mItemPrice = (EditText) findViewById(R.id.edit_item_price);
        mItemQuantity = (EditText) findViewById(R.id.edit_item_quantity);
        mItemSupplier = (EditText) findViewById(R.id.edit_item_supplier);
        mItemImage = (EditText) findViewById(R.id.edit_item_image);

        increaseQuantity = (Button) findViewById(R.id.increase_quantity);
        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityText = mItemQuantity.getText().toString().trim();
                if(TextUtils.isEmpty(quantityText)){
                    quantityText = "0";
                }
                int quantity = Integer.parseInt(quantityText);
                quantity++;
                mItemQuantity.setText(String.valueOf(quantity));

            }
        });

        decreaseQuantity = (Button) findViewById(R.id.decrease_quantity);
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityText = mItemQuantity.getText().toString().trim();
                if(TextUtils.isEmpty(quantityText)){
                    quantityText = "0";
                }
                int quantity = Integer.parseInt(quantityText);
                quantity--;
                if(quantity < 0){
                    quantity = 0;
                }
                mItemQuantity.setText(String.valueOf(quantity));
            }
        });

        Intent received = getIntent();
        receivedUri = received.getData();

        if(receivedUri == null) {
            setTitle("Add Item");
            invalidateOptionsMenu();
        } else {
            setTitle("Edit Item");
        }

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(receivedUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
            menuItem = menu.findItem(R.id.action_reorder);
            menuItem.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_save:
                saveItem();
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case R.id.action_reorder:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {mItemSupplier.getText().toString().trim()});
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveItem(){
        String itemName = mItemName.getText().toString().trim();
        String itemPrice = mItemPrice.getText().toString().trim();
        String quantityString = mItemQuantity.getText().toString().trim();
        String itemSupplier = mItemSupplier.getText().toString().trim();
        String itemImage = mItemImage.getText().toString().trim();

        if(TextUtils.isEmpty(itemName) || TextUtils.isEmpty(itemPrice) || TextUtils.isEmpty(quantityString) || TextUtils.isEmpty(itemSupplier) ||
                TextUtils.isEmpty(itemImage)){
            Toast.makeText(this, R.string.enter_valid_info, Toast.LENGTH_SHORT).show();
            return;
        }

        int itemQuantity = Integer.parseInt(quantityString);
        if(itemQuantity < 0){
            itemQuantity = 0;
        }

        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_ITEM_NAME, itemName);
        values.put(StockContract.StockEntry.COLUMN_ITEM_PRICE, itemPrice);
        values.put(StockContract.StockEntry.COLUMN_ITEM_QUANTITY, itemQuantity);
        values.put(StockContract.StockEntry.COLUMN_ITEM_SUPPLIER, itemSupplier);
        values.put(StockContract.StockEntry.COLUMN_ITEM_PICTURE, itemImage);

        if(receivedUri == null) {
            Uri uri = getContentResolver().insert(StockContract.StockEntry.CONTENT_URI, values);
            if (uri == null) {
                Toast.makeText(this, R.string.error_saving, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.item_saved, Toast.LENGTH_SHORT).show();
            }
        } else {
            int updated = getContentResolver().update(receivedUri, values, null, null);
            if(updated == 0){
                Toast.makeText(this, R.string.error_updating, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.item_updated, Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void deleteItem(){
        if(receivedUri != null) {
            int deleted = getContentResolver().delete(receivedUri, null, null);
            if (deleted == 0) {
                Toast.makeText(this, R.string.error_deleting, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.item_deleted, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(receivedUri != null) {
            return new CursorLoader(this, receivedUri, null, null, null, null);
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.moveToFirst()){
            String name = data.getString(data.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_NAME));
            String price = data.getString(data.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_PRICE));
            int quantity = data.getInt(data.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_QUANTITY));
            String supplier = data.getString(data.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_SUPPLIER));
            String image = data.getString(data.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_PICTURE));

            mItemName.setText(name);
            mItemPrice.setText(price);
            mItemQuantity.setText(String.valueOf(quantity));
            mItemSupplier.setText(supplier);
            mItemImage.setText(image);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mItemName.setText("");
        mItemPrice.setText("");
        mItemQuantity.setText("");
        mItemSupplier.setText("");
        mItemImage.setText("");
    }

    private void showDeleteConfirmationDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.delete_confirmation);
        dialog.setNegativeButton(getString(R.string.continue_editing), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton(R.string.delete_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem();
                finish();
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}
