package tc.ex_timc_v1.For_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import tc.ex_timc_v1.BD_conect.Insert_SQLite;
import tc.ex_timc_v1.MainActivity;
import tc.ex_timc_v1.R;
import tc.ex_timc_v1.dev_cl.Singleton.Customization_Singleton;
import tc.ex_timc_v1.dev_cl.SpreadSheet.SpreadSheetAdapter;
import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 16.03.2015.
 * Фрагмент отображает экран с набором данных для транзакции в SQLite
 */
public class filling_fragment extends Fragment {
    public final static String          KEY_MSG_1 = "FRAGMENT1_MSG";
    public final static String          KEY_MSG_2 = "FRAGMENT2_MSG";
    public final static String          KEY_MSG_3 = "FRAGMENT3_MSG";
    public static FragmentManager       myFragmentManager;
    public FrameLayout                  container;
    public authorization_fragment       myFragment1;
    tc.ex_timc_v1.interface_cl.messageBox mes;
    TextView                            textMsg;
    Button                              bt;
    GridView                            ex;

    GridView                            vehicle_gv;
    GridView                            range_gv;
    GridView                            stores_gv;
    GridView                            result_gv;
    TabHost                             tabH;


    //public static Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.filling_frag, null);

        tabH = (TabHost) view.findViewById(R.id.tabHost2);
        tabH.setup();
        TabHost.TabSpec spec;


        spec = tabH.newTabSpec("tag1");
        spec.setContent(R.id.vehicle);
        spec.setIndicator("Транспортное средство");
        tabH.addTab(spec);

        spec = tabH.newTabSpec("tag2");
        spec.setContent(R.id.range);
        spec.setIndicator("Номенклатура");
        tabH.addTab(spec);

        spec = tabH.newTabSpec("tag3");
        spec.setContent(R.id.stores);
        spec.setIndicator("Склады");
        tabH.addTab(spec);

        spec = tabH.newTabSpec("tag4");
        spec.setContent(R.id.result);
        spec.setIndicator("Результат");
        tabH.addTab(spec);

        tabH.setCurrentTab(0);

        ///кнопка подтвердить
        Button bt_insert = (Button) view.findViewById(R.id.result_bt);
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (func_check_array_result_send()) {
                   ((MainActivity) (new Var_class()).getMContext()).func_result_frag();
                   (new Insert_SQLite()).func_insert_SQLite();
               }
                else
               {
                   Toast.makeText(Var_class.MContext,
                           "Данные не заполнены", Toast.LENGTH_LONG).show();
               }

            }
        });

        ///кнопка отменить
        Button bt_cancel = (Button) view.findViewById(R.id.cancel_bt);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) (new Var_class()).getMContext()).func_result_frag();
            }
        });

        vehicle_gv = (GridView) view.findViewById(R.id.vehicle_gv); ///транспортное средство
/*        vehicle_gv.setAdapter(new SpreadSheetAdapter(((new Var_class()).getMContext()),
                (new Customization_Singleton().setArray_
                        ((new Var_class()).get_listviewitem_ts1(), (new Var_class()).get_listviewitem_ts2()))));

        vehicle_gv.setNumColumns(4);
*/

        vehicle_gv.setAdapter(null);
        vehicle_gv.setAdapter(new SpreadSheetAdapter(((new Var_class()).getMContext()), ///vehicle_gv
                (new Customization_Singleton().setArray_
                        //((new Var_class()).get_listviewitem_ts1(), (new Var_class()).get_listviewitem_ts1()))));
                        ((new Var_class()).get_listviewitem_ts1_10(1), (new Var_class()).get_listviewitem_ts2_10(1)))));

        vehicle_gv.setNumColumns(4);



        vehicle_gv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean touch = false;
                int Action=event.getAction();
                switch(Action)
                {
                    case MotionEvent.ACTION_UP:
                        boolean DownUp = Var_class.y_coord - event.getY()<0;
                        if(DownUp){Var_class.tab_ts++;}
                        if(!DownUp){Var_class.tab_ts--;}
                        if ((int)Var_class.tab_ts > (int)(Math.ceil(new Double(Var_class.listviewitem_ts1.length)/8.0))){Var_class.tab_ts=1;}
                        if ((int)Var_class.tab_ts == 0 ){Var_class.tab_ts=(int)(Math.ceil(new Double(Var_class.listviewitem_ts1.length)/8.0));}
                        Toast.makeText(Var_class.MContext,
                                "Страница № " + Var_class.tab_ts.toString() + " из "
                                        + ((int)(Math.ceil(new Double(Var_class.listviewitem_ts1.length)/8.0))) , Toast.LENGTH_SHORT).show() ;
                        vehicle_gv.setAdapter(new SpreadSheetAdapter(((new Var_class()).getMContext()), ///vehicle_gv
                                (new Customization_Singleton().setArray_
                                        ((new Var_class()).get_listviewitem_ts1_10(Var_class.tab_ts), (new Var_class()).get_listviewitem_ts2_10(Var_class.tab_ts)))));
                        Var_class.y_coord = 0;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Var_class.y_coord == 0){Var_class.y_coord = event.getY();}
                        break;
                }
                return touch;
            }

        });


        range_gv = (GridView) view.findViewById(R.id.range_gv); ///номенклатура
        range_gv.setAdapter(new SpreadSheetAdapter(((new Var_class()).getMContext()),
                (new Customization_Singleton().setArray_
                        ((new Var_class()).get_listviewitem_it2(), (new Var_class()).get_listviewitem_it1()))));

        range_gv.setNumColumns(3);

        stores_gv = (GridView) view.findViewById(R.id.stores_gv); ///склады
        stores_gv.setAdapter(new SpreadSheetAdapter(((new Var_class()).getMContext()),
                (new Customization_Singleton().setArray_
                        ((new Var_class()).get_listviewitem_sc2(), (new Var_class()).get_listviewitem_sc1()))));

        stores_gv.setNumColumns(3);

        result_gv = (GridView) view.findViewById(R.id.result_gv); //результаты
        result_gv.setNumColumns(2);
        result_gv.setAdapter(new ArrayAdapter<String>((new Var_class()).getMContext(), R.layout.item, R.id.tvText, (new Var_class()).getresult_array()));
        result_gv.scrollBy(0,0);

        tabH.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                func_next_tab(view);
            }
        });

        return view;
    }

    public void func_next_tab(View view) {
        result_gv = (GridView) view.findViewById(R.id.result_gv);
        result_gv.setNumColumns(2);
        result_gv.setAdapter(new ArrayAdapter<String>((new Var_class()).getMContext(), R.layout.item, R.id.tvText, (new Var_class()).getresult_array()));
        result_gv.setColumnWidth(80);
        result_gv.setVerticalSpacing(5);
        result_gv.setHorizontalSpacing(5);
    }

    public boolean func_check_array_result_send()
    {
        int ind_while =0;
        while (ind_while < Var_class.result_array.length)
        {
            if (Var_class.result_array[ind_while+2].equals(""))
            {
                return false;
            }
            ind_while = ind_while+3;
        }
        return true;
    }
}
