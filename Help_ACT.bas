Type=Activity
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
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
Activity.Title="راهنمای نرم افزار"
Dim wb_help As WebView
wb_help.Initialize("")
wb_help.LoadUrl("file:///android_asset/help_fast_accessibility.html")
Activity.AddView(wb_help,0,0,Activity.Width,Activity.Height)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		
		StartActivity(Home_Act)
		Activity.Finish()
		
	End If
	
	Return True
End Sub
