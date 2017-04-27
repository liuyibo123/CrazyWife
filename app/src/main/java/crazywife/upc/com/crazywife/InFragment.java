package crazywife.upc.com.crazywife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class InFragment extends Fragment implements View.OnClickListener {

    TextInputEditText name;
    TextInputEditText amount;

    Button btnMakesure;

    Button btnCancel;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in, container, false);
        name = (TextInputEditText) view.findViewById(R.id.name);
        amount = (TextInputEditText) view.findViewById(R.id.amount);
        btnMakesure = (Button) view.findViewById(R.id.btn_makesure);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        btnMakesure.setOnClickListener(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_makesure:
                SqliteHelper helper = new SqliteHelper(this.getActivity().getApplicationContext(),"food",null,1);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name",name.getText().toString());
                cv.put("amount",Float.parseFloat(amount.getText().toString()));
                cv.put("date",(String)DateFormat.format("dd日 kk:mm", new Date()));
                db.insert("food",null,cv);
                Snackbar.make(getView(),"okay!",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel:
                name.setText("");
                amount.setText("");
                break;
        }
    }



}
