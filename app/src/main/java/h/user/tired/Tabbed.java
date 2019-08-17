package h.user.tired;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tabbed extends AppCompatActivity {
    public Fragment Tabb(){
        return new Tab3Fragment();


    }
    private static final String TAG = "Tabbed";

    private SectionsPageAdapter mSectionsPageAdapter;
    private Intent intw;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

try {
    mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager(),this);

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.container);
    mViewPager.setOffscreenPageLimit(3);

    setupViewPager(mViewPager);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(mViewPager);
}catch (Exception ex){
    Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
}}

    private void setupViewPager(ViewPager viewPager) {
       try {
           SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager(),this);
           adapter.addFragment(new Tab1Fragment(), "Home");
           adapter.addFragment(new Tab3Fragment(), "Profile");
           viewPager.setAdapter(adapter);
       }catch (Exception ex){
           Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_SHORT).show();


       }
       }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_SHORT).show();
finish();
        System.exit(1);
intw=(Intent)new Intent(Intent.ACTION_MAIN);
        intw.addCategory(Intent.CATEGORY_HOME);
        intw.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intw);
    }
}
