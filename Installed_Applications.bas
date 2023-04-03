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


 Dim Intent_AddedToList_InstallApp As Boolean=False
 Dim IntentApp_URI,IntentApp_FN,IntentApp_LN As String

End Sub

Sub Globals

	Dim Package_Apps As PackageManager


	Private ListView1 As ListView
	Private EditText1 As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	ProgressDialogHide
	Activity.LoadLayout("LastPermission_Definition")
	EditText1.Visible=False

#Region SetSizes
	ListView1.Height=Activity.Height
	ListView1.Width=Activity.Width
#End Region


	Intent_AddedToList_InstallApp=False
	IntentApp_URI=""
	IntentApp_FN=""
	IntentApp_LN=""

	ListView1.Top=0
	ListView1.Height=Activity.Height


End Sub

Sub Activity_Resume
	ProgressDialogShow("لطفا کمی صبر کنید...")
	Dim List_Install_Apps As List
	
	List_Install_Apps.Initialize

	ListView1.Clear
	List_Install_Apps.Initialize
	List_Install_Apps=Package_Apps.GetInstalledPackages()

	For j=0 To List_Install_Apps.Size-1
		Try
			Dim bmp As BitmapDrawable
			bmp=Package_Apps.GetApplicationIcon(List_Install_Apps.Get(j))
			ListView1.AddTwoLinesAndBitmap2("				         				"&Package_Apps.GetApplicationLabel(List_Install_Apps.Get(j)),"				"&Package_Apps.GetVersionName(List_Install_Apps.Get(j)),bmp.Bitmap,List_Install_Apps.Get(j))
		Catch
		End Try
	Next




	ProgressDialogHide
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
	
	
	Dim msgdialog As InputDialog
	If msgdialog.Show("","لطفا نام دلخواهی برای دسترسی مورد نظرتان وارد کنید","تأیید","لغو","",Null)=DialogResponse.POSITIVE Then
		ProgressDialogShow("لطفا صبر کنید ...")
		IntentApp_FN=msgdialog.Input
		IntentApp_LN=Package_Apps.GetApplicationLabel(Value)
		IntentApp_URI=Value

		Intent_AddedToList_InstallApp=True
	
		StartActivity(Definition_Permissions_ACT)
		ProgressDialogHide
	
		Activity.Finish()
	
	
	End If


	
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		
		
		StartActivity(Definition_Permissions_ACT)
		Activity.Finish()
		
	End If
	
	Return True

End Sub