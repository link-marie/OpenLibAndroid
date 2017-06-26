# Utility library for Android develpment
[OpenLibAndroid](https://github.com/link-marie/OpenLibAndroid)

# Library

[libOpenAndroid](https://github.com/link-marie/OpenLibAndroid/tree/master/libOpenAndroid)

 Many useful methods (and going to be added..)
 such as..

## Easy logging (Debug/Warning/Error) method

- One line statement to log a method (for simple way)
- Show stack methods for tracing calling sequence
- Able to specify any tag
- Enable/Disable setting of logging for release build

### Example 1: Just call one line debug method like this:

```java

    class MainActivity {
    
        void methodCaller(){
        
            // Just call like this
            Utl.logDebug();
        
        }
    }


```

you will get following log on 'logcat'.

    date-yyyy-mm-dd hh:mm:ss/com.linknext.openlib `D/anyTag: MainActivity.methodCaller`

Not necessary to define "TAG" for each file anymore!


### Example 2: Possible to log stack methods for trace callers.

 ```java
 
     class MainActivity {
  
         void methodUpper(){
            methodCaller();
         }
     
         void methodCaller(){
         
             // specify nest level for stack trace
             int numStack = 2;
             Utl.logDebug( "message", numStack);
         
         }
     }
 
 
 ```


Both *methodCaller()* and *methodUpper()* are logged like this.

    date-yyyy-mm-dd hh:mm:ss/com.linknext.openlib `D/anyTag: MainActivity.methodCaller; MainActivity.methodUpper`

Very useful to determine who is calling the target method.


# Demo and Test app for the library

[demoOpenAndroid](https://github.com/link-marie/OpenLibAndroid/tree/master/demoOpenAndroid)

- Quick test for the library

# Setup
Just download master project and build with Android Studio


# Developed By
Marie Rijk <rijkmarie@gmail.com>
with our members ...

# License

    Copyright Marie Rijk since 2009

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

