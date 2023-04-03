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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub


Sub Globals
	''''''''''''''''''''''''''''
	Dim WebSite_URL As String ="http://www.modernplus.ir"
	'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	Private Contact_Label As Label
	Private Envelope_ImageView As ImageView
	Private Name_TXT As EditText
	Private Name_Label As Label
	Private Email_TXT As EditText
	Private Email_Label As Label
	Private Comment_TXT As EditText
	Private Comment_Label As Label
	Private Send_Button As Button
	Private Telegram_Logo_Button As Button
	Private Insta_Logo_Button As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("Contact_We_Loyout")
'	size_View.SetPX(Activity.Width,Activity.Height,240,320)
'	size_View.SetPastScreenSizetoInche(3.333,4.444)
	size_View.SetPX(Activity.Width,Activity.Height,240,320)
	size_View.SetPastScreenSizetoInche(3.333,4.444)
	
	
	size_View.SetSizeViews(Envelope_ImageView,8,177,44,37,0)
	size_View.SetSizeViews(Insta_Logo_Button,275,133,40,40,0)
	size_View.SetSizeViews(Telegram_Logo_Button,275,38,40,40,0)
	size_View.SetSize_lbl_Views(Contact_Label,15,27,28,145,14)
	size_View.SetSize_lbl_Views(Name_Label,61,182,30,32,14)
	size_View.SetSize_lbl_Views(Email_Label,96,182,30,32,14)
	size_View.SetSize_lbl_Views(Comment_Label,182,182,30,32,14)
	
	size_View.SetSize_txt(Name_TXT,63,38,135,30,10)
	size_View.SetSize_txt(Email_TXT,98,38,135,30,10)
	size_View.SetSize_txt(Comment_TXT,135,38,135,90,15)
	size_View.SetSize_btn(Send_Button,234,84,73,30,8)
	
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Sub Telegram_Logo_Button_Click
	Dim i As Intent
	i.Initialize(i.ACTION_VIEW,"https://t.me/joinchat/AAAAAD8mLhtdxctQkXR5Hg")
	StartActivity(i)
End Sub


Sub Insta_Logo_Button_Click
	Dim i As Intent
	i.Initialize(i.ACTION_VIEW,"https://www.instagram.com/modernplus.team/")
	StartActivity(i)
End Sub


Sub Contact_Label_Click
	Dim i As Intent
	i.Initialize(i.ACTION_VIEW,WebSite_URL)
	StartActivity(i)
End Sub


Sub Envelope_ImageView_Click
	Contact_Label_Click
End Sub


Sub Send_Button_Click
	
	If Name_TXT.Text="" Then
		Msgbox("لطفا نام خود را وارد نمایید","خطا")
	Else If Email_TXT.Text="" Then
		Msgbox("لطفا ایمیل خود را وارد نمایید","خطا")
	Else If Comment_TXT.Text="" Then
		Msgbox("لطفا پیام خود را وارد نمایید","خطا")
	Else If Regular_Validations.Email_validation(Email_TXT.Text)=False Then
		Msgbox("لطفا آدرس ایمیل را صحیح وارد نمایید","خطا")
	Else
		ProgressDialogShow2("لطفا کمی صبر کنید ...",False)
		Dim ht1 As HttpJob
		ht1.Initialize("ht",Me)
		ht1.PostString(WebSite_URL&"/Contact_Comment/Default.aspx","name="&Name_TXT.Text&"&mail="&Email_TXT.Text&"&cmt="&Comment_TXT.Text)
	End If
		
End Sub

Sub JobDone(Job As HttpJob)

ProgressDialogHide
If Job.Success Then

	If Job.GetString2("UTF-8").IndexOf("ثبت شد")<>-1 Then
		Msgbox("با تشکر از شما"&CRLF&"پیام و شما ارسال شد و در اسرع وقت بررسی میگردد","تأیید")
		Email_TXT.Text=""
		Name_TXT.Text=""
		Comment_TXT.Text=""
		Activity.Finish()
		StartActivity(Main)
	Else
	Msgbox("متأسفانه مشکلی در هنگام ارسال اطلاعات به وجود آمد ، لطفا بعدا دوباره تلاش کنید","خطا")
	End If

Else

Msgbox("متأسفانه مشکلی در هنگام ارسال اطلاعات به وجود آمد ، لطفا بعدا دوباره تلاش کنید","خطا")

End If

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
	StartActivity(Main)
	Activity.Finish
	Return True
	End If
End Sub