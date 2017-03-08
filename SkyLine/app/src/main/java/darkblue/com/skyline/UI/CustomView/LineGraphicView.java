package darkblue.com.skyline.UI.CustomView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import darkblue.com.skyline.R;

/**
 * Created by Alee on 2017/3/8.
 */

public class LineGraphicView extends View {
    /**
     * 鍏?鍏遍儴鍒?
     */
    private static final int CIRCLE_SIZE = 20; //鐐圭殑澶у皬

    private static enum Linestyle {
        Line, Curve
    }

    private Context mContext;
    private Paint mPaint;
    private Resources res;
    private DisplayMetrics dm; //鍙栧緱鎵嬫満灞忓箷澶у皬鐨勫叧閿?绫?
    private int width;// 鎵嬫満灞忓箷鐨勫?藉害
    private Double YTotal;

    /**
     * data
     */
    private Linestyle mStyle = Linestyle.Curve;

    private int canvasHeight;
    private int canvasWidth;
    private int bheight = 0;
    private int blwidh;
    private boolean isMeasure = true;
    /**
     * Y杞存渶澶у€?
     */
    private int maxValue;
    /**
     * Y杞撮棿璺濆€?
     */
    private int averageValue;
    private int marginTop = 0;
    private int marginBottom = 0;

    /**
     * 鏇茬嚎涓婃€荤偣鏁°
     */
    private Point[] mPoints;
    /**
     * 绾靛潗鏍囧€?
     */
    private ArrayList<Double> yRawData;
    /**
     * 妯a鍧愭爣鍊?
     */
    private ArrayList<String> xRawDatas;
    private ArrayList<Integer> xList = new ArrayList<Integer>();// 璁板綍姣忎釜x鐨勫€?
    private int spacingHeight;

    public LineGraphicView(Context context) {
        this(context, null);
    }

    public LineGraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        this.res = mContext.getResources();
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        blwidh = dm.widthPixels;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (isMeasure) {
            this.canvasHeight = getHeight();
            this.canvasWidth = getWidth();
            if (bheight == 0) bheight = (int) (getHeight() / 3);
//                bheight = (int) (canvasHeight - marginBottom);
            blwidh = dip2px(getWidth() / 6); //绗?涓€涓a鐐圭殑x浣嶇疆
            isMeasure = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(res.getColor(R.color.color_f2f2f2));

        drawAllXLine(canvas);
        // 鐢荤洿绾匡紙绾靛悜锛?
//
        // 鐐圭殑鎿嶄綔璁剧疆
        mPoints = getPoints();

        mPaint.setColor(res.getColor(R.color.colorPrimaryDark));
        mPaint.setStrokeWidth(dip2px(4f));//绾跨殑缁勭粏
        mPaint.setStyle(Paint.Style.STROKE); //绾跨殑褰㈢姸(骞虫粦銆佸甫榻?)

        if (mStyle == Linestyle.Curve) {
            drawScrollLine(canvas);
        } else {
            drawLine(canvas);
        }

        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < mPoints.length; i++) {
            canvas.drawCircle(mPoints[i].x, canvasHeight - mPoints[i].y, CIRCLE_SIZE / 2, mPaint);
        }
        drawAllYLine(canvas);
        drawText(canvas);
    }

    /**
     * 鐢绘墍鏈夋í鍚戣〃鏍硷紝鍖呮嫭X杞′
     */
    private void drawAllXLine(Canvas canvas) {
        for (int i = 0; i < spacingHeight+1;i++){
//            canvas.drawLine(blwidh, bheight - (bheight / spacingHeight) * i  marginTop, (canvasWidth - blwidh),
//                    bheight - (bheight / spacingHeight) * i  marginTop, mPaint);// Y鍧愭爣
//            drawText(yRawData.get(i).toString(), mPoints[i].x, bheight - (bheight / spacingHeight) * i  marginTop,
//                    canvas);
        }
    }

    /**
     * 鐢绘墍鏈夌旱鍚戣〃鏍硷紝鍖呮嫭Y杞′
     */
    private void drawAllYLine(Canvas canvas) {
        for (int i = 0; i < yRawData.size(); i++) {
            xList.add(canvasWidth * (i+1) / 6);
//            xList.add(blwidh  (canvasWidth - blwidh) / yRawData.size() * i);
//            canvas.drawLine(blwidh  (canvasWidth - blwidh) / yRawData.size() * i, marginTop, blwidh
//                     (canvasWidth - blwidh) / yRawData.size() * i, bheight  marginTop, mPaint);
            drawText(xRawDatas.get(i), canvasWidth * (i+1) / 6, canvasHeight - canvasHeight / 14,
                    canvas);// X鍧愭爣
        }
    }

    private void drawText(Canvas canvas) {
        for (int i = 0; i < yRawData.size(); i++) {
            xList.add(canvasWidth * (i+1) / 6);
//            xList.add(blwidh  (canvasWidth - blwidh) / yRawData.size() * i);
//            canvas.drawLine(blwidh  (canvasWidth - blwidh) / yRawData.size() * i, marginTop, blwidh
//                     (canvasWidth - blwidh) / yRawData.size() * i, bheight  marginTop, mPaint);
            drawText(yRawData.get(i).toString(), canvasWidth * (i+1) / 6, canvasHeight - mPoints[i].y - dip2px(10),
                    canvas);// X鍧愭爣
        }
    }

    private void drawScrollLine(Canvas canvas) {
        Point startp = new Point();
        Point endp = new Point();
        Path path = new Path();
        path.moveTo(0, canvasHeight - mPoints[0].y);
        path.lineTo(mPoints[0].x, canvasHeight - mPoints[0].y);
        canvas.drawPath(path, mPaint);

        for (int i = 0; i < mPoints.length - 1; i++) {
            startp = mPoints[i];
            endp = mPoints[i+1];
            int wt = (startp.x+endp.x)/2;
            Point p3 = new Point();
            Point p4 = new Point();
            p3.y = startp.y;
            p3.x = wt;
            p4.y = endp.y;
            p4.x = wt;


            Log.i("x", "x_"+mPoints[i].x);
            Log.i("x", "x_"+mPoints[i].y);
            path.moveTo(startp.x, canvasHeight - startp.y);
//            path.moveTo(startp.x, startp.y);
            path.lineTo(endp.x, canvasHeight - endp.y);
//            path.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            canvas.drawPath(path, mPaint);
        }


        path.moveTo(mPoints[mPoints.length - 1].x, canvasHeight - mPoints[mPoints.length - 1].y);
        path.lineTo(width, canvasHeight - mPoints[mPoints.length - 1].y);
        canvas.drawPath(path, mPaint);
    }

    private void drawLine(Canvas canvas) {
        Point startp = new Point();
        Point endp = new Point();
        for (int i = 0; i < mPoints.length - 1; i++) {
            startp = mPoints[i];
            endp = mPoints[i+1];
            canvas.drawLine(startp.x, startp.y, endp.x, endp.y, mPaint);
        }
    }

    private void drawText(String text, int x, int y, Canvas canvas) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(dip2px(12));
        p.setColor(res.getColor(R.color.text_black_color));
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(text, x, y, p);
    }

    private Point[] getPoints() {
        Point[] points = new Point[yRawData.size()];


        for (int i = 0; i < yRawData.size(); i++) {
//            int ph = bheight - (int) (bheight * (yRawData.get(i) / maxValue)); //鍧愭爣鐨刌鍊?

            int y = (int) (canvasHeight * (yRawData.get(i) / YTotal) / 2);
//            points[i] = new Point(xList.get(i), ph  marginTop);

            Log.i("鍧愭爣:", "bili-"+canvasHeight * (yRawData.get(i) / YTotal)+"x-"+canvasWidth * (i+1) / 6+
            "y-"+(y +canvasHeight / 4));
            points[i] = new Point(canvasWidth * (i+1) / 6, y+canvasHeight / 4);
        }


        return points;
    }

    public void setData(ArrayList<Double> yRawData, ArrayList<String> xRawData, int maxValue, int averageValue) {
        this.maxValue = maxValue;
        this.averageValue = averageValue;
        this.mPoints = new Point[yRawData.size()];
        this.xRawDatas = xRawData;
        this.yRawData = yRawData;
        this.spacingHeight = maxValue / averageValue;
        this.YTotal = get(yRawData);

    }


    public double get(ArrayList<Double> yRawData) {
        double num = 0.0;
        for (int i = 0; i < yRawData.size(); i++) {

            num = yRawData.get(i);
        }
        return num;
    }

    public void setTotalvalue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setPjvalue(int averageValue) {
        this.averageValue = averageValue;
    }

    public void setMargint(int marginTop) {
        this.marginTop = marginTop;
    }

    public void setMarginb(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public void setMstyle(Linestyle mStyle) {
        this.mStyle = mStyle;
    }

    public void setBheight(int bheight) {
        this.bheight = bheight;
    }

    /**
     * 鏍规嵁鎵嬫満鐨勫垎杈ㄧ巼浠? dp 鐨勫崟浣? 杞?鎴愪负 px(鍍忕礌)
     */
    private int dip2px(float dpValue) {
        return (int) (dpValue * dm.density+0.5f);
    }
}
