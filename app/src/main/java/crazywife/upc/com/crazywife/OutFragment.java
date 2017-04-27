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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OutFragment extends Fragment implements View.OnClickListener {



    EditText name;

    ListView list;
    Button btn_search;
    public OutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_out, container, false);
        name= (EditText) view.findViewById(R.id.name);
        list = (ListView) view.findViewById(R.id.list);
        btn_search = (Button) view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
            SqliteHelper helper = new SqliteHelper(this.getActivity().getApplicationContext(),"food",null,1);
                SQLiteDatabase db = helper.getWritableDatabase();
            String selection = "name=?";
            String[] selectionArgs = new String[]{name.getText().toString()};
                Cursor cursor = db.query("food",null,selection,selectionArgs,null,null,null);

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
                    return  true;
                }
                });
        }
    }
}
