package tc.ex_timc_v1.dev_cl.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by au.oschepkov on 13.03.2015.
 */
public class Customization_Singleton {
    public List<Singleton_gv> setArray_(String[] array_idname, String[] array_name) {
        int ind_array = 0;
        List<Singleton_gv> prod = new ArrayList<>();
        while (ind_array < array_idname.length) {
            if (!array_idname[ind_array].equals("Экс")) {
                prod.add(new Singleton_gv(array_idname[ind_array], array_name[ind_array]));
            }
            ind_array++;
        }
        return prod;
    }
}
