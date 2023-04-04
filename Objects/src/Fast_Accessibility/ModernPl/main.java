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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Fast_Accessibility.ModernPl.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (main) Resume **");
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
public static anywheresoftware.b4a.samples.httputils2.httpjob _vvv3 = null;
public static com.devil.signature.CheckSignature _ch_sign = null;
public static String _edu_clip = "";
public static String _p_key = "";
public static String _p_id = "";
public anywheresoftware.b4a.inappbilling3.BillingManager3 _b_mark = null;
public static int _vvv2 = 0;
public static int _vvvv0 = 0;
public b4a.util.BClipboard _vvvvv1 = null;
public static String _hardware_licence = "";
public static String _software_licence = "";
public anywheresoftware.b4a.objects.EditTextWrapper _software_txt = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_buy = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_exit = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_demo = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_clip = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvv4 = null;
public Fast_Accessibility.ModernPl.home_act _home_act = null;
public Fast_Accessibility.ModernPl.definition_permissions_act _definition_permissions_act = null;
public Fast_Accessibility.ModernPl.installed_applications _installed_applications = null;
public Fast_Accessibility.ModernPl.setting_parts _setting_parts = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (home_act.mostCurrent != null);
vis = vis | (definition_permissions_act.mostCurrent != null);
vis = vis | (installed_applications.mostCurrent != null);
vis = vis | (setting_parts.mostCurrent != null);
vis = vis | (file_manager.mostCurrent != null);
vis = vis | (send_comment_act.mostCurrent != null);
vis = vis | (contact_act.mostCurrent != null);
vis = vis | (selected_contact.mostCurrent != null);
vis = vis | (help_act.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 51;BA.debugLine="File.MakeDir(File.DirInternal,\"fsaccs\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs");
 //BA.debugLineNum = 53;BA.debugLine="StartActivity(Home_Act)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._home_act.getObject()));
 //BA.debugLineNum = 54;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _bm_billingsupported(boolean _supported,String _message) throws Exception{
 //BA.debugLineNum = 992;BA.debugLine="Sub bm_BillingSupported (Supported As Boolean, Mes";
 //BA.debugLineNum = 1005;BA.debugLine="End Sub";
return "";
}
public static String  _bm_purchasecompleted(boolean _success,anywheresoftware.b4a.inappbilling3.BillingManager3.Prchase _product) throws Exception{
 //BA.debugLineNum = 1008;BA.debugLine="Sub bm_PurchaseCompleted (Success As Boolean, Prod";
 //BA.debugLineNum = 1028;BA.debugLine="End Sub";
return "";
}
public static String  _btn_buy_click() throws Exception{
 //BA.debugLineNum = 1031;BA.debugLine="Sub btn_buy_Click";
 //BA.debugLineNum = 1045;BA.debugLine="End Sub";
return "";
}
public static String  _btn_clip_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _edu_i = null;
 //BA.debugLineNum = 954;BA.debugLine="Sub btn_clip_Click";
 //BA.debugLineNum = 955;BA.debugLine="Dim edu_i As Intent";
_edu_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 956;BA.debugLine="edu_i.Initialize(edu_i.ACTION_VIEW,edu_CLip)";
_edu_i.Initialize(_edu_i.ACTION_VIEW,mostCurrent._edu_clip);
 //BA.debugLineNum = 957;BA.debugLine="StartActivity(edu_i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_edu_i.getObject()));
 //BA.debugLineNum = 958;BA.debugLine="End Sub";
return "";
}
public static String  _btn_exit_click() throws Exception{
 //BA.debugLineNum = 960;BA.debugLine="Sub btn_Exit_Click";
 //BA.debugLineNum = 961;BA.debugLine="If Msgbox2(\"میخواهید از برنامه خارج شوید ؟\",\"خروج";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("میخواهید از برنامه خارج شوید ؟","خروج","بله","نه","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 962;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 964;BA.debugLine="End Sub";
return "";
}
public static String  _create_hardlicence(String _rand_number,String _p_serial) throws Exception{
anywheresoftware.b4a.phone.Phone _ph = null;
anywheresoftware.b4a.agraham.encryption.Base64 _bs = null;
 //BA.debugLineNum = 69;BA.debugLine="Sub Create_HardLicence(Rand_Number As String,P_Ser";
 //BA.debugLineNum = 71;BA.debugLine="Dim ph As Phone";
_ph = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 72;BA.debugLine="Dim bs As Base64";
_bs = new anywheresoftware.b4a.agraham.encryption.Base64();
 //BA.debugLineNum = 73;BA.debugLine="Hardware_licence=Rand_Number&ph.Product&ph.Manufa";
mostCurrent._hardware_licence = _rand_number+_ph.getProduct()+_ph.getManufacturer()+_ph.getModel()+_ph.GetSettings("android_id");
 //BA.debugLineNum = 74;BA.debugLine="Hardware_licence=bs.EncodeBtoS(Hardware_licence.G";
mostCurrent._hardware_licence = _bs.EncodeBtoS(mostCurrent._hardware_licence.getBytes("UTF8"),(int) (0),mostCurrent._hardware_licence.getBytes("UTF8").length);
 //BA.debugLineNum = 75;BA.debugLine="Hardware_licence=Hardware_licence&P_Serial";
mostCurrent._hardware_licence = mostCurrent._hardware_licence+_p_serial;
 //BA.debugLineNum = 77;BA.debugLine="Hardware_licence= Encryption_stn1(Hardware_licenc";
mostCurrent._hardware_licence = _encryption_stn1(mostCurrent._hardware_licence);
 //BA.debugLineNum = 79;BA.debugLine="Hardware_licence= Hardware_licence.SubString2(Har";
mostCurrent._hardware_licence = mostCurrent._hardware_licence.substring((int) (mostCurrent._hardware_licence.length()-4),mostCurrent._hardware_licence.length())+mostCurrent._hardware_licence.substring((int) (0),(int) (mostCurrent._hardware_licence.length()-4));
 //BA.debugLineNum = 81;BA.debugLine="Return Interchange_stn.Encryption_Query_String_st";
if (true) return mostCurrent._interchange_stn._encryption_query_string_stn(mostCurrent.activityBA,mostCurrent._hardware_licence);
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _decryption_stn1_stn1(String _t) throws Exception{
String _txt = "";
String _tx2 = "";
String[] _cdc = null;
int _n = 0;
String _x = "";
int _b = 0;
 //BA.debugLineNum = 311;BA.debugLine="Public Sub Decryption_stn1_stn1( t As String) As S";
 //BA.debugLineNum = 312;BA.debugLine="lng =4";
_vvv2 = (int) (4);
 //BA.debugLineNum = 315;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 317;BA.debugLine="Dim tx2 As String";
_tx2 = "";
 //BA.debugLineNum = 318;BA.debugLine="Dim cdc(t.Length) As String";
_cdc = new String[_t.length()];
java.util.Arrays.fill(_cdc,"");
 //BA.debugLineNum = 320;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 322;BA.debugLine="For n = 0 To t.Length / lng - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()/(double)_vvv2-1);
for (_n = (int) (0) ; (step6 > 0 && _n <= limit6) || (step6 < 0 && _n >= limit6); _n = ((int)(0 + _n + step6)) ) {
 //BA.debugLineNum = 323;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 324;BA.debugLine="x = txt.Substring(txt.Length - lng)";
_x = _txt.substring((int) (_txt.length()-_vvv2));
 //BA.debugLineNum = 325;BA.debugLine="cdc(n) = sina_DEC_stn1_stn1(x)";
_cdc[_n] = _sina_dec_stn1_stn1(_x);
 //BA.debugLineNum = 326;BA.debugLine="txt = txt.SubString2(0, txt.Length - lng)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-_vvv2));
 }
};
 //BA.debugLineNum = 329;BA.debugLine="For b = 0 To cdc.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cdc.length-1);
for (_b = (int) (0) ; (step12 > 0 && _b <= limit12) || (step12 < 0 && _b >= limit12); _b = ((int)(0 + _b + step12)) ) {
 //BA.debugLineNum = 330;BA.debugLine="tx2 = cdc(b) & tx2";
_tx2 = _cdc[_b]+_tx2;
 }
};
 //BA.debugLineNum = 333;BA.debugLine="Return tx2";
if (true) return _tx2;
 //BA.debugLineNum = 334;BA.debugLine="End Sub";
return "";
}
public static String  _decryption_stn2(String _t) throws Exception{
String _txt = "";
String _tx2 = "";
String[] _cdc = null;
int _n = 0;
String _x = "";
int _b = 0;
 //BA.debugLineNum = 743;BA.debugLine="Public Sub Decryption_stn2( t As String) As String";
 //BA.debugLineNum = 744;BA.debugLine="lng =2";
_vvv2 = (int) (2);
 //BA.debugLineNum = 747;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 749;BA.debugLine="Dim tx2 As String";
_tx2 = "";
 //BA.debugLineNum = 750;BA.debugLine="Dim cdc(t.Length) As String";
_cdc = new String[_t.length()];
java.util.Arrays.fill(_cdc,"");
 //BA.debugLineNum = 752;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 754;BA.debugLine="For n = 0 To t.Length / lng - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()/(double)_vvv2-1);
for (_n = (int) (0) ; (step6 > 0 && _n <= limit6) || (step6 < 0 && _n >= limit6); _n = ((int)(0 + _n + step6)) ) {
 //BA.debugLineNum = 755;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 756;BA.debugLine="x = txt.Substring(txt.Length - lng)";
_x = _txt.substring((int) (_txt.length()-_vvv2));
 //BA.debugLineNum = 757;BA.debugLine="cdc(n) = sina_DEC_stn2(x)";
_cdc[_n] = _sina_dec_stn2(_x);
 //BA.debugLineNum = 758;BA.debugLine="txt = txt.SubString2(0, txt.Length - lng)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-_vvv2));
 }
};
 //BA.debugLineNum = 761;BA.debugLine="For b = 0 To cdc.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cdc.length-1);
for (_b = (int) (0) ; (step12 > 0 && _b <= limit12) || (step12 < 0 && _b >= limit12); _b = ((int)(0 + _b + step12)) ) {
 //BA.debugLineNum = 762;BA.debugLine="tx2 = cdc(b) & tx2";
_tx2 = _cdc[_b]+_tx2;
 }
};
 //BA.debugLineNum = 765;BA.debugLine="Return tx2";
if (true) return _tx2;
 //BA.debugLineNum = 766;BA.debugLine="End Sub";
return "";
}
public static String  _encryption_stn1(String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 94;BA.debugLine="Public Sub Encryption_stn1(t As String) As String";
 //BA.debugLineNum = 96;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 98;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 99;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 100;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 101;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 103;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 104;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 105;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 106;BA.debugLine="cec(i) = sina_ENC_stn1(x)";
_cec[_i] = _sina_enc_stn1(_x);
 //BA.debugLineNum = 107;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 109;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 110;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13); _j = ((int)(0 + _j + step13)) ) {
 //BA.debugLineNum = 111;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 116;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _encryption_stn2(String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 526;BA.debugLine="Public Sub Encryption_stn2(t As String) As String";
 //BA.debugLineNum = 528;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 530;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 531;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 532;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 533;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 535;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 536;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 537;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 538;BA.debugLine="cec(i) = sina_ENC_stn2(x)";
_cec[_i] = _sina_enc_stn2(_x);
 //BA.debugLineNum = 539;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 541;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 542;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13); _j = ((int)(0 + _j + step13)) ) {
 //BA.debugLineNum = 543;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 548;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 549;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim edu_CLip As String=\"http://modernplus.ir/post";
mostCurrent._edu_clip = "http://modernplus.ir/posts/Fast_Accessibility/fast_accessibility.mp4";
 //BA.debugLineNum = 25;BA.debugLine="Dim p_Key As String=\"MIGfMA0GCSqGSIb3DQEBAQUAA4GN";
mostCurrent._p_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgVJAyPzXRMabeFQv0YgQWkdZs65KiHVjvUAjKn9/9DfLAaHxytWJCbU3LnM/xn8UQ+QffGi4XDMRm2hQyCbn9wBRGqQwaWNply966lGWE7UaCAwmBo7iWNwo2psfedDyWNVRjT3dm9g4Srjmkn22+GCok8eEfSXZvDICN7I6mPQIDAQAB";
 //BA.debugLineNum = 26;BA.debugLine="Dim P_ID As String=\"fast_accessibility_app_1396\"";
mostCurrent._p_id = "fast_accessibility_app_1396";
 //BA.debugLineNum = 27;BA.debugLine="Dim b_mark As BillingManager3";
mostCurrent._b_mark = new anywheresoftware.b4a.inappbilling3.BillingManager3();
 //BA.debugLineNum = 31;BA.debugLine="Dim lng As Int";
_vvv2 = 0;
 //BA.debugLineNum = 32;BA.debugLine="Dim rn As Int";
_vvvv0 = 0;
 //BA.debugLineNum = 33;BA.debugLine="Dim clp As BClipboard";
mostCurrent._vvvvv1 = new b4a.util.BClipboard();
 //BA.debugLineNum = 34;BA.debugLine="Dim Hardware_licence,Software_licence As String";
mostCurrent._hardware_licence = "";
mostCurrent._software_licence = "";
 //BA.debugLineNum = 37;BA.debugLine="Private Software_TXT As EditText";
mostCurrent._software_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lbl_1 As Label";
mostCurrent._lbl_1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private btn_buy As Button";
mostCurrent._btn_buy = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private btn_Exit As Button";
mostCurrent._btn_exit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private lbl_2 As Label";
mostCurrent._lbl_2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private btn_demo As Button";
mostCurrent._btn_demo = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private btn_clip As Button";
mostCurrent._btn_clip = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
 //BA.debugLineNum = 967;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 988;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
main._process_globals();
home_act._process_globals();
definition_permissions_act._process_globals();
installed_applications._process_globals();
setting_parts._process_globals();
file_manager._process_globals();
send_comment_act._process_globals();
contact_act._process_globals();
selected_contact._process_globals();
help_act._process_globals();
searchmodule._process_globals();
sizeviewv3._process_globals();
regular_validations._process_globals();
size_view._process_globals();
interchange_stn._process_globals();
size_view301._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim ht As HttpJob";
_vvv3 = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 17;BA.debugLine="Dim ch_sign As CheckSignature";
_ch_sign = new com.devil.signature.CheckSignature();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _sina_dec_stn1_stn1(String _v) throws Exception{
 //BA.debugLineNum = 337;BA.debugLine="Public Sub sina_DEC_stn1_stn1(v As String) As Stri";
 //BA.debugLineNum = 338;BA.debugLine="If v=\"8TrD\" Then";
if ((_v).equals("8TrD")) { 
 //BA.debugLineNum = 339;BA.debugLine="Return \"0\"";
if (true) return "0";
 }else if((_v).equals("dG3I")) { 
 //BA.debugLineNum = 341;BA.debugLine="Return \"1\"";
if (true) return "1";
 }else if((_v).equals("lKj^")) { 
 //BA.debugLineNum = 343;BA.debugLine="Return \"2\"";
if (true) return "2";
 }else if((_v).equals("U-Wx")) { 
 //BA.debugLineNum = 345;BA.debugLine="Return \"3\"";
if (true) return "3";
 }else if((_v).equals("cq`c")) { 
 //BA.debugLineNum = 347;BA.debugLine="Return \"4\"";
if (true) return "4";
 }else if((_v).equals("B&Up")) { 
 //BA.debugLineNum = 349;BA.debugLine="Return \"5\"";
if (true) return "5";
 }else if((_v).equals("3^n?")) { 
 //BA.debugLineNum = 351;BA.debugLine="Return \"7\"";
if (true) return "7";
 }else if((_v).equals("gT^2")) { 
 //BA.debugLineNum = 353;BA.debugLine="Return \"8\"";
if (true) return "8";
 }else if((_v).equals("Z+.c")) { 
 //BA.debugLineNum = 355;BA.debugLine="Return \"9\"";
if (true) return "9";
 }else if((_v).equals("p].D")) { 
 //BA.debugLineNum = 357;BA.debugLine="Return \"a\"";
if (true) return "a";
 }else if((_v).equals("<Jc)")) { 
 //BA.debugLineNum = 359;BA.debugLine="Return \"b\"";
if (true) return "b";
 }else if((_v).equals(",<e1")) { 
 //BA.debugLineNum = 361;BA.debugLine="Return \"c\"";
if (true) return "c";
 }else if((_v).equals("d)]t")) { 
 //BA.debugLineNum = 363;BA.debugLine="Return \"d\"";
if (true) return "d";
 }else if((_v).equals("Dnrw")) { 
 //BA.debugLineNum = 365;BA.debugLine="Return \"e\"";
if (true) return "e";
 }else if((_v).equals("64Sy")) { 
 //BA.debugLineNum = 367;BA.debugLine="Return \"f\"";
if (true) return "f";
 }else if((_v).equals("evOV")) { 
 //BA.debugLineNum = 369;BA.debugLine="Return \"g\"";
if (true) return "g";
 }else if((_v).equals("W3g&")) { 
 //BA.debugLineNum = 371;BA.debugLine="Return \"h\"";
if (true) return "h";
 }else if((_v).equals("KUBo")) { 
 //BA.debugLineNum = 373;BA.debugLine="Return \"i\"";
if (true) return "i";
 }else if((_v).equals("oF])")) { 
 //BA.debugLineNum = 375;BA.debugLine="Return \"j\"";
if (true) return "j";
 }else if((_v).equals("=jQ(")) { 
 //BA.debugLineNum = 377;BA.debugLine="Return \"k\"";
if (true) return "k";
 }else if((_v).equals("1BN&")) { 
 //BA.debugLineNum = 379;BA.debugLine="Return \"l\"";
if (true) return "l";
 }else if((_v).equals("_/!$")) { 
 //BA.debugLineNum = 381;BA.debugLine="Return \"m\"";
if (true) return "m";
 }else if((_v).equals("BsH/")) { 
 //BA.debugLineNum = 383;BA.debugLine="Return \"n\"";
if (true) return "n";
 }else if((_v).equals("8T$4")) { 
 //BA.debugLineNum = 385;BA.debugLine="Return \"o\"";
if (true) return "o";
 }else if((_v).equals(",)2]")) { 
 //BA.debugLineNum = 387;BA.debugLine="Return \"p\"";
if (true) return "p";
 }else if((_v).equals("Jq,N")) { 
 //BA.debugLineNum = 389;BA.debugLine="Return \"q\"";
if (true) return "q";
 }else if((_v).equals("{$]T")) { 
 //BA.debugLineNum = 391;BA.debugLine="Return \"r\"";
if (true) return "r";
 }else if((_v).equals("kGhY")) { 
 //BA.debugLineNum = 393;BA.debugLine="Return \"s\"";
if (true) return "s";
 }else if((_v).equals("T?W)")) { 
 //BA.debugLineNum = 395;BA.debugLine="Return \"t\"";
if (true) return "t";
 }else if((_v).equals("}+=p")) { 
 //BA.debugLineNum = 397;BA.debugLine="Return \"u\"";
if (true) return "u";
 }else if((_v).equals("jEK2")) { 
 //BA.debugLineNum = 399;BA.debugLine="Return \"v\"";
if (true) return "v";
 }else if((_v).equals("gy**")) { 
 //BA.debugLineNum = 401;BA.debugLine="Return \"w\"";
if (true) return "w";
 }else if((_v).equals("K7fM")) { 
 //BA.debugLineNum = 403;BA.debugLine="Return \"x\"";
if (true) return "x";
 }else if((_v).equals("VN=%")) { 
 //BA.debugLineNum = 405;BA.debugLine="Return \"y\"";
if (true) return "y";
 }else if((_v).equals("RC4T")) { 
 //BA.debugLineNum = 407;BA.debugLine="Return \"z\"";
if (true) return "z";
 }else if((_v).equals("/dhN")) { 
 //BA.debugLineNum = 409;BA.debugLine="Return \"A\"";
if (true) return "A";
 }else if((_v).equals("MBI_")) { 
 //BA.debugLineNum = 411;BA.debugLine="Return \"B\"";
if (true) return "B";
 }else if((_v).equals("FkZz")) { 
 //BA.debugLineNum = 413;BA.debugLine="Return \"C\"";
if (true) return "C";
 }else if((_v).equals("_Wx.")) { 
 //BA.debugLineNum = 415;BA.debugLine="Return \"D\"";
if (true) return "D";
 }else if((_v).equals("L`9s")) { 
 //BA.debugLineNum = 417;BA.debugLine="Return \"E\"";
if (true) return "E";
 }else if((_v).equals("x98&")) { 
 //BA.debugLineNum = 419;BA.debugLine="Return \"F\"";
if (true) return "F";
 }else if((_v).equals("%z/?")) { 
 //BA.debugLineNum = 421;BA.debugLine="Return \"G\"";
if (true) return "G";
 }else if((_v).equals("1glP")) { 
 //BA.debugLineNum = 423;BA.debugLine="Return \"H\"";
if (true) return "H";
 }else if((_v).equals("8OtO")) { 
 //BA.debugLineNum = 425;BA.debugLine="Return \"I\"";
if (true) return "I";
 }else if((_v).equals("B-D_")) { 
 //BA.debugLineNum = 427;BA.debugLine="Return \"J\"";
if (true) return "J";
 }else if((_v).equals(".3b!")) { 
 //BA.debugLineNum = 429;BA.debugLine="Return \"K\"";
if (true) return "K";
 }else if((_v).equals("e2[2")) { 
 //BA.debugLineNum = 431;BA.debugLine="Return \"L\"";
if (true) return "L";
 }else if((_v).equals("DtJ`")) { 
 //BA.debugLineNum = 433;BA.debugLine="Return \"M\"";
if (true) return "M";
 }else if((_v).equals("5t*5")) { 
 //BA.debugLineNum = 435;BA.debugLine="Return \"N\"";
if (true) return "N";
 }else if((_v).equals("UB^~")) { 
 //BA.debugLineNum = 437;BA.debugLine="Return \"O\"";
if (true) return "O";
 }else if((_v).equals("+yKp")) { 
 //BA.debugLineNum = 439;BA.debugLine="Return \"P\"";
if (true) return "P";
 }else if((_v).equals("KUlK")) { 
 //BA.debugLineNum = 441;BA.debugLine="Return \"Q\"";
if (true) return "Q";
 }else if((_v).equals("~odq")) { 
 //BA.debugLineNum = 443;BA.debugLine="Return \"R\"";
if (true) return "R";
 }else if((_v).equals("#qi$")) { 
 //BA.debugLineNum = 445;BA.debugLine="Return \"S\"";
if (true) return "S";
 }else if((_v).equals("$3Qw")) { 
 //BA.debugLineNum = 447;BA.debugLine="Return \"T\"";
if (true) return "T";
 }else if((_v).equals("83x}")) { 
 //BA.debugLineNum = 449;BA.debugLine="Return \"U\"";
if (true) return "U";
 }else if((_v).equals("Inz.")) { 
 //BA.debugLineNum = 451;BA.debugLine="Return \"V\"";
if (true) return "V";
 }else if((_v).equals("$IO{")) { 
 //BA.debugLineNum = 453;BA.debugLine="Return \"W\"";
if (true) return "W";
 }else if((_v).equals(",7&L")) { 
 //BA.debugLineNum = 455;BA.debugLine="Return \"X\"";
if (true) return "X";
 }else if((_v).equals(",fH?")) { 
 //BA.debugLineNum = 457;BA.debugLine="Return \"Y\"";
if (true) return "Y";
 }else if((_v).equals("tj1}")) { 
 //BA.debugLineNum = 459;BA.debugLine="Return \"Z\"";
if (true) return "Z";
 }else if((_v).equals("<^dt")) { 
 //BA.debugLineNum = 461;BA.debugLine="Return \"~\"";
if (true) return "~";
 }else if((_v).equals("Gmp`")) { 
 //BA.debugLineNum = 463;BA.debugLine="Return \"`\"";
if (true) return "`";
 }else if((_v).equals("D=jX")) { 
 //BA.debugLineNum = 465;BA.debugLine="Return \"!\"";
if (true) return "!";
 }else if((_v).equals("F`mE")) { 
 //BA.debugLineNum = 467;BA.debugLine="Return \"#\"";
if (true) return "#";
 }else if((_v).equals("W44?")) { 
 //BA.debugLineNum = 469;BA.debugLine="Return \"$\"";
if (true) return "$";
 }else if((_v).equals("<LG4")) { 
 //BA.debugLineNum = 471;BA.debugLine="Return \"%\"";
if (true) return "%";
 }else if((_v).equals("plD/")) { 
 //BA.debugLineNum = 473;BA.debugLine="Return \"^\"";
if (true) return "^";
 }else if((_v).equals("#uk{")) { 
 //BA.debugLineNum = 475;BA.debugLine="Return \"&\"";
if (true) return "&";
 }else if((_v).equals("L,n[")) { 
 //BA.debugLineNum = 477;BA.debugLine="Return \"*\"";
if (true) return "*";
 }else if((_v).equals(">}(G")) { 
 //BA.debugLineNum = 479;BA.debugLine="Return \"(\"";
if (true) return "(";
 }else if((_v).equals("R}M6")) { 
 //BA.debugLineNum = 481;BA.debugLine="Return \")\"";
if (true) return ")";
 }else if((_v).equals("_`mg")) { 
 //BA.debugLineNum = 483;BA.debugLine="Return \"_\"";
if (true) return "_";
 }else if((_v).equals(".%jm")) { 
 //BA.debugLineNum = 485;BA.debugLine="Return \"-\"";
if (true) return "-";
 }else if((_v).equals("iAs}")) { 
 //BA.debugLineNum = 487;BA.debugLine="Return \"=\"";
if (true) return "=";
 }else if((_v).equals("bGqV")) { 
 //BA.debugLineNum = 489;BA.debugLine="Return \"+\"";
if (true) return "+";
 }else if((_v).equals("[,f[")) { 
 //BA.debugLineNum = 491;BA.debugLine="Return \"/\"";
if (true) return "/";
 }else if((_v).equals("{`6T")) { 
 //BA.debugLineNum = 493;BA.debugLine="Return \"?\"";
if (true) return "?";
 }else if((_v).equals("sXCJ")) { 
 //BA.debugLineNum = 495;BA.debugLine="Return \"<\"";
if (true) return "<";
 }else if((_v).equals("l83=")) { 
 //BA.debugLineNum = 497;BA.debugLine="Return \">\"";
if (true) return ">";
 }else if((_v).equals("%N6I")) { 
 //BA.debugLineNum = 499;BA.debugLine="Return \".\"";
if (true) return ".";
 }else if((_v).equals("IS-4")) { 
 //BA.debugLineNum = 501;BA.debugLine="Return \"{\"";
if (true) return "{";
 }else if((_v).equals("bV?P")) { 
 //BA.debugLineNum = 503;BA.debugLine="Return \"}\"";
if (true) return "}";
 }else if((_v).equals("2Mw}")) { 
 //BA.debugLineNum = 505;BA.debugLine="Return \"[\"";
if (true) return "[";
 }else if((_v).equals("i%Oz")) { 
 //BA.debugLineNum = 507;BA.debugLine="Return \"]\"";
if (true) return "]";
 }else if((_v).equals("J+,7")) { 
 //BA.debugLineNum = 509;BA.debugLine="Return \",\"";
if (true) return ",";
 }else if((_v).equals("1BB^")) { 
 //BA.debugLineNum = 511;BA.debugLine="Return \"'\"";
if (true) return "'";
 }else if((_v).equals("r,W5")) { 
 //BA.debugLineNum = 513;BA.debugLine="Return \"6\"";
if (true) return "6";
 }else {
 //BA.debugLineNum = 515;BA.debugLine="Return v.Substring(v.Length - 1)";
if (true) return _v.substring((int) (_v.length()-1));
 };
 //BA.debugLineNum = 518;BA.debugLine="End Sub";
return "";
}
public static String  _sina_dec_stn2(String _v) throws Exception{
 //BA.debugLineNum = 769;BA.debugLine="Public Sub sina_DEC_stn2(v As String) As String";
 //BA.debugLineNum = 770;BA.debugLine="If v=\"_%\" Then";
if ((_v).equals("_%")) { 
 //BA.debugLineNum = 771;BA.debugLine="Return \"0\"";
if (true) return "0";
 }else if((_v).equals("8i")) { 
 //BA.debugLineNum = 773;BA.debugLine="Return \"1\"";
if (true) return "1";
 }else if((_v).equals("-f")) { 
 //BA.debugLineNum = 775;BA.debugLine="Return \"2\"";
if (true) return "2";
 }else if((_v).equals("?1")) { 
 //BA.debugLineNum = 777;BA.debugLine="Return \"3\"";
if (true) return "3";
 }else if((_v).equals("JM")) { 
 //BA.debugLineNum = 779;BA.debugLine="Return \"4\"";
if (true) return "4";
 }else if((_v).equals("<*")) { 
 //BA.debugLineNum = 781;BA.debugLine="Return \"5\"";
if (true) return "5";
 }else if((_v).equals("qO")) { 
 //BA.debugLineNum = 783;BA.debugLine="Return \"7\"";
if (true) return "7";
 }else if((_v).equals("[/")) { 
 //BA.debugLineNum = 785;BA.debugLine="Return \"8\"";
if (true) return "8";
 }else if((_v).equals("D>")) { 
 //BA.debugLineNum = 787;BA.debugLine="Return \"9\"";
if (true) return "9";
 }else if((_v).equals("JF")) { 
 //BA.debugLineNum = 789;BA.debugLine="Return \"a\"";
if (true) return "a";
 }else if((_v).equals("y/")) { 
 //BA.debugLineNum = 791;BA.debugLine="Return \"b\"";
if (true) return "b";
 }else if((_v).equals("7Q")) { 
 //BA.debugLineNum = 793;BA.debugLine="Return \"c\"";
if (true) return "c";
 }else if((_v).equals("TS")) { 
 //BA.debugLineNum = 795;BA.debugLine="Return \"d\"";
if (true) return "d";
 }else if((_v).equals("zB")) { 
 //BA.debugLineNum = 797;BA.debugLine="Return \"e\"";
if (true) return "e";
 }else if((_v).equals("9c")) { 
 //BA.debugLineNum = 799;BA.debugLine="Return \"f\"";
if (true) return "f";
 }else if((_v).equals("Ig")) { 
 //BA.debugLineNum = 801;BA.debugLine="Return \"g\"";
if (true) return "g";
 }else if((_v).equals("lt")) { 
 //BA.debugLineNum = 803;BA.debugLine="Return \"h\"";
if (true) return "h";
 }else if((_v).equals("Yl")) { 
 //BA.debugLineNum = 805;BA.debugLine="Return \"i\"";
if (true) return "i";
 }else if((_v).equals("&+")) { 
 //BA.debugLineNum = 807;BA.debugLine="Return \"j\"";
if (true) return "j";
 }else if((_v).equals("DT")) { 
 //BA.debugLineNum = 809;BA.debugLine="Return \"k\"";
if (true) return "k";
 }else if((_v).equals("-s")) { 
 //BA.debugLineNum = 811;BA.debugLine="Return \"l\"";
if (true) return "l";
 }else if((_v).equals("MH")) { 
 //BA.debugLineNum = 813;BA.debugLine="Return \"m\"";
if (true) return "m";
 }else if((_v).equals("aC")) { 
 //BA.debugLineNum = 815;BA.debugLine="Return \"n\"";
if (true) return "n";
 }else if((_v).equals("8n")) { 
 //BA.debugLineNum = 817;BA.debugLine="Return \"o\"";
if (true) return "o";
 }else if((_v).equals("J/")) { 
 //BA.debugLineNum = 819;BA.debugLine="Return \"p\"";
if (true) return "p";
 }else if((_v).equals("a%")) { 
 //BA.debugLineNum = 821;BA.debugLine="Return \"q\"";
if (true) return "q";
 }else if((_v).equals("J{")) { 
 //BA.debugLineNum = 823;BA.debugLine="Return \"r\"";
if (true) return "r";
 }else if((_v).equals(")n")) { 
 //BA.debugLineNum = 825;BA.debugLine="Return \"s\"";
if (true) return "s";
 }else if((_v).equals("_Y")) { 
 //BA.debugLineNum = 827;BA.debugLine="Return \"t\"";
if (true) return "t";
 }else if((_v).equals("V7")) { 
 //BA.debugLineNum = 829;BA.debugLine="Return \"u\"";
if (true) return "u";
 }else if((_v).equals("[f")) { 
 //BA.debugLineNum = 831;BA.debugLine="Return \"v\"";
if (true) return "v";
 }else if((_v).equals("2L")) { 
 //BA.debugLineNum = 833;BA.debugLine="Return \"w\"";
if (true) return "w";
 }else if((_v).equals("FM")) { 
 //BA.debugLineNum = 835;BA.debugLine="Return \"x\"";
if (true) return "x";
 }else if((_v).equals("{x")) { 
 //BA.debugLineNum = 837;BA.debugLine="Return \"y\"";
if (true) return "y";
 }else if((_v).equals("_d")) { 
 //BA.debugLineNum = 839;BA.debugLine="Return \"z\"";
if (true) return "z";
 }else if((_v).equals("Of")) { 
 //BA.debugLineNum = 841;BA.debugLine="Return \"A\"";
if (true) return "A";
 }else if((_v).equals("P[")) { 
 //BA.debugLineNum = 843;BA.debugLine="Return \"B\"";
if (true) return "B";
 }else if((_v).equals("b.")) { 
 //BA.debugLineNum = 845;BA.debugLine="Return \"C\"";
if (true) return "C";
 }else if((_v).equals("L1")) { 
 //BA.debugLineNum = 847;BA.debugLine="Return \"D\"";
if (true) return "D";
 }else if((_v).equals("LZ")) { 
 //BA.debugLineNum = 849;BA.debugLine="Return \"E\"";
if (true) return "E";
 }else if((_v).equals("GE")) { 
 //BA.debugLineNum = 851;BA.debugLine="Return \"F\"";
if (true) return "F";
 }else if((_v).equals("rj")) { 
 //BA.debugLineNum = 853;BA.debugLine="Return \"G\"";
if (true) return "G";
 }else if((_v).equals("gG")) { 
 //BA.debugLineNum = 855;BA.debugLine="Return \"H\"";
if (true) return "H";
 }else if((_v).equals("3U")) { 
 //BA.debugLineNum = 857;BA.debugLine="Return \"I\"";
if (true) return "I";
 }else if((_v).equals("aN")) { 
 //BA.debugLineNum = 859;BA.debugLine="Return \"J\"";
if (true) return "J";
 }else if((_v).equals("+&")) { 
 //BA.debugLineNum = 861;BA.debugLine="Return \"K\"";
if (true) return "K";
 }else if((_v).equals("7m")) { 
 //BA.debugLineNum = 863;BA.debugLine="Return \"L\"";
if (true) return "L";
 }else if((_v).equals("o4")) { 
 //BA.debugLineNum = 865;BA.debugLine="Return \"M\"";
if (true) return "M";
 }else if((_v).equals("D]")) { 
 //BA.debugLineNum = 867;BA.debugLine="Return \"N\"";
if (true) return "N";
 }else if((_v).equals("pG")) { 
 //BA.debugLineNum = 869;BA.debugLine="Return \"O\"";
if (true) return "O";
 }else if((_v).equals("2>")) { 
 //BA.debugLineNum = 871;BA.debugLine="Return \"P\"";
if (true) return "P";
 }else if((_v).equals(")D")) { 
 //BA.debugLineNum = 873;BA.debugLine="Return \"Q\"";
if (true) return "Q";
 }else if((_v).equals("zJ")) { 
 //BA.debugLineNum = 875;BA.debugLine="Return \"R\"";
if (true) return "R";
 }else if((_v).equals("+x")) { 
 //BA.debugLineNum = 877;BA.debugLine="Return \"S\"";
if (true) return "S";
 }else if((_v).equals("0L")) { 
 //BA.debugLineNum = 879;BA.debugLine="Return \"T\"";
if (true) return "T";
 }else if((_v).equals("+6")) { 
 //BA.debugLineNum = 881;BA.debugLine="Return \"U\"";
if (true) return "U";
 }else if((_v).equals("bN")) { 
 //BA.debugLineNum = 883;BA.debugLine="Return \"V\"";
if (true) return "V";
 }else if((_v).equals(".E")) { 
 //BA.debugLineNum = 885;BA.debugLine="Return \"W\"";
if (true) return "W";
 }else if((_v).equals("oi")) { 
 //BA.debugLineNum = 887;BA.debugLine="Return \"X\"";
if (true) return "X";
 }else if((_v).equals("b=")) { 
 //BA.debugLineNum = 889;BA.debugLine="Return \"Y\"";
if (true) return "Y";
 }else if((_v).equals("-/")) { 
 //BA.debugLineNum = 891;BA.debugLine="Return \"Z\"";
if (true) return "Z";
 }else if((_v).equals("(6")) { 
 //BA.debugLineNum = 893;BA.debugLine="Return \"~\"";
if (true) return "~";
 }else if((_v).equals("xp")) { 
 //BA.debugLineNum = 895;BA.debugLine="Return \"`\"";
if (true) return "`";
 }else if((_v).equals("UN")) { 
 //BA.debugLineNum = 897;BA.debugLine="Return \"!\"";
if (true) return "!";
 }else if((_v).equals("ha")) { 
 //BA.debugLineNum = 899;BA.debugLine="Return \"#\"";
if (true) return "#";
 }else if((_v).equals("&F")) { 
 //BA.debugLineNum = 901;BA.debugLine="Return \"$\"";
if (true) return "$";
 }else if((_v).equals("[e")) { 
 //BA.debugLineNum = 903;BA.debugLine="Return \"%\"";
if (true) return "%";
 }else if((_v).equals("G9")) { 
 //BA.debugLineNum = 905;BA.debugLine="Return \"^\"";
if (true) return "^";
 }else if((_v).equals("yL")) { 
 //BA.debugLineNum = 907;BA.debugLine="Return \"&\"";
if (true) return "&";
 }else if((_v).equals("In")) { 
 //BA.debugLineNum = 909;BA.debugLine="Return \"*\"";
if (true) return "*";
 }else if((_v).equals("Ca")) { 
 //BA.debugLineNum = 911;BA.debugLine="Return \"(\"";
if (true) return "(";
 }else if((_v).equals("Xg")) { 
 //BA.debugLineNum = 913;BA.debugLine="Return \")\"";
if (true) return ")";
 }else if((_v).equals("IP")) { 
 //BA.debugLineNum = 915;BA.debugLine="Return \"_\"";
if (true) return "_";
 }else if((_v).equals("lG")) { 
 //BA.debugLineNum = 917;BA.debugLine="Return \"-\"";
if (true) return "-";
 }else if((_v).equals("N!")) { 
 //BA.debugLineNum = 919;BA.debugLine="Return \"=\"";
if (true) return "=";
 }else if((_v).equals("OD")) { 
 //BA.debugLineNum = 921;BA.debugLine="Return \"+\"";
if (true) return "+";
 }else if((_v).equals("[c")) { 
 //BA.debugLineNum = 923;BA.debugLine="Return \"/\"";
if (true) return "/";
 }else if((_v).equals("!+")) { 
 //BA.debugLineNum = 925;BA.debugLine="Return \"?\"";
if (true) return "?";
 }else if((_v).equals("7s")) { 
 //BA.debugLineNum = 927;BA.debugLine="Return \"<\"";
if (true) return "<";
 }else if((_v).equals("m#")) { 
 //BA.debugLineNum = 929;BA.debugLine="Return \">\"";
if (true) return ">";
 }else if((_v).equals("=b")) { 
 //BA.debugLineNum = 931;BA.debugLine="Return \".\"";
if (true) return ".";
 }else if((_v).equals("*]")) { 
 //BA.debugLineNum = 933;BA.debugLine="Return \"{\"";
if (true) return "{";
 }else if((_v).equals("wc")) { 
 //BA.debugLineNum = 935;BA.debugLine="Return \"}\"";
if (true) return "}";
 }else if((_v).equals("#G")) { 
 //BA.debugLineNum = 937;BA.debugLine="Return \"[\"";
if (true) return "[";
 }else if((_v).equals("0T")) { 
 //BA.debugLineNum = 939;BA.debugLine="Return \"]\"";
if (true) return "]";
 }else if((_v).equals("<>")) { 
 //BA.debugLineNum = 941;BA.debugLine="Return \",\"";
if (true) return ",";
 }else if((_v).equals("a*")) { 
 //BA.debugLineNum = 943;BA.debugLine="Return \"'\"";
if (true) return "'";
 }else if((_v).equals("rS")) { 
 //BA.debugLineNum = 945;BA.debugLine="Return \"6\"";
if (true) return "6";
 }else {
 //BA.debugLineNum = 947;BA.debugLine="Return v.Substring(v.Length - 1)";
if (true) return _v.substring((int) (_v.length()-1));
 };
 //BA.debugLineNum = 950;BA.debugLine="End Sub";
return "";
}
public static String  _sina_enc_stn1(String _v) throws Exception{
 //BA.debugLineNum = 121;BA.debugLine="Public Sub sina_ENC_stn1(v As String) As String";
 //BA.debugLineNum = 123;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 124;BA.debugLine="Return \"8TrD\"";
if (true) return "8TrD";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 126;BA.debugLine="Return \"dG3I\"";
if (true) return "dG3I";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 128;BA.debugLine="Return \"lKj^\"";
if (true) return "lKj^";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 130;BA.debugLine="Return \"U-Wx\"";
if (true) return "U-Wx";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 132;BA.debugLine="Return \"cq`c\"";
if (true) return "cq`c";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 134;BA.debugLine="Return \"B&Up\"";
if (true) return "B&Up";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 136;BA.debugLine="Return \"3^n?\"";
if (true) return "3^n?";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 138;BA.debugLine="Return \"gT^2\"";
if (true) return "gT^2";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 140;BA.debugLine="Return \"Z+.c\"";
if (true) return "Z+.c";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 142;BA.debugLine="Return \"p].D\"";
if (true) return "p].D";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 144;BA.debugLine="Return \"<Jc)\"";
if (true) return "<Jc)";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 146;BA.debugLine="Return \",<e1\"";
if (true) return ",<e1";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 148;BA.debugLine="Return \"d)]t\"";
if (true) return "d)]t";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 150;BA.debugLine="Return \"Dnrw\"";
if (true) return "Dnrw";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 152;BA.debugLine="Return \"64Sy\"";
if (true) return "64Sy";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 154;BA.debugLine="Return \"evOV\"";
if (true) return "evOV";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 156;BA.debugLine="Return \"W3g&\"";
if (true) return "W3g&";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 158;BA.debugLine="Return \"KUBo\"";
if (true) return "KUBo";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 160;BA.debugLine="Return \"oF])\"";
if (true) return "oF])";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 162;BA.debugLine="Return \"=jQ(\"";
if (true) return "=jQ(";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 164;BA.debugLine="Return \"1BN&\"";
if (true) return "1BN&";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 166;BA.debugLine="Return \"_/!$\"";
if (true) return "_/!$";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 168;BA.debugLine="Return \"BsH/\"";
if (true) return "BsH/";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 170;BA.debugLine="Return \"8T$4\"";
if (true) return "8T$4";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 172;BA.debugLine="Return \",)2]\"";
if (true) return ",)2]";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 174;BA.debugLine="Return \"Jq,N\"";
if (true) return "Jq,N";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 176;BA.debugLine="Return \"{$]T\"";
if (true) return "{$]T";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 178;BA.debugLine="Return \"kGhY\"";
if (true) return "kGhY";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 180;BA.debugLine="Return \"T?W)\"";
if (true) return "T?W)";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 182;BA.debugLine="Return \"}+=p\"";
if (true) return "}+=p";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 184;BA.debugLine="Return \"jEK2\"";
if (true) return "jEK2";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 186;BA.debugLine="Return \"gy**\"";
if (true) return "gy**";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 188;BA.debugLine="Return \"K7fM\"";
if (true) return "K7fM";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 190;BA.debugLine="Return \"VN=%\"";
if (true) return "VN=%";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 192;BA.debugLine="Return \"RC4T\"";
if (true) return "RC4T";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 194;BA.debugLine="Return \"/dhN\"";
if (true) return "/dhN";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 196;BA.debugLine="Return \"MBI_\"";
if (true) return "MBI_";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 198;BA.debugLine="Return \"FkZz\"";
if (true) return "FkZz";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 200;BA.debugLine="Return \"_Wx.\"";
if (true) return "_Wx.";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 202;BA.debugLine="Return \"L`9s\"";
if (true) return "L`9s";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 204;BA.debugLine="Return \"x98&\"";
if (true) return "x98&";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 206;BA.debugLine="Return \"%z/?\"";
if (true) return "%z/?";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 208;BA.debugLine="Return \"1glP\"";
if (true) return "1glP";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 210;BA.debugLine="Return \"8OtO\"";
if (true) return "8OtO";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 212;BA.debugLine="Return \"B-D_\"";
if (true) return "B-D_";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 214;BA.debugLine="Return \".3b!\"";
if (true) return ".3b!";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 216;BA.debugLine="Return \"e2[2\"";
if (true) return "e2[2";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 218;BA.debugLine="Return \"DtJ`\"";
if (true) return "DtJ`";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 220;BA.debugLine="Return \"5t*5\"";
if (true) return "5t*5";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 222;BA.debugLine="Return \"UB^~\"";
if (true) return "UB^~";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 224;BA.debugLine="Return \"+yKp\"";
if (true) return "+yKp";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 226;BA.debugLine="Return \"KUlK\"";
if (true) return "KUlK";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 228;BA.debugLine="Return \"~odq\"";
if (true) return "~odq";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 230;BA.debugLine="Return \"#qi$\"";
if (true) return "#qi$";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 232;BA.debugLine="Return \"$3Qw\"";
if (true) return "$3Qw";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 234;BA.debugLine="Return \"83x}\"";
if (true) return "83x}";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 236;BA.debugLine="Return \"Inz.\"";
if (true) return "Inz.";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 238;BA.debugLine="Return \"$IO{\"";
if (true) return "$IO{";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 240;BA.debugLine="Return \",7&L\"";
if (true) return ",7&L";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 242;BA.debugLine="Return \",fH?\"";
if (true) return ",fH?";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 244;BA.debugLine="Return \"tj1}\"";
if (true) return "tj1}";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 246;BA.debugLine="Return \"<^dt\"";
if (true) return "<^dt";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 248;BA.debugLine="Return \"Gmp`\"";
if (true) return "Gmp`";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 250;BA.debugLine="Return \"D=jX\"";
if (true) return "D=jX";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 252;BA.debugLine="Return \"F`mE\"";
if (true) return "F`mE";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 254;BA.debugLine="Return \"W44?\"";
if (true) return "W44?";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 256;BA.debugLine="Return \"<LG4\"";
if (true) return "<LG4";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 258;BA.debugLine="Return \"plD/\"";
if (true) return "plD/";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 260;BA.debugLine="Return \"#uk{\"";
if (true) return "#uk{";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 262;BA.debugLine="Return \"L,n[\"";
if (true) return "L,n[";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 264;BA.debugLine="Return \">}(G\"";
if (true) return ">}(G";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 266;BA.debugLine="Return \"R}M6\"";
if (true) return "R}M6";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 268;BA.debugLine="Return \"_`mg\"";
if (true) return "_`mg";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 270;BA.debugLine="Return \".%jm\"";
if (true) return ".%jm";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 272;BA.debugLine="Return \"iAs}\"";
if (true) return "iAs}";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 274;BA.debugLine="Return \"bGqV\"";
if (true) return "bGqV";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 276;BA.debugLine="Return \"[,f[\"";
if (true) return "[,f[";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 278;BA.debugLine="Return \"{`6T\"";
if (true) return "{`6T";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 280;BA.debugLine="Return \"sXCJ\"";
if (true) return "sXCJ";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 282;BA.debugLine="Return \"l83=\"";
if (true) return "l83=";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 284;BA.debugLine="Return \"%N6I\"";
if (true) return "%N6I";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 286;BA.debugLine="Return \"IS-4\"";
if (true) return "IS-4";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 288;BA.debugLine="Return \"bV?P\"";
if (true) return "bV?P";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 290;BA.debugLine="Return \"2Mw}\"";
if (true) return "2Mw}";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 292;BA.debugLine="Return \"i%Oz\"";
if (true) return "i%Oz";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 294;BA.debugLine="Return \"J+,7\"";
if (true) return "J+,7";
 }else if((_v).equals("'")) { 
 //BA.debugLineNum = 296;BA.debugLine="Return \"1BB^\"";
if (true) return "1BB^";
 }else if((_v).equals("6")) { 
 //BA.debugLineNum = 298;BA.debugLine="Return \"r,W5\"";
if (true) return "r,W5";
 }else {
 //BA.debugLineNum = 300;BA.debugLine="Return v & v & v & v";
if (true) return _v+_v+_v+_v;
 };
 //BA.debugLineNum = 302;BA.debugLine="End Sub";
return "";
}
public static String  _sina_enc_stn2(String _v) throws Exception{
 //BA.debugLineNum = 553;BA.debugLine="Public Sub sina_ENC_stn2(v As String) As String";
 //BA.debugLineNum = 555;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 556;BA.debugLine="Return \"_%\"";
if (true) return "_%";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 558;BA.debugLine="Return \"8i\"";
if (true) return "8i";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 560;BA.debugLine="Return \"-f\"";
if (true) return "-f";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 562;BA.debugLine="Return \"?1\"";
if (true) return "?1";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 564;BA.debugLine="Return \"JM\"";
if (true) return "JM";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 566;BA.debugLine="Return \"<*\"";
if (true) return "<*";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 568;BA.debugLine="Return \"qO\"";
if (true) return "qO";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 570;BA.debugLine="Return \"[/\"";
if (true) return "[/";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 572;BA.debugLine="Return \"D>\"";
if (true) return "D>";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 574;BA.debugLine="Return \"JF\"";
if (true) return "JF";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 576;BA.debugLine="Return \"y/\"";
if (true) return "y/";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 578;BA.debugLine="Return \"7Q\"";
if (true) return "7Q";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 580;BA.debugLine="Return \"TS\"";
if (true) return "TS";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 582;BA.debugLine="Return \"zB\"";
if (true) return "zB";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 584;BA.debugLine="Return \"9c\"";
if (true) return "9c";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 586;BA.debugLine="Return \"Ig\"";
if (true) return "Ig";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 588;BA.debugLine="Return \"lt\"";
if (true) return "lt";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 590;BA.debugLine="Return \"Yl\"";
if (true) return "Yl";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 592;BA.debugLine="Return \"&+\"";
if (true) return "&+";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 594;BA.debugLine="Return \"DT\"";
if (true) return "DT";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 596;BA.debugLine="Return \"-s\"";
if (true) return "-s";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 598;BA.debugLine="Return \"MH\"";
if (true) return "MH";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 600;BA.debugLine="Return \"aC\"";
if (true) return "aC";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 602;BA.debugLine="Return \"8n\"";
if (true) return "8n";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 604;BA.debugLine="Return \"J/\"";
if (true) return "J/";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 606;BA.debugLine="Return \"a%\"";
if (true) return "a%";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 608;BA.debugLine="Return \"J{\"";
if (true) return "J{";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 610;BA.debugLine="Return \")n\"";
if (true) return ")n";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 612;BA.debugLine="Return \"_Y\"";
if (true) return "_Y";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 614;BA.debugLine="Return \"V7\"";
if (true) return "V7";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 616;BA.debugLine="Return \"[f\"";
if (true) return "[f";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 618;BA.debugLine="Return \"2L\"";
if (true) return "2L";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 620;BA.debugLine="Return \"FM\"";
if (true) return "FM";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 622;BA.debugLine="Return \"{x\"";
if (true) return "{x";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 624;BA.debugLine="Return \"_d\"";
if (true) return "_d";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 626;BA.debugLine="Return \"Of\"";
if (true) return "Of";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 628;BA.debugLine="Return \"P[\"";
if (true) return "P[";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 630;BA.debugLine="Return \"b.\"";
if (true) return "b.";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 632;BA.debugLine="Return \"L1\"";
if (true) return "L1";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 634;BA.debugLine="Return \"LZ\"";
if (true) return "LZ";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 636;BA.debugLine="Return \"GE\"";
if (true) return "GE";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 638;BA.debugLine="Return \"rj\"";
if (true) return "rj";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 640;BA.debugLine="Return \"gG\"";
if (true) return "gG";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 642;BA.debugLine="Return \"3U\"";
if (true) return "3U";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 644;BA.debugLine="Return \"aN\"";
if (true) return "aN";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 646;BA.debugLine="Return \"+&\"";
if (true) return "+&";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 648;BA.debugLine="Return \"7m\"";
if (true) return "7m";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 650;BA.debugLine="Return \"o4\"";
if (true) return "o4";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 652;BA.debugLine="Return \"D]\"";
if (true) return "D]";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 654;BA.debugLine="Return \"pG\"";
if (true) return "pG";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 656;BA.debugLine="Return \"2>\"";
if (true) return "2>";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 658;BA.debugLine="Return \")D\"";
if (true) return ")D";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 660;BA.debugLine="Return \"zJ\"";
if (true) return "zJ";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 662;BA.debugLine="Return \"+x\"";
if (true) return "+x";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 664;BA.debugLine="Return \"0L\"";
if (true) return "0L";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 666;BA.debugLine="Return \"+6\"";
if (true) return "+6";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 668;BA.debugLine="Return \"bN\"";
if (true) return "bN";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 670;BA.debugLine="Return \".E\"";
if (true) return ".E";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 672;BA.debugLine="Return \"oi\"";
if (true) return "oi";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 674;BA.debugLine="Return \"b=\"";
if (true) return "b=";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 676;BA.debugLine="Return \"-/\"";
if (true) return "-/";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 678;BA.debugLine="Return \"(6\"";
if (true) return "(6";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 680;BA.debugLine="Return \"xp\"";
if (true) return "xp";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 682;BA.debugLine="Return \"UN\"";
if (true) return "UN";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 684;BA.debugLine="Return \"ha\"";
if (true) return "ha";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 686;BA.debugLine="Return \"&F\"";
if (true) return "&F";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 688;BA.debugLine="Return \"[e\"";
if (true) return "[e";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 690;BA.debugLine="Return \"G9\"";
if (true) return "G9";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 692;BA.debugLine="Return \"yL\"";
if (true) return "yL";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 694;BA.debugLine="Return \"In\"";
if (true) return "In";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 696;BA.debugLine="Return \"Ca\"";
if (true) return "Ca";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 698;BA.debugLine="Return \"Xg\"";
if (true) return "Xg";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 700;BA.debugLine="Return \"IP\"";
if (true) return "IP";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 702;BA.debugLine="Return \"lG\"";
if (true) return "lG";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 704;BA.debugLine="Return \"N!\"";
if (true) return "N!";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 706;BA.debugLine="Return \"OD\"";
if (true) return "OD";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 708;BA.debugLine="Return \"[c\"";
if (true) return "[c";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 710;BA.debugLine="Return \"!+\"";
if (true) return "!+";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 712;BA.debugLine="Return \"7s\"";
if (true) return "7s";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 714;BA.debugLine="Return \"m#\"";
if (true) return "m#";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 716;BA.debugLine="Return \"=b\"";
if (true) return "=b";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 718;BA.debugLine="Return \"*]\"";
if (true) return "*]";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 720;BA.debugLine="Return \"wc\"";
if (true) return "wc";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 722;BA.debugLine="Return \"#G\"";
if (true) return "#G";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 724;BA.debugLine="Return \"0T\"";
if (true) return "0T";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 726;BA.debugLine="Return \"<>\"";
if (true) return "<>";
 }else if((_v).equals("'")) { 
 //BA.debugLineNum = 728;BA.debugLine="Return \"a*\"";
if (true) return "a*";
 }else if((_v).equals("6")) { 
 //BA.debugLineNum = 730;BA.debugLine="Return \"rS\"";
if (true) return "rS";
 }else {
 //BA.debugLineNum = 732;BA.debugLine="Return v & v";
if (true) return _v+_v;
 };
 //BA.debugLineNum = 734;BA.debugLine="End Sub";
return "";
}
}
