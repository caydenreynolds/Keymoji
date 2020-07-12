package com.example.keymoji;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.HashMap;

public class KeymojiService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private HashMap<Integer, String> emojiMap;
    private KeyboardView keyboardView;

    public KeymojiService()
    {
        super();
        emojiMap = CreateKeyMap();
    }

    @Override
    public View onCreateInputView() {
        // get the KeyboardView and add our Keyboard layout to it
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.keyboard_layout);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
                break;
            case -1:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                ic.commitText(emojiMap.get(primaryCode), 1);
        }
    }

    @Override
    public void onPress(int primaryCode) { }

    @Override
    public void onRelease(int primaryCode) { }

    @Override
    public void onText(CharSequence text) { }

    @Override
    public void swipeLeft() { }

    @Override
    public void swipeRight() { }

    @Override
    public void swipeDown() { }

    @Override
    public void swipeUp() { }

    public static HashMap<Integer, String> CreateKeyMap()
    {
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        // Reserved
        map.put(-1, "RESERVED FOR ENTER KEY");
        map.put(-5, "RESERVED FOR DELETE KEY");

        // Numbers
        map.put(0, "0️⃣");
        map.put(1, "1️⃣");
        map.put(2, "2️⃣");
        map.put(3, "3️⃣");
        map.put(4, "4️⃣");
        map.put(5, "5️⃣");
        map.put(6, "6️⃣");
        map.put(7, "7️⃣");
        map.put(8, "🎱");
        map.put(9, "9️⃣");

        // Letters
        map.put(10, "\uD83C\uDD70️"); // a
        map.put(11, "\uD83C\uDD71️"); // b
        map.put(12, "\uD83E\uDDF2"); // c
        map.put(13, "\uD83D\uDD03"); // d
        map.put(14, "\uD83D\uDCE7"); // e
        map.put(15, "\uD83E\uDD40"); // f
        map.put(16, "\uD83C\uDF64"); // g
        map.put(17, "♓"); // h
        map.put(18, "ℹ️"); // i
        map.put(19, "\uD83D\uDDFE"); // j
        map.put(20, "\uD83D\uDC4C"); // k
        map.put(21, "\uD83D\uDCAA"); // l
        map.put(22, "♏"); // m
        map.put(23, "\uD83C\uDFB5"); // n
        map.put(24, "⭕️"); // o
        map.put(25, "\uD83C\uDD7F️"); // p
        map.put(26, "♌"); // q
        map.put(27, "®️"); // r
        map.put(28, "\uD83D\uDCB2"); // s
        map.put(29, "✝️"); // t
        map.put(30, "⛎"); // u
        map.put(31, "✌️"); // v
        map.put(32, "\uD83D\uDD96"); // w
        map.put(33, "⚔️"); // x
        map.put(34, "\uD83E\uDD9E"); // y
        map.put(35, "⚡"); // z

        // Special
        map.put(36, "  ");
        map.put(37, "'");
        map.put(38, ".");
        map.put(39, "❔");
        map.put(40, "❗");
        map.put(41, ",");

        return map;
    }
}