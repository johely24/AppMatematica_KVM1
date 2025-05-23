package com.example.appmatematica_kvm1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        // Instancia de la calculadora
        Calculadora calculadora = new Calculadora();

        // Referencias a los elementos de pantalla_principal.xml
        EditText editTextNumero1 = findViewById(R.id.editTextNumero1);
        EditText editTextNumero2 = findViewById(R.id.editTextNumero2);
        Button buttonSumar = findViewById(R.id.buttonSumar);
        Button buttonRestar = findViewById(R.id.buttonRestar);
        Button buttonMultiplicar = findViewById(R.id.buttonMultiplicar);
        Button buttonDividir = findViewById(R.id.buttonDividir);
        TextView textViewResultado = findViewById(R.id.textViewResultado);

        // Asignar funcionalidad a los botones
        buttonSumar.setOnClickListener(v -> operar(editTextNumero1, editTextNumero2, textViewResultado, calculadora, "+"));
        buttonRestar.setOnClickListener(v -> operar(editTextNumero1, editTextNumero2, textViewResultado, calculadora, "-"));
        buttonMultiplicar.setOnClickListener(v -> operar(editTextNumero1, editTextNumero2, textViewResultado, calculadora, "*"));
        buttonDividir.setOnClickListener(v -> operar(editTextNumero1, editTextNumero2, textViewResultado, calculadora, "/"));
    }

    // Método para realizar operaciones matemáticas llamando a Calculadora
    private void operar(EditText editTextNumero1, EditText editTextNumero2, TextView textViewResultado, Calculadora calculadora, String operacion) {
        if (!editTextNumero1.getText().toString().isEmpty() && !editTextNumero2.getText().toString().isEmpty()) {
            double num1 = Double.parseDouble(editTextNumero1.getText().toString());
            double num2 = Double.parseDouble(editTextNumero2.getText().toString());
            double resultado;

            try {
                switch (operacion) {
                    case "+":
                        resultado = calculadora.sumar(num1, num2);
                        break;
                    case "-":
                        resultado = calculadora.restar(num1, num2);
                        break;
                    case "*":
                        resultado = calculadora.multiplicar(num1, num2);
                        break;
                    case "/":
                        resultado = calculadora.dividir(num1, num2);
                        break;
                    default:
                        textViewResultado.setText("Operación no válida");
                        return;
                }

                textViewResultado.setText("Resultado: " + resultado);

                // Limpiar los EditText después de cada operación
                editTextNumero1.setText("");
                editTextNumero2.setText("");
            } catch (ArithmeticException e) {
                textViewResultado.setText("Error: " + e.getMessage());
            }
        } else {
            textViewResultado.setText("Ingresa ambos números");
        }
    }
}


