package crazywife.upc.com.crazywife;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import crazywife.upc.com.crazywife.event.ChangedEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateFragment extends Fragment {

    Thread thread;
    TextView temp;

    TextView air;

    TextView light;
    TextView shidu;


    public StateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_state, container, false);
        temp = (TextView) view.findViewById(R.id.temp);
        air = (TextView) view.findViewById(R.id.air);
        light = (TextView) view.findViewById(R.id.light);
        shidu = (TextView) view.findViewById(R.id.shidu);
        EventBus.getDefault().register(this);


        return view;
    }
    @Subscribe
    public void updateTemp(ChangedEvent event){
        String airstate[] ={"正常","异常"};
        String doorstate[] = {"打开","关闭"};
        temp.setText("温度："+event.temp);
        shidu.setText("湿度"+event.shidu+"%");
        air.setText("箱内气体状态:"+airstate[event.air]);
        light.setText("冰箱门："+doorstate[event.light]);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
