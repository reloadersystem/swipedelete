package apptablet.sacooliveros.edu.pe.swipedelete;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements  RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    private RecyclerView recyclerView;
    private RViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback simpleCallback =
                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, MainActivity.this);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }




    private void init() {

        this.recyclerView = findViewById(R.id.recycler_view);
        this.layoutManager = new LinearLayoutManager(getBaseContext());
        this.adapter = new RViewAdapter(getBaseContext(), getListaCoche());

    }


    private ArrayList<Coche> getListaCoche()
    {
        ArrayList<Coche> arrayListCoches= new ArrayList<>();

        arrayListCoches.add(new Coche(R.drawable.a_1, "Compendios-Tomo1-Algebra.pdf"));
        arrayListCoches.add(new Coche(R.drawable.a_2, "Compendios-Tomo2-Física.pdf"));
        arrayListCoches.add(new Coche(R.drawable.a_3, "Hélico Diapositivas-Tomo1-Algebra.pdf"));
        arrayListCoches.add(new Coche(R.drawable.a_4, "Hélico Diapositivas-Tomo1-Química.pdf"));
        arrayListCoches.add(new Coche(R.drawable.a_5, "Problemas Propuestos-Mes1-Algebra.pdf"));
        arrayListCoches.add(new Coche(R.drawable.a_6, "Computación-Tomo2.pdf"));

        return arrayListCoches;


    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof RViewAdapter.DataObjectHolder){


            String nombre = getListaCoche().get(viewHolder.getAdapterPosition()).getNombre();
            final Coche cocheBorrado = getListaCoche().get(viewHolder.getAdapterPosition());
            final int deletedIntex = viewHolder.getAdapterPosition();

            adapter.removeItem(viewHolder.getAdapterPosition());

            recuperarCocheBorrado(viewHolder,nombre,cocheBorrado,deletedIntex);

        }
    }

     private  void recuperarCocheBorrado(RecyclerView.ViewHolder viewHolder,  String nombre,
                                       final Coche cocheBorrado, final int deletedIntex)
    {

        Snackbar snackbar = Snackbar.make(((RViewAdapter.DataObjectHolder)viewHolder).layoutABorrar,
                nombre + " eliminado", Snackbar.LENGTH_LONG);

        snackbar.setAction("Deshacer", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.restoreItem(cocheBorrado, deletedIntex);

            }
        });

        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();


    }





}
