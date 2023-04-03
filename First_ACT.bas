Type=Activity
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

End Sub

Sub Activity_Create(FirstTime As Boolean)
	If File.Exists(File.DirInternal,"fsaccs/sqrgdb_lsit_Fani_t.txt") And File.Exists(File.DirInternal,"fsaccs/sqrgdb_lsit_Landy_C.txt") And File.Exists(File.DirInternal,"fsaccs/sqrgdb_Lostictevision_URL.txt") Then
		
		Definition_Permissions_ACT.Current_Tab=0
		StartActivity(Definition_Permissions_ACT)
		Activity.Finish
		
	Else
	
		File.Delete(File.DirInternal,"fsaccs/sqrgdb_lsit_Fani_t.txt")
		File.Delete(File.DirInternal,"fsaccs/sqrgdb_lsit_Landy_C.txt")
		File.Delete(File.DirInternal,"fsaccs/sqrgdb_Lostictevision_URL.txt")
		File.MakeDir(File.DirInternal,"fsaccs")
		
		StartActivity(Home_Act)
		Activity.Finish
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


