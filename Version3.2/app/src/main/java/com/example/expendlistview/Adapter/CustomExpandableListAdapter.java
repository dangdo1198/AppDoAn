package com.example.expendlistview.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.expendlistview.MenuObject.GroupObject;
import com.example.expendlistview.MenuObject.ItemObject;
import com.example.expendlistview.R;

import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
//    private List<String> listTitle;
//    private Map<String,List<String>> listItem;

    private List<GroupObject> mListGroup;
    private Map<GroupObject, List<ItemObject>> mListItem;

    public CustomExpandableListAdapter(Context context, List<GroupObject> mListGroup, Map<GroupObject, List<ItemObject>> mListItem) {
        this.context = context;
        this.mListGroup = mListGroup;
        this.mListItem = mListItem;
    }

    @Override
    public int getGroupCount() {
        if(mListGroup!=null){
            return mListGroup.size();
        }
        return 0;
//        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(mListGroup!=null && mListItem!= null){
            return mListItem.get(mListGroup.get(groupPosition)).size();
        }
        return 0;
//        return listItem.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListGroup.get(groupPosition);
//        return listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
//        return listItem.get(listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        GroupObject groupObject = mListGroup.get(groupPosition);
        return groupObject.getId();
//        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        ItemObject itemObject = mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
        return itemObject.getId();
//        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group,parent,false);


        }
        TextView tvGroup = convertView.findViewById(R.id.listTitle);
        GroupObject groupObject = mListGroup.get(groupPosition);
        tvGroup.setTypeface(null, Typeface.ITALIC);
        tvGroup.setText(groupObject.getName().toUpperCase());
        return convertView;

//       String title =(String)getGroup(groupPosition);
//       if(convertView==null){
//           convertView= LayoutInflater.from(context).inflate(R.layout.list_group,null);
//       }
//       TextView txtTitle =(TextView)convertView.findViewById(R.id.listTitle);
//       txtTitle.setTypeface(null, Typeface.BOLD);
//       txtTitle.setText(title);
//        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);


        }
        TextView tvItem = convertView.findViewById(R.id.expandableListItem);
        ItemObject itemObject = mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
        tvItem.setText(itemObject.getName());
        return convertView;

//        String title =(String)getChild(groupPosition,childPosition);
//        if(convertView==null){
//            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
//        }
//        TextView txtChild =(TextView)convertView.findViewById(R.id.expandableListItem);
//        txtChild.setText(title);
//        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
