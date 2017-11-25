package ahv1.app.autohelpv2;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import ahv1.app.autohelpv2.Activity.Comentario;
import ahv1.app.autohelpv2.Cadastro_Login.DatabaseHelper;
import ahv1.app.autohelpv2.Cadastro_Login.LoginActivity;
import ahv1.app.autohelpv2.EditaPerfil.EditaPerfil;
import ahv1.app.autohelpv2.Maps.MapsActivityAH;
import ahv1.app.autohelpv2.adapter.TabAdapter;
import ahv1.app.autohelpv2.fragment.ForumDAO;
import ahv1.app.autohelpv2.helper.SlidingTabLayout;

//import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private ListView lista;
    private static String user = null;


    protected SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(user == null) {
            user = getIntent().getStringExtra("Username");
        }

        //inicializa toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("AutoHelp");
        setSupportActionBar(toolbar);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.Sl_Tab);
        viewPager = (ViewPager) findViewById(R.id.VP_pagina);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
       /* SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem menuItem = menu.findItem(R.id.Bpesquisa);

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            searchView = (SearchView) menuItem.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Pesquisar . . .");*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Blocalizar:
                Intent intent = new Intent(MainActivity.this, MapsActivityAH.class);
                startActivity(intent);
                return true;
            case R.id.EdPerfil:
                Intent inte = new Intent(MainActivity.this, EditaPerfil.class);
                inte.putExtra("Usuario", user);
                startActivity(inte);
                return true;
            case R.id.sair:
                deslogarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deslogarUsuario() {
        user = null;
        SharedPreferences sessao = getSharedPreferences("usuarioLogado", MODE_PRIVATE);
        SharedPreferences.Editor edit = sessao.edit();
        edit.clear().commit();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void ButtonJanela(View v) {
        System.out.println("onclik");
        if (v.getId() == R.id.floatingButton) {
            final EditText edit = new EditText(MainActivity.this.getApplicationContext());
            AlertDialog.Builder alert =  new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Publicar Comentario: ")
                    .setMessage("Digite a dúvida que deseja publicar no mural")
                    .setView(edit)
                    .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            lista = (ListView) findViewById(R.id.List_id);
                            Comentario post = new Comentario();
                            Locale locale = new Locale("pt", "BR");
                            GregorianCalendar calendar = new GregorianCalendar();
                            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale);

                            post.setDataPost(formatador.format(calendar.getTime()));
                            post.setUsuario(getIntent().getStringExtra("Username"));
                            post.setTxt_comentario(edit.getText().toString());

                            System.out.println(edit.getText().toString());

                            ForumDAO persist = new ForumDAO(MainActivity.this);
                            String resultado = persist.GuardaPost(post, lista, MainActivity.this);

                            Toast.makeText(MainActivity.this, ""+resultado+"", Toast.LENGTH_SHORT).show();

                        }
                    })
                    // negative button
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Comentario não publicado", Toast.LENGTH_SHORT).show();
                        }
                    });

            alert.create().show();
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initLFloatingButtons() {

        final int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());

        final ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, size, size);
            }
        };

        ImageButton floatingButton = ((ImageButton) findViewById(R.id.floatingButton));
        floatingButton.setOutlineProvider(viewOutlineProvider);
        floatingButton.setClipToOutline(true);

    }


}