package Fast_Accessibility.ModernPl;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class searchmodule {
private static searchmodule mostCurrent = new searchmodule();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public Fast_Accessibility.ModernPl.main _main = null;
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
public Fast_Accessibility.ModernPl.sizeviewv3 _sizeviewv3 = null;
public Fast_Accessibility.ModernPl.regular_validations _regular_validations = null;
public Fast_Accessibility.ModernPl.size_view _size_view = null;
public Fast_Accessibility.ModernPl.interchange_stn _interchange_stn = null;
public Fast_Accessibility.ModernPl.size_view301 _size_view301 = null;
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _searching_listresult(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _lists,String _text) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _i = 0;
String _str1 = "";
 //BA.debugLineNum = 10;BA.debugLine="Sub Searching_ListResult(lists As List,Text As Str";
 //BA.debugLineNum = 11;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 12;BA.debugLine="lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 15;BA.debugLine="For i=0 To lists.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (_lists.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 17;BA.debugLine="Dim str1 As String=lists.Get(i)";
_str1 = BA.ObjectToString(_lists.Get(_i));
 //BA.debugLineNum = 18;BA.debugLine="If str1.IndexOf(Text)<>-1 Then";
if (_str1.indexOf(_text)!=-1) { 
 //BA.debugLineNum = 19;BA.debugLine="lst.Add(str1)";
_lst.Add((Object)(_str1));
 };
 }
};
 //BA.debugLineNum = 23;BA.debugLine="Return lst";
if (true) return _lst;
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _searching_numberresult(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _lists,String _text) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _i = 0;
String _str1 = "";
 //BA.debugLineNum = 29;BA.debugLine="Sub Searching_NumberResult(lists As List,Text As S";
 //BA.debugLineNum = 30;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 31;BA.debugLine="lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 34;BA.debugLine="For i=0 To lists.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (_lists.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 36;BA.debugLine="Dim str1 As String=lists.Get(i)";
_str1 = BA.ObjectToString(_lists.Get(_i));
 //BA.debugLineNum = 37;BA.debugLine="If str1.IndexOf(Text)<>-1 Then";
if (_str1.indexOf(_text)!=-1) { 
 //BA.debugLineNum = 38;BA.debugLine="lst.Add(i)";
_lst.Add((Object)(_i));
 };
 }
};
 //BA.debugLineNum = 42;BA.debugLine="Return lst";
if (true) return _lst;
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return null;
}
}
