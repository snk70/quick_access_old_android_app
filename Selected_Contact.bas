Type=Activity
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
Dim F_Intent,L_Intent,Value_Intent As String
Dim contact_Added As Boolean=False
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim cnt_list As List
	Private ListView1 As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	
	ProgressDialogHide
	
	
	Activity.LoadLayout("Select_Contact_lv")
	ListView1.Width=Activity.Width
	ListView1.Height=Activity.Height
	ListView1.Left=0
	ListView1.Top=0
	
	
	Dim cons As Contacts2


	cnt_list.Initialize
	
	cnt_list=cons.GetAll(True,True)
	
	
	For i=0 To cnt_list.Size-1
		Dim cn As Contact
		cn=cnt_list.Get(i)
		ListView1.AddTwoLinesAndBitmap2(cn.DisplayName,cn.PhoneNumber,Null,cn.PhoneNumber)
	Next
	
		
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
		
	Dim msgdialog As InputDialog

	If msgdialog.Show("لطفا نام دلخواهی برای این دسترسی انتخاب کنید","","تأیید","لغو","",Null)=DialogResponse.CANCEL Then
		Return	
	End If
	
	Value_Intent="content://com.android.contacts/contacts/"&Value
	
	Dim cn As Contact
	cn=cnt_list.Get(Position)
	F_Intent=msgdialog.Input
	L_Intent=cn.DisplayName
	contact_Added=True
	StartActivity(Definition_Permissions_ACT)
	Activity.Finish
	
End Sub