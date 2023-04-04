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

public class file_manager extends Activity implements B4AActivity{
	public static file_manager mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.file_manager");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (file_manager).");
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
		activityBA = new BA(this, layout, processBA, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.file_manager");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Fast_Accessibility.ModernPl.file_manager", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (file_manager) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (file_manager) Resume **");
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
		return file_manager.class;
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
        BA.LogInfo("** Activity (file_manager) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (file_manager) Resume **");
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
public static String _get_path = "";
public ariagp.amin.shahedi.files.AriaFile _vvvvvv5 = null;
public ariagp.amin.shahedi.files.AriaFileUtils _vvvvvv1 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public static String _vvvvvv2 = "";
public ariagp.amin.shahedi.files.AriaFile _vvvvv7 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvv4 = null;
public Fast_Accessibility.ModernPl.main _vvvv5 = null;
public Fast_Accessibility.ModernPl.home_act _home_act = null;
public Fast_Accessibility.ModernPl.definition_permissions_act _definition_permissions_act = null;
public Fast_Accessibility.ModernPl.installed_applications _installed_applications = null;
public Fast_Accessibility.ModernPl.setting_parts _setting_parts = null;
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
 //BA.debugLineNum = 24;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"list\")";
mostCurrent._activity.LoadLayout("list",mostCurrent.activityBA);
 //BA.debugLineNum = 31;BA.debugLine="ListView1.FastScrollEnabled=True";
mostCurrent._listview1.setFastScrollEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 34;BA.debugLine="LoadPathToListView(Get_Path)";
_vvvvv6(_get_path);
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 159;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 165;BA.debugLine="Try";
try { //BA.debugLineNum = 166;BA.debugLine="If LastFileClicked.GetParent==\"/sto";
if ((mostCurrent._vvvvv7.GetParent()).equals("/storage/emulated")) { 
 //BA.debugLineNum = 168;BA.debugLine="StartActivity(Definition_Permissions_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 //BA.debugLineNum = 169;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 173;BA.debugLine="LoadPathToListView(LastFileClicked.Ge";
_vvvvv6(mostCurrent._vvvvv7.GetParent());
 //BA.debugLineNum = 175;BA.debugLine="LastFileClicked=LastFileClicked.GetPa";
mostCurrent._vvvvv7 = mostCurrent._vvvvv7.GetParentFile();
 };
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 178;BA.debugLine="Definition_Permissions_ACT.Current_T";
mostCurrent._definition_permissions_act._current_tab = (int) (0);
 //BA.debugLineNum = 179;BA.debugLine="StartActivity(Definition_Permissions";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 //BA.debugLineNum = 180;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 };
 };
 //BA.debugLineNum = 186;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 37;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvv0() throws Exception{
int _r = 0;
 //BA.debugLineNum = 71;BA.debugLine="Sub Delete";
 //BA.debugLineNum = 72;BA.debugLine="Dim r As Int";
_r = 0;
 //BA.debugLineNum = 73;BA.debugLine="r=Msgbox2(\"Are you sure ?\",\"Delete \"&LastFileCli";
_r = anywheresoftware.b4a.keywords.Common.Msgbox2("Are you sure ?","Delete "+mostCurrent._vvvvv7.GetName(),"DELETE","CANCEL","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 74;BA.debugLine="If r=DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 78;BA.debugLine="If LastFileClicked.IsDirectory Then";
if (mostCurrent._vvvvv7.IsDirectory()) { 
 //BA.debugLineNum = 79;BA.debugLine="FileUtils.DeleteFolder(LastFileClicked)";
mostCurrent._vvvvvv1.DeleteFolder(mostCurrent._vvvvv7);
 }else {
 //BA.debugLineNum = 81;BA.debugLine="LastFileClicked.Delete";
mostCurrent._vvvvv7.Delete();
 };
 //BA.debugLineNum = 83;BA.debugLine="ToastMessageShow(\"Deleted\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Deleted",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 84;BA.debugLine="LoadPathToListView(LastAddressLoaded)";
_vvvvv6(mostCurrent._vvvvvv2);
 };
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv3() throws Exception{
 //BA.debugLineNum = 118;BA.debugLine="Sub Edit";
 //BA.debugLineNum = 120;BA.debugLine="If LastFileClicked.IsFile Then";
if (mostCurrent._vvvvv7.IsFile()) { 
 //BA.debugLineNum = 121;BA.debugLine="Try";
try { //BA.debugLineNum = 123;BA.debugLine="StartActivity(FileUtils.GetIntentForOpenFile(Las";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvv1.GetIntentForOpenFile(mostCurrent._vvvvv7,anywheresoftware.b4a.keywords.Common.False).getObject()));
 } 
       catch (Exception e5) {
			processBA.setLastException(e5); //BA.debugLineNum = 126;BA.debugLine="ToastMessageShow(\"there is no app to handle this";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("there is no app to handle this file",anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
 //BA.debugLineNum = 130;BA.debugLine="ToastMessageShow(\"cant edit directory\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("cant edit directory",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _vvvvvv4(ariagp.amin.shahedi.files.AriaFile _f) throws Exception{
 //BA.debugLineNum = 188;BA.debugLine="Sub GetBitmap(f As AriaFile) As Bitmap";
 //BA.debugLineNum = 190;BA.debugLine="If f.IsDirectory Then";
if (_f.IsDirectory()) { 
 //BA.debugLineNum = 191;BA.debugLine="Return LoadBitmap(File.DirAssets,\"folder.png\")";
if (true) return anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"folder.png");
 }else if(_f.GetMimeType()== null) { 
 //BA.debugLineNum = 193;BA.debugLine="Return LoadBitmap(File.DirAssets,\"doc.png\")";
if (true) return anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doc.png");
 }else if(_f.GetMimeType().startsWith("image")) { 
 //BA.debugLineNum = 195;BA.debugLine="Return LoadBitmap(File.DirAssets,\"pic.png\")";
if (true) return anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pic.png");
 }else if(_f.GetMimeType().startsWith("audio")) { 
 //BA.debugLineNum = 197;BA.debugLine="Return LoadBitmap(File.DirAssets,\"music.png\")";
if (true) return anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"music.png");
 }else {
 //BA.debugLineNum = 199;BA.debugLine="Return LoadBitmap(File.DirAssets,\"doc.png\")";
if (true) return anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doc.png");
 };
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim AriaFile As AriaFile";
mostCurrent._vvvvvv5 = new ariagp.amin.shahedi.files.AriaFile();
 //BA.debugLineNum = 17;BA.debugLine="Dim FileUtils As AriaFileUtils";
mostCurrent._vvvvvv1 = new ariagp.amin.shahedi.files.AriaFileUtils();
 //BA.debugLineNum = 18;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim LastAddressLoaded As String  ' akharin addres";
mostCurrent._vvvvvv2 = "";
 //BA.debugLineNum = 20;BA.debugLine="Dim LastFileClicked As AriaFile  ' akharin file y";
mostCurrent._vvvvv7 = new ariagp.amin.shahedi.files.AriaFile();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 48;BA.debugLine="LastFileClicked=Value";
mostCurrent._vvvvv7.setObject((java.io.File)(_value));
 //BA.debugLineNum = 50;BA.debugLine="OpenFileOrFolder";
_vvvvv4();
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemlongclick(int _position,Object _value) throws Exception{
String _msgtext = "";
int _r = 0;
 //BA.debugLineNum = 53;BA.debugLine="Sub ListView1_ItemLongClick (Position As Int, Valu";
 //BA.debugLineNum = 57;BA.debugLine="LastFileClicked=Value";
mostCurrent._vvvvv7.setObject((java.io.File)(_value));
 //BA.debugLineNum = 59;BA.debugLine="Dim MsgText As String =\"Name :\"&CRLF&\"  \"&LastFil";
_msgtext = "Name :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+mostCurrent._vvvvv7.GetName()+anywheresoftware.b4a.keywords.Common.CRLF+"Size EN :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+mostCurrent._vvvvvv1.CalcSize_EN(mostCurrent._vvvvv7.Size())+anywheresoftware.b4a.keywords.Common.CRLF+"Size FA :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+mostCurrent._vvvvvv1.CalcSize_FA(mostCurrent._vvvvv7.Size())+anywheresoftware.b4a.keywords.Common.CRLF+"Path :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+mostCurrent._vvvvv7.GetPath()+anywheresoftware.b4a.keywords.Common.CRLF+"MimeType :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+mostCurrent._vvvvv7.GetMimeType()+anywheresoftware.b4a.keywords.Common.CRLF+"LastModified :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+anywheresoftware.b4a.keywords.Common.DateTime.Date(mostCurrent._vvvvv7.LastModified())+anywheresoftware.b4a.keywords.Common.CRLF+"IsHidden :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+BA.ObjectToString(mostCurrent._vvvvv7.IsHidden())+anywheresoftware.b4a.keywords.Common.CRLF+"IsDirectory :"+anywheresoftware.b4a.keywords.Common.CRLF+"  "+BA.ObjectToString(mostCurrent._vvvvv7.IsDirectory());
 //BA.debugLineNum = 60;BA.debugLine="Dim r As Int";
_r = 0;
 //BA.debugLineNum = 61;BA.debugLine="r=Msgbox2(MsgText,\"Details\",\"SHARE\",\"EDIT\",\"DELET";
_r = anywheresoftware.b4a.keywords.Common.Msgbox2(_msgtext,"Details","SHARE","EDIT","DELETE",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 62;BA.debugLine="If r=DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 63;BA.debugLine="Share";
_vvvvvv6();
 }else if(_r==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
 //BA.debugLineNum = 65;BA.debugLine="Delete";
_vvvvv0();
 }else if(_r==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 67;BA.debugLine="Edit";
_vvvvvv3();
 };
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvv6(String _path) throws Exception{
ariagp.amin.shahedi.files.AriaFile[] _listfiles = null;
int _i = 0;
 //BA.debugLineNum = 133;BA.debugLine="Sub LoadPathToListView(Path As String)";
 //BA.debugLineNum = 134;BA.debugLine="ListView1.Clear 'listview ro khali mikonim";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 135;BA.debugLine="AriaFile.initialize2(Path) 'ba address dade shode";
mostCurrent._vvvvvv5.initialize2(_path);
 //BA.debugLineNum = 139;BA.debugLine="Dim listfiles() As AriaFile = AriaFile.ListFiles";
_listfiles = mostCurrent._vvvvvv5.ListFiles();
 //BA.debugLineNum = 141;BA.debugLine="For i=0 To AriaFile.ListFiles.Length -1 'be ezae";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._vvvvvv5.ListFiles().length-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 142;BA.debugLine="If listfiles(i).IsDirectory Then 'inja migim age";
if (_listfiles[_i].IsDirectory()) { 
 //BA.debugLineNum = 144;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(listfiles(i).Ge";
mostCurrent._listview1.AddTwoLinesAndBitmap2(_listfiles[_i].GetName(),_listfiles[_i].GetParent(),(android.graphics.Bitmap)(_vvvvvv4(_listfiles[_i]).getObject()),(Object)(_listfiles[_i].getObject()));
 }else {
 //BA.debugLineNum = 147;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(listfiles(i).Ge";
mostCurrent._listview1.AddTwoLinesAndBitmap2(_listfiles[_i].GetName(),mostCurrent._vvvvvv1.CalcSize_EN(_listfiles[_i].Size()),(android.graphics.Bitmap)(_vvvvvv4(_listfiles[_i]).getObject()),(Object)(_listfiles[_i].getObject()));
 };
 }
};
 //BA.debugLineNum = 151;BA.debugLine="LastAddressLoaded=Path 'LastAddressLoaded ro bara";
mostCurrent._vvvvvv2 = _path;
 //BA.debugLineNum = 156;BA.debugLine="Activity.Title=LastAddressLoaded.Replace(\"/storag";
mostCurrent._activity.setTitle((Object)(mostCurrent._vvvvvv2.replace("/storage/emulated/0","SD CARD").replace("/"," > ")));
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvv4() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub OpenFileOrFolder";
 //BA.debugLineNum = 103;BA.debugLine="If LastFileClicked.IsDirectory Then";
if (mostCurrent._vvvvv7.IsDirectory()) { 
 //BA.debugLineNum = 104;BA.debugLine="LoadPathToListView(LastFileClicked.GetPath)";
_vvvvv6(mostCurrent._vvvvv7.GetPath());
 }else {
 //BA.debugLineNum = 107;BA.debugLine="Try";
try { //BA.debugLineNum = 109;BA.debugLine="StartActivity(FileUtils.GetIntentForOpenFile(L";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvv1.GetIntentForOpenFile(mostCurrent._vvvvv7,anywheresoftware.b4a.keywords.Common.True).getObject()));
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 112;BA.debugLine="ToastMessageShow(\"there is no app to handle th";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("there is no app to handle this file",anywheresoftware.b4a.keywords.Common.False);
 };
 };
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim Get_Path As String";
_get_path = "";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv6() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Sub Share";
 //BA.debugLineNum = 91;BA.debugLine="If LastFileClicked.IsFile Then";
if (mostCurrent._vvvvv7.IsFile()) { 
 //BA.debugLineNum = 92;BA.debugLine="StartActivity(FileUtils.GetIntentForShareFile(La";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvv1.GetIntentForShareFile(mostCurrent._vvvvv7,"Share With?").getObject()));
 }else {
 //BA.debugLineNum = 95;BA.debugLine="ToastMessageShow(\"emkane eshterak gozarie direct";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("emkane eshterak gozarie directory nist",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
}
