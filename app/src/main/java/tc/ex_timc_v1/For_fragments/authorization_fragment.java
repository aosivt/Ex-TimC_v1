package tc.ex_timc_v1.For_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import tc.ex_timc_v1.MainActivity;
import tc.ex_timc_v1.R;
import tc.ex_timc_v1.dev_cl.Singleton.Customization_Singleton;
import tc.ex_timc_v1.dev_cl.Singleton.SingletonAdapter;
import tc.ex_timc_v1.dev_cl.Singleton.Singleton_gv;
import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 13.03.2015.
 */
public class authorization_fragment extends Fragment {

    public final static String KEY_MSG_1 = "FRAGMENT1_MSG";
    public final static String KEY_MSG_2 = "FRAGMENT2_MSG";
    public final static String KEY_MSG_3 = "FRAGMENT3_MSG";
    public static FragmentManager myFragmentManager;
    public FrameLayout container;
    public authorization_fragment myFragment1;
    tc.ex_timc_v1.interface_cl.messageBox mes;
    TextView textMsg;
    Button bt;
    GridView ex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.authorization_frag, null);
        if (Var_class.result_array_send[0].toString().equals(""))
        {
            ex = (GridView) view.findViewById(R.id.exa);
            ex.setAdapter(new SingletonAdapter((new Var_class()).getMContext(),
                    (new Customization_Singleton().setArray_
                            ((new Var_class()).get_listviewitem_ex2(), (new Var_class()).get_listviewitem_ex2()))));

            ex.setNumColumns(5);

        }
        if (!Var_class.result_array_send[0].toString().equals("")) {
            ex = (GridView) view.findViewById(R.id.exa);
            ex.setAdapter(new SingletonAdapter((new Var_class()).getMContext(),
                    (new Customization_Singleton().setArray_
                            ((new Var_class()).get_listviewitem_point_set1(), (new Var_class()).get_listviewitem_point_set2()))));

            ex.setNumColumns(5);

        }




        return view;
    }

}
