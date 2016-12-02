package tc.ex_timc_v1.Con_ax;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 17.03.2015.
 */
public class ShowTimer {
    public static final int MILLIS_PER_SECOND = 1000;
    public static final int SECONDS_TO_COUNTDOWN = 5;
    private TextView countdownDisplay;
    private int call_reconfigure_frag = 0; //переменна для сдерживания переподключения фрагмента в классе filling_frag
    private CountDownTimer timer;

    public void showTimer(int countdownMillis) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                //new Insert_ax().func_insert_ax_timer();
                try {

                   /* if (Var_class.if_call == 1)
                    {
                        //Var_class.message_for_connection_web.dismiss();
                     if (Var_class.error_connect == "errorAOS") {
                         ((Main_form) (new Var_class()).getMContext()).func_reboot_application();
                     }
                        else {
                         Var_class.if_call = 2;
                         showTimer(SECONDS_TO_COUNTDOWN * MILLIS_PER_SECOND);
                     }

                    }
                    else {
                        Var_class.if_call = 2;
                        if (Var_class.id_tab_cache == "" && Var_class.ansver_qu == "") {
                            Var_class.break_insert_ax = true;
                            (new Insert_ax()).func_insert_ax_timer();
                            (new Insert_ax()).func_reconnect_result_fragment();
                        }

                        showTimer(SECONDS_TO_COUNTDOWN * MILLIS_PER_SECOND);
                    }*/
                    if (!Var_class.Item_connection_for_webservice[3].toString().equals("local session") && Var_class.OnOffLine) {
                        Var_class.if_call = 2;
                        if (Var_class.id_tab_cache == "" && Var_class.ansver_qu == "") {
                            Var_class.break_insert_ax = true;
                            (new Insert_ax()).func_insert_ax_timer();
                            if (call_reconfigure_frag < 5) {
                                call_reconfigure_frag++;
                            } else {
                                (new Insert_ax()).func_reconnect_result_fragment();
                                call_reconfigure_frag = 0;
                            }

                        }
                    }
                    else if (Var_class.Item_connection_for_webservice[3].toString().equals("local session"))
                    {


                        if (Var_class.call_transact == false) {
                            AsyncCallWS task = new AsyncCallWS();
                            Var_class.if_call = 1;
                            task.execute();

                        }


                    }
                    showTimer(SECONDS_TO_COUNTDOWN * MILLIS_PER_SECOND);


                } catch (NumberFormatException e) {
                    // method ignores invalid (non-integer) input and waits
                    // for something it can use
                }
            }
        }.start();
    }
}
