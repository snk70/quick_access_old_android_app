package Fast_Accessibility.ModernPl;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class regular_validations {
private static regular_validations mostCurrent = new regular_validations();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvv4 = null;
public Fast_Accessibility.ModernPl.main _vvvv5 = null;
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
public Fast_Accessibility.ModernPl.size_view _size_view = null;
public Fast_Accessibility.ModernPl.interchange_stn _interchange_stn = null;
public Fast_Accessibility.ModernPl.size_view301 _size_view301 = null;
public static boolean  _email_validation(anywheresoftware.b4a.BA _ba,String _email_address) throws Exception{
boolean _valid = false;
int _atsain = 0;
int _dat = 0;
 //BA.debugLineNum = 11;BA.debugLine="Sub Email_validation(Email_Address As String) As B";
 //BA.debugLineNum = 13;BA.debugLine="Dim valid As Boolean=True";
_valid = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 15;BA.debugLine="If Email_Address.IndexOf(\"@\")=-1 Or Email_Address";
if (_email_address.indexOf("@")==-1 || _email_address.indexOf(".")==-1) { 
 //BA.debugLineNum = 17;BA.debugLine="valid=False";
_valid = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 25;BA.debugLine="Dim Atsain=Email_Address.IndexOf(\"@\"),dat=Email_";
_atsain = _email_address.indexOf("@");
_dat = _email_address.indexOf(".");
 //BA.debugLineNum = 27;BA.debugLine="If Email_Address.IndexOf2(\"@\",Atsain+1)<>-1 Then";
if (_email_address.indexOf("@",(int) (_atsain+1))!=-1) { 
 //BA.debugLineNum = 28;BA.debugLine="valid=False";
_valid = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 32;BA.debugLine="Do While Email_Address.IndexOf2(\".\",dat+1) <>-1";
while (_email_address.indexOf(".",(int) (_dat+1))!=-1) {
 //BA.debugLineNum = 34;BA.debugLine="dat=Email_Address.IndexOf2(\".\",dat+1)";
_dat = _email_address.indexOf(".",(int) (_dat+1));
 }
;
 //BA.debugLineNum = 39;BA.debugLine="If Atsain>dat Then";
if (_atsain>_dat) { 
 //BA.debugLineNum = 41;BA.debugLine="valid=False";
_valid = anywheresoftware.b4a.keywords.Common.False;
 };
 };
 //BA.debugLineNum = 49;BA.debugLine="Return valid";
if (true) return _valid;
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return false;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
