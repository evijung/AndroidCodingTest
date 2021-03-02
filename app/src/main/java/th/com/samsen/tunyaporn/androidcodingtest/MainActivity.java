package th.com.samsen.tunyaporn.androidcodingtest;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton, deleteButton;
    ListView recyclerView;
    EditText inputEditText;
    String[] mDataSet, mDataChecked;
List<String> mData, mChecked;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindWidget();

        mData = new ArrayList<>();
        mChecked = new ArrayList<>();


        addButton.setEnabled(false);
        addButton.getBackground().setTint(Color.parseColor("#50e23425"));

        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    addButton.getBackground().setTint(Color.parseColor("#50e23425"));
                    addButton.setEnabled(false);
                } else {
                    addButton.setEnabled(true);
                    addButton.getBackground().setTint(Color.parseColor("#e23425"));
                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int x = mData.size() ; x > 0; x--)
                {
                    if (mChecked.get(x-1).equals("Y")) {

                        mData.remove(x-1);
                        mChecked.remove(x-1);
                    }

                }


                mDataSet = mData.toArray(new String[mData.size()]);
                mDataChecked = mChecked.toArray(new String[mChecked.size()]);
                ViewAdapter viewAdapter = new ViewAdapter(mDataSet,mDataChecked );
                recyclerView.setAdapter(viewAdapter);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mData.add(inputEditText.getText().toString());
                mChecked.add("N");

                mDataSet = mData.toArray(new String[mData.size()]);
                mDataChecked = mChecked.toArray(new String[mChecked.size()]);
                    ViewAdapter viewAdapter = new ViewAdapter(mDataSet,mDataChecked );
                recyclerView.setAdapter(viewAdapter);

                inputEditText.setText("");

            }
        });



    }

    private void bindWidget() {

        addButton = this.findViewById(R.id.btn_add);
        deleteButton = findViewById(R.id.btn_delete);
        recyclerView = findViewById(R.id.item_view);
        inputEditText = findViewById(R.id.edt_input);

    }

    public class ViewAdapter extends BaseAdapter {

        String[] data;
        String[] check;
        ViewHolder viewHolder;

        private class ViewHolder{

            TextView textView;
            CheckBox checkBox;

            public ViewHolder(View view) {
                textView = view.findViewById(R.id.item_text);
                checkBox = view.findViewById(R.id.item_box);

            }


        }

        public ViewAdapter(String[] data, String[] check) {
            this.data = data;
            this.check = check;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int i) {
            return data[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view != null) {
                viewHolder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view, viewGroup, false);
                viewHolder = new ViewHolder(view);

                view.setTag(viewHolder);
            }

            viewHolder.textView.setText(data[i]);

            viewHolder.checkBox.setChecked(check[i].equals("Y"));

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        check[i] = "Y";
                        mChecked.set(i, "Y");
                    } else {

                        check[i] = "N";
                        mChecked.set(i, "N");
                    }
                }
            });

            return view;
        }
    }

//    public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.Holder> {
//
//        private final String[] data;
//        private final String[] checked;
//
//        public ItemViewAdapter(String[] mDataSet, String[] checkedBox) {
//            data = mDataSet;
//            checked = checkedBox;
//        }
//
//
//        class Holder extends RecyclerView.ViewHolder {
//
//            TextView textView;
//            CheckBox checkBox;
//
//            public Holder(View itemView) {
//                super(itemView);
//
//                textView = itemView.findViewById(R.id.item_text);
//                checkBox = itemView.findViewById(R.id.item_box);
//            }
//
//            public void setItem(int position) {
//                textView.setText(data[position]);
//                Log.d("TAG", "setItem: " + position + " Text " + inputEditText.getText());
//
//                checkBox.setChecked(checked[position].equals("Y"));
//            }
//
//
//        }
//
//        @NonNull
//        @Override
//        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view, parent, false);
//            Holder holder = new Holder(view);
//
//
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull Holder holder, int position) {
//
//            holder.setItem(position);
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return data.length;
//        }
//    }

}