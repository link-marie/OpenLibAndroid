package com.linknext.libopen;

import android.util.Log;

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


}
