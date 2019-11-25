package com.example.fraza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* Widoki */
    private Button buttonCopyText;
    private Button buttonFindPhrase;
    private Button buttonReset;
    private TextView textViewFoundPhrase;
    private TextView textViewCopiedText;

    /* Zmienne lokalne */
    private String initialText;
    private String phrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Inicjalizacja widoków */
        buttonCopyText = (Button) findViewById(R.id.buttonCopyText);
        buttonFindPhrase = (Button) findViewById(R.id.buttonFindPhrase);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        textViewFoundPhrase = (TextView) findViewById(R.id.textViewFoundPhrase);
        textViewCopiedText = (TextView) findViewById(R.id.textViewCopiedText);

        /* Akcja przycisku */
        buttonCopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String clip = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                if (clip != null) {
                    textViewCopiedText.setText(clip);
                    initialText = textViewCopiedText.getText().toString();
                } else {
                    textViewFoundPhrase.setText("Schowek jest pusty.");

                }
            }
        });

        /* Akcja przycisku */
        buttonFindPhrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!textViewCopiedText.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Znajdź fraze");

                    final EditText editTextPhrase = new EditText(MainActivity.this);
                    editTextPhrase.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(editTextPhrase);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            phrase = editTextPhrase.getText().toString();

                            if (phrase.length() > 0) {
                                textViewFoundPhrase.setText(findPhrase(phrase));
                            } else {
                                textViewCopiedText.setText(initialText);
                                textViewFoundPhrase.setText("Nie mogę nic znaleźć?");

                            }
                        }
                    });
                    builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    textViewFoundPhrase.setText("Najpierw coś skopiuj.");
                }
            }
        });

                 }
            }
        }
    }
}
