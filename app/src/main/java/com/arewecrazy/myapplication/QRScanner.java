package com.arewecrazy.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.nfc.tech.NfcBarcode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRScanner extends AppCompatActivity {

    String Tag = "Testing";
    private Button generate, scan;
    private EditText mytext;
    private ImageView qr_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        generate = findViewById(R.id.generate);
        scan = findViewById(R.id.scan);
        mytext = findViewById(R.id.text);
        qr_code = findViewById(R.id.qrcode);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mytext.getText().toString();
                if (text!= null && !text.isEmpty()){
                    try{
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        qr_code.setImageBitmap(bitmap);
                    }
                    catch (WriterException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(QRScanner.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
             final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
             if (result !=null && result.getContents() !=null){

                 new AlertDialog.Builder(QRScanner.this)
                 .setTitle("Scan Result")
                 .setMessage(result.getContents())
                 .setPositiveButton("Copy", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                         ClipData data = ClipData.newPlainText("result", result.getContents());
                         manager.setPrimaryClip(data);
                     }
                 }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                     }
                 }) .create().show();


             }

        super.onActivityResult(requestCode, resultCode, data);
    }




    @Override
    protected void onStart(){
        super.onStart();
        Log.i(Tag, "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(Tag, "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(Tag, "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(Tag, "onStop");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(Tag, "onRestart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(Tag, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(Tag, "onSaveInstantState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(Tag, "onRestoreInstantState");
    }
}