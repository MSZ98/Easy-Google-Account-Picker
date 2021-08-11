# Easy-Google-Account-Picker

<pre>
 <b>USAGE</b>

 1. Constructor must be invoked in OnCreate() method of activity
    otherwise you'll get exception like
    java.lang.IllegalStateException: LifecycleOwner is attempting to register while current state is RESUMED.
    It should look like this:

    <b>GoogleAccountPicker googleAccountPicker = new GoogleAccountPicker(this);</b>

 2. Use pickAccount() method anywhere to pick an account. It doesn't return anything. You have to
    do it like this:

<b>
    googleAccountPicker.pickAccount(account -> {
        System.out.println(account.getName());
    });
</b>
    or
<b>    
    googleAccountPicker.pickAccount(new OnAccountPickedListener() {
          public void onAccountPicked(Account account) {
              System.out.println(account.getName());
          }
    });
</b>
 3. You can also use getAccounts() method to get all available accounts without showing up dialog.
    It's useful when your app remembers once chosen account.


 Remember to set up permission in manifest:
      <b>&lt;uses-permission android:name="android.permission.GET_ACCOUNTS"/&gt;</b>


 and add implementation to build.gradle:
      <b>implementation 'com.google.android.gms:play-services-base:17.6.0'</b>

 </pre>
