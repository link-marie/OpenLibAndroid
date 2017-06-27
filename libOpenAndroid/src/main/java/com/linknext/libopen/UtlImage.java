package com.linknext.libopen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ImageView utility
 */
public class UtlImage {

    /**
     * Retrieve bitmap from web
     *
     * @param uri
     * @return
     */
    static public Bitmap getBitmap( String uri ) {

        Bitmap bmp = null;

        try {
            URL url = new URL( uri );
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput( true );
            connection.connect();
            InputStream input = connection.getInputStream();
            bmp = BitmapFactory.decodeStream( input );
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
        catch( Exception e ) {
            e.printStackTrace();
            bmp = null;
        }
        return bmp;
    }

    /**
     * Bmp -> Drawable
     *
     * @param res
     * @param bmp
     * @return
     */
    static public Drawable toDrawable( Resources res, Bitmap bmp ) {
        if( bmp == null ) {
            return null;
        }
        Drawable drawable = new BitmapDrawable( res, bmp );
        return drawable;
    }

    /**
     * Drawable -> Bitmap
     *
     * @param drawable
     * @return
     */
    static public Bitmap toBitmap( Drawable drawable ) {
        if( drawable == null ) {
            return null;
        }
        Bitmap bitmap = ( (BitmapDrawable)drawable ).getBitmap();
        return bitmap;
    }

    /**
     * Get screen size
     *
     * @param ctx
     * @return Configuration.SCREENLAYOUT_SIZE_LARGE e.t.c.
     */
    static public int getScreenSize( Context ctx ) {
        Resources res = ctx.getResources();
        int screenLayout = res.getConfiguration().screenLayout;
        int screenSize = screenLayout&Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenSize;
    }

    static public kScreenType getScreenType( Context ctx ) {
        int screenSize = getScreenSize( ctx );
        kScreenType lType = kScreenType.getEnum( screenSize );
        return lType;
    }

    /**
     * is Large screen?
     *
     * @param ctx
     * @return
     */
    static public boolean isScreenLarge( Context ctx ) {
        int lSize = getScreenSize( ctx );
        if( lSize >= Configuration.SCREENLAYOUT_SIZE_LARGE ) {
            return true;
        }
        return false;
    }

    /**
     * Convert integer color code to (ARGB)
     *
     * @param c
     * @return
     */
    static public int[] getColorCode( int c ) {

        int lNum = 4;
        int[] lCols = new int[lNum];

        int lShift = 8;
        int lMask = 0x000000ff;
        for( int i = 0; i < lNum; i++ ) {

            int lVal = c&lMask;
            int lIdx = lNum - i - 1;
            lCols[lIdx] = lVal;
            c = c>>( lShift );
        }
        return lCols;
    }

    static public String getColorString( int c ) {

        int[] lCols = getColorCode( c );
        String lLine = lCols[0] + ", " + lCols[1] + ", " + lCols[2] + ", " + lCols[3];
        return lLine;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    static public Drawable getDrawable( Resources res, int resId ) {
        Drawable drawable;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Resources.Theme theme = null;
            drawable = res.getDrawable( resId, theme );
        }
        else {
            drawable = res.getDrawable( resId );
        }
        return drawable;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    static public void setBackground( View v, Drawable drawable ) {
        if( v == null ) {
            return;
        }
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ) {
            v.setBackground( drawable );
        }
        else {
            v.setBackgroundDrawable( drawable );
        }
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    static public Drawable getDrawableForDensity( Resources res, int resId, int dpi ) {
        Drawable drawable;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Resources.Theme theme = null;
            drawable = res.getDrawableForDensity( resId, dpi, theme );
        }
        else {
            drawable = res.getDrawableForDensity( resId, dpi );
        }
        return drawable;
    }

    /**
     * http://stackoverflow.com/questions/11932805/cropping-circular-area-from-bitmap-in-android
     *
     * @param bitmap
     * @return
     */
    static public Bitmap getCircleBitmap( Bitmap bitmap ) {
        Bitmap output = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888 );
        Canvas canvas = new Canvas( output );

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect( 0, 0, bitmap.getWidth(), bitmap.getHeight() );

        paint.setAntiAlias( true );
        canvas.drawARGB( 0, 0, 0, 0 );
        paint.setColor( color );
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle( bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint );
        paint.setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) );
        canvas.drawBitmap( bitmap, rect, rect, paint );
        // Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        // return _bmp;
        return output;
    }

    static public Bitmap resize( Bitmap bmp, int width, int height ) {
        return Bitmap.createScaledBitmap( bmp, width, height, false );
    }

    public enum kScreenType {
        Unknown( Configuration.SCREENLAYOUT_SIZE_UNDEFINED ),
        Small( Configuration.SCREENLAYOUT_SIZE_SMALL ),
        Normal( Configuration.SCREENLAYOUT_SIZE_NORMAL ),
        Large( Configuration.SCREENLAYOUT_SIZE_LARGE ),
        XLarge( Configuration.SCREENLAYOUT_SIZE_XLARGE ),;

        private int sizeType;

        kScreenType( int sizeType ) {
            this.sizeType = sizeType;
        }

        static public kScreenType getEnum( int type ) {
            for( kScreenType e : values() ) {
                if( e.getSizeType() == type ) {
                    return e;
                }
            }
            return kScreenType.Unknown;
        }

        private int getSizeType() {
            return sizeType;
        }
    }

    public enum kDpiType {
        Ldpi( 120 ),
        Mdpi( 160 ),
        TvDpi( 213 ),
        Hdpi( 240 ),
        Xhdpi( 320 ),
        Xxdpi( 480 ),
        Xxxdpi( 1000 );

        private int dpi;

        kDpiType( int dpi ) {
            this.dpi = dpi;
        }

        public static kDpiType getEnum( int i ) {
            for( kDpiType e : values() ) {
                if( e.ordinal() == i ) {
                    return e;
                }
            }
            return null;
        }

        public static kDpiType getEnumDpi( int dpi ) {
            if( Xxxdpi.getDpi() <= dpi ) {
                return Xxxdpi;
            }
            else if( Xxdpi.getDpi() <= dpi ) {
                return Xxdpi;
            }
            else if( Hdpi.getDpi() <= dpi ) {
                return Hdpi;
            }
            else if( TvDpi.getDpi() <= dpi ) {
                return TvDpi;
            }
            else if( Mdpi.getDpi() <= dpi ) {
                return Mdpi;
            }
            return Ldpi;
        }

        public int getDpi() {
            return dpi;
        }
    }
}
