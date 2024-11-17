package com.example.calculatrice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonPlus, buttonMoins, buttonDiv, buttonMul, buttonC, buttonEgal, buttonPoint;
    EditText ecran;


    private double chiffre1;
    private boolean clicOperateur = false;
    private boolean update = false;
    private String operateur = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }


    private void initViews() {

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMoins = findViewById(R.id.buttonMoins);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMul = findViewById(R.id.buttonMul);
        buttonC = findViewById(R.id.buttonC);
        buttonEgal = findViewById(R.id.buttonEgal);
        buttonPoint = findViewById(R.id.buttonPoint);
        ecran = findViewById(R.id.EditText01);


        button0.setOnClickListener(v -> ajouterChiffre("0"));
        button1.setOnClickListener(v -> ajouterChiffre("1"));
        button2.setOnClickListener(v -> ajouterChiffre("2"));
        button3.setOnClickListener(v -> ajouterChiffre("3"));
        button4.setOnClickListener(v -> ajouterChiffre("4"));
        button5.setOnClickListener(v -> ajouterChiffre("5"));
        button6.setOnClickListener(v -> ajouterChiffre("6"));
        button7.setOnClickListener(v -> ajouterChiffre("7"));
        button8.setOnClickListener(v -> ajouterChiffre("8"));
        button9.setOnClickListener(v -> ajouterChiffre("9"));
        buttonPlus.setOnClickListener(v -> setOperateur("+"));
        buttonMoins.setOnClickListener(v -> setOperateur("-"));
        buttonMul.setOnClickListener(v -> setOperateur("*"));
        buttonDiv.setOnClickListener(v -> setOperateur("/"));
        buttonC.setOnClickListener(v -> reset());
        buttonEgal.setOnClickListener(v -> calculer());
        buttonPoint.setOnClickListener(v -> ajouterChiffre("."));
    }


    private void ajouterChiffre(String chiffre) {
        if (update) {
            update = false;
            ecran.setText("");
        }
        ecran.append(chiffre);
    }


    private void setOperateur(String op) {
        chiffre1 = Double.parseDouble(ecran.getText().toString());
        operateur = op;
        clicOperateur = true;
        update = true;
    }


    private void calculer() {
        double chiffre2 = Double.parseDouble(ecran.getText().toString());
        double result = 0;

        switch (operateur) {
            case "+":
                result = chiffre1 + chiffre2;
                break;
            case "-":
                result = chiffre1 - chiffre2;
                break;
            case "*":
                result = chiffre1 * chiffre2;
                break;
            case "/":
                if (chiffre2 != 0) {
                    result = chiffre1 / chiffre2;
                } else {
                    ecran.setText("Erreur");
                    return;
                }
                break;
        }
        ecran.setText(String.valueOf(result));
        update = true;
        clicOperateur = false;
    }


    private void reset() {
        ecran.setText("");
        chiffre1 = 0;
        operateur = "";
        clicOperateur = false;
        update = false;
    }
}
