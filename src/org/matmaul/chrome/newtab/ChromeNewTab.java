package org.matmaul.chrome.newtab;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class ChromeNewTab implements IXposedHookLoadPackage {
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		if (!lpparam.packageName.equals("com.chrome.beta") && !lpparam.packageName.equals("com.android.chrome"))
			return;

		try {
			findAndHookMethod("com.google.android.apps.chrome.tabmodel.TabModelImpl", lpparam.classLoader, "launchUrlFromExternalApp", String.class, String.class, String.class, "boolean", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param)
					throws Throwable {
				param.args[3] = true;
			}
			
			});
		} catch (Throwable t) {
			XposedBridge.log(t);
		}
	}
}
