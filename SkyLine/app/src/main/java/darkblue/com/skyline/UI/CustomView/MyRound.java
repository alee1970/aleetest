package darkblue.com.skyline.UI.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import darkblue.com.skyline.R;

/**
 * Created by 欢大哥 on 2017/3/8.
 */

public class MyRound extends View {
    private int roundwidth;//环形的宽度
    private Paint paint;//定义画笔
    private int roundradius;//圆环的半径
    private RectF rectf;
    private float starangle =-90;//开始角度
    private float currentangle;//当前角度
    private int currentnum;//当前的值
    private int allnum;//所有的值

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int centerX;//圆心点X
    private int centerY;//圆心点Y



    private Context mContext;
    private String remind_text;//提示文字，表示这个是什么图形

    public void setAllnum(int allnum) {
        this.allnum = allnum;
    }

    public void setCurrentnum(int currentnum) {
        this.currentnum = currentnum;
    }

    public void setRoundwidth(int roundwidth) {
        this.roundwidth = roundwidth;
    }

    private int MARGINTOP =50;//设置距离上边距的距离
    private float firststart;//第一个开始的角度

    private float firstend;//第一个结束的角度

    private Rect currentround = new Rect();


    public MyRound(Context context) {
        super(context);
        this.mContext = context;
    }
    public MyRound(Context context,int allnum,int currentnum) {
        super(context);
        this.mContext = context;
        this.allnum = allnum;
        this.currentnum = currentnum;
    }

    public MyRound(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setMyRoundAttributes(attrs);

    }


    public MyRound(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setMyRoundAttributes(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        //获取圆的半径
        roundradius = getWidth() / 8 ;

        paint.setAntiAlias(true);
        //绘制圆圈内的文字
        paint.setTextSize(30);
        paint.getTextBounds("总消费",0,3, currentround);
//        canvas.drawText(currentnum + "/" + allnum, getWidth() /2 -20 - roundwidth, MARGINTOP + roundradius, paint);

        paint.getTextBounds("已使用统计",0,4, currentround);
//        canvas.drawText(remind_text, getWidth() / 2 - currentround.width() /2, MARGINTOP + roundradius + currentround.height(), paint);
        //设置画圆是的风格和圆的宽度
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(roundwidth);

        rectf = new RectF(getWidth() / 2 - roundradius, getHeight()/2-roundradius, getWidth() / 2 + roundradius, getHeight()/2+ roundradius);

//        paint.setColor(getResources().getColor(R.color.colorAccent));
        currentangle = (float) currentnum / allnum *360;
        //用来添加后面的圆弧
        firststart = starangle;
        firstend = currentangle;
//        canvas.drawArc(rectf, starangle, currentangle , false, paint);

        centerX = getWidth()/2;
        centerY = getHeight()/2;

        starangle += currentangle;
        paint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        currentangle = (float) (allnum - currentnum) / allnum *360;
        Log.d("MyRound", "currentangle1:" + currentangle);
        canvas.drawArc(rectf, starangle, currentangle, false, paint);

        Paint paintLine = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paintLine.setColor(getResources().getColor(R.color.colorAccent));
        paintLine.setStrokeWidth(6.0f);


        paint.setColor(getResources().getColor(R.color.myround_color));
        currentangle = (float) (currentnum) / allnum *360;
        canvas.drawArc(rectf, firststart-2, currentangle+4,false, paint);
        Log.d("MyRound", "currentangle2:" + currentangle);
        x1 = (int)(centerX - (10+roundradius+roundwidth/2) * Math.cos(currentangle/2*3.14/180));
        y1 = (int)(centerY + (10+roundradius+roundwidth/2)  * Math.sin(currentangle/2*3.14/180));

        canvas.drawLine(x1,y1,x1+2,y1+2,paintLine);
//        canvas.drawLine(x1-30,y1+60,x1-100,y1+60,paintLine);
        x2 = (int)(centerX + (roundradius+roundwidth/2)  * Math.cos(currentangle/2*3.14/180));
        y2 = (int)(centerY - (roundradius+roundwidth/2)  * Math.sin(currentangle/2*3.14/180));
        paintLine.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawLine(x2,y2,x2-30,y2+60,paintLine);
        Log.d("MyRound", "-centerX"+centerX+"-centerY"+centerY+"-x1:" + x1+"-y1:" + y1+"-x2:" + x2+"-y2:" + y2);


    }
    //在这里配置可以在xml中直接引用
    private void setMyRoundAttributes(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs,
                R.styleable.myround);
        currentnum = a.getInteger(R.styleable.myround_currentnum, 1);//占有的值
        allnum = a.getInt(R.styleable.myround_allnum, 10); //总值
        remind_text = a.getString(R.styleable.myround_remind);
        roundwidth = a.getInteger(R.styleable.myround_roundwidth, 60); //圆的宽度
        a.recycle();
    }
}
