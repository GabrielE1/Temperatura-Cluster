package escobar.gabriel.temperaturacluster;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int notificationID;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationID = 1;
        TextView temperatura = (TextView) findViewById(R.id.temperaturaView);
        temp = getTemp();
        temperatura.setText(temp);
        notification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    private String getTemp(){
        Random rand = new Random();


        String temp = ""+rand.nextInt(100)+"."+rand.nextInt(10)+"°C";
        return temp;
    }
    public void onClickAtualizar(View v) {
        TextView temperatura = (TextView) findViewById(R.id.temperaturaView);
        this.temp = getTemp();
        temperatura.setText(this.temp);
        notification();

    }
    public void notification() {
       Intent i = new Intent(this,MainActivity.class);
        i.putExtra("notificationID",notificationID);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification =  new Notification(R.drawable.notification_template_icon_bg,"Temperatura Atualizada",System.currentTimeMillis());
        CharSequence from = "Temperatura: ";
        CharSequence message = this.temp;
        notification.setLatestEventInfo(this,from,message,pi);
        notification.vibrate = new long[] {100,100,100,100};
        nm.notify(notificationID,notification);
    }
}
