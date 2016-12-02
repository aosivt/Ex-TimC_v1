package tc.ex_timc_v1.interface_cl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import tc.ex_timc_v1.MainActivity;
import tc.ex_timc_v1.var_cl.Var_class;
import tc.ex_timc_v1.var_cl.update_apk.update_this_apk;

/**
 * Created by au.oschepkov on 13.03.2015.
 */
public class messageBox {
    private SQLiteDatabase database;
    public void start_m(Context context) {
    }

//    public InputMethodManager imm;
    public AlertDialog enter_dialog(final Context context) {

        final EditText user_name;
        final EditText pass;
        final ToggleButton check_pass;

        final MessageDigest md;
        final update_this_apk up_apk = new update_this_apk();
        Button bt_upgrade = new Button(context);

        bt_upgrade.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        up_apk.downloadFile("https://topgorn.sibcem.ru/wsa_Dev/app-debug.apk");
    }
});

        AlertDialog ad;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        TableLayout tableLayout = new TableLayout(context);

        TableRow userN = new TableRow(context);
        TableRow passU = new TableRow(context);
        TableRow check_passU = new TableRow(context);
        TableRow empty_row = new TableRow(context);

        user_name = new EditText(context);
        pass = new EditText(context);
        user_name.setTextSize(25);
        user_name.setText(Var_class.Item_connection_for_webservice[1].toString());

        user_name.setHint("Введите имя пользователя");
        //user_name.setInputType(View.DRAWING_CACHE_QUALITY_AUTO);
       // user_name.setRawInputType(View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION); //цифровая клавиатура
        pass.setInputType(129); ///отображать скрытые символы
        pass.setTextSize(25);
        pass.setText("");
        pass.setHint("Введите пароль пользователя");
        check_pass = new ToggleButton(context);
        check_pass.setText("Пароль скрыт");
        check_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // Enable vibrate
                    ((ToggleButton) v).setText("Пароль открыт");
                    //pass.setTypeface(Typeface.DEFAULT);
                    pass.setInputType(View.DRAWING_CACHE_QUALITY_AUTO);
                        }
                else    {
                    // Disable vibrate
                    ((ToggleButton) v).setText("Пароль скрыт");
                    pass.setInputType(129);
                        }
                }
        });

        check_pass.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
      //          func_admin_proporty(context).show();
                Toast.makeText(Var_class.MContext,"Долгое нажатие!!!", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        TextView Nuser = new TextView(context);
        Nuser.setText("Имя пользователя");
        TextView Npass = new TextView(context);
        Npass.setText("Пароль");
        Nuser.setTextSize(25);
        Npass.setTextSize(25);

 //       Nuser.setShowSoftInputOnFocus(true);
//        Nuser.setElegantTextHeight(true);
//        Nuser.setRawInputType(2);

 //       Npass.setShowSoftInputOnFocus(true);
        //Nuser.setEms(2);

        check_passU.addView(check_pass);
   //     check_passU.addView(bt_upgrade);
        userN.addView(Nuser);
        userN.addView(user_name);
        passU.addView(Npass);
        passU.addView(pass);
        empty_row.addView(new TextView(context));

        tableLayout.addView(check_passU);
        tableLayout.addView(userN);
        tableLayout.addView(passU);
        tableLayout.addView(empty_row);

        userN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) Var_class.MContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(user_name, 0);
                }
                user_name.requestFocus();

            }
        });
        passU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) Var_class.MContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(pass, 0);
                }
                pass.requestFocus();
            }
        });

        user_name.setText("au.oschepkov");
        pass.setText("VTlktyyjqIk.grjq");

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!user_name.getText().toString().equals("") && !pass.getText().toString().equals("")) {
                    Var_class.Item_connection_for_webservice[1] = "";
                    Var_class.Item_connection_for_webservice[2] = "";
                    Var_class.Item_connection_for_webservice[1] = user_name.getText().toString();
                    Var_class.Item_connection_for_webservice[2] = pass.getText().toString();
                    ((MainActivity) (new Var_class()).getMContext()).Start_func();
                    InputMethodManager imm = (InputMethodManager) Var_class.MContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(pass.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(user_name.getWindowToken(), 0);
                    }
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(Var_class.MContext,
                            "Введите данные для подключения", Toast.LENGTH_LONG).show();
                    messageBox mb = new messageBox();
                    mb.enter_dialog(Var_class.MContext).show();
                }

            }
        };

        //"16.02.2015" "17.02.2015"    String.valueOf("15.02.2015".compareTo("17.02.2015")) +

        ad = builder.setTitle       ("Введите учетные данные" + "\n" + Var_class.error_connect)
                .setPositiveButton  ("Подтвердить", onClickListener)
                .setCancelable(false)
                .setView(tableLayout).create();

        //ad.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ad.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

        return ad;
    }

    public Dialog createProgressDialog(final Context context) {

        ProgressDialog dialog;
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Ожидайте - процесс получения данных");
        dialog.setCancelable(false);
        return dialog;
    }
    public void func_insert_empty_row()
    {


    }
public AlertDialog func_admin_proporty (Context context)
{
    AlertDialog ad;
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    final String mask_pass = "b97d1e58ecfcdabb688305664cd665c8d97fc9f1c2a5796efca387a922e55aa2";
    final EditText command_txt= new EditText(context);
    EditText pass_txt = new EditText(context);
    TableLayout tableLayout = new TableLayout(context);
    TableRow command = new TableRow(context);
    TableRow pass_comm = new TableRow(context);

    command.addView(command_txt);
    pass_comm.addView(pass_txt);

    tableLayout.addView(command);
    tableLayout.addView(pass_comm);
    MessageDigest md = null;
    String mess = "";
    try {
        md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(pass_txt.getText().toString().getBytes());
        byte[] sha256 = md.digest();
        String tmp = "";
        for (int i = 0; i < sha256.length; i++) {
            tmp = (Integer.toHexString(0xFF & sha256[i]));
            if (tmp.length() == 1) {
                mess += "0" + tmp;
            } else {
                mess += tmp;
            }
        }
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }

    final String tmp_pass = mess;

    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
           // if (!mask_pass.equals(tmp_pass) && command_txt.getText().toString().equals("Обновить"))

                /*DatabaseHelper dbh;
                SQLiteDatabase sdb;
                dbh = new DatabaseHelper((new Var_class()).getMContext(),new Var_class().getDATABASE_NAME(), null, 2);
                sdb = dbh.getWritableDatabase();
                dbh.onUpgrade(sdb,1,1);
                sdb.execSQL("");
                */
        }
    };

    ad = builder.setTitle("Панель администрирования")
            .setPositiveButton("Подтвердить", onClickListener)
            .setView(tableLayout).create();

    return ad;

}

}
