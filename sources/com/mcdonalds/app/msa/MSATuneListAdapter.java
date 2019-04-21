package com.mcdonalds.app.msa;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.List;

public class MSATuneListAdapter extends ArrayAdapter<MSATuneItem> {
    private Activity mActivity;
    private Handler mHandler;
    private LayoutInflater mInflater;
    private List<MSATuneItem> mItems;
    private MediaPlayer mMediaPlayer;
    private int mPositionChecked = -1;
    private boolean mSelectMusicDisabled;

    private class ViewHolder {
        public View mBackground;
        public CheckBox mCheckBox;
        public TextView mTuneLine1;
        public TextView mTuneLine2;
        public ImageView mTunePreplay;

        private ViewHolder() {
        }

        /* synthetic */ ViewHolder(MSATuneListAdapter x0, C32701 x1) {
            this();
        }
    }

    static /* synthetic */ MediaPlayer access$202(MSATuneListAdapter x0, MediaPlayer x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$202", new Object[]{x0, x1});
        x0.mMediaPlayer = x1;
        return x1;
    }

    static /* synthetic */ int access$402(MSATuneListAdapter x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$402", new Object[]{x0, new Integer(x1)});
        x0.mPositionChecked = x1;
        return x1;
    }

    public MSATuneListAdapter(Activity a, int resource, List<MSATuneItem> items) {
        super(a, resource, items);
        this.mInflater = LayoutInflater.from(a);
        this.mActivity = a;
        this.mItems = items;
        this.mHandler = new Handler();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View root;
        boolean z;
        if (convertView == null) {
            root = this.mInflater.inflate(C2658R.layout.msa_tune_item, null);
        } else {
            root = convertView;
        }
        ViewHolder viewHolder = (ViewHolder) root.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(this, null);
            viewHolder.mTunePreplay = (ImageView) root.findViewById(C2358R.C2357id.msa_choose_tune_preplay);
            viewHolder.mTuneLine1 = (TextView) root.findViewById(C2358R.C2357id.msa_choose_tune_line1);
            viewHolder.mCheckBox = (CheckBox) root.findViewById(C2358R.C2357id.msa_choose_tune_checkbox);
            viewHolder.mBackground = root.findViewById(C2358R.C2357id.msa_choose_tune_background);
            viewHolder.mTuneLine2 = (TextView) root.findViewById(C2358R.C2357id.msa_choose_tune_line2);
            root.setTag(viewHolder);
        }
        final ViewHolder vh = viewHolder;
        final MSATuneItem item = (MSATuneItem) this.mItems.get(position);
        if (item.getType() == MSATuneItem.MSA_TUNE_RANDOM || item.getType() == MSATuneItem.MSA_TUNE_FROM_PHONE) {
            vh.mTunePreplay.setVisibility(4);
            vh.mTunePreplay.setOnClickListener(null);
        } else {
            vh.mTunePreplay.setVisibility(0);
            vh.mTunePreplay.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$100", new Object[]{MSATuneListAdapter.this}).removeCallbacksAndMessages(null);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$200", new Object[]{MSATuneListAdapter.this}) != null) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$200", new Object[]{MSATuneListAdapter.this}).stop();
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$200", new Object[]{MSATuneListAdapter.this}).release();
                    }
                    final MediaPlayer mediaPlayer = MediaPlayer.create(Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$300", new Object[]{MSATuneListAdapter.this}), item.getMusicResId());
                    MSATuneListAdapter.access$202(MSATuneListAdapter.this, mediaPlayer);
                    if (mediaPlayer != null) {
                        mediaPlayer.setLooping(true);
                        mediaPlayer.start();
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$100", new Object[]{MSATuneListAdapter.this}).postDelayed(new Runnable() {
                            public void run() {
                                Ensighten.evaluateEvent(this, "run", null);
                                mediaPlayer.stop();
                                mediaPlayer.release();
                                MSATuneListAdapter.access$202(MSATuneListAdapter.this, null);
                            }
                        }, 5000);
                    }
                }
            });
        }
        if (item.getType() == MSATuneItem.MSA_TUNE_FROM_PHONE) {
            vh.mTuneLine1.setVisibility(0);
            vh.mTuneLine1.setText(item.getInstruction());
            String filename = item.getChoice();
            if (filename.isEmpty()) {
                vh.mTuneLine2.setVisibility(8);
            } else {
                vh.mTuneLine2.setVisibility(0);
                vh.mTuneLine2.setText(filename);
            }
        } else if (item.getType() == MSATuneItem.MSA_TUNE_FROM_APP) {
            vh.mTuneLine1.setVisibility(0);
            vh.mTuneLine1.setText(item.getChoice());
            vh.mTuneLine2.setVisibility(8);
        } else {
            vh.mTuneLine1.setVisibility(0);
            vh.mTuneLine1.setText(item.getInstruction());
            vh.mTuneLine2.setVisibility(8);
        }
        if (item.getType() == MSATuneItem.MSA_TUNE_FROM_PHONE && this.mSelectMusicDisabled) {
            vh.mCheckBox.setEnabled(false);
        } else {
            vh.mCheckBox.setEnabled(true);
        }
        vh.mCheckBox.setOnCheckedChangeListener(null);
        CheckBox checkBox = vh.mCheckBox;
        if (position == this.mPositionChecked) {
            z = true;
        } else {
            z = false;
        }
        checkBox.setChecked(z);
        vh.mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{compoundButton, new Boolean(b)});
                if (b) {
                    MSATuneListAdapter.access$402(MSATuneListAdapter.this, position);
                    if (item.getType() == MSATuneItem.MSA_TUNE_RANDOM) {
                        item.setMusicResId(MSASettings.getRandomTune().getMusicResId());
                    } else if (item.getType() == MSATuneItem.MSA_TUNE_FROM_PHONE) {
                        Intent intent = new Intent("android.intent.action.GET_CONTENT");
                        intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
                        intent.setType("audio/*");
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSATuneListAdapter", "access$300", new Object[]{MSATuneListAdapter.this}).startActivityForResult(intent, 1);
                    }
                    MSASettings.saveAlarmId(MSATuneListAdapter.this.getContext(), item.getType(), item.getTuneId(), item.getChoice());
                }
                MSATuneListAdapter.this.notifyDataSetChanged();
            }
        });
        if (item.getType() == MSATuneItem.MSA_TUNE_FROM_PHONE && this.mSelectMusicDisabled) {
            vh.mBackground.setEnabled(false);
        } else {
            vh.mBackground.setEnabled(true);
        }
        vh.mBackground.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                vh.mCheckBox.setChecked(true);
            }
        });
        Ensighten.getViewReturnValue(root, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return root;
    }

    public void onDestroy() {
        Ensighten.evaluateEvent(this, "onDestroy", null);
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }
}
