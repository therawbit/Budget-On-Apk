package np.com.sudarshandevkota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import np.com.sudarshandevkota.fragments.HomeFragment;
import np.com.sudarshandevkota.fragments.PendingFragment;
import np.com.sudarshandevkota.fragments.StatementFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment homeFragment = new HomeFragment();
    Fragment statementFragment = new StatementFragment();
    Fragment pendingFragment = new PendingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.pending:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, pendingFragment).commit();
                            return true;
                        case R.id.home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                            return true;
                        case R.id.statement:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,statementFragment).commit();
                            return true;
                    }
                    return false;
                }

        );

    }
}