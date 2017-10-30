package ahv1.app.autohelpv2.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ahv1.app.autohelpv2.R;
import ahv1.app.autohelpv2.adapter.ComentarioAdapter;
import ahv1.app.autohelpv2.fragment.ForumDAO;

public class SearchableActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private ArrayList ListAux;
    private ArrayList<? extends Comentario> Lista;
    private ListView pesquisa;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        System.out.println("Entrei no search");
        //inicializa toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarPesquisa);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        ForumDAO forumDAO = new ForumDAO(this);
        Lista = forumDAO.getListaItens();
        ListAux = new ArrayList<>();

        pesquisa = (ListView) findViewById(R.id.listPesquisa);
        adapter = new ComentarioAdapter(this, (ArrayList<Comentario>) ListAux);
        pesquisa.setAdapter(adapter);
        hendleSearch(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        hendleSearch(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem menuItem = menu.findItem(R.id.Bpesquisa);

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            searchView = (SearchView) menuItem.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Pesquisar . . .");
        return true;
    }

    public void hendleSearch(Intent intent){
        if(Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())){
            String q = intent.getStringExtra(SearchManager.QUERY);
            toolbar.setTitle(q);
            filterProb(q);
        }
    }

    public void filterProb(String q){
        ListAux.clear();
        for(int i = 0; i <= Lista.size(); i++ ){
            if( !ListAux.contains(q) &&
                    Lista.get(i).getTxt_comentario().startsWith(q.toLowerCase())){
                    ListAux.add(Lista.get(i));
            }
        }
    }
}
