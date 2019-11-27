package com.example.annanops.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.annaops.R;
import com.example.annanops.util.OrderInfoUtil2_0;
import com.example.annanops.util.PayResult;

import java.util.Map;

public class Alipay_test extends AppCompatActivity implements View.OnClickListener {
    private Button btnPay;
    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCh/bW8jvRKNIa+F41Sb6TuFXmwOXkhzmqFVwDWTmGBddjZqaIJedqVhMiLRTlm0HF2hKqRpw5gI9OmVT+cIOU/s+tCDkTyC1FNbuTAWXrHNb9Ow+0+3xQFgBDGKXZRGPW/RujeXHN7DEJXEi0OOAQqih3P/xJf9C68aGuFtXiEkca+HTu1e+GmNQXTnQdtfLH0Gf/atDpgCxvefvvk/E5vRaSpcNWUC7U/GW+CTpEo1NHbFp3DjKel2GlGpgM768Qx2UyAeT50C212V060JSFYzLTchrx475UljUTQLBNn3MKHCVH0wmIgKIkHR1sc8VX6Akx+IBGRbYO0CSkXablfAgMBAAECggEAIdh9qUkBF6QD5yHKoEe9YRVRmJVZjsuvC+8SuLQlwUPbLBc6SNqR5h2ZPSgYhzfUDRxI+ye5ZDL4HM/A+6EP5RB1fha3Jw9gTFp4JwxLD+ZV82ISZsl7SG7qkkzFcMpTRkTNkPAmHLaCAelFSqw/ipfTBMtZjnzZavoyRPyWkrQBFvpnswsZZQNJdRXAJv80QttsvwU+KuLbjWPo/l3wOOL8WtI0d6BX2DoPyXW5GNnVfOep4IvZ5+eiT9ZFm556ELSUc0rJdg9k8PZ33zBQe0cpnAUXwfKseeC2xoHcZmv9RXuv7TfyrBeGRMCSZrxRRoqnVCI5yFOgpStQAIVEeQKBgQDhUpTJm6Eias0nHdDKex26PJoH1DqjcG7ADYdLw39JKbYxwNgkWzxpjlovnuoFZkauYCLdKI+Nsy9c6i6M5doPggpYG4Ck9sHYs2oB8oargAx1jFKoIZXti9nxCINOyGJLhg1kuEn7rPomqivHpsP//dwr9Vubz9tEesK2w+z0iwKBgQC4C8Oyx5jkglfWxraoqEX3ePzFG86Tn9rBs//QPqxwxnHLAZnPAd3elyeIqSo0elD/U+Tf6rGGYy34dc6c+3MY94zmYAMmBxiUZkg9HUyUVAl2M04zlLhXJH5jN+o5L22IfoH3k6HGmeCo11ydjTJdZB3w2HTuG87D8ry2WBmk/QKBgFZFrUxoN1EbPYO9qcTtUNuO+pcGzpDnqrAEPgpnRyQ3QVhZcyiwejgcVjGnkgkROPzpOFAfiJ+DvR3BWKOAGncXnmhP13jSNjSHtY4bUy71L1AOs1NG0MYMZx0f2k7e8KvkHtqyqTkg6QLG405lbuRkSdnW7wWAl37n1gfuipqzAoGAcW9G0gT1djnj+8uEP1VfslTOrooh+5j2EpdnnylRzTxyXGYDL7R91DijtDZXffq5u3UwxtX3TtYIOnAZxWWxnfzNyhv5NNIA005rTcemMHHPVxvuIhqSt5sVjv+ijFZwE0WtwzMp7adf8ZCzU6P4G9meHpi7ERrwiJMuvtB0Fd0CgYEAkLoUdmVvYI/+E9USf1ngfRlOH3FJRdS51OVDYnxmFPiA9nYMJ44CwBPi0aI29zxwz+wDGGHfdBYdyN95gAO4BTnNMWHxZ5pDOHIHaCJ/XF7WOl1Msxp5EXKWXmEo15KOrCD0F8zv1Ua1B46o0nLTd3dIcGge4uCbTZcjJvXka6w=";
   // private String RSA_PRIVATE ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDAA9w1aqWpLvWRgOUifIuXKZm9bg5qoFyB5EBVH4oOW6OLeyqN2KpJcrZ00k6Bsv0X0Kz7xjNxTHKWKpZdN9OyXEjMSa88I1ltFvGxKz9xou8yLanvOKt0OOsIBBIGNZhnQBog4JcoOTJTQb3Omre1ImnV1b8bkp7OAXK67hmkmqyA7/LPzVEWkLOVWsJInzQDrnScDFSBzD4P4PXs6OIo5zQ88sr4dIuriEe0MF/ri8Xyw5AyIo1V5Hf4SdFjffhxEfHq+Js9y5/ThrSfCU4Tfg9pky4OhjofgyeHMZ1n26d5veu0PzRCCohXKhqhpTeyEEix038NtwDGJHSqgH0jAgMBAAECggEAdEQiG8oWw0mm8lLM68PkNkS/Tb+NRS0StqgB+EkfrC/66GNM0hUGj3DBYB1T4h8CN2Bozg8maQ00OQ9SeuFp8w8EGYbob46sAG0rLe8U/Hb/RS/3PN3gm4A3eGQWkeH0BVkAOTYk8PspKNae2rI+WJSc5xkFHen69JxV3zB7IHpnNxKaWeU8YDUU5XDfouCBoOGG//tvhB+uuEzXWIaBwjOXltJySBI4td6pzhEuOHTCqIURGjkM9Rdh2QDU27+6kK3FX94yOboCHGEkvK0l35j05biowas77yCLpnwXB9eBusRdmUUCdT+7xDlJwpvg41AdVCwfC8XBic8IXrFb2QKBgQDisFJ0CjlHWluRixX8qNqjb2ezAgmD4ymMI41e+6qEnRlkhQ+lneAZTsiF+snWbhRECND8OX2bYVly6FWasiat2RyDOCR3rIQChuzQWe1y8XIaIGuPWJfD8qSR95Wq1i5lXKojEYUojf7/85OuEOohb95xODHLxlEeSRdhSSzwXQKBgQDY18vm4HgfTGZwMS7tjuQJWsu9kqQYpjorHtxrlqN1i9Ivxdw11UbrI8Hk52j5Rw08wy89iYd2W3L/BYZJkKLtxaz5GMxEmQaqYtR/2LEjT1C6+4ml7w5nWSdB8PiiTFLf0o2i/T64qAMw/UxW5zQ6u2RX6kjkiK9FTZy9uVJLfwKBgQCS/N/KDGMNqEJdwkD0foyekvs7sGN5GE/1ZYwifa8YpEE1063Mpu0Lm/EcJvy/RMtxPgI8ug5/NQVqI8p2L3QzH+eEEmBbFQrYFKLziowUDwj8de8omLp3vRsG5vIuPwkAgrBzYsXkAQbK8ibdJGycV8x1v4lSZ3pr19sIIwm3pQKBgQCGHlsQbu/9M8z8QX9sEYA7xPvVQhja4h2CRDFDHkX100hqoe+FIGgf0dsAUJ0d6XNnjLXwUtsFOqTS1UDsyVfsxuIoo8zb9OYphr02YfmWBF3DeQb3Kff3ti3Q3FF8WMa9h0ZxdD9SCSEIMPMBoTktxm/W7KKvL1dw/wMeWbxr0wKBgGR9ljl7DlrfHsnLiEaJuaM8/ZQn0Q9ror5Bs0C1vkmcsg6/hMgwcHsyVhfh6HGlbiZF8/MRRTCXRFNufD3UEyh3W52GuAy6XXiRAqrEiJdC49Bt4TRCc1EXyTTnDD5S5T2bt+rEn1Ig3QcFs85/vBESYYyfX4Xf/nPqOVrA/0IR";
    public static final String APPID = "2016101200665248";
    //商户应用客户端通过当前调用支付的Activity的Handler对象，通过它的回调函数获取支付结果
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                                      * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                                      * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                                      * docType=1) 建议商户依赖异步通知
                                      */
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功\
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(Alipay_test.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(Alipay_test.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Alipay_test.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alipay_test);
        initView();
    }
    private void initView() {
        btnPay = (Button) findViewById(R.id.alipay_button);
       btnPay.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alipay_button:
                //秘钥验证的类型 true:RSA2 false:RSA
                boolean rsa = false;
                //构造支付订单参数列表
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
                //构造支付订单参数信息
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                //对支付参数信息进行签名
                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
                //订单信息
                final String orderInfo = orderParam + "&" + sign;
                //异步处理
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        //新建任务
                        PayTask alipay = new PayTask(Alipay_test.this);
                        //获取支付结果
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
            case R.id.wxpay_button:
                //WX_Pay pay = new WX_Pay(this);
               //pay.pay(str1,str2,str3);
                break;

        }
    }
}

