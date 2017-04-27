package crazywife.upc.com.crazywife;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static crazywife.upc.com.crazywife.R.id.name;


/**
 * A simple {@link Fragment} subclass.
 */
public class KucunFragment extends Fragment {

    ListView list;
    public KucunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.fragment_kucun, container, false);
        list = (ListView) view.findViewById(R.id.list_1);
        SqliteHelper helper = new SqliteHelper(this.getActivity().getApplicationContext(),"food",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query("food",null,null,null,null,null,null);

        HashMap<String,Object> map = new HashMap<String,Object>();
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        while(cursor.moveToNext()){
            map.put("name",cursor.getString(cursor.getColumnIndex("name")));
            map.put("amount",cursor.getString(cursor.getColumnIndex("amount")));
            map.put("date",cursor.getString(cursor.getColumnIndex("date")));
            listItem.add(map);
        }
        db.close();
        SimpleAdapter listadapter = new SimpleAdapter(this.getActivity(),listItem,R.layout.list_item,
                new String[]{"name","amount","date"},
                new int[]{R.id.txt_name,R.id.txt_amount,R.id.txt_date});
        list.setAdapter(listadapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SqliteHelper helper = new SqliteHelper(getActivity().getApplicationContext(),"food",null,1);
                TextView name = (TextView) parent.findViewById(R.id.txt_name);
                String s = name.getText().toString();

                SQLiteDatabase db = helper.getWritableDatabase();
                db.delete("food","name=?",new String[]{s});
                Snackbar.make(getView(),"okay!",Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });
        return  view;
    }

}
