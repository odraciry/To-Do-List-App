package br.senai.sp.cotia.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.adapter.TarefaAdapter;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;
    //variavel para lista
    private List<Tarefa> tarefas;
    //variavle pro adapter
    private TarefaAdapter adapter;
    //variavel para database
    private AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o biding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(), container, false);

        binding.btNovaTarefa.setOnClickListener(v ->{
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
        });

        //retorna a view raiz (root) do biding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>>{
        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            super.onPostExecute(tarefas);
        }
    }
}