package darkblue.com.skyline;

import android.app.Application;

import org.xutils.x;


/**
 * Created by 欢大哥 on 2017/3/2.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//Xutils框架注入
    }
}
