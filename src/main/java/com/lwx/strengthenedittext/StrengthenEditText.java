package com.lwx.strengthenedittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by 36249 on 2017/3/16.
 */

public class StrengthenEditText extends RelativeLayout {

    private EditText editText;
    private List<ImageView> imageViewList;
    private Context context;
    private AttributeSet attr;
    private int componentPadding;
    private int componentMargin;

    private int editTextPadding;
    private int editTextMargin;
    public StrengthenEditText(Context context){

        super(context);
        this.context = context;

    }

    public StrengthenEditText(Context context, AttributeSet attr){

        super(context,attr);
        this.context = context;
        this.attr = attr;

    }

    public StrengthenEditText(Context context, AttributeSet attr, int defStyleAttr){

        super(context,attr,defStyleAttr);
        this.context = context;
        this.attr = attr;

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StrengthenEditText(Context context, AttributeSet attr, int defStyleAttr, int defStyleRes){

        super(context,attr,defStyleAttr,defStyleRes);
        this.context = context;
        this.attr = attr;

    }


    public void setComponent(List<ImageView> imageViewList){

        editText = new EditText(context);
        this.imageViewList = imageViewList;
        LayoutParams lp1 =
                new LayoutParams(
                        LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        editText.setId(R.id.edittext);
        editText.setLayoutParams(lp1);
        addView(editText);

        if(imageViewList == null){

            return;
        }

        int length = imageViewList.size();
        switch(length){

            case 3:
                imageViewList.get(2).setId(R.id.imageview3);

            case 2:
                imageViewList.get(1).setId(R.id.imageview2);

            case 1:
                imageViewList.get(0).setId(R.id.imageview1);
                break;

            case 0:
                return;
        }

        LayoutParams lp3 = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
        );
        lp3.addRule(RelativeLayout.LEFT_OF,R.id.imageview1);
        editText.setLayoutParams(lp3);
        LayoutParams lp2 = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT
        );
        lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


        imageViewList.get(length-1).setPadding(componentPadding,0,componentPadding,0);
        lp2.setMargins(componentMargin,0,componentMargin,0);

        imageViewList.get(length-1).setLayoutParams(lp2);

        for(int i = length - 2; i > -1; --i){

            LayoutParams lp = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT
            );

            lp.addRule(RelativeLayout.LEFT_OF,imageViewList.get(i+1).getId());



            lp.setMargins(componentMargin,0,componentMargin,0);
            imageViewList.get(i).setPadding(componentPadding,0,componentPadding,0);


            imageViewList.get(i).setLayoutParams(lp);

        }
        for(int i = 0 ; i < length; ++i){

            addView(imageViewList.get(i));
        }

    }

    public void setEditTextOnClickListener(OnClickListener listener){

        editText.setOnClickListener(listener);

    }

    public void setListOnClickListener(List<OnClickListener> listeners){

        if(imageViewList == null){

            return;
        }
        for(int i = 0; i < imageViewList.size(); ++i){

            imageViewList.get(i).setOnClickListener(listeners.get(i));
        }


    }

    public void setHint(String str){

        editText.setHint(str);
        return ;
    }

    public void setDefaultTheme(){

        setBackgroundColor(Color.parseColor("#ffffff"));
        editText.setBackgroundColor(Color.parseColor("#ffffff"));
        try{

            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            field.set(editText,R.drawable.cursor_color);
        }
        catch (NoSuchFieldException e){

            e.printStackTrace();
        }
        catch (IllegalAccessException e){

            e.printStackTrace();
        }
        return ;
    }

    public void setComponentItemVisibility(int index,int visibility){


        imageViewList.get(index).setVisibility(visibility);
    }
    public void setComponentItemOnClickListener(int index,OnClickListener listener){

        imageViewList.get(index).setOnClickListener(listener);
    }

    public void setComponentItemBitmap(int index, Bitmap bitmap){

        imageViewList.get(index).setImageBitmap(bitmap);
    }

    public String getText(){

        return editText.getText().toString();
    }

    public void setComponentPadding(int padding){

        this.componentPadding = padding;
        if(imageViewList == null){

            return;
        }

        int length = imageViewList.size();
        if(length == 0){

            return;
        }

        for(int i = length - 1; i > -1 ; --i){


            imageViewList.get(i).setPadding(padding,0,padding,0);

        }
    }

    public void setComponentMargin(int margin){

        this.componentMargin = margin;

        if(imageViewList == null){

            return;
        }
        int length = imageViewList.size();
        if(length == 0 || length == 1){

            return;
        }

        for(int i = length - 1; i > -1 ;--i){

            LayoutParams lp1 = (LayoutParams)imageViewList.get(i)
                    .getLayoutParams();


            lp1.setMargins(margin,0,margin,0);

            imageViewList.get(i).setLayoutParams(lp1);
        }

    }

    public void setText(String str){

        editText.setText(str);
    }

    public void setEditTextPaddingLeft(int padding){
        editTextPadding = padding;
        int r = editText.getPaddingRight();
        int t = editText.getPaddingTop();
        int b = editText.getPaddingBottom();
        editText.setPadding(padding,t,r,b);
    }

    public void setEditTextMaginLeft(int margin){

        editTextMargin = margin;
        LayoutParams lp = (LayoutParams)editText.getLayoutParams();
        int r = lp.rightMargin;
        int t = lp.topMargin;
        int b = lp.bottomMargin;
        lp.setMargins(margin,r,t,b);
    }

    public void setEditTextInputType(int type){

        editText.setInputType(type);
    }
}
