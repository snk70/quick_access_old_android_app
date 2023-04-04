package Fast_Accessibility.ModernPl;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class setting_parts extends Activity implements B4AActivity{
	public static setting_parts mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.setting_parts");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (setting_parts).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.setting_parts");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Fast_Accessibility.ModernPl.setting_parts", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (setting_parts) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (setting_parts) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return setting_parts.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (setting_parts) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (setting_parts) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        Object[] o;
        if (permissions.length > 0)
            o = new Object[] {permissions[0], grantResults[0] == 0};
        else
            o = new Object[] {"", false};
        processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static boolean _intent_added = false;
public static String _intent_fn = "";
public static String _intent_ln = "";
public static String _intent_url = "";
public anywheresoftware.b4a.objects.ListViewWrapper _vvvvv5 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvv4 = null;
public Fast_Accessibility.ModernPl.main _vvvv5 = null;
public Fast_Accessibility.ModernPl.home_act _home_act = null;
public Fast_Accessibility.ModernPl.definition_permissions_act _definition_permissions_act = null;
public Fast_Accessibility.ModernPl.installed_applications _installed_applications = null;
public Fast_Accessibility.ModernPl.file_manager _file_manager = null;
public Fast_Accessibility.ModernPl.send_comment_act _send_comment_act = null;
public Fast_Accessibility.ModernPl.contact_act _contact_act = null;
public Fast_Accessibility.ModernPl.selected_contact _selected_contact = null;
public Fast_Accessibility.ModernPl.help_act _help_act = null;
public Fast_Accessibility.ModernPl.searchmodule _vvvv6 = null;
public Fast_Accessibility.ModernPl.sizeviewv3 _vvvv7 = null;
public Fast_Accessibility.ModernPl.regular_validations _regular_validations = null;
public Fast_Accessibility.ModernPl.size_view _size_view = null;
public Fast_Accessibility.ModernPl.interchange_stn _interchange_stn = null;
public Fast_Accessibility.ModernPl.size_view301 _size_view301 = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 25;BA.debugLine="lstv.Initialize(\"ls\")";
mostCurrent._vvvvv5.Initialize(mostCurrent.activityBA,"ls");
 //BA.debugLineNum = 30;BA.debugLine="lstv.Color=Colors.ARGB(255,142,24,46)";
mostCurrent._vvvvv5.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (142),(int) (24),(int) (46)));
 //BA.debugLineNum = 31;BA.debugLine="Activity.AddView(lstv,0,0,Activity.Width,Activity";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._vvvvv5.getObject()),(int) (0),(int) (0),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 //BA.debugLineNum = 34;BA.debugLine="Intent_Added=False";
_intent_added = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 35;BA.debugLine="Intent_FN=\"\"";
_intent_fn = "";
 //BA.debugLineNum = 36;BA.debugLine="Intent_LN=\"\"";
_intent_ln = "";
 //BA.debugLineNum = 37;BA.debugLine="Intent_URL=\"\"";
_intent_url = "";
 //BA.debugLineNum = 40;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات\",\"SETTINGS\")";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات",(Object)("SETTINGS"));
 //BA.debugLineNum = 41;BA.debugLine="lstv.AddSingleline2(\"		\"&\"دسترسی\",\"ACCESSIBILITY_";
mostCurrent._vvvvv5.AddSingleLine2("		"+"دسترسی",(Object)("ACCESSIBILITY_SETTINGS"));
 //BA.debugLineNum = 42;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات بیسیم و شبکه\",\"";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات بیسیم و شبکه",(Object)("AIRPLANE_MODE_SETTINGS"));
 //BA.debugLineNum = 43;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات WIFI\",\"WIFI_SET";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات WIFI",(Object)("WIFI_SETTINGS"));
 //BA.debugLineNum = 44;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات بلوتوث\",\"BLUETO";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات بلوتوث",(Object)("BLUETOOTH_SETTINGS"));
 //BA.debugLineNum = 45;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات IP\",\"WIFI_IP_SE";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات IP",(Object)("WIFI_IP_SETTINGS"));
 //BA.debugLineNum = 46;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات APN\",\"APN_SETTI";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات APN",(Object)("APN_SETTINGS"));
 //BA.debugLineNum = 47;BA.debugLine="lstv.AddSingleline2(\"		\"&\"گزینه های برنامه نویس\",";
mostCurrent._vvvvv5.AddSingleLine2("		"+"گزینه های برنامه نویس",(Object)("APPLICATION_DEVELOPMENT_SETTINGS"));
 //BA.debugLineNum = 48;BA.debugLine="lstv.AddSingleline2(\"		\"&\"مدیریت برنامه های نصب ش";
mostCurrent._vvvvv5.AddSingleLine2("		"+"مدیریت برنامه های نصب شده",(Object)("APPLICATION_SETTINGS"));
 //BA.debugLineNum = 49;BA.debugLine="lstv.AddSingleline2(\"		\"&\"مدیریت تمام برنامه های";
mostCurrent._vvvvv5.AddSingleLine2("		"+"مدیریت تمام برنامه های نصب شده",(Object)("MANAGE_ALL_APPLICATIONS_SETTINGS"));
 //BA.debugLineNum = 50;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمان رومینگ\",\"DATA_R";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمان رومینگ",(Object)("DATA_ROAMING_SETTINGS"));
 //BA.debugLineNum = 51;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات تاریخ و زمان\",\"";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات تاریخ و زمان",(Object)("DATE_SETTINGS"));
 //BA.debugLineNum = 52;BA.debugLine="lstv.AddSingleline2(\"		\"&\"اطلاعات دستگاه\",\"DEVICE";
mostCurrent._vvvvv5.AddSingleLine2("		"+"اطلاعات دستگاه",(Object)("DEVICE_INFO_SETTINGS"));
 //BA.debugLineNum = 53;BA.debugLine="lstv.AddSingleline2(\"		\"&\"صفحه نمایش\",\"DISPLAY_SE";
mostCurrent._vvvvv5.AddSingleLine2("		"+"صفحه نمایش",(Object)("DISPLAY_SETTINGS"));
 //BA.debugLineNum = 54;BA.debugLine="lstv.AddSingleline2(\"		\"&\"روش ورودی صفحه کلید\",\"I";
mostCurrent._vvvvv5.AddSingleLine2("		"+"روش ورودی صفحه کلید",(Object)("INPUT_METHOD_SETTINGS"));
 //BA.debugLineNum = 55;BA.debugLine="lstv.AddSingleline2(\"		\"&\"زبان صفحه کلید\",\"INPUT_";
mostCurrent._vvvvv5.AddSingleLine2("		"+"زبان صفحه کلید",(Object)("INPUT_METHOD_SUBTYPE_SETTINGS"));
 //BA.debugLineNum = 56;BA.debugLine="lstv.AddSingleline2(\"		\"&\"مدیریت حافظه\",\"INTERNAL";
mostCurrent._vvvvv5.AddSingleLine2("		"+"مدیریت حافظه",(Object)("INTERNAL_STORAGE_SETTINGS"));
 //BA.debugLineNum = 57;BA.debugLine="lstv.AddSingleline2(\"		\"&\"زبان سیستم\",\"LOCALE_SET";
mostCurrent._vvvvv5.AddSingleLine2("		"+"زبان سیستم",(Object)("LOCALE_SETTINGS"));
 //BA.debugLineNum = 58;BA.debugLine="lstv.AddSingleline2(\"		\"&\"سرویس های مکانی\",\"LOCAT";
mostCurrent._vvvvv5.AddSingleLine2("		"+"سرویس های مکانی",(Object)("LOCATION_SOURCE_SETTINGS"));
 //BA.debugLineNum = 59;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات کارت حافظه\",\"ME";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات کارت حافظه",(Object)("MEMORY_CARD_SETTINGS"));
 //BA.debugLineNum = 60;BA.debugLine="lstv.AddSingleline2(\"		\"&\"ارائه دهندگان سرویس سیم";
mostCurrent._vvvvv5.AddSingleLine2("		"+"ارائه دهندگان سرویس سیمکارت",(Object)("NETWORK_OPERATOR_SETTINGS"));
 //BA.debugLineNum = 61;BA.debugLine="lstv.AddSingleline2(\"NFC\",\"NFCSHARING_SETTINGS\")";
mostCurrent._vvvvv5.AddSingleLine2("NFC",(Object)("NFCSHARING_SETTINGS"));
 //BA.debugLineNum = 62;BA.debugLine="lstv.AddSingleline2(\"		\"&\"فایل پشتیبان و بازنشانی";
mostCurrent._vvvvv5.AddSingleLine2("		"+"فایل پشتیبان و بازنشانی",(Object)("PRIVACY_SETTINGS"));
 //BA.debugLineNum = 63;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات امنیتی\",\"SECURI";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات امنیتی",(Object)("SECURITY_SETTINGS"));
 //BA.debugLineNum = 64;BA.debugLine="lstv.AddSingleline2(\"		\"&\"تنظیمات صدا\",\"SOUND_SET";
mostCurrent._vvvvv5.AddSingleLine2("		"+"تنظیمات صدا",(Object)("SOUND_SETTINGS"));
 //BA.debugLineNum = 65;BA.debugLine="lstv.AddSingleline2(\"		\"&\"همگام سازی\",\"SYNC_SETTI";
mostCurrent._vvvvv5.AddSingleLine2("		"+"همگام سازی",(Object)("SYNC_SETTINGS"));
 //BA.debugLineNum = 66;BA.debugLine="lstv.AddSingleLine2(\"		\"&\"دیکشنری کاربر\",\"USER_DI";
mostCurrent._vvvvv5.AddSingleLine2("		"+"دیکشنری کاربر",(Object)("USER_DICTIONARY_SETTINGS"));
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 131;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 134;BA.debugLine="StartActivity(Definition_Permissions_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 //BA.debugLineNum = 135;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 138;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 80;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim lstv As ListView";
mostCurrent._vvvvv5 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _ls_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog _msgdialog = null;
int _r = 0;
anywheresoftware.b4a.objects.IntentWrapper _dointent = null;
 //BA.debugLineNum = 87;BA.debugLine="Sub ls_ItemClick (Position As Int, Value As Object";
 //BA.debugLineNum = 90;BA.debugLine="Dim msgdialog As InputDialog";
_msgdialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 93;BA.debugLine="Dim r As Int=Msgbox2(\"میخواهید این بخش رو به لیست";
_r = anywheresoftware.b4a.keywords.Common.Msgbox2("میخواهید این بخش رو به لیست دسترسی های سریع اضافه کنید یا اینکه اونو ببینید ؟","","اضافه کردن","لغو","دیدن این بخش",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 95;BA.debugLine="If r=DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 97;BA.debugLine="If msgdialog.Show(\"لطفا نام دلخواهی برای این دستر";
if (_msgdialog.Show("لطفا نام دلخواهی برای این دسترسی انتخاب کنید","","تأیید","لغو","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null))==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 98;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 105;BA.debugLine="Intent_FN=msgdialog.Input";
_intent_fn = _msgdialog.getInput();
 //BA.debugLineNum = 107;BA.debugLine="Intent_URL=\"android.settings.\"&Value";
_intent_url = "android.settings."+BA.ObjectToString(_value);
 //BA.debugLineNum = 108;BA.debugLine="Intent_Added=True";
_intent_added = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 109;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 110;BA.debugLine="StartActivity(Definition_Permissions_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 }else if(_r==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
 //BA.debugLineNum = 114;BA.debugLine="Try";
try { //BA.debugLineNum = 115;BA.debugLine="Dim DoIntent As Intent";
_dointent = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 116;BA.debugLine="DoIntent.Initialize(\"android.settings.\"&Value";
_dointent.Initialize("android.settings."+BA.ObjectToString(_value),"");
 //BA.debugLineNum = 117;BA.debugLine="StartActivity(DoIntent)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_dointent.getObject()));
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 119;BA.debugLine="Msgbox(\"متأسفانه این قسمت توسط دستگاه شما پشتیب";
anywheresoftware.b4a.keywords.Common.Msgbox("متأسفانه این قسمت توسط دستگاه شما پشتیبانی نمیشود","اخطار",mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim Intent_Added As Boolean=False";
_intent_added = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 10;BA.debugLine="Dim Intent_FN,Intent_LN,Intent_URL As String";
_intent_fn = "";
_intent_ln = "";
_intent_url = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
}
