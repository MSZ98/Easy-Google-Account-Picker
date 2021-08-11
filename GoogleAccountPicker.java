package com.xyz.pstest;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.gms.common.AccountPicker;

import java.util.Arrays;


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
public class GoogleAccountPicker {


    private OnAccountPickedListener onAccountPickedListener = (account) -> {};
    private AccountManager accountManager;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public GoogleAccountPicker(ComponentActivity componentActivity) {
        accountManager = AccountManager.get(componentActivity);
        activityResultLauncher = componentActivity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_CANCELED) return;
                    String accountName = result.getData().getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    Account[] accounts = accountManager.getAccounts();
                    Account account = null;
                    for(Account a : accounts) if(a.name.equals(accountName)) account = a;
                    onAccountPickedListener.onAccountPicked(account);
                }
        );
    }


    public interface OnAccountPickedListener {
        public void onAccountPicked(Account account);
    }


    public void pickAccount(OnAccountPickedListener onAccountPickedListener) {
        this.onAccountPickedListener = onAccountPickedListener;
        Intent intent = AccountPicker.newChooseAccountIntent(new AccountPicker.AccountChooserOptions.Builder()
                .setAllowableAccountsTypes(Arrays.asList("com.google"))
                .setAlwaysShowAccountPicker(true)
                .build());
        activityResultLauncher.launch(intent);
    }


    public Account[] getAccounts() {
        return accountManager.getAccounts();
    }


    public AccountManager getAccountManager() {
        return accountManager;
    }


}
