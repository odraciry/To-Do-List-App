package br.senai.sp.cotia.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{
    //variavel para lista de tarefas
    private List<Tarefa> tarefas;
    //variavel para o Context
    private Context context;

    //cpnstrutor que recebe os parametros para o adapter
    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas = lista;
        this.context = contexto;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflar a view do viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefa, parent, false);
        //retornar uma viewHolder
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        // obter a tarefa atraves da position
        Tarefa t = tarefas.get(position);
        //tansportar a informação da tarefa para o holder
        holder.tvTitulo.setText(t.getTitulo());
        //formata a data e exibe no textView
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));

        //verifica se esta concluida
        if(t.isConcluida()){
            holder.tvStatus.setText("Tarefa finalizada");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.verdeMedio));
        }else if(t.isAtrasado()){
            holder.tvStatus.setText("Tarefa atrasada");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.vermelho));
        }
    }

    @Override
    public int getItemCount() {
        if (tarefas != null) {
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvData, tvStatus;
        ProgressBar pgProgresso;
        public TarefaViewHolder(View view){
            super(view);
            //passar da view para os components
            tvTitulo = view.findViewById(R.id.nome_tarefa);
            tvData = view.findViewById(R.id.data_tarefa);
            tvStatus = view.findViewById(R.id.data_tarefa);
            pgProgresso = view.findViewById(R.id.progresso_tarefa);


        }
    }
}
