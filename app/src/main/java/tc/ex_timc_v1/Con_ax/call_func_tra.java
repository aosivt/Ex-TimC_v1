package tc.ex_timc_v1.Con_ax;

import android.app.ProgressDialog;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import tc.ex_timc_v1.var_cl.Var_class;

/**
 * Created by au.oschepkov on 16.03.2015.
 */
public class call_func_tra {

    private static final String INSTANCE = Var_class.WSA;


    private static final String NAMESPACE = "http://172.16.20.48/" + INSTANCE;
    //private static final String NAMESPACE = "http://172.16.20.48/wsa_v3";
    //  private static final String URL = "http://172.16.20.48/wsa_v4/Service1.asmx?wsdl";

    private static final String METHOD_NAME = "transact_all";

    private static final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;
    //private static final String SOAP_ACTION = "http://172.16.20.48/wsa_/transact_all";
    private static TrustManager[] trustManagers;

    public static void allowAllSSL() {

        javax.net.ssl.HttpsURLConnection
                .setDefaultHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

        javax.net.ssl.SSLContext context = null;

        if (trustManagers == null) {
            trustManagers = new javax.net.ssl.TrustManager[]{new _FakeX509TrustManager()};
        }

        try {
            context = javax.net.ssl.SSLContext.getInstance("TLS");
            context.init(null, trustManagers, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            Log.e("allowAllSSL", e.toString());
        } catch (KeyManagementException e) {
            Log.e("allowAllSSL", e.toString());
        }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(context
                .getSocketFactory());
    }

    public boolean transact_all() {

        SoapObject req = new SoapObject(NAMESPACE, METHOD_NAME);

        req.addProperty("var1", (new Var_class()).getItem_connection_for_webservice()[0].toString() + "/" +
                (new Var_class()).getItem_connection_for_webservice()[1].toString()); ///имя пользователя
        req.addProperty("var2", (new Var_class()).getItem_connection_for_webservice()[2].toString()); ///пароль


        Var_class.success_select = false;

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(req);
//        HttpTransportSE trans = new HttpTransportSE(URL, 60000);
        allowAllSSL();
        //HttpsTransportSE trans = new HttpsTransportSE("topgorn.sibcem.ru",443,"/wsa_v4/Service1.asmx", 60000);
        HttpsTransportSE trans = new HttpsTransportSE("topgorn.sibcem.ru", 443, "/" + INSTANCE + "/Service1.asmx", 60000);


        try {
            trans.call(SOAP_ACTION, envelope);
            ((ProgressDialog) Var_class.message_for_connection_web).incrementProgressBy(60);

            if (envelope.getResponse() == null) {
                Var_class.error_connect = "Нет подключения к Axapta!!! Необходимо перезагрузить приложение, при повторном возникновении ошибки обратитесь в тех поддержку";
                return Var_class.success_select;
            }

            SoapObject response = (SoapObject) envelope.getResponse();

            int i = 0;
            int j = 0;
            int k = 0;
            SoapObject result_ax_r2 = null;
            SoapObject result_ax_r3 = null;
            ArrayList<String> result_comback = new ArrayList<String>();

            while (i < response.getPropertyCount()) {
                result_ax_r2 = (SoapObject) response.getProperty(i);
                while (j < result_ax_r2.getPropertyCount()) {
                    result_ax_r3 = (SoapObject) result_ax_r2.getProperty(j);

                    while (k < result_ax_r3.getPropertyCount()) {
                        if(result_ax_r3.getProperty(k).toString().equals("0"))
                        {
                            Var_class.if_call = Integer.parseInt(result_ax_r3.getProperty(k).toString());
                            Var_class.error_connect = result_ax_r3.getProperty(k+1).toString();
                            return Var_class.success_select;
                        }
                        else if(!result_ax_r3.getProperty(k).toString().equals("0"))
                        {
                            result_comback.add(result_ax_r3.getProperty(k).toString());
                            k++;
                        }
                    }
                    k = 0;
                    func_ins(i, j, result_comback);
                    result_comback.clear();
                    j++;
                }
                j = 0;
                i++;
            }
            Var_class.success_select = true;
            Var_class.OnOffLine = Var_class.success_select;
            i = 0;
            j = 0;
            k = 0;

        } catch (IOException e) {
            e.printStackTrace();

            Var_class.error_connect = "Нет подключения к серверу приложений!!! Необходимо перезагрузить приложение, при повторном возникновении ошибки обратитесь в тех поддержку";
            Var_class.success_select = false;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Var_class.error_connect = "Не удалось получить данные проверьте Имя пользователя и Пароль";
            Var_class.success_select = false;
        } catch (Exception e) {

            e.printStackTrace();
            Var_class.success_select = false;
        }

        trans.reset();

        return Var_class.success_select;

    }

    public void func_ins(int ind_i, int ind_j, ArrayList<String> ar_l) {
        if (ind_i == 0) {
            if (ind_j == 0) {
                Var_class.listviewitem_it1 = new String[ar_l.size()];
                Var_class.listviewitem_it1 = ar_l.toArray(Var_class.listviewitem_it1);

            } else if (ind_j == 1) {
                Var_class.listviewitem_it2 = new String[ar_l.size()];
                Var_class.listviewitem_it2 = ar_l.toArray(Var_class.listviewitem_it2);

            }
        } else if (ind_i == 1) {
            if (ind_j == 0) {
                Var_class.listviewitem_ex1 = new String[ar_l.size()];
                Var_class.listviewitem_ex1 = ar_l.toArray(Var_class.listviewitem_ex1);

            } else if (ind_j == 1) {
                Var_class.listviewitem_ex2 = new String[ar_l.size()];
                Var_class.listviewitem_ex2 = ar_l.toArray(Var_class.listviewitem_ex2);

            }
        } else if (ind_i == 2) {
            if (ind_j == 0) {
                Var_class.listviewitem_sc1 = new String[ar_l.size()];
                Var_class.listviewitem_sc1 = ar_l.toArray(Var_class.listviewitem_sc1);

            } else if (ind_j == 1) {
                Var_class.listviewitem_sc2 = new String[ar_l.size()];
                Var_class.listviewitem_sc2 = ar_l.toArray(Var_class.listviewitem_sc2);

            }
        } else if (ind_i == 3) {
            if (ind_j == 0) {
                Var_class.listviewitem_ts1 = new String[ar_l.size()];
                Var_class.listviewitem_ts1 = ar_l.toArray(Var_class.listviewitem_ts1);

            } else if (ind_j == 1) {
                Var_class.listviewitem_ts2 = new String[ar_l.size()];
                Var_class.listviewitem_ts2 = ar_l.toArray(Var_class.listviewitem_ts2);

            }
        }
        /*else if (ind_i == 4) {
            if (ind_j == 0) {
                Var_class.listviewitem_point_set1 = new String[ar_l.size()];
                Var_class.listviewitem_point_set1 = ar_l.toArray(Var_class.listviewitem_point_set1);

            } else if (ind_j == 1) {
                Var_class.listviewitem_point_set2 = new String[ar_l.size()];
                Var_class.listviewitem_point_set2 = ar_l.toArray(Var_class.listviewitem_point_set2);
            }
        }*/

    }

    public String func_insert_ax_web(String[] insert_item) {
        String NAMESPACE_inesrt = "http://172.16.20.48/" + INSTANCE;
        //String NAMESPACE_inesrt = "http://172.16.20.48/wsa_Live";


        //String URL_inesrt = "http://172.16.20.48/wsa_v4/Service1.asmx?wsdl";

        String METHOD_NAME_inesrt = "ax_web_insertLive";

        String SOAP_ACTION_inesrt = NAMESPACE_inesrt + "/" + METHOD_NAME_inesrt;

        SoapObject req_inesrt = new SoapObject(NAMESPACE_inesrt, METHOD_NAME_inesrt);

        req_inesrt.addProperty("insert_ax1", insert_item[0].toString().split("/")[0].toString()); ///рабочие центры insert_item[0]
        req_inesrt.addProperty("insert_ax8", insert_item[0].toString().split("/")[1].toString()); ///точка погрузки
        req_inesrt.addProperty("insert_ax2", insert_item[1]); ///транспортное средство_item[1]
        req_inesrt.addProperty("insert_ax3", insert_item[2]); ///номенклатура insert_item[2]
        req_inesrt.addProperty("insert_ax4", insert_item[3]); ///склады insert_item[3]
        req_inesrt.addProperty("insert_ax5", insert_item[4]); ///дата insert_item[4]
        req_inesrt.addProperty("insert_ax6", insert_item[7]); ///дата insert_item[5]
        req_inesrt.addProperty("insert_ax7", insert_item[8]); ///время insert_item[5]

        ///на клиентской стороне
        //рабочий центр (экскаватор) 0
        //код номенклатуры 1
        //склад 2
        //транспортное средство (гаражный номер) 3
        //транспортное средство (гос номер) 4
        //дата заполнения 5
        //время заполнения 6

        ///на серверной стороне
        //рабочий центр (экскаватор) 0
        //код номенклатуры 1
        //склад 2
        //транспортное средство (гаражный номер) 3
        //транспортное средство (гос номер) 4
        //дата заполнения 5
        //время заполнения 6


        req_inesrt.addProperty("var1",
                (new Var_class()).getItem_connection_for_webservice()[0].toString() + "/" +
                (new Var_class()).getItem_connection_for_webservice()[1].toString() + "/" +
                insert_item[5].toString()); ///имя пользователя подключившегося / имя пользователя создавшего запись

        req_inesrt.addProperty("var2",
                (new Var_class()).getItem_connection_for_webservice()[2].toString()); ///пароль


/*
        req_inesrt.addProperty("var1", insert_item[5]); ///имя пользователя
        req_inesrt.addProperty("var2", insert_item[6]); ///пароль
*/
        SoapSerializationEnvelope envelope_insert = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope_insert.dotNet = true;

        envelope_insert.setOutputSoapObject(req_inesrt);

        allowAllSSL();
        ///HttpsTransportSE trans = new HttpsTransportSE("topgorn.sibcem.ru",443,"/wsa_v4/Service1.asmx", 6000);
        HttpsTransportSE trans = new HttpsTransportSE("topgorn.sibcem.ru", 443, "/" + INSTANCE + "/Service1.asmx", 60000);
//        HttpTransportSE trans = new HttpTransportSE(URL_inesrt,60000);

//        allowAllSSL();
        //      HttpsTransportSE trans = new HttpsTransportSE("192.168.1.11",443,"/webservice1.asmx", 60000);


        try {

            trans.call(SOAP_ACTION_inesrt, envelope_insert);
            SoapPrimitive response_inesrt = (SoapPrimitive) envelope_insert.getResponse();
            Var_class.success_select = true;
            Var_class.error_connect = "Данные переданны и записаны";
            return response_inesrt.getValue().toString();

        } catch (IOException e) {
            Var_class.success_select = false;
            Var_class.error_connect = " Ошибка записи - проверьте подключение или свяжитесь с технической поддержкой";
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            Var_class.success_select = false;
            Var_class.error_connect = "Нет подключения к серверу приложений - ошибка записи!!!";
            e.printStackTrace();
        }
        trans.reset();
        return "break!!!";
    }

    public static class _FakeX509TrustManager implements
            javax.net.ssl.X509TrustManager {
        private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[]{};

        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public boolean isClientTrusted(X509Certificate[] chain) {
            return (true);
        }

        public boolean isServerTrusted(X509Certificate[] chain) {
            return (true);
        }

        public X509Certificate[] getAcceptedIssuers() {
            return (_AcceptedIssuers);
        }
    }


}
