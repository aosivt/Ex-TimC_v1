package tc.ex_timc_v1.BD_conect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 18.03.2015.
 */
public class Select_SQLite {
    public ArrayAdapter<String> getAdapterForResultFragment() {

        DatabaseHelper dbh = new DatabaseHelper((new Var_class().getMContext()), new Var_class().getDATABASE_NAME(), null, 1);
        SQLiteDatabase sdb = dbh.getWritableDatabase();
        ArrayList lst_res = new ArrayList();

        Cursor cursor = sdb.query(dbh.DATABASE_TABLE_CACHE, new String[]{dbh.W_CTR_COLUMN, dbh.NAME_ITEMS_COLUMN, dbh.LOCATION_COLUMN, dbh.VEHICALE_COLUMN,
                        dbh.SENT_EX, dbh.DATE_COLUMN_EX, dbh.TIME_LOADING_EX},
                null, null,
                null, null, null);

        String str1 = (new Var_class().getresult_array())[0].toString();
        String row_date_from_del = "";
        int ind_while_all = 0;
        int ind_while_insert = 0;

        while (cursor.moveToNext()) {

            if (cursor.getString(cursor.getColumnIndex(dbh.DATE_COLUMN_EX))
                    .compareTo(android.text.format.DateFormat.getDateFormat((new Var_class()).getMContext()).format(new Date())) >= -1) {
                lst_res.addAll(Arrays.asList(new String[]{
                        cursor.getString(cursor.getColumnIndex(dbh.SENT_EX)),
                        cursor.getString(cursor.getColumnIndex(dbh.NAME_ITEMS_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(dbh.LOCATION_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(dbh.VEHICALE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(dbh.DATE_COLUMN_EX)),
                        cursor.getString(cursor.getColumnIndex(dbh.TIME_LOADING_EX))
                }));
            }
            if (cursor.getString(cursor.getColumnIndex(dbh.DATE_COLUMN_EX))
                    .compareTo(android.text.format.DateFormat.getDateFormat((new Var_class()).getMContext()).format(new Date())) < -6 && row_date_from_del.equals("")) {
                row_date_from_del = cursor.getString(cursor.getColumnIndex(dbh.DATE_COLUMN_EX));
            }
            ind_while_all++;
            ind_while_insert++;

        }


        String[] lst_temp = new String[lst_res.size()];
        lst_temp = (String[]) lst_res.toArray(new String[lst_res.size()]);
        int i = lst_res.size();
        int j = 0;
        int k = 6;

        while (i > 0) {
            if (k > 0) {
                lst_temp[j + k - 1] = String.valueOf(lst_res.get(i - 1));
                k--;
                i--;
            } else {
                k = 6;
                j = j + k;
            }
        }

        ArrayList lst_tab = new ArrayList();


        lst_tab.addAll(Arrays.asList(lst_temp));

        if (!row_date_from_del.equals("")) {
            new Delete_row_from_cache().func_del_row_from_cache(row_date_from_del);
            row_date_from_del = "";
        }

        return new ArrayAdapter<String>((new Var_class().getMContext()), android.R.layout.simple_list_item_1,
                (String[]) lst_tab.toArray(new String[lst_tab.size()]));


    }

    /*Заполнение массивов для локального создания транзакций (Начало)*/
    public void func_filling_array()
    {
        DatabaseHelper dbh = new DatabaseHelper((new Var_class().getMContext()), new Var_class().getDATABASE_NAME(), null, 1);

        Var_class.listviewitem_it1 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_IT,dbh.ITEM_NOM1,dbh.ITEM_NOM2)[0]; //номенклатура
        Var_class.listviewitem_it2 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_IT,dbh.ITEM_NOM1,dbh.ITEM_NOM2)[1]; //номенклатура

        Var_class.listviewitem_sc1 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_SC,dbh.STOREHOUSE1,dbh.STOREHOUSE2)[0]; //склад
        Var_class.listviewitem_sc2 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_SC,dbh.STOREHOUSE1,dbh.STOREHOUSE2)[1]; //склад

        Var_class.listviewitem_ex1 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_EX,dbh.TRANSPORT_EXC1,dbh.TRANSPORT_EXC2)[0]; //транспортное средство
        Var_class.listviewitem_ex2 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_EX,dbh.TRANSPORT_EXC1,dbh.TRANSPORT_EXC2)[1]; //транспортное средство

        Var_class.listviewitem_ts1 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_TS,dbh.TRANSPORT_VECH1,dbh.TRANSPORT_VECH2)[0]; //экскаватор
        Var_class.listviewitem_ts2 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_TS,dbh.TRANSPORT_VECH1,dbh.TRANSPORT_VECH2)[1]; //экскаватор

        Var_class.listviewitem_point_set1 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_PS,dbh.TRANSPORT_PS1,dbh.TRANSPORT_PS2)[0]; //точка погрузки
        Var_class.listviewitem_point_set2 = func_tmp_array_for_localBD(dbh.DATABASE_TABLE_SESSION_PS,dbh.TRANSPORT_PS1,dbh.TRANSPORT_PS2)[1]; //точка погрузки
    }


public String[][]  func_tmp_array_for_localBD(String DB_TB_NAME, String fill_tmp1, String fill_tmp2)
{
    DatabaseHelper dbh = new DatabaseHelper((new Var_class().getMContext()), new Var_class().getDATABASE_NAME(), null, 1);
    SQLiteDatabase sdb = dbh.getWritableDatabase();
    String[][] temp_array;
    ArrayList temp_id = new ArrayList();
    ArrayList temp_name = new ArrayList();

    Cursor cursor = sdb.query(DB_TB_NAME, new String[]{fill_tmp1, fill_tmp2},
            null, null, null, null, null);

    while (cursor.moveToNext())
    {
        temp_id.addAll(Arrays.asList(new String[]{cursor.getString(cursor.getColumnIndex(fill_tmp1))}));
        temp_name.addAll(Arrays.asList(new String[]{cursor.getString(cursor.getColumnIndex(fill_tmp2))}));
    }
    temp_array = new String[2][temp_id.size()];
    temp_array[0] = (String[]) temp_id.toArray(new String[temp_id.size()]);
    temp_array[1] = (String[]) temp_name.toArray(new String[temp_id.size()]);
    return temp_array;
}
/*Заполнение массивов для локального создания транзакций (Конец)*/

public int func_sel_users_for(String nameuser)
{
    DatabaseHelper dbh = new DatabaseHelper((new Var_class().getMContext()), new Var_class().getDATABASE_NAME(), null, 1);
    SQLiteDatabase sdb = dbh.getReadableDatabase();
    Cursor  cursor = sdb.rawQuery("SELECT count(*) FROM conw_tab where nameuser = '"+nameuser+"'", null);
if (cursor.moveToFirst())
{return cursor.getInt(cursor.getColumnIndex("count(*)"));}
else {return 0;}
}

}


