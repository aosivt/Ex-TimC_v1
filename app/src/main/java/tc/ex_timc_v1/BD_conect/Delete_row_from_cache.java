package tc.ex_timc_v1.BD_conect;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 08.04.2015.
 */
public class Delete_row_from_cache {

    public DatabaseHelper dbh;
    public SQLiteDatabase sdb;

    public void func_del_row_from_cache(String date_col) {
        ContentValues newValues = new ContentValues();
        newValues.put(dbh.DATE_COLUMN_EX, date_col);
        dbh = new DatabaseHelper((new Var_class()).getMContext(), new Var_class().getDATABASE_NAME(), null, 1);
        sdb = dbh.getWritableDatabase();

        try {
            sdb.beginTransaction();
            sdb.delete(dbh.DATABASE_TABLE_CACHE, dbh.DATE_COLUMN_EX + " = '" + date_col + "'", null);
            sdb.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        } finally {
            sdb.endTransaction();
        }
    }
    public void func_del_all_conw()
    {
        dbh = new DatabaseHelper((new Var_class()).getMContext(),new Var_class().getDATABASE_NAME(), null, 1);

        sdb = dbh.getWritableDatabase();

        try {
            sdb.beginTransaction();
            sdb.delete(dbh.DATABASE_TABLE_CONW, null , null);
            sdb.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        } finally {
            sdb.endTransaction();
        }

    }
    public void func_del_all_tab_sessions()
    {
        dbh = new DatabaseHelper((new Var_class()).getMContext(),new Var_class().getDATABASE_NAME(), null, 1);
        sdb = dbh.getWritableDatabase();

        try {
            sdb.beginTransaction();
            sdb.delete(dbh.DATABASE_TABLE_SESSION_IT, null , null);
            sdb.setTransactionSuccessful();} catch (SQLException e) {throw e;}
        finally {sdb.endTransaction();}

        try {
            sdb.beginTransaction();
            sdb.delete(dbh.DATABASE_TABLE_SESSION_SC, null , null);
            sdb.setTransactionSuccessful(); } catch (SQLException e) { throw e;}
        finally {sdb.endTransaction(); }

        try {
            sdb.beginTransaction();
            sdb.delete(dbh.DATABASE_TABLE_SESSION_EX, null , null);
            sdb.setTransactionSuccessful(); } catch (SQLException e) {throw e;}
        finally {sdb.endTransaction();}

        try {
            sdb.beginTransaction();
            sdb.delete(dbh.DATABASE_TABLE_SESSION_TS, null , null);
            sdb.setTransactionSuccessful();} catch (SQLException e) {throw e;}
        finally {sdb.endTransaction();}

        try {
            sdb.beginTransaction();
            sdb.delete(dbh.DATABASE_TABLE_SESSION_PS, null , null);
            sdb.setTransactionSuccessful();} catch (SQLException e) {throw e;}
        finally {sdb.endTransaction();}

    }
}
