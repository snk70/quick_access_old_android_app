package Fast_Accessibility.ModernPl;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class security_class_list extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "Fast_Accessibility.ModernPl.security_class_list");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", Fast_Accessibility.ModernPl.security_class_list.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public String _vvv0 = "";
public String _vvvv1 = "";
public String _vvvv2 = "";
public String _vvvv3 = "";
public String _v7 = "";
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
public Fast_Accessibility.ModernPl.regular_validations _regular_validations = null;
public Fast_Accessibility.ModernPl.size_view _size_view = null;
public Fast_Accessibility.ModernPl.interchange_stn _interchange_stn = null;
public Fast_Accessibility.ModernPl.size_view301 _size_view301 = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Dim txt,c,e,d,l As String";
_vvv0 = "";
_vvvv1 = "";
_vvvv2 = "";
_vvvv3 = "";
_v7 = "";
 //BA.debugLineNum = 4;BA.debugLine="End Sub";
return "";
}
public String  _vvv4(String _t) throws Exception{
String _tx2 = "";
String[] _cdc = null;
int _n = 0;
String _x = "";
int _b = 0;
 //BA.debugLineNum = 32;BA.debugLine="Public Sub dec(t As String) As String";
 //BA.debugLineNum = 33;BA.debugLine="Dim tx2 As String";
_tx2 = "";
 //BA.debugLineNum = 34;BA.debugLine="Dim cdc(t.Length) As String";
_cdc = new String[_t.length()];
java.util.Arrays.fill(_cdc,"");
 //BA.debugLineNum = 36;BA.debugLine="txt=t";
_vvv0 = _t;
 //BA.debugLineNum = 38;BA.debugLine="For n=0 To t.Length/3-1";
{
final int step4 = 1;
final int limit4 = (int) (_t.length()/(double)3-1);
for (_n = (int) (0) ; (step4 > 0 && _n <= limit4) || (step4 < 0 && _n >= limit4); _n = ((int)(0 + _n + step4)) ) {
 //BA.debugLineNum = 39;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 40;BA.debugLine="x=txt.SubString(txt.Length-3)";
_x = _vvv0.substring((int) (_vvv0.length()-3));
 //BA.debugLineNum = 41;BA.debugLine="cdc(n)=moderndec(x)";
_cdc[_n] = _vvv6(_x);
 //BA.debugLineNum = 42;BA.debugLine="txt=txt.SubString2(0,txt.Length-3)";
_vvv0 = _vvv0.substring((int) (0),(int) (_vvv0.length()-3));
 }
};
 //BA.debugLineNum = 45;BA.debugLine="For b=0 To cdc.Length-1";
{
final int step10 = 1;
final int limit10 = (int) (_cdc.length-1);
for (_b = (int) (0) ; (step10 > 0 && _b <= limit10) || (step10 < 0 && _b >= limit10); _b = ((int)(0 + _b + step10)) ) {
 //BA.debugLineNum = 46;BA.debugLine="tx2=cdc(b)&tx2";
_tx2 = _cdc[_b]+_tx2;
 }
};
 //BA.debugLineNum = 49;BA.debugLine="Return tx2";
if (true) return _tx2;
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return "";
}
public String  _vvv5(String _t) throws Exception{
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 10;BA.debugLine="Public Sub enc(t As String) As String";
 //BA.debugLineNum = 11;BA.debugLine="Dim tx1 As String=\"\"";
_tx1 = "";
 //BA.debugLineNum = 12;BA.debugLine="tx1=\"\"";
_tx1 = "";
 //BA.debugLineNum = 13;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 14;BA.debugLine="txt=t";
_vvv0 = _t;
 //BA.debugLineNum = 16;BA.debugLine="For i=0 To t.Length-1";
{
final int step5 = 1;
final int limit5 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 17;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 18;BA.debugLine="x=txt.SubString(txt.Length-1)";
_x = _vvv0.substring((int) (_vvv0.length()-1));
 //BA.debugLineNum = 19;BA.debugLine="cec(i)=modernenc(x)";
_cec[_i] = _vvv7(_x);
 //BA.debugLineNum = 20;BA.debugLine="txt=txt.SubString2(0,txt.Length-1)";
_vvv0 = _vvv0.substring((int) (0),(int) (_vvv0.length()-1));
 }
};
 //BA.debugLineNum = 22;BA.debugLine="tx1=\"\"";
_tx1 = "";
 //BA.debugLineNum = 23;BA.debugLine="For j=0 To cec.Length-1";
{
final int step12 = 1;
final int limit12 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step12 > 0 && _j <= limit12) || (step12 < 0 && _j >= limit12); _j = ((int)(0 + _j + step12)) ) {
 //BA.debugLineNum = 24;BA.debugLine="tx1=cec(j)&tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 29;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 6;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _list_dec(anywheresoftware.b4a.objects.collections.List _mylist) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst1 = null;
int _i = 0;
String _str = "";
 //BA.debugLineNum = 53;BA.debugLine="Public Sub List_Dec(MyList As List) As List";
 //BA.debugLineNum = 54;BA.debugLine="Dim lst1 As List";
_lst1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 55;BA.debugLine="lst1.Initialize";
_lst1.Initialize();
 //BA.debugLineNum = 57;BA.debugLine="For i=0 To MyList.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (_mylist.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 58;BA.debugLine="Dim str As String=MyList.Get(i)";
_str = BA.ObjectToString(_mylist.Get(_i));
 //BA.debugLineNum = 59;BA.debugLine="lst1.Add(dec(str))";
_lst1.Add((Object)(_vvv4(_str)));
 }
};
 //BA.debugLineNum = 61;BA.debugLine="Return lst1";
if (true) return _lst1;
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _list_enc(anywheresoftware.b4a.objects.collections.List _mylist) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst1 = null;
int _i = 0;
String _str = "";
 //BA.debugLineNum = 64;BA.debugLine="Public Sub List_Enc(MyList As List) As List";
 //BA.debugLineNum = 65;BA.debugLine="Dim lst1 As List";
_lst1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 66;BA.debugLine="lst1.Initialize";
_lst1.Initialize();
 //BA.debugLineNum = 68;BA.debugLine="For i=0 To MyList.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (_mylist.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 69;BA.debugLine="Dim str As String=MyList.Get(i)";
_str = BA.ObjectToString(_mylist.Get(_i));
 //BA.debugLineNum = 70;BA.debugLine="lst1.Add(enc(str))";
_lst1.Add((Object)(_vvv5(_str)));
 }
};
 //BA.debugLineNum = 72;BA.debugLine="Return lst1";
if (true) return _lst1;
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return null;
}
public String  _vvv6(String _v) throws Exception{
 //BA.debugLineNum = 258;BA.debugLine="Private Sub moderndec(v As String) As String";
 //BA.debugLineNum = 259;BA.debugLine="If v=\"746\" Then";
if ((_v).equals("746")) { 
 //BA.debugLineNum = 260;BA.debugLine="Return \"0\"";
if (true) return "0";
 }else if((_v).equals("888")) { 
 //BA.debugLineNum = 262;BA.debugLine="Return \"1\"";
if (true) return "1";
 }else if((_v).equals("174")) { 
 //BA.debugLineNum = 264;BA.debugLine="Return \"2\"";
if (true) return "2";
 }else if((_v).equals("972")) { 
 //BA.debugLineNum = 266;BA.debugLine="Return \"3\"";
if (true) return "3";
 }else if((_v).equals("579")) { 
 //BA.debugLineNum = 268;BA.debugLine="Return \"4\"";
if (true) return "4";
 }else if((_v).equals("572")) { 
 //BA.debugLineNum = 270;BA.debugLine="Return \"5\"";
if (true) return "5";
 }else if((_v).equals("268")) { 
 //BA.debugLineNum = 272;BA.debugLine="Return \"7\"";
if (true) return "7";
 }else if((_v).equals("117")) { 
 //BA.debugLineNum = 274;BA.debugLine="Return \"8\"";
if (true) return "8";
 }else if((_v).equals("236")) { 
 //BA.debugLineNum = 276;BA.debugLine="Return \"9\"";
if (true) return "9";
 }else if((_v).equals("543")) { 
 //BA.debugLineNum = 278;BA.debugLine="Return \"a\"";
if (true) return "a";
 }else if((_v).equals("991")) { 
 //BA.debugLineNum = 280;BA.debugLine="Return \"b\"";
if (true) return "b";
 }else if((_v).equals("732")) { 
 //BA.debugLineNum = 282;BA.debugLine="Return \"c\"";
if (true) return "c";
 }else if((_v).equals("699")) { 
 //BA.debugLineNum = 284;BA.debugLine="Return \"d\"";
if (true) return "d";
 }else if((_v).equals("415")) { 
 //BA.debugLineNum = 286;BA.debugLine="Return \"e\"";
if (true) return "e";
 }else if((_v).equals("675")) { 
 //BA.debugLineNum = 288;BA.debugLine="Return \"f\"";
if (true) return "f";
 }else if((_v).equals("845")) { 
 //BA.debugLineNum = 290;BA.debugLine="Return \"g\"";
if (true) return "g";
 }else if((_v).equals("591")) { 
 //BA.debugLineNum = 292;BA.debugLine="Return \"h\"";
if (true) return "h";
 }else if((_v).equals("349")) { 
 //BA.debugLineNum = 294;BA.debugLine="Return \"i\"";
if (true) return "i";
 }else if((_v).equals("250")) { 
 //BA.debugLineNum = 296;BA.debugLine="Return \"j\"";
if (true) return "j";
 }else if((_v).equals("584")) { 
 //BA.debugLineNum = 298;BA.debugLine="Return \"k\"";
if (true) return "k";
 }else if((_v).equals("643")) { 
 //BA.debugLineNum = 300;BA.debugLine="Return \"l\"";
if (true) return "l";
 }else if((_v).equals("300")) { 
 //BA.debugLineNum = 302;BA.debugLine="Return \"m\"";
if (true) return "m";
 }else if((_v).equals("822")) { 
 //BA.debugLineNum = 304;BA.debugLine="Return \"n\"";
if (true) return "n";
 }else if((_v).equals("635")) { 
 //BA.debugLineNum = 306;BA.debugLine="Return \"o\"";
if (true) return "o";
 }else if((_v).equals("553")) { 
 //BA.debugLineNum = 308;BA.debugLine="Return \"p\"";
if (true) return "p";
 }else if((_v).equals("177")) { 
 //BA.debugLineNum = 310;BA.debugLine="Return \"q\"";
if (true) return "q";
 }else if((_v).equals("133")) { 
 //BA.debugLineNum = 312;BA.debugLine="Return \"r\"";
if (true) return "r";
 }else if((_v).equals("788")) { 
 //BA.debugLineNum = 314;BA.debugLine="Return \"s\"";
if (true) return "s";
 }else if((_v).equals("347")) { 
 //BA.debugLineNum = 316;BA.debugLine="Return \"t\"";
if (true) return "t";
 }else if((_v).equals("140")) { 
 //BA.debugLineNum = 318;BA.debugLine="Return \"u\"";
if (true) return "u";
 }else if((_v).equals("166")) { 
 //BA.debugLineNum = 320;BA.debugLine="Return \"v\"";
if (true) return "v";
 }else if((_v).equals("458")) { 
 //BA.debugLineNum = 322;BA.debugLine="Return \"w\"";
if (true) return "w";
 }else if((_v).equals("243")) { 
 //BA.debugLineNum = 324;BA.debugLine="Return \"x\"";
if (true) return "x";
 }else if((_v).equals("323")) { 
 //BA.debugLineNum = 326;BA.debugLine="Return \"y\"";
if (true) return "y";
 }else if((_v).equals("194")) { 
 //BA.debugLineNum = 328;BA.debugLine="Return \"z\"";
if (true) return "z";
 }else if((_v).equals("471")) { 
 //BA.debugLineNum = 330;BA.debugLine="Return \"A\"";
if (true) return "A";
 }else if((_v).equals("138")) { 
 //BA.debugLineNum = 332;BA.debugLine="Return \"B\"";
if (true) return "B";
 }else if((_v).equals("296")) { 
 //BA.debugLineNum = 334;BA.debugLine="Return \"C\"";
if (true) return "C";
 }else if((_v).equals("970")) { 
 //BA.debugLineNum = 336;BA.debugLine="Return \"D\"";
if (true) return "D";
 }else if((_v).equals("477")) { 
 //BA.debugLineNum = 338;BA.debugLine="Return \"E\"";
if (true) return "E";
 }else if((_v).equals("631")) { 
 //BA.debugLineNum = 340;BA.debugLine="Return \"F\"";
if (true) return "F";
 }else if((_v).equals("738")) { 
 //BA.debugLineNum = 342;BA.debugLine="Return \"G\"";
if (true) return "G";
 }else if((_v).equals("714")) { 
 //BA.debugLineNum = 344;BA.debugLine="Return \"H\"";
if (true) return "H";
 }else if((_v).equals("507")) { 
 //BA.debugLineNum = 346;BA.debugLine="Return \"I\"";
if (true) return "I";
 }else if((_v).equals("157")) { 
 //BA.debugLineNum = 348;BA.debugLine="Return \"J\"";
if (true) return "J";
 }else if((_v).equals("124")) { 
 //BA.debugLineNum = 350;BA.debugLine="Return \"K\"";
if (true) return "K";
 }else if((_v).equals("102")) { 
 //BA.debugLineNum = 352;BA.debugLine="Return \"L\"";
if (true) return "L";
 }else if((_v).equals("218")) { 
 //BA.debugLineNum = 354;BA.debugLine="Return \"M\"";
if (true) return "M";
 }else if((_v).equals("259")) { 
 //BA.debugLineNum = 356;BA.debugLine="Return \"N\"";
if (true) return "N";
 }else if((_v).equals("961")) { 
 //BA.debugLineNum = 358;BA.debugLine="Return \"O\"";
if (true) return "O";
 }else if((_v).equals("603")) { 
 //BA.debugLineNum = 360;BA.debugLine="Return \"P\"";
if (true) return "P";
 }else if((_v).equals("404")) { 
 //BA.debugLineNum = 362;BA.debugLine="Return \"Q\"";
if (true) return "Q";
 }else if((_v).equals("545")) { 
 //BA.debugLineNum = 364;BA.debugLine="Return \"R\"";
if (true) return "R";
 }else if((_v).equals("590")) { 
 //BA.debugLineNum = 366;BA.debugLine="Return \"S\"";
if (true) return "S";
 }else if((_v).equals("346")) { 
 //BA.debugLineNum = 368;BA.debugLine="Return \"T\"";
if (true) return "T";
 }else if((_v).equals("538")) { 
 //BA.debugLineNum = 370;BA.debugLine="Return \"U\"";
if (true) return "U";
 }else if((_v).equals("518")) { 
 //BA.debugLineNum = 372;BA.debugLine="Return \"V\"";
if (true) return "V";
 }else if((_v).equals("502")) { 
 //BA.debugLineNum = 374;BA.debugLine="Return \"W\"";
if (true) return "W";
 }else if((_v).equals("539")) { 
 //BA.debugLineNum = 376;BA.debugLine="Return \"X\"";
if (true) return "X";
 }else if((_v).equals("478")) { 
 //BA.debugLineNum = 378;BA.debugLine="Return \"Y\"";
if (true) return "Y";
 }else if((_v).equals("768")) { 
 //BA.debugLineNum = 380;BA.debugLine="Return \"Z\"";
if (true) return "Z";
 }else if((_v).equals("196")) { 
 //BA.debugLineNum = 382;BA.debugLine="Return \"~\"";
if (true) return "~";
 }else if((_v).equals("633")) { 
 //BA.debugLineNum = 384;BA.debugLine="Return \"`\"";
if (true) return "`";
 }else if((_v).equals("589")) { 
 //BA.debugLineNum = 386;BA.debugLine="Return \"!\"";
if (true) return "!";
 }else if((_v).equals("476")) { 
 //BA.debugLineNum = 388;BA.debugLine="Return \"#\"";
if (true) return "#";
 }else if((_v).equals("321")) { 
 //BA.debugLineNum = 390;BA.debugLine="Return \"$\"";
if (true) return "$";
 }else if((_v).equals("304")) { 
 //BA.debugLineNum = 392;BA.debugLine="Return \"%\"";
if (true) return "%";
 }else if((_v).equals("807")) { 
 //BA.debugLineNum = 394;BA.debugLine="Return \"^\"";
if (true) return "^";
 }else if((_v).equals("394")) { 
 //BA.debugLineNum = 396;BA.debugLine="Return \"&\"";
if (true) return "&";
 }else if((_v).equals("377")) { 
 //BA.debugLineNum = 398;BA.debugLine="Return \"*\"";
if (true) return "*";
 }else if((_v).equals("771")) { 
 //BA.debugLineNum = 400;BA.debugLine="Return \"(\"";
if (true) return "(";
 }else if((_v).equals("951")) { 
 //BA.debugLineNum = 402;BA.debugLine="Return \")\"";
if (true) return ")";
 }else if((_v).equals("904")) { 
 //BA.debugLineNum = 404;BA.debugLine="Return \"_\"";
if (true) return "_";
 }else if((_v).equals("585")) { 
 //BA.debugLineNum = 406;BA.debugLine="Return \"-\"";
if (true) return "-";
 }else if((_v).equals("577")) { 
 //BA.debugLineNum = 408;BA.debugLine="Return \"=\"";
if (true) return "=";
 }else if((_v).equals("529")) { 
 //BA.debugLineNum = 410;BA.debugLine="Return \"+\"";
if (true) return "+";
 }else if((_v).equals("913")) { 
 //BA.debugLineNum = 412;BA.debugLine="Return \"/\"";
if (true) return "/";
 }else if((_v).equals("628")) { 
 //BA.debugLineNum = 414;BA.debugLine="Return \"\\\"";
if (true) return "\\";
 }else if((_v).equals("384")) { 
 //BA.debugLineNum = 416;BA.debugLine="Return \"?\"";
if (true) return "?";
 }else if((_v).equals("535")) { 
 //BA.debugLineNum = 418;BA.debugLine="Return \"<\"";
if (true) return "<";
 }else if((_v).equals("760")) { 
 //BA.debugLineNum = 420;BA.debugLine="Return \">\"";
if (true) return ">";
 }else if((_v).equals("911")) { 
 //BA.debugLineNum = 422;BA.debugLine="Return \".\"";
if (true) return ".";
 }else if((_v).equals("651")) { 
 //BA.debugLineNum = 424;BA.debugLine="Return \"{\"";
if (true) return "{";
 }else if((_v).equals("975")) { 
 //BA.debugLineNum = 426;BA.debugLine="Return \"}\"";
if (true) return "}";
 }else if((_v).equals("647")) { 
 //BA.debugLineNum = 428;BA.debugLine="Return \"[\"";
if (true) return "[";
 }else if((_v).equals("598")) { 
 //BA.debugLineNum = 430;BA.debugLine="Return \"]\"";
if (true) return "]";
 }else if((_v).equals("777")) { 
 //BA.debugLineNum = 432;BA.debugLine="Return \"\"\"\"";
if (true) return "\"";
 }else if((_v).equals("568")) { 
 //BA.debugLineNum = 434;BA.debugLine="Return \",\"";
if (true) return ",";
 }else {
 //BA.debugLineNum = 436;BA.debugLine="Return v.SubString(v.Length-1)";
if (true) return _v.substring((int) (_v.length()-1));
 };
 //BA.debugLineNum = 438;BA.debugLine="End Sub";
return "";
}
public String  _vvv7(String _v) throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Private Sub modernenc(v As String) As String";
 //BA.debugLineNum = 77;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 78;BA.debugLine="Return \"746\"";
if (true) return "746";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 80;BA.debugLine="Return \"888\"";
if (true) return "888";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 82;BA.debugLine="Return \"174\"";
if (true) return "174";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 84;BA.debugLine="Return \"972\"";
if (true) return "972";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 86;BA.debugLine="Return \"579\"";
if (true) return "579";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 88;BA.debugLine="Return \"572\"";
if (true) return "572";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 90;BA.debugLine="Return \"268\"";
if (true) return "268";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 92;BA.debugLine="Return \"117\"";
if (true) return "117";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 94;BA.debugLine="Return \"236\"";
if (true) return "236";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 96;BA.debugLine="Return \"543\"";
if (true) return "543";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 98;BA.debugLine="Return \"991\"";
if (true) return "991";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 100;BA.debugLine="Return \"732\"";
if (true) return "732";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 102;BA.debugLine="Return \"699\"";
if (true) return "699";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 104;BA.debugLine="Return \"415\"";
if (true) return "415";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 106;BA.debugLine="Return \"675\"";
if (true) return "675";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 108;BA.debugLine="Return \"845\"";
if (true) return "845";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 110;BA.debugLine="Return \"591\"";
if (true) return "591";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 112;BA.debugLine="Return \"349\"";
if (true) return "349";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 114;BA.debugLine="Return \"250\"";
if (true) return "250";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 116;BA.debugLine="Return \"584\"";
if (true) return "584";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 118;BA.debugLine="Return \"643\"";
if (true) return "643";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 120;BA.debugLine="Return \"300\"";
if (true) return "300";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 122;BA.debugLine="Return \"822\"";
if (true) return "822";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 124;BA.debugLine="Return \"635\"";
if (true) return "635";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 126;BA.debugLine="Return \"553\"";
if (true) return "553";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 128;BA.debugLine="Return \"177\"";
if (true) return "177";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 130;BA.debugLine="Return \"133\"";
if (true) return "133";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 132;BA.debugLine="Return \"788\"";
if (true) return "788";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 134;BA.debugLine="Return \"347\"";
if (true) return "347";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 136;BA.debugLine="Return \"140\"";
if (true) return "140";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 138;BA.debugLine="Return \"166\"";
if (true) return "166";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 140;BA.debugLine="Return \"458\"";
if (true) return "458";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 142;BA.debugLine="Return \"243\"";
if (true) return "243";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 144;BA.debugLine="Return \"323\"";
if (true) return "323";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 146;BA.debugLine="Return \"194\"";
if (true) return "194";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 148;BA.debugLine="Return \"471\"";
if (true) return "471";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 150;BA.debugLine="Return \"138\"";
if (true) return "138";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 152;BA.debugLine="Return \"296\"";
if (true) return "296";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 154;BA.debugLine="Return \"970\"";
if (true) return "970";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 156;BA.debugLine="Return \"477\"";
if (true) return "477";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 158;BA.debugLine="Return \"631\"";
if (true) return "631";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 160;BA.debugLine="Return \"738\"";
if (true) return "738";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 162;BA.debugLine="Return \"714\"";
if (true) return "714";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 164;BA.debugLine="Return \"507\"";
if (true) return "507";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 166;BA.debugLine="Return \"157\"";
if (true) return "157";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 168;BA.debugLine="Return \"124\"";
if (true) return "124";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 170;BA.debugLine="Return \"102\"";
if (true) return "102";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 172;BA.debugLine="Return \"218\"";
if (true) return "218";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 174;BA.debugLine="Return \"259\"";
if (true) return "259";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 176;BA.debugLine="Return \"961\"";
if (true) return "961";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 178;BA.debugLine="Return \"603\"";
if (true) return "603";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 180;BA.debugLine="Return \"404\"";
if (true) return "404";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 182;BA.debugLine="Return \"545\"";
if (true) return "545";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 184;BA.debugLine="Return \"590\"";
if (true) return "590";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 186;BA.debugLine="Return \"346\"";
if (true) return "346";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 188;BA.debugLine="Return \"538\"";
if (true) return "538";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 190;BA.debugLine="Return \"518\"";
if (true) return "518";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 192;BA.debugLine="Return \"502\"";
if (true) return "502";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 194;BA.debugLine="Return \"539\"";
if (true) return "539";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 196;BA.debugLine="Return \"478\"";
if (true) return "478";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 198;BA.debugLine="Return \"768\"";
if (true) return "768";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 200;BA.debugLine="Return \"196\"";
if (true) return "196";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 202;BA.debugLine="Return \"633\"";
if (true) return "633";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 204;BA.debugLine="Return \"589\"";
if (true) return "589";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 206;BA.debugLine="Return \"476\"";
if (true) return "476";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 208;BA.debugLine="Return \"321\"";
if (true) return "321";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 210;BA.debugLine="Return \"304\"";
if (true) return "304";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 212;BA.debugLine="Return \"807\"";
if (true) return "807";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 214;BA.debugLine="Return \"394\"";
if (true) return "394";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 216;BA.debugLine="Return \"377\"";
if (true) return "377";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 218;BA.debugLine="Return \"771\"";
if (true) return "771";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 220;BA.debugLine="Return \"951\"";
if (true) return "951";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 222;BA.debugLine="Return \"904\"";
if (true) return "904";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 224;BA.debugLine="Return \"585\"";
if (true) return "585";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 226;BA.debugLine="Return \"577\"";
if (true) return "577";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 228;BA.debugLine="Return \"529\"";
if (true) return "529";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 230;BA.debugLine="Return \"913\"";
if (true) return "913";
 }else if((_v).equals("\\")) { 
 //BA.debugLineNum = 232;BA.debugLine="Return \"628\"";
if (true) return "628";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 234;BA.debugLine="Return \"384\"";
if (true) return "384";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 236;BA.debugLine="Return \"535\"";
if (true) return "535";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 238;BA.debugLine="Return \"760\"";
if (true) return "760";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 240;BA.debugLine="Return \"911\"";
if (true) return "911";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 242;BA.debugLine="Return \"651\"";
if (true) return "651";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 244;BA.debugLine="Return \"975\"";
if (true) return "975";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 246;BA.debugLine="Return \"647\"";
if (true) return "647";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 248;BA.debugLine="Return \"598\"";
if (true) return "598";
 }else if((_v).equals("\"")) { 
 //BA.debugLineNum = 250;BA.debugLine="Return \"777\"";
if (true) return "777";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 252;BA.debugLine="Return \"568\"";
if (true) return "568";
 }else {
 //BA.debugLineNum = 254;BA.debugLine="Return v&v&v";
if (true) return _v+_v+_v;
 };
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
