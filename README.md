# Easy-Google-Account-Picker

/**<pre>
 * <b>USAGE</b>
 *
 * 1. Constructor must be invoked in OnCreate() method of activity
 *    otherwise you'll get exception like
 *    java.lang.IllegalStateException: LifecycleOwner is attempting to register while current state is RESUMED.
 *    It should look like this:
 *
 *    GoogleAccountPicker googleAccountPicker = new GoogleAccountPicker(this);
 *
 * 2. Use pickAccount() method anywhere to pick an account. It doesn't return anything. You have to
 *    do it like this:
 *
 *    googleAccountPicker.pickAccount(account -> {
 *        System.out.println(account.getName());
 *    });
 *
 *    or
 *    googleAccountPicker.pickAccount(new OnAccountPickedListener() {
 *          public void onAccountPicked(Account account) {
 *              System.out.println(account.getName());
 *          }
 *    });
 *
 * 3. You can also use getAccounts() method to get all available accounts without showing up dialog.
 *    It's useful when your app remembers once chosen account.
 *
 *
 * Remember to set up permission in manifest:
 *
 *      &lt;uses-permission android:name="android.permission.GET_ACCOUNTS"/&gt;
 *
 *
 * and add implementation to build.gradle:
 *
 *      implementation 'com.google.android.gms:play-services-base:17.6.0'
 *
 * </pre>
 * */
