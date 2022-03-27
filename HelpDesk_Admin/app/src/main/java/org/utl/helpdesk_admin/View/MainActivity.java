package org.utl.helpdesk_admin.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import org.utl.helpdesk_admin.R;
import org.utl.helpdesk_admin.databinding.ActivityMainBinding;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

 //   Fragment fragmentHome, fragmentTickets, fragmentEmployees;
 //   FragmentTransaction transaction;

    private int tabIndex =0;

    //Otra alternativa para mostrar los fragments es por medio de Navigation Components
    NavHostFragment navHostFragment;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        fragmentHome = new HomeFragment();
//        fragmentTickets = new TicketsFragment();
//        fragmentEmployees = new EmployeesFragment();

        //Carga por default de un fragment en el activity
//        getSupportFragmentManager()
//                .beginTransaction()
//               .add(R.id.contenedor, fragmentHome)
  //              .commit();
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                          .findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabIndex = binding.tabLayout.getSelectedTabPosition();
                //Toast.makeText(MainActivity.this, "Tab: "+tabIndex, Toast.LENGTH_SHORT).show();
                loadFragment(tabIndex);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void loadFragment(int tabIndex){
 //       transaction = getSupportFragmentManager().beginTransaction();
        switch (tabIndex){
            case 0:
                navController.navigate(R.id.homeFragment);
 //               transaction.replace(R.id.contenedor, fragmentHome);
                break;
            case 1:
                navController.navigate(R.id.ticketsFragment);
//               transaction.replace(R.id.contenedor, fragmentTickets);
                break;
            case 2:
                navController.navigate(R.id.employeesFragment);
//               transaction.replace(R.id.contenedor, fragmentEmployees);
                break;
        }
        binding.tabLayout.getTabAt(tabIndex).select();
 //       transaction.commit();
    }

    public void onBackPressed(){
        switch (tabIndex){
            case 0:
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Seguro que quieres salir?")
                        .setConfirmText("Sí")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .setCancelButton("Cancelar", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
                break;
            case 1:
                loadFragment(0);
                break;
            case 2:
                loadFragment(1);
                break;
        }
    }

}