package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;


public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;
    //variavel pro datepicker
    private DatePickerDialog datePicker;
    int ano, mes, dia;
    //variavel para obter a data atual
    Calendar dataAtual;
    //variavel para data formatada
    String dataFormatada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o biding
        binding =  FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        //instanciar a data atual
        dataAtual = Calendar.getInstance();
        //obter ano mes e dia da data atual
        ano = dataAtual.get(Calendar.YEAR);
        mes = dataAtual.get(Calendar.MONTH);
        dia = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia)->{
            //ao escolher uma data no datPicker cai aq

        }, ano, mes, dia);

        //ação do click do  otao de seleção da data
        binding.data.setOnClickListener(v ->{
            datePicker.show();
        });

        //retorna a view raiz (root) do biding
        return binding.getRoot();
    }
}