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

Dim Get_Path As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	Dim AriaFile As AriaFile
	Dim FileUtils As AriaFileUtils
	Private ListView1 As ListView
	Dim LastAddressLoaded As String  ' akharin addressi ke tuye list load shode
	Dim LastFileClicked As AriaFile  ' akharin file ya folderi ke tuye list rush click shode

End Sub

Sub Activity_Create(FirstTime As Boolean)
	If Rnd(0,10)=0 Then
		StartService(check_servis)
	End If
	'''''''''''''''''''''''''''''''''''''''''''''''
	Activity.LoadLayout("list")

	ListView1.FastScrollEnabled=True
	
	'	LoadPathToListView(FileUtils.DirRootExternalpath) ' Load kardane pushe aslie memory tuye listview
	LoadPathToListView(Get_Path)

End Sub
Sub Activity_Resume

End Sub
Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	
	 'LastFileClicked ro barabare meqdari mizarim k rush click shode 
	 '(meqdar be surate ye value as AriaFile bargasht dade mishe)
	LastFileClicked=Value
	'methode baz kardane file ya foldero ejra mikonim
	OpenFileOrFolder 

End Sub
Sub ListView1_ItemLongClick (Position As Int, Value As Object)
	
	'LastFileClicked ro barabare meqdari mizarim k rush click shode
	'(meqdar be surate ye value as AriaFile bargasht dade mishe)
	LastFileClicked=Value
	  
	Dim MsgText As String ="Name :"&CRLF&"  "&LastFileClicked.GetName&CRLF &"Size EN :"&CRLF&"  "&FileUtils.CalcSize_EN(LastFileClicked.Size)&CRLF &"Size FA :"&CRLF&"  "&FileUtils.CalcSize_FA(LastFileClicked.size) &CRLF&"Path :"&CRLF&"  "&LastFileClicked.GetPath&CRLF &"MimeType :"&CRLF&"  "&LastFileClicked.GetMimeType&CRLF &"LastModified :"&CRLF&"  "&DateTime.Date(LastFileClicked.LastModified)&CRLF &"IsHidden :"&CRLF&"  "&LastFileClicked.IsHidden&CRLF &"IsDirectory :"&CRLF&"  "&LastFileClicked.IsDirectory
	Dim r As Int
	r=Msgbox2(MsgText,"Details","SHARE","EDIT","DELETE",Null)
	If r=DialogResponse.POSITIVE Then
		Share
	Else If r=DialogResponse.NEGATIVE Then
		Delete
	Else If r=DialogResponse.CANCEL Then
		Edit
	End If
End Sub

Sub Delete
		Dim r As Int
		r=Msgbox2("Are you sure ?","Delete "&LastFileClicked.GetName,"DELETE","CANCEL","",Null)
		If r=DialogResponse.POSITIVE Then
			
			 'shart mizarim age item click shode ye folder bud az tariqe methode FileUtils.DeleteFolder
			 'folder ro pak mikonim dar qeyre in surat age item click shode ye file bud ba methode delete un ro pak mikonim
		If LastFileClicked.IsDirectory Then 
			FileUtils.DeleteFolder(LastFileClicked)
			Else
			LastFileClicked.Delete	
		End If
		ToastMessageShow("Deleted",False)
		LoadPathToListView(LastAddressLoaded)
		End If
End Sub

Sub Share
	'shart mizarim age item click shode file bud un ro be eshterak bezarim
	'in method GetIntentForShareFile -> intent eshterak gozari baraye file o behetun mide
	If LastFileClicked.IsFile Then
		StartActivity(FileUtils.GetIntentForShareFile(LastFileClicked,"Share With?"))
	Else
		'age barnamei emkane share file ro nadasht error mide pas inja error ronamayesh midim
		ToastMessageShow("emkane eshterak gozarie directory nist",False)
	End If
End Sub

Sub OpenFileOrFolder
Msgbox(LastFileClicked,"")
	'age item click shode ye directory ya hamun folder bud migim item hae dakhelesh ro
	'tuye listview namayesh bede
	If LastFileClicked.IsDirectory Then
		LoadPathToListView(LastFileClicked.GetPath)
		Else
			'age item file bud pas az tariqe methode zir un ro ejra mikonim
			Try
				'in method GetIntentForOpenFile -> intent ejra va ya edit file o be ma mide baraye ejra
				StartActivity(FileUtils.GetIntentForOpenFile(LastFileClicked,True))'true for view
			Catch
				'age barnamei emkane namayesh file ro nadasht error mide pas inja error ronamayesh midim
				ToastMessageShow("there is no app to handle this file",False)
			End Try
			
		
	End If
End Sub
Sub Edit
	'age item click shode ye file bud migim un ro az tariqe methode zir baraye edit shodan ejra kone
	If LastFileClicked.IsFile Then
	Try
		'in method GetIntentForOpenFile -> intent ejra va ya edit file o be ma mide baraye ejra
		StartActivity(FileUtils.GetIntentForOpenFile(LastFileClicked,False)) ' false for edit
	Catch
		'age barnamei emkane edit kardane file ro nadasht error mide pas inja error ronamayesh midim
		ToastMessageShow("there is no app to handle this file",False)
	End Try
	Else
		'age item click shode ye folder bud migim emkane editesh nist
		ToastMessageShow("cant edit directory",False)
	End If
End Sub
Sub LoadPathToListView(Path As String)
	ListView1.Clear 'listview ro khali mikonim
	AriaFile.initialize2(Path) 'ba address dade shode ye ariafile initialize mikonim
	
	'ye array az file hae darune pushe be surate ariafile migirim yani ye array ke ajzash az ariafile tashkil shode
	'yani age 5ta file o pushe darun pushe asli dashte bashim 5ta ariafile behemun barmigardune
	Dim listfiles() As AriaFile = AriaFile.ListFiles
	 
	For i=0 To AriaFile.ListFiles.Length -1 'be ezae hame itemae dakhele folder miaim b listview itemaro ezafe mikonim
		If listfiles(i).IsDirectory Then 'inja migim age ariafile i ke behemun dade ye foldere
			'be listview itemaro ezafe mikonim,va meqdare return value ro migim khode ariafilesho zamane click behemun bargardune
			ListView1.AddTwoLinesAndBitmap2(listfiles(i).GetName,listfiles(i).GetParent,GetBitmap(listfiles(i)),listfiles(i))
			Else
			'be listview itemaro ezafe mikonim,va meqdare return value ro migim khode ariafilesho zamane click behemun bargardune
			ListView1.AddTwoLinesAndBitmap2(listfiles(i).GetName,FileUtils.CalcSize_EN(listfiles(i).Size),GetBitmap(listfiles(i)),listfiles(i))	
		End If
	Next

	LastAddressLoaded=Path 'LastAddressLoaded ro barabare address mizarim (ta zamani k file i ro delete kardim betunim ba hamin address listo dobare besazim va be ebarati refresh konim) 
	
	'inja miaim title activity ro barabare address mizarim faghat bejae "/storage/emulated/0" mizanim "SD CARD" va be jae / mizarim > (faqat baraye zibai va fahme rahat)
	'yani addressi mese addresse ziro injuri namayesh mide :
	'/storage/emulated/0/music/chavoshi    :::  SD CARD > music > chavoshi
	Activity.Title=LastAddressLoaded.Replace("/storage/emulated/0","SD CARD").Replace("/"," > ")
End Sub
Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
	'inja migim age parente akharin item click shode barabare "/storage/emulated" bud pas 
	'yani ma tuye sd cart qarar darim va emkane bargashtan nist pas payamo namayesh midim
													
													
													
													Try
															If LastFileClicked.GetParent=="/storage/emulated"  Then
'													ToastMessageShow("Not allowed",False)
StartActivity(Definition_Permissions_ACT)
Activity.Finish
													Else
													'inja migim address parente akharin file click shodaro neshun bede tuye listivew
													'parent hamun pusheie ke file ya folder tush qarar dare
													LoadPathToListView(LastFileClicked.GetParent)
													'akharin item ro barabare parente khodesh mizarim ta doare amale back betune anjam she
													LastFileClicked=LastFileClicked.GetParentFile
													End If												
													Catch
														Definition_Permissions_ACT.Current_Tab=0
														StartActivity(Definition_Permissions_ACT)
														Activity.Finish()
													End Try
													
													
													
End If
	Return True
End Sub
Sub GetBitmap(f As AriaFile) As Bitmap
	'inja az tariqe shart ha miaim nesbat be directory budan va ya mimetype file ye icon baraye listview bar migardunim
	If f.IsDirectory Then
		Return LoadBitmap(File.DirAssets,"folder.png")	
	Else If f.GetMimeType=Null Then
		Return LoadBitmap(File.DirAssets,"doc.png")
	else If f.GetMimeType.StartsWith("image") Then
		Return LoadBitmap(File.DirAssets,"pic.png")
	Else If f.GetMimeType.StartsWith("audio")  Then
		Return LoadBitmap(File.DirAssets,"music.png")
	Else
	   Return LoadBitmap(File.DirAssets,"doc.png")
	End If
End Sub
