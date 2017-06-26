package com.linknext.openlib;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.linknext.libopen.Utl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar)findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        FloatingActionButton fab = (FloatingActionButton)findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                actionDoit();
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();
        if( id == R.id.action_settings ) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    private void actionDoit() {
        actionNested();
    }

    private void actionNested() {

        // Choose test mode!
        int testMode = 3;

        switch( testMode ) {
        case 0:
        default:
            // Log this method
            Utl.logDebug();
            break;
        case 1: {
            // Log this method with message
            String msg = "message";
            Utl.logDebug( msg );
            Utl.logWarning( msg );
            Utl.logError( msg );
        }
        break;

        case 2: {
            // Log nested caller methods
            String msg = "message";
            int num = 3;
            Utl.logDebug( msg, num );
            Utl.logWarning( msg, num );
            Utl.logError( msg, num );
        }
        break;

        case 3: {
            String tag = "anyTag";
            String msg = "message";
            int num = 1;
            Utl.logDebug( tag, msg, num );
            Utl.logWarning( tag, msg, num );
            Utl.logError( tag, msg, num );
        }
        break;
        case 4: {
            int level = 2;
            int num = 2;
            Log.d( Utl.tagDebug, Utl.getCallerMethod( level, num ) );
        }
        break;
        case 5:
            Utl.logStack();
            break;
        }
    }
}
