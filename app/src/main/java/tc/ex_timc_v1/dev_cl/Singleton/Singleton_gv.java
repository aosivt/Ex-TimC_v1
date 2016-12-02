package tc.ex_timc_v1.dev_cl.Singleton;

/**
 * Created by au.oschepkov on 13.03.2015.
 */
public class Singleton_gv {
    private String name;
    private String idname;

    public Singleton_gv(String name, String idname) {
        this.name = name;
        this.idname = idname;
    }

    public String getidname() {
        return idname;
    }

    public void setidname(String price) {
        this.idname = idname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
