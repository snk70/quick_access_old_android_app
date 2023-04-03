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

'شماره جدول
	Dim cmt_Table As String="14806612140190"
	Dim WebSite_URL As String ="http://www.modernplus.ir"
'لینک پست اپلیکیشن
	Dim post_Address As String=WebSite_URL&"/"&"posts/fast_accessibility.php"

End Sub

Sub Globals
	
	Private Contact_Label As Label
	Private Envelope_ImageView As ImageView
	Private Name_TXT As EditText
	Private Name_Label As Label
	Private Email_TXT As EditText
	Private Email_Label As Label
	Private Comment_TXT As EditText
	Private Comment_Label As Label
	Private Send_Button As Button
	Private btn_View_Comments As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("Send_Comment_Loyout")
	size_View.SetPX(Activity.Width,Activity.Height,240,320)
	size_View.SetPastScreenSizetoInche(3.333,4.444)
	
	size_View.SetSizeViews(Envelope_ImageView,8,177,44,37,0)
	size_View.SetSize_lbl_Views(Contact_Label,15,27,28,145,14)
	size_View.SetSize_lbl_Views(Name_Label,61,182,30,32,14)
	size_View.SetSize_lbl_Views(Email_Label,96,182,30,32,14)
	size_View.SetSize_lbl_Views(Comment_Label,182,182,30,32,14)
	
	size_View.SetSize_txt(Name_TXT,63,38,135,30,10)
	size_View.SetSize_txt(Email_TXT,98,38,135,30,10)
	size_View.SetSize_txt(Comment_TXT,135,38,135,90,15)
	size_View.SetSize_btn(Send_Button,234,141,73,30,11)
	size_View.SetSize_btn(btn_View_Comments,234,38,73,30,11)

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

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
		
		ht1.PostString(WebSite_URL&"/save_comments/Default.aspx","name="&Name_TXT.Text&"&mail="&Email_TXT.Text&"&cmt="&Comment_TXT.Text&"&tb="&cmt_Table)
	End If
	
End Sub

Sub JobDone(Job As HttpJob)

	ProgressDialogHide
	If Job.Success Then

		If Job.GetString2("UTF-8").IndexOf("ثبت شد")<>-1 Then
			If	Msgbox2("با تشکر از شما"&CRLF&CRLF&"نظر شما ثبت شد و در اسرع وقت بررسی میگردد"&CRLF&CRLF&"برای مشاهده سایر دیدگاه ها کلیک کنید","تأیید"&CRLF&CRLF,"مشاهده سایر دیدگاه ها","فعلا نه","",Null)=DialogResponse.POSITIVE Then
				btn_View_Comments_Click
				Email_TXT.Text=""
				Name_TXT.Text=""
				Comment_TXT.Text=""
			Else
				StartActivity(Main)
				Activity.Finish
			End If
		Else
			Msgbox("متأسفانه مشکلی در هنگام ارسال اطلاعات به وجود آمده است ، لطفا بعدا دوباره تلاش کنید","خطا")
		End If

	Else

		Msgbox("متأسفانه مشکلی در هنگام ارسال اطلاعات به وجود آمده است ، لطفا بعدا دوباره تلاش کنید","خطا")

	End If

End Sub


Sub btn_View_Comments_Click
	Dim i As Intent
	i.Initialize(i.ACTION_VIEW,post_Address)
	StartActivity(i)
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		StartActivity(Main)
		Activity.Finish
		Return True
	End If
End Sub
