# DIAnalytics


## Requirements

- Firebase project and his "google-services.json". For more information, refer to [Firebase Cloud Messaging docs][1]
- Application ID provided by Dialog Insight

## Installation

1. Add Google Services to your project build.gradle dependencies:
     ```groovy
    dependencies {
        ...
        classpath 'com.google.gms:google-services:3.0.0'
    }
    ```
     
2. Add to your build.gradle application module this plugin at the end of the file
    ```groovy
    apply plugin: 'com.google.gms.google-services'
    ```
For more information, refer to [Firebase Cloud Messaging docs][1]

3. Add the maven repository link to your repositories in your build.gradle project: 
    ```groovy
    allprojects {
        repositories {
            jcenter()
            maven { url "https://diandroid.s3.amazonaws.com" }
        }
    }
    ```

4. Add this to dependencies in your build.gradle application module:
    ```groovy
    dependencies {
        compile 'com.dialoginsight:dianalytics:0.1.0'
    }
    ```

## Usage

1. Add your "google-services.json" provided by Firebase to the root of your application module.

2. In the onCreate() of your Application class, before the super : 
    1. Set the base URL of the server (optional)
    2. Set if you want to display the library’s log in the logcat
    3. Start library with your ApplicationId provided by Dialog Insight
    ```java
    //Set the base URL for all calls to the server
    DIAnalytics.setBaseUrl("https://app.dialoginsight.com/");
    
    //Enable logs to be displayed
    DIAnalytics.setLogEnabled(true);
    
    //Register your application to the service
    DIAnalytics.startWithApplicationId(this, "MY_APPLICATION_ID_PROVIDED_BY_DIALOG_INSIGHT");
    
    //Application class super
    super.onCreate();
    ```
    You also need to have a receiver to display the received notification
    ```java
    public class NotificationReceiver extends DINotificationReceiver {
        @Override
        public void onMessageReceived(Context context, RemoteMessage remoteMessage) {
            //Your code to display the notification
        }
    }
    ```
    and declare it to your manifest
    ```xml
    <receiver android:name=".NotificationReceiver">
        <intent-filter>
            <action android:name="com.dialoginsight.dianalytics.NotificationBroadcast"/>
        </intent-filter>
    </receiver>
    ```

3. Send information about the user as an HashMap using the function DIAnalytics.identify(hashmap)

     In order to identify a user of the application as a contact of your DI project, you must provide the information corresponding to        the fields of the [unique key][2].

     Note: 
     the DI fields always start with a "f_". In the DI project fields page, look at the fields tagged has “primary field” and note their      code. In the following steps, just prepend “f_” with any field code. For instance, a DI field with the code “idCustomer” will            become “f_idCustomer”.

     This is made in two simples step. First, gather the primary key fields informations in a HashMap.

     For example, a project where the unique key is composed only of the EMail field, do:
     ```java
    HashMap contactData = new HashMap();
    contactData.put("f_EMail", "example@example.com");
    ```
     For a project where the unique key is composed of the fields FirstName and LastName, do:
     ```java
    HashMap contactData = new HashMap();
    contactData.put("f_EMail", "example@example.com");
    ```
     For a project where the unique key is composed only of a field named “idCustomer”, do:
     ```java
     HashMap contactData = new HashMap();
     contactData.put("f_EMail", "example@example.com");
    ```
    
    Then, make another HashMap with a “contact” entry and send it.
    ```java
    HashMap contactData = new HashMap();
    contactData.put("f_EMail", "example@example.com");
    contactData.put("f_FirstName", "FirstNane");
    contactData.put("f_LastName", "LastName");
    
    HashMap hashMap = new HashMap();
    hashMap.put("contact", contactData);
    DIAnalytics.identify(hashMap);
    ```

4. Register for push notification. On android 6.0 and above this will prompt to user an AlertDialog asking to authorize notifications.
    ```java
    DIAnalytics.registerForRemoteNotification();
    ```
    
5. Send confirmation of receipt.
    ```java
    DIAnalytics.sendPushReception("messageID");
    ```
    
    You can retrieve the message ID within the data payload by using the key OFSYSReceptionID.
    ```java
    @Override
    public void onMessageReceived(Context context, RemoteMessage remoteMessage) {
          DIAnalytics.sendPushReception(remoteMessage.getData().get("OFSYSReceptionID"));
          ...
    }
    ```
    When the app is in the background, the data payload is delivered in the extras of the intent of your launcher Activity. When the app     is in the foreground, the data payload is delivered to the onMessageReceived.
    
## Authors
Dialog Insight, info@dialoginsight.com

## License

[1]: https://firebase.google.com/docs/cloud-messaging/
[2]: https://support.dialoginsight.com/en/support/solutions/articles/1000249331-defining-project-fields
