package tc.ex_timc_v1.BD_conect;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 17.03.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    /*Основные параметры БД (Начало)*/
    public static final String DATABASE_NAME                    = new Var_class().getDATABASE_NAME();        ///Наименование БД

    public static final String DATABASE_TABLE_CACHE             = "cashe_tab";          ///Наименование таблици содержащей информацию кэша данных проведенных транзакций
    public static final String DATABASE_TABLE_CON               = "con_tab";            ///Наименование таблици содержащей информацию о параметры подключения к сервесу (в данной версии не доступно по причеине не доделанного функционла)
    public static final String DATABASE_TABLE_CONW              = "conw_tab";           ///Наименование таблици содержащей информацию о последнем подключенном пользователе


    public static final String DATABASE_TABLE_SESSION_IT        = "session_it";         ///Наименование таблици содержащей информацию о полученных ранее данных для работы ПО (Номенклатура)
    public static final String DATABASE_TABLE_SESSION_SC        = "session_sc";         ///Наименование таблици содержащей информацию о полученных ранее данных для работы ПО (Склад)
    public static final String DATABASE_TABLE_SESSION_EX        = "session_ex";         ///Наименование таблици содержащей информацию о полученных ранее данных для работы ПО (Экскаватор)
    public static final String DATABASE_TABLE_SESSION_TS        = "session_ts";         ///Наименование таблици содержащей информацию о полученных ранее данных для работы ПО (Транспортное средство)
    public static final String DATABASE_TABLE_SESSION_PS        = "session_ps";         ///Наименование таблици содержащей информацию о полученных ранее данных для работы ПО (Точка погрузки)

    public static final String DATABASE_TRIGER_SESSION_US       = "session_no_con";     ///Наименование тригера служащего для проставлении даты в таблицу "conw_tab"
    /*Основные параметры БД (Конец)*/

    /*Создание запроса для таблици с проведенными транзакциями (Нчало)*/
    public static final String ITEMS_COLUMN             = "load_items";                 ///код номенклатуры
    public static final String NAME_ITEMS_COLUMN        = "load_name_items";            ///наименование номенклатуры
    public static final String LOCATION_COLUMN          = "invent_location";            ///Склады
    public static final String VEHICALE_COLUMN          = "vehicle_set";                //транспортное средство (гаражный номер)
    public static final String VEHICALE_S_COLUMN        = "vehicle_s_set";              //транспортное средство(гос номер)
    public static final String W_CTR_COLUMN             = "wrk_ctr";                    ///рабочие центры
    public static final String DATE_COLUMN_EX           = "date_ex";                    ///дата отправки
    public static final String TIME_LOADING_EX          = "time_ex";                    ///время отправки
    public static final String SENT_EX                  = "sent_ex";                    ///проверки отправок
    public static final String SENT_USER                = "sent_us";                    ///имя отправителя
    public static final String SENT_PASS                = "sent_ps";                    ///пароль отправителя
    public static final String TAB_ID                   = BaseColumns._ID;              ///индекс

    private static final String DATABASE_CREATE_CACHE = "create table "
            + DATABASE_TABLE_CACHE              + " ("
            + TAB_ID                            + " integer primary key autoincrement, "//индефикатор строки
            + ITEMS_COLUMN                      + " text not null, "                    //код номенклатуры
            + NAME_ITEMS_COLUMN                 + " text not null, "                    //наименование номенклатуры
            + LOCATION_COLUMN                   + " text not null, "                    //склады
            + VEHICALE_COLUMN                   + " text not null, "                    //транспортное средство (гаражный номер)
            + VEHICALE_S_COLUMN                 + " text not null, "                    //транспортное средство (гос номер)
            + W_CTR_COLUMN                      + " text not null, "                    //рабочий центр
            + SENT_USER                         + " text not null, "                    //имя отправителя
            + SENT_PASS                         + " text not null, "                    //пароль отправителя
            + DATE_COLUMN_EX                    + " text not null, "                    //дата создания строки
            + TIME_LOADING_EX                   + " text not null, "                    //время создания строки
            + SENT_EX                           + " text not null );";                  //проверка на отправку строки

    /*Создание запроса для таблици с проведенными транзакциями (Конец)*/


    /*Создание запроса для таблици с наименование всех параметров требующих в работе ПО (Начало)*/
    public  static final String ITEM_NOM1                       = "item1";              //номенклатура
    public  static final String ITEM_NOM2                       = "item2";              //номенклатура

    public  static final String STOREHOUSE1                     = "sc1";                //склад
    public  static final String STOREHOUSE2                     = "sc2";                //склад

    public  static final String TRANSPORT_VECH1                 = "ts1";                //транспортное средство
    public  static final String TRANSPORT_VECH2                 = "ts2";                //транспортное средство

    public  static final String TRANSPORT_EXC1                  = "ex1";                //Рабочий центр(экскаватор)
    public  static final String TRANSPORT_EXC2                  = "ex2";                //Рабочий центр(экскаватор)

    public  static final String TRANSPORT_PS1                   = "ps1";                //Точка погрузки
    public  static final String TRANSPORT_PS2                   = "ps2";                //Точка погрузки


    private static final String DATABASE_CREATE_IT              = "create table "
            + DATABASE_TABLE_SESSION_IT                         + " ("
            + BaseColumns._ID                                   + " integer primary key autoincrement, "
            + ITEM_NOM1                                         + " text not null, "
            + ITEM_NOM2                                         + " text not null );";

    private static final String DATABASE_CREATE_SC              = "create table "
            + DATABASE_TABLE_SESSION_SC                         + " ("
            + BaseColumns._ID                                   + " integer primary key autoincrement, "
            + STOREHOUSE1                                       + " text not null, "
            + STOREHOUSE2                                       + " text not null );";

    private static final String DATABASE_CREATE_EX              = "create table "
            + DATABASE_TABLE_SESSION_EX                         + " ("
            + BaseColumns._ID                                   + " integer primary key autoincrement, "
            + TRANSPORT_EXC1                                    + " text not null, "
            + TRANSPORT_EXC2                                    + " text not null );";

    private static final String DATABASE_CREATE_TS              = "create table "
            + DATABASE_TABLE_SESSION_TS                         + " ("
            + BaseColumns._ID                                   + " integer primary key autoincrement, "
            + TRANSPORT_VECH1                                   + " text not null, "
            + TRANSPORT_VECH2                                   + " text not null );";

    private static final String DATABASE_CREATE_PS              = "create table "
            + DATABASE_TABLE_SESSION_PS                         + " ("
            + BaseColumns._ID                                   + " integer primary key autoincrement, "
            + TRANSPORT_PS1                                     + " text not null, "
            + TRANSPORT_PS2                                     + " text not null );";



    /*Создание запроса для таблиц с наименование всех параметров требующих в работе ПО (Конец)*/

    /*Создание запроса для таблици содержащей последнего подключившегося пользователя (Начало)*/
    public  static  final   String  NAMEUSER                    = "nameuser";           ///Наименование пользователя
    public  static  final   String  DATE_CON_USER               = "date_con";           ///Дата заполнения таблици
 //   public  static  final   String  PASS                        = "pass_user";          ///Пароль пользователя
    private static  final   String  DATABASE_CREATE_CONW        = "create table "
            + DATABASE_TABLE_CONW                               + " ("
            + BaseColumns._ID                                   + " integer primary key autoincrement, "
            + NAMEUSER                                          + " text not null, "
 //           + PASS                                              + " text not null, "
            + DATE_CON_USER                                     + " DATETIME DEFAULT CURRENT_DATE );";
    /*Создание запроса для таблици содержащей последнего подключившегося пользователя (Начало)*/

    /*Создание запроса для тригера который проставляет дату при заполнении в таблицу с conw_tab(Начало)*/

    private static final String TRIGER_CREATE_CONW =
            " CREATE TRIGGER "       + DATABASE_TRIGER_SESSION_US +
            " AFTER UPDATE ON "      + DATABASE_TABLE_CONW        +
            " BEGIN UPDATE "         + DATABASE_TABLE_CONW        +
            " SET "                  + DATE_CON_USER              +
            " = CURRENT_DATE WHERE " + BaseColumns._ID            +
            " = (select "            + BaseColumns._ID            +
            " from "                 + DATABASE_TABLE_CONW        +
            " where "                + NAMEUSER                   +
            " = new."                + NAMEUSER                   +
            " );END;";

    /*Создание запроса для тригера который проставляет дату при заполнении в таблицу с conw_tab(Конец)*/

    /*Создание запроса для таблици содержащей инфорацию для подклювения к сервесу (Начало)*/
    public  static  final   String  NAMESPACE                   = "namespace_ex";       ///NAMESPACE
    public  static  final   String  URL                         = "url_ex";             ///URL
    public  static  final   String  METHOD_NAME                 = "method_ex";          ///METHOD_NAME
    public  static  final   String  SOAP_ACTION                 = "soap_ex";            ///SOAP_ACTION
    private static  final   String  DATABASE_CREATE_CON         = "create table "
            + DATABASE_TABLE_CON                                + " ("
            + BaseColumns._ID                                   + " integer primary key autoincrement, "
            + NAMESPACE                                         + " text not null, "
            + URL                                               + " text not null, "
            + METHOD_NAME                                       + " text not null, "
            + SOAP_ACTION                                       + " text not null );";
    /*Создание запроса для таблици содержащей инфорацию для подклювения к сервесу (Начало)*/


    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        // TODO Auto-generated constructor stub
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(DATABASE_CREATE_CACHE);                                      ///создание кэша отправленных транзакций
        db.execSQL(DATABASE_CREATE_CON);                                        ///создание таблици с параметрами подключения
        db.execSQL(DATABASE_CREATE_CONW);                                       ///создание таблици с параметрами пользователя домена

        db.execSQL(DATABASE_CREATE_IT);                                         ///создание таблици с наименованием номенклатурой
        db.execSQL(DATABASE_CREATE_SC);                                         ///создание таблици с наименованием складов
        db.execSQL(DATABASE_CREATE_EX);                                         ///создание таблици с наименованием экскаваторов
        db.execSQL(DATABASE_CREATE_TS);                                         ///создание таблици с наименованием транспортных средств
        db.execSQL(DATABASE_CREATE_PS);                                         ///создание таблици с наименованием точек погрузок

        db.execSQL(TRIGER_CREATE_CONW);                                         ///создание тригера на таблицу DATABASE_CREATE_CONW
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Запишем в журнал
        // Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую

        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_CACHE);           ///Удаление таблици содержащей кэшь
        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_CON);             ///Удаление таблици содержащей параметры подключения
        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_CONW);            ///Удаление таблици содержащей параметры подключенного ранее пользователя

        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_SESSION_IT);      ///Удаление таблици с наименованием номенклатурой
        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_SESSION_SC);      ///Удаление таблици с наименованием складов
        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_SESSION_EX);      ///Удаление таблици с наименованием экскаваторов
        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_SESSION_TS);      ///Удаление таблици с наименованием транспортных средств
        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE_SESSION_PS);      ///Удаление таблици с наименованием точек погрузки

        db.execSQL("DROP TRIGER "            + DATABASE_TRIGER_SESSION_US);     ///Удаление тригера на таблицу DATABASE_CREATE_CONW
        // Создаём новые таблици*/
        onCreate(db);
    }

    public void onUpgrade_erase() {

    }

}