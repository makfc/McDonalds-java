package com.mcdonalds.app.msa;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.ArrayList;
import java.util.Arrays;

public class MSAChooseTuneFragment extends URLNavigationFragment {
    private MSATuneListAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_msa_choose_tune_page, null);
        ListView listView = (ListView) rootView.findViewById(C2358R.C2357id.msa_tune_list);
        ArrayList<MSATuneItem> al = new ArrayList();
        al.addAll(Arrays.asList(MSASettings.BUNDLED_TUNES));
        al.add(MSASettings.RANDOM_TUNE);
        this.mAdapter = new MSATuneListAdapter(getActivity(), 17367043, al);
        listView.setAdapter(this.mAdapter);
        return rootView;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.onDestroy();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 1) {
            Uri uri = data.getData();
            if (uri != null) {
                String name = "";
                String[] projection = new String[]{"_display_name"};
                Cursor c = getActivity().getContentResolver().query(uri, projection, null, null, null);
                if (c != null) {
                    if (c.moveToFirst()) {
                        int colIndex = c.getColumnIndex(projection[0]);
                        if (colIndex != -1) {
                            name = c.getString(colIndex);
                        }
                    }
                    c.close();
                }
                MSATuneItem item = MSASettings.PHONE_TUNE;
                item.setChoice(name);
                item.setMusicUri(uri);
                MSASettings.saveAlarmId(getActivity(), MSATuneItem.MSA_TUNE_FROM_PHONE, uri.toString(), name);
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }
}
