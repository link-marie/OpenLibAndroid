package com.linknext.libopen;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/**
 * Utility methods
 */
public class Utl {

    public static final String tagDebug = "dTag";

    private static boolean logEnable = true;

    /**
     * Check if logging is enabled
     *
     * @return
     */
    public static boolean isLogEnable() {
        return logEnable;
    }

    /**
     * Set master switch of logging
     * Better to disable all logging for release build
     *
     * @param logEnable
     */
    public static void setLogEnable( boolean logEnable ) {
        Utl.logEnable = logEnable;
    }

    /**
     * Log caller method
     *
     * @return
     */
    static public String logDebug() {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, 1 );
        Log.d( tagDebug, lMsg );
        return lMsg;
    }

    /**
     * Log caller method with message
     *
     * @param msg
     * @return
     */
    static public String logDebug( String msg ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, 1 ) + " " + msg;
        Log.d( tagDebug, lMsg );
        return lMsg;
    }

    /**
     * Log specified number of caller methods for debugging
     *
     * @param msg
     * @param num nested caller methods
     * @return
     */
    static public String logDebug( String msg, int num ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, num ) + " " + msg;
        Log.d( tagDebug, lMsg );
        return lMsg;
    }

    /**
     * Log caller method with specified tag and message
     *
     * @param tag
     * @param msg
     * @return
     */
    static public String logDebug( String tag, String msg ) {
        int level = 3;
        return logDebug( tag, msg, level );
    }

    /**
     * Log caller method with specified tag and message
     *
     * @param tag
     * @param msg
     * @param num nested caller methods
     * @return
     */
    static public String logDebug( String tag, String msg, int num ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, num ) + " " + msg;
        Log.d( tag, lMsg );
        return lMsg;
    }

    static public String logError( String msg ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, 1 ) + " " + msg;
        Log.e( tagDebug, lMsg );
        return lMsg;
    }

    static public String logError( String tag, String msg ) {
        return logError( tag, msg, 3 );
    }

    static public String logError( String msg, int num ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, num ) + " " + msg;
        Log.e( tagDebug, lMsg );
        return lMsg;
    }

    static public String logError( String tag, String msg, int num ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, num ) + " " + msg;
        Log.e( tag, lMsg );
        return lMsg;
    }

    static public String logWarning( String msg ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, 1 ) + " " + msg;
        Log.w( tagDebug, lMsg );
        return lMsg;
    }

    static public String logWarning( String tag, String msg ) {
        return logWarning( tag, msg, 2 );
    }

    static public String logWarning( String msg, int num ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, num ) + " " + msg;
        Log.w( tagDebug, lMsg );
        return lMsg;
    }

    static public String logWarning( String tag, String msg, int num ) {
        if( isLogEnable() == false ) {
            return "";
        }
        String lMsg = getCallerMethod( 2, num ) + " " + msg;
        Log.w( tag, lMsg );
        return lMsg;
    }

    /**
     * Log all caller methods stack
     * <p>
     * Level:
     * 0: The method info
     * 1:  dispatch
     * 2:  this method
     * 3:  caller1
     * 4:  caller2
     * 5:  caller....
     */
    static public void logStack() {
        if( isLogEnable() == false ) {
            return;
        }
        StackTraceElement[] lElem = new Throwable().getStackTrace();
        if( lElem == null ) {
            return;
        }
        for( int i = 0; i < lElem.length; i++ ) {
            StackTraceElement lElement = lElem[i];
            String lMsg;
            if( i == 0 ) {
                lMsg = "";
            }
            else {
                lMsg = " ";
            }
            lMsg += lElement.toString();
            Log.d( tagDebug, lMsg );
        }
    }


    /**
     * Get caller method names which are more than specified number of nest level
     *
     * @param level nest level of caller stack
     * @param num   number of nests above the 'level'
     * @return
     */
    static public String getCallerMethod( int level, int num ) {

        String callerNames = "unknown";
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        int lLevel = level;
        if( stackTrace.length > lLevel ) {

            callerNames = "";

            for( int i = 0; i < num; i++ ) {

                StackTraceElement elem = stackTrace[lLevel];
                String classNameL = elem.getClassName();
                String[] split = classNameL.split( "\\." );
                String classNameS = null;
                if( split.length > 0 ) {
                    classNameS = split[split.length - 1];
                }
                String method = elem.getMethodName();

                if( i > 0 ) {
                    callerNames += ": ";
                }

                if( classNameS != null ) {
                    callerNames += classNameS + "." + method;
                }
                else {
                    callerNames += classNameL + "." + method;
                }

                lLevel++;
                if( lLevel < 0 || lLevel >= stackTrace.length ) {
                    break;
                }
            }
        }
        return callerNames;
    }

    static public boolean hasNavBar( Resources resources ) {
        int id = resources.getIdentifier( "config_showNavigationBar", "bool", "android" );
        if( id > 0 ) {
            return resources.getBoolean( id );
        }
        else {
            return false;
        }
    }

    static public int getNavigationBarHeight( Resources resources ) {
        if( !hasNavBar( resources ) ) {
            return 0;
        }

        int orientation = resources.getConfiguration().orientation;
        // Only phone between 0 - 599 has navigationbar can move
        boolean isSmartphone = resources.getConfiguration().smallestScreenWidthDp < 600;
        if( isSmartphone && Configuration.ORIENTATION_LANDSCAPE == orientation ) {
            return 0;
        }

        int id = resources.getIdentifier( orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android" );
        if( id > 0 ) {
            return resources.getDimensionPixelSize( id );
        }

        return 0;
    }

    static public boolean isTranslucentNavigation( Activity activity ) {
        if( Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT ) {
            return false;
        }
        int flags = activity.getWindow().getAttributes().flags;
        int result = flags&android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        if( result == 0 ) {
            return false;
        }
        return true;
    }

    static public DisplayMetrics getDisplayMetrics( Context ctx ) {
        WindowManager windowManager = (WindowManager)ctx.getSystemService( Context.WINDOW_SERVICE );
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics( displayMetrics );
        return displayMetrics;
    }

    static public float px2dp( Context ctx, int px ) {
        float density = getDensity( ctx );
        float dp = px/density + 0.5f;
        return dp;
    }

    static public float getDensity( Context ctx ) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return density;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    static public int getColor( Context ctx, int id ) {

        int col = 888888;
        if( ctx == null ) {
            return col;
        }
        if( Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M ) {
            col = ctx.getColor( id );
        }
        else {
            Resources res = ctx.getResources();
            if( res == null ) {
                return col;
            }
            col = res.getColor( id );
        }

        return col;
    }

    static public int getVersionCode( Context ctx ) {
        String lPackageName = ctx.getPackageName();

        int i = -1;
        try {
            PackageInfo pi = ctx.getPackageManager().getPackageInfo( lPackageName, PackageManager.GET_META_DATA );
            i = pi.versionCode;
        }
        catch( PackageManager.NameNotFoundException e ) {
            e.printStackTrace();
        }

        return i;
    }

    static public String getResStr( Context ctx, int resId ) {
        return ctx.getResources().getString( resId );
    }

    static public String getResStr( Context ctx, int resId, String arg1 ) {
        return ctx.getResources().getString( resId, arg1 );
    }

    static public String getResStr( Context ctx, int resId, String arg1, String arg2 ) {
        return ctx.getResources().getString( resId, arg1, arg2 );
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    static public boolean setTranslucentNavitation( Activity activity ) {
        boolean ret = false;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            activity.getWindow().addFlags( android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION );
            ret = true;
        }
        return ret;
    }


}
