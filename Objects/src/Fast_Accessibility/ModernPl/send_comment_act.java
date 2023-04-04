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

public class send_comment_act extends Activity implements B4AActivity{
	public static send_comment_act mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.send_comment_act");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (send_comment_act).");
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
		activityBA = new BA(this, layout, processBA, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.send_comment_act");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Fast_Accessibility.ModernPl.send_comment_act", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (send_comment_act) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (send_comment_act) Resume **");
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
		return send_comment_act.class;
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
        BA.LogInfo("** Activity (send_comment_act) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (send_comment_act) Resume **");
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
public static String _cmt_table = "";
public static String _website_url = "";
public static String _post_address = "";
public anywheresoftware.b4a.objects.LabelWrapper _contact_label = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _envelope_imageview = null;
public anywheresoftware.b4a.objects.EditTextWrapper _name_txt = null;
public anywheresoftware.b4a.objects.LabelWrapper _name_label = null;
public anywheresoftware.b4a.objects.EditTextWrapper _email_txt = null;
public anywheresoftware.b4a.objects.LabelWrapper _email_label = null;
public anywheresoftware.b4a.objects.EditTextWrapper _comment_txt = null;
public anywheresoftware.b4a.objects.LabelWrapper _comment_label = null;
public anywheresoftware.b4a.objects.ButtonWrapper _send_button = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_view_comments = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvv4 = null;
public Fast_Accessibility.ModernPl.main _vvvv5 = null;
public Fast_Accessibility.ModernPl.home_act _home_act = null;
public Fast_Accessibility.ModernPl.definition_permissions_act _definition_permissions_act = null;
public Fast_Accessibility.ModernPl.installed_applications _installed_applications = null;
public Fast_Accessibility.ModernPl.setting_parts _setting_parts = null;
public Fast_Accessibility.ModernPl.file_manager _file_manager = null;
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
 //BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"Send_Comment_Loyout\")";
mostCurrent._activity.LoadLayout("Send_Comment_Loyout",mostCurrent.activityBA);
 //BA.debugLineNum = 33;BA.debugLine="size_View.SetPX(Activity.Width,Activity.Height,24";
mostCurrent._size_view._vv0(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (240),(float) (320));
 //BA.debugLineNum = 34;BA.debugLine="size_View.SetPastScreenSizetoInche(3.333,4.444)";
mostCurrent._size_view._vv7(mostCurrent.activityBA,(float) (3.333),(float) (4.444));
 //BA.debugLineNum = 36;BA.debugLine="size_View.SetSizeViews(Envelope_ImageView,8,177,4";
mostCurrent._size_view._vvv1(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._envelope_imageview.getObject())),(float) (8),(float) (177),(float) (44),(float) (37),(float) (0));
 //BA.debugLineNum = 37;BA.debugLine="size_View.SetSize_lbl_Views(Contact_Label,15,27,2";
mostCurrent._size_view._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._contact_label,(float) (15),(float) (27),(float) (28),(float) (145),(float) (14));
 //BA.debugLineNum = 38;BA.debugLine="size_View.SetSize_lbl_Views(Name_Label,61,182,30,";
mostCurrent._size_view._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._name_label,(float) (61),(float) (182),(float) (30),(float) (32),(float) (14));
 //BA.debugLineNum = 39;BA.debugLine="size_View.SetSize_lbl_Views(Email_Label,96,182,30";
mostCurrent._size_view._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._email_label,(float) (96),(float) (182),(float) (30),(float) (32),(float) (14));
 //BA.debugLineNum = 40;BA.debugLine="size_View.SetSize_lbl_Views(Comment_Label,182,182";
mostCurrent._size_view._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._comment_label,(float) (182),(float) (182),(float) (30),(float) (32),(float) (14));
 //BA.debugLineNum = 42;BA.debugLine="size_View.SetSize_txt(Name_TXT,63,38,135,30,10)";
mostCurrent._size_view._setsize_txt(mostCurrent.activityBA,mostCurrent._name_txt,(float) (63),(float) (38),(float) (135),(float) (30),(float) (10));
 //BA.debugLineNum = 43;BA.debugLine="size_View.SetSize_txt(Email_TXT,98,38,135,30,10)";
mostCurrent._size_view._setsize_txt(mostCurrent.activityBA,mostCurrent._email_txt,(float) (98),(float) (38),(float) (135),(float) (30),(float) (10));
 //BA.debugLineNum = 44;BA.debugLine="size_View.SetSize_txt(Comment_TXT,135,38,135,90,1";
mostCurrent._size_view._setsize_txt(mostCurrent.activityBA,mostCurrent._comment_txt,(float) (135),(float) (38),(float) (135),(float) (90),(float) (15));
 //BA.debugLineNum = 45;BA.debugLine="size_View.SetSize_btn(Send_Button,234,141,73,30,1";
mostCurrent._size_view._setsize_btn(mostCurrent.activityBA,mostCurrent._send_button,(float) (234),(float) (141),(float) (73),(float) (30),(float) (11));
 //BA.debugLineNum = 46;BA.debugLine="size_View.SetSize_btn(btn_View_Comments,234,38,73";
mostCurrent._size_view._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_view_comments,(float) (234),(float) (38),(float) (73),(float) (30),(float) (11));
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 127;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 128;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvv5.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 130;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _btn_view_comments_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 119;BA.debugLine="Sub btn_View_Comments_Click";
 //BA.debugLineNum = 120;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 121;BA.debugLine="i.Initialize(i.ACTION_VIEW,post_Address)";
_i.Initialize(_i.ACTION_VIEW,_post_address);
 //BA.debugLineNum = 122;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _contact_label_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 59;BA.debugLine="Sub Contact_Label_Click";
 //BA.debugLineNum = 60;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 61;BA.debugLine="i.Initialize(i.ACTION_VIEW,WebSite_URL)";
_i.Initialize(_i.ACTION_VIEW,_website_url);
 //BA.debugLineNum = 62;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _envelope_imageview_click() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub Envelope_ImageView_Click";
 //BA.debugLineNum = 67;BA.debugLine="Contact_Label_Click";
_contact_label_click();
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private Contact_Label As Label";
mostCurrent._contact_label = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private Envelope_ImageView As ImageView";
mostCurrent._envelope_imageview = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private Name_TXT As EditText";
mostCurrent._name_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private Name_Label As Label";
mostCurrent._name_label = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private Email_TXT As EditText";
mostCurrent._email_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Email_Label As Label";
mostCurrent._email_label = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Comment_TXT As EditText";
mostCurrent._comment_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Comment_Label As Label";
mostCurrent._comment_label = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Send_Button As Button";
mostCurrent._send_button = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private btn_View_Comments As Button";
mostCurrent._btn_view_comments = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
 //BA.debugLineNum = 91;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 93;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 94;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 96;BA.debugLine="If Job.GetString2(\"UTF-8\").IndexOf(\"ثبت شد\")<>-1";
if (_job._getstring2("UTF-8").indexOf("ثبت شد")!=-1) { 
 //BA.debugLineNum = 97;BA.debugLine="If	Msgbox2(\"با تشکر از شما\"&CRLF&CRLF&\"نظر شما";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("با تشکر از شما"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"نظر شما ثبت شد و در اسرع وقت بررسی میگردد"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"برای مشاهده سایر دیدگاه ها کلیک کنید","تأیید"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF,"مشاهده سایر دیدگاه ها","فعلا نه","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 98;BA.debugLine="btn_View_Comments_Click";
_btn_view_comments_click();
 //BA.debugLineNum = 99;BA.debugLine="Email_TXT.Text=\"\"";
mostCurrent._email_txt.setText((Object)(""));
 //BA.debugLineNum = 100;BA.debugLine="Name_TXT.Text=\"\"";
mostCurrent._name_txt.setText((Object)(""));
 //BA.debugLineNum = 101;BA.debugLine="Comment_TXT.Text=\"\"";
mostCurrent._comment_txt.setText((Object)(""));
 }else {
 //BA.debugLineNum = 103;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvv5.getObject()));
 //BA.debugLineNum = 104;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 }else {
 //BA.debugLineNum = 107;BA.debugLine="Msgbox(\"متأسفانه مشکلی در هنگام ارسال اطلاعات ب";
anywheresoftware.b4a.keywords.Common.Msgbox("متأسفانه مشکلی در هنگام ارسال اطلاعات به وجود آمده است ، لطفا بعدا دوباره تلاش کنید","خطا",mostCurrent.activityBA);
 };
 }else {
 //BA.debugLineNum = 112;BA.debugLine="Msgbox(\"متأسفانه مشکلی در هنگام ارسال اطلاعات به";
anywheresoftware.b4a.keywords.Common.Msgbox("متأسفانه مشکلی در هنگام ارسال اطلاعات به وجود آمده است ، لطفا بعدا دوباره تلاش کنید","خطا",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim cmt_Table As String=\"14806612140190\"";
_cmt_table = BA.__b (new byte[] {24,54,101,107,6,20,124,126,59,36,109,119,52,58}, 115482);
 //BA.debugLineNum = 10;BA.debugLine="Dim WebSite_URL As String =\"http://www.modernplus";
_website_url = BA.__b (new byte[] {65,118,-12,47,10,13,-65,63,125,103,-82,47,98,110,-6,48,117,106,-79,19,115,41,-1,43}, 264244);
 //BA.debugLineNum = 12;BA.debugLine="Dim post_Address As String=WebSite_URL&\"/\"&\"posts";
_post_address = _website_url+BA.__b (new byte[] {6}, 739624)+BA.__b (new byte[] {89,109,-86,-98,67,13,-81,-100,121,100,-122,-106,110,105,-93,-124,104,115,-26,-70,108,110,-69,-107,47,67,-82,-88}, 338914);
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _send_button_click() throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _ht1 = null;
 //BA.debugLineNum = 71;BA.debugLine="Sub Send_Button_Click";
 //BA.debugLineNum = 73;BA.debugLine="If Name_TXT.Text=\"\" Then";
if ((mostCurrent._name_txt.getText()).equals("")) { 
 //BA.debugLineNum = 74;BA.debugLine="Msgbox(\"لطفا نام خود را وارد نمایید\",\"خطا\")";
anywheresoftware.b4a.keywords.Common.Msgbox("لطفا نام خود را وارد نمایید","خطا",mostCurrent.activityBA);
 }else if((mostCurrent._email_txt.getText()).equals("")) { 
 //BA.debugLineNum = 76;BA.debugLine="Msgbox(\"لطفا ایمیل خود را وارد نمایید\",\"خطا\")";
anywheresoftware.b4a.keywords.Common.Msgbox("لطفا ایمیل خود را وارد نمایید","خطا",mostCurrent.activityBA);
 }else if((mostCurrent._comment_txt.getText()).equals("")) { 
 //BA.debugLineNum = 78;BA.debugLine="Msgbox(\"لطفا پیام خود را وارد نمایید\",\"خطا\")";
anywheresoftware.b4a.keywords.Common.Msgbox("لطفا پیام خود را وارد نمایید","خطا",mostCurrent.activityBA);
 }else if(mostCurrent._regular_validations._email_validation(mostCurrent.activityBA,mostCurrent._email_txt.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 80;BA.debugLine="Msgbox(\"لطفا آدرس ایمیل را صحیح وارد نمایید\",\"خط";
anywheresoftware.b4a.keywords.Common.Msgbox("لطفا آدرس ایمیل را صحیح وارد نمایید","خطا",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 82;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید ...\",Fals";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"لطفا کمی صبر کنید ...",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 83;BA.debugLine="Dim ht1 As HttpJob";
_ht1 = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 84;BA.debugLine="ht1.Initialize(\"ht\",Me)";
_ht1._initialize(processBA,"ht",send_comment_act.getObject());
 //BA.debugLineNum = 86;BA.debugLine="ht1.PostString(WebSite_URL&\"/save_comments/Defau";
_ht1._poststring(_website_url+"/save_comments/Default.aspx","name="+mostCurrent._name_txt.getText()+"&mail="+mostCurrent._email_txt.getText()+"&cmt="+mostCurrent._comment_txt.getText()+"&tb="+_cmt_table);
 };
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
}
