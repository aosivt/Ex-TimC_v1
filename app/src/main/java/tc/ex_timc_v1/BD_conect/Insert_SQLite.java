package tc.ex_timc_v1.BD_conect;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 17.03.2015.
 */


public class Insert_SQLite {
    public DatabaseHelper dbh;
    public SQLiteDatabase sdb;

    public void func_insert_SQLite() {
        dbh = new DatabaseHelper((new Var_class()).getMContext(), new Var_class().getDATABASE_NAME(), null, 1);

        sdb = dbh.getWritableDatabase();

        ContentValues newValues = new ContentValues();

        // Задайте значения для каждой строки.
        newValues.put(dbh.W_CTR_COLUMN, Var_class.result_array_send[0]); //рабочий центр
        newValues.put(dbh.VEHICALE_COLUMN, Var_class.result_array_showing[1]); //гаражный номер тс
        newValues.put(dbh.VEHICALE_S_COLUMN, Var_class.result_array_send[1]); //гос номер тс
        newValues.put(dbh.ITEMS_COLUMN, Var_class.result_array_send[2]); //код номенклатуры
        newValues.put(dbh.NAME_ITEMS_COLUMN, Var_class.result_array_showing[2]); //наименование номенклатуры
        newValues.put(dbh.LOCATION_COLUMN, Var_class.result_array_send[3]); //наименование склады
        newValues.put(dbh.SENT_USER, (new Var_class()).getItem_connection_for_webservice()[1].toString()); //имя отправителя
        newValues.put(dbh.SENT_PASS, "qwerty123"); //пароль отправителя
        newValues.put(dbh.DATE_COLUMN_EX, Var_class.result_array_send[4]); //дата создания записи
        newValues.put(dbh.TIME_LOADING_EX, Var_class.result_array_send[5]); //время создания записи
        newValues.put(dbh.SENT_EX, "-");

        // Вставляем данные в базу
        sdb.insert("cashe_tab", null, newValues);

    }

    public void func_insert_SQLite_conw(String username) //добавление записи для подключение к веб сервису
    {
        dbh = new DatabaseHelper((new Var_class()).getMContext(),new Var_class().getDATABASE_NAME(), null, 1);

        sdb = dbh.getWritableDatabase();

 //       new Delete_row_from_cache().func_del_all_conw(); ///удаление всех записей в таблице

        ContentValues newValues = new ContentValues();

        // Задайте значения для каждой строки.
        newValues.put(dbh.NAMEUSER, username);
 //       newValues.put(dbh.PASS, pass);

        // Вставляем данные в базу
        sdb.insert("conw_tab", null, newValues);
    }

    public void func_filling_tab_session(String[] ar1, String[] ar2,String name_tab,
                                         String fill_1,String fill_2)
    {
        dbh = new DatabaseHelper((new Var_class()).getMContext(),new Var_class().getDATABASE_NAME(), null, 1);

        sdb = dbh.getWritableDatabase();



        ContentValues newValues = new ContentValues();

        // Задайте значения для каждой строки.
        int i =0;
        while (i<ar1.length)
        {
            newValues.put(fill_1,     ar1[i].toString());
            newValues.put(fill_2,     ar2[i].toString());

            sdb.insert(name_tab, null, newValues);

            i++;
        }


        // Вставляем данные в базу


    }


}
