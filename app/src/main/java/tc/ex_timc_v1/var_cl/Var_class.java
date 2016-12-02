package tc.ex_timc_v1.var_cl;

import android.app.Dialog;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by au.oschepkov on 13.03.2015.
 */
public class Var_class {

    public static String DATABASE_NAME = "ex_dr_v11.db"; //экскаватор//добавление точек погрузок в 11 версии
    public static String WSA = "wsa_Live_timC"; //экскаватор
    //public static String WSA = "wsa_Live"; //экскаватор Топки
    //public final static String[] Item_connection_for_webservice = new String[]{"sibcem", "Exc1", "(VSD?kb1)"};
    public final static String[] Item_connection_for_webservice = new String[]{"sibcem", "", "",""};
    //public final static String[] Item_connection_for_webservice = new String[]{"sibcem", "au.oschepkov", "rfarfYfgkz;t"};
    final static String AUTHO_FRAG      = "FRAGMENT_1";
    final static String FILLING_FRAG    = "FRAGMENT_2";
    final static String RESULT_FRAG     = "FRAGMENT_3";

    public static String[] result_array_send = new String[]{"", "", "", "", "", ""}; ///результирующий массив на отправку
    public static String[] result_array_showing = new String[]{"", "", "", "", "", ""}; ///результирующий массив на отображение
    public static String[] result_array_send_ax = new String[]{"", "", "", "", "", "", ""}; ///результирующий массив на отображение
    public static boolean break_insert_ax;
    public static String id_tab_cache = "";
    public static String ansver_qu = "";
    public static String KEY_MSG;
    public static String error_connect = "";
    public static boolean success_select;
    public static Integer count_call_insert = 0;
    public static Dialog message_for_connection_web = null;

    public static boolean OnOffLine;

    /*часть кода с перемеными для работы с прокруткой*/
    public static Integer tab_ts = 1;

    public static float x_coord;
    public static float y_coord;
    /**/

    public static String[] listviewitem_it1;        //номенклатура
    public static String[] listviewitem_it2;        //номенклатура

    public static String[] listviewitem_sc1;        //склад
    public static String[] listviewitem_sc2;        //склад

    public static String[] listviewitem_ts1;        //транспортное средство
    public static String[] listviewitem_ts2;        //транспортное средство

    public static String[] listviewitem_ex1;        //экскаватор
    public static String[] listviewitem_ex2;        //экскаватор

    public static String[] listviewitem_point_set1 = new String[]{"103","234"}; //место погрузки
    public static String[] listviewitem_point_set2 = new String[]{"103","234"}; //место погрузки

    public static String[] result_array = new String[]{"Экскаватор", "", "ТС", "", //массив для отображения результатов выбора
            "Номеклатура", "", "Склады", "",
            "Дата отправки", "", "Время отправки", ""};


    public static boolean call_transact = false; //вызов функции запроса к ax
    public static Integer if_call = 0;


    public static Context MContext;     // контекст главной страницы Main_form


    public String[] test1 = new String[]{"23222222222222222222224", "345", "345", "345", "345", "345"};
    public String[] test2 = new String[]{"2341", "3451", "3451", "3415", "3451", "3415"};

    public String[] getTest1() {
        return test1;
    }

    public String[] getTest2() {
        return test2;
    }

    public String[] getresult_array() {
        return result_array;
    }

    public String[] get_listviewitem_it1() {
        return listviewitem_it1;
    }

    public String[] get_listviewitem_it2() {
        return listviewitem_it2;
    }

    public String[] get_listviewitem_ex1() {
        return listviewitem_ex1;
    }

    public String[] get_listviewitem_ex2() {
        return listviewitem_ex2;
    }

    public String[] get_listviewitem_ts1() {
        return listviewitem_ts1;
    }

    public String[] get_listviewitem_ts2() {
        return listviewitem_ts2;
    }

    public String[] get_listviewitem_sc1() {
        return listviewitem_sc1;
    }

    public String[] get_listviewitem_sc2() {
        return listviewitem_sc2;
    }

    public String[] get_listviewitem_point_set1() {
        return listviewitem_point_set1;
    }
    public String[] get_listviewitem_point_set2() {
        return listviewitem_point_set2;
    }

    public boolean get_break_insert_ax() {
        return break_insert_ax;
    }

    public String[] get_listviewitem_ts1_10(int ind) {
        ArrayList<String> temp_list = new ArrayList<String>();
        String temp_array[];
        int start_ind_temp =(ind-1)*8;

        while (start_ind_temp< 8 * ind && start_ind_temp < listviewitem_ts1.length)
        {
            temp_list.add(listviewitem_ts1[start_ind_temp]);
            start_ind_temp ++;
        }
        temp_array = new String[temp_list.size()];
        temp_array = temp_list.toArray(temp_array);
        return temp_array;
    }

    public String[] get_listviewitem_ts2_10(int ind) {
        ArrayList<String> temp_list = new ArrayList<String>();
        String temp_array[];
        int start_ind_temp =(ind-1)*8;

        while (start_ind_temp< 8 * ind && start_ind_temp < listviewitem_ts2.length)
        {
            temp_list.add(listviewitem_ts2[start_ind_temp]);
            start_ind_temp ++;
        }
        temp_array = new String[temp_list.size()];
        temp_array = temp_list.toArray(temp_array);
        return temp_array;
    }

    public Context getMContext() {
        return MContext;
    }

    public String getAuthoFrag() {
        return AUTHO_FRAG;
    }

    public String getFillingFrag() {
        return FILLING_FRAG;
    }

    public String getResultFrag() {
        return RESULT_FRAG;
    }

    public String getDATABASE_NAME() {
        return DATABASE_NAME;
    }

    public String getResultKEYMSG() {
        return KEY_MSG;
    }

    public String[] getItem_connection_for_webservice() {
        return Item_connection_for_webservice;
    }


}
