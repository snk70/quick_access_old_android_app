Type=StaticCode
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub



Sub Email_validation(Email_Address As String) As Boolean
	
	Dim valid As Boolean=True
	
	If Email_Address.IndexOf("@")=-1 Or Email_Address.IndexOf(".")=-1 Then
		
		valid=False
		
		
		
		
	Else
		
		
		Dim Atsain=Email_Address.IndexOf("@"),dat=Email_Address.IndexOf(".") As Int
		
		If Email_Address.IndexOf2("@",Atsain+1)<>-1 Then
			valid=False
		End If
		
		
		Do While Email_Address.IndexOf2(".",dat+1) <>-1
						
			dat=Email_Address.IndexOf2(".",dat+1)
						
		Loop
					
					
		If Atsain>dat Then
						
			valid=False
						
		End If
		
		
	End If
	
	
	Return valid
	
	
	
End Sub