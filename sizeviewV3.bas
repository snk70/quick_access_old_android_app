Type=StaticCode
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'1 : SetPX
'2 : SetPastScreenSizetoInche
'3 : SetSizeViews
Sub Process_Globals
Dim inc As Float
Dim t,l,w,h As Float
Dim WidthAct,HeightAct As Float
Dim PastWidthScreen,PastHeightScreen As Float
Dim DV_Width,DV_Height As Float
End Sub


Sub SetPX(Wdt As Float,Hig As Float,PastScreenWidth_PX As Float,PastScreenHeight_PX As Float)
DV_Width=PastScreenWidth_PX
DV_Height=PastScreenHeight_PX
'''''''''''''''''''''''''''''
WidthAct=Wdt
HeightAct=Hig
'''''''''''''''''''''''''''''
Dim f1,f2,f3 As Float
Dim vatar As Float
vatar=GetDeviceLayoutValues.ApproximateScreenSize
f1=(Hig*Hig)+(Wdt*Wdt)
f2=Sqrt(f1)
f3=f2/vatar
inc=f3
End Sub



Sub SetPastScreenSizetoInche(Wdt As Float,Hig As Float)
PastWidthScreen=Wdt
PastHeightScreen=Hig
End Sub

Sub SetSizeViews(View As View,tv As Float,lv As Float,hv As Float,wv As Float,FontSize As Float)
sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)
End Sub

Sub SetSize_btn(View As Button,tv As Float,lv As Float,hv As Float,wv As Float,FontSize As Float)
sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)
View.TextSize=fsz(View.Width,View.Height,GetPastWidth(View.Width),GetPastHeight(View.Height),FontSize)
End Sub

Sub SetSize_txt(View As EditText,tv As Float,lv As Float,hv As Float,wv As Float,FontSize As Float)
sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)
View.TextSize=fsz(View.Width,View.Height,GetPastWidth(View.Width),GetPastHeight(View.Height),FontSize)
End Sub

Sub SetSize_ScrolView(View As ScrollView,tv As Float,lv As Float,hv As Float,wv As Float,FontSize As Float)
sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)
End Sub

Sub SetSize_lbl_Views(View As Label,tv As Float,lv As Float,hv As Float,wv As Float,FontSize As Float)
sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)
View.TextSize=fsz(View.Width,View.Height,GetPastWidth(View.Width),GetPastHeight(View.Height),FontSize)
End Sub

Sub SetSize_img(View As ImageView,tv As Float,lv As Float,hv As Float,wv As Float,FontSize As Float)
sizemode(View,tv,lv,hv,wv,WidthAct,HeightAct)
End Sub

Sub getinch(px As Float)
Dim h As Float
h=px/inc
Return h
End Sub

Private Sub fsz(wdh As Float,hgh As Float,szw As Float,szh As Float,fs As Int) As Int
Dim fs1,fs2,fs3 As Float
Dim n1,n2 As Float
''''''
fs1=getinch(wdh)
fs3=getinch(hgh)
n1=fs1/szw
n2=fs3/szh

If n1>n2 Then
fs2=n2*fs
Else If n2>n1 Then
fs2=n1*fs
Else If n1=n2 Then
fs2=n1*fs
End If

Return fs2*1.5773684210526315789473684210526
End Sub



Private Sub sizemode(View As View,tv As Float,lv As Float,hv As Float,wv As Float,xv As Float,yv As Float)
View.Top=tv*(yv/DV_Height)
View.Left=lv*(xv/DV_Width)
View.Width=wv*(xv/DV_Width)
View.Height=hv*(yv/DV_Height)
''''''''''''''''''''''''
t=View.Top
l=View.Left
w=View.Width
h=View.Height
End Sub


Private Sub GetPastWidth(px As Float) As Double

Return (PastWidthScreen/getinch(WidthAct))*getinch(px)
End Sub

Private Sub GetPastHeight(px As Float) As Double

Return (PastHeightScreen/getinch(HeightAct))*getinch(px)
End Sub