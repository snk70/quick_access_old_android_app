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

public class definition_permissions_act extends Activity implements B4AActivity{
	public static definition_permissions_act mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.definition_permissions_act");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (definition_permissions_act).");
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
		activityBA = new BA(this, layout, processBA, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.definition_permissions_act");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Fast_Accessibility.ModernPl.definition_permissions_act", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (definition_permissions_act) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (definition_permissions_act) Resume **");
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
		return definition_permissions_act.class;
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
        BA.LogInfo("** Activity (definition_permissions_act) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (definition_permissions_act) Resume **");
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
public static int _current_tab = 0;
public Fast_Accessibility.ModernPl.security_class_list _sc = null;
public anywheresoftware.b4a.objects.collections.List _list_url = null;
public anywheresoftware.b4a.objects.collections.List _list_fname = null;
public anywheresoftware.b4a.objects.collections.List _list_lname = null;
public anywheresoftware.b4a.objects.SlidingMenuWrapper _sld1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_slidemenu = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_homescreen = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_choosedirectory = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_installedapps = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_settingparts = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_contactchoose = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_removepermissions = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_home_slidebar = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_home_slidebar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_lastdefinitionaccessibility_slidebar = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_lastdefinitionaccessibility_slidebar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_help_slidebar = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_help_slidebar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_definition_slidemenu = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_definition_slidemenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_contact_slidemenu = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_contact_slidemenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_comments_slidemenu = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_comments_slidemenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_exit_slidemenu = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_exit_slidemenu = null;
public anywheresoftware.b4a.objects.TabHostWrapper _tabhost1 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide6 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public Fast_Accessibility.ModernPl.main _main = null;
public Fast_Accessibility.ModernPl.home_act _home_act = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.phone.Phone _p1 = null;
 //BA.debugLineNum = 55;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 57;BA.debugLine="If Rnd(0,10)=0 Then";
if (anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (10))==0) { 
 //BA.debugLineNum = 58;BA.debugLine="StartService(check_servis)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._check_servis.getObject()));
 };
 //BA.debugLineNum = 61;BA.debugLine="If check_servis.OK_Status=False Then";
if (mostCurrent._check_servis._ok_status==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 62;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 65;BA.debugLine="Activity.LoadLayout(\"Permissions_ACT\")";
mostCurrent._activity.LoadLayout("Permissions_ACT",mostCurrent.activityBA);
 //BA.debugLineNum = 67;BA.debugLine="ProgressDialogShow(\"لطفا کمی صبر کنید...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"لطفا کمی صبر کنید...");
 //BA.debugLineNum = 69;BA.debugLine="TabHost1.AddTab(\"دسترسی های تعیین شده\",\"LastPermi";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"دسترسی های تعیین شده","LastPermission_Definition");
 //BA.debugLineNum = 71;BA.debugLine="TabHost1.AddTab(\"تعیین دسترسی ها\",\"DefinitionandR";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"تعیین دسترسی ها","DefinitionandRemove_Permission");
 //BA.debugLineNum = 74;BA.debugLine="sizeviewV3.SetPX(Activity.Width-5%x,Activity.Heig";
mostCurrent._sizeviewv3._setpx(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA)),(float) (mostCurrent._activity.getHeight()),(float) (320),(float) (480));
 //BA.debugLineNum = 75;BA.debugLine="sizeviewV3.SetPastScreenSizetoInche(4.444,6.667)";
mostCurrent._sizeviewv3._setpastscreensizetoinche(mostCurrent.activityBA,(float) (4.444),(float) (6.667));
 //BA.debugLineNum = 79;BA.debugLine="ListView1.Top=(60/480)*Activity.Height";
mostCurrent._listview1.setTop((int) ((60/(double)480)*mostCurrent._activity.getHeight()));
 //BA.debugLineNum = 80;BA.debugLine="ListView1.Height=(410/480)*Activity.Height";
mostCurrent._listview1.setHeight((int) ((410/(double)480)*mostCurrent._activity.getHeight()));
 //BA.debugLineNum = 81;BA.debugLine="ListView1.Width=Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 82;BA.debugLine="sizeviewV3.SetSize_txt(EditText1,0,0,50,320,20)";
mostCurrent._sizeviewv3._setsize_txt(mostCurrent.activityBA,mostCurrent._edittext1,(float) (0),(float) (0),(float) (50),(float) (320),(float) (20));
 //BA.debugLineNum = 89;BA.debugLine="sizeviewV3.SetSize_img(img_SlideMenu,5,280,30,30,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_slidemenu,(float) (5),(float) (280),(float) (30),(float) (30),(float) (0));
 //BA.debugLineNum = 90;BA.debugLine="sizeviewV3.SetSize_img(img_HomeScreen,5,10,30,30,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_homescreen,(float) (5),(float) (10),(float) (30),(float) (30),(float) (0));
 //BA.debugLineNum = 91;BA.debugLine="sizeviewV3.SetSize_lbl_Views(Label1,44,10,65,300,";
mostCurrent._sizeviewv3._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._label1,(float) (44),(float) (10),(float) (65),(float) (300),(float) (16));
 //BA.debugLineNum = 92;BA.debugLine="sizeviewV3.SetSize_btn(btn_ChooseDirectory,114,10";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_choosedirectory,(float) (114),(float) (10),(float) (50),(float) (130),(float) (14));
 //BA.debugLineNum = 93;BA.debugLine="sizeviewV3.SetSize_btn(btn_InstalledApps,114,180,";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_installedapps,(float) (114),(float) (180),(float) (50),(float) (130),(float) (14));
 //BA.debugLineNum = 94;BA.debugLine="sizeviewV3.SetSize_btn(btn_SettingPArts,253,180,5";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_settingparts,(float) (253),(float) (180),(float) (50),(float) (130),(float) (14));
 //BA.debugLineNum = 95;BA.debugLine="sizeviewV3.SetSize_btn(btn_ContactChoose,253,10,5";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_contactchoose,(float) (253),(float) (10),(float) (50),(float) (130),(float) (14));
 //BA.debugLineNum = 96;BA.debugLine="sizeviewV3.SetSize_btn(btn_RemovePermissions,173,";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_removepermissions,(float) (173),(float) (100),(float) (70),(float) (96),(float) (14));
 //BA.debugLineNum = 101;BA.debugLine="sizeviewV3.SetPX(Activity.Width,Activity.Height,3";
mostCurrent._sizeviewv3._setpx(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (320),(float) (480));
 //BA.debugLineNum = 104;BA.debugLine="sld1.Initialize(\"\")";
mostCurrent._sld1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 105;BA.debugLine="sld1.Mode=sld1.RIGHT";
mostCurrent._sld1.setMode(mostCurrent._sld1.RIGHT);
 //BA.debugLineNum = 106;BA.debugLine="sld1.BehindOffset=(150/320)*Activity.Width";
mostCurrent._sld1.setBehindOffset((int) ((150/(double)320)*mostCurrent._activity.getWidth()));
 //BA.debugLineNum = 107;BA.debugLine="sld1.Menu.LoadLayout(\"SlideMenu_Loyout\")";
mostCurrent._sld1.getMenu().LoadLayout("SlideMenu_Loyout",mostCurrent.activityBA);
 //BA.debugLineNum = 109;BA.debugLine="sizeviewV3.SetSize_btn(btn_Home_Slidebar,0,0,38,1";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_home_slidebar,(float) (0),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 110;BA.debugLine="sizeviewV3.SetSize_btn(btn_LastDefinitionAccessib";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_lastdefinitionaccessibility_slidebar,(float) (38),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 111;BA.debugLine="sizeviewV3.SetSize_btn(btn_Help_SlideBAr,76,0,38,";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_help_slidebar,(float) (76),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 112;BA.debugLine="sizeviewV3.SetSize_btn(btn_Definition_SlideMenu,1";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_definition_slidemenu,(float) (114),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 113;BA.debugLine="sizeviewV3.SetSize_btn(btn_Contact_SlideMenu,151,";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_contact_slidemenu,(float) (151),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 114;BA.debugLine="sizeviewV3.SetSize_btn(btn_Comments_SlideMenu,188";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_comments_slidemenu,(float) (188),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 115;BA.debugLine="sizeviewV3.SetSize_btn(btn_Exit_SlideMenu,226,0,3";
mostCurrent._sizeviewv3._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_exit_slidemenu,(float) (226),(float) (0),(float) (38),(float) (171),(float) (20));
 //BA.debugLineNum = 117;BA.debugLine="sizeviewV3.SetSize_img(img_Home_SlideBar,2,4,34,3";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_home_slidebar,(float) (2),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 118;BA.debugLine="sizeviewV3.SetSize_img(img_LastDefinitionAccessib";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_lastdefinitionaccessibility_slidebar,(float) (40),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 119;BA.debugLine="sizeviewV3.SetSize_img(img_Help_SlideBAr,78,4,34,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_help_slidebar,(float) (78),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 120;BA.debugLine="sizeviewV3.SetSize_img(img_Definition_SlideMenu,1";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_definition_slidemenu,(float) (116),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 121;BA.debugLine="sizeviewV3.SetSize_img(img_Contact_SlideMenu,153,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_contact_slidemenu,(float) (153),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 122;BA.debugLine="sizeviewV3.SetSize_img(img_Comments_SlideMenu,190";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_comments_slidemenu,(float) (190),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 123;BA.debugLine="sizeviewV3.SetSize_img(img_Exit_SlideMenu,226,4,3";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_exit_slidemenu,(float) (226),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 127;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide1,38,0,1,1";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide1,(float) (38),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 128;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide2,76,0,1,1";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide2,(float) (76),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 129;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide3,114,0,1,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide3,(float) (114),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 130;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide4,151,0,1,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide4,(float) (151),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 131;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide5,188,0,1,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide5,(float) (188),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 132;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide6,226,0,1,";
mostCurrent._sizeviewv3._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide6,(float) (226),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 134;BA.debugLine="img_Border_Slide1.Color=Colors.Red";
mostCurrent._img_border_slide1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 135;BA.debugLine="img_Border_Slide2.Color=Colors.Red";
mostCurrent._img_border_slide2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 136;BA.debugLine="img_Border_Slide3.Color=Colors.Red";
mostCurrent._img_border_slide3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 137;BA.debugLine="img_Border_Slide4.Color=Colors.Red";
mostCurrent._img_border_slide4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 138;BA.debugLine="img_Border_Slide5.Color=Colors.Red";
mostCurrent._img_border_slide5.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 139;BA.debugLine="img_Border_Slide6.Color=Colors.Red";
mostCurrent._img_border_slide6.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 154;BA.debugLine="TabHost1.CurrentTab=Current_Tab";
mostCurrent._tabhost1.setCurrentTab(_current_tab);
 //BA.debugLineNum = 161;BA.debugLine="Dim p1 As Phone";
_p1 = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 162;BA.debugLine="p1.SetScreenOrientation(-1)";
_p1.SetScreenOrientation(processBA,(int) (-1));
 //BA.debugLineNum = 164;BA.debugLine="Filling_ListView'پر کردن لیست ویو";
_filling_listview();
 //BA.debugLineNum = 167;BA.debugLine="If Installed_Applications.Intent_AddedToList_Inst";
if (mostCurrent._installed_applications._intent_addedtolist_installapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 171;BA.debugLine="List_URL.Add(Installed_Applications.IntentApp_UR";
mostCurrent._list_url.Add((Object)(mostCurrent._installed_applications._intentapp_uri));
 //BA.debugLineNum = 172;BA.debugLine="List_Fname.Add(Installed_Applications.IntentApp_";
mostCurrent._list_fname.Add((Object)(mostCurrent._installed_applications._intentapp_fn));
 //BA.debugLineNum = 173;BA.debugLine="List_Lname.Add(\"Application : \"&Installed_Applic";
mostCurrent._list_lname.Add((Object)("Application : "+mostCurrent._installed_applications._intentapp_ln));
 //BA.debugLineNum = 175;BA.debugLine="ToastMessageShow(\"برنامه \"&Installed_Application";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("برنامه "+mostCurrent._installed_applications._intentapp_fn+" اضافه شد",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 176;BA.debugLine="Save_Lists";
_save_lists();
 //BA.debugLineNum = 178;BA.debugLine="Filling_ListView";
_filling_listview();
 //BA.debugLineNum = 180;BA.debugLine="Installed_Applications.Intent_AddedToList_Instal";
mostCurrent._installed_applications._intent_addedtolist_installapp = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 181;BA.debugLine="Installed_Applications.IntentApp_URI=\"\"";
mostCurrent._installed_applications._intentapp_uri = "";
 //BA.debugLineNum = 182;BA.debugLine="Installed_Applications.IntentApp_FN=\"\"";
mostCurrent._installed_applications._intentapp_fn = "";
 //BA.debugLineNum = 183;BA.debugLine="Installed_Applications.IntentApp_LN=\"\"";
mostCurrent._installed_applications._intentapp_ln = "";
 //BA.debugLineNum = 184;BA.debugLine="Installed_Applications.Intent_AddedToList_Instal";
mostCurrent._installed_applications._intent_addedtolist_installapp = anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._setting_parts._intent_added==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 189;BA.debugLine="List_URL.Add(Setting_Parts.Intent_URL)";
mostCurrent._list_url.Add((Object)(mostCurrent._setting_parts._intent_url));
 //BA.debugLineNum = 191;BA.debugLine="List_Fname.Add(Setting_Parts.Intent_FN)";
mostCurrent._list_fname.Add((Object)(mostCurrent._setting_parts._intent_fn));
 //BA.debugLineNum = 193;BA.debugLine="List_Lname.Add(Setting_Parts.Intent_LN)";
mostCurrent._list_lname.Add((Object)(mostCurrent._setting_parts._intent_ln));
 //BA.debugLineNum = 195;BA.debugLine="Setting_Parts.Intent_Added=False";
mostCurrent._setting_parts._intent_added = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 197;BA.debugLine="Save_Lists";
_save_lists();
 //BA.debugLineNum = 198;BA.debugLine="Filling_ListView";
_filling_listview();
 //BA.debugLineNum = 200;BA.debugLine="ToastMessageShow(\" تنظیمات \"&Setting_Parts.Inten";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(" تنظیمات "+mostCurrent._setting_parts._intent_fn+" اضافه شد",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 203;BA.debugLine="Setting_Parts.Intent_Added=False";
mostCurrent._setting_parts._intent_added = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 204;BA.debugLine="Setting_Parts.Intent_FN=\"\"";
mostCurrent._setting_parts._intent_fn = "";
 //BA.debugLineNum = 205;BA.debugLine="Setting_Parts.Intent_LN=\"\"";
mostCurrent._setting_parts._intent_ln = "";
 //BA.debugLineNum = 206;BA.debugLine="Setting_Parts.Intent_URL=\"\"";
mostCurrent._setting_parts._intent_url = "";
 }else if(mostCurrent._selected_contact._contact_added==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 211;BA.debugLine="List_URL.Add(Selected_Contact.Value_Intent)";
mostCurrent._list_url.Add((Object)(mostCurrent._selected_contact._value_intent));
 //BA.debugLineNum = 213;BA.debugLine="List_Fname.Add(Selected_Contact.F_Intent)";
mostCurrent._list_fname.Add((Object)(mostCurrent._selected_contact._f_intent));
 //BA.debugLineNum = 215;BA.debugLine="List_Lname.Add(Selected_Contact.L_Intent)";
mostCurrent._list_lname.Add((Object)(mostCurrent._selected_contact._l_intent));
 //BA.debugLineNum = 217;BA.debugLine="Selected_Contact.contact_Added=False";
mostCurrent._selected_contact._contact_added = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 219;BA.debugLine="ToastMessageShow(\"مخاطب \"&Selected_Contact.F_Int";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("مخاطب "+mostCurrent._selected_contact._f_intent+" اضافه شد",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 221;BA.debugLine="Save_Lists";
_save_lists();
 //BA.debugLineNum = 222;BA.debugLine="Filling_ListView";
_filling_listview();
 };
 //BA.debugLineNum = 228;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 558;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 560;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 562;BA.debugLine="StartActivity(Home_Act)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._home_act.getObject()));
 //BA.debugLineNum = 563;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 567;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 568;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 239;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 236;BA.debugLine="Filling_ListView";
_filling_listview();
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public static String  _btn_choosedirectory_click() throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog.FileDialog _location_dialog = null;
int _location_dialog_response = 0;
anywheresoftware.b4a.agraham.dialogs.InputDialog _msgdialog = null;
 //BA.debugLineNum = 347;BA.debugLine="Sub btn_ChooseDirectory_Click";
 //BA.debugLineNum = 349;BA.debugLine="Dim Location_Dialog As FileDialog";
_location_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog.FileDialog();
 //BA.debugLineNum = 350;BA.debugLine="Location_Dialog.FilePath=File.DirRootExternal";
_location_dialog.setFilePath(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal());
 //BA.debugLineNum = 351;BA.debugLine="Dim Location_Dialog_Response As Int=-5";
_location_dialog_response = (int) (-5);
 //BA.debugLineNum = 353;BA.debugLine="Location_Dialog_Response=Location_Dialog.Show(\"ان";
_location_dialog_response = _location_dialog.Show("انتخاب مسیر فایل یا پوشه","انتخاب پوشه","لغو","انتخاب فایل",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 358;BA.debugLine="If Location_Dialog_Response<>DialogResponse.NEGAT";
if (_location_dialog_response!=anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE && _location_dialog_response!=anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 359;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 364;BA.debugLine="Dim msgdialog As InputDialog";
_msgdialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 365;BA.debugLine="msgdialog.Show(\"\",\"لطفا نام دلخواهی برای دسترسی م";
_msgdialog.Show("","لطفا نام دلخواهی برای دسترسی مورد نظرتان وارد کنید","تأیید","لغو","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 367;BA.debugLine="If msgdialog.Response<>DialogResponse.POSITIVE Th";
if (_msgdialog.getResponse()!=anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 368;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 371;BA.debugLine="If Location_Dialog_Response=DialogResponse.POSITI";
if (_location_dialog_response==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 374;BA.debugLine="List_Fname.Add(msgdialog.Input)";
mostCurrent._list_fname.Add((Object)(_msgdialog.getInput()));
 //BA.debugLineNum = 375;BA.debugLine="List_Lname.Add(Location_Dialog.FilePath)";
mostCurrent._list_lname.Add((Object)(_location_dialog.getFilePath()));
 //BA.debugLineNum = 376;BA.debugLine="List_URL.Add(Location_Dialog.FilePath)";
mostCurrent._list_url.Add((Object)(_location_dialog.getFilePath()));
 //BA.debugLineNum = 377;BA.debugLine="ToastMessageShow(\"مسیر \"&msgdialog.Input&\" اضافه";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("مسیر "+_msgdialog.getInput()+" اضافه شد",anywheresoftware.b4a.keywords.Common.True);
 }else if(_location_dialog_response==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
 //BA.debugLineNum = 381;BA.debugLine="If Location_Dialog.ChosenName=\"\" Then";
if ((_location_dialog.getChosenName()).equals("")) { 
 //BA.debugLineNum = 382;BA.debugLine="List_Fname.Add(msgdialog.Input)";
mostCurrent._list_fname.Add((Object)(_msgdialog.getInput()));
 //BA.debugLineNum = 383;BA.debugLine="List_Lname.Add(Location_Dialog.FilePath)";
mostCurrent._list_lname.Add((Object)(_location_dialog.getFilePath()));
 //BA.debugLineNum = 384;BA.debugLine="List_URL.Add(Location_Dialog.FilePath)";
mostCurrent._list_url.Add((Object)(_location_dialog.getFilePath()));
 //BA.debugLineNum = 385;BA.debugLine="ToastMessageShow(\"مسیر \"&Location_Dialog.FilePa";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("مسیر "+_location_dialog.getFilePath()+" اضافه شد",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 387;BA.debugLine="List_Fname.Add(msgdialog.Input)";
mostCurrent._list_fname.Add((Object)(_msgdialog.getInput()));
 //BA.debugLineNum = 388;BA.debugLine="List_Lname.Add(\"Path : \"&Location_Dialog.FilePa";
mostCurrent._list_lname.Add((Object)("Path : "+_location_dialog.getFilePath()+"/"+_location_dialog.getChosenName()));
 //BA.debugLineNum = 389;BA.debugLine="List_URL.Add(Location_Dialog.FilePath&\"/\"&Locat";
mostCurrent._list_url.Add((Object)(_location_dialog.getFilePath()+"/"+_location_dialog.getChosenName()));
 //BA.debugLineNum = 390;BA.debugLine="ToastMessageShow(\"مسیر \"&Location_Dialog.FilePa";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("مسیر "+_location_dialog.getFilePath()+"/"+_location_dialog.getChosenName()+" اضافه شد",anywheresoftware.b4a.keywords.Common.True);
 };
 };
 //BA.debugLineNum = 393;BA.debugLine="Save_Lists";
_save_lists();
 //BA.debugLineNum = 394;BA.debugLine="Filling_ListView";
_filling_listview();
 //BA.debugLineNum = 396;BA.debugLine="End Sub";
return "";
}
public static String  _btn_comments_slidemenu_click() throws Exception{
 //BA.debugLineNum = 546;BA.debugLine="Sub btn_Comments_SlideMenu_Click";
 //BA.debugLineNum = 547;BA.debugLine="StartActivity(Send_Comment_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._send_comment_act.getObject()));
 //BA.debugLineNum = 548;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 549;BA.debugLine="End Sub";
return "";
}
public static String  _btn_contact_slidemenu_click() throws Exception{
 //BA.debugLineNum = 541;BA.debugLine="Sub btn_Contact_SlideMenu_Click";
 //BA.debugLineNum = 542;BA.debugLine="StartActivity(Contact_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._contact_act.getObject()));
 //BA.debugLineNum = 543;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 544;BA.debugLine="End Sub";
return "";
}
public static String  _btn_contactchoose_click() throws Exception{
 //BA.debugLineNum = 339;BA.debugLine="Sub btn_ContactChoose_Click";
 //BA.debugLineNum = 340;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"لطفا کمی صبر کنید",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 341;BA.debugLine="StartActivity(Selected_Contact)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._selected_contact.getObject()));
 //BA.debugLineNum = 342;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 343;BA.debugLine="End Sub";
return "";
}
public static String  _btn_definition_slidemenu_click() throws Exception{
 //BA.debugLineNum = 537;BA.debugLine="Sub btn_Definition_SlideMenu_Click";
 //BA.debugLineNum = 538;BA.debugLine="sld1.HideMenus";
mostCurrent._sld1.HideMenus();
 //BA.debugLineNum = 539;BA.debugLine="End Sub";
return "";
}
public static String  _btn_exit_slidemenu_click() throws Exception{
 //BA.debugLineNum = 551;BA.debugLine="Sub btn_Exit_SlideMenu_Click";
 //BA.debugLineNum = 552;BA.debugLine="If Msgbox2(\"میخواهید از برنامه خارج شوید ؟\",\"\",\"";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("میخواهید از برنامه خارج شوید ؟","","بله","","نه",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 553;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 555;BA.debugLine="End Sub";
return "";
}
public static String  _btn_help_slidebar_click() throws Exception{
 //BA.debugLineNum = 532;BA.debugLine="Sub btn_Help_SlideBAr_Click";
 //BA.debugLineNum = 533;BA.debugLine="StartActivity(Help_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._help_act.getObject()));
 //BA.debugLineNum = 534;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 535;BA.debugLine="End Sub";
return "";
}
public static String  _btn_home_slidebar_click() throws Exception{
 //BA.debugLineNum = 522;BA.debugLine="Sub btn_Home_Slidebar_Click";
 //BA.debugLineNum = 523;BA.debugLine="StartActivity(Home_Act)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._home_act.getObject()));
 //BA.debugLineNum = 524;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 525;BA.debugLine="End Sub";
return "";
}
public static String  _btn_installedapps_click() throws Exception{
 //BA.debugLineNum = 398;BA.debugLine="Sub btn_InstalledApps_Click";
 //BA.debugLineNum = 399;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید ...\",False";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"لطفا کمی صبر کنید ...",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 400;BA.debugLine="StartActivity(Installed_Applications)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._installed_applications.getObject()));
 //BA.debugLineNum = 401;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 404;BA.debugLine="End Sub";
return "";
}
public static String  _btn_lastdefinitionaccessibility_slidebar_click() throws Exception{
 //BA.debugLineNum = 527;BA.debugLine="Sub btn_LastDefinitionAccessibility_SlideBar_Click";
 //BA.debugLineNum = 528;BA.debugLine="TabHost1.CurrentTab=0";
mostCurrent._tabhost1.setCurrentTab((int) (0));
 //BA.debugLineNum = 529;BA.debugLine="sld1.HideMenus";
mostCurrent._sld1.HideMenus();
 //BA.debugLineNum = 530;BA.debugLine="End Sub";
return "";
}
public static String  _btn_removepermissions_click() throws Exception{
int _int1 = 0;
String _str = "";
 //BA.debugLineNum = 411;BA.debugLine="Sub btn_RemovePermissions_Click";
 //BA.debugLineNum = 413;BA.debugLine="Dim int1 As Int=InputList(List_Fname,\"دسترسی مورد";
_int1 = anywheresoftware.b4a.keywords.Common.InputList(mostCurrent._list_fname,"دسترسی مورد نظر را حذف نمائید",(int) (-1),mostCurrent.activityBA);
 //BA.debugLineNum = 414;BA.debugLine="If int1<0 Then";
if (_int1<0) { 
 //BA.debugLineNum = 415;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 418;BA.debugLine="Dim str As String=List_Fname.Get(int1)";
_str = BA.ObjectToString(mostCurrent._list_fname.Get(_int1));
 //BA.debugLineNum = 420;BA.debugLine="If Msgbox2(\"میخواهید دسترسی \"&str&\" را حذف کنید ؟";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("میخواهید دسترسی "+_str+" را حذف کنید ؟","","بله","","نه",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 422;BA.debugLine="List_URL.RemoveAt(int1)";
mostCurrent._list_url.RemoveAt(_int1);
 //BA.debugLineNum = 424;BA.debugLine="List_Fname.RemoveAt(int1)";
mostCurrent._list_fname.RemoveAt(_int1);
 //BA.debugLineNum = 426;BA.debugLine="List_Lname.RemoveAt(int1)";
mostCurrent._list_lname.RemoveAt(_int1);
 //BA.debugLineNum = 428;BA.debugLine="Save_Lists";
_save_lists();
 //BA.debugLineNum = 429;BA.debugLine="ToastMessageShow(\"دسترسی \"&str&\" حذف شد\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("دسترسی "+_str+" حذف شد",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 431;BA.debugLine="Filling_ListView";
_filling_listview();
 };
 //BA.debugLineNum = 439;BA.debugLine="End Sub";
return "";
}
public static String  _btn_settingparts_click() throws Exception{
 //BA.debugLineNum = 406;BA.debugLine="Sub btn_SettingPArts_Click";
 //BA.debugLineNum = 407;BA.debugLine="StartActivity(Setting_Parts)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._setting_parts.getObject()));
 //BA.debugLineNum = 408;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 409;BA.debugLine="End Sub";
return "";
}
public static String  _edittext1_textchanged(String _old,String _new) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _i = 0;
int _nm = 0;
 //BA.debugLineNum = 571;BA.debugLine="Sub EditText1_TextChanged (Old As String, New As S";
 //BA.debugLineNum = 572;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 573;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 574;BA.debugLine="lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 575;BA.debugLine="lst=SearchModule.Searching_NumberResult(List_Fname";
_lst = mostCurrent._searchmodule._searching_numberresult(mostCurrent.activityBA,mostCurrent._list_fname,_new);
 //BA.debugLineNum = 577;BA.debugLine="For i=0 To lst.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (_lst.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 579;BA.debugLine="Dim nm As Int=lst.Get(i)";
_nm = (int)(BA.ObjectToNumber(_lst.Get(_i)));
 //BA.debugLineNum = 581;BA.debugLine="ListView1.AddTwoLines2(\"             \"&List_Fnam";
mostCurrent._listview1.AddTwoLines2("             "+BA.ObjectToString(mostCurrent._list_fname.Get(_nm)),"	"+BA.ObjectToString(mostCurrent._list_lname.Get(_nm)),mostCurrent._list_url.Get(_nm));
 }
};
 //BA.debugLineNum = 588;BA.debugLine="End Sub";
return "";
}
public static String  _filling_listview() throws Exception{
int _k = 0;
 //BA.debugLineNum = 243;BA.debugLine="Sub Filling_ListView";
 //BA.debugLineNum = 244;BA.debugLine="Reload_Lists";
_reload_lists();
 //BA.debugLineNum = 245;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 246;BA.debugLine="For k=0 To List_URL.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._list_url.getSize()-1);
for (_k = (int) (0) ; (step3 > 0 && _k <= limit3) || (step3 < 0 && _k >= limit3); _k = ((int)(0 + _k + step3)) ) {
 //BA.debugLineNum = 247;BA.debugLine="ListView1.AddTwoLines2(\"             \"&List_Fnam";
mostCurrent._listview1.AddTwoLines2("             "+BA.ObjectToString(mostCurrent._list_fname.Get((int) (mostCurrent._list_url.getSize()-1-_k))),"    "+BA.ObjectToString(mostCurrent._list_lname.Get((int) (mostCurrent._list_url.getSize()-1-_k))),mostCurrent._list_url.Get((int) (mostCurrent._list_url.getSize()-1-_k)));
 }
};
 //BA.debugLineNum = 249;BA.debugLine="ListView1.AddTwoLinesAndBitmap(\"\",\"\",Null)";
mostCurrent._listview1.AddTwoLinesAndBitmap("","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 250;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Dim sc As Security_Class_List";
mostCurrent._sc = new Fast_Accessibility.ModernPl.security_class_list();
 //BA.debugLineNum = 13;BA.debugLine="Dim List_URL,List_Fname,List_Lname As List";
mostCurrent._list_url = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._list_fname = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._list_lname = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 14;BA.debugLine="Dim sld1 As SlidingMenu";
mostCurrent._sld1 = new anywheresoftware.b4a.objects.SlidingMenuWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private img_SlideMenu As ImageView";
mostCurrent._img_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private img_HomeScreen As ImageView";
mostCurrent._img_homescreen = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private btn_ChooseDirectory As Button";
mostCurrent._btn_choosedirectory = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private btn_InstalledApps As Button";
mostCurrent._btn_installedapps = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private btn_SettingPArts As Button";
mostCurrent._btn_settingparts = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private btn_ContactChoose As Button";
mostCurrent._btn_contactchoose = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private btn_RemovePermissions As Button";
mostCurrent._btn_removepermissions = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private btn_Home_Slidebar As Button";
mostCurrent._btn_home_slidebar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private img_Home_SlideBar As ImageView";
mostCurrent._img_home_slidebar = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private btn_LastDefinitionAccessibility_SlideBar";
mostCurrent._btn_lastdefinitionaccessibility_slidebar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private img_LastDefinitionAccessibility_SlideBar";
mostCurrent._img_lastdefinitionaccessibility_slidebar = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private btn_Help_SlideBAr As Button";
mostCurrent._btn_help_slidebar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private img_Help_SlideBAr As ImageView";
mostCurrent._img_help_slidebar = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private btn_Definition_SlideMenu As Button";
mostCurrent._btn_definition_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private img_Definition_SlideMenu As ImageView";
mostCurrent._img_definition_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private btn_Contact_SlideMenu As Button";
mostCurrent._btn_contact_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private img_Contact_SlideMenu As ImageView";
mostCurrent._img_contact_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private btn_Comments_SlideMenu As Button";
mostCurrent._btn_comments_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private img_Comments_SlideMenu As ImageView";
mostCurrent._img_comments_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private btn_Exit_SlideMenu As Button";
mostCurrent._btn_exit_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private img_Exit_SlideMenu As ImageView";
mostCurrent._img_exit_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private TabHost1 As TabHost";
mostCurrent._tabhost1 = new anywheresoftware.b4a.objects.TabHostWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private EditText1 As EditText";
mostCurrent._edittext1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private img_Border_Slide1 As ImageView";
mostCurrent._img_border_slide1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private img_Border_Slide2 As ImageView";
mostCurrent._img_border_slide2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private img_Border_Slide3 As ImageView";
mostCurrent._img_border_slide3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private img_Border_Slide4 As ImageView";
mostCurrent._img_border_slide4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private img_Border_Slide5 As ImageView";
mostCurrent._img_border_slide5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private img_Border_Slide6 As ImageView";
mostCurrent._img_border_slide6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _img_homescreen_click() throws Exception{
 //BA.debugLineNum = 334;BA.debugLine="Sub img_HomeScreen_Click";
 //BA.debugLineNum = 335;BA.debugLine="StartActivity(Home_Act)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._home_act.getObject()));
 //BA.debugLineNum = 336;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 337;BA.debugLine="End Sub";
return "";
}
public static String  _img_slidemenu_click() throws Exception{
 //BA.debugLineNum = 330;BA.debugLine="Sub img_SlideMenu_Click";
 //BA.debugLineNum = 331;BA.debugLine="sld1.ShowMenu()";
mostCurrent._sld1.ShowMenu();
 //BA.debugLineNum = 332;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
String _str = "";
anywheresoftware.b4a.objects.IntentWrapper _listview_intents = null;
anywheresoftware.b4a.objects.IntentWrapper _i = null;
anywheresoftware.b4a.phone.PackageManagerWrapper _pg = null;
 //BA.debugLineNum = 442;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 446;BA.debugLine="If Value<>\"\" Then";
if ((_value).equals((Object)("")) == false) { 
 //BA.debugLineNum = 448;BA.debugLine="Dim str As String=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 450;BA.debugLine="If str.StartsWith(\"content://com.android.conta";
if (_str.startsWith("content://com.android.contacts/contacts/")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 451;BA.debugLine="Dim ListView_Intents As Intent";
_listview_intents = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 452;BA.debugLine="ListView_Intents.Initialize(ListView_Intents";
_listview_intents.Initialize(_listview_intents.ACTION_VIEW,BA.ObjectToString(_value));
 //BA.debugLineNum = 453;BA.debugLine="StartActivity(ListView_Intents)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_listview_intents.getObject()));
 }else if(_str.startsWith("android.settings.")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 456;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 457;BA.debugLine="i.Initialize(Value, \"\")";
_i.Initialize(BA.ObjectToString(_value),"");
 //BA.debugLineNum = 458;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 }else {
 //BA.debugLineNum = 462;BA.debugLine="Try";
try { //BA.debugLineNum = 464;BA.debugLine="Dim pg As PackageManager";
_pg = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 465;BA.debugLine="StartActivity(pg.GetApplicationIntent(Value";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_pg.GetApplicationIntent(BA.ObjectToString(_value)).getObject()));
 //BA.debugLineNum = 466;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 } 
       catch (Exception e17) {
			processBA.setLastException(e17); //BA.debugLineNum = 470;BA.debugLine="If File.IsDirectory(\"\",Value)=True Then";
if (anywheresoftware.b4a.keywords.Common.File.IsDirectory("",BA.ObjectToString(_value))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 471;BA.debugLine="File_Manager.Get_Path=Value";
mostCurrent._file_manager._get_path = BA.ObjectToString(_value);
 //BA.debugLineNum = 472;BA.debugLine="StartActivity(File_Manager)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._file_manager.getObject()));
 //BA.debugLineNum = 473;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 476;BA.debugLine="OpenFileOrFolder(Value)";
_openfileorfolder(BA.ObjectToString(_value));
 };
 };
 };
 };
 //BA.debugLineNum = 492;BA.debugLine="End Sub";
return "";
}
public static String  _openfileorfolder(String _file_path) throws Exception{
ariagp.amin.shahedi.files.AriaFile _file_selected = null;
ariagp.amin.shahedi.files.AriaFileUtils _fileutils = null;
 //BA.debugLineNum = 495;BA.debugLine="Sub OpenFileOrFolder(File_Path As String)";
 //BA.debugLineNum = 498;BA.debugLine="Dim File_Selected As AriaFile";
_file_selected = new ariagp.amin.shahedi.files.AriaFile();
 //BA.debugLineNum = 499;BA.debugLine="File_Selected.initialize2(File_Path)";
_file_selected.initialize2(_file_path);
 //BA.debugLineNum = 500;BA.debugLine="Dim FileUtils As AriaFileUtils";
_fileutils = new ariagp.amin.shahedi.files.AriaFileUtils();
 //BA.debugLineNum = 503;BA.debugLine="If File_Selected.IsDirectory Then";
if (_file_selected.IsDirectory()) { 
 }else {
 //BA.debugLineNum = 507;BA.debugLine="Try";
try { //BA.debugLineNum = 509;BA.debugLine="StartActivity(FileUtils.GetIntentForOpenFile(F";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_fileutils.GetIntentForOpenFile(_file_selected,anywheresoftware.b4a.keywords.Common.True).getObject()));
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 512;BA.debugLine="ToastMessageShow(\"there is no app to handle th";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("there is no app to handle this file",anywheresoftware.b4a.keywords.Common.False);
 };
 };
 //BA.debugLineNum = 518;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim Current_Tab As Int";
_current_tab = 0;
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _reload_lists() throws Exception{
anywheresoftware.b4a.objects.collections.List _list_fname_dec = null;
anywheresoftware.b4a.objects.collections.List _list_lname_dec = null;
anywheresoftware.b4a.objects.collections.List _list_url_dec = null;
 //BA.debugLineNum = 252;BA.debugLine="Sub Reload_Lists";
 //BA.debugLineNum = 253;BA.debugLine="List_Fname.Initialize";
mostCurrent._list_fname.Initialize();
 //BA.debugLineNum = 254;BA.debugLine="List_Lname.Initialize";
mostCurrent._list_lname.Initialize();
 //BA.debugLineNum = 255;BA.debugLine="List_URL.Initialize";
mostCurrent._list_url.Initialize();
 //BA.debugLineNum = 256;BA.debugLine="If File.Exists(File.DirInternal,\"fsaccs/sqrgdb_ls";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Fani_t.txt")==anywheresoftware.b4a.keywords.Common.True && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Landy_C.txt")==anywheresoftware.b4a.keywords.Common.True && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_Lostictevision_URL.txt")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 258;BA.debugLine="Dim List_Fname_DEC As List";
_list_fname_dec = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 259;BA.debugLine="List_Fname_DEC.Initialize";
_list_fname_dec.Initialize();
 //BA.debugLineNum = 260;BA.debugLine="List_Fname_DEC=File.ReadList(File.DirInternal,\"fs";
_list_fname_dec = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Fani_t.txt");
 //BA.debugLineNum = 262;BA.debugLine="Dim List_Lname_DEC As List";
_list_lname_dec = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 263;BA.debugLine="List_Lname_DEC.Initialize";
_list_lname_dec.Initialize();
 //BA.debugLineNum = 264;BA.debugLine="List_Lname_DEC=File.ReadList(File.DirInternal,\"fs";
_list_lname_dec = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Landy_C.txt");
 //BA.debugLineNum = 266;BA.debugLine="Dim List_URL_DEC As List";
_list_url_dec = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 267;BA.debugLine="List_URL_DEC.Initialize";
_list_url_dec.Initialize();
 //BA.debugLineNum = 268;BA.debugLine="List_URL_DEC=File.ReadList(File.DirInternal,\"fsac";
_list_url_dec = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_Lostictevision_URL.txt");
 //BA.debugLineNum = 270;BA.debugLine="List_Fname=sc.List_Dec(List_Fname_DEC)";
mostCurrent._list_fname = mostCurrent._sc._list_dec(_list_fname_dec);
 //BA.debugLineNum = 271;BA.debugLine="List_Lname=sc.List_Dec(List_Lname_DEC)";
mostCurrent._list_lname = mostCurrent._sc._list_dec(_list_lname_dec);
 //BA.debugLineNum = 272;BA.debugLine="List_URL=sc.List_Dec(List_URL_DEC)";
mostCurrent._list_url = mostCurrent._sc._list_dec(_list_url_dec);
 };
 //BA.debugLineNum = 275;BA.debugLine="End Sub";
return "";
}
public static String  _save_lists() throws Exception{
anywheresoftware.b4a.objects.collections.List _list_url_enc = null;
anywheresoftware.b4a.objects.collections.List _list_lname_enc = null;
anywheresoftware.b4a.objects.collections.List _list_fname_enc = null;
 //BA.debugLineNum = 310;BA.debugLine="Sub Save_Lists";
 //BA.debugLineNum = 313;BA.debugLine="Dim List_URL_ENC As List";
_list_url_enc = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 314;BA.debugLine="List_URL_ENC.Initialize";
_list_url_enc.Initialize();
 //BA.debugLineNum = 315;BA.debugLine="List_URL_ENC=sc.List_Enc(List_URL)";
_list_url_enc = mostCurrent._sc._list_enc(mostCurrent._list_url);
 //BA.debugLineNum = 317;BA.debugLine="Dim List_Lname_ENC As List";
_list_lname_enc = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 318;BA.debugLine="List_Lname_ENC.Initialize";
_list_lname_enc.Initialize();
 //BA.debugLineNum = 319;BA.debugLine="List_Lname_ENC=sc.List_Enc(List_Lname)";
_list_lname_enc = mostCurrent._sc._list_enc(mostCurrent._list_lname);
 //BA.debugLineNum = 321;BA.debugLine="Dim List_Fname_ENC As List";
_list_fname_enc = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 322;BA.debugLine="List_Fname_ENC.Initialize";
_list_fname_enc.Initialize();
 //BA.debugLineNum = 323;BA.debugLine="List_Fname_ENC=sc.List_Enc(List_Fname)";
_list_fname_enc = mostCurrent._sc._list_enc(mostCurrent._list_fname);
 //BA.debugLineNum = 325;BA.debugLine="File.WriteList(File.DirInternal,\"fsaccs/sqrgdb_l";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Fani_t.txt",_list_fname_enc);
 //BA.debugLineNum = 326;BA.debugLine="File.WriteList(File.DirInternal,\"fsaccs/sqrgdb_l";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Landy_C.txt",_list_lname_enc);
 //BA.debugLineNum = 327;BA.debugLine="File.WriteList(File.DirInternal,\"fsaccs/sqrgdb_L";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_Lostictevision_URL.txt",_list_url_enc);
 //BA.debugLineNum = 328;BA.debugLine="End Sub";
return "";
}
public static String  _setnumber_getid(String _numb) throws Exception{
anywheresoftware.b4a.phone.Contacts2Wrapper _cnts = null;
anywheresoftware.b4a.objects.collections.List _lst = null;
int _i = 0;
anywheresoftware.b4a.phone.ContactsWrapper.Contact _cnt = null;
anywheresoftware.b4a.objects.collections.Map _map = null;
String _string1 = "";
String _string2 = "";
 //BA.debugLineNum = 278;BA.debugLine="Sub SetNumber_GetID(Numb As String) As String";
 //BA.debugLineNum = 280;BA.debugLine="Dim cnts As Contacts2";
_cnts = new anywheresoftware.b4a.phone.Contacts2Wrapper();
 //BA.debugLineNum = 281;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 282;BA.debugLine="lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 283;BA.debugLine="lst= cnts.GetAll(False,False)";
_lst = _cnts.GetAll(anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 285;BA.debugLine="For i=0 To lst.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (_lst.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 286;BA.debugLine="Dim cnt As Contact";
_cnt = new anywheresoftware.b4a.phone.ContactsWrapper.Contact();
 //BA.debugLineNum = 287;BA.debugLine="cnt=lst.Get(i)";
_cnt = (anywheresoftware.b4a.phone.ContactsWrapper.Contact)(_lst.Get(_i));
 //BA.debugLineNum = 288;BA.debugLine="Dim map As Map";
_map = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 289;BA.debugLine="map.Initialize";
_map.Initialize();
 //BA.debugLineNum = 290;BA.debugLine="map=cnt.GetPhones";
_map = _cnt.GetPhones();
 //BA.debugLineNum = 292;BA.debugLine="Dim string1,string2 As String";
_string1 = "";
_string2 = "";
 //BA.debugLineNum = 293;BA.debugLine="string1=map";
_string1 = BA.ObjectToString(_map);
 //BA.debugLineNum = 294;BA.debugLine="Try";
try { //BA.debugLineNum = 295;BA.debugLine="string2=string1.SubString2(9,string1.IndexOf(\"=2";
_string2 = _string1.substring((int) (9),_string1.indexOf("=2"));
 //BA.debugLineNum = 296;BA.debugLine="If \" \"&string2=Numb Then";
if ((" "+_string2).equals(_numb)) { 
 //BA.debugLineNum = 297;BA.debugLine="Return cnt.Id";
if (true) return BA.NumberToString(_cnt.Id);
 //BA.debugLineNum = 298;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e20) {
			processBA.setLastException(e20); };
 }
};
 //BA.debugLineNum = 306;BA.debugLine="End Sub";
return "";
}
}
