package com.example.test_nga.data;

public class HotKeyData {
    private String hotkeyName="";
    private String hotkeycmd="";

    public String getHotkeyName() {
        return hotkeyName;
    }

    public void setHotkeyName(String hotkeyName) {
        this.hotkeyName = hotkeyName;
    }

    public String getHotkeycmd() {
        return hotkeycmd;
    }

    public void setHotkeycmd(String hotkeycmd) {
        this.hotkeycmd = hotkeycmd;
    }

    public HotKeyData(String hotkeyName, String hotkeycmd) {
        this.hotkeyName = hotkeyName;
        this.hotkeycmd = hotkeycmd;
    }
}
