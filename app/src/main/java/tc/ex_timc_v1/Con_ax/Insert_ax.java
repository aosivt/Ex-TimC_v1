package tc.ex_timc_v1.Con_ax;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import tc.ex_timc_v1.BD_conect.DatabaseHelper;
import tc.ex_timc_v1.BD_conect.Update_SQLite;
import tc.ex_timc_v1.For_fragments.result_fragment;
import tc.ex_timc_v1.MainActivity;
import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 17.03.2015.
 */
public class Insert_ax {
    public DatabaseHelper dbh;
    public SQLiteDatabase sdb;
    public AsyncCallWS task;

    public void func_insert_ax_timer() {
        ArrayList lst_res = new ArrayList();
        if (Var_class.break_insert_ax == true) {

            Var_class.id_tab_cache = "";
            dbh = new DatabaseHelper((new Var_class()).getMContext(), new Var_class().getDATABASE_NAME(), null, 1);
            sdb = dbh.getWritableDatabase();

            Cursor cursor = sdb.query(dbh.DATABASE_TABLE_CACHE, new String[]{
                            dbh.TAB_ID, dbh.W_CTR_COLUMN, dbh.ITEMS_COLUMN,
                            dbh.LOCATION_COLUMN, dbh.VEHICALE_S_COLUMN, dbh.VEHICALE_COLUMN,
                            dbh.SENT_EX, dbh.DATE_COLUMN_EX, dbh.TIME_LOADING_EX,dbh.SENT_USER,dbh.SENT_PASS},

                    null, null,
                    null, null, null);

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                if (cursor.getString(cursor.getColumnIndex(dbh.SENT_EX)).toString().equals("-")) {
                    lst_res.addAll(Arrays.asList(new String[]{
                            cursor.getString(cursor.getColumnIndex(dbh.W_CTR_COLUMN)),      //рабочий центр (экскаватор) 0
                            cursor.getString(cursor.getColumnIndex(dbh.ITEMS_COLUMN)),      //код номенклатуры 1
                            cursor.getString(cursor.getColumnIndex(dbh.LOCATION_COLUMN)),   //склад 2
                            cursor.getString(cursor.getColumnIndex(dbh.VEHICALE_COLUMN)),   //транспортное средство (гаражный номер) 3
                            cursor.getString(cursor.getColumnIndex(dbh.VEHICALE_S_COLUMN)), //транспортное средство (гос номер) 4
                            cursor.getString(cursor.getColumnIndex(dbh.SENT_USER)),         ///имя отправителя 5
                            cursor.getString(cursor.getColumnIndex(dbh.SENT_PASS)),         ///пароль отправителя 6
                            cursor.getString(cursor.getColumnIndex(dbh.DATE_COLUMN_EX)),    //дата заполнения 7
                            cursor.getString(cursor.getColumnIndex(dbh.TIME_LOADING_EX))    //время заполнения 8
                    }));
                    Var_class.id_tab_cache = cursor.getString(cursor.getColumnIndex(dbh.TAB_ID));

                    break;
                }
                cursor.moveToNext();
            }
            if (lst_res.size() != 0) {
                Var_class.result_array_send_ax = (String[]) lst_res.toArray(new String[lst_res.size()]);
//          if (!Var_class.result_array_send_ax[6].toString().equals("local session")) {
                    Var_class.if_call = 2;
                    Var_class.break_insert_ax = false;
                    task = new AsyncCallWS();
                    task.execute();
                    lst_res.clear();
                    Var_class.break_insert_ax = false;
//                }
/*Проверка на заполнение пользователя */
/*
             else if (Var_class.result_array_send_ax[6].toString().equals("local session")) {
                    Var_class.result_array_send_ax[6] = Var_class.Item_connection_for_webservice[2].toString();
                    Var_class.result_array_send_ax[5] = Var_class.Item_connection_for_webservice[0].toString() + Var_class.Item_connection_for_webservice[1].toString();
                    Var_class.if_call = 2;
                    Var_class.break_insert_ax = false;
                    task = new AsyncCallWS();
                    task.execute();
                    lst_res.clear();
                    Var_class.break_insert_ax = false;
                }

                else if (!Var_class.Item_connection_for_webservice[2].toString().equals("local session")){
                    (new Update_SQLite()).func_update_local_session_at_user();
                }
*/
            }

        }

    }

    public void func_cach() {
        if (Var_class.ansver_qu.equals("done")) {

            new Update_SQLite().func_update_SQLite(Var_class.id_tab_cache);
            Var_class.ansver_qu = "";
            Var_class.id_tab_cache = "";
        } else {
            Var_class.ansver_qu = "";
            Var_class.id_tab_cache = "";
        }
    }

    public void func_reconnect_result_fragment() {
        result_fragment fragment = (result_fragment) ((MainActivity) ((new Var_class()).getMContext())).func_return_FM()
                .findFragmentByTag((new Var_class()).getResultFrag());

        if (fragment != null) {
            fragment.replace_gv_result();
        }
    }
}
