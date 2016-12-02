package tc.ex_timc_v1.For_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ToggleButton;

import tc.ex_timc_v1.BD_conect.Select_SQLite;
import tc.ex_timc_v1.MainActivity;
import tc.ex_timc_v1.R;
import tc.ex_timc_v1.interface_cl.messageBox;
import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 16.03.2015.
 */
public class result_fragment extends Fragment {
    tc.ex_timc_v1.interface_cl.messageBox mes;
    TextView textMsg;
    Button bt;
    GridView ex;
    ToggleButton TB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.result_frag, null);
        ex = (GridView) view.findViewById(R.id.result_array);
        ex.setNumColumns(6);
        ex.setColumnWidth(80);
        ex.setVerticalSpacing(5);
        ex.setHorizontalSpacing(5);

        String data =               "Экскаватор: "          + Var_class.result_array[1].toString().split("/")[0].toString() + "\n" +
                                    "Место погрузки: "      + Var_class.result_array[1].toString().split("/")[1].toString() + "\n" +
                                    "Вы подключены как: "   + Var_class.listviewitem_ex1[0];




        textMsg = (TextView) view.findViewById(R.id.ex_title);
        textMsg.setText(data);
        Linkify.addLinks(textMsg, Linkify.ALL);

        /*

//включение отключение вывод окна для ввода пользователя и пароля
        textMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageBox mb = new messageBox();
                Var_class.call_transact = false;
                //(new ShowTimer()).showTimer(500000);
                //(new ShowTimer()).showTimer(ShowTimer.SECONDS_TO_COUNTDOWN * ShowTimer.MILLIS_PER_SECOND);
                mb.enter_dialog(Var_class.MContext).show();

            }
        });
*/
        TB = (ToggleButton) view.findViewById(R.id.TB_light);

        TB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {((MainActivity) (new Var_class()).getMContext()).func_day_on();}
                else {((MainActivity) (new Var_class()).getMContext()).func_day_off();}

                ((MainActivity) (new Var_class()).getMContext()).func_result_frag_restart();
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {

        } else {
            ex.setAdapter((new Select_SQLite()).getAdapterForResultFragment());
        }

        Button bt_send = (Button) view.findViewById(R.id.send_result);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) (new Var_class()).getMContext()).func_result_fragment_new();
            }
        });

        return view;
    }

    public void replace_gv_result() {
        ex.setAdapter((new Select_SQLite()).getAdapterForResultFragment());
    }

}
