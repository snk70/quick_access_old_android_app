Type=Activity
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Dim Intent_Added As Boolean=False


	Dim Intent_FN,Intent_LN,Intent_URL As String


End Sub


Sub Globals
	Dim lstv As ListView

End Sub



Sub Activity_Create(FirstTime As Boolean)
	
	lstv.Initialize("ls")
	'lstv.Top=0
	'lstv.Left=0
	'lstv.Width=Activity.Width
	'lstv.Height=Activity.Height
	lstv.Color=Colors.ARGB(255,142,24,46)
	Activity.AddView(lstv,0,0,Activity.Width,Activity.Height)


	Intent_Added=False
	Intent_FN=""
	Intent_LN=""
	Intent_URL=""

#Region اضافه کردن لیست بخش های مختلف تنظیمات گوشی
	lstv.AddSingleline2("		"&"تنظیمات","SETTINGS")
	lstv.AddSingleline2("		"&"دسترسی","ACCESSIBILITY_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات بیسیم و شبکه","AIRPLANE_MODE_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات WIFI","WIFI_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات بلوتوث","BLUETOOTH_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات IP","WIFI_IP_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات APN","APN_SETTINGS")
	lstv.AddSingleline2("		"&"گزینه های برنامه نویس","APPLICATION_DEVELOPMENT_SETTINGS")
	lstv.AddSingleline2("		"&"مدیریت برنامه های نصب شده","APPLICATION_SETTINGS")
	lstv.AddSingleline2("		"&"مدیریت تمام برنامه های نصب شده","MANAGE_ALL_APPLICATIONS_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمان رومینگ","DATA_ROAMING_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات تاریخ و زمان","DATE_SETTINGS")
	lstv.AddSingleline2("		"&"اطلاعات دستگاه","DEVICE_INFO_SETTINGS")
	lstv.AddSingleline2("		"&"صفحه نمایش","DISPLAY_SETTINGS")
	lstv.AddSingleline2("		"&"روش ورودی صفحه کلید","INPUT_METHOD_SETTINGS")
	lstv.AddSingleline2("		"&"زبان صفحه کلید","INPUT_METHOD_SUBTYPE_SETTINGS")
	lstv.AddSingleline2("		"&"مدیریت حافظه","INTERNAL_STORAGE_SETTINGS")
	lstv.AddSingleline2("		"&"زبان سیستم","LOCALE_SETTINGS")
	lstv.AddSingleline2("		"&"سرویس های مکانی","LOCATION_SOURCE_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات کارت حافظه","MEMORY_CARD_SETTINGS")
	lstv.AddSingleline2("		"&"ارائه دهندگان سرویس سیمکارت","NETWORK_OPERATOR_SETTINGS")
	lstv.AddSingleline2("NFC","NFCSHARING_SETTINGS")
	lstv.AddSingleline2("		"&"فایل پشتیبان و بازنشانی","PRIVACY_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات امنیتی","SECURITY_SETTINGS")
	lstv.AddSingleline2("		"&"تنظیمات صدا","SOUND_SETTINGS")
	lstv.AddSingleline2("		"&"همگام سازی","SYNC_SETTINGS")
	lstv.AddSingleLine2("		"&"دیکشنری کاربر","USER_DICTIONARY_SETTINGS")


#End Region


End Sub



Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub




Sub ls_ItemClick (Position As Int, Value As Object)
	

	Dim msgdialog As InputDialog
	
	
	Dim r As Int=Msgbox2("میخواهید این بخش رو به لیست دسترسی های سریع اضافه کنید یا اینکه اونو ببینید ؟","","اضافه کردن","لغو","دیدن این بخش",Null)
	
	If r=DialogResponse.POSITIVE Then
	
	If msgdialog.Show("لطفا نام دلخواهی برای این دسترسی انتخاب کنید","","تأیید","لغو","",Null)=DialogResponse.CANCEL Then
	Return	
	End If
		
		
		
		
		
		Intent_FN=msgdialog.Input
		
		Intent_URL="android.settings."&Value
		Intent_Added=True
		Activity.Finish()
		StartActivity(Definition_Permissions_ACT)
	Else If r=DialogResponse.NEGATIVE Then
	
	
			Try
					Dim DoIntent As Intent
					DoIntent.Initialize("android.settings."&Value, "")
					StartActivity(DoIntent)
			Catch
			Msgbox("متأسفانه این قسمت توسط دستگاه شما پشتیبانی نمیشود","اخطار")
			End Try
		
	End If
	
	
	
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		
		
		StartActivity(Definition_Permissions_ACT)
		Activity.Finish()
		
	End If
	Return True

End Sub


