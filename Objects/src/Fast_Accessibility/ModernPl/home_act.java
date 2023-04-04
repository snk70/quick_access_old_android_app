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

public class home_act extends Activity implements B4AActivity{
	public static home_act mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.home_act");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (home_act).");
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
		activityBA = new BA(this, layout, processBA, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.home_act");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Fast_Accessibility.ModernPl.home_act", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (home_act) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (home_act) Resume **");
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
		return home_act.class;
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
        BA.LogInfo("** Activity (home_act) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (home_act) Resume **");
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
public anywheresoftware.b4a.objects.SlidingMenuWrapper _vvvvv2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_strip = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_toolbar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_definition_permissions = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_help = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_aboutwe = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_contact = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_advice = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_exit = null;
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
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_border_slide1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _main_panel = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvv4 = null;
public Fast_Accessibility.ModernPl.main _vvvv5 = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 49;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 50;BA.debugLine="If File.Exists(File.DirInternal,\"fsaccs/sqrgdb_l";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Fani_t.txt") && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_lsit_Landy_C.txt") && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"fsaccs/sqrgdb_Lostictevision_URL.txt")) { 
 //BA.debugLineNum = 52;BA.debugLine="Definition_Permissions_ACT.Current_Tab=0";
mostCurrent._definition_permissions_act._current_tab = (int) (0);
 //BA.debugLineNum = 53;BA.debugLine="StartActivity(Definition_Permissions_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 //BA.debugLineNum = 54;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 };
 //BA.debugLineNum = 59;BA.debugLine="Activity.LoadLayout(\"Home_Screen\")";
mostCurrent._activity.LoadLayout("Home_Screen",mostCurrent.activityBA);
 //BA.debugLineNum = 60;BA.debugLine="main_panel.LoadLayout(\"loyout_items\")";
mostCurrent._main_panel.LoadLayout("loyout_items",mostCurrent.activityBA);
 //BA.debugLineNum = 62;BA.debugLine="main_panel.SetVisibleAnimated(0,False)";
mostCurrent._main_panel.SetVisibleAnimated((int) (0),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 63;BA.debugLine="main_panel.SetVisibleAnimated(3000,True)";
mostCurrent._main_panel.SetVisibleAnimated((int) (3000),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 65;BA.debugLine="sizeviewV3.SetPX(Activity.Width,Activity.Height,32";
mostCurrent._vvvv7._vv0(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (320),(float) (480));
 //BA.debugLineNum = 66;BA.debugLine="sizeviewV3.SetPastScreenSizetoInche(4.444,6.667)";
mostCurrent._vvvv7._vv7(mostCurrent.activityBA,(float) (4.444),(float) (6.667));
 //BA.debugLineNum = 69;BA.debugLine="sizeviewV3.SetSize_lbl_Views(lbl_Strip,10,33,50,25";
mostCurrent._vvvv7._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._lbl_strip,(float) (10),(float) (33),(float) (50),(float) (254),(float) (25/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 70;BA.debugLine="sizeviewV3.SetSize_lbl_Views(lbl_Toolbar,20,288,30";
mostCurrent._vvvv7._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._lbl_toolbar,(float) (20),(float) (288),(float) (30),(float) (30),(float) (0));
 //BA.debugLineNum = 71;BA.debugLine="sizeviewV3.SetSizeViews(main_panel,110,10,370,300,";
mostCurrent._vvvv7._vvv1(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._main_panel.getObject())),(float) (110),(float) (10),(float) (370),(float) (300),(float) (0));
 //BA.debugLineNum = 74;BA.debugLine="sizeviewV3.SetSize_btn(btn_Definition_Permissions,";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_definition_permissions,(float) (10),(float) (40),(float) (39),(float) (225),(float) (25/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 75;BA.debugLine="sizeviewV3.SetSize_btn(btn_Help,69,40,39,225,25/1.";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_help,(float) (69),(float) (40),(float) (39),(float) (225),(float) (25/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 76;BA.debugLine="sizeviewV3.SetSize_btn(btn_AboutWe,128,40,39,225,2";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_aboutwe,(float) (128),(float) (40),(float) (39),(float) (225),(float) (25/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 77;BA.debugLine="sizeviewV3.SetSize_btn(btn_Contact,187,40,39,225,2";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_contact,(float) (187),(float) (40),(float) (39),(float) (225),(float) (25/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 78;BA.debugLine="sizeviewV3.SetSize_btn(btn_Advice,246,40,39,225,25";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_advice,(float) (246),(float) (40),(float) (39),(float) (225),(float) (25/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 79;BA.debugLine="sizeviewV3.SetSize_btn(btn_Exit,305,40,39,225,25/1";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_exit,(float) (305),(float) (40),(float) (39),(float) (225),(float) (25/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 81;BA.debugLine="main_panel.RequestFocus";
mostCurrent._main_panel.RequestFocus();
 //BA.debugLineNum = 85;BA.debugLine="sld1.Initialize(\"\")";
mostCurrent._vvvvv2.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 86;BA.debugLine="sld1.Mode=sld1.RIGHT";
mostCurrent._vvvvv2.setMode(mostCurrent._vvvvv2.RIGHT);
 //BA.debugLineNum = 87;BA.debugLine="sld1.BehindOffset=(150/320)*Activity.Width";
mostCurrent._vvvvv2.setBehindOffset((int) ((150/(double)320)*mostCurrent._activity.getWidth()));
 //BA.debugLineNum = 88;BA.debugLine="sld1.Menu.LoadLayout(\"SlideMenu_Loyout\")";
mostCurrent._vvvvv2.getMenu().LoadLayout("SlideMenu_Loyout",mostCurrent.activityBA);
 //BA.debugLineNum = 90;BA.debugLine="sizeviewV3.SetSize_btn(btn_Home_Slidebar,0,0,38,17";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_home_slidebar,(float) (0),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 91;BA.debugLine="sizeviewV3.SetSize_btn(btn_LastDefinitionAccessibi";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_lastdefinitionaccessibility_slidebar,(float) (38),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 92;BA.debugLine="sizeviewV3.SetSize_btn(btn_Help_SlideBAr,76,0,38,1";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_help_slidebar,(float) (76),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 93;BA.debugLine="sizeviewV3.SetSize_btn(btn_Definition_SlideMenu,11";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_definition_slidemenu,(float) (114),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 94;BA.debugLine="sizeviewV3.SetSize_btn(btn_Contact_SlideMenu,151,0";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_contact_slidemenu,(float) (151),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 95;BA.debugLine="sizeviewV3.SetSize_btn(btn_Comments_SlideMenu,188,";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_comments_slidemenu,(float) (188),(float) (0),(float) (38),(float) (171),(float) (20/(double)1.5773684210526315789473684210526));
 //BA.debugLineNum = 96;BA.debugLine="sizeviewV3.SetSize_btn(btn_Exit_SlideMenu,226,0,38";
mostCurrent._vvvv7._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_exit_slidemenu,(float) (226),(float) (0),(float) (38),(float) (171),(float) (20));
 //BA.debugLineNum = 98;BA.debugLine="sizeviewV3.SetSize_img(img_Home_SlideBar,2,4,34,36";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_home_slidebar,(float) (2),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 99;BA.debugLine="sizeviewV3.SetSize_img(img_LastDefinitionAccessibi";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_lastdefinitionaccessibility_slidebar,(float) (40),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 100;BA.debugLine="sizeviewV3.SetSize_img(img_Help_SlideBAr,78,4,34,3";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_help_slidebar,(float) (78),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 101;BA.debugLine="sizeviewV3.SetSize_img(img_Definition_SlideMenu,11";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_definition_slidemenu,(float) (116),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 102;BA.debugLine="sizeviewV3.SetSize_img(img_Contact_SlideMenu,153,4";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_contact_slidemenu,(float) (153),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 103;BA.debugLine="sizeviewV3.SetSize_img(img_Comments_SlideMenu,190,";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_comments_slidemenu,(float) (190),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 104;BA.debugLine="sizeviewV3.SetSize_img(img_Exit_SlideMenu,226,4,34";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_exit_slidemenu,(float) (226),(float) (4),(float) (34),(float) (36),(float) (0));
 //BA.debugLineNum = 108;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide1,38,0,1,17";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide1,(float) (38),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 109;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide2,76,0,1,17";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide2,(float) (76),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 110;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide3,114,0,1,1";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide3,(float) (114),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 111;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide4,151,0,1,1";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide4,(float) (151),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 112;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide5,188,0,1,1";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide5,(float) (188),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 113;BA.debugLine="sizeviewV3.SetSize_img(img_Border_Slide6,226,0,1,1";
mostCurrent._vvvv7._setsize_img(mostCurrent.activityBA,mostCurrent._img_border_slide6,(float) (226),(float) (0),(float) (1),(float) (171),(float) (0));
 //BA.debugLineNum = 115;BA.debugLine="img_Border_Slide1.Color=Colors.Red";
mostCurrent._img_border_slide1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 116;BA.debugLine="img_Border_Slide2.Color=Colors.Red";
mostCurrent._img_border_slide2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 117;BA.debugLine="img_Border_Slide3.Color=Colors.Red";
mostCurrent._img_border_slide3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 118;BA.debugLine="img_Border_Slide4.Color=Colors.Red";
mostCurrent._img_border_slide4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 119;BA.debugLine="img_Border_Slide5.Color=Colors.Red";
mostCurrent._img_border_slide5.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 120;BA.debugLine="img_Border_Slide6.Color=Colors.Red";
mostCurrent._img_border_slide6.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 127;BA.debugLine="btn_AboutWe.Text=\"درباره من\"";
mostCurrent._btn_aboutwe.setText((Object)("درباره من"));
 //BA.debugLineNum = 128;BA.debugLine="btn_Contact.Text=\"اطلاعات تماس\"";
mostCurrent._btn_contact.setText((Object)("اطلاعات تماس"));
 //BA.debugLineNum = 129;BA.debugLine="btn_Advice.Text=\"مشاهده صفحه گیت هاب\"";
mostCurrent._btn_advice.setText((Object)("مشاهده صفحه گیت هاب"));
 //BA.debugLineNum = 130;BA.debugLine="btn_Contact_SlideMenu.Text=\"اطلاعات تماس\"";
mostCurrent._btn_contact_slidemenu.setText((Object)("اطلاعات تماس"));
 //BA.debugLineNum = 131;BA.debugLine="btn_Comments_SlideMenu.Text=\"مشاهده صفحه گیت هاب\"";
mostCurrent._btn_comments_slidemenu.setText((Object)("مشاهده صفحه گیت هاب"));
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 223;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 225;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 227;BA.debugLine="btn_Exit_Click";
_btn_exit_click();
 };
 //BA.debugLineNum = 230;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 232;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 140;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 136;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static String  _btn_aboutwe_click() throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub btn_AboutWe_Click";
 //BA.debugLineNum = 162;BA.debugLine="Msgbox(\"توسعه دهنده: سینا کردستانی\"&CRLF&CRLF&\"برن";
anywheresoftware.b4a.keywords.Common.Msgbox("توسعه دهنده: سینا کردستانی"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"برنامه نویس و معمار سامانه های نرم افزاری تحت وب و اپلیکیشن های تلفن های همراه","درباره سازنده",mostCurrent.activityBA);
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _btn_advice_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 172;BA.debugLine="Sub btn_Advice_Click";
 //BA.debugLineNum = 175;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 176;BA.debugLine="i.Initialize(i.ACTION_VIEW,\"https://github.com/sn";
_i.Initialize(_i.ACTION_VIEW,"https://github.com/snk70/quick_access_old_android_app");
 //BA.debugLineNum = 177;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static String  _btn_comments_slidemenu_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 213;BA.debugLine="Sub btn_Comments_SlideMenu_Click";
 //BA.debugLineNum = 214;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 215;BA.debugLine="i.Initialize(i.ACTION_VIEW,\"https://github.com/sn";
_i.Initialize(_i.ACTION_VIEW,"https://github.com/snk70");
 //BA.debugLineNum = 216;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static String  _btn_contact_click() throws Exception{
 //BA.debugLineNum = 166;BA.debugLine="Sub btn_Contact_Click";
 //BA.debugLineNum = 169;BA.debugLine="Msgbox(\"Email: SinaKordestani@gmail.com\"&CRLF&CRLF";
anywheresoftware.b4a.keywords.Common.Msgbox("Email: SinaKordestani@gmail.com"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Linkedin: Sina-Kordestani","اطلاعات تماس",mostCurrent.activityBA);
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String  _btn_contact_slidemenu_click() throws Exception{
 //BA.debugLineNum = 209;BA.debugLine="Sub btn_Contact_SlideMenu_Click";
 //BA.debugLineNum = 210;BA.debugLine="Msgbox(\"Email: SinaKordestani@gmail.com\"&CRLF&CRLF";
anywheresoftware.b4a.keywords.Common.Msgbox("Email: SinaKordestani@gmail.com"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Linkedin: Sina-Kordestani","اطلاعات تماس",mostCurrent.activityBA);
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _btn_definition_permissions_click() throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Sub btn_Definition_Permissions_Click";
 //BA.debugLineNum = 151;BA.debugLine="Definition_Permissions_ACT.Current_Tab=1";
mostCurrent._definition_permissions_act._current_tab = (int) (1);
 //BA.debugLineNum = 152;BA.debugLine="StartActivity(Definition_Permissions_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 //BA.debugLineNum = 153;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return "";
}
public static String  _btn_definition_slidemenu_click() throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Sub btn_Definition_SlideMenu_Click";
 //BA.debugLineNum = 204;BA.debugLine="Definition_Permissions_ACT.Current_Tab=1";
mostCurrent._definition_permissions_act._current_tab = (int) (1);
 //BA.debugLineNum = 205;BA.debugLine="StartActivity(Definition_Permissions_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 //BA.debugLineNum = 206;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public static String  _btn_exit_click() throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Sub btn_Exit_Click";
 //BA.debugLineNum = 181;BA.debugLine="If Msgbox2(\"میخواهید از برنامه خارج شوید ؟\",\"\",\"";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("میخواهید از برنامه خارج شوید ؟","","بله","","نه",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 182;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _btn_exit_slidemenu_click() throws Exception{
 //BA.debugLineNum = 219;BA.debugLine="Sub btn_Exit_SlideMenu_Click";
 //BA.debugLineNum = 220;BA.debugLine="btn_Exit_Click";
_btn_exit_click();
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _btn_help_click() throws Exception{
 //BA.debugLineNum = 156;BA.debugLine="Sub btn_Help_Click";
 //BA.debugLineNum = 157;BA.debugLine="StartActivity(Help_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._help_act.getObject()));
 //BA.debugLineNum = 158;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _btn_help_slidebar_click() throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Sub btn_Help_SlideBAr_Click";
 //BA.debugLineNum = 199;BA.debugLine="StartActivity(Help_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._help_act.getObject()));
 //BA.debugLineNum = 200;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _btn_home_slidebar_click() throws Exception{
 //BA.debugLineNum = 188;BA.debugLine="Sub btn_Home_Slidebar_Click";
 //BA.debugLineNum = 189;BA.debugLine="sld1.HideMenus";
mostCurrent._vvvvv2.HideMenus();
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _btn_lastdefinitionaccessibility_slidebar_click() throws Exception{
 //BA.debugLineNum = 192;BA.debugLine="Sub btn_LastDefinitionAccessibility_SlideBar_Click";
 //BA.debugLineNum = 193;BA.debugLine="Definition_Permissions_ACT.Current_Tab=0";
mostCurrent._definition_permissions_act._current_tab = (int) (0);
 //BA.debugLineNum = 194;BA.debugLine="StartActivity(Definition_Permissions_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._definition_permissions_act.getObject()));
 //BA.debugLineNum = 195;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Dim sld1 As SlidingMenu";
mostCurrent._vvvvv2 = new anywheresoftware.b4a.objects.SlidingMenuWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private lbl_Strip As Label";
mostCurrent._lbl_strip = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private lbl_Toolbar As Label";
mostCurrent._lbl_toolbar = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private btn_Definition_Permissions As Button";
mostCurrent._btn_definition_permissions = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private btn_Help As Button";
mostCurrent._btn_help = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private btn_AboutWe As Button";
mostCurrent._btn_aboutwe = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private btn_Contact As Button";
mostCurrent._btn_contact = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private btn_Advice As Button";
mostCurrent._btn_advice = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private btn_Exit As Button";
mostCurrent._btn_exit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private btn_Home_Slidebar As Button";
mostCurrent._btn_home_slidebar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private img_Home_SlideBar As ImageView";
mostCurrent._img_home_slidebar = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private btn_LastDefinitionAccessibility_SlideBar";
mostCurrent._btn_lastdefinitionaccessibility_slidebar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private img_LastDefinitionAccessibility_SlideBar";
mostCurrent._img_lastdefinitionaccessibility_slidebar = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private btn_Help_SlideBAr As Button";
mostCurrent._btn_help_slidebar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private img_Help_SlideBAr As ImageView";
mostCurrent._img_help_slidebar = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private btn_Definition_SlideMenu As Button";
mostCurrent._btn_definition_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private img_Definition_SlideMenu As ImageView";
mostCurrent._img_definition_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private btn_Contact_SlideMenu As Button";
mostCurrent._btn_contact_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private img_Contact_SlideMenu As ImageView";
mostCurrent._img_contact_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private btn_Comments_SlideMenu As Button";
mostCurrent._btn_comments_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private img_Comments_SlideMenu As ImageView";
mostCurrent._img_comments_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private btn_Exit_SlideMenu As Button";
mostCurrent._btn_exit_slidemenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private img_Exit_SlideMenu As ImageView";
mostCurrent._img_exit_slidemenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private img_Border_Slide5 As ImageView";
mostCurrent._img_border_slide5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private img_Border_Slide6 As ImageView";
mostCurrent._img_border_slide6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private img_Border_Slide4 As ImageView";
mostCurrent._img_border_slide4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private img_Border_Slide3 As ImageView";
mostCurrent._img_border_slide3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private img_Border_Slide2 As ImageView";
mostCurrent._img_border_slide2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private img_Border_Slide1 As ImageView";
mostCurrent._img_border_slide1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private main_panel As Panel";
mostCurrent._main_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_toolbar_click() throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub lbl_Toolbar_Click";
 //BA.debugLineNum = 146;BA.debugLine="sld1.ShowMenu";
mostCurrent._vvvvv2.ShowMenu();
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
