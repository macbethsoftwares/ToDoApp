package com.naeem.todoapp;



import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<profile>myDataLists;

    public MyAdapter(List<profile> myDataLists) {
        this.myDataLists = myDataLists;
    }

    
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        profile md=myDataLists.get(i);

        //  viewHolder.txtid.setText(md.getId());
        viewHolder.txtname.setText(md.getFirstName()+md.getLastName());
        viewHolder.txtemail.setText(md.getEmail());

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DatabaseApp db = DatabaseApp.getInstance(v.getContext());

                profile profile = new profile(md.getFirstName(),md.getLastName(),md.getEmail());
                profile.setFirstName(md.getFirstName());
                profile.setDesc(md.getLastName());
                profile.setEmail(md.getEmail());
                profile.setId(md.getId());
                db.Dao().delete(profile);

                Toast.makeText(v.getContext(), "Values deleted.",Toast.LENGTH_LONG).show();

                final Context context = v.getContext();
                Intent intent = new Intent(context , MainActivity.class);
                context.startActivity(intent);

            }
        });

        viewHolder.buttonUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DatabaseApp db = DatabaseApp.getInstance(v.getContext());

                profile profile = new profile(md.getFirstName(),md.getLastName(),md.getEmail());
                profile.setFirstName(md.getFirstName());
                profile.setDesc(md.getLastName());
                profile.setEmail(md.getEmail());
                profile.setId(md.getId());


                final Context context = v.getContext();
                Intent intent = new Intent(context , updateData.class);
                intent.putExtra("key", md.getId());
                context.startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        return myDataLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtid,txtname,txtemail,txtcity;
        private Button deleteButton;
        private Button buttonUpdate;
        public ViewHolder( View itemView) {
            super(itemView);
            //txtid=(TextView)itemView.findViewById(R.id.txt_id);
            txtname=(TextView)itemView.findViewById(R.id.name);
            txtemail=(TextView)itemView.findViewById(R.id.email);
            deleteButton=(Button)itemView.findViewById(R.id.buttonDelete);
            buttonUpdate=(Button)itemView.findViewById(R.id.buttonUpdate);
            
        }
    }
}
