package crazywife.upc.com.crazywife.event;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ChangedEvent {
    public final byte temp;
    public final byte shidu;
    public final byte air;
    public final byte light;


    public ChangedEvent(byte temp,byte shidu, byte air, byte light) {
        this.temp = temp;
        this.air = air;
        this.shidu=shidu;
        this.light = light;
    }
}
