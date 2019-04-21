package com.mcdonalds.app.msa;

import android.net.Uri;
import com.ensighten.Ensighten;

public class MSATuneItem {
    public static int MSA_TUNE_FROM_APP = 1;
    public static int MSA_TUNE_FROM_PHONE = 0;
    public static int MSA_TUNE_RANDOM = 2;
    private String mChoice;
    private String mInstruction;
    private int mMusicResId;
    private Uri mMusicUri;
    private int mType;

    public MSATuneItem(String instruction, String choice, Uri musicUri, int musicResId, int type) {
        this.mInstruction = instruction;
        this.mChoice = choice;
        this.mMusicUri = musicUri;
        this.mMusicResId = musicResId;
        this.mType = type;
    }

    public String getChoice() {
        Ensighten.evaluateEvent(this, "getChoice", null);
        return this.mChoice;
    }

    public void setChoice(String choice) {
        Ensighten.evaluateEvent(this, "setChoice", new Object[]{choice});
        this.mChoice = choice;
    }

    public void setMusicUri(Uri musicUri) {
        Ensighten.evaluateEvent(this, "setMusicUri", new Object[]{musicUri});
        this.mMusicUri = musicUri;
    }

    public int getMusicResId() {
        Ensighten.evaluateEvent(this, "getMusicResId", null);
        return this.mMusicResId;
    }

    public void setMusicResId(int musicResId) {
        Ensighten.evaluateEvent(this, "setMusicResId", new Object[]{new Integer(musicResId)});
        this.mMusicResId = musicResId;
    }

    public int getType() {
        Ensighten.evaluateEvent(this, "getType", null);
        return this.mType;
    }

    public String getTuneId() {
        Ensighten.evaluateEvent(this, "getTuneId", null);
        if (this.mType == MSA_TUNE_FROM_PHONE && this.mMusicUri != null) {
            return this.mMusicUri.toString();
        }
        if (this.mType == MSA_TUNE_FROM_APP || this.mType == MSA_TUNE_RANDOM) {
            return String.valueOf(this.mMusicResId);
        }
        return null;
    }

    public String getInstruction() {
        Ensighten.evaluateEvent(this, "getInstruction", null);
        return this.mInstruction;
    }
}
