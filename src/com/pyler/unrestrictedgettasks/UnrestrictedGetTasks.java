package com.pyler.unrestrictedgettasks;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class UnrestrictedGetTasks implements IXposedHookLoadPackage {
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if (!"android".equals(lpparam.packageName)) {
			return;
		}
		
		XposedBridge.hookAllMethods(XposedHelpers.findClass(
				"com.android.server.am.ActivityManagerService",
				lpparam.classLoader), "isGetTasksAllowed", XC_MethodReplacement
				.returnConstant(true));
	}
}
