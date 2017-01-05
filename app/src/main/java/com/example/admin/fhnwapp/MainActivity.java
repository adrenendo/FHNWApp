package com.example.admin.fhnwapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.fragments.FAQFragment;
import com.example.admin.fragments.LinksFragment;
import com.example.admin.fragments.MainFragment;
import com.example.admin.fragments.SettingsFragment;
import com.example.admin.fragments.SlideshowFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static boolean isInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem mItem = (MenuItem) findViewById(R.id.action_settings);
/*
        mItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                             @Override
                                             public boolean onMenuItemClick(MenuItem item) {

                                                 return true;
                                             }
                                         }
        );
*/
        if(isInit) {
            android.app.FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            android.app.FragmentManager fm = getFragmentManager();

            fm.beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.app.FragmentManager fm = getFragmentManager();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_faq) {
            // Handle the camera action
            fm.beginTransaction().replace(R.id.content_frame, new FAQFragment()).commit();
        } else if (id == R.id.nav_notifications) {
            sendNotification("Event change: No lessons today!");
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_links) {
            fm.beginTransaction().replace(R.id.content_frame, new LinksFragment()).commit();
        } else if (id == R.id.nav_slideshow) {
            fm.beginTransaction().replace(R.id.content_frame, new SlideshowFragment()).commit();
            fm.executePendingTransactions();

            // Set up Slideshow
            ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
            SlideAdapter adapterView = new SlideAdapter(this);
            mViewPager.setAdapter(adapterView);

        } else if (id == R.id.nav_send) {

        } else if(id == R.id.nav_homepage) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fhnw.ch/business/msc-bis"));
            startActivity(browserIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    @Override
    public void onDeletedMessages() {

        sendNotification("Message Deleted On server");
        super.onDeletedMessages();
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {

        sendNotification("Received: " + data.getString("message"));
        super.onMessageReceived(from, data);
    }

    @Override
    public void onMessageSent(String msgId) {

        sendNotification("Message Sent: " + msgId);
        super.onMessageSent(msgId);
    }

    @Override
    public void onSendError(String msgId, String error) {

        sendNotification("Message Sent Error: " + msgId + "Error: " + error);
        super.onSendError(msgId, error);
    }
*/
    private void sendNotification(String msg) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Message", msg);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_menu_send)
                .setContentTitle("Notification Message").setContentText(msg)
                .setAutoCancel(true).setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

    }




}