package Fast_Accessibility.ModernPl;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class size_view301 {
private static size_view301 mostCurrent = new size_view301();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static float _inc = 0f;
public static float _t = 0f;
public static float _l = 0f;
public static float _w = 0f;
public static float _h = 0f;
public static float _widthact = 0f;
public static float _heightact = 0f;
public static float _pastwidthscreen = 0f;
public static float _pastheightscreen = 0f;
public static float _dv_width = 0f;
public static float _dv_height = 0f;
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
public Fast_Accessibility.ModernPl.interchange_stn _interchange_stn = null;
public static int  _fsz(anywheresoftware.b4a.BA _ba,float _wdh,float _hgh,float _szw,float _szh,int _fs) throws Exception{
float _fs1 = 0f;
float _fs2 = 0f;
float _fs3 = 0f;
float _n1 = 0f;
float _n2 = 0f;
 //BA.debugLineNum = 75;BA.debugLine="Private Sub fsz(wdh As Float,hgh As Float,szw As F";
 //BA.debugLineNum = 76;BA.debugLine="Dim fs1,fs2,fs3 As Float";
_fs1 = 0f;
_fs2 = 0f;
_fs3 = 0f;
 //BA.debugLineNum = 77;BA.debugLine="Dim n1,n2 As Float";
_n1 = 0f;
_n2 = 0f;
 //BA.debugLineNum = 79;BA.debugLine="fs1=getinch(wdh)";
_fs1 = (float)(Double.parseDouble(_getinch(_ba,_wdh)));
 //BA.debugLineNum = 80;BA.debugLine="fs3=getinch(hgh)";
_fs3 = (float)(Double.parseDouble(_getinch(_ba,_hgh)));
 //BA.debugLineNum = 81;BA.debugLine="n1=fs1/szw";
_n1 = (float) (_fs1/(double)_szw);
 //BA.debugLineNum = 82;BA.debugLine="n2=fs3/szh";
_n2 = (float) (_fs3/(double)_szh);
 //BA.debugLineNum = 84;BA.debugLine="If n1>n2 Then";
if (_n1>_n2) { 
 //BA.debugLineNum = 85;BA.debugLine="fs2=n2*fs";
_fs2 = (float) (_n2*_fs);
 }else if(_n2>_n1) { 
 //BA.debugLineNum = 87;BA.debugLine="fs2=n1*fs";
_fs2 = (float) (_n1*_fs);
 }else if(_n1==_n2) { 
 //BA.debugLineNum = 89;BA.debugLine="fs2=n1*fs";
_fs2 = (float) (_n1*_fs);
 };
 //BA.debugLineNum = 92;BA.debugLine="Return fs2*1.5773684210526315789473684210526";
if (true) return (int) (_fs2*1.5773684210526315789473684210526);
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return 0;
}
public static String  _getinch(anywheresoftware.b4a.BA _ba,float _px) throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Sub getinch(px As Float)";
 //BA.debugLineNum = 70;BA.debugLine="Dim h As Float";
_h = 0f;
 //BA.debugLineNum = 71;BA.debugLine="h=px/inc";
_h = (float) (_px/(double)_inc);
 //BA.debugLineNum = 72;BA.debugLine="Return h";
if (true) return BA.NumberToString(_h);
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return "";
}
public static double  _getpastheight(anywheresoftware.b4a.BA _ba,float _px) throws Exception{
 //BA.debugLineNum = 115;BA.debugLine="Private Sub GetPastHeight(px As Float) As Double";
 //BA.debugLineNum = 117;BA.debugLine="Return (PastHeightScreen/getinch(HeightAct))*getin";
if (true) return (_pastheightscreen/(double)(double)(Double.parseDouble(_getinch(_ba,_heightact))))*(double)(Double.parseDouble(_getinch(_ba,_px)));
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return 0;
}
public static double  _getpastwidth(anywheresoftware.b4a.BA _ba,float _px) throws Exception{
 //BA.debugLineNum = 110;BA.debugLine="Private Sub GetPastWidth(px As Float) As Double";
 //BA.debugLineNum = 112;BA.debugLine="Return (PastWidthScreen/getinch(WidthAct))*getinch";
if (true) return (_pastwidthscreen/(double)(double)(Double.parseDouble(_getinch(_ba,_widthact))))*(double)(Double.parseDouble(_getinch(_ba,_px)));
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 4;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Dim inc As Float";
_inc = 0f;
 //BA.debugLineNum = 6;BA.debugLine="Dim t,l,w,h As Float";
_t = 0f;
_l = 0f;
_w = 0f;
_h = 0f;
 //BA.debugLineNum = 7;BA.debugLine="Dim WidthAct,HeightAct As Float";
_widthact = 0f;
_heightact = 0f;
 //BA.debugLineNum = 8;BA.debugLine="Dim PastWidthScreen,PastHeightScreen As Float";
_pastwidthscreen = 0f;
_pastheightscreen = 0f;
 //BA.debugLineNum = 9;BA.debugLine="Dim DV_Width,DV_Height As Float";
_dv_width = 0f;
_dv_height = 0f;
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _setpastscreensizetoinche(anywheresoftware.b4a.BA _ba,float _wdt,float _hig) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub SetPastScreenSizetoInche(Wdt As Float,Hig As F";
 //BA.debugLineNum = 32;BA.debugLine="PastWidthScreen=Wdt";
_pastwidthscreen = _wdt;
 //BA.debugLineNum = 33;BA.debugLine="PastHeightScreen=Hig";
_pastheightscreen = _hig;
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _setpx(anywheresoftware.b4a.BA _ba,float _wdt,float _hig,float _pastscreenwidth_px,float _pastscreenheight_px) throws Exception{
float _f1 = 0f;
float _f2 = 0f;
float _f3 = 0f;
float _vatar = 0f;
 //BA.debugLineNum = 13;BA.debugLine="Sub SetPX(Wdt As Float,Hig As Float,PastScreenWidt";
 //BA.debugLineNum = 14;BA.debugLine="DV_Width=PastScreenWidth_PX";
_dv_width = _pastscreenwidth_px;
 //BA.debugLineNum = 15;BA.debugLine="DV_Height=PastScreenHeight_PX";
_dv_height = _pastscreenheight_px;
 //BA.debugLineNum = 17;BA.debugLine="WidthAct=Wdt";
_widthact = _wdt;
 //BA.debugLineNum = 18;BA.debugLine="HeightAct=Hig";
_heightact = _hig;
 //BA.debugLineNum = 20;BA.debugLine="Dim f1,f2,f3 As Float";
_f1 = 0f;
_f2 = 0f;
_f3 = 0f;
 //BA.debugLineNum = 21;BA.debugLine="Dim vatar As Float";
_vatar = 0f;
 //BA.debugLineNum = 22;BA.debugLine="vatar=GetDeviceLayoutValues.ApproximateScreenSize";
_vatar = (float) (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(_ba).getApproximateScreenSize());
 //BA.debugLineNum = 23;BA.debugLine="f1=(Hig*Hig)+(Wdt*Wdt)";
_f1 = (float) ((_hig*_hig)+(_wdt*_wdt));
 //BA.debugLineNum = 24;BA.debugLine="f2=Sqrt(f1)";
_f2 = (float) (anywheresoftware.b4a.keywords.Common.Sqrt(_f1));
 //BA.debugLineNum = 25;BA.debugLine="f3=f2/vatar";
_f3 = (float) (_f2/(double)_vatar);
 //BA.debugLineNum = 26;BA.debugLine="inc=f3";
_inc = _f3;
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _setsize_btn(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ButtonWrapper _view,float _tv,float _lv,float _wv,float _hv,float _fontsize) throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Sub SetSize_btn(View As Button,tv As Float,lv As F";
 //BA.debugLineNum = 41;BA.debugLine="sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)";
_sizemode(_ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_view.getObject())),_tv,_lv,_hv,_wv,_widthact,_heightact);
 //BA.debugLineNum = 42;BA.debugLine="View.TextSize=fsz(View.Width,View.Height,GetPastWi";
_view.setTextSize((float) (_fsz(_ba,(float) (_view.getWidth()),(float) (_view.getHeight()),(float) (_getpastwidth(_ba,(float) (_view.getWidth()))),(float) (_getpastheight(_ba,(float) (_view.getHeight()))),(int) (_fontsize))));
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _setsize_checkbox(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _view,float _tv,float _lv,float _wv,float _hv,float _fontsize) throws Exception{
 //BA.debugLineNum = 45;BA.debugLine="Sub SetSize_Checkbox(View As CheckBox,tv As Float,";
 //BA.debugLineNum = 46;BA.debugLine="sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)";
_sizemode(_ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_view.getObject())),_tv,_lv,_hv,_wv,_widthact,_heightact);
 //BA.debugLineNum = 47;BA.debugLine="View.TextSize=fsz(View.Width,View.Height,GetPastWi";
_view.setTextSize((float) (_fsz(_ba,(float) (_view.getWidth()),(float) (_view.getHeight()),(float) (_getpastwidth(_ba,(float) (_view.getWidth()))),(float) (_getpastheight(_ba,(float) (_view.getHeight()))),(int) (_fontsize))));
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _setsize_img(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ImageViewWrapper _view,float _tv,float _lv,float _wv,float _hv) throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Sub SetSize_img(View As ImageView,tv As Float,lv A";
 //BA.debugLineNum = 56;BA.debugLine="sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)";
_sizemode(_ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_view.getObject())),_tv,_lv,_hv,_wv,_widthact,_heightact);
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _setsize_lbl_views(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _view,float _tv,float _lv,float _hv,float _wv,float _fontsize) throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub SetSize_lbl_Views(View As Label,tv As Float,lv";
 //BA.debugLineNum = 64;BA.debugLine="sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)";
_sizemode(_ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_view.getObject())),_tv,_lv,_hv,_wv,_widthact,_heightact);
 //BA.debugLineNum = 65;BA.debugLine="View.TextSize=fsz(View.Width,View.Height,GetPastWi";
_view.setTextSize((float) (_fsz(_ba,(float) (_view.getWidth()),(float) (_view.getHeight()),(float) (_getpastwidth(_ba,(float) (_view.getWidth()))),(float) (_getpastheight(_ba,(float) (_view.getHeight()))),(int) (_fontsize))));
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _setsize_seekbar(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.SeekBarWrapper _view,float _tv,float _lv,float _wv,float _hv) throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub SetSize_seekbar(View As SeekBar,tv As Float,lv";
 //BA.debugLineNum = 60;BA.debugLine="sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)";
_sizemode(_ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_view.getObject())),_tv,_lv,_hv,_wv,_widthact,_heightact);
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _setsize_txt(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.EditTextWrapper _view,float _tv,float _lv,float _wv,float _hv,float _fontsize) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub SetSize_TXT(View As EditText,tv As Float,lv As";
 //BA.debugLineNum = 51;BA.debugLine="sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)";
_sizemode(_ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_view.getObject())),_tv,_lv,_hv,_wv,_widthact,_heightact);
 //BA.debugLineNum = 52;BA.debugLine="View.TextSize=fsz(View.Width,View.Height,GetPastWi";
_view.setTextSize((float) (_fsz(_ba,(float) (_view.getWidth()),(float) (_view.getHeight()),(float) (_getpastwidth(_ba,(float) (_view.getWidth()))),(float) (_getpastheight(_ba,(float) (_view.getHeight()))),(int) (_fontsize))));
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _setsizeviews(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _view,float _tv,float _lv,float _wv,float _hv,float _fontsize) throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub SetSizeViews(View As View,tv As Float,lv As Fl";
 //BA.debugLineNum = 37;BA.debugLine="sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)";
_sizemode(_ba,_view,_tv,_lv,_hv,_wv,_widthact,_heightact);
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _sizemode(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _view,float _tv,float _lv,float _hv,float _wv,float _xv,float _yv) throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Private Sub sizemode(View As View,tv As Float,lv A";
 //BA.debugLineNum = 98;BA.debugLine="View.Top=tv*(yv/DV_Height)";
_view.setTop((int) (_tv*(_yv/(double)_dv_height)));
 //BA.debugLineNum = 99;BA.debugLine="View.Left=lv*(xv/DV_Width)";
_view.setLeft((int) (_lv*(_xv/(double)_dv_width)));
 //BA.debugLineNum = 100;BA.debugLine="View.Width=wv*(xv/DV_Width)";
_view.setWidth((int) (_wv*(_xv/(double)_dv_width)));
 //BA.debugLineNum = 101;BA.debugLine="View.Height=hv*(yv/DV_Height)";
_view.setHeight((int) (_hv*(_yv/(double)_dv_height)));
 //BA.debugLineNum = 103;BA.debugLine="t=View.Top";
_t = (float) (_view.getTop());
 //BA.debugLineNum = 104;BA.debugLine="l=View.Left";
_l = (float) (_view.getLeft());
 //BA.debugLineNum = 105;BA.debugLine="w=View.Width";
_w = (float) (_view.getWidth());
 //BA.debugLineNum = 106;BA.debugLine="h=View.Height";
_h = (float) (_view.getHeight());
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
}
