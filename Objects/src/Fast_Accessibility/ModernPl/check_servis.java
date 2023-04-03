package Fast_Accessibility.ModernPl;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class check_servis extends android.app.Service {
	public static class check_servis_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, check_servis.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static check_servis mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return check_servis.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "Fast_Accessibility.ModernPl", "Fast_Accessibility.ModernPl.check_servis");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "Fast_Accessibility.ModernPl.check_servis", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, true) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("** Service (check_servis) Create **");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
			if (ServiceHelper.StarterHelper.waitForLayout != null)
				BA.handler.post(ServiceHelper.StarterHelper.waitForLayout);
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA))
			handleStart(intent);
		else {
			ServiceHelper.StarterHelper.waitForLayout = new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (check_servis) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
				}
			};
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (check_servis) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
        BA.LogInfo("** Service (check_servis) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.samples.httputils2.httpjob _ht_check = null;
public static String _vain_text = "";
public static String _pi = "";
public static String _rnd_path = "";
public static String _rnd_f = "";
public static String _lsn_path = "";
public static String _lsn_f = "";
public static com.devil.signature.CheckSignature _ch_sign = null;
public static String _website_url = "";
public static String _p_sign = "";
public static int _rn = 0;
public static boolean _ok_status = false;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public Fast_Accessibility.ModernPl.main _main = null;
public Fast_Accessibility.ModernPl.home_act _home_act = null;
public Fast_Accessibility.ModernPl.definition_permissions_act _definition_permissions_act = null;
public Fast_Accessibility.ModernPl.installed_applications _installed_applications = null;
public Fast_Accessibility.ModernPl.setting_parts _setting_parts = null;
public Fast_Accessibility.ModernPl.file_manager _file_manager = null;
public Fast_Accessibility.ModernPl.send_comment_act _send_comment_act = null;
public Fast_Accessibility.ModernPl.contact_act _contact_act = null;
public Fast_Accessibility.ModernPl.selected_contact _selected_contact = null;
public Fast_Accessibility.ModernPl.help_act _help_act = null;
public Fast_Accessibility.ModernPl.searchmodule _searchmodule = null;
public Fast_Accessibility.ModernPl.sizeviewv3 _sizeviewv3 = null;
public Fast_Accessibility.ModernPl.regular_validations _regular_validations = null;
public Fast_Accessibility.ModernPl.size_view _size_view = null;
public Fast_Accessibility.ModernPl.interchange_stn _interchange_stn = null;
public Fast_Accessibility.ModernPl.size_view301 _size_view301 = null;
public static String  _create_hardlicence(String _rand_number,String _p_serial) throws Exception{
String _hardware_licence = "";
anywheresoftware.b4a.phone.Phone _ph = null;
anywheresoftware.b4a.agraham.encryption.Base64 _bs = null;
 //BA.debugLineNum = 497;BA.debugLine="Sub Create_HardLicence(Rand_Number As String,P_Ser";
 //BA.debugLineNum = 499;BA.debugLine="Dim Hardware_licence As String";
_hardware_licence = "";
 //BA.debugLineNum = 501;BA.debugLine="Dim ph As Phone";
_ph = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 502;BA.debugLine="Dim bs As Base64";
_bs = new anywheresoftware.b4a.agraham.encryption.Base64();
 //BA.debugLineNum = 503;BA.debugLine="Hardware_licence=Rand_Number&ph.Product&ph.Manufa";
_hardware_licence = _rand_number+_ph.getProduct()+_ph.getManufacturer()+_ph.getModel()+_ph.GetSettings("android_id");
 //BA.debugLineNum = 504;BA.debugLine="Hardware_licence=bs.EncodeBtoS(Hardware_licence.G";
_hardware_licence = _bs.EncodeBtoS(_hardware_licence.getBytes("UTF8"),(int) (0),_hardware_licence.getBytes("UTF8").length);
 //BA.debugLineNum = 505;BA.debugLine="Hardware_licence=Hardware_licence&P_Serial";
_hardware_licence = _hardware_licence+_p_serial;
 //BA.debugLineNum = 507;BA.debugLine="Hardware_licence= Encryption_stn1(Hardware_licenc";
_hardware_licence = _encryption_stn1(_hardware_licence);
 //BA.debugLineNum = 509;BA.debugLine="Hardware_licence= Hardware_licence.SubString2(Har";
_hardware_licence = _hardware_licence.substring((int) (_hardware_licence.length()-4),_hardware_licence.length())+_hardware_licence.substring((int) (0),(int) (_hardware_licence.length()-4));
 //BA.debugLineNum = 511;BA.debugLine="Return Interchange_stn.Encryption_Query_String_st";
if (true) return mostCurrent._interchange_stn._encryption_query_string_stn(processBA,_hardware_licence);
 //BA.debugLineNum = 513;BA.debugLine="End Sub";
return "";
}
public static String  _encryption_stn1(String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 287;BA.debugLine="Public Sub Encryption_stn1(t As String) As String";
 //BA.debugLineNum = 289;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 291;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 292;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 293;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 294;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 296;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 297;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 298;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 299;BA.debugLine="cec(i) = sina_ENC_stn1(x)";
_cec[_i] = _sina_enc_stn1(_x);
 //BA.debugLineNum = 300;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 302;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 303;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13); _j = ((int)(0 + _j + step13)) ) {
 //BA.debugLineNum = 304;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 309;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 310;BA.debugLine="End Sub";
return "";
}
public static String  _encryption_stn2(String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 77;BA.debugLine="Public Sub Encryption_stn2(t As String) As String";
 //BA.debugLineNum = 79;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 81;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 82;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 83;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 84;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 86;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 87;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 88;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 89;BA.debugLine="cec(i) = sina_ENC_stn2(x)";
_cec[_i] = _sina_enc_stn2(_x);
 //BA.debugLineNum = 90;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 92;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 93;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13); _j = ((int)(0 + _j + step13)) ) {
 //BA.debugLineNum = 94;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 99;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public static String  _handwork_licence() throws Exception{
 //BA.debugLineNum = 68;BA.debugLine="Sub HandWork_Licence";
 //BA.debugLineNum = 69;BA.debugLine="ToastMessageShow(\"لایسنس امنیتی نرم افزار دستکاری";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("لایسنس امنیتی نرم افزار دستکاری شده",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 70;BA.debugLine="File.Delete(File.DirInternal,lsn_Path&\"/\"&lsn_f)";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_lsn_path+"/"+_lsn_f);
 //BA.debugLineNum = 71;BA.debugLine="File.Delete(File.DirInternal,rnd_Path&\"/\"&rnd_f)";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_rnd_path+"/"+_rnd_f);
 //BA.debugLineNum = 72;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 57;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 58;BA.debugLine="If Job.JobName=\"ht_check\" And Job.GetString2(\"AS";
if ((_job._jobname).equals("ht_check") && (_job._getstring2("ASCII")).equals("") == false) { 
 //BA.debugLineNum = 59;BA.debugLine="If Job.GetString2(\"ASCII\").Replace(\"?\",\"\")<>ch_";
if ((_job._getstring2("ASCII").replace("?","")).equals(_ch_sign.sha1(processBA).replace(":","").toLowerCase()) == false && (_job._getstring2("ASCII")).equals("") == false) { 
 //BA.debugLineNum = 61;BA.debugLine="HandWork_Licence";
_handwork_licence();
 };
 };
 };
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim ht_check As HttpJob";
_ht_check = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 8;BA.debugLine="Dim vain_text=\"ertjerth34%Y#$%TYERtgherlkther8585";
_vain_text = "ertjerth34%Y#$%TYERtgherlkther8585therthjiop0w-kmgdsf,bwoie033$3wep0jbfrgbdkjdflrtgkjwerngwtb45@#dfnkweregtrbw4tberebwtrbertbrt09ugert9gertgiegmert0g4u,cg4cg5498ut3v54v";
 //BA.debugLineNum = 9;BA.debugLine="Dim pi As String=\"148066\"";
_pi = "148066";
 //BA.debugLineNum = 10;BA.debugLine="Dim rnd_Path As String=\"rteuiokiu_dsgerg/wergmlre";
_rnd_path = "rteuiokiu_dsgerg/wergmlret/sgdfsdf/lkmsdferpjjld/Der2343SDF";
 //BA.debugLineNum = 11;BA.debugLine="Dim rnd_f As String=\"sfgjmsert3w4eg.png\"";
_rnd_f = "sfgjmsert3w4eg.png";
 //BA.debugLineNum = 12;BA.debugLine="Dim lsn_Path As String=\"wertweadfgsdfg/ssdfgsdfg4";
_lsn_path = "wertweadfgsdfg/ssdfgsdfg45trvbertgb/34tgefbvsbw5gewevergfwe/sdgwetgerbs5w4vrtgwuj56h/wertwe";
 //BA.debugLineNum = 13;BA.debugLine="Dim lsn_f As String=\"gjbhfdkbdfgbesrwek4rgvpk_ghs";
_lsn_f = "gjbhfdkbdfgbesrwek4rgvpk_ghsdgsg.zip";
 //BA.debugLineNum = 14;BA.debugLine="Dim ch_sign As CheckSignature";
_ch_sign = new com.devil.signature.CheckSignature();
 //BA.debugLineNum = 15;BA.debugLine="Dim WebSite_URL As String=\"http://modernplus.ir\"";
_website_url = "http://modernplus.ir";
 //BA.debugLineNum = 16;BA.debugLine="Dim p_sign=\"market_apps\"";
_p_sign = "market_apps";
 //BA.debugLineNum = 17;BA.debugLine="Dim rn As Int";
_rn = 0;
 //BA.debugLineNum = 19;BA.debugLine="Dim OK_Status As Boolean=True";
_ok_status = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 30;BA.debugLine="If File.Exists(File.DirInternal,lsn_Path&\"/\"&lsn_";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_lsn_path+"/"+_lsn_f)==anywheresoftware.b4a.keywords.Common.True && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_rnd_path+"/"+_rnd_f)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 33;BA.debugLine="rn=(File.GetText(File.DirInternal,rnd_Path&\"/\"&r";
_rn = (int) (((double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.File.GetText(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_rnd_path+"/"+_rnd_f)))-3)/(double)2);
 //BA.debugLineNum = 35;BA.debugLine="If File.GetText(File.DirInternal,lsn_Path&\"/\"&ls";
if ((anywheresoftware.b4a.keywords.Common.File.GetText(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_lsn_path+"/"+_lsn_f).replace(_vain_text,"")).equals(_create_hardlicence(BA.NumberToString(_rn),_pi))) { 
 }else {
 //BA.debugLineNum = 39;BA.debugLine="HandWork_Licence";
_handwork_licence();
 };
 };
 //BA.debugLineNum = 45;BA.debugLine="ht_check.Initialize(\"ht_check\",Me)";
_ht_check._initialize(processBA,"ht_check",check_servis.getObject());
 //BA.debugLineNum = 46;BA.debugLine="ht_check.PostString(WebSite_URL&\"/prv/bdsgsdfgsdf";
_ht_check._poststring(_website_url+"/prv/bdsgsdfgsdfg/dsdfgsdfgsdfgsdfgsdfg/sdfgsdfgsdfgsdfg.php","sina=gh&ya_ali=dfgsdf&gsdfgsdf="+_p_sign);
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _sina_enc_stn1(String _v) throws Exception{
 //BA.debugLineNum = 314;BA.debugLine="Public Sub sina_ENC_stn1(v As String) As String";
 //BA.debugLineNum = 316;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 317;BA.debugLine="Return \"8TrD\"";
if (true) return "8TrD";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 319;BA.debugLine="Return \"dG3I\"";
if (true) return "dG3I";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 321;BA.debugLine="Return \"lKj^\"";
if (true) return "lKj^";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 323;BA.debugLine="Return \"U-Wx\"";
if (true) return "U-Wx";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 325;BA.debugLine="Return \"cq`c\"";
if (true) return "cq`c";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 327;BA.debugLine="Return \"B&Up\"";
if (true) return "B&Up";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 329;BA.debugLine="Return \"3^n?\"";
if (true) return "3^n?";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 331;BA.debugLine="Return \"gT^2\"";
if (true) return "gT^2";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 333;BA.debugLine="Return \"Z+.c\"";
if (true) return "Z+.c";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 335;BA.debugLine="Return \"p].D\"";
if (true) return "p].D";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 337;BA.debugLine="Return \"<Jc)\"";
if (true) return "<Jc)";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 339;BA.debugLine="Return \",<e1\"";
if (true) return ",<e1";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 341;BA.debugLine="Return \"d)]t\"";
if (true) return "d)]t";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 343;BA.debugLine="Return \"Dnrw\"";
if (true) return "Dnrw";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 345;BA.debugLine="Return \"64Sy\"";
if (true) return "64Sy";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 347;BA.debugLine="Return \"evOV\"";
if (true) return "evOV";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 349;BA.debugLine="Return \"W3g&\"";
if (true) return "W3g&";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 351;BA.debugLine="Return \"KUBo\"";
if (true) return "KUBo";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 353;BA.debugLine="Return \"oF])\"";
if (true) return "oF])";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 355;BA.debugLine="Return \"=jQ(\"";
if (true) return "=jQ(";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 357;BA.debugLine="Return \"1BN&\"";
if (true) return "1BN&";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 359;BA.debugLine="Return \"_/!$\"";
if (true) return "_/!$";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 361;BA.debugLine="Return \"BsH/\"";
if (true) return "BsH/";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 363;BA.debugLine="Return \"8T$4\"";
if (true) return "8T$4";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 365;BA.debugLine="Return \",)2]\"";
if (true) return ",)2]";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 367;BA.debugLine="Return \"Jq,N\"";
if (true) return "Jq,N";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 369;BA.debugLine="Return \"{$]T\"";
if (true) return "{$]T";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 371;BA.debugLine="Return \"kGhY\"";
if (true) return "kGhY";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 373;BA.debugLine="Return \"T?W)\"";
if (true) return "T?W)";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 375;BA.debugLine="Return \"}+=p\"";
if (true) return "}+=p";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 377;BA.debugLine="Return \"jEK2\"";
if (true) return "jEK2";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 379;BA.debugLine="Return \"gy**\"";
if (true) return "gy**";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 381;BA.debugLine="Return \"K7fM\"";
if (true) return "K7fM";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 383;BA.debugLine="Return \"VN=%\"";
if (true) return "VN=%";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 385;BA.debugLine="Return \"RC4T\"";
if (true) return "RC4T";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 387;BA.debugLine="Return \"/dhN\"";
if (true) return "/dhN";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 389;BA.debugLine="Return \"MBI_\"";
if (true) return "MBI_";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 391;BA.debugLine="Return \"FkZz\"";
if (true) return "FkZz";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 393;BA.debugLine="Return \"_Wx.\"";
if (true) return "_Wx.";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 395;BA.debugLine="Return \"L`9s\"";
if (true) return "L`9s";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 397;BA.debugLine="Return \"x98&\"";
if (true) return "x98&";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 399;BA.debugLine="Return \"%z/?\"";
if (true) return "%z/?";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 401;BA.debugLine="Return \"1glP\"";
if (true) return "1glP";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 403;BA.debugLine="Return \"8OtO\"";
if (true) return "8OtO";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 405;BA.debugLine="Return \"B-D_\"";
if (true) return "B-D_";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 407;BA.debugLine="Return \".3b!\"";
if (true) return ".3b!";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 409;BA.debugLine="Return \"e2[2\"";
if (true) return "e2[2";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 411;BA.debugLine="Return \"DtJ`\"";
if (true) return "DtJ`";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 413;BA.debugLine="Return \"5t*5\"";
if (true) return "5t*5";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 415;BA.debugLine="Return \"UB^~\"";
if (true) return "UB^~";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 417;BA.debugLine="Return \"+yKp\"";
if (true) return "+yKp";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 419;BA.debugLine="Return \"KUlK\"";
if (true) return "KUlK";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 421;BA.debugLine="Return \"~odq\"";
if (true) return "~odq";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 423;BA.debugLine="Return \"#qi$\"";
if (true) return "#qi$";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 425;BA.debugLine="Return \"$3Qw\"";
if (true) return "$3Qw";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 427;BA.debugLine="Return \"83x}\"";
if (true) return "83x}";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 429;BA.debugLine="Return \"Inz.\"";
if (true) return "Inz.";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 431;BA.debugLine="Return \"$IO{\"";
if (true) return "$IO{";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 433;BA.debugLine="Return \",7&L\"";
if (true) return ",7&L";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 435;BA.debugLine="Return \",fH?\"";
if (true) return ",fH?";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 437;BA.debugLine="Return \"tj1}\"";
if (true) return "tj1}";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 439;BA.debugLine="Return \"<^dt\"";
if (true) return "<^dt";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 441;BA.debugLine="Return \"Gmp`\"";
if (true) return "Gmp`";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 443;BA.debugLine="Return \"D=jX\"";
if (true) return "D=jX";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 445;BA.debugLine="Return \"F`mE\"";
if (true) return "F`mE";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 447;BA.debugLine="Return \"W44?\"";
if (true) return "W44?";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 449;BA.debugLine="Return \"<LG4\"";
if (true) return "<LG4";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 451;BA.debugLine="Return \"plD/\"";
if (true) return "plD/";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 453;BA.debugLine="Return \"#uk{\"";
if (true) return "#uk{";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 455;BA.debugLine="Return \"L,n[\"";
if (true) return "L,n[";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 457;BA.debugLine="Return \">}(G\"";
if (true) return ">}(G";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 459;BA.debugLine="Return \"R}M6\"";
if (true) return "R}M6";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 461;BA.debugLine="Return \"_`mg\"";
if (true) return "_`mg";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 463;BA.debugLine="Return \".%jm\"";
if (true) return ".%jm";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 465;BA.debugLine="Return \"iAs}\"";
if (true) return "iAs}";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 467;BA.debugLine="Return \"bGqV\"";
if (true) return "bGqV";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 469;BA.debugLine="Return \"[,f[\"";
if (true) return "[,f[";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 471;BA.debugLine="Return \"{`6T\"";
if (true) return "{`6T";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 473;BA.debugLine="Return \"sXCJ\"";
if (true) return "sXCJ";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 475;BA.debugLine="Return \"l83=\"";
if (true) return "l83=";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 477;BA.debugLine="Return \"%N6I\"";
if (true) return "%N6I";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 479;BA.debugLine="Return \"IS-4\"";
if (true) return "IS-4";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 481;BA.debugLine="Return \"bV?P\"";
if (true) return "bV?P";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 483;BA.debugLine="Return \"2Mw}\"";
if (true) return "2Mw}";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 485;BA.debugLine="Return \"i%Oz\"";
if (true) return "i%Oz";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 487;BA.debugLine="Return \"J+,7\"";
if (true) return "J+,7";
 }else if((_v).equals("'")) { 
 //BA.debugLineNum = 489;BA.debugLine="Return \"1BB^\"";
if (true) return "1BB^";
 }else if((_v).equals("6")) { 
 //BA.debugLineNum = 491;BA.debugLine="Return \"r,W5\"";
if (true) return "r,W5";
 }else {
 //BA.debugLineNum = 493;BA.debugLine="Return v & v & v & v";
if (true) return _v+_v+_v+_v;
 };
 //BA.debugLineNum = 495;BA.debugLine="End Sub";
return "";
}
public static String  _sina_enc_stn2(String _v) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Public Sub sina_ENC_stn2(v As String) As String";
 //BA.debugLineNum = 106;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 107;BA.debugLine="Return \"_%\"";
if (true) return "_%";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 109;BA.debugLine="Return \"8i\"";
if (true) return "8i";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 111;BA.debugLine="Return \"-f\"";
if (true) return "-f";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 113;BA.debugLine="Return \"?1\"";
if (true) return "?1";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 115;BA.debugLine="Return \"JM\"";
if (true) return "JM";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 117;BA.debugLine="Return \"<*\"";
if (true) return "<*";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 119;BA.debugLine="Return \"qO\"";
if (true) return "qO";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 121;BA.debugLine="Return \"[/\"";
if (true) return "[/";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 123;BA.debugLine="Return \"D>\"";
if (true) return "D>";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 125;BA.debugLine="Return \"JF\"";
if (true) return "JF";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 127;BA.debugLine="Return \"y/\"";
if (true) return "y/";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 129;BA.debugLine="Return \"7Q\"";
if (true) return "7Q";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 131;BA.debugLine="Return \"TS\"";
if (true) return "TS";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 133;BA.debugLine="Return \"zB\"";
if (true) return "zB";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 135;BA.debugLine="Return \"9c\"";
if (true) return "9c";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 137;BA.debugLine="Return \"Ig\"";
if (true) return "Ig";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 139;BA.debugLine="Return \"lt\"";
if (true) return "lt";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 141;BA.debugLine="Return \"Yl\"";
if (true) return "Yl";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 143;BA.debugLine="Return \"&+\"";
if (true) return "&+";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 145;BA.debugLine="Return \"DT\"";
if (true) return "DT";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 147;BA.debugLine="Return \"-s\"";
if (true) return "-s";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 149;BA.debugLine="Return \"MH\"";
if (true) return "MH";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 151;BA.debugLine="Return \"aC\"";
if (true) return "aC";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 153;BA.debugLine="Return \"8n\"";
if (true) return "8n";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 155;BA.debugLine="Return \"J/\"";
if (true) return "J/";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 157;BA.debugLine="Return \"a%\"";
if (true) return "a%";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 159;BA.debugLine="Return \"J{\"";
if (true) return "J{";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 161;BA.debugLine="Return \")n\"";
if (true) return ")n";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 163;BA.debugLine="Return \"_Y\"";
if (true) return "_Y";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 165;BA.debugLine="Return \"V7\"";
if (true) return "V7";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 167;BA.debugLine="Return \"[f\"";
if (true) return "[f";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 169;BA.debugLine="Return \"2L\"";
if (true) return "2L";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 171;BA.debugLine="Return \"FM\"";
if (true) return "FM";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 173;BA.debugLine="Return \"{x\"";
if (true) return "{x";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 175;BA.debugLine="Return \"_d\"";
if (true) return "_d";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 177;BA.debugLine="Return \"Of\"";
if (true) return "Of";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 179;BA.debugLine="Return \"P[\"";
if (true) return "P[";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 181;BA.debugLine="Return \"b.\"";
if (true) return "b.";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 183;BA.debugLine="Return \"L1\"";
if (true) return "L1";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 185;BA.debugLine="Return \"LZ\"";
if (true) return "LZ";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 187;BA.debugLine="Return \"GE\"";
if (true) return "GE";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 189;BA.debugLine="Return \"rj\"";
if (true) return "rj";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 191;BA.debugLine="Return \"gG\"";
if (true) return "gG";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 193;BA.debugLine="Return \"3U\"";
if (true) return "3U";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 195;BA.debugLine="Return \"aN\"";
if (true) return "aN";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 197;BA.debugLine="Return \"+&\"";
if (true) return "+&";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 199;BA.debugLine="Return \"7m\"";
if (true) return "7m";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 201;BA.debugLine="Return \"o4\"";
if (true) return "o4";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 203;BA.debugLine="Return \"D]\"";
if (true) return "D]";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 205;BA.debugLine="Return \"pG\"";
if (true) return "pG";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 207;BA.debugLine="Return \"2>\"";
if (true) return "2>";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 209;BA.debugLine="Return \")D\"";
if (true) return ")D";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 211;BA.debugLine="Return \"zJ\"";
if (true) return "zJ";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 213;BA.debugLine="Return \"+x\"";
if (true) return "+x";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 215;BA.debugLine="Return \"0L\"";
if (true) return "0L";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 217;BA.debugLine="Return \"+6\"";
if (true) return "+6";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 219;BA.debugLine="Return \"bN\"";
if (true) return "bN";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 221;BA.debugLine="Return \".E\"";
if (true) return ".E";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 223;BA.debugLine="Return \"oi\"";
if (true) return "oi";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 225;BA.debugLine="Return \"b=\"";
if (true) return "b=";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 227;BA.debugLine="Return \"-/\"";
if (true) return "-/";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 229;BA.debugLine="Return \"(6\"";
if (true) return "(6";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 231;BA.debugLine="Return \"xp\"";
if (true) return "xp";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 233;BA.debugLine="Return \"UN\"";
if (true) return "UN";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 235;BA.debugLine="Return \"ha\"";
if (true) return "ha";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 237;BA.debugLine="Return \"&F\"";
if (true) return "&F";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 239;BA.debugLine="Return \"[e\"";
if (true) return "[e";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 241;BA.debugLine="Return \"G9\"";
if (true) return "G9";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 243;BA.debugLine="Return \"yL\"";
if (true) return "yL";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 245;BA.debugLine="Return \"In\"";
if (true) return "In";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 247;BA.debugLine="Return \"Ca\"";
if (true) return "Ca";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 249;BA.debugLine="Return \"Xg\"";
if (true) return "Xg";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 251;BA.debugLine="Return \"IP\"";
if (true) return "IP";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 253;BA.debugLine="Return \"lG\"";
if (true) return "lG";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 255;BA.debugLine="Return \"N!\"";
if (true) return "N!";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 257;BA.debugLine="Return \"OD\"";
if (true) return "OD";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 259;BA.debugLine="Return \"[c\"";
if (true) return "[c";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 261;BA.debugLine="Return \"!+\"";
if (true) return "!+";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 263;BA.debugLine="Return \"7s\"";
if (true) return "7s";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 265;BA.debugLine="Return \"m#\"";
if (true) return "m#";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 267;BA.debugLine="Return \"=b\"";
if (true) return "=b";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 269;BA.debugLine="Return \"*]\"";
if (true) return "*]";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 271;BA.debugLine="Return \"wc\"";
if (true) return "wc";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 273;BA.debugLine="Return \"#G\"";
if (true) return "#G";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 275;BA.debugLine="Return \"0T\"";
if (true) return "0T";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 277;BA.debugLine="Return \"<>\"";
if (true) return "<>";
 }else if((_v).equals("'")) { 
 //BA.debugLineNum = 279;BA.debugLine="Return \"a*\"";
if (true) return "a*";
 }else if((_v).equals("6")) { 
 //BA.debugLineNum = 281;BA.debugLine="Return \"rS\"";
if (true) return "rS";
 }else {
 //BA.debugLineNum = 283;BA.debugLine="Return v & v";
if (true) return _v+_v;
 };
 //BA.debugLineNum = 285;BA.debugLine="End Sub";
return "";
}
}
