package Fast_Accessibility.ModernPl;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class interchange_stn {
private static interchange_stn mostCurrent = new interchange_stn();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static int _lng = 0;
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
public Fast_Accessibility.ModernPl.searchmodule _searchmodule = null;
public Fast_Accessibility.ModernPl.sizeviewv3 _sizeviewv3 = null;
public Fast_Accessibility.ModernPl.regular_validations _regular_validations = null;
public Fast_Accessibility.ModernPl.size_view _size_view = null;
public Fast_Accessibility.ModernPl.size_view301 _size_view301 = null;
public static String  _decryption_query_string_stn(anywheresoftware.b4a.BA _ba,String _t) throws Exception{
String _txt = "";
String _tx2 = "";
String[] _cdc = null;
int _n = 0;
String _x = "";
int _b = 0;
 //BA.debugLineNum = 223;BA.debugLine="Public Sub Decryption_Query_String_stn( t As Strin";
 //BA.debugLineNum = 224;BA.debugLine="lng =2";
_lng = (int) (2);
 //BA.debugLineNum = 227;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 229;BA.debugLine="Dim tx2 As String";
_tx2 = "";
 //BA.debugLineNum = 230;BA.debugLine="Dim cdc(t.Length) As String";
_cdc = new String[_t.length()];
java.util.Arrays.fill(_cdc,"");
 //BA.debugLineNum = 232;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 234;BA.debugLine="For n = 0 To t.Length / lng - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()/(double)_lng-1);
for (_n = (int) (0) ; (step6 > 0 && _n <= limit6) || (step6 < 0 && _n >= limit6); _n = ((int)(0 + _n + step6)) ) {
 //BA.debugLineNum = 235;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 236;BA.debugLine="x = txt.Substring(txt.Length - lng)";
_x = _txt.substring((int) (_txt.length()-_lng));
 //BA.debugLineNum = 237;BA.debugLine="cdc(n) = sina_DEC_Query_String_stn(x)";
_cdc[_n] = _sina_dec_query_string_stn(_ba,_x);
 //BA.debugLineNum = 238;BA.debugLine="txt = txt.SubString2(0, txt.Length - lng)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-_lng));
 }
};
 //BA.debugLineNum = 241;BA.debugLine="For b = 0 To cdc.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cdc.length-1);
for (_b = (int) (0) ; (step12 > 0 && _b <= limit12) || (step12 < 0 && _b >= limit12); _b = ((int)(0 + _b + step12)) ) {
 //BA.debugLineNum = 242;BA.debugLine="tx2 = cdc(b) & tx2";
_tx2 = _cdc[_b]+_tx2;
 }
};
 //BA.debugLineNum = 245;BA.debugLine="Return tx2";
if (true) return _tx2;
 //BA.debugLineNum = 246;BA.debugLine="End Sub";
return "";
}
public static String  _encryption_query_string_stn(anywheresoftware.b4a.BA _ba,String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 7;BA.debugLine="Public Sub Encryption_Query_String_stn(t As String";
 //BA.debugLineNum = 9;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 12;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 13;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 14;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 16;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 17;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 18;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 19;BA.debugLine="cec(i) = sina_ENC_Query_String_stn(x)";
_cec[_i] = _sina_enc_query_string_stn(_ba,_x);
 //BA.debugLineNum = 20;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 22;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 23;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
for (_j = (int) (0) ; (step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13); _j = ((int)(0 + _j + step13)) ) {
 //BA.debugLineNum = 24;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 29;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Dim lng As Int";
_lng = 0;
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public static String  _sina_dec_query_string_stn(anywheresoftware.b4a.BA _ba,String _v) throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Public Sub sina_DEC_Query_String_stn(v As String)";
 //BA.debugLineNum = 250;BA.debugLine="If v=\"wW\" Then";
if ((_v).equals("wW")) { 
 //BA.debugLineNum = 251;BA.debugLine="Return \"0\"";
if (true) return "0";
 }else if((_v).equals("0A")) { 
 //BA.debugLineNum = 253;BA.debugLine="Return \"1\"";
if (true) return "1";
 }else if((_v).equals("LI")) { 
 //BA.debugLineNum = 255;BA.debugLine="Return \"2\"";
if (true) return "2";
 }else if((_v).equals("p5")) { 
 //BA.debugLineNum = 257;BA.debugLine="Return \"3\"";
if (true) return "3";
 }else if((_v).equals("Yf")) { 
 //BA.debugLineNum = 259;BA.debugLine="Return \"4\"";
if (true) return "4";
 }else if((_v).equals("9s")) { 
 //BA.debugLineNum = 261;BA.debugLine="Return \"5\"";
if (true) return "5";
 }else if((_v).equals("QD")) { 
 //BA.debugLineNum = 263;BA.debugLine="Return \"7\"";
if (true) return "7";
 }else if((_v).equals("1Y")) { 
 //BA.debugLineNum = 265;BA.debugLine="Return \"8\"";
if (true) return "8";
 }else if((_v).equals("vy")) { 
 //BA.debugLineNum = 267;BA.debugLine="Return \"9\"";
if (true) return "9";
 }else if((_v).equals("ZS")) { 
 //BA.debugLineNum = 269;BA.debugLine="Return \"a\"";
if (true) return "a";
 }else if((_v).equals("7P")) { 
 //BA.debugLineNum = 271;BA.debugLine="Return \"b\"";
if (true) return "b";
 }else if((_v).equals("NM")) { 
 //BA.debugLineNum = 273;BA.debugLine="Return \"c\"";
if (true) return "c";
 }else if((_v).equals("uO")) { 
 //BA.debugLineNum = 275;BA.debugLine="Return \"d\"";
if (true) return "d";
 }else if((_v).equals("W9")) { 
 //BA.debugLineNum = 277;BA.debugLine="Return \"e\"";
if (true) return "e";
 }else if((_v).equals("N2")) { 
 //BA.debugLineNum = 279;BA.debugLine="Return \"f\"";
if (true) return "f";
 }else if((_v).equals("co")) { 
 //BA.debugLineNum = 281;BA.debugLine="Return \"g\"";
if (true) return "g";
 }else if((_v).equals("bb")) { 
 //BA.debugLineNum = 283;BA.debugLine="Return \"h\"";
if (true) return "h";
 }else if((_v).equals("fb")) { 
 //BA.debugLineNum = 285;BA.debugLine="Return \"i\"";
if (true) return "i";
 }else if((_v).equals("ru")) { 
 //BA.debugLineNum = 287;BA.debugLine="Return \"j\"";
if (true) return "j";
 }else if((_v).equals("fw")) { 
 //BA.debugLineNum = 289;BA.debugLine="Return \"k\"";
if (true) return "k";
 }else if((_v).equals("gM")) { 
 //BA.debugLineNum = 291;BA.debugLine="Return \"l\"";
if (true) return "l";
 }else if((_v).equals("Ui")) { 
 //BA.debugLineNum = 293;BA.debugLine="Return \"m\"";
if (true) return "m";
 }else if((_v).equals("4b")) { 
 //BA.debugLineNum = 295;BA.debugLine="Return \"n\"";
if (true) return "n";
 }else if((_v).equals("WF")) { 
 //BA.debugLineNum = 297;BA.debugLine="Return \"o\"";
if (true) return "o";
 }else if((_v).equals("OT")) { 
 //BA.debugLineNum = 299;BA.debugLine="Return \"p\"";
if (true) return "p";
 }else if((_v).equals("sf")) { 
 //BA.debugLineNum = 301;BA.debugLine="Return \"q\"";
if (true) return "q";
 }else if((_v).equals("Ri")) { 
 //BA.debugLineNum = 303;BA.debugLine="Return \"r\"";
if (true) return "r";
 }else if((_v).equals("DN")) { 
 //BA.debugLineNum = 305;BA.debugLine="Return \"s\"";
if (true) return "s";
 }else if((_v).equals("TS")) { 
 //BA.debugLineNum = 307;BA.debugLine="Return \"t\"";
if (true) return "t";
 }else if((_v).equals("Il")) { 
 //BA.debugLineNum = 309;BA.debugLine="Return \"u\"";
if (true) return "u";
 }else if((_v).equals("2e")) { 
 //BA.debugLineNum = 311;BA.debugLine="Return \"v\"";
if (true) return "v";
 }else if((_v).equals("qF")) { 
 //BA.debugLineNum = 313;BA.debugLine="Return \"w\"";
if (true) return "w";
 }else if((_v).equals("GT")) { 
 //BA.debugLineNum = 315;BA.debugLine="Return \"x\"";
if (true) return "x";
 }else if((_v).equals("S7")) { 
 //BA.debugLineNum = 317;BA.debugLine="Return \"y\"";
if (true) return "y";
 }else if((_v).equals("Zf")) { 
 //BA.debugLineNum = 319;BA.debugLine="Return \"z\"";
if (true) return "z";
 }else if((_v).equals("1q")) { 
 //BA.debugLineNum = 321;BA.debugLine="Return \"A\"";
if (true) return "A";
 }else if((_v).equals("LY")) { 
 //BA.debugLineNum = 323;BA.debugLine="Return \"B\"";
if (true) return "B";
 }else if((_v).equals("2o")) { 
 //BA.debugLineNum = 325;BA.debugLine="Return \"C\"";
if (true) return "C";
 }else if((_v).equals("TD")) { 
 //BA.debugLineNum = 327;BA.debugLine="Return \"D\"";
if (true) return "D";
 }else if((_v).equals("76")) { 
 //BA.debugLineNum = 329;BA.debugLine="Return \"E\"";
if (true) return "E";
 }else if((_v).equals("Cm")) { 
 //BA.debugLineNum = 331;BA.debugLine="Return \"F\"";
if (true) return "F";
 }else if((_v).equals("9T")) { 
 //BA.debugLineNum = 333;BA.debugLine="Return \"G\"";
if (true) return "G";
 }else if((_v).equals("mo")) { 
 //BA.debugLineNum = 335;BA.debugLine="Return \"H\"";
if (true) return "H";
 }else if((_v).equals("5L")) { 
 //BA.debugLineNum = 337;BA.debugLine="Return \"I\"";
if (true) return "I";
 }else if((_v).equals("9Y")) { 
 //BA.debugLineNum = 339;BA.debugLine="Return \"J\"";
if (true) return "J";
 }else if((_v).equals("VE")) { 
 //BA.debugLineNum = 341;BA.debugLine="Return \"K\"";
if (true) return "K";
 }else if((_v).equals("cR")) { 
 //BA.debugLineNum = 343;BA.debugLine="Return \"L\"";
if (true) return "L";
 }else if((_v).equals("lX")) { 
 //BA.debugLineNum = 345;BA.debugLine="Return \"M\"";
if (true) return "M";
 }else if((_v).equals("2Z")) { 
 //BA.debugLineNum = 347;BA.debugLine="Return \"N\"";
if (true) return "N";
 }else if((_v).equals("3x")) { 
 //BA.debugLineNum = 349;BA.debugLine="Return \"O\"";
if (true) return "O";
 }else if((_v).equals("pz")) { 
 //BA.debugLineNum = 351;BA.debugLine="Return \"P\"";
if (true) return "P";
 }else if((_v).equals("kg")) { 
 //BA.debugLineNum = 353;BA.debugLine="Return \"Q\"";
if (true) return "Q";
 }else if((_v).equals("oP")) { 
 //BA.debugLineNum = 355;BA.debugLine="Return \"R\"";
if (true) return "R";
 }else if((_v).equals("3F")) { 
 //BA.debugLineNum = 357;BA.debugLine="Return \"S\"";
if (true) return "S";
 }else if((_v).equals("3E")) { 
 //BA.debugLineNum = 359;BA.debugLine="Return \"T\"";
if (true) return "T";
 }else if((_v).equals("ST")) { 
 //BA.debugLineNum = 361;BA.debugLine="Return \"U\"";
if (true) return "U";
 }else if((_v).equals("pV")) { 
 //BA.debugLineNum = 363;BA.debugLine="Return \"V\"";
if (true) return "V";
 }else if((_v).equals("Zg")) { 
 //BA.debugLineNum = 365;BA.debugLine="Return \"W\"";
if (true) return "W";
 }else if((_v).equals("s4")) { 
 //BA.debugLineNum = 367;BA.debugLine="Return \"X\"";
if (true) return "X";
 }else if((_v).equals("Ah")) { 
 //BA.debugLineNum = 369;BA.debugLine="Return \"Y\"";
if (true) return "Y";
 }else if((_v).equals("bH")) { 
 //BA.debugLineNum = 371;BA.debugLine="Return \"Z\"";
if (true) return "Z";
 }else if((_v).equals("tP")) { 
 //BA.debugLineNum = 373;BA.debugLine="Return \"~\"";
if (true) return "~";
 }else if((_v).equals("qw")) { 
 //BA.debugLineNum = 375;BA.debugLine="Return \"`\"";
if (true) return "`";
 }else if((_v).equals("c7")) { 
 //BA.debugLineNum = 377;BA.debugLine="Return \"!\"";
if (true) return "!";
 }else if((_v).equals("oh")) { 
 //BA.debugLineNum = 379;BA.debugLine="Return \"#\"";
if (true) return "#";
 }else if((_v).equals("9b")) { 
 //BA.debugLineNum = 381;BA.debugLine="Return \"$\"";
if (true) return "$";
 }else if((_v).equals("q0")) { 
 //BA.debugLineNum = 383;BA.debugLine="Return \"%\"";
if (true) return "%";
 }else if((_v).equals("lW")) { 
 //BA.debugLineNum = 385;BA.debugLine="Return \"^\"";
if (true) return "^";
 }else if((_v).equals("su")) { 
 //BA.debugLineNum = 387;BA.debugLine="Return \"&\"";
if (true) return "&";
 }else if((_v).equals("bS")) { 
 //BA.debugLineNum = 389;BA.debugLine="Return \"*\"";
if (true) return "*";
 }else if((_v).equals("0p")) { 
 //BA.debugLineNum = 391;BA.debugLine="Return \"(\"";
if (true) return "(";
 }else if((_v).equals("GC")) { 
 //BA.debugLineNum = 393;BA.debugLine="Return \")\"";
if (true) return ")";
 }else if((_v).equals("bc")) { 
 //BA.debugLineNum = 395;BA.debugLine="Return \"_\"";
if (true) return "_";
 }else if((_v).equals("YA")) { 
 //BA.debugLineNum = 397;BA.debugLine="Return \"-\"";
if (true) return "-";
 }else if((_v).equals("4i")) { 
 //BA.debugLineNum = 399;BA.debugLine="Return \"=\"";
if (true) return "=";
 }else if((_v).equals("N9")) { 
 //BA.debugLineNum = 401;BA.debugLine="Return \"+\"";
if (true) return "+";
 }else if((_v).equals("i8")) { 
 //BA.debugLineNum = 403;BA.debugLine="Return \"/\"";
if (true) return "/";
 }else if((_v).equals("xK")) { 
 //BA.debugLineNum = 405;BA.debugLine="Return \"?\"";
if (true) return "?";
 }else if((_v).equals("I3")) { 
 //BA.debugLineNum = 407;BA.debugLine="Return \"<\"";
if (true) return "<";
 }else if((_v).equals("2J")) { 
 //BA.debugLineNum = 409;BA.debugLine="Return \">\"";
if (true) return ">";
 }else if((_v).equals("Nc")) { 
 //BA.debugLineNum = 411;BA.debugLine="Return \".\"";
if (true) return ".";
 }else if((_v).equals("m7")) { 
 //BA.debugLineNum = 413;BA.debugLine="Return \"{\"";
if (true) return "{";
 }else if((_v).equals("0J")) { 
 //BA.debugLineNum = 415;BA.debugLine="Return \"}\"";
if (true) return "}";
 }else if((_v).equals("7S")) { 
 //BA.debugLineNum = 417;BA.debugLine="Return \"[\"";
if (true) return "[";
 }else if((_v).equals("QK")) { 
 //BA.debugLineNum = 419;BA.debugLine="Return \"]\"";
if (true) return "]";
 }else if((_v).equals("1f")) { 
 //BA.debugLineNum = 421;BA.debugLine="Return \",\"";
if (true) return ",";
 }else if((_v).equals("PF")) { 
 //BA.debugLineNum = 423;BA.debugLine="Return \"'\"";
if (true) return "'";
 }else if((_v).equals("si")) { 
 //BA.debugLineNum = 425;BA.debugLine="Return \"6\"";
if (true) return "6";
 }else {
 //BA.debugLineNum = 427;BA.debugLine="Return v.Substring(v.Length - 1)";
if (true) return _v.substring((int) (_v.length()-1));
 };
 //BA.debugLineNum = 430;BA.debugLine="End Sub";
return "";
}
public static String  _sina_enc_query_string_stn(anywheresoftware.b4a.BA _ba,String _v) throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Public Sub sina_ENC_Query_String_stn(v As String)";
 //BA.debugLineNum = 36;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 37;BA.debugLine="Return \"wW\"";
if (true) return "wW";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 39;BA.debugLine="Return \"0A\"";
if (true) return "0A";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 41;BA.debugLine="Return \"LI\"";
if (true) return "LI";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 43;BA.debugLine="Return \"p5\"";
if (true) return "p5";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 45;BA.debugLine="Return \"Yf\"";
if (true) return "Yf";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 47;BA.debugLine="Return \"9s\"";
if (true) return "9s";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 49;BA.debugLine="Return \"QD\"";
if (true) return "QD";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 51;BA.debugLine="Return \"1Y\"";
if (true) return "1Y";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 53;BA.debugLine="Return \"vy\"";
if (true) return "vy";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 55;BA.debugLine="Return \"ZS\"";
if (true) return "ZS";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 57;BA.debugLine="Return \"7P\"";
if (true) return "7P";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 59;BA.debugLine="Return \"NM\"";
if (true) return "NM";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 61;BA.debugLine="Return \"uO\"";
if (true) return "uO";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 63;BA.debugLine="Return \"W9\"";
if (true) return "W9";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 65;BA.debugLine="Return \"N2\"";
if (true) return "N2";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 67;BA.debugLine="Return \"co\"";
if (true) return "co";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 69;BA.debugLine="Return \"bb\"";
if (true) return "bb";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 71;BA.debugLine="Return \"fb\"";
if (true) return "fb";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 73;BA.debugLine="Return \"ru\"";
if (true) return "ru";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 75;BA.debugLine="Return \"fw\"";
if (true) return "fw";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 77;BA.debugLine="Return \"gM\"";
if (true) return "gM";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 79;BA.debugLine="Return \"Ui\"";
if (true) return "Ui";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 81;BA.debugLine="Return \"4b\"";
if (true) return "4b";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 83;BA.debugLine="Return \"WF\"";
if (true) return "WF";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 85;BA.debugLine="Return \"OT\"";
if (true) return "OT";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 87;BA.debugLine="Return \"sf\"";
if (true) return "sf";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 89;BA.debugLine="Return \"Ri\"";
if (true) return "Ri";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 91;BA.debugLine="Return \"DN\"";
if (true) return "DN";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 93;BA.debugLine="Return \"TS\"";
if (true) return "TS";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 95;BA.debugLine="Return \"Il\"";
if (true) return "Il";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 97;BA.debugLine="Return \"2e\"";
if (true) return "2e";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 99;BA.debugLine="Return \"qF\"";
if (true) return "qF";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 101;BA.debugLine="Return \"GT\"";
if (true) return "GT";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 103;BA.debugLine="Return \"S7\"";
if (true) return "S7";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 105;BA.debugLine="Return \"Zf\"";
if (true) return "Zf";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 107;BA.debugLine="Return \"1q\"";
if (true) return "1q";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 109;BA.debugLine="Return \"LY\"";
if (true) return "LY";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 111;BA.debugLine="Return \"2o\"";
if (true) return "2o";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 113;BA.debugLine="Return \"TD\"";
if (true) return "TD";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 115;BA.debugLine="Return \"76\"";
if (true) return "76";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 117;BA.debugLine="Return \"Cm\"";
if (true) return "Cm";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 119;BA.debugLine="Return \"9T\"";
if (true) return "9T";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 121;BA.debugLine="Return \"mo\"";
if (true) return "mo";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 123;BA.debugLine="Return \"5L\"";
if (true) return "5L";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 125;BA.debugLine="Return \"9Y\"";
if (true) return "9Y";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 127;BA.debugLine="Return \"VE\"";
if (true) return "VE";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 129;BA.debugLine="Return \"cR\"";
if (true) return "cR";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 131;BA.debugLine="Return \"lX\"";
if (true) return "lX";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 133;BA.debugLine="Return \"2Z\"";
if (true) return "2Z";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 135;BA.debugLine="Return \"3x\"";
if (true) return "3x";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 137;BA.debugLine="Return \"pz\"";
if (true) return "pz";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 139;BA.debugLine="Return \"kg\"";
if (true) return "kg";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 141;BA.debugLine="Return \"oP\"";
if (true) return "oP";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 143;BA.debugLine="Return \"3F\"";
if (true) return "3F";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 145;BA.debugLine="Return \"3E\"";
if (true) return "3E";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 147;BA.debugLine="Return \"ST\"";
if (true) return "ST";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 149;BA.debugLine="Return \"pV\"";
if (true) return "pV";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 151;BA.debugLine="Return \"Zg\"";
if (true) return "Zg";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 153;BA.debugLine="Return \"s4\"";
if (true) return "s4";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 155;BA.debugLine="Return \"Ah\"";
if (true) return "Ah";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 157;BA.debugLine="Return \"bH\"";
if (true) return "bH";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 159;BA.debugLine="Return \"tP\"";
if (true) return "tP";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 161;BA.debugLine="Return \"qw\"";
if (true) return "qw";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 163;BA.debugLine="Return \"c7\"";
if (true) return "c7";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 165;BA.debugLine="Return \"oh\"";
if (true) return "oh";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 167;BA.debugLine="Return \"9b\"";
if (true) return "9b";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 169;BA.debugLine="Return \"q0\"";
if (true) return "q0";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 171;BA.debugLine="Return \"lW\"";
if (true) return "lW";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 173;BA.debugLine="Return \"su\"";
if (true) return "su";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 175;BA.debugLine="Return \"bS\"";
if (true) return "bS";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 177;BA.debugLine="Return \"0p\"";
if (true) return "0p";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 179;BA.debugLine="Return \"GC\"";
if (true) return "GC";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 181;BA.debugLine="Return \"bc\"";
if (true) return "bc";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 183;BA.debugLine="Return \"YA\"";
if (true) return "YA";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 185;BA.debugLine="Return \"4i\"";
if (true) return "4i";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 187;BA.debugLine="Return \"N9\"";
if (true) return "N9";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 189;BA.debugLine="Return \"i8\"";
if (true) return "i8";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 191;BA.debugLine="Return \"xK\"";
if (true) return "xK";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 193;BA.debugLine="Return \"I3\"";
if (true) return "I3";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 195;BA.debugLine="Return \"2J\"";
if (true) return "2J";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 197;BA.debugLine="Return \"Nc\"";
if (true) return "Nc";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 199;BA.debugLine="Return \"m7\"";
if (true) return "m7";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 201;BA.debugLine="Return \"0J\"";
if (true) return "0J";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 203;BA.debugLine="Return \"7S\"";
if (true) return "7S";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 205;BA.debugLine="Return \"QK\"";
if (true) return "QK";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 207;BA.debugLine="Return \"1f\"";
if (true) return "1f";
 }else if((_v).equals("'")) { 
 //BA.debugLineNum = 209;BA.debugLine="Return \"PF\"";
if (true) return "PF";
 }else if((_v).equals("6")) { 
 //BA.debugLineNum = 211;BA.debugLine="Return \"si\"";
if (true) return "si";
 }else {
 //BA.debugLineNum = 213;BA.debugLine="Return v & v";
if (true) return _v+_v;
 };
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
}
