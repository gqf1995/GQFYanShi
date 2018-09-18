/*******************************************************************************
    Copyright (c) 2009-2012, ERENEBEN INFORMATION TECH LTD.
    All rights reserved.

    FILE NAME
        DrawStrokes.java

    DESCRIPTION
        .

      DATE      AUTHOR      REMARKS
 ===============================================================================
    2013-03-14   Jun.Duan     create
 ******************************************************************************/
package com.gqfyanshi.mvp.activity.main;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.ebensz.eink.api.DataUtils;
import com.ebensz.eink.api.PennableLayout;
import com.ebensz.eink.api.StrokeRecognizer;
import com.ebensz.eink.api.StrokeRecognizer.OnResultListener;
import com.ebensz.epen.Strokes;
import com.ebensz.penpanel.PenPanel;
import com.ebensz.penpanel.PenPanel.Pen;
import com.ebensz.recognizer.latest.Result;
import com.ebensz.widget.PenPickerView;
import com.gqfyanshi.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author bingle 演示简单的写字。
 */
public class Scrawl extends Activity {
    private static final int REQUEST_CODE_GET_IMAGE = 100;
    private static final int SHOW_NODE = 10000;
    private static final String FILE_TEST = "/sdcard/scrawl_test_demo.data";
    private PennableLayout mPennable;
    private MenuItem mSelectionItem;
    private MenuItem mEraserItem;
    private StrokeRecognizer mRecognizer;
    private final Matrix mTransform = new Matrix();
    private int mCurrentTransform = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //隐藏键盘
        setContentView(R.layout.scrawl);
        mPennable = (PennableLayout) findViewById(R.id.ink);
        mPennable.setStrokeColor(Color.BLUE);
        mPennable.setStrokeWidth(9.0f);
        mPennable.setSideKeyEnable(true);
        mRecognizer = new StrokeRecognizer(getApplicationContext());
        mRecognizer.setOnResultListener(new OnResultListener() {
            @Override
            public void onComplete(int sessionId, Result result) {
                Toast.makeText(getApplicationContext(),
                        result.getBestCandidate(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel(int sessionId) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.draw_strokes_actions, menu);
        mSelectionItem = menu.findItem(R.id.selection_mode);
        mEraserItem = menu.findItem(R.id.eraser_mode);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isShow = true;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_Pennable:
                isShow = !isShow;
                showPennableLayout(isShow);
                return true;
        case R.id.stroke_color:
        case R.id.stroke_width:
            // mPennable.showPenIconOnTaskBar(true);
            // sendBroadcast(new Intent(FunctionManager.ACTION_PENPANEL_CLICK));
            final PenPickerView panel = new PenPickerView(this, getPackageName());
            panel.onAdded(null, new PenPanel.Callback() {
                @Override
                public void sendPenChanged(Context context, Pen pen) {
                    mPennable.setStrokeColor(pen.getPenColor());
                    mPennable.setStrokeWidth(pen.getPenWidth());
                }
            });
            panel.show(this, null);
            return true;
        case R.id.insert_image:
            requestImage();
            return true;
        case R.id.insert_textbox:
            mPennable.insertTextBox(new RectF(100, 100, 500, 500), "TextBox");
            return true;
        case R.id.export_bitmap:
            exportBitmap();
            return true;
        case R.id.save:
            if (mPennable.isDataModified()) {
                mPennable.save(FILE_TEST);
                Toast.makeText(this, "save ok", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "data does not modified !", Toast.LENGTH_LONG).show();
            }
            return true;
        case R.id.load:
            mPennable.load(FILE_TEST);
            return true;
        case R.id.clear:
            mPennable.clear();
            return true;
        case R.id.side_key:
            toggleSideKey(item);
            return true;
        case R.id.eraser_mode:
            toggleEraserMode(item);
            return true;
        case R.id.selection_mode:
            toggleSelectionMode(item);
            return true;
        case R.id.undo:
            mPennable.undo();
            return true;
        case R.id.redo:
            mPennable.redo();
            return true;
        case R.id.replay:
            replay();
            return true;
        case R.id.share:
            shareBitmap(mPennable.export());
            return true;
        case R.id.pen_setting:
            showPenSettingDialog();
            return true;
        case R.id.recognizer:
            recognize();
            return true;
        case R.id.transform:
            transform();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPennable.showPenIconOnTaskBar(true);
        // 设置笔盘的默认值 ，test
        // PenUtils.setDefault(getPackageName(), 1, new PenPanel.Pen() {
        // @Override
        // public int getPenColor() {
        // return Color.BLUE;
        // }
        //
        // @Override
        // public float getPenWidth() {
        // return 4.0f;
        // }
        //
        // @Override
        // public String getPenName() {
        // return null;
        // }
        // });
        // PenUtils.setCurrentPen(getPackageName(), 1);
        // Log.d("TTT", "c="
        // + Convert.toHex(PenUtils.getCurrentPenColor(getPackageName()) &
        // 0xffffffff));
        // Log.d("TTT", "w=" + PenUtils.getCurrentPenWidth(getPackageName()));
    }

    @Override
    protected void onPause() {
        mPennable.showPenIconOnTaskBar(false);
        super.onPause();
    }

    private final Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_NODE) {
                mPennable.add(msg.obj);
            } else {
                super.handleMessage(msg);
            }
        }
    };

    private void replay() {
        Object[] gn = mPennable.getData();
        mPennable.clear();
        int time = 1000;
        for (Object node : gn) {
            myHandler.sendMessageDelayed(
                    Message.obtain(myHandler, SHOW_NODE, node), time);
            time += 1000;
        }
    }

    private void recognize() {
        Object[] gn = mPennable.getData();
        mRecognizer.clear();
        for (Object obj : gn) {
            Strokes s = DataUtils.getStrokes(obj);
            if (s != null) {
                mRecognizer.addStroke(s);
            }
        }
        mRecognizer.submit();
    }

    private void toggleSideKey(MenuItem item) {
        item.setChecked(!item.isChecked());
        mPennable.setSideKeyEnable(item.isChecked());
    }

    private void toggleEraserMode(MenuItem item) {
        item.setChecked(!item.isChecked());
        mSelectionItem.setChecked(false);
        mPennable.setEraserMode(item.isChecked());
    }

    private void toggleSelectionMode(MenuItem item) {
        item.setChecked(!item.isChecked());
        mEraserItem.setChecked(false);
        mPennable.setSelectionMode(item.isChecked());
    }

    private void showPenSettingDialog() {
    }

    private void transform() {
        mCurrentTransform++;
        mCurrentTransform %= 3;
        if (mCurrentTransform == 0) {
            mTransform.setScale(1.0f, 1.0f);
        } else if (mCurrentTransform == 1) {
            mTransform.setScale(2.0f, 2.0f);
        } else if (mCurrentTransform == 2) {
            mTransform.setScale(0.5f, 0.5f);
        }
      //mPennable.setTransform(mTransform);
//         PopupWindow transform = new PopupWindow(this);
//         LayoutInflater li = LayoutInflater.from(this);
//         View v = li.inflate(R.layout.transform, null);
//         transform.setContentView(v);
//         if (mWindowX < 0) {
//             mWindowX = mPennable.getWidth();
//             mWindowY = mPennable.getHeight();
//         }
//         ((SeekBar) v.findViewById(R.id.transform_scale))
//         .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//         @Override
//         public void onProgressChanged(SeekBar seekBar,
//         int progress,
//         boolean fromUser) {
//         mTransform.setScale(mWindowX * progress, mWindowY
//         * progress);
//         mPennable.setTransform(mTransform);
//         }
//
//         @Override
//         public void onStartTrackingTouch(SeekBar seekBar) {
//         }
//
//         @Override
//         public void onStopTrackingTouch(SeekBar seekBar) {
//         }
//         });
//         ((SeekBar) v.findViewById(R.id.transform_translate))
//         .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//         @Override
//         public void onProgressChanged(SeekBar seekBar,
//         int progress,
//         boolean fromUser) {
//         mTransform.setTranslate(progress, progress);
//         mPennable.setTransform(mTransform);
//         }
//
//         @Override
//         public void onStartTrackingTouch(SeekBar seekBar) {
//         }
//
//         @Override
//         public void onStopTrackingTouch(SeekBar seekBar) {
//         }
//         });
//         ((SeekBar) v.findViewById(R.id.transform_skew))
//         .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//         @Override
//         public void onProgressChanged(SeekBar seekBar,
//         int progress,
//         boolean fromUser) {
//         mTransform.setSkew(progress, progress);
//         mPennable.setTransform(mTransform);
//         }
//
//         @Override
//         public void onStartTrackingTouch(SeekBar seekBar) {
//         }
//
//         @Override
//         public void onStopTrackingTouch(SeekBar seekBar) {
//         }
//         });
//         transform.showAtLocation(mPennable, Gravity.TOP, 0, 0);
    }

    private void exportBitmap() {
        Log.e("ComputeBounds", "bounds:"
                + mPennable.computeBounds(new Rect()).toString());
        Rect rect = mPennable.computeBounds(new Rect());
/*        Bitmap bitmap = mPennable.exportBitmap(rect,rect.width(),rect.height()); //mPennable.computeBounds(new Rect()), 100, 200
        File f = new File("/sdcard/" + "777" + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void shareBitmap(Bitmap bitmap) {
        final String pngName = "DrawStrokesDemo.png";
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), pngName);
        file.getParentFile().mkdirs();
        try {
            // 以PNG格式写入文件
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // 调用系统像册查看图片
        Uri uriFile = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uriFile, "image/*");
        startActivity(intent);
    }

    @Override
    protected void
    onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_GET_IMAGE) {
                Bitmap bmp = getImage(data);
                if (bmp != null) {
                    mPennable.insert(50, 50, bmp);
                }
            }
        }
    }

    /**
     * 获取图像。
     *
     *            context。
     *            intent data。
     * @return 图片。
     */
    private void requestImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CODE_GET_IMAGE);
    }

    /**
     * 获取图像。
     *
     *            context。
     * @param data
     *            intent data。
     * @return 图片。
     */
    private Bitmap getImage(Intent data) {
        Uri uri = data.getData();
        ContentResolver cr = getContentResolver();
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(cr
                    .openInputStream(uri));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void showPennableLayout(boolean b){
/*        if(b){
            mPennable.setVisibility(View.VISIBLE);
        } else {
            mPennable.setVisibility(View.GONE);
        }*/
        mPennable.setEnabled(b);
    }
}
