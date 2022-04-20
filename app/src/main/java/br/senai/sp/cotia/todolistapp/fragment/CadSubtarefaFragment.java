package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadSubTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;


public class CadSubtarefaFragment extends Fragment {
    private FragmentCadSubTarefaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o biding
        binding =  FragmentCadSubTarefaBinding.inflate(getLayoutInflater(), container, false);
        //retorna a view raiz (root) do biding
        return binding.getRoot();
    }
}