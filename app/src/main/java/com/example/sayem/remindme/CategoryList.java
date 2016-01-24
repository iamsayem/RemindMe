package com.example.sayem.remindme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CategoryList extends Activity {

    private ExpandableListView categoryListView;
    private CategoryListAdapter categoryListAdapter;
    static List<String> listDataHeader;
    static HashMap<String, List<String> > listDataChild;
    static int group_size, child_size;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeContextMenu();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivityForResult(getIntent(), 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        categoryListView = (ExpandableListView) findViewById(R.id.categoryListView);
    }



    @Override
    protected void onResume() {
        super.onResume();
        prepareListData();
        categoryListAdapter = new CategoryListAdapter(this, listDataHeader, listDataChild);
        categoryListView.setAdapter(categoryListAdapter);
        registerForContextMenu(categoryListView);
        categoryListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (categoryListAdapter.getChildrenCount(groupPosition) == 0) {
                    Toast.makeText(getApplicationContext(), "Empty!!!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        /*if (categoryListAdapter == null){
            categoryListAdapter = new CategoryListAdapter(this, listDataHeader, listDataChild);
            categoryListView.setAdapter(categoryListAdapter);
            categoryListAdapter.notifyDataSetChanged();
            registerForContextMenu(categoryListView);
            categoryListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                    if (categoryListAdapter.getChildrenCount(groupPosition) == 0) {
                        Intent intent = new Intent(CategoryList.this, ItemEditorActivity.class);

                        String text = categoryListAdapter.getGroupText(groupPosition);
                        intent.putExtra("GROUP_POSITION", text);
                        Toast.makeText(CategoryList.this, "Group Position: " + text, Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 2);
                    }
                    else{
                        categoryListAdapter.notifyDataSetChanged();
                    }
                    return false;
                }
            });
        }*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.reset:
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CategoryList.this);
                alertDialog.setTitle("Attention!!");
                alertDialog.setMessage("Are you sure want to delete?");
                alertDialog.setIcon(R.drawable.ic_error_black_24dp);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ItemListDatabase itemListDatabase = new ItemListDatabase(CategoryList.this);
                        itemListDatabase.deleteAll();
                        categoryListAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Delete Successful!!", Toast.LENGTH_SHORT).show();
                        onRestart();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                alertDialog.create();
                alertDialog.show();
                return true;

            case R.id.alarm:
                Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(intent);
                return true;
            case R.id.aboutUs:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.categoryListView){
            ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
            int type = ExpandableListView.getPackedPositionType(info.packedPosition);
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
            //menu.setHeaderTitle(listDataHeader.get(groupPos));
            if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
                menu.setHeaderTitle("Are you sure want to delete " + "\"" + listDataChild.get(listDataHeader.get(groupPos)).get(childPos)+ "\"?");
                menu.setHeaderIcon(R.drawable.ic_error_black_24dp);
                menu.add(Menu.NONE, 1, Menu.NONE, "Yes");
                menu.add(Menu.NONE, 2, Menu.NONE, "No");
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
        String groupName = categoryListAdapter.getGroupText(groupPos);
        String itemName = categoryListAdapter.getChildText(groupPos, childPos);
        switch (item.getItemId()){
            /*case 0:
                if (categoryListAdapter.getChildrenCount(groupPos) > 0){
                    Intent intent = new Intent(CategoryList.this, ItemEditorActivity.class);
                    intent.putExtra("GROUP_POSITION", groupName);
                    startActivityForResult(intent, 2);
                    return true;
                }
                else{
                    return false;
                }*/
            case 1:
                ItemListDatabase itemListDatabase = new ItemListDatabase(CategoryList.this);
                //itemListDatabase.deleteTable(groupName);
                itemListDatabase.deleteRow(groupName, itemName);
                categoryListAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Delete Successful!!", Toast.LENGTH_SHORT).show();
                onRestart();
                return true;
            case 2:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String group_name = data.getStringExtra("GROUP_NAME");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 2){
            addItemData(group_name);
        }
    }

    private void showPickMenu(View anchor, final int groupPosition) {


        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), anchor);
        popupMenu.inflate(R.menu.category_item_menu);
        setForceShowIcon(popupMenu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
                //int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
                final String groupName = categoryListAdapter.getGroupText(groupPosition);


                switch (item.getItemId()) {
                    case R.id.editMenu:
                        //if (categoryListAdapter.getChildrenCount(groupPosition) == 0) {
                            Intent intent = new Intent(CategoryList.this, ItemEditorActivity.class);
                            String text = categoryListAdapter.getGroupText(groupPosition);
                            intent.putExtra("GROUP_POSITION", text);
                            Toast.makeText(CategoryList.this, text, Toast.LENGTH_SHORT).show();
                            startActivityForResult(intent, 2);
                        //}
                        break;
                    case R.id.deleteMenu:

                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CategoryList.this);
                        alertDialog.setTitle("Attention!!");
                        alertDialog.setMessage("Are you sure want to delete?");
                        alertDialog.setIcon(R.drawable.ic_error_black_24dp);
                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ItemListDatabase itemListDatabase = new ItemListDatabase(CategoryList.this);
                                itemListDatabase.deleteTable(groupName);
                                categoryListAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Delete Successful!!", Toast.LENGTH_SHORT).show();
                                onRestart();
                            }
                        });
                        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });
                        alertDialog.create();
                        alertDialog.show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }


    public static void setForceShowIcon(PopupMenu popupMenu){
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields){
                if ("mPopup".equals(field.getName())){
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    private  void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String> >();
        ItemListDatabase itemListDatabase = new ItemListDatabase(CategoryList.this);

        listDataHeader.clear();
        // Adding group data
        listDataHeader.add("Pharmacy");
        listDataHeader.add("Super Shop");
        listDataHeader.add("Stationery Shop");
        listDataHeader.add("Market");
        listDataHeader.add("Hotel");
        listDataHeader.add("Hardware Shop");
        listDataHeader.add("Computer Accessories Shop");
        listDataHeader.add("Others");

        listDataChild.clear();
        List<String> pharmacy = new ArrayList<String>();
        if (itemListDatabase.readData("Pharmacy").length > 0){
            String item[] = itemListDatabase.readData("Pharmacy");
            for (int i = 0; i < item.length; i++){
                pharmacy.add(item[i]);
            }
        }
        List<String> super_shop = new ArrayList<String>();
        if (itemListDatabase.readData("Super Shop").length > 0){
            String item[] = itemListDatabase.readData("Super Shop");
            for (int i = 0; i < item.length; i++){
                super_shop.add(item[i]);
            }
        }
        List<String> stationery_shop = new ArrayList<String>();
        if (itemListDatabase.readData("Stationery Shop").length > 0){
            String item[] = itemListDatabase.readData("Stationery Shop");
            for (int i = 0; i < item.length; i++){
                stationery_shop.add(item[i]);
            }
        }
        List<String> market = new ArrayList<String>();
        if (itemListDatabase.readData("Market").length > 0){
            String item[] = itemListDatabase.readData("Market");
            for (int i = 0; i < item.length; i++){
                market.add(item[i]);
            }
        }
        List<String> hotel = new ArrayList<String>();
        if (itemListDatabase.readData("Hotel").length > 0){
            String item[] = itemListDatabase.readData("Hotel");
            for (int i = 0; i < item.length; i++){
                hotel.add(item[i]);
            }
        }
        List<String> hardware_shop = new ArrayList<String>();
        if (itemListDatabase.readData("Hardware Shop").length > 0){
            String item[] = itemListDatabase.readData("Hardware Shop");
            for (int i = 0; i < item.length; i++){
                hardware_shop.add(item[i]);
            }
        }
        List<String> computer_accessories_shop = new ArrayList<String>();
        if (itemListDatabase.readData("Computer Accessories Shop").length > 0){
            String item[] = itemListDatabase.readData("Computer Accessories Shop");
            for (int i = 0; i < item.length; i++){
                computer_accessories_shop.add(item[i]);
            }
        }
        List<String> others = new ArrayList<String>();



        listDataChild.put(listDataHeader.get(0), pharmacy);
        listDataChild.put(listDataHeader.get(1), super_shop);
        listDataChild.put(listDataHeader.get(2), stationery_shop);
        listDataChild.put(listDataHeader.get(3), market);
        listDataChild.put(listDataHeader.get(4), hotel);
        listDataChild.put(listDataHeader.get(5), hardware_shop);
        listDataChild.put(listDataHeader.get(6), computer_accessories_shop);
        listDataChild.put(listDataHeader.get(7), others);

    }

    private void addItemData(String text){
        ItemListDatabase itemListDatabase = new ItemListDatabase(CategoryList.this);
        String item[] = itemListDatabase.readData(text);

        // Adding child data
        List<String> pharmacy = new ArrayList<String>();
        List<String> super_shop = new ArrayList<String>();
        List<String> stationery_shop = new ArrayList<String>();
        List<String> market = new ArrayList<String>();
        List<String> hotel = new ArrayList<String>();
        List<String> hardware_shop = new ArrayList<String>();
        List<String> computer_accessories_shop = new ArrayList<String>();
        List<String> others = new ArrayList<String>();


        if (text.equals("Pharmacy")){
            for (int i = 0; i < item.length; i++){
                pharmacy.add(item[i]);
                categoryListAdapter.notifyDataSetChanged();
            }
        }
        else if (text.equals("Super Shop")){
            for (int i = 0; i < item.length; i++){
                super_shop.add(item[i]);
                categoryListAdapter.notifyDataSetChanged();
            }
        }
        else if (text.equals("Stationery Shop")){
            for (int i = 0; i < item.length; i++){
                stationery_shop.add(item[i]);
                categoryListAdapter.notifyDataSetChanged();
            }
        }
        else if (text.equals("Market")){
            for (int i = 0; i < item.length; i++){
                market.add(item[i]);
                categoryListAdapter.notifyDataSetChanged();
            }
        }
        else if (text.equals("Hotel")){
            for (int i = 0; i < item.length; i++){
                hotel.add(item[i]);
                categoryListAdapter.notifyDataSetChanged();
            }
        }
        else if (text.equals("Hardware Shop")){
            for (int i = 0; i < item.length; i++){
                hardware_shop.add(item[i]);
                categoryListAdapter.notifyDataSetChanged();
            }
        }
        else if (text.equals("Computer Accessories Shop")){
            for (int i = 0; i < item.length; i++){
                computer_accessories_shop.add(item[i]);
                categoryListAdapter.notifyDataSetChanged();
            }
        }

        listDataChild.put(listDataHeader.get(0), pharmacy);
        listDataChild.put(listDataHeader.get(1), super_shop);
        listDataChild.put(listDataHeader.get(2), stationery_shop);
        listDataChild.put(listDataHeader.get(3), market);
        listDataChild.put(listDataHeader.get(4), hotel);
        listDataChild.put(listDataHeader.get(5), hardware_shop);
        listDataChild.put(listDataHeader.get(6), computer_accessories_shop);
        listDataChild.put(listDataHeader.get(7), others);

    }

    public class CategoryListAdapter extends BaseExpandableListAdapter{

        private Context _context;
        private List<String> _listDataHeader; // Header titles
        private HashMap<String, List<String> > _listDataChild; // Child Items

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(_context.LAYOUT_INFLATER_SERVICE);

        public CategoryListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listDataChild;
        }

        @Override
        public int getGroupCount() {
            group_size = this._listDataHeader.size();
            return group_size;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            child_size = this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
            return child_size;
        }

        @Override
        public Object getGroup(int groupPosition) {

            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {

            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.category_item, null);
            }
            TextView categoryItem = (TextView) convertView.findViewById(R.id.categoryItem);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.tv_menu);
            categoryItem.setText(getGroup(groupPosition).toString());
            imageView.setImageResource(R.drawable.ic_action_threedots);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showPickMenu(v, groupPosition);
                    showPickMenu(v, groupPosition);
                }
            });

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.item_name, null);
            }

            TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
            itemName.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public String getGroupText(int groupPosition){
            return this._listDataHeader.get(groupPosition).toString();
        }

        public String getChildText(int groupPosition, int childPosition){
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
        }

    }

}
