package tc.ex_timc_v1.BD_conect;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 17.03.2015.
 */
public class Update_SQLite {
    public DatabaseHelper dbh;
    public SQLiteDatabase sdb;

    public void func_update_SQLite(String tab_id) {

        dbh = new DatabaseHelper((new Var_class()).getMContext(), new Var_class().getDATABASE_NAME(), null, 1);
        sdb = dbh.getWritableDatabase();

     /*   AlertDialog.Builder builder = new AlertDialog.Builder((new Var_class()).getMContext());
        builder.setTitle("Проверочное сообщение!" + tab_id)
                .setMessage("Context Main_form получен!")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
*/
        ContentValues newValues = new ContentValues();
        newValues.put(dbh.SENT_EX, "+");
        try {
            sdb.beginTransaction();
            sdb.update(dbh.DATABASE_TABLE_CACHE, newValues, dbh.TAB_ID + " = " + tab_id, null);
            sdb.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        } finally {
            sdb.endTransaction();
        }

    }
    public void func_update_local_session_at_user()
    {
        dbh = new DatabaseHelper((new Var_class()).getMContext(), new Var_class().getDATABASE_NAME(), null, 1);
        sdb = dbh.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(dbh.SENT_USER, (new Var_class()).getItem_connection_for_webservice()[1].toString());
        newValues.put(dbh.SENT_PASS, "Internet session");
        try {
            sdb.beginTransaction();
            sdb.update(dbh.DATABASE_TABLE_CACHE, newValues, dbh.SENT_PASS + " = " + "'local session'", null);
            sdb.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        } finally {
            sdb.endTransaction();
        }
    }
}
