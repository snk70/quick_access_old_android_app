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
public static anywheresoftware.b4a.samples.httputils2.httpjob _ht = null;
public static com.devil.signature.CheckSignature _ch_sign = null;
public static String _edu_clip = "";
public static String _p_key = "";
public static String _p_id = "";
public anywheresoftware.b4a.inappbilling3.BillingManager3 _b_mark = null;
public static int _lng = 0;
public static int _rn = 0;
public b4a.util.BClipboard _clp = null;
public static String _hardware_licence = "";
public static String _software_licence = "";
public anywheresoftware.b4a.objects.EditTextWrapper _software_txt = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_buy = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_exit = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_demo = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_clip = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public Fast_Accessibility.ModernPl.home_act _home_act = null;
public Fast_Accessibility.ModernPl.definition_permissions_act _definition_permissions_act = null;
public Fast_Accessibility.ModernPl.installed_applications _installed_applications = null;
public Fast_Accessibility.ModernPl.setting_parts _setting_parts = null;
public Fast_Accessibility.ModernPl.file_manager _file_manager = null;
public Fast_Accessibility.ModernPl.send_comment_act _send_comment_act = null;
public Fast_Accessibility.ModernPl.contact_act _contact_act = null;
public Fast_Accessibility.ModernPl.check_servis _check_servis = null;
public Fast_Accessibility.ModernPl.selected_contact _selected_contact = null;
public Fast_Accessibility.ModernPl.help_act _help_act = null;
public Fast_Accessibility.ModernPl.searchmodule _searchmodule = null;
public Fast_Accessibility.ModernPl.sizeviewv3 _sizeviewv3 = null;
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
 //BA.debugLineNum = 52;BA.debugLine="If Rnd(0,10)=0 Then";
if (anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (10))==0) { 
 //BA.debugLineNum = 53;BA.debugLine="StartService(check_servis)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._check_servis.getObject()));
 };
 //BA.debugLineNum = 57;BA.debugLine="If File.Exists(File.DirInternal,check_servis.lsn_";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._lsn_path+"/"+mostCurrent._check_servis._lsn_f)==anywheresoftware.b4a.keywords.Common.False && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._rnd_path+"/"+mostCurrent._check_servis._rnd_f)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 60;BA.debugLine="Activity.LoadLayout(\"Pay_Port_Loyout\")";
mostCurrent._activity.LoadLayout("Pay_Port_Loyout",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="size_View301.SetPX(Activity.Width,Activity.Heigh";
mostCurrent._size_view301._setpx(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (240),(float) (320));
 //BA.debugLineNum = 62;BA.debugLine="size_View301.SetPastScreenSizetoInche(3.333,4.44";
mostCurrent._size_view301._setpastscreensizetoinche(mostCurrent.activityBA,(float) (3.333),(float) (4.444));
 //BA.debugLineNum = 63;BA.debugLine="size_View301.SetSize_lbl_Views(lbl_1,18,15,30,20";
mostCurrent._size_view301._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._lbl_1,(float) (18),(float) (15),(float) (30),(float) (207),(float) (14));
 //BA.debugLineNum = 64;BA.debugLine="size_View301.SetSize_lbl_Views(lbl_2,54,15,40,20";
mostCurrent._size_view301._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._lbl_2,(float) (54),(float) (15),(float) (40),(float) (207),(float) (14));
 //BA.debugLineNum = 65;BA.debugLine="size_View301.SetSize_btn(btn_buy,107,32,176,29,1";
mostCurrent._size_view301._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_buy,(float) (107),(float) (32),(float) (176),(float) (29),(float) (10));
 //BA.debugLineNum = 66;BA.debugLine="size_View301.SetSize_btn(btn_clip,147,32,176,29,";
mostCurrent._size_view301._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_clip,(float) (147),(float) (32),(float) (176),(float) (29),(float) (10));
 //BA.debugLineNum = 67;BA.debugLine="size_View301.SetSize_btn(btn_Exit,188,32,176,29,";
mostCurrent._size_view301._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_exit,(float) (188),(float) (32),(float) (176),(float) (29),(float) (10));
 //BA.debugLineNum = 68;BA.debugLine="rn=Rnd(0,10000)";
_rn = anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (10000));
 }else if(anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._lsn_path+"/"+mostCurrent._check_servis._lsn_f)==anywheresoftware.b4a.keywords.Common.True && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._rnd_path+"/"+mostCurrent._check_servis._rnd_f)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 72;BA.debugLine="StartService(check_servis)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._check_servis.getObject()));
 //BA.debugLineNum = 75;BA.debugLine="rn=(File.GetText(File.DirInternal,check_servis.r";
_rn = (int) (((double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.File.GetText(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._rnd_path+"/"+mostCurrent._check_servis._rnd_f)))-3)/(double)2);
 //BA.debugLineNum = 77;BA.debugLine="If Rnd(0,3)=0 Then";
if (anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (3))==0) { 
 //BA.debugLineNum = 78;BA.debugLine="StartActivity(Home_Act)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._home_act.getObject()));
 //BA.debugLineNum = 79;BA.debugLine="StartService(check_servis)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._check_servis.getObject()));
 //BA.debugLineNum = 80;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 82;BA.debugLine="StartActivity(Home_Act)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._home_act.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 }else {
 //BA.debugLineNum = 87;BA.debugLine="HandWork_Licence";
_handwork_licence();
 };
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _bm_billingsupported(boolean _supported,String _message) throws Exception{
String _msl = "";
 //BA.debugLineNum = 1027;BA.debugLine="Sub bm_BillingSupported (Supported As Boolean, Mes";
 //BA.debugLineNum = 1029;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 1031;BA.debugLine="If Supported=True Then";
if (_supported==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1032;BA.debugLine="Dim msl As String=Create_HardLicence(rn,check_se";
_msl = _create_hardlicence(BA.NumberToString(_rn),mostCurrent._check_servis._pi);
 //BA.debugLineNum = 1033;BA.debugLine="b_mark.RequestPayment(P_ID,\"inapp\",msl.SubString";
mostCurrent._b_mark.RequestPayment(processBA,mostCurrent._p_id,"inapp",_msl.substring((int) (0),(int) (((_msl.length()*2)/(double)10)+7)));
 }else {
 //BA.debugLineNum = 1037;BA.debugLine="Msgbox(\"لطفا از نصب آخرین نسخه اپلیکیشن ایران اپ";
anywheresoftware.b4a.keywords.Common.Msgbox("لطفا از نصب آخرین نسخه اپلیکیشن ایران اپس بر روی دستگاه خود مطمئن شوید ، خطای زیر روی داده است :"+anywheresoftware.b4a.keywords.Common.CRLF+_message,"خطا",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 1040;BA.debugLine="End Sub";
return "";
}
public static String  _bm_purchasecompleted(boolean _success,anywheresoftware.b4a.inappbilling3.BillingManager3.Prchase _product) throws Exception{
String _msl = "";
String _soft_license = "";
 //BA.debugLineNum = 1043;BA.debugLine="Sub bm_PurchaseCompleted (Success As Boolean, Prod";
 //BA.debugLineNum = 1045;BA.debugLine="Dim msl As String=Create_HardLicence(rn,check_ser";
_msl = _create_hardlicence(BA.NumberToString(_rn),mostCurrent._check_servis._pi);
 //BA.debugLineNum = 1046;BA.debugLine="msl=msl.SubString2(0,((msl.Length*2)/10)+7)";
_msl = _msl.substring((int) (0),(int) (((_msl.length()*2)/(double)10)+7));
 //BA.debugLineNum = 1047;BA.debugLine="If Success And Product.DeveloperPayload=msl Then";
if (_success && (_product.getDeveloperPayload()).equals(_msl)) { 
 //BA.debugLineNum = 1049;BA.debugLine="Dim soft_License As String=Create_HardLicence(r";
_soft_license = _create_hardlicence(BA.NumberToString(_rn),mostCurrent._check_servis._pi);
 //BA.debugLineNum = 1051;BA.debugLine="File.MakeDir(File.DirInternal,check_servis.rnd_";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._rnd_path);
 //BA.debugLineNum = 1052;BA.debugLine="File.MakeDir(File.DirInternal,check_servis.lsn_";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._lsn_path);
 //BA.debugLineNum = 1053;BA.debugLine="File.WriteString(File.DirInternal,check_servis.";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._lsn_path+"/"+mostCurrent._check_servis._lsn_f,_soft_license.substring((int) (0),(int) (_soft_license.length()/(double)2))+mostCurrent._check_servis._vain_text+_soft_license.substring((int) (_soft_license.length()/(double)2),_soft_license.length()));
 //BA.debugLineNum = 1054;BA.debugLine="File.WriteString(File.DirInternal,check_servis.";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._rnd_path+"/"+mostCurrent._check_servis._rnd_f,BA.NumberToString((_rn*2)+3));
 //BA.debugLineNum = 1056;BA.debugLine="StartActivity(Home_Act)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._home_act.getObject()));
 //BA.debugLineNum = 1057;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 1060;BA.debugLine="Msgbox(\"فرآیند خرید نرم افزار ناموفق بوده است\",";
anywheresoftware.b4a.keywords.Common.Msgbox("فرآیند خرید نرم افزار ناموفق بوده است","خطا",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 1063;BA.debugLine="End Sub";
return "";
}
public static String  _btn_buy_click() throws Exception{
b4a.example.securityinbilling _k1 = null;
 //BA.debugLineNum = 1066;BA.debugLine="Sub btn_buy_Click";
 //BA.debugLineNum = 1067;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید ...\",False";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"لطفا کمی صبر کنید ...",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1069;BA.debugLine="Dim k1 As SecurityInBilling";
_k1 = new b4a.example.securityinbilling();
 //BA.debugLineNum = 1070;BA.debugLine="k1.Initialize";
_k1._initialize(processBA);
 //BA.debugLineNum = 1073;BA.debugLine="If k1.CheckPatcher = True Then";
if (_k1._checkpatcher()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1074;BA.debugLine="Msgbox(\"شما از برنامه هک استفاده میکنید و مجاز";
anywheresoftware.b4a.keywords.Common.Msgbox("شما از برنامه هک استفاده میکنید و مجاز به خرید نرم افزار نیستید !!","مشکل امنیتی",mostCurrent.activityBA);
 //BA.debugLineNum = 1075;BA.debugLine="HandWork_Licence";
_handwork_licence();
 }else {
 //BA.debugLineNum = 1077;BA.debugLine="ht.Initialize(\"check_ht\",Me)";
_ht._initialize(processBA,"check_ht",main.getObject());
 //BA.debugLineNum = 1078;BA.debugLine="ht.PostString(check_servis.WebSite_URL&\"/prv/bd";
_ht._poststring(mostCurrent._check_servis._website_url+"/prv/bdsgsdfgsdfg/dsdfgsdfgsdfgsdfgsdfg/sdfgsdfgsdfgsdfg.php","sina=gh&ya_ali=dfgsdf&gsdfgsdf="+mostCurrent._check_servis._p_sign);
 };
 //BA.debugLineNum = 1080;BA.debugLine="End Sub";
return "";
}
public static String  _btn_clip_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _edu_i = null;
 //BA.debugLineNum = 989;BA.debugLine="Sub btn_clip_Click";
 //BA.debugLineNum = 990;BA.debugLine="Dim edu_i As Intent";
_edu_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 991;BA.debugLine="edu_i.Initialize(edu_i.ACTION_VIEW,edu_CLip)";
_edu_i.Initialize(_edu_i.ACTION_VIEW,mostCurrent._edu_clip);
 //BA.debugLineNum = 992;BA.debugLine="StartActivity(edu_i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_edu_i.getObject()));
 //BA.debugLineNum = 993;BA.debugLine="End Sub";
return "";
}
public static String  _btn_exit_click() throws Exception{
 //BA.debugLineNum = 995;BA.debugLine="Sub btn_Exit_Click";
 //BA.debugLineNum = 996;BA.debugLine="If Msgbox2(\"میخواهید از برنامه خارج شوید ؟\",\"خروج";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("میخواهید از برنامه خارج شوید ؟","خروج","بله","نه","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 997;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 999;BA.debugLine="End Sub";
return "";
}
public static String  _create_hardlicence(String _rand_number,String _p_serial) throws Exception{
anywheresoftware.b4a.phone.Phone _ph = null;
anywheresoftware.b4a.agraham.encryption.Base64 _bs = null;
 //BA.debugLineNum = 104;BA.debugLine="Sub Create_HardLicence(Rand_Number As String,P_Ser";
 //BA.debugLineNum = 106;BA.debugLine="Dim ph As Phone";
_ph = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 107;BA.debugLine="Dim bs As Base64";
_bs = new anywheresoftware.b4a.agraham.encryption.Base64();
 //BA.debugLineNum = 108;BA.debugLine="Hardware_licence=Rand_Number&ph.Product&ph.Manufa";
mostCurrent._hardware_licence = _rand_number+_ph.getProduct()+_ph.getManufacturer()+_ph.getModel()+_ph.GetSettings("android_id");
 //BA.debugLineNum = 109;BA.debugLine="Hardware_licence=bs.EncodeBtoS(Hardware_licence.G";
mostCurrent._hardware_licence = _bs.EncodeBtoS(mostCurrent._hardware_licence.getBytes("UTF8"),(int) (0),mostCurrent._hardware_licence.getBytes("UTF8").length);
 //BA.debugLineNum = 110;BA.debugLine="Hardware_licence=Hardware_licence&P_Serial";
mostCurrent._hardware_licence = mostCurrent._hardware_licence+_p_serial;
 //BA.debugLineNum = 112;BA.debugLine="Hardware_licence= Encryption_stn1(Hardware_licenc";
mostCurrent._hardware_licence = _encryption_stn1(mostCurrent._hardware_licence);
 //BA.debugLineNum = 114;BA.debugLine="Hardware_licence= Hardware_licence.SubString2(Har";
mostCurrent._hardware_licence = mostCurrent._hardware_licence.substring((int) (mostCurrent._hardware_licence.length()-4),mostCurrent._hardware_licence.length())+mostCurrent._hardware_licence.substring((int) (0),(int) (mostCurrent._hardware_licence.length()-4));
 //BA.debugLineNum = 116;BA.debugLine="Return Interchange_stn.Encryption_Query_String_st";
if (true) return mostCurrent._interchange_stn._encryption_query_string_stn(mostCurrent.activityBA,mostCurrent._hardware_licence);
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _decryption_stn1_stn1(String _t) throws Exception{
String _txt = "";
String _tx2 = "";
String[] _cdc = null;
int _n = 0;
String _x = "";
int _b = 0;
 //BA.debugLineNum = 346;BA.debugLine="Public Sub Decryption_stn1_stn1( t As String) As S";
 //BA.debugLineNum = 347;BA.debugLine="lng =4";
_lng = (int) (4);
 //BA.debugLineNum = 350;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 352;BA.debugLine="Dim tx2 As String";
_tx2 = "";
 //BA.debugLineNum = 353;BA.debugLine="Dim cdc(t.Length) As String";
_cdc = new String[_t.length()];
java.util.Arrays.fill(_cdc,"");
 //BA.debugLineNum = 355;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 357;BA.debugLine="For n = 0 To t.Length / lng - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()/(double)_lng-1);
for (_n = (int) (0) ; (step6 > 0 && _n <= limit6) || (step6 < 0 && _n >= limit6); _n = ((int)(0 + _n + step6)) ) {
 //BA.debugLineNum = 358;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 359;BA.debugLine="x = txt.Substring(txt.Length - lng)";
_x = _txt.substring((int) (_txt.length()-_lng));
 //BA.debugLineNum = 360;BA.debugLine="cdc(n) = sina_DEC_stn1_stn1(x)";
_cdc[_n] = _sina_dec_stn1_stn1(_x);
 //BA.debugLineNum = 361;BA.debugLine="txt = txt.SubString2(0, txt.Length - lng)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-_lng));
 }
};
 //BA.debugLineNum = 364;BA.debugLine="For b = 0 To cdc.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cdc.length-1);
for (_b = (int) (0) ; (step12 > 0 && _b <= limit12) || (step12 < 0 && _b >= limit12); _b = ((int)(0 + _b + step12)) ) {
 //BA.debugLineNum = 365;BA.debugLine="tx2 = cdc(b) & tx2";
_tx2 = _cdc[_b]+_tx2;
 }
};
 //BA.debugLineNum = 368;BA.debugLine="Return tx2";
if (true) return _tx2;
 //BA.debugLineNum = 369;BA.debugLine="End Sub";
return "";
}
public static String  _decryption_stn2(String _t) throws Exception{
String _txt = "";
String _tx2 = "";
String[] _cdc = null;
int _n = 0;
String _x = "";
int _b = 0;
 //BA.debugLineNum = 778;BA.debugLine="Public Sub Decryption_stn2( t As String) As String";
 //BA.debugLineNum = 779;BA.debugLine="lng =2";
_lng = (int) (2);
 //BA.debugLineNum = 782;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 784;BA.debugLine="Dim tx2 As String";
_tx2 = "";
 //BA.debugLineNum = 785;BA.debugLine="Dim cdc(t.Length) As String";
_cdc = new String[_t.length()];
java.util.Arrays.fill(_cdc,"");
 //BA.debugLineNum = 787;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 789;BA.debugLine="For n = 0 To t.Length / lng - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()/(double)_lng-1);
for (_n = (int) (0) ; (step6 > 0 && _n <= limit6) || (step6 < 0 && _n >= limit6); _n = ((int)(0 + _n + step6)) ) {
 //BA.debugLineNum = 790;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 791;BA.debugLine="x = txt.Substring(txt.Length - lng)";
_x = _txt.substring((int) (_txt.length()-_lng));
 //BA.debugLineNum = 792;BA.debugLine="cdc(n) = sina_DEC_stn2(x)";
_cdc[_n] = _sina_dec_stn2(_x);
 //BA.debugLineNum = 793;BA.debugLine="txt = txt.SubString2(0, txt.Length - lng)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-_lng));
 }
};
 //BA.debugLineNum = 796;BA.debugLine="For b = 0 To cdc.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cdc.length-1);
for (_b = (int) (0) ; (step12 > 0 && _b <= limit12) || (step12 < 0 && _b >= limit12); _b = ((int)(0 + _b + step12)) ) {
 //BA.debugLineNum = 797;BA.debugLine="tx2 = cdc(b) & tx2";
_tx2 = _cdc[_b]+_tx2;
 }
};
 //BA.debugLineNum = 800;BA.debugLine="Return tx2";
if (true) return _tx2;
 //BA.debugLineNum = 801;BA.debugLine="End Sub";
return "";
}
public static String  _encryption_stn1(String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 129;BA.debugLine="Public Sub Encryption_stn1(t As String) As String";
 //BA.debugLineNum = 131;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 133;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 134;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 135;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 136;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 138;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 139;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 140;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 141;BA.debugLine="cec(i) = sina_ENC_stn1(x)";
_cec[_i] = _sina_enc_stn1(_x);
 //BA.debugLineNum = 142;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 144;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 145;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13); _j = ((int)(0 + _j + step13)) ) {
 //BA.debugLineNum = 146;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 151;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public static String  _encryption_stn2(String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 561;BA.debugLine="Public Sub Encryption_stn2(t As String) As String";
 //BA.debugLineNum = 563;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 565;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 566;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 567;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 568;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 570;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 571;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 572;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 573;BA.debugLine="cec(i) = sina_ENC_stn2(x)";
_cec[_i] = _sina_enc_stn2(_x);
 //BA.debugLineNum = 574;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 576;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 577;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13); _j = ((int)(0 + _j + step13)) ) {
 //BA.debugLineNum = 578;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 583;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 584;BA.debugLine="End Sub";
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
_lng = 0;
 //BA.debugLineNum = 32;BA.debugLine="Dim rn As Int";
_rn = 0;
 //BA.debugLineNum = 33;BA.debugLine="Dim clp As BClipboard";
mostCurrent._clp = new b4a.util.BClipboard();
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
public static String  _handwork_licence() throws Exception{
 //BA.debugLineNum = 120;BA.debugLine="Sub HandWork_Licence";
 //BA.debugLineNum = 121;BA.debugLine="Msgbox(\"لایسنس امنیتی نرم افزار دستکاری شده\",\"مشک";
anywheresoftware.b4a.keywords.Common.Msgbox("لایسنس امنیتی نرم افزار دستکاری شده","مشکل امنیتی",mostCurrent.activityBA);
 //BA.debugLineNum = 122;BA.debugLine="File.Delete(File.DirInternal,check_servis.lsn_Pat";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._lsn_path+"/"+mostCurrent._check_servis._lsn_f);
 //BA.debugLineNum = 123;BA.debugLine="File.Delete(File.DirInternal,check_servis.rnd_Pat";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._check_servis._rnd_path+"/"+mostCurrent._check_servis._rnd_f);
 //BA.debugLineNum = 124;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _msg_title = "";
String _msg_cmt = "";
 //BA.debugLineNum = 1002;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 1007;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 1009;BA.debugLine="If Job.JobName=\"check_ht\" And Job.GetString2(\"AS";
if ((_job._jobname).equals("check_ht") && (_job._getstring2("ASCII")).equals("") == false) { 
 //BA.debugLineNum = 1011;BA.debugLine="If Job.GetString2(\"ASCII\").Replace(\"?\",\"\")=ch_s";
if ((_job._getstring2("ASCII").replace("?","")).equals(_ch_sign.sha1(processBA).replace(":","").toLowerCase())) { 
 //BA.debugLineNum = 1012;BA.debugLine="b_mark.Initialize(\"bm\",p_Key)";
mostCurrent._b_mark.Initialize(processBA,"bm",mostCurrent._p_key);
 }else {
 //BA.debugLineNum = 1014;BA.debugLine="HandWork_Licence";
_handwork_licence();
 };
 };
 }else {
 //BA.debugLineNum = 1019;BA.debugLine="Dim msg_title=\"خطا\",msg_cmt=\"خطایی در حین خرید ن";
_msg_title = "خطا";
_msg_cmt = "خطایی در حین خرید نرم افزار به وجود آمده است"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"لطفا از اتصال به اینترنت و نصب آخرین نسخه نرم افزار بر روی دستگاه خود مطمئن شوید";
 //BA.debugLineNum = 1020;BA.debugLine="Msgbox(msg_cmt,msg_title)";
anywheresoftware.b4a.keywords.Common.Msgbox(_msg_cmt,_msg_title,mostCurrent.activityBA);
 };
 //BA.debugLineNum = 1023;BA.debugLine="End Sub";
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
check_servis._process_globals();
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
_ht = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 17;BA.debugLine="Dim ch_sign As CheckSignature";
_ch_sign = new com.devil.signature.CheckSignature();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _sina_dec_stn1_stn1(String _v) throws Exception{
 //BA.debugLineNum = 372;BA.debugLine="Public Sub sina_DEC_stn1_stn1(v As String) As Stri";
 //BA.debugLineNum = 373;BA.debugLine="If v=\"8TrD\" Then";
if ((_v).equals("8TrD")) { 
 //BA.debugLineNum = 374;BA.debugLine="Return \"0\"";
if (true) return "0";
 }else if((_v).equals("dG3I")) { 
 //BA.debugLineNum = 376;BA.debugLine="Return \"1\"";
if (true) return "1";
 }else if((_v).equals("lKj^")) { 
 //BA.debugLineNum = 378;BA.debugLine="Return \"2\"";
if (true) return "2";
 }else if((_v).equals("U-Wx")) { 
 //BA.debugLineNum = 380;BA.debugLine="Return \"3\"";
if (true) return "3";
 }else if((_v).equals("cq`c")) { 
 //BA.debugLineNum = 382;BA.debugLine="Return \"4\"";
if (true) return "4";
 }else if((_v).equals("B&Up")) { 
 //BA.debugLineNum = 384;BA.debugLine="Return \"5\"";
if (true) return "5";
 }else if((_v).equals("3^n?")) { 
 //BA.debugLineNum = 386;BA.debugLine="Return \"7\"";
if (true) return "7";
 }else if((_v).equals("gT^2")) { 
 //BA.debugLineNum = 388;BA.debugLine="Return \"8\"";
if (true) return "8";
 }else if((_v).equals("Z+.c")) { 
 //BA.debugLineNum = 390;BA.debugLine="Return \"9\"";
if (true) return "9";
 }else if((_v).equals("p].D")) { 
 //BA.debugLineNum = 392;BA.debugLine="Return \"a\"";
if (true) return "a";
 }else if((_v).equals("<Jc)")) { 
 //BA.debugLineNum = 394;BA.debugLine="Return \"b\"";
if (true) return "b";
 }else if((_v).equals(",<e1")) { 
 //BA.debugLineNum = 396;BA.debugLine="Return \"c\"";
if (true) return "c";
 }else if((_v).equals("d)]t")) { 
 //BA.debugLineNum = 398;BA.debugLine="Return \"d\"";
if (true) return "d";
 }else if((_v).equals("Dnrw")) { 
 //BA.debugLineNum = 400;BA.debugLine="Return \"e\"";
if (true) return "e";
 }else if((_v).equals("64Sy")) { 
 //BA.debugLineNum = 402;BA.debugLine="Return \"f\"";
if (true) return "f";
 }else if((_v).equals("evOV")) { 
 //BA.debugLineNum = 404;BA.debugLine="Return \"g\"";
if (true) return "g";
 }else if((_v).equals("W3g&")) { 
 //BA.debugLineNum = 406;BA.debugLine="Return \"h\"";
if (true) return "h";
 }else if((_v).equals("KUBo")) { 
 //BA.debugLineNum = 408;BA.debugLine="Return \"i\"";
if (true) return "i";
 }else if((_v).equals("oF])")) { 
 //BA.debugLineNum = 410;BA.debugLine="Return \"j\"";
if (true) return "j";
 }else if((_v).equals("=jQ(")) { 
 //BA.debugLineNum = 412;BA.debugLine="Return \"k\"";
if (true) return "k";
 }else if((_v).equals("1BN&")) { 
 //BA.debugLineNum = 414;BA.debugLine="Return \"l\"";
if (true) return "l";
 }else if((_v).equals("_/!$")) { 
 //BA.debugLineNum = 416;BA.debugLine="Return \"m\"";
if (true) return "m";
 }else if((_v).equals("BsH/")) { 
 //BA.debugLineNum = 418;BA.debugLine="Return \"n\"";
if (true) return "n";
 }else if((_v).equals("8T$4")) { 
 //BA.debugLineNum = 420;BA.debugLine="Return \"o\"";
if (true) return "o";
 }else if((_v).equals(",)2]")) { 
 //BA.debugLineNum = 422;BA.debugLine="Return \"p\"";
if (true) return "p";
 }else if((_v).equals("Jq,N")) { 
 //BA.debugLineNum = 424;BA.debugLine="Return \"q\"";
if (true) return "q";
 }else if((_v).equals("{$]T")) { 
 //BA.debugLineNum = 426;BA.debugLine="Return \"r\"";
if (true) return "r";
 }else if((_v).equals("kGhY")) { 
 //BA.debugLineNum = 428;BA.debugLine="Return \"s\"";
if (true) return "s";
 }else if((_v).equals("T?W)")) { 
 //BA.debugLineNum = 430;BA.debugLine="Return \"t\"";
if (true) return "t";
 }else if((_v).equals("}+=p")) { 
 //BA.debugLineNum = 432;BA.debugLine="Return \"u\"";
if (true) return "u";
 }else if((_v).equals("jEK2")) { 
 //BA.debugLineNum = 434;BA.debugLine="Return \"v\"";
if (true) return "v";
 }else if((_v).equals("gy**")) { 
 //BA.debugLineNum = 436;BA.debugLine="Return \"w\"";
if (true) return "w";
 }else if((_v).equals("K7fM")) { 
 //BA.debugLineNum = 438;BA.debugLine="Return \"x\"";
if (true) return "x";
 }else if((_v).equals("VN=%")) { 
 //BA.debugLineNum = 440;BA.debugLine="Return \"y\"";
if (true) return "y";
 }else if((_v).equals("RC4T")) { 
 //BA.debugLineNum = 442;BA.debugLine="Return \"z\"";
if (true) return "z";
 }else if((_v).equals("/dhN")) { 
 //BA.debugLineNum = 444;BA.debugLine="Return \"A\"";
if (true) return "A";
 }else if((_v).equals("MBI_")) { 
 //BA.debugLineNum = 446;BA.debugLine="Return \"B\"";
if (true) return "B";
 }else if((_v).equals("FkZz")) { 
 //BA.debugLineNum = 448;BA.debugLine="Return \"C\"";
if (true) return "C";
 }else if((_v).equals("_Wx.")) { 
 //BA.debugLineNum = 450;BA.debugLine="Return \"D\"";
if (true) return "D";
 }else if((_v).equals("L`9s")) { 
 //BA.debugLineNum = 452;BA.debugLine="Return \"E\"";
if (true) return "E";
 }else if((_v).equals("x98&")) { 
 //BA.debugLineNum = 454;BA.debugLine="Return \"F\"";
if (true) return "F";
 }else if((_v).equals("%z/?")) { 
 //BA.debugLineNum = 456;BA.debugLine="Return \"G\"";
if (true) return "G";
 }else if((_v).equals("1glP")) { 
 //BA.debugLineNum = 458;BA.debugLine="Return \"H\"";
if (true) return "H";
 }else if((_v).equals("8OtO")) { 
 //BA.debugLineNum = 460;BA.debugLine="Return \"I\"";
if (true) return "I";
 }else if((_v).equals("B-D_")) { 
 //BA.debugLineNum = 462;BA.debugLine="Return \"J\"";
if (true) return "J";
 }else if((_v).equals(".3b!")) { 
 //BA.debugLineNum = 464;BA.debugLine="Return \"K\"";
if (true) return "K";
 }else if((_v).equals("e2[2")) { 
 //BA.debugLineNum = 466;BA.debugLine="Return \"L\"";
if (true) return "L";
 }else if((_v).equals("DtJ`")) { 
 //BA.debugLineNum = 468;BA.debugLine="Return \"M\"";
if (true) return "M";
 }else if((_v).equals("5t*5")) { 
 //BA.debugLineNum = 470;BA.debugLine="Return \"N\"";
if (true) return "N";
 }else if((_v).equals("UB^~")) { 
 //BA.debugLineNum = 472;BA.debugLine="Return \"O\"";
if (true) return "O";
 }else if((_v).equals("+yKp")) { 
 //BA.debugLineNum = 474;BA.debugLine="Return \"P\"";
if (true) return "P";
 }else if((_v).equals("KUlK")) { 
 //BA.debugLineNum = 476;BA.debugLine="Return \"Q\"";
if (true) return "Q";
 }else if((_v).equals("~odq")) { 
 //BA.debugLineNum = 478;BA.debugLine="Return \"R\"";
if (true) return "R";
 }else if((_v).equals("#qi$")) { 
 //BA.debugLineNum = 480;BA.debugLine="Return \"S\"";
if (true) return "S";
 }else if((_v).equals("$3Qw")) { 
 //BA.debugLineNum = 482;BA.debugLine="Return \"T\"";
if (true) return "T";
 }else if((_v).equals("83x}")) { 
 //BA.debugLineNum = 484;BA.debugLine="Return \"U\"";
if (true) return "U";
 }else if((_v).equals("Inz.")) { 
 //BA.debugLineNum = 486;BA.debugLine="Return \"V\"";
if (true) return "V";
 }else if((_v).equals("$IO{")) { 
 //BA.debugLineNum = 488;BA.debugLine="Return \"W\"";
if (true) return "W";
 }else if((_v).equals(",7&L")) { 
 //BA.debugLineNum = 490;BA.debugLine="Return \"X\"";
if (true) return "X";
 }else if((_v).equals(",fH?")) { 
 //BA.debugLineNum = 492;BA.debugLine="Return \"Y\"";
if (true) return "Y";
 }else if((_v).equals("tj1}")) { 
 //BA.debugLineNum = 494;BA.debugLine="Return \"Z\"";
if (true) return "Z";
 }else if((_v).equals("<^dt")) { 
 //BA.debugLineNum = 496;BA.debugLine="Return \"~\"";
if (true) return "~";
 }else if((_v).equals("Gmp`")) { 
 //BA.debugLineNum = 498;BA.debugLine="Return \"`\"";
if (true) return "`";
 }else if((_v).equals("D=jX")) { 
 //BA.debugLineNum = 500;BA.debugLine="Return \"!\"";
if (true) return "!";
 }else if((_v).equals("F`mE")) { 
 //BA.debugLineNum = 502;BA.debugLine="Return \"#\"";
if (true) return "#";
 }else if((_v).equals("W44?")) { 
 //BA.debugLineNum = 504;BA.debugLine="Return \"$\"";
if (true) return "$";
 }else if((_v).equals("<LG4")) { 
 //BA.debugLineNum = 506;BA.debugLine="Return \"%\"";
if (true) return "%";
 }else if((_v).equals("plD/")) { 
 //BA.debugLineNum = 508;BA.debugLine="Return \"^\"";
if (true) return "^";
 }else if((_v).equals("#uk{")) { 
 //BA.debugLineNum = 510;BA.debugLine="Return \"&\"";
if (true) return "&";
 }else if((_v).equals("L,n[")) { 
 //BA.debugLineNum = 512;BA.debugLine="Return \"*\"";
if (true) return "*";
 }else if((_v).equals(">}(G")) { 
 //BA.debugLineNum = 514;BA.debugLine="Return \"(\"";
if (true) return "(";
 }else if((_v).equals("R}M6")) { 
 //BA.debugLineNum = 516;BA.debugLine="Return \")\"";
if (true) return ")";
 }else if((_v).equals("_`mg")) { 
 //BA.debugLineNum = 518;BA.debugLine="Return \"_\"";
if (true) return "_";
 }else if((_v).equals(".%jm")) { 
 //BA.debugLineNum = 520;BA.debugLine="Return \"-\"";
if (true) return "-";
 }else if((_v).equals("iAs}")) { 
 //BA.debugLineNum = 522;BA.debugLine="Return \"=\"";
if (true) return "=";
 }else if((_v).equals("bGqV")) { 
 //BA.debugLineNum = 524;BA.debugLine="Return \"+\"";
if (true) return "+";
 }else if((_v).equals("[,f[")) { 
 //BA.debugLineNum = 526;BA.debugLine="Return \"/\"";
if (true) return "/";
 }else if((_v).equals("{`6T")) { 
 //BA.debugLineNum = 528;BA.debugLine="Return \"?\"";
if (true) return "?";
 }else if((_v).equals("sXCJ")) { 
 //BA.debugLineNum = 530;BA.debugLine="Return \"<\"";
if (true) return "<";
 }else if((_v).equals("l83=")) { 
 //BA.debugLineNum = 532;BA.debugLine="Return \">\"";
if (true) return ">";
 }else if((_v).equals("%N6I")) { 
 //BA.debugLineNum = 534;BA.debugLine="Return \".\"";
if (true) return ".";
 }else if((_v).equals("IS-4")) { 
 //BA.debugLineNum = 536;BA.debugLine="Return \"{\"";
if (true) return "{";
 }else if((_v).equals("bV?P")) { 
 //BA.debugLineNum = 538;BA.debugLine="Return \"}\"";
if (true) return "}";
 }else if((_v).equals("2Mw}")) { 
 //BA.debugLineNum = 540;BA.debugLine="Return \"[\"";
if (true) return "[";
 }else if((_v).equals("i%Oz")) { 
 //BA.debugLineNum = 542;BA.debugLine="Return \"]\"";
if (true) return "]";
 }else if((_v).equals("J+,7")) { 
 //BA.debugLineNum = 544;BA.debugLine="Return \",\"";
if (true) return ",";
 }else if((_v).equals("1BB^")) { 
 //BA.debugLineNum = 546;BA.debugLine="Return \"'\"";
if (true) return "'";
 }else if((_v).equals("r,W5")) { 
 //BA.debugLineNum = 548;BA.debugLine="Return \"6\"";
if (true) return "6";
 }else {
 //BA.debugLineNum = 550;BA.debugLine="Return v.Substring(v.Length - 1)";
if (true) return _v.substring((int) (_v.length()-1));
 };
 //BA.debugLineNum = 553;BA.debugLine="End Sub";
return "";
}
public static String  _sina_dec_stn2(String _v) throws Exception{
 //BA.debugLineNum = 804;BA.debugLine="Public Sub sina_DEC_stn2(v As String) As String";
 //BA.debugLineNum = 805;BA.debugLine="If v=\"_%\" Then";
if ((_v).equals("_%")) { 
 //BA.debugLineNum = 806;BA.debugLine="Return \"0\"";
if (true) return "0";
 }else if((_v).equals("8i")) { 
 //BA.debugLineNum = 808;BA.debugLine="Return \"1\"";
if (true) return "1";
 }else if((_v).equals("-f")) { 
 //BA.debugLineNum = 810;BA.debugLine="Return \"2\"";
if (true) return "2";
 }else if((_v).equals("?1")) { 
 //BA.debugLineNum = 812;BA.debugLine="Return \"3\"";
if (true) return "3";
 }else if((_v).equals("JM")) { 
 //BA.debugLineNum = 814;BA.debugLine="Return \"4\"";
if (true) return "4";
 }else if((_v).equals("<*")) { 
 //BA.debugLineNum = 816;BA.debugLine="Return \"5\"";
if (true) return "5";
 }else if((_v).equals("qO")) { 
 //BA.debugLineNum = 818;BA.debugLine="Return \"7\"";
if (true) return "7";
 }else if((_v).equals("[/")) { 
 //BA.debugLineNum = 820;BA.debugLine="Return \"8\"";
if (true) return "8";
 }else if((_v).equals("D>")) { 
 //BA.debugLineNum = 822;BA.debugLine="Return \"9\"";
if (true) return "9";
 }else if((_v).equals("JF")) { 
 //BA.debugLineNum = 824;BA.debugLine="Return \"a\"";
if (true) return "a";
 }else if((_v).equals("y/")) { 
 //BA.debugLineNum = 826;BA.debugLine="Return \"b\"";
if (true) return "b";
 }else if((_v).equals("7Q")) { 
 //BA.debugLineNum = 828;BA.debugLine="Return \"c\"";
if (true) return "c";
 }else if((_v).equals("TS")) { 
 //BA.debugLineNum = 830;BA.debugLine="Return \"d\"";
if (true) return "d";
 }else if((_v).equals("zB")) { 
 //BA.debugLineNum = 832;BA.debugLine="Return \"e\"";
if (true) return "e";
 }else if((_v).equals("9c")) { 
 //BA.debugLineNum = 834;BA.debugLine="Return \"f\"";
if (true) return "f";
 }else if((_v).equals("Ig")) { 
 //BA.debugLineNum = 836;BA.debugLine="Return \"g\"";
if (true) return "g";
 }else if((_v).equals("lt")) { 
 //BA.debugLineNum = 838;BA.debugLine="Return \"h\"";
if (true) return "h";
 }else if((_v).equals("Yl")) { 
 //BA.debugLineNum = 840;BA.debugLine="Return \"i\"";
if (true) return "i";
 }else if((_v).equals("&+")) { 
 //BA.debugLineNum = 842;BA.debugLine="Return \"j\"";
if (true) return "j";
 }else if((_v).equals("DT")) { 
 //BA.debugLineNum = 844;BA.debugLine="Return \"k\"";
if (true) return "k";
 }else if((_v).equals("-s")) { 
 //BA.debugLineNum = 846;BA.debugLine="Return \"l\"";
if (true) return "l";
 }else if((_v).equals("MH")) { 
 //BA.debugLineNum = 848;BA.debugLine="Return \"m\"";
if (true) return "m";
 }else if((_v).equals("aC")) { 
 //BA.debugLineNum = 850;BA.debugLine="Return \"n\"";
if (true) return "n";
 }else if((_v).equals("8n")) { 
 //BA.debugLineNum = 852;BA.debugLine="Return \"o\"";
if (true) return "o";
 }else if((_v).equals("J/")) { 
 //BA.debugLineNum = 854;BA.debugLine="Return \"p\"";
if (true) return "p";
 }else if((_v).equals("a%")) { 
 //BA.debugLineNum = 856;BA.debugLine="Return \"q\"";
if (true) return "q";
 }else if((_v).equals("J{")) { 
 //BA.debugLineNum = 858;BA.debugLine="Return \"r\"";
if (true) return "r";
 }else if((_v).equals(")n")) { 
 //BA.debugLineNum = 860;BA.debugLine="Return \"s\"";
if (true) return "s";
 }else if((_v).equals("_Y")) { 
 //BA.debugLineNum = 862;BA.debugLine="Return \"t\"";
if (true) return "t";
 }else if((_v).equals("V7")) { 
 //BA.debugLineNum = 864;BA.debugLine="Return \"u\"";
if (true) return "u";
 }else if((_v).equals("[f")) { 
 //BA.debugLineNum = 866;BA.debugLine="Return \"v\"";
if (true) return "v";
 }else if((_v).equals("2L")) { 
 //BA.debugLineNum = 868;BA.debugLine="Return \"w\"";
if (true) return "w";
 }else if((_v).equals("FM")) { 
 //BA.debugLineNum = 870;BA.debugLine="Return \"x\"";
if (true) return "x";
 }else if((_v).equals("{x")) { 
 //BA.debugLineNum = 872;BA.debugLine="Return \"y\"";
if (true) return "y";
 }else if((_v).equals("_d")) { 
 //BA.debugLineNum = 874;BA.debugLine="Return \"z\"";
if (true) return "z";
 }else if((_v).equals("Of")) { 
 //BA.debugLineNum = 876;BA.debugLine="Return \"A\"";
if (true) return "A";
 }else if((_v).equals("P[")) { 
 //BA.debugLineNum = 878;BA.debugLine="Return \"B\"";
if (true) return "B";
 }else if((_v).equals("b.")) { 
 //BA.debugLineNum = 880;BA.debugLine="Return \"C\"";
if (true) return "C";
 }else if((_v).equals("L1")) { 
 //BA.debugLineNum = 882;BA.debugLine="Return \"D\"";
if (true) return "D";
 }else if((_v).equals("LZ")) { 
 //BA.debugLineNum = 884;BA.debugLine="Return \"E\"";
if (true) return "E";
 }else if((_v).equals("GE")) { 
 //BA.debugLineNum = 886;BA.debugLine="Return \"F\"";
if (true) return "F";
 }else if((_v).equals("rj")) { 
 //BA.debugLineNum = 888;BA.debugLine="Return \"G\"";
if (true) return "G";
 }else if((_v).equals("gG")) { 
 //BA.debugLineNum = 890;BA.debugLine="Return \"H\"";
if (true) return "H";
 }else if((_v).equals("3U")) { 
 //BA.debugLineNum = 892;BA.debugLine="Return \"I\"";
if (true) return "I";
 }else if((_v).equals("aN")) { 
 //BA.debugLineNum = 894;BA.debugLine="Return \"J\"";
if (true) return "J";
 }else if((_v).equals("+&")) { 
 //BA.debugLineNum = 896;BA.debugLine="Return \"K\"";
if (true) return "K";
 }else if((_v).equals("7m")) { 
 //BA.debugLineNum = 898;BA.debugLine="Return \"L\"";
if (true) return "L";
 }else if((_v).equals("o4")) { 
 //BA.debugLineNum = 900;BA.debugLine="Return \"M\"";
if (true) return "M";
 }else if((_v).equals("D]")) { 
 //BA.debugLineNum = 902;BA.debugLine="Return \"N\"";
if (true) return "N";
 }else if((_v).equals("pG")) { 
 //BA.debugLineNum = 904;BA.debugLine="Return \"O\"";
if (true) return "O";
 }else if((_v).equals("2>")) { 
 //BA.debugLineNum = 906;BA.debugLine="Return \"P\"";
if (true) return "P";
 }else if((_v).equals(")D")) { 
 //BA.debugLineNum = 908;BA.debugLine="Return \"Q\"";
if (true) return "Q";
 }else if((_v).equals("zJ")) { 
 //BA.debugLineNum = 910;BA.debugLine="Return \"R\"";
if (true) return "R";
 }else if((_v).equals("+x")) { 
 //BA.debugLineNum = 912;BA.debugLine="Return \"S\"";
if (true) return "S";
 }else if((_v).equals("0L")) { 
 //BA.debugLineNum = 914;BA.debugLine="Return \"T\"";
if (true) return "T";
 }else if((_v).equals("+6")) { 
 //BA.debugLineNum = 916;BA.debugLine="Return \"U\"";
if (true) return "U";
 }else if((_v).equals("bN")) { 
 //BA.debugLineNum = 918;BA.debugLine="Return \"V\"";
if (true) return "V";
 }else if((_v).equals(".E")) { 
 //BA.debugLineNum = 920;BA.debugLine="Return \"W\"";
if (true) return "W";
 }else if((_v).equals("oi")) { 
 //BA.debugLineNum = 922;BA.debugLine="Return \"X\"";
if (true) return "X";
 }else if((_v).equals("b=")) { 
 //BA.debugLineNum = 924;BA.debugLine="Return \"Y\"";
if (true) return "Y";
 }else if((_v).equals("-/")) { 
 //BA.debugLineNum = 926;BA.debugLine="Return \"Z\"";
if (true) return "Z";
 }else if((_v).equals("(6")) { 
 //BA.debugLineNum = 928;BA.debugLine="Return \"~\"";
if (true) return "~";
 }else if((_v).equals("xp")) { 
 //BA.debugLineNum = 930;BA.debugLine="Return \"`\"";
if (true) return "`";
 }else if((_v).equals("UN")) { 
 //BA.debugLineNum = 932;BA.debugLine="Return \"!\"";
if (true) return "!";
 }else if((_v).equals("ha")) { 
 //BA.debugLineNum = 934;BA.debugLine="Return \"#\"";
if (true) return "#";
 }else if((_v).equals("&F")) { 
 //BA.debugLineNum = 936;BA.debugLine="Return \"$\"";
if (true) return "$";
 }else if((_v).equals("[e")) { 
 //BA.debugLineNum = 938;BA.debugLine="Return \"%\"";
if (true) return "%";
 }else if((_v).equals("G9")) { 
 //BA.debugLineNum = 940;BA.debugLine="Return \"^\"";
if (true) return "^";
 }else if((_v).equals("yL")) { 
 //BA.debugLineNum = 942;BA.debugLine="Return \"&\"";
if (true) return "&";
 }else if((_v).equals("In")) { 
 //BA.debugLineNum = 944;BA.debugLine="Return \"*\"";
if (true) return "*";
 }else if((_v).equals("Ca")) { 
 //BA.debugLineNum = 946;BA.debugLine="Return \"(\"";
if (true) return "(";
 }else if((_v).equals("Xg")) { 
 //BA.debugLineNum = 948;BA.debugLine="Return \")\"";
if (true) return ")";
 }else if((_v).equals("IP")) { 
 //BA.debugLineNum = 950;BA.debugLine="Return \"_\"";
if (true) return "_";
 }else if((_v).equals("lG")) { 
 //BA.debugLineNum = 952;BA.debugLine="Return \"-\"";
if (true) return "-";
 }else if((_v).equals("N!")) { 
 //BA.debugLineNum = 954;BA.debugLine="Return \"=\"";
if (true) return "=";
 }else if((_v).equals("OD")) { 
 //BA.debugLineNum = 956;BA.debugLine="Return \"+\"";
if (true) return "+";
 }else if((_v).equals("[c")) { 
 //BA.debugLineNum = 958;BA.debugLine="Return \"/\"";
if (true) return "/";
 }else if((_v).equals("!+")) { 
 //BA.debugLineNum = 960;BA.debugLine="Return \"?\"";
if (true) return "?";
 }else if((_v).equals("7s")) { 
 //BA.debugLineNum = 962;BA.debugLine="Return \"<\"";
if (true) return "<";
 }else if((_v).equals("m#")) { 
 //BA.debugLineNum = 964;BA.debugLine="Return \">\"";
if (true) return ">";
 }else if((_v).equals("=b")) { 
 //BA.debugLineNum = 966;BA.debugLine="Return \".\"";
if (true) return ".";
 }else if((_v).equals("*]")) { 
 //BA.debugLineNum = 968;BA.debugLine="Return \"{\"";
if (true) return "{";
 }else if((_v).equals("wc")) { 
 //BA.debugLineNum = 970;BA.debugLine="Return \"}\"";
if (true) return "}";
 }else if((_v).equals("#G")) { 
 //BA.debugLineNum = 972;BA.debugLine="Return \"[\"";
if (true) return "[";
 }else if((_v).equals("0T")) { 
 //BA.debugLineNum = 974;BA.debugLine="Return \"]\"";
if (true) return "]";
 }else if((_v).equals("<>")) { 
 //BA.debugLineNum = 976;BA.debugLine="Return \",\"";
if (true) return ",";
 }else if((_v).equals("a*")) { 
 //BA.debugLineNum = 978;BA.debugLine="Return \"'\"";
if (true) return "'";
 }else if((_v).equals("rS")) { 
 //BA.debugLineNum = 980;BA.debugLine="Return \"6\"";
if (true) return "6";
 }else {
 //BA.debugLineNum = 982;BA.debugLine="Return v.Substring(v.Length - 1)";
if (true) return _v.substring((int) (_v.length()-1));
 };
 //BA.debugLineNum = 985;BA.debugLine="End Sub";
return "";
}
public static String  _sina_enc_stn1(String _v) throws Exception{
 //BA.debugLineNum = 156;BA.debugLine="Public Sub sina_ENC_stn1(v As String) As String";
 //BA.debugLineNum = 158;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 159;BA.debugLine="Return \"8TrD\"";
if (true) return "8TrD";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 161;BA.debugLine="Return \"dG3I\"";
if (true) return "dG3I";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 163;BA.debugLine="Return \"lKj^\"";
if (true) return "lKj^";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 165;BA.debugLine="Return \"U-Wx\"";
if (true) return "U-Wx";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 167;BA.debugLine="Return \"cq`c\"";
if (true) return "cq`c";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 169;BA.debugLine="Return \"B&Up\"";
if (true) return "B&Up";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 171;BA.debugLine="Return \"3^n?\"";
if (true) return "3^n?";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 173;BA.debugLine="Return \"gT^2\"";
if (true) return "gT^2";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 175;BA.debugLine="Return \"Z+.c\"";
if (true) return "Z+.c";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 177;BA.debugLine="Return \"p].D\"";
if (true) return "p].D";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 179;BA.debugLine="Return \"<Jc)\"";
if (true) return "<Jc)";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 181;BA.debugLine="Return \",<e1\"";
if (true) return ",<e1";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 183;BA.debugLine="Return \"d)]t\"";
if (true) return "d)]t";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 185;BA.debugLine="Return \"Dnrw\"";
if (true) return "Dnrw";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 187;BA.debugLine="Return \"64Sy\"";
if (true) return "64Sy";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 189;BA.debugLine="Return \"evOV\"";
if (true) return "evOV";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 191;BA.debugLine="Return \"W3g&\"";
if (true) return "W3g&";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 193;BA.debugLine="Return \"KUBo\"";
if (true) return "KUBo";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 195;BA.debugLine="Return \"oF])\"";
if (true) return "oF])";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 197;BA.debugLine="Return \"=jQ(\"";
if (true) return "=jQ(";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 199;BA.debugLine="Return \"1BN&\"";
if (true) return "1BN&";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 201;BA.debugLine="Return \"_/!$\"";
if (true) return "_/!$";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 203;BA.debugLine="Return \"BsH/\"";
if (true) return "BsH/";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 205;BA.debugLine="Return \"8T$4\"";
if (true) return "8T$4";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 207;BA.debugLine="Return \",)2]\"";
if (true) return ",)2]";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 209;BA.debugLine="Return \"Jq,N\"";
if (true) return "Jq,N";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 211;BA.debugLine="Return \"{$]T\"";
if (true) return "{$]T";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 213;BA.debugLine="Return \"kGhY\"";
if (true) return "kGhY";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 215;BA.debugLine="Return \"T?W)\"";
if (true) return "T?W)";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 217;BA.debugLine="Return \"}+=p\"";
if (true) return "}+=p";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 219;BA.debugLine="Return \"jEK2\"";
if (true) return "jEK2";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 221;BA.debugLine="Return \"gy**\"";
if (true) return "gy**";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 223;BA.debugLine="Return \"K7fM\"";
if (true) return "K7fM";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 225;BA.debugLine="Return \"VN=%\"";
if (true) return "VN=%";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 227;BA.debugLine="Return \"RC4T\"";
if (true) return "RC4T";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 229;BA.debugLine="Return \"/dhN\"";
if (true) return "/dhN";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 231;BA.debugLine="Return \"MBI_\"";
if (true) return "MBI_";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 233;BA.debugLine="Return \"FkZz\"";
if (true) return "FkZz";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 235;BA.debugLine="Return \"_Wx.\"";
if (true) return "_Wx.";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 237;BA.debugLine="Return \"L`9s\"";
if (true) return "L`9s";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 239;BA.debugLine="Return \"x98&\"";
if (true) return "x98&";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 241;BA.debugLine="Return \"%z/?\"";
if (true) return "%z/?";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 243;BA.debugLine="Return \"1glP\"";
if (true) return "1glP";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 245;BA.debugLine="Return \"8OtO\"";
if (true) return "8OtO";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 247;BA.debugLine="Return \"B-D_\"";
if (true) return "B-D_";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 249;BA.debugLine="Return \".3b!\"";
if (true) return ".3b!";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 251;BA.debugLine="Return \"e2[2\"";
if (true) return "e2[2";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 253;BA.debugLine="Return \"DtJ`\"";
if (true) return "DtJ`";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 255;BA.debugLine="Return \"5t*5\"";
if (true) return "5t*5";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 257;BA.debugLine="Return \"UB^~\"";
if (true) return "UB^~";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 259;BA.debugLine="Return \"+yKp\"";
if (true) return "+yKp";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 261;BA.debugLine="Return \"KUlK\"";
if (true) return "KUlK";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 263;BA.debugLine="Return \"~odq\"";
if (true) return "~odq";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 265;BA.debugLine="Return \"#qi$\"";
if (true) return "#qi$";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 267;BA.debugLine="Return \"$3Qw\"";
if (true) return "$3Qw";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 269;BA.debugLine="Return \"83x}\"";
if (true) return "83x}";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 271;BA.debugLine="Return \"Inz.\"";
if (true) return "Inz.";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 273;BA.debugLine="Return \"$IO{\"";
if (true) return "$IO{";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 275;BA.debugLine="Return \",7&L\"";
if (true) return ",7&L";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 277;BA.debugLine="Return \",fH?\"";
if (true) return ",fH?";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 279;BA.debugLine="Return \"tj1}\"";
if (true) return "tj1}";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 281;BA.debugLine="Return \"<^dt\"";
if (true) return "<^dt";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 283;BA.debugLine="Return \"Gmp`\"";
if (true) return "Gmp`";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 285;BA.debugLine="Return \"D=jX\"";
if (true) return "D=jX";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 287;BA.debugLine="Return \"F`mE\"";
if (true) return "F`mE";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 289;BA.debugLine="Return \"W44?\"";
if (true) return "W44?";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 291;BA.debugLine="Return \"<LG4\"";
if (true) return "<LG4";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 293;BA.debugLine="Return \"plD/\"";
if (true) return "plD/";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 295;BA.debugLine="Return \"#uk{\"";
if (true) return "#uk{";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 297;BA.debugLine="Return \"L,n[\"";
if (true) return "L,n[";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 299;BA.debugLine="Return \">}(G\"";
if (true) return ">}(G";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 301;BA.debugLine="Return \"R}M6\"";
if (true) return "R}M6";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 303;BA.debugLine="Return \"_`mg\"";
if (true) return "_`mg";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 305;BA.debugLine="Return \".%jm\"";
if (true) return ".%jm";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 307;BA.debugLine="Return \"iAs}\"";
if (true) return "iAs}";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 309;BA.debugLine="Return \"bGqV\"";
if (true) return "bGqV";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 311;BA.debugLine="Return \"[,f[\"";
if (true) return "[,f[";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 313;BA.debugLine="Return \"{`6T\"";
if (true) return "{`6T";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 315;BA.debugLine="Return \"sXCJ\"";
if (true) return "sXCJ";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 317;BA.debugLine="Return \"l83=\"";
if (true) return "l83=";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 319;BA.debugLine="Return \"%N6I\"";
if (true) return "%N6I";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 321;BA.debugLine="Return \"IS-4\"";
if (true) return "IS-4";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 323;BA.debugLine="Return \"bV?P\"";
if (true) return "bV?P";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 325;BA.debugLine="Return \"2Mw}\"";
if (true) return "2Mw}";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 327;BA.debugLine="Return \"i%Oz\"";
if (true) return "i%Oz";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 329;BA.debugLine="Return \"J+,7\"";
if (true) return "J+,7";
 }else if((_v).equals("'")) { 
 //BA.debugLineNum = 331;BA.debugLine="Return \"1BB^\"";
if (true) return "1BB^";
 }else if((_v).equals("6")) { 
 //BA.debugLineNum = 333;BA.debugLine="Return \"r,W5\"";
if (true) return "r,W5";
 }else {
 //BA.debugLineNum = 335;BA.debugLine="Return v & v & v & v";
if (true) return _v+_v+_v+_v;
 };
 //BA.debugLineNum = 337;BA.debugLine="End Sub";
return "";
}
public static String  _sina_enc_stn2(String _v) throws Exception{
 //BA.debugLineNum = 588;BA.debugLine="Public Sub sina_ENC_stn2(v As String) As String";
 //BA.debugLineNum = 590;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 591;BA.debugLine="Return \"_%\"";
if (true) return "_%";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 593;BA.debugLine="Return \"8i\"";
if (true) return "8i";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 595;BA.debugLine="Return \"-f\"";
if (true) return "-f";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 597;BA.debugLine="Return \"?1\"";
if (true) return "?1";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 599;BA.debugLine="Return \"JM\"";
if (true) return "JM";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 601;BA.debugLine="Return \"<*\"";
if (true) return "<*";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 603;BA.debugLine="Return \"qO\"";
if (true) return "qO";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 605;BA.debugLine="Return \"[/\"";
if (true) return "[/";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 607;BA.debugLine="Return \"D>\"";
if (true) return "D>";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 609;BA.debugLine="Return \"JF\"";
if (true) return "JF";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 611;BA.debugLine="Return \"y/\"";
if (true) return "y/";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 613;BA.debugLine="Return \"7Q\"";
if (true) return "7Q";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 615;BA.debugLine="Return \"TS\"";
if (true) return "TS";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 617;BA.debugLine="Return \"zB\"";
if (true) return "zB";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 619;BA.debugLine="Return \"9c\"";
if (true) return "9c";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 621;BA.debugLine="Return \"Ig\"";
if (true) return "Ig";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 623;BA.debugLine="Return \"lt\"";
if (true) return "lt";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 625;BA.debugLine="Return \"Yl\"";
if (true) return "Yl";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 627;BA.debugLine="Return \"&+\"";
if (true) return "&+";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 629;BA.debugLine="Return \"DT\"";
if (true) return "DT";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 631;BA.debugLine="Return \"-s\"";
if (true) return "-s";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 633;BA.debugLine="Return \"MH\"";
if (true) return "MH";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 635;BA.debugLine="Return \"aC\"";
if (true) return "aC";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 637;BA.debugLine="Return \"8n\"";
if (true) return "8n";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 639;BA.debugLine="Return \"J/\"";
if (true) return "J/";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 641;BA.debugLine="Return \"a%\"";
if (true) return "a%";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 643;BA.debugLine="Return \"J{\"";
if (true) return "J{";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 645;BA.debugLine="Return \")n\"";
if (true) return ")n";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 647;BA.debugLine="Return \"_Y\"";
if (true) return "_Y";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 649;BA.debugLine="Return \"V7\"";
if (true) return "V7";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 651;BA.debugLine="Return \"[f\"";
if (true) return "[f";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 653;BA.debugLine="Return \"2L\"";
if (true) return "2L";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 655;BA.debugLine="Return \"FM\"";
if (true) return "FM";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 657;BA.debugLine="Return \"{x\"";
if (true) return "{x";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 659;BA.debugLine="Return \"_d\"";
if (true) return "_d";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 661;BA.debugLine="Return \"Of\"";
if (true) return "Of";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 663;BA.debugLine="Return \"P[\"";
if (true) return "P[";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 665;BA.debugLine="Return \"b.\"";
if (true) return "b.";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 667;BA.debugLine="Return \"L1\"";
if (true) return "L1";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 669;BA.debugLine="Return \"LZ\"";
if (true) return "LZ";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 671;BA.debugLine="Return \"GE\"";
if (true) return "GE";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 673;BA.debugLine="Return \"rj\"";
if (true) return "rj";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 675;BA.debugLine="Return \"gG\"";
if (true) return "gG";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 677;BA.debugLine="Return \"3U\"";
if (true) return "3U";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 679;BA.debugLine="Return \"aN\"";
if (true) return "aN";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 681;BA.debugLine="Return \"+&\"";
if (true) return "+&";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 683;BA.debugLine="Return \"7m\"";
if (true) return "7m";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 685;BA.debugLine="Return \"o4\"";
if (true) return "o4";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 687;BA.debugLine="Return \"D]\"";
if (true) return "D]";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 689;BA.debugLine="Return \"pG\"";
if (true) return "pG";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 691;BA.debugLine="Return \"2>\"";
if (true) return "2>";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 693;BA.debugLine="Return \")D\"";
if (true) return ")D";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 695;BA.debugLine="Return \"zJ\"";
if (true) return "zJ";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 697;BA.debugLine="Return \"+x\"";
if (true) return "+x";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 699;BA.debugLine="Return \"0L\"";
if (true) return "0L";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 701;BA.debugLine="Return \"+6\"";
if (true) return "+6";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 703;BA.debugLine="Return \"bN\"";
if (true) return "bN";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 705;BA.debugLine="Return \".E\"";
if (true) return ".E";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 707;BA.debugLine="Return \"oi\"";
if (true) return "oi";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 709;BA.debugLine="Return \"b=\"";
if (true) return "b=";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 711;BA.debugLine="Return \"-/\"";
if (true) return "-/";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 713;BA.debugLine="Return \"(6\"";
if (true) return "(6";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 715;BA.debugLine="Return \"xp\"";
if (true) return "xp";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 717;BA.debugLine="Return \"UN\"";
if (true) return "UN";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 719;BA.debugLine="Return \"ha\"";
if (true) return "ha";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 721;BA.debugLine="Return \"&F\"";
if (true) return "&F";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 723;BA.debugLine="Return \"[e\"";
if (true) return "[e";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 725;BA.debugLine="Return \"G9\"";
if (true) return "G9";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 727;BA.debugLine="Return \"yL\"";
if (true) return "yL";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 729;BA.debugLine="Return \"In\"";
if (true) return "In";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 731;BA.debugLine="Return \"Ca\"";
if (true) return "Ca";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 733;BA.debugLine="Return \"Xg\"";
if (true) return "Xg";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 735;BA.debugLine="Return \"IP\"";
if (true) return "IP";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 737;BA.debugLine="Return \"lG\"";
if (true) return "lG";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 739;BA.debugLine="Return \"N!\"";
if (true) return "N!";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 741;BA.debugLine="Return \"OD\"";
if (true) return "OD";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 743;BA.debugLine="Return \"[c\"";
if (true) return "[c";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 745;BA.debugLine="Return \"!+\"";
if (true) return "!+";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 747;BA.debugLine="Return \"7s\"";
if (true) return "7s";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 749;BA.debugLine="Return \"m#\"";
if (true) return "m#";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 751;BA.debugLine="Return \"=b\"";
if (true) return "=b";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 753;BA.debugLine="Return \"*]\"";
if (true) return "*]";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 755;BA.debugLine="Return \"wc\"";
if (true) return "wc";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 757;BA.debugLine="Return \"#G\"";
if (true) return "#G";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 759;BA.debugLine="Return \"0T\"";
if (true) return "0T";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 761;BA.debugLine="Return \"<>\"";
if (true) return "<>";
 }else if((_v).equals("'")) { 
 //BA.debugLineNum = 763;BA.debugLine="Return \"a*\"";
if (true) return "a*";
 }else if((_v).equals("6")) { 
 //BA.debugLineNum = 765;BA.debugLine="Return \"rS\"";
if (true) return "rS";
 }else {
 //BA.debugLineNum = 767;BA.debugLine="Return v & v";
if (true) return _v+_v;
 };
 //BA.debugLineNum = 769;BA.debugLine="End Sub";
return "";
}
}
