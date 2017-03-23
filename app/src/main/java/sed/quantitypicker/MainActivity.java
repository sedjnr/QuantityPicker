package sed.quantitypicker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;
import static android.R.attr.order;
import static android.R.id.message;
import static sed.quantitypicker.R.id.checkbox;
import static sed.quantitypicker.R.id.orderSummary;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;

    public void add1(View view) {
        if (quantity == 10) {
            Toast.makeText(this, "TOO MUCHHHHHH", Toast.LENGTH_SHORT).show();
            return;
        }
        quantityUpdate(quantity += 1);
    }

    public void subtract1(View view) {
        if (quantity == 0) {
            Toast.makeText(this, "TOO MUCHHHHHH", Toast.LENGTH_SHORT).show();
            return;
        }
        quantityUpdate(quantity -= 1);
    }

    private int calculatePrice() {
        CheckBox syrupChecked = (CheckBox) findViewById(R.id.Syrup);
        boolean hasSyrup = syrupChecked.isChecked();

        CheckBox chocoChecked = (CheckBox) findViewById(R.id.Choco);
        boolean hasChoco = chocoChecked.isChecked();


        int cost = 5;
        if (hasChoco) {
            cost += 1;
        }
        if (hasSyrup) {
            cost += 2;
        }
        return quantity * cost;
    }

    public void orderUpdate(View view) {
        orderSum(message());

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your order Summary ");

                        CheckBox syrupChecked = (CheckBox) findViewById(R.id.Syrup);
                        boolean hasSyrup = syrupChecked.isChecked();

                        CheckBox chocoChecked = (CheckBox) findViewById(R.id.Choco);
                        boolean hasChoco = chocoChecked.isChecked();

                        EditText addName = (EditText) findViewById(R.id.Name);
                        String nameAdd = addName.getText().toString();

                        String message = "Hello " + nameAdd + "!";
                        message += "\nYou have bought " + quantity + " pancakes.";
                        message += "\nAdd syrup? - " + hasSyrup;
                        message += "\nAdd Chocolate? - " + hasChoco;
                        message += "\nThe total cost is £" + calculatePrice() + ".";
                        message += "\nThank you for shopping with us!";

        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        orderSum("");
    }


    private String message() {
        CheckBox syrupChecked = (CheckBox) findViewById(R.id.Syrup);
        boolean hasSyrup = syrupChecked.isChecked();

        CheckBox chocoChecked = (CheckBox) findViewById(R.id.Choco);
        boolean hasChoco = chocoChecked.isChecked();

        EditText addName = (EditText) findViewById(R.id.Name);
        String nameAdd = addName.getText().toString();

        String message = "Hello " + nameAdd + "!";
        message += "\nYou have bought " + quantity + " pancakes.";
        message += "\nAdd syrup? - " + hasSyrup;
        message += "\nAdd Chocolate? - " + hasChoco;
        message += "\nThe total cost is £" + calculatePrice() + ".";
        message += "\nThank you for shopping with us!";

        return message;
    }

    public void quantityUpdate(int quantity) {
        TextView quantityView = (TextView) findViewById(R.id.quantity);
        quantityView.setText(String.valueOf(quantity));
    }

    public void orderSum(String message) {


        TextView messageView = (TextView) findViewById(orderSummary);
        messageView.setText(message);
    }

}