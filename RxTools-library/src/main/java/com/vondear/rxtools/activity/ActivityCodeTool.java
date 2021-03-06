package com.vondear.rxtools.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vondear.rxtools.R;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxBarUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.RxUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

public class ActivityCodeTool extends ActivityBase {

    RxTitle mRxTitle;
    EditText mEtQrCode;
    ImageView mIvCreateQrCode;
    ImageView mIvQrCode;
    LinearLayout mActivityCodeTool;
    LinearLayout mLlCode;
    LinearLayout mLlQrRoot;

    EditText mEtBarCode;
    ImageView mIvCreateBarCode;
    ImageView mIvBarCode;
    LinearLayout mLlBarCode;
    LinearLayout mLlBarRoot;
    LinearLayout mLlScaner;
    LinearLayout mLlQr;
    LinearLayout mLlBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBarUtils.noTitle(this);
        RxBarUtils.setTransparentStatusBar(this);
        setContentView(R.layout.activity_code_tool);
        initView();
        initEvent();
    }


    private void initView() {
        mRxTitle = (RxTitle) findViewById(R.id.rx_title);
        mEtQrCode = (EditText) findViewById(R.id.et_qr_code);
        mIvCreateQrCode = (ImageView) findViewById(R.id.iv_create_qr_code);
        mIvQrCode = (ImageView) findViewById(R.id.iv_qr_code);
        mActivityCodeTool = (LinearLayout) findViewById(R.id.activity_code_tool);
        mLlCode = (LinearLayout) findViewById(R.id.ll_code);
        mLlQrRoot =  (LinearLayout) findViewById(R.id.ll_qr_root);

        mEtBarCode = (EditText) findViewById(R.id.et_bar_code);
        mIvCreateBarCode = (ImageView) findViewById(R.id.iv_create_bar_code);
        mIvBarCode = (ImageView) findViewById(R.id.iv_bar_code);
        mLlBarCode =(LinearLayout) findViewById(R.id.ll_bar_code);
        mLlBarRoot = (LinearLayout) findViewById(R.id.ll_bar_root);
        mLlScaner = (LinearLayout) findViewById(R.id.ll_scaner);
        mLlQr = (LinearLayout) findViewById(R.id.ll_qr);
        mLlBar = (LinearLayout) findViewById(R.id.ll_bar);
    }

    private void initEvent() {
        mRxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIvCreateQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEtQrCode.getText().toString();
                if (RxDataUtils.isNullString(str)) {
                    RxToast.error(mContext, "二维码文字内容不能为空！", Toast.LENGTH_SHORT, true).show();
                } else {
                    mLlCode.setVisibility(View.VISIBLE);
                    RxUtils.createQRImage(str, 800, 800, mIvQrCode);
                }
            }
        });

        mIvCreateBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = mEtBarCode.getText().toString();
                if (RxDataUtils.isNullString(str1)) {
                    RxToast.error(mContext, "条形码文字内容不能为空！", Toast.LENGTH_SHORT, true).show();
                } else {
                    mLlBarCode.setVisibility(View.VISIBLE);
                    mIvBarCode.setImageBitmap(RxUtils.drawLinecode(mContext, str1, 1000, 300));
                }
            }
        });

        mLlScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityUtils.skipActivity(mContext, ActivityScanerCode.class);
            }
        });

        mLlQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlQrRoot.setVisibility(View.VISIBLE);
                mLlBarRoot.setVisibility(View.GONE);
            }
        });

        mLlBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlBarRoot.setVisibility(View.VISIBLE);
                mLlQrRoot.setVisibility(View.GONE);
            }
        });
    }
}
