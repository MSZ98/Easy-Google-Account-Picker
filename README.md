# Easy-Google-Account-Picker


 ### USAGE

 1. Constructor must be invoked in OnCreate() method of activity
    otherwise you'll get exception like
    java.lang.IllegalStateException: LifecycleOwner is attempting to register while current state is RESUMED.
    It should look like this:

```java
    GoogleAccountPicker googleAccountPicker = new GoogleAccountPicker(this);
```

 2. Use pickAccount() method anywhere to pick an account. It doesn't return anything. You have to
    do it like this:

```java
    googleAccountPicker.pickAccount(account -> {
        System.out.println(account.getName());
    });
```
    or
```java    
    googleAccountPicker.pickAccount(new OnAccountPickedListener() {
          public void onAccountPicked(Account account) {
              System.out.println(account.getName());
          }
    });
```
 3. You can also use getAccounts() method to get all available accounts without showing up dialog.
    It's useful when your app remembers once chosen account.


 Remember to set up permission in manifest:
 ```xml
      <b>&lt;uses-permission android:name="android.permission.GET_ACCOUNTS"/&gt;</b>
 ```

 and add dependency to build.gradle:
 ```gradle
      <b>implementation 'com.google.android.gms:play-services-base:17.6.0'</b>
 ```

