package tc.ex_timc_v1.dev_cl.Singleton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.List;

import tc.ex_timc_v1.MainActivity;
import tc.ex_timc_v1.R;
import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 13.03.2015.
 */
public class SingletonAdapter extends BaseAdapter {

    public Context context;
    private List<Singleton_gv> idnames;

    public SingletonAdapter(Context context, List<Singleton_gv> products) {
        this.context = context;
        this.idnames = products;
    }

    @Override
    public int getCount() {
        return idnames.size();
    }

    @Override
    public Object getItem(int position) {
        return idnames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Customization_Button button;

        if (convertView == null) {
            button = new Customization_Button(context);
            button.setText(idnames.get(position).getName());
        } else {
            button = (Customization_Button) convertView;
        }
        button.setId(position);
        button.setTextSize(50);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Var_class.result_array_send[0].toString().split("/").length == 1)
                {

                    Var_class.result_array[1] = ((Singleton_gv) getItem(position)).getidname() + "/123";;

                    Var_class.result_array_send[0] = ((Singleton_gv) getItem(position)).getidname() + "/123";
                    Var_class.result_array_showing[0] = ((Singleton_gv) getItem(position)).getidname()  + "/123";;

                    ((MainActivity) context).func_auth_restart();
                }
                else if (Var_class.result_array_send[0].toString().split("/").length == 2) {

                    Var_class.result_array[1] = Var_class.result_array[1].toString().split("/")[0].toString() + "/" +((Singleton_gv) getItem(position)).getidname();

                    Var_class.result_array_send[0] = Var_class.result_array_send[0].toString().split("/")[0].toString() + "/" + ((Singleton_gv) getItem(position)).getidname();
                    Var_class.result_array_showing[0] = Var_class.result_array_showing[0].toString().split("/")[0].toString()  + "/" + ((Singleton_gv) getItem(position)).getidname();


                    ((MainActivity) context).func_result_frag();

                }

            }
        });


        return button;
    }

}