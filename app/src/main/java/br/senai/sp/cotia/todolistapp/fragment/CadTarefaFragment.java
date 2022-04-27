package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;
    //variavel pro datepicker
    private DatePickerDialog datePicker;
    int ano, mes, dia;
    //variavel para obter a data atual
    Calendar dataAtual;
    //variavel para data formatada
    String dataFormatada = "";
    //variavel para a database
    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia a database
        database = AppDatabase.getDatabase(getContext());

        //instanciar o biding
        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        //instanciar a data atual
        dataAtual = Calendar.getInstance();
        //obter ano mes e dia da data atual
        ano = dataAtual.get(Calendar.YEAR);
        mes = dataAtual.get(Calendar.MONTH);
        dia = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
            //ao escolher uma data no datPicker cai aq
            //passar para as variaveis globais
            ano = year;
            dia = day;
            mes = month;
            //formata a data
            dataFormatada = String.format("%02d/%02d/%04d", day, month + 1, year);
            //aplica a data formatada no botao
            binding.data.setText(dataFormatada);
        }, ano, mes, dia);

        //ação do click do  otao de seleção da data
        binding.data.setOnClickListener(v -> {
            datePicker.show();
        });

        //listener do botao salvar
        binding.salvar.setOnClickListener(v -> {
            if (binding.titulo.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Informe um título para a tarefa", Toast.LENGTH_SHORT).show();
            } else if (dataFormatada.isEmpty()) {
                Toast.makeText(getContext(), "Informe uma data para a tarefa", Toast.LENGTH_SHORT).show();
            } else {
                //criar uma tarefa
                Tarefa tarefa = new Tarefa();
                tarefa.setTitulo(binding.titulo.getText().toString());
                tarefa.setDescricao(binding.descricao.getText().toString());
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                //criar um calendar
                Calendar dataPrevista = Calendar.getInstance();
                dataPrevista.set(ano, mes, dia);
                //passa os milisegundos da data para a data prevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());
                //salvar a tarefa
                new InsertTarefa().execute(tarefa);
            }
        });

        //retorna a view raiz (root) do biding
        return binding.getRoot();
    }

    //Asynctask para inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
           //pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                //chamar o metodo para salvar a tarefa
                database.getTarefaDao().insert(t);
                //retornar
                return "ok";
            }catch (Exception error){
                error.printStackTrace();
                return error.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ok")){
                Log.w("result","tudo certo por aq man");
                Toast.makeText(getContext(), "Tarefa inserida com sucesso", Toast.LENGTH_LONG).show();
                //voltar ao fragment anterior
                getActivity().onBackPressed();
            }else{
                Log.w("result",result);
                Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    }
}