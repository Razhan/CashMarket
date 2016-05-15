package com.example.cashmarket;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.DateFormat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import com.example.cashmarket.Helper.*;



public class ArchiveFileChooser extends ListActivity {

    private FileArrayAdapter adapter;
    private File currentDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDir = new File(getApplicationContext().getFilesDir().getAbsolutePath());
        //File prefsdir = new File(getApplicationInfo().dataDir,"shared_prefs");
        fill(currentDir);
    }

    private void fill(File f) {
        File[]dirs = f.listFiles();

        //Log.i("path", f.getName());
        List<Item>dir = new ArrayList<Item>();
        List<Item>fls = new ArrayList<Item>();

        try{
            for(File ff: dirs)
            {
                //Log.i("file", ff.getName());

                java.util.Date lastModDate = new java.util.Date(ff.lastModified());
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date_modify = formater.format(lastModDate);

                fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"file_icon"));
            }
        }catch(Exception e)
        {

        }
        dir.addAll(fls);
        adapter = new FileArrayAdapter(ArchiveFileChooser.this,R.layout.activity_file_view,dir);
        this.setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Item item = adapter.getItem(position);

        Intent intent = new Intent();
        intent.putExtra("From",true);
        intent.putExtra("FileName",item.getName());

        intent.setClass(ArchiveFileChooser.this, Load.class);
        ArchiveFileChooser.this.startActivity(intent);
    }

    public void delete (String path, Item o) {
        File file = new File(path);
        File spath = new File(getApplicationInfo().dataDir,"shared_prefs");
        String temp = spath.getAbsolutePath()+ "/" + o.getName() + ".xml";
        File sfile = new File(temp);

        file.delete();
        sfile.delete();


        adapter.remove(o);
        this.setListAdapter(adapter);
    }

    public class FileArrayAdapter extends ArrayAdapter<Item> {

        private Context c;
        private int id;
        private List<Item>items;

        public FileArrayAdapter(Context context, int textViewResourceId, List<Item> objects) {
            super(context, textViewResourceId, objects);
            c = context;
            id = textViewResourceId;
            items = objects;
        }
        public Item getItem(int i)
        {
            return items.get(i);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(id, null);
            }

               /* create a new view of my layout and inflate it in the row */
            //convertView = ( RelativeLayout ) inflater.inflate( resource, null );

            final Item o = items.get(position);
            if (o != null) {
                TextView t1 = (TextView) v.findViewById(R.id.TextView01);
                TextView t2 = (TextView) v.findViewById(R.id.TextView02);
                TextView t3 = (TextView) v.findViewById(R.id.TextViewDate);
                       /* Take the ImageView from layout and set the city's image */
                ImageView imageCity = (ImageView) v.findViewById(R.id.fd_Icon1);
                String uri = "drawable/" + o.getImage();
                int imageResource = c.getResources().getIdentifier(uri, null, c.getPackageName());
                Drawable image = c.getResources().getDrawable(imageResource);
                imageCity.setImageDrawable(image);

                if(t1!=null)
                    t1.setText(o.getName());
                if(t2!=null)
                    t2.setText(o.getData());
                if(t3!=null)
                    t3.setText(o.getDate());


                final Button remove = (Button) v.findViewById(R.id.button2);
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(ArchiveFileChooser.this)
                                .setTitle("确认")
                                .setMessage("确定删除" + o.getName() + "吗？")
                                .setNegativeButton("否", null)
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        delete(o.getPath(), o);
                                    }
                                })
                                .show();
                    }
                });
            }
            return v;
        }
    }
}
