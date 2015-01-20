package com.example.over1;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;

/**
 * Android常用的物理按键及其触发事件
 * KEYCODE_POWER 电源键
 * KEYCODE_MENU 菜单键
 * KEYCODE_BACK 后退键
 * KEYCODE_HOME Home键
 * KEYCODE_CAMERA 相机键
 * KEYCODE_SEARCH 查找键
 * KEYCODE_VOLUME_UP 音量键+
 * KEYCODE_VOLUME_DOWN 音量键-
 * KEYCODE_VOLUME_MUTE 静音
 * 方向键
 * KEYCODE_DPAD_CENTER
 * KEYCODE_DPAD_UP
 * KEYCODE_DPAD_DOWN
 * KEYCODE_DPAD_LEFT
 * KEYCODE_DPAD_RIGHT
 * 键盘键
 * 数字0~9 字母A~Z
 * KEYCODE_0 ~ KEYCODE_9
 * KEYCODE_A ~ KEYCODE_Z
 * 提供的回调方法有
 * onKeyUp()、OnKeyDown()、onKeyLongPress()
 *
 * @author Administrator
 *
 */
public class Test extends Activity {

    private Button btnClose = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new closelistener());
    }

    class closelistener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            finish();
        }
    }

    /**
     * 重写onKeyDown方法可以拦截系统默认的处理
     */
    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Toast.makeText(this, "后退键", Toast.LENGTH_SHORT).show();
//            return true;
//        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//            Toast.makeText(this, "声音+", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            Toast.makeText(this, "声音-", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_MUTE) {
//            Toast.makeText(this, "静音", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
//            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Integer i = 0 ;
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            i--;
           Log.i("down------->", i.toString());
           Toast.makeText(this, "声音-", Toast.LENGTH_SHORT).show();

            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            i++;
            Log.i("up------->", i.toString());
            Toast.makeText(this, "声音+", Toast.LENGTH_SHORT).show();

            return true;
        }
        else return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){

            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            return true;
        }
        else return super.onKeyUp(keyCode, event);
    }
}